package org.ypbay.core.net.message;

import org.ypbay.core.capsule.PbftSignCapsule;
import org.ypbay.protos.Protocol.PBFTCommitResult;

public class PbftCommitMessage extends ypbayMessage {

  private PbftSignCapsule pbftSignCapsule;

  public PbftCommitMessage(byte[] data) {
    super(data);
    this.type = MessageTypes.PBFT_COMMIT_MSG.asByte();
    this.pbftSignCapsule = new PbftSignCapsule(data);
  }

  public PbftCommitMessage(PbftSignCapsule pbftSignCapsule) {
    data = pbftSignCapsule.getData();
    this.type = MessageTypes.PBFT_COMMIT_MSG.asByte();
    this.pbftSignCapsule = pbftSignCapsule;
  }

  public PBFTCommitResult getPBFTCommitResult() {
    return getPbftSignCapsule().getPbftCommitResult();
  }

  public PbftSignCapsule getPbftSignCapsule() {
    return pbftSignCapsule;
  }

  @Override
  public Class<?> getAnswerMessage() {
    return null;
  }
  
}
