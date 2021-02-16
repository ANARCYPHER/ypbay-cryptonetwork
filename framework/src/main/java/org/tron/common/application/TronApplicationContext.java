package org.ypbay.common.application;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.ypbay.common.overlay.discover.DiscoverServer;
import org.ypbay.common.overlay.discover.node.NodeManager;
import org.ypbay.common.overlay.server.ChannelManager;
import org.ypbay.core.db.Manager;

public class ypbayApplicationContext extends AnnotationConfigApplicationContext {

  public ypbayApplicationContext() {
  }

  public ypbayApplicationContext(DefaultListableBeanFactory beanFactory) {
    super(beanFactory);
  }

  public ypbayApplicationContext(Class<?>... annotatedClasses) {
    super(annotatedClasses);
  }

  public ypbayApplicationContext(String... basePackages) {
    super(basePackages);
  }

  @Override
  public void destroy() {

    Application appT = ApplicationFactory.create(this);
    appT.shutdownServices();
    appT.shutdown();

    DiscoverServer discoverServer = getBean(DiscoverServer.class);
    discoverServer.close();
    ChannelManager channelManager = getBean(ChannelManager.class);
    channelManager.close();
    NodeManager nodeManager = getBean(NodeManager.class);
    nodeManager.close();

    Manager dbManager = getBean(Manager.class);
    dbManager.stopRePushThread();
    dbManager.stopRePushTriggerThread();
    super.destroy();
  }
}
