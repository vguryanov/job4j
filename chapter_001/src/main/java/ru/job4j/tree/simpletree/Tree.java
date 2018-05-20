package ru.job4j.tree.simpletree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Created by User2 on 19.05.2018.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E e) {
        this.root = new Node<E>(e);
    }

    @Override
    public boolean add(E parent, E child) {
        if (this.contains(child)) {
            return false;
        }
        findBy(parent).get().add(new Node<E>(child));
        return true;
    }

    public boolean contains(E e) {
        for (E element : this) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        NodeIterator nodeIterator = new NodeIterator();
        while (nodeIterator.hasNext()) {
            Node<E> next = nodeIterator.next();
            if (next.eqValue(value)) {
                return Optional.of(next);
            }
        }
        return Optional.empty();
    }

    public boolean isBinary() {
        NodeIterator nodeIterator = new NodeIterator();
        while (nodeIterator.hasNext()) {
            if (nodeIterator.next().leaves().size() > 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodeIterator nodeIterator = new NodeIterator();

            @Override
            public boolean hasNext() {
                return nodeIterator.hasNext();
            }

            @Override
            public E next() {
                return nodeIterator.next().getValue();
            }
        };
    }

    private class NodeIterator implements Iterator<Node<E>> {
        private Queue<Node<E>> data = new LinkedList<>();

        {
            data.offer(Tree.this.root);
        }

        @Override
        public boolean hasNext() {
            return !data.isEmpty();
        }

        @Override
        public Node<E> next() {
            Node<E> result = data.poll();
            for (Node<E> leaf : result.leaves()) {
                data.offer(leaf);
            }
            return result;
        }
    }
}
