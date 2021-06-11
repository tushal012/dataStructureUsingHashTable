package com.bridgelabz;

import java.util.ArrayList;

public class MyLinkedHashMap<K, V> {
    private final int numberBuckets;
    ArrayList<MyLinkedList<K>> myBucketArray;

    public MyLinkedHashMap() {
        this.numberBuckets = 10;
        this.myBucketArray = new ArrayList<>(numberBuckets);
        //Create empty Linked List
        for (int i = 0; i < numberBuckets; i++)
            this.myBucketArray.add(null);
    }

    private int getBucketIndex(K key) {
        int hashCode = Math.abs((key.hashCode()));
        int index = hashCode % numberBuckets;
        return index;
    }

    public V get(K key) {
        int index = this.getBucketIndex(key);
        MyLinkedList<K> myLinkedList = this.myBucketArray.get(index);
        if (myLinkedList == null) return null;
        MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
        return (myMapNode == null) ? null : myMapNode.getValue();
    }

    public void add(K key, V value) {
        int index = this.getBucketIndex(key);
        MyLinkedList<K> myLinkedList = this.myBucketArray.get(index);
        if (myLinkedList == null) {
            myLinkedList = new MyLinkedList<>();
            this.myBucketArray.set(index, myLinkedList);
        }
        MyMapNode<K, V> myMapNode = (MyMapNode<K, V>) myLinkedList.search(key);
        if (myMapNode == null) {
            myMapNode = new MyMapNode<>(key, value);
            myLinkedList.append(myMapNode);
        } else {
            myMapNode.setValue(value);
        }
    }

    @Override
    public String toString() {
        return "MyLinkedHashMap List {" + myBucketArray + "}";
    }
}
