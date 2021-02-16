package org.ypbay.core.services.ratelimiter.adapter;

public interface IPreemptibleRateLimiter extends IRateLimiter {

  void release();
}
