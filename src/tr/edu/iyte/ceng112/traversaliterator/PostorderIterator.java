package tr.edu.iyte.ceng112.traversaliterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import tr.edu.iyte.ceng112.stack.ArrayStack;
import tr.edu.iyte.ceng112.stack.StackInterface;
import tr.edu.iyte.ceng112.tree.BinaryNode;

public class PostorderIterator<T> implements Iterator<T> {

    private StackInterface<BinaryNode<T>> nodeStack;

    public PostorderIterator(BinaryNode<T> root) {
        nodeStack = new ArrayStack<>();
        pushToStack(root);
    }

    @Override
    public boolean hasNext() {
        return !nodeStack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        BinaryNode<T> nextNode = nodeStack.pop();
        T data = nextNode.getData();

        if (!nodeStack.isEmpty()) {
            BinaryNode<T> topNode = nodeStack.peek();

            if (nextNode == topNode.getLeftChild()) {
                pushToStack(topNode.getRightChild());
            }
        }

        return data;
    }

    private void pushToStack(BinaryNode<T> node) {
        while (node != null) {
            nodeStack.push(node);
            if (node.getLeftChild() != null) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}