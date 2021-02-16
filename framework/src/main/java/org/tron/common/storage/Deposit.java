package org.ypbay.common.storage;

import org.ypbay.common.runtime.vm.DataWord;
import org.ypbay.core.capsule.AccountCapsule;
import org.ypbay.core.capsule.AssetIssueCapsule;
import org.ypbay.core.capsule.BlockCapsule;
import org.ypbay.core.capsule.BytesCapsule;
import org.ypbay.core.capsule.ContractCapsule;
import org.ypbay.core.capsule.ProposalCapsule;
import org.ypbay.core.capsule.TransactionCapsule;
import org.ypbay.core.capsule.VotesCapsule;
import org.ypbay.core.capsule.WitnessCapsule;
import org.ypbay.core.db.Manager;
import org.ypbay.core.vm.program.Storage;
import org.ypbay.core.vm.repository.Key;
import org.ypbay.core.vm.repository.Value;
import org.ypbay.protos.Protocol;

public interface Deposit {

  Manager getDbManager();

  AccountCapsule createAccount(byte[] address, Protocol.AccountType type);

  AccountCapsule createAccount(byte[] address, String accountName, Protocol.AccountType type);

  AccountCapsule getAccount(byte[] address);

  WitnessCapsule getWitness(byte[] address);

  VotesCapsule getVotesCapsule(byte[] address);

  ProposalCapsule getProposalCapsule(byte[] id);

  BytesCapsule getDynamic(byte[] bytesKey);

  void deleteContract(byte[] address);

  void createContract(byte[] address, ContractCapsule contractCapsule);

  ContractCapsule getContract(byte[] address);

  void updateContract(byte[] address, ContractCapsule contractCapsule);

  void updateAccount(byte[] address, AccountCapsule accountCapsule);

  void saveCode(byte[] address, byte[] code);

  byte[] getCode(byte[] address);

  void putStorageValue(byte[] address, DataWord key, DataWord value);

  DataWord getStorageValue(byte[] address, DataWord key);

  Storage getStorage(byte[] address);

  long getBalance(byte[] address);

  long addBalance(byte[] address, long value);

  Deposit newDepositChild();

  void setParent(Deposit deposit);

  void commit();

  void putAccount(Key key, Value value);

  void putTransaction(Key key, Value value);

  void putBlock(Key key, Value value);

  void putWitness(Key key, Value value);

  void putCode(Key key, Value value);

  void putContract(Key key, Value value);

  void putStorage(Key key, Storage cache);

  void putVotes(Key key, Value value);

  void putProposal(Key key, Value value);

  void putDynamicProperties(Key key, Value value);

  void putAccountValue(byte[] address, AccountCapsule accountCapsule);

  void putVoteValue(byte[] address, VotesCapsule votesCapsule);

  void putProposalValue(byte[] address, ProposalCapsule proposalCapsule);

  void putDynamicPropertiesWithLatestProposalNum(long num);

  long getLatestProposalNum();

  long getWitnessAllowanceFrozenTime();

  long getMaintenanceTimeInterval();

  long getNextMaintenanceTime();

  long addTokenBalance(byte[] address, byte[] tokenId, long value);

  long getTokenBalance(byte[] address, byte[] tokenId);

  AssetIssueCapsule getAssetIssue(byte[] tokenId);

  TransactionCapsule getTransaction(byte[] trxHash);

  BlockCapsule getBlock(byte[] blockHash);

  byte[] getBlackHoleAddress();

  public AccountCapsule createNormalAccount(byte[] address);


}

