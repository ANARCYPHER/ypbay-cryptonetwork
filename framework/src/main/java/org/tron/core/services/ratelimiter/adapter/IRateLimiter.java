package org.ypbay.core.services.ratelimiter.adapter;

import org.ypbay.core.services.ratelimiter.RuntimeData;

public interface IRateLimiter {

  boolean acquire(RuntimeData data);

}
