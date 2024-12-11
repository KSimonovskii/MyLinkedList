package telran.set.model;

import telran.set.interfaces.ISet;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements ISet<E> {

    private LinkedList<E>[] hashSet;
    private int size;
    private int capacity;
    private double loadFactor;

    public MyHashSet(int capacity, double loadFactor){
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        hashSet = new LinkedList[capacity];
    }

    public MyHashSet(int capacity){
        this(capacity, 0.75);
    }

    public MyHashSet(){
        this(16);
    }


    @Override
    public boolean add(E element) {
        if (size >= loadFactor * capacity){
            rebuildArray();
        }

        int index = getIndex(element);
        if (hashSet[index] == null){
            hashSet[index] = new LinkedList<>();
        }
        if (hashSet[index].contains(element)){
            return false;
        }
        hashSet[index].add(element);
        size++;
        return true;
    }

    public int getIndex(E element){
        int hashCode = element.hashCode();
        hashCode = hashCode > 0 ? hashCode : -hashCode;
        return hashCode % capacity;
    }

    private void rebuildArray(){
        capacity *= 2;
        LinkedList<E>[] newHashSet = new LinkedList[capacity];
        for (int i = 0; i < hashSet.length; i++) {
            if (hashSet[i] == null){
                continue;
            }
            for (E element: hashSet[i]) {
                int index = getIndex(element);
                if (newHashSet[index] == null){
                    newHashSet[index] = new LinkedList<>();
                }
                newHashSet[index].add(element);
            }
        }
        hashSet = newHashSet;
    }

    @Override
    public boolean contains(E element) {
        int index = getIndex(element);
        if (hashSet[index] == null){
            return false;
        }
        return hashSet[index].contains(element);
    }

    @Override
    public boolean remove(E element) {
        int index = getIndex(element);
        if (hashSet[index] == null
            || !hashSet[index].contains(element)){
            return false;
        }
        size--;
        return hashSet[index].remove(element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int currIndex;
            private int currHashIndex;

            @Override
            public boolean hasNext() {
                if (currHashIndex == hashSet.length - 1) {
                    return hashSet[currHashIndex] == null ? false : currIndex < hashSet[currHashIndex].size();
                }
                return currHashIndex < hashSet.length;
            }

            @Override
            public E next() {
                while (hasNext()) {
                    if (hashSet[currHashIndex] != null) {
                        while (currIndex < hashSet[currHashIndex].size()) {
                            E res = hashSet[currHashIndex].get(currIndex++);
                            if (res != null) {
                                return res;
                            }
                        }
                        currIndex = 0;
                    }
                    currHashIndex++;
                }

                return null;
            }
        };
    }

}
