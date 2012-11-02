package org.vertx.mods.shareddata;

import java.util.Map;

class SharedDataEntry<K, V> implements Map.Entry<K, V> {

  final Map.Entry<K, V> internalEntry;

  public SharedDataEntry(Map.Entry<K, V> internalEntry) {
    this.internalEntry = internalEntry;
  }

  public K getKey() {
    return Checker.copyIfRequired(internalEntry.getKey());
  }

  public V getValue() {
    return Checker.copyIfRequired(internalEntry.getValue());
  }

  public V setValue(V value) {
    V old = internalEntry.getValue();
    Checker.checkType(value);
    internalEntry.setValue(value);
    return old;
  }
}
