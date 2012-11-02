package org.vertx.mods.shareddata.impl.hazelcast;

import java.util.List;

import org.vertx.java.core.shareddata.SharedList;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;

public class HazelcastSharedList<E> extends SharedList<E> {

  private IList<E> list;

  public HazelcastSharedList(IList<E> list, String listName) {
    super(list, listName);
    this.list = list;
  }

  @Override
  public void destroy() {
    list.destroy();
  }

}
