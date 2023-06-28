package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PreorderIterator<T> implements Iterator<T> {
    private ArrayStack<BinaryNode<T>> stack;

    public PreorderIterator(BinaryNode<T> root) {
        stack = new ArrayStack<>();
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        BinaryNode<T> current = stack.pop();

        if (current.getRightChild() != null) {
            stack.push(current.getRightChild());
        }
        if (current.getLeftChild() != null) {
            stack.push(current.getLeftChild());
        }

        return current.getData();
    }}