import java.util.ArrayList;

public class HashMapContruction {

    private class Node {
        int key = 0;
        int value = 0;
        Node next = null;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private class LinkedList {

        public Node head = null;
        public Node tail = null;
        public int NoOfElement = 0;

        public LinkedList() {
            this.head = this.tail = null;
            this.NoOfElement = 0;
        }

        public int size() {
            return NoOfElement;
        }

        public void addLast(Node node) {
            if (head == null)
                head = tail = node;
            else {
                this.tail.next = node;
                this.tail = node;
            }
            NoOfElement++;
        }

        public int getFirst() {
            return this.head.key;
        }

        public Node removeFirst() {

            if (this.NoOfElement == 0)
                head = tail = null;
            Node node = this.head;

            if (NoOfElement == 1) {
                head = tail = null;
            } else {
                head = node.next;
                node.next = null;
            }
            NoOfElement--;

            return node;
        }

    }

    private LinkedList[] containers;
    private int sizeOfHM = 0;

    public void assignValue(int size) {
        containers = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            containers[i] = new LinkedList();
        }
    }

    HashMapContruction() {
        assignValue(10);
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for (int i = 0; i < this.containers.length; i++) {
            LinkedList group = this.containers[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                sb.append("{" + node.key + " = " + node.value + "}");

                count++;
                if (count != sizeOfHM)
                    sb.append(",");
                group.addLast(node);

            }

        }
        sb.append("]");
        return sb.toString();

    }

    private void rehash() {
        LinkedList[] backup = this.containers;
        assignValue(2 * this.containers.length); // double the size

        for (int i = 0; i < backup.length; i++) {
            LinkedList group = backup[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                put(node.key, node.value);

            }
        }
    }

    public void put(Integer key, Integer value) {
        boolean isKey = containsKey(key);
        LinkedList group = group(key);
        if (isKey)
            group.head.value = value;
        else {
            group.addLast(new Node(key, value));
            this.sizeOfHM++;

            double lambda = (group.size() / this.containers.length * 1.0);
            if (lambda > 0.745) {
                rehash();
            }
        }
    }

    public void putIfabsent(Integer key, Integer defaultValue) {
        boolean isKey = containsKey(key);
        if (!isKey)
            put(key, defaultValue);

    }

    public ArrayList<Integer> keySet() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < this.containers.length; i++) {
            LinkedList group = this.containers[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                list.add(node.key);
                group.addLast(node);

            }

        }
        return list;

    }

    public Integer remove(Integer key) {
        boolean isKey = containsKey(key);
        LinkedList group = group(key);
        if (!isKey)
            return null;
        Node removeele = group.removeFirst();
        this.sizeOfHM--;

        return removeele.key;

    }

    public Integer getOrDefault(Integer key, Integer defaultValue) {
        Integer value = get(key);
        return value != null ? value : defaultValue;

    }

    public Integer get(Integer key) {
        boolean isKey = containsKey(key);
        LinkedList group = group(key);
        return isKey ? group.head.value : null;

    }

    public boolean containsKey(Integer key) {
        LinkedList group = group(key);
        int size = group.size();
        while (size-- > 0) {
            if (group.getFirst() == key) {
                return true;
            }

            group.addLast(group.removeFirst());
        }
        return false;

    }

    private LinkedList group(Integer key) {
        int code = hashCode(key);
        return this.containers[code];
    }

    private int hashCode(Integer key) {
        int value = key.hashCode();
        return value % containers.length;
    }

}
