import java.util.*;

class main {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        // System.out.println(mazesofar(0, 0, 2, 2, ""));

        // int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        // String[] dirs = { "h", "v", "d" };
        // System.out.println(mazesofar2(0, 0, 2, 2, dir, dirs, ""));
        // System.out.println(mazesofarmulti(0, 0, 2, 2, dir, dirs, ""));

        // =======================================================flood fill
        // section=======================================

        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        String[] dirs = { "U", "E", "R", "W", "D", "N", "L", "S" };
        int[][] visited = new int[3][3];
        System.out.println(floodfill(0, 0, 2, 2, dir, dirs, "", visited));
    }

    public static int mazesofar(int sr, int sc, int er, int ec, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1; // reached the destination now
        }

        int cnt = 0;
        if (sc + 1 <= ec) {
            cnt += mazesofar(sr, sc + 1, er, ec, psf + "H"); // ---> Horizental call
        }
        if (sr + 1 <= er) {
            cnt += mazesofar(sr + 1, sc, er, ec, psf + "V"); // Vertiacal call
        }
        if (sc + 1 <= ec && sr + 1 <= er) {
            cnt += mazesofar(sr + 1, sc + 1, er, ec, psf + "D"); // ---> Horizental call
        }
        return cnt;
    }

    public static int mazesofar2(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1; // reached the destination now
        }
        int cnt = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                cnt += mazesofar2(r, c, er, ec, dir, dirs, psf + dirs[d]);
            }

        }

        return cnt;
    }

    public static int mazesofarmulti(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1; // reached the destination now
        }
        int cnt = 0;
        for (int d = 0; d < dir.length; d++) { // direction

            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    cnt += mazesofarmulti(r, c, er, ec, dir, dirs, psf + dirs[d] + rad);
                }

            }

        }

        return cnt;
    }

    // ======================================================================Flood
    // fill===================================================
    public static int floodfill(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf,
            int[][] visited) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1; // reached the destination now
        }
        int cnt = 0;

        visited[sr][sc] = 1; // mark your place ;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec && visited[r][c] == 0) {
                cnt += floodfill(r, c, er, ec, dir, dirs, psf + dirs[d], visited);
            }

        }

        visited[sr][sc] = 0; // unmark your place ;

        return cnt;
    }

}