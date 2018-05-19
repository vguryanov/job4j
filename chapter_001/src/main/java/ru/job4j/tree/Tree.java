package ru.job4j.tree;

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
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> data = new LinkedList<>();

            {
                data.offer(Tree.this.root);
            }

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                Node<E> result = data.poll();
                for (Node<E> leaf : result.leaves()) {
                    data.offer(leaf);
                }

                return result.getValue();
            }
        };
    }
}
