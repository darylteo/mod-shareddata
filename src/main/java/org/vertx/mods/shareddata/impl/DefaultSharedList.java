package org.vertx.mods.shareddata.impl;

import java.util.LinkedList;

import org.vertx.java.core.shareddata.SharedList;

public class DefaultSharedList<E> extends SharedList<E> {

  public DefaultSharedList(String listName) {
    super(new LinkedList<E>(), listName);
  }

  @Override
  public void destroy() {
    /* non-op */
  }

}
