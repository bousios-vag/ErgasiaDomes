
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {

    class StringStackNode {

        String value;
        StringStackNode next;
    }

    private int size = 0;
    private StringStackNode first = null;

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String peek() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack error.");
        } else {
            return first.value;
        }
    }

    @Override
    public void printStack(PrintStream stream) {
        StringStackNode now;

        now = first;

        while (now != null) {
            stream.println(now.value);
            now = now.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

       @Override
    public void push(String item) {
        StringStackNode newnode = new StringStackNode();
        newnode.value = item;
        StringStackNode temp = first;
        first = newnode;
        newnode.next = temp;
        size++;
    }

    @Override
    public String pop() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack error.");
        } else {
            String temp = first.value;
            first = first.next;
            size--;
            return temp;
        }
    }
}
