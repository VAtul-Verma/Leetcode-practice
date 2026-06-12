public class l004Nqueen {
    public static void main(String[] args) {
        boolean[][] box = new boolean[4][4];
        // System.out.println(queenpermutation1D(box, 3, 0, ""));
        // System.out.println(queencombination2D(box, 4, 0, 0, ""));
        System.out.println(queen01(box, 4, 0, 0, ""));

    }

    // tnq =Total number of Queen ,bno = box number,
    public static int queencombination1D(boolean[] box, int tnq, int bno, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        for (int bidx = bno; bidx < box.length; bidx++) {
            cnt += queencombination1D(box, tnq, bidx + 1, qpsf + 1, psf + "b" + bidx + "q" + qpsf + " ");
        }
        return cnt;

    }

    public static int queenpermutation1D(boolean[] box, int tnq, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        for (int bidx = 0; bidx < box.length; bidx++) {
            if (!box[bidx]) {
                box[bidx] = true;
                cnt += queenpermutation1D(box, tnq, qpsf + 1, psf + "b" + bidx + "q" + qpsf + " ");
                box[bidx] = false;
            }
        }
        return cnt;

    }

    // nqueeen 2D
    public static int queencombination2D(boolean[][] box, int tnq, int bno, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        int n = box.length;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;
            cnt += queencombination2D(box, tnq, bidx + 1, qpsf + 1, psf + "(" + r + "," + c + ") ");
        }
        return cnt;

    }

    public static int queenpermutation2D(boolean[][] box, int tnq, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        int n = box.length;
        for (int bidx = 0; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;
            if (!box[r][c]) {
                box[r][c] = true;

                cnt += queenpermutation2D(box, tnq, qpsf + 1, psf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return cnt;

    }

    // =============================================Official
    // NQUENN=========================================
    public static boolean isSafetoPlacequeen(boolean[][] box, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int n = box.length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];
                if (x >= 0 && y >= 0 && x < n && y < n) {
                    // check weather the queen is already placed or not
                    if (box[x][y])
                        return false;
                } else {
                    break;
                }
            }
        }
        return true;

    }

    public static int queen01(boolean[][] box, int tnq, int bno, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        int n = box.length;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;
            if (isSafetoPlacequeen(box, r, c)) {
                box[r][c] = true;
                cnt += queen01(box, tnq, bidx + 1, qpsf + 1, psf + "(" + r + "," + c + ") ");
                box[r][c] = false;

            }
        }
        return cnt;
    }
}
