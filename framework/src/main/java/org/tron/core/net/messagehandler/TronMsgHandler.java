package org.ypbay.core.net.messagehandler;

import org.ypbay.core.exception.P2pException;
import org.ypbay.core.net.message.TronMessage;
import org.ypbay.core.net.peer.PeerConnection;

public interface TronMsgHandler {

  void processMessage(PeerConnection peer, TronMessage msg) throws P2pException;

}
