package org.ypbay.core.net.message;

import org.ypbay.protos.Protocol;

public class ItemNotFound extends ypbayMessage {

  private org.ypbay.protos.Protocol.Items notFound;

  /**
   * means can not find this block or trx.
   */
  public ItemNotFound() {
    Protocol.Items.Builder itemsBuilder = Protocol.Items.newBuilder();
    itemsBuilder.setType(Protocol.Items.ItemType.ERR);
    notFound = itemsBuilder.build();
    this.type = MessageTypes.ITEM_NOT_FOUND.asByte();
    this.data = notFound.toByteArray();
  }

  @Override
  public String toString() {
    return "item not found";
  }

  @Override
  public Class<?> getAnswerMessage() {
    return null;
  }

}
