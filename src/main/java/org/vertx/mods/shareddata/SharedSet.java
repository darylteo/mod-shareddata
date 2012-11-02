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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public abstract class SharedSet<E> implements Set<E> {

  private final Set<E> impl;
  private final String setName;

  public SharedSet(Set<E> impl, String setName){
     this.impl = impl;
     this.setName = setName;
  }

  @Override
  public int size() {
    return impl.size();
  }

  @Override
  public boolean isEmpty() {
    return impl.isEmpty();
  }

  @Override
  public boolean contains(Object value) {
    return impl.contains(value);
  }

  @Override
  public Iterator<E> iterator() {
    Set<E> copied = new HashSet<>();
    for (E e : this.impl) {
      copied.add(Checker.copyIfRequired(e));
    }
    return copied.iterator();
  }

  @Override
  public Object[] toArray() {
    return this.toArray(new Object[this.impl.size()]);
  }

  @Override
  public <T> T[] toArray(T[] a) {
    List<E> copied = new LinkedList<>();
    for (E e : this.impl) {
      copied.add(Checker.copyIfRequired(e));
    }
    return copied.toArray(a);
  }

  @Override
  public boolean add(E e) {
    Checker.checkType(e);
    return impl.add(e);
  }

  @Override
  public boolean remove(Object value) {
    return impl.remove(value);
  }

  @Override
  public boolean containsAll(Collection<?> values) {
    return impl.containsAll(values);
  }

  @Override
  public boolean addAll(Collection<? extends E> values) {
    for (E value : values){
      Checker.checkType(value);
    }

    return this.impl.addAll(values);
  }

  @Override
  public boolean retainAll(Collection<?> values) {
    return impl.retainAll(values);
  }

  @Override
  public boolean removeAll(Collection<?> values) {
    return impl.removeAll(values);
  }

  @Override
  public void clear() {
    impl.clear();
  }

  @Override
  public boolean equals(Object o) {
    return impl.equals(o);
  }

  @Override
  public int hashCode() {
    return impl.hashCode();
  }

  protected abstract void destroy();
}
