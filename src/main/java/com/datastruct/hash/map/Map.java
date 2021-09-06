package com.datastruct.hash.map;

public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V remove(K key);

    V get(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    abstract class Visitor<K, V> {
        boolean stop;

        public abstract boolean visit(K key, V value);
    }
}
