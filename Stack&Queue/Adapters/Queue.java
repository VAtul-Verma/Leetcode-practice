package Adapters;

//Queue Using the LinkedList;
public class Queue {
    private class Node {
        int data = 0;
        Node next = null;

        public Node(int data) {
            this.data = data;

        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public void addLast(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public Queue() {

    }

    public Node remoFirst() {
        Node node = this.head;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = node.next;
        }
        node.next = null;
        this.size--;
        return node;
    }

    public void push(int data) {
        addLast(new Node(data));
    }

    public int pop() {
        return remoFirst().data;

    }

    public int top() {
        return this.head.data;

    }

    public boolean empty() {
        return this.size == 0 ? true : false;
    }

}
