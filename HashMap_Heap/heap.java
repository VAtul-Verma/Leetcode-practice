import java.util.ArrayList;

public class heap {
    private ArrayList<Integer> arr;
    private boolean isMax = true;

    // constructor
    public heap(boolean isMax) {
        this.arr = new ArrayList<>();
        this.isMax = isMax;
    }

    // this constructor will take the array and convert it into heap;

    // TC: it seems n + nlog(n) --> but it's TC is O(n)
    public heap(int[] arr, boolean isMax) {
        this(isMax);
        // copy the array data into the list
        for (int ele : arr) {
            this.arr.add(ele);
        }

        // traverse the array list from the end
        for (int i = this.arr.size() - 1; i >= 0; i--) {
            // cal the downheapify
            downheapify(i);
        }
    }

    // this is the function to compare the lci ,rci with maxIdx
    // TC : O(1)
    private boolean compareTo(int x, int y) {
        if (isMax)
            return this.arr.get(x) > this.arr.get(y);
        else
            return this.arr.get(y) > this.arr.get(x);
    }

    // TC : O(1)
    private void swap(int x, int y) {
        // get the value from the list
        int v1 = arr.get(x);
        int v2 = arr.get(y);

        // swpa the values
        arr.set(x, v2);
        arr.set(y, v1);
    }

    public int size() {
        return this.arr.size();
    }

    // downheapify to check the parent with childs and convert it into heap
    private void downheapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxIdx = pi;

        // check the left child and if greater then swap with maxIdx
        if (lci < arr.size() && compareTo(lci, maxIdx)) {
            maxIdx = lci;
        }

        // check the right child and if greater then swap with maxIdx
        if (rci < arr.size() && compareTo(rci, maxIdx)) {
            maxIdx = rci;
        }

        if (pi != maxIdx) { // mean child is greater than parents

            // update the parent by swap
            swap(pi, maxIdx);

            // check the same this for rest tree weather they are still heap or not
            downheapify(maxIdx);

        }
    }

    // =====Upheapify for adding the new elements in heap
    // ci-->the index of new element which is added
    // TC : Log(n)
    private void Upheapify(int ci) {
        int pi = (ci - 1) / 2;
        // if current elment is greater than parent
        if (pi >= 0 && compareTo(ci, pi)) {
            // swap it
            swap(ci, pi);
            // updte the heap again
            Upheapify(pi);
        }

    }

    // functions for users

    // remove the top elment
    // TC: O(Log(n))
    public int remove() {
        int removedata = this.arr.get(0);
        // remove the first elememnt from the arrayList Cost O(n)-->to rearrang it and
        // make heap
        // so we swap the top element with the last element of the array list and then
        // remove last element

        swap(0, this.arr.size() - 1);
        this.arr.remove(this.arr.size() - 1);
        // after that again call the downheapify so it will again convert into heap
        downheapify(0);

        return removedata;

    }

    // add the data into it
    // TC: O(Log(n))
    public void add(int data) {
        this.arr.add(data);
        Upheapify(this.arr.size() - 1);
    }

    // give the top element
    // TC: O(1)
    public int peek() {

        return this.arr.get(0);

    }

}