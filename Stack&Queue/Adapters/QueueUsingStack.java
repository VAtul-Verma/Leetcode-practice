package Adapters;

import java.util.LinkedList;

public class QueueUsingStack {
    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();

    public QueueUsingStack() {

    }

    public void swapData(LinkedList<Integer> st1, LinkedList<Integer> temp) {
        while (st1.size() != 0) {
            temp.addFirst(st1.removeFirst());
        }
    }

    public void push(int x) {
        st.addFirst(x);
    }

    public int pop() {
        swapData(st, temp);
        int rn = temp.removeFirst();
        swapData(temp, st);
        return rn;

    }

    public int top() {
        swapData(st, temp);
        int rn = temp.getFirst();
        swapData(temp, st);
        return rn;
    }

    public boolean empty() {
        if (this.st.size() == 0)
            return true;
        return false;

    }

    public static void main(String[] args) {
        System.out.println("Hello world ");
    }
}