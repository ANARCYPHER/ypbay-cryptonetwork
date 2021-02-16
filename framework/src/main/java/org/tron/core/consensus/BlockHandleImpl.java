package org.ypbay.core.consensus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ypbay.common.backup.BackupManager;
import org.ypbay.common.backup.BackupManager.BackupStatusEnum;
import org.ypbay.consensus.Consensus;
import org.ypbay.consensus.base.BlockHandle;
import org.ypbay.consensus.base.Param.Miner;
import org.ypbay.consensus.base.State;
import org.ypbay.core.capsule.BlockCapsule;
import org.ypbay.core.db.Manager;
import org.ypbay.core.net.TronNetService;
import org.ypbay.core.net.message.BlockMessage;

@Slf4j(topic = "consensus")
@Component
public class BlockHandleImpl implements BlockHandle {

  @Autowired
  private Manager manager;

  @Autowired
  private BackupManager backupManager;

  @Autowired
  private TronNetService tronNetService;

  @Autowired
  private Consensus consensus;

  @Override
  public State getState() {
    if (!backupManager.getStatus().equals(BackupStatusEnum.MASTER)) {
      return State.BACKUP_IS_NOT_MASTER;
    }
    return State.OK;
  }

  public Object getLock() {
    return manager;
  }

  public BlockCapsule produce(Miner miner, long blockTime, long timeout) {
    BlockCapsule blockCapsule = manager.generateBlock(miner, blockTime, timeout);
    if (blockCapsule == null) {
      return null;
    }
    try {
      consensus.receiveBlock(blockCapsule);
      BlockMessage blockMessage = new BlockMessage(blockCapsule);
      tronNetService.fastForward(blockMessage);
      manager.pushBlock(blockCapsule);
      tronNetService.broadcast(blockMessage);
    } catch (Exception e) {
      logger.error("Handle block {} failed.", blockCapsule.getBlockId().getString(), e);
      return null;
    }
    return blockCapsule;
  }
}
