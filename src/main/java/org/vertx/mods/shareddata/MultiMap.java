package org.vertx.mods.shareddata;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

public interface MultiMap <K,V> {
  /* java.util.Map interface */
  public void clear();
  public boolean containsKey(K k);
  public boolean containsValue(Object o);
  public Set<Entry<K, V>> entrySet();
  public Collection<V> get(K k);
  public boolean isEmpty();
  public Set<K> keySet();
  public boolean put(K k, V v);
  public Collection<V> remove(Object o);
  public int size();
  public Collection<V> values();

  /* java.util.ConcurrentMap interface */
  public boolean remove(Object o1, Object o2);

  /* MultiMap interface */
  public boolean containsEntry(K k, V v);
  public int valueCount(K k);
}
