/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.mods.shareddata;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

/**
 * Sometimes it is desirable to share immutable data between different event
 * loops, for example to implement a cache of data.
 * <p>
 * This class allows instances of shared data structures to be looked up and
 * used from different event loops.
 * <p>
 * The data structures themselves will only allow certain data types to be
 * stored into them. This shields you from worrying about any thread safety
 * issues might occur if mutable objects were shared between event loops.
 * <p>
 * The following types can be stored in a shareddata data structure:
 * <p>
 *
 * <pre>
 *   {@link String}
 *   {@link Integer}
 *   {@link Long}
 *   {@link Double}
 *   {@link Float}
 *   {@link Short}
 *   {@link Byte}
 *   {@link Character}
 *   {@code byte[]} - this will be automatically copied, and the copy will be stored in the structure.
 *   {@link org.vertx.java.core.buffer.Buffer} - this will be automatically copied, and the copy will be stored in the
 *   structure.
 * </pre>
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public abstract class SharedData {

  private static final Logger log = LoggerFactory.getLogger(SharedData.class);

  private final ConcurrentMap<Object, SharedMap<?, ?>> maps = new ConcurrentHashMap<>();
  private final ConcurrentMap<Object, SharedMultiMap<?, ?>> multiMaps = new ConcurrentHashMap<>();
  private final ConcurrentMap<Object, SharedSet<?>> sets = new ConcurrentHashMap<>();
  private final ConcurrentMap<Object, SharedList<?>> lists = new ConcurrentHashMap<>();

  /**
   * Return a {@code Map} with the specific {@code name}. All invocations of
   * this method with the same value of {@code name} are guaranteed to return
   * the same {@code Map} instance.
   * <p>
   */
  public <K, V> ConcurrentMap<K, V> getMap(String name) {
    SharedMap<K, V> map = (SharedMap<K, V>) maps.get(name);
    if (map == null) {
      map = createSharedMap(name);
      SharedMap prev = maps.putIfAbsent(name, map);
      if (prev != null) {
        map = prev;
      }
    }
    return map;
  }

  /**
   * Return a {@code Set} with the specific {@code name}. All invocations of
   * this method with the same value of {@code name} are guaranteed to return
   * the same {@code Set} instance.
   * <p>
   */
  public <E> List<E> getList(String name) {
    SharedList<E> list = (SharedList<E>) lists.get(name);
    if (list == null) {
      list = createSharedList(name);
      SharedList prev = lists.putIfAbsent(name, list);
      if (prev != null) {
        list = prev;
      }
    }
    return list;
  }

  /**
   * Return a {@code Set} with the specific {@code name}. All invocations of
   * this method with the same value of {@code name} are guaranteed to return
   * the same {@code Set} instance.
   * <p>
   */
  public <E> Set<E> getSet(String name) {
    SharedSet<E> set = (SharedSet<E>) sets.get(name);
    if (set == null) {
      set = createSharedSet(name);
      SharedSet prev = sets.putIfAbsent(name, set);
      if (prev != null) {
        set = prev;
      }
    }
    return set;
  }

  /**
   * Return a {@code MultiMap} with the specific {@code name}. All invocations
   * of this method with the same value of {@code name} are guaranteed to return
   * the same {@code Set} instance.
   * <p>
   */
  public <K, V> MultiMap<K, V> getMultiMap(String name) {
    SharedMultiMap<K, V> map = (SharedMultiMap<K, V>) multiMaps.get(name);
    if (map == null) {
      map = createSharedMultiMap(name);
      SharedMultiMap prev = multiMaps.putIfAbsent(name, map);
      if (prev != null) {
        map = prev;
      }
    }
    return map;
  }

  /**
   * Remove the {@code Map} with the specified {@code name}.
   */
  public boolean removeMap(Object name) {
    SharedMap<?, ?> map = this.maps.remove(name);

    if (map == null) {
      return false;
    }

    map.destroy();

    return true;
  }

  /**
   * Remove the {@code Set} with the specified {@code name}.
   */
  public boolean removeSet(Object name) {
    SharedSet<?> set = this.sets.remove(name);

    if (set == null) {
      return false;
    }

    set.destroy();

    return true;
  }

  /**
   * Remove the {@code Set} with the specified {@code name}.
   */
  public boolean removeList(Object name) {
    SharedList<?> list = this.lists.remove(name);

    if (list == null) {
      return false;
    }

    list.destroy();

    return true;
  }

  /**
   * Remove the {@code MultiMap} with the specified {@code name}.
   */
  public boolean removeMultiMap(Object name) {
    SharedMultiMap<?, ?> map = this.multiMaps.remove(name);

    if (map == null) {
      return false;
    }

    map.destroy();

    return true;
  }

  /**
   * Creates and Returns a SharedMap
   */
  protected <K, V> SharedMap<K, V> createSharedMap(String mapName) {
    return createSharedMap(mapName, -1);
  }

  protected abstract <K, V> SharedMap<K, V> createSharedMap(String mapName, int timeToLive);
  
  /**
   * Creates and Returns a SharedMultiMap
   */
  protected <K, V> SharedMultiMap<K, V> createSharedMultiMap(String mapName) {
    return createSharedMultiMap(mapName, -1);
  }

  protected abstract <K, V> SharedMultiMap<K, V> createSharedMultiMap(String mapName, int timeToLive);

  /**
   * Creates and Returns a SharedSet
   */
  protected <E> SharedSet<E> createSharedSet(String mapName) {
    return createSharedSet(mapName, -1);
  }
  protected abstract <E> SharedSet<E> createSharedSet(String setName, int timeToLive);

  /**
   * Creates and Returns a SharedSet
   */
  protected <E> SharedList<E> createSharedList(String listName) {
    return createSharedList(listName, -1);
  }
  protected abstract <E> SharedList<E> createSharedList(String listName, int timeToLive);


}
