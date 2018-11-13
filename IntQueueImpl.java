
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue {

    class IntStackNode {

        int value;
        IntStackNode next;
    }

    private int size = 0;
    private IntStackNode first = null;
    private IntStackNode last = null;

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int peek() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack error.");
        } else {
            return first.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(int item) {
        IntStackNode newnode = new IntStackNode();
        newnode.value = item;

        if (size == 0) {
            first = newnode;
            last = newnode;
        } else {
            last.next = newnode;
            last = newnode;
        }
        size++;
    }

    @Override
    public int get() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("Empty stack error.");
        } else {
            int temp = first.value;
            first = first.next;
            size--;

            if (size == 0) {
                last = null;
            }
            return temp;
        }
    }

    @Override
    public void printQueue(PrintStream stream) {
        IntStackNode now;

        now = first;

        while (now != null) {
            stream.println(now.value);
            now = now.next;
        }
    }

}
