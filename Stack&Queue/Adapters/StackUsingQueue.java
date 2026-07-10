package Adapters;

import java.util.LinkedList;

public class StackUsingQueue {
    LinkedList<Integer> queue = new LinkedList<>();
    LinkedList<Integer> temp = new LinkedList<>();
    public int topele = 0;

    public StackUsingQueue() {

    }

    public void swapData(LinkedList<Integer> q1, LinkedList<Integer> q2) {

        while (q1.size() != 1) {
            q2.addLast(q1.removeFirst());
        }
    }

    public void push(int x) {
        queue.addLast(x);
        topele = x;
    }

    public int pop() {
        if (queue.size() <= 1) {
            return queue.removeFirst();
        }
        swapData(queue, temp);
        int rn = queue.removeFirst();
        swapData(temp, queue);
        this.topele = temp.getFirst();
        queue.addLast(temp.removeFirst());
        return rn;

    }

    public int top() {
        return this.topele;
    }

    public boolean empty() {
        if (this.queue.size() == 0)
            return true;
        return false;

    }

}
