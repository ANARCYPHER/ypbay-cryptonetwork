package org.ypbay.core.services.ratelimiter.adapter;

import org.ypbay.core.services.ratelimiter.RuntimeData;
import org.ypbay.core.services.ratelimiter.strategy.QpsStrategy;

public class QpsRateLimiterAdapter implements IRateLimiter {

  private QpsStrategy strategy;

  public QpsRateLimiterAdapter(String paramString) {
    strategy = new QpsStrategy(paramString);
  }

  @Override
  public boolean acquire(RuntimeData data) {
    return strategy.acquire();
  }

}