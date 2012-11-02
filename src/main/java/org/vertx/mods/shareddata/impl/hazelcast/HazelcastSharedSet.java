package org.vertx.mods.shareddata.impl.hazelcast;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.vertx.java.core.shareddata.SharedSet;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ISet;

public class HazelcastSharedSet<E> extends SharedSet<E> {
  private final ISet<E> set;

  public HazelcastSharedSet(ISet<E> set, String setName) {
    super(set, setName);
    this.set = set;
  }

  @Override
  protected void destroy() {
    this.set.destroy();
  }
}
