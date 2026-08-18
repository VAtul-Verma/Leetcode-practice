import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSQUESTIONS {
    // ===============================leetcode
    // 785==========================================
    public boolean bipartite(int[][] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        // No Color : -1 , Red : 0, Green : 1
        int color = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    if (color != vis[rvtx]) // conflict
                        return false;
                    continue;
                }

                vis[rvtx] = color;
                for (int v : graph[rvtx]) {
                    if (vis[v] == -1) {
                        que.addLast(v);
                    }
                }
            }

            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n]; // vector<int> vis(n,-1);
        Arrays.fill(vis, -1);

        for (int i = 0; i < n; i++) {
            if (vis[i] == -1 && !bipartite(graph, i, vis))
                return false;
        }

        return true;
    }

    // =============================================LEETCODE
    // 994=======================================
    public int orangesRotting(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        Queue<Integer> que = new ArrayDeque<>();
        int totalFreshorange = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2) {
                    que.offer(i * m + j);
                } else if (arr[i][j] == 1) {
                    totalFreshorange++;
                }
            }
        }

        if (totalFreshorange == 0)
            return 0;
        int time = 0;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.poll();
                int sr = idx / m;
                int sc = idx % m;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] == 1) {
                        // mark neghibour as rotten
                        arr[r][c] = 2;
                        totalFreshorange--;
                        que.offer(r * m + c);
                        if (totalFreshorange == 0)
                            return time + 1;
                    }

                }
            }
            time++;
        }
        return -1;
    }

    // ===============================LEETCODE
    // 1091====================================================
    public int shortestPathBinaryMatrix(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        if (n == 0 || m == 0)
            return 0;
        if (arr[0][0] == 1 || arr[n - 1][m - 1] == 1)
            return -1;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

        int shortpath = 1;
        que.addLast(0);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m;
                int sc = idx % m;
                if (sr == n - 1 && sc == m - 1)
                    return shortpath;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] == 0) {
                        arr[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            shortpath++;
        }
        return -1;
    }

    // ===========================================LEETCODE
    // 542===================================
    public int[][] updateMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return grid;

        int n = grid.length, m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.push(i * m + j);
                    vis[i][j] = true;
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c]) {
                        grid[r][c] = grid[sr][sc] + 1;
                        vis[r][c] = true;
                        que.addLast(r * m + c);
                    }

                }
            }
        }

        return grid;
    }

    // =====================================Hacker rank journy to
    // moon=====================================
    public static int dfs(int src, List<List<Integer>> graph, boolean[] vis) {
        int size = 1;
        vis[src] = true;
        for (Integer v : graph.get(src)) {
            if (!vis[v]) {
                size += dfs(v, graph, vis);
            }
        }
        return size;
    }

    public static long journeyToMoon(int n, List<List<Integer>> edges) {
        // Write your code here
        List<List<Integer>> graph = new ArrayList<>();

        // Create adjacency list
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (List<Integer> e : edges) {
            graph.get(e.get(0)).add(e.get(1));
            graph.get(e.get(1)).add(e.get(0));
        }

        boolean[] vis = new boolean[n];

        long sum = 0;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int size = dfs(i, graph, vis);

                ans += size * sum;
                sum += size;
            }
        }

        return ans;

    }

    // ==================================================Leetcode
    // 207====================================
    // topological order questions
    public boolean canFinish(int N, int[][] prerequisites) {

        // Create graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N];

        // Build graph
        for (int[] ar : prerequisites) {
            graph.get(ar[0]).add(ar[1]);
            indegree[ar[1]]++;
        }

        // Add vertices having indegree 0
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.offer(i);
            }
        }

        int vtxCount = 0;

        // BFS
        while (!que.isEmpty()) {

            int vtx = que.poll();
            vtxCount++;

            for (int v : graph.get(vtx)) {

                indegree[v]--;

                if (indegree[v] == 0) {
                    que.offer(v);
                }
            }
        }

        return vtxCount == N;
    }

    // ===================================Leetcode 210
    // ===========================================
    public int[] findOrder(int N, int[][] prerequisites) {

        // Create graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N];

        // Build graph
        for (int[] ar : prerequisites) {
            graph.get(ar[1]).add(ar[0]);
            indegree[ar[0]]++;
        }

        // Add vertices having indegree 0
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.offer(i);
            }
        }

        int[] ans = new int[N];
        int idx = 0;

        // BFS
        while (!que.isEmpty()) {

            int vtx = que.poll();

            ans[idx++] = vtx;

            for (int v : graph.get(vtx)) {

                indegree[v]--;

                if (indegree[v] == 0) {
                    que.offer(v);
                }
            }
        }

        // Cycle exists
        if (idx != N) {
            return new int[0];
        }

        return ans;
    }

    public static void main(String[] args) {

    }

}
