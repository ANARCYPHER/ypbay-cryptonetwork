package org.ypbay.core.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.ypbay.common.overlay.server.Channel;
import org.ypbay.common.overlay.server.MessageQueue;
import org.ypbay.core.net.message.ypbayMessage;
import org.ypbay.core.net.peer.PeerConnection;

@Component
@Scope("prototype")
public class ypbayNetHandler extends SimpleChannelInboundHandler<ypbayonMessage> {

  protected PeerConnection peer;

  private MessageQueue msgQueue;

  @Autowired
  private ypbayNetService ypbayNetService;

  @Override
  public void channelRead0(final ChannelHandlerContext ctx, ypbayMessage msg) throws Exception {
    msgQueue.receivedMessage(msg);
    ypbayNetService.onMessage(peer, msg);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    peer.processException(cause);
  }

  public void setMsgQueue(MessageQueue msgQueue) {
    this.msgQueue = msgQueue;
  }

  public void setChannel(Channel channel) {
    this.peer = (PeerConnection) channel;
  }

}