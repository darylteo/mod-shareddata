package org.vertx.mods.shareddata.impl;

import org.vertx.java.core.shareddata.SharedSet;

class DefaultSharedSet<E> extends SharedSet<E> {

  public DefaultSharedSet(String setName) {
    super(new DefaultSharedSetImpl<E>(), setName);
  }

  @Override
  public void destroy() {
    /* non-op */
  }
}
