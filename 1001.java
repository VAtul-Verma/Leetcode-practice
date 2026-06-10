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
            } else {
                break;
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
                } else {
                    break;
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

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                if (visited[r][c] == 0)
                    cnt += floodfill(r, c, er, ec, dir, dirs, psf + dirs[d], visited);

            }

        }

        visited[sr][sc] = 0; // unmark your place ;

        return cnt;
    }

    // ========================================LEETCODE 63 TLE
    // CODE======================================================
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1)
            return 0;

        int[][] dir = { { 0, 1 }, { 1, 0 } };
        String[] dirs = { "h", "v" };
        return mazepath(0, 0, m - 1, n - 1, dir, dirs, "", obstacleGrid);
    }

    public int mazepath(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf, int[][] obstacleGrid) {
        if (sr == er && sc == ec) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        obstacleGrid[sr][sc] = 1;
        for (int d = 0; d < dir.length; d++) {

            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r <= er && c <= ec && obstacleGrid[r][c] == 0) {
                cnt += mazepath(r, c, er, ec, dir, dirs, psf + dirs[d], obstacleGrid);
            }
        }
        obstacleGrid[sr][sc] = 0;
        return cnt;
    }

    // =======================================================GFG Rat in a Maze
    // https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1===========================================================
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirs = { "D", "L", "R", "U" };
        int n = maze.length;
        int m = maze[0].length;
        if (maze[0][0] == 0 || maze[n - 1][m - 1] == 0)
            return new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        floodfill(0, 0, n - 1, m - 1, dir, dirs, "", maze, ans);
        return ans;
    }

    public int floodfill(int sr, int sc, int er, int ec, int[][] dir, String[] dirs, String psf,
            int[][] visited, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1; // reached the destination now
        }
        int cnt = 0;

        visited[sr][sc] = 0; // mark your place ;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                if (visited[r][c] == 1)
                    cnt += floodfill(r, c, er, ec, dir, dirs, psf + dirs[d], visited, ans);
            }
        }

        visited[sr][sc] = 1; // unmark your place ;
        return cnt;
    }
}