package org.vertx.mods.shareddata.impl;

import org.vertx.java.core.shareddata.SharedData;
import org.vertx.java.core.shareddata.SharedList;
import org.vertx.java.core.shareddata.SharedMap;
import org.vertx.java.core.shareddata.SharedMultiMap;
import org.vertx.java.core.shareddata.SharedSet;

public class DefaultSharedData extends SharedData {

  @Override
  protected <K, V> SharedMap<K, V> createSharedMap(String mapName, int timeToLive) {
    return new DefaultSharedMap<>(mapName);
  }

  @Override
  protected <E> SharedSet<E> createSharedSet(String setName, int timeToLive) {
    return new DefaultSharedSet<>(setName);
  }

  @Override
  protected <E> SharedList<E> createSharedList(String listName, int timeToLive) {
    return new DefaultSharedList<>(listName);
  }

  @Override
  protected <K, V> SharedMultiMap<K, V> createSharedMultiMap(String mapName, int timeToLive) {
    return new DefaultSharedMultiMap<>(mapName);
  }

}
