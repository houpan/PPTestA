package model;

import java.util.ArrayDeque;

public final class ImmutableQueue<T> implements Queue<T> {
    private final ArrayDeque<T> queue;

    public ImmutableQueue(ArrayDeque<T> queue) {
        this.queue = queue.clone();
    }

    public ImmutableQueue() {
        this(new ArrayDeque<T>());
    }

    @Override
    public Queue<T> enQueue(T t) {
        queue.addLast(t);
        ImmutableQueue<T> newImmutableQueue = new ImmutableQueue<>(queue);
        queue.removeLast();

        return newImmutableQueue;
    }

    @Override
    public Queue<T> deQueue() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        T dequeuedTmp = queue.removeFirst();
        ImmutableQueue<T> newImmutableQueue = new ImmutableQueue<>(queue);
        queue.addFirst(dequeuedTmp);

        return newImmutableQueue;
    }

    @Override
    public T head() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}