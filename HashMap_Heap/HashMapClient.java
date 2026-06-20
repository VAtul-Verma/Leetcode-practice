public class HashMapClient {

    public static void main(String[] args) {
        HashMapContruction hm = new HashMapContruction();
        hm.put(10, 100);
        hm.put(20, 200);
        hm.put(30, 300);
        hm.put(40, 400);
        hm.put(50, 500);

        // hm.putIfabsent(60, 800);
        // hm.remove(60);
        // System.out.println(hm.get(90));
        System.out.println(hm.display());

        System.out.println(hm.keySet());

    }

}
