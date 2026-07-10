
//Queue USing Array
public class Queue {

    private int[] arr;
    private int front; // front of the queue
    private int back;// rare of the queue
    private int NoOfElements;
    private int MaxCapacity;

    Queue(int size) {
        initialize(size);

    }

    Queue() {
        this(10);// constructor chaining
    }

    protected void initialize(int size) {
        this.NoOfElements = 0;
        this.MaxCapacity = size;
        this.arr = new int[this.MaxCapacity];
        this.front = this.back = 0;
    }

    private void overFlowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity) {
            throw new Exception("Queue is overflow");
        }
    }

    public void push(int data) throws Exception {
        overFlowException();
        this.arr[this.back] = data;
        this.back = (this.back + 1) % this.MaxCapacity;
        this.NoOfElements++;
    }

    private void underFlowException() throws Exception {
        if (this.NoOfElements == 0) {
            throw new Exception("Custom Queue is underflow");
        }
    }

    public int capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;

    }

    public int peek() throws Exception {
        underFlowException();
        return this.arr[this.front];

    }

    public int pop() throws Exception {
        underFlowException();
        int rv = this.arr[this.front];
        this.front = (this.front + 1) % this.MaxCapacity;
        this.NoOfElements--;
        return rv;

    }

    public void displayQueue() {
        for (int idx = 0; idx < this.NoOfElements; idx++) {
            int i = (idx + this.front) % this.capacity();
            System.err.print(this.arr[i] + " ");
        }
    }

}
