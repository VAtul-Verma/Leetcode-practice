public class stack {

    private int[] arr;
    private int tos; // Top of the stack
    private int NoOfElements;
    private int MaxCapacity;

    stack(int size) {
        initialize(size);

    }

    stack() {
        this(10);// constructor chaining
    }

    protected void initialize(int size) {
        this.NoOfElements = 0;
        this.MaxCapacity = size;
        this.arr = new int[this.MaxCapacity];
        this.tos = -1;
    }

    private void overFlowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity) {
            throw new Exception("Stack is overflow");
        }
    }

    public void push(int data) throws Exception {
        overFlowException();
        this.arr[++this.tos] = data;
        this.NoOfElements++;
    }

    private void underFlowException() throws Exception {
        if (this.NoOfElements == 0) {
            throw new Exception("Custom Stack is underflow");
        }
    }

    // print from index 0 - size();
    // public void displayStack() {
    // for (int i = 0; i < this.NoOfElements; i++) {
    // System.out.println(this.arr[i] + " ");
    // }
    // }
    // print from top of stack to down
    public void displayStack() {
        for (int i = this.tos; i >= 0; i--) {
            System.out.print(this.arr[i] + " ");
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
        return this.arr[this.tos];

    }

    public int pop() throws Exception {
        underFlowException();
        int rv = this.arr[this.tos--];
        this.NoOfElements--;
        return rv;

    }

    public static void main(String[] args) {

    }
}