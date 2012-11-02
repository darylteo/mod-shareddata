package org.vertx.mods.shareddata.impl.hazelcast;

import org.vertx.mods.shareddata.SharedList;

import com.hazelcast.core.IList;

public class HazelcastSharedList<E> extends SharedList<E> {

  private final IList<E> list;

  public HazelcastSharedList(IList<E> list, String listName) {
    super(list, listName);
    this.list = list;
  }

  @Override
  public void destroy() {
    list.destroy();
  }

}
