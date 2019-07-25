package model;

import java.util.ArrayDeque;

public final class ImmutableQueue<T> implements Queue<T> {
    private final ArrayDeque<T> queue;

    public ImmutableQueue(ArrayDeque<T> queue) {
        this.queue = queue;
    }

    public ImmutableQueue() {
        this(new ArrayDeque<T>());
    }

    @Override
    public Queue<T> enQueue(T t) {
        ImmutableQueue<T> newImmutableQueue;
        synchronized (queue) {
            ArrayDeque<T> newQueue = queue.clone();
            newQueue.addLast(t);
            newImmutableQueue = new ImmutableQueue<>(newQueue);
        }
        return newImmutableQueue;
    }

    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableQueue<T> newImmutableQueue;
        synchronized (queue) {
            ArrayDeque<T> newQueue = queue.clone();
            newQueue.removeFirst();
            newImmutableQueue = new ImmutableQueue<>(newQueue);
        }

        return newImmutableQueue;
    }

    @Override
    public T head() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
