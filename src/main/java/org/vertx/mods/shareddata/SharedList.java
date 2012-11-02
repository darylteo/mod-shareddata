package org.vertx.mods.shareddata;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class SharedList<E> implements List<E> {

  private List<E> impl;
  private String listName;

  public SharedList(List<E> impl, String listName) {
    this.impl = impl;
    this.listName = listName;
  }

  @Override
  public boolean add(E e) {
    Checker.checkType(e);
    return impl.add(e);
  }

  @Override
  public void add(int index, E e) {
    Checker.checkType(e);
    impl.add(index, e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    for (E value : c) {
      Checker.checkType(value);
    }

    return this.impl.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    for (E value : c) {
      Checker.checkType(value);
    }

    return this.impl.addAll(index, c);
  }

  @Override
  public void clear() {
    this.impl.clear();
  }

  @Override
  public boolean contains(Object o) {
    return this.impl.contains(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return this.containsAll(c);
  }

  @Override
  public E get(int index) {
    return this.impl.get(index);
  }

  @Override
  public int indexOf(Object o) {
    return this.impl.indexOf(o);
  }

  @Override
  public boolean isEmpty() {
    return this.impl.isEmpty();
  }

  @Override
  public Iterator<E> iterator() {
    return this.impl.iterator();
  }

  @Override
  public int lastIndexOf(Object o) {
    return this.impl.lastIndexOf(o);
  }

  @Override
  public ListIterator<E> listIterator() {
    return this.impl.listIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return this.impl.listIterator(index);
  }

  @Override
  public boolean remove(Object o) {
    return this.impl.remove(o);
  }

  @Override
  public E remove(int index) {
    return this.impl.remove(index);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.impl.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.impl.retainAll(c);
  }

  @Override
  public E set(int index, E element) {
    return this.impl.set(index, element);
  }

  @Override
  public int size() {
    return this.impl.size();
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return this.impl.subList(fromIndex, toIndex);
  }

  @Override
  public Object[] toArray() {
    return this.impl.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return this.impl.toArray(a);
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
