import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class l004Nqueen {
    public static void main(String[] args) {
        boolean[][] box = new boolean[4][4];
        // System.out.println(queenpermutation1D(box, 3, 0, ""));
        // System.out.println(queencombination2D(box, 4, 0, 0, ""));
        System.out.println(queen01(box, 4, 0, 0, ""));
        System.out.println(queen01permutation(box, 4, 0, ""));

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
            for (int rad = 1; rad <= n; rad++) { // radious 1 se start isliye he because 0 per wo khud ko kill ka check
                                                 // karega so
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

    // ===========================official NQUEEN01
    // permutation====================================================

    public static boolean isSafetoPlacequeen_permutation(boolean[][] box, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        int n = box.length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) { // radious 1 se start isliye he because 0 per wo khud ko kill ka check
                                                 // karega so
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

    public static int queen01permutation(boolean[][] box, int tnq, int qpsf, String psf) {
        if (qpsf == tnq) {
            System.out.println(psf);
            return 1;
        }
        int cnt = 0;
        int n = box.length;
        for (int bidx = 0; bidx < n * n; bidx++) {
            int r = bidx / n;
            int c = bidx % n;
            if (!box[r][c] && isSafetoPlacequeen_permutation(box, r, c)) {
                box[r][c] = true;

                cnt += queen01permutation(box, tnq, qpsf + 1, psf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return cnt;

    }

    // =============================================LeetCode
    // 51=======================================================

    public List<List<String>> solveNQueens(int n) {
        boolean[][] arr = new boolean[n][n];
        List<List<String>> ans = new ArrayList<>();
        queen01(arr, n, 0, 0, "", ans);
        return ans;
    }

    public void printAns(boolean[][] board, List<List<String>> ans) {
        List<String> currboardpath = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            String path = "";
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == false) {
                    path += ".";
                } else {
                    path += "Q";
                }

            }
            currboardpath.add(path);
        }
        ans.add(currboardpath);
    }

    // public boolean isSafetoPlacequeen(boolean[][] box, int r, int c) {
    // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    // int n = box.length;
    // for (int d = 0; d < dir.length; d++) {
    // for (int rad = 1; rad <= n; rad++) { // radious 1 se start isliye he because
    // 0 per wo khud ko kill ka check
    // // karega so
    // int x = r + rad * dir[d][0];
    // int y = c + rad * dir[d][1];
    // if (x >= 0 && y >= 0 && x < n && y < n) {
    // // check weather the queen is already placed or not
    // if (box[x][y])
    // return false;
    // } else {
    // break;
    // }
    // }
    // }
    // return true;

    // }

    public int queen01(boolean[][] box, int tnq, int bno, int qpsf, String psf, List<List<String>> ans) {
        if (qpsf == tnq) {
            printAns(box, ans);
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
                cnt += queen01(box, tnq, bidx + 1, qpsf + 1, psf + "(" + r + "," + c + ") ", ans);
                box[r][c] = false;

            }
        }
        return cnt;
    }

    // ==================================LeetCode Sudoku solver
    // 37================================

    public boolean isPossibleToPlaceNumber(char[][] board, int r, int c, int num) {
        // row check
        for (int row = 0; row < 9; row++) {
            if (board[row][c] - '0' == num)
                return false;
        }
        // check in the column
        for (int col = 0; col < 9; col++) {
            if (board[r][col] - '0' == num)
                return false;
        }

        // check in the 3*3 matrix

        // get the start point of each 3*3 matrix
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[r + row][c + col] - '0' == num)
                    return false;
            }
        }
        return true;
    }

    public boolean sudokusolver(char[][] board, ArrayList<Integer> list, int idx) {
        if (idx == list.size()) {
            return true;// sudoku sovle as all blank places fill;
        }

        // again decode the idx which store in the List from 1d into 2D row and column
        int r = list.get(idx) / 9;
        int c = list.get(idx) % 9;

        // now place the number from 0-9 in that blank place
        for (int num = 1; num <= 9; num++) {
            if (isPossibleToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) ('0' + num); // place the number
                // call the next place and if sudoku solve return true
                if (sudokusolver(board, list, idx + 1))
                    return true;
                board[r][c] = '.'; // mark blank again if not right solve

            }

        }
        return false;

    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = 9;// sudoku is 9*9
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(i * n + j);// 2d to 1d conversition store the blank place in a list
                }
            }
        }
        sudokusolver(board, list, 0);

    }

    // ==================leetcode 139=========================
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        return wordBreak(s, "", set);
    }

    public boolean wordBreak(String str, String asf, HashSet<String> set) {
        if (str.length() == 0) {
            System.out.println(asf);
            return true;
        }

        for (int len = 1; len <= str.length(); len++) {
            String smallstr = str.substring(0, len);
            if (set.contains(smallstr)) {
                if (wordBreak(str.substring(len), asf + smallstr + " ", set))
                    return true;
            }

        }
        return false;
    }

    // sudoku Solver 02 more optimised=====================================
    boolean[][] rows = new boolean[10][10];
    boolean[][] cols = new boolean[10][10];
    boolean[][][] mats = new boolean[3][3][10];

    public boolean sudokuSolver_02(char[][] board, ArrayList<Integer> list, int idx) {
        if (idx == list.size())
            return true;

        int r = list.get(idx) / 9;
        int c = list.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (!rows[r][num] && !cols[c][num] && !mats[r / 3][c / 3][num]) {
                board[r][c] = (char) ('0' + num);
                rows[r][num] = cols[c][num] = mats[r / 3][c / 3][num] = true;

                if (sudokuSolver_02(board, list, idx + 1))
                    return true;

                board[r][c] = '.';
                rows[r][num] = cols[c][num] = mats[r / 3][c / 3][num] = false;
            }
        }

        return false;
    }

    public void solveSudoku_02(char[][] board) {
        ArrayList<Integer> list = new ArrayList<>(); // blank places
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(i * n + j);
                } else {
                    int num = board[i][j] - '0';
                    rows[i][num] = cols[j][num] = mats[i / 3][j / 3][num] = true;
                }
            }
        }

        sudokuSolver_02(board, list, 0);
    }

    // sudoku solver using the bits
    int[] row = new int[10];
    int[] col = new int[10];
    int[][] mat = new int[3][3];

    public boolean sudokuSolver_03_bits(char[][] board, ArrayList<Integer> list, int idx) {
        if (idx == list.size())
            return true;

        int r = list.get(idx) / 9;
        int c = list.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            int mask = 1 << num;
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                board[r][c] = (char) ('0' + num);
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

                if (sudokuSolver_03_bits(board, list, idx + 1))
                    return true;

                board[r][c] = '.';

                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

            }
        }

        return false;
    }

    public void solveSudoku_03_bits(char[][] board) {
        ArrayList<Integer> list = new ArrayList<>(); // blank places
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(i * n + j);
                } else {
                    int num = board[i][j] - '0';
                    int mask = 1 << num;
                    row[i] ^= mask;
                    col[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;
                }
            }
        }

        sudokuSolver_03_bits(board, list, 0);
    }

}
