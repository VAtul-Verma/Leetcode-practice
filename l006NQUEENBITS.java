public class l006NQUEENBITS {
    static boolean[] rows, cols, digs, antidigs;

    public static void main(String[] args) {
        int n = 4;
        rows = new boolean[n];
        cols = new boolean[n];
        digs = new boolean[n + n - 1];
        antidigs = new boolean[n + n - 1];
        System.out.println(nqueen_01bits(n, 4, 0, ""));

    }

    public static int nqueen_01(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;
            if (!rows[r] && !cols[c] && !digs[r - c + n - 1] && !antidigs[r + c]) {

                rows[r] = cols[c] = digs[r - c + n - 1] = antidigs[r + c] = true;
                cnt += nqueen_01(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");
                rows[r] = cols[c] = digs[r - c + n - 1] = antidigs[r + c] = false;

            }

        }
        return cnt;
    }

    static int row, col, dig, antidig = 0;

    public static int nqueen_01bits(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;

            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (dig & (1 << r - c + n - 1)) == 0
                    && (antidig & (1 << r + c)) == 0) {

                row ^= 1 << r;
                col ^= 1 << c;
                dig ^= (1 << r - c + n - 1);
                antidig ^= (1 << r + c);
                cnt += nqueen_01bits(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");
                row ^= 1 << r;
                col ^= 1 << c;
                dig ^= (1 << r - c + n - 1);
                antidig ^= (1 << r + c);

            }

        }
        return cnt;
    }
}
