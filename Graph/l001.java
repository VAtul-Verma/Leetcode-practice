import java.util.ArrayList;
import java.util.LinkedList;

public class l001 {
    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    // O(2E) == O(E)
    public static void display(ArrayList<Edge>[] graph, int V) {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        // basicall u me jake v ko search karna he bus
        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (e.v == v)
                return i;// returnt the index if got
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        // basicall u me jake v ko search karke remove
        int idx1 = findEdge(graph, u, v);
        graph[u].remove(idx1);

        // also remove the edge V me jake u ko search karke remove karna he
        int idx2 = findEdge(graph, v, u);
        graph[v].remove(idx2);

    }

    // TC: O(E^2)
    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        for (int i = graph[u].size() - 1; i >= 0; i--) {
            Edge e = graph[u].get(i);
            removeEdge(graph, u, e.v);

        }
    }

    // TC : O(E)
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int desti, boolean[] vis) {
        if (src == desti)
            return true;
        boolean res = false;
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                res = res || hasPath(graph, e.v, desti, vis);

            }
        }
        return res;

    }

    public static int allPath(ArrayList<Edge>[] graph, int src, int desti, boolean[] vis, String psf) {
        if (src == desti) {
            System.out.println(psf + desti);
            return 1;
        }
        int cnt = 0;
        // makr
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                cnt += allPath(graph, e.v, desti, vis, psf + src);

            }
        }
        // unmark
        vis[src] = false;
        return cnt;

    }

    public static void preOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        System.out.println(src + " -> " + (psf + src) + "@" + wsf);
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                preOrder(graph, e.v, vis, wsf + e.w, psf + src);
        }

        vis[src] = false;
    }

    public static void postOrder(ArrayList<Edge>[] graph, int src, boolean[] vis, int wsf, String psf) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                postOrder(graph, e.v, vis, wsf + e.w, psf + src);
        }

        System.out.println(src + " -> " + (psf + src) + "@" + wsf);
        vis[src] = false;
    }

    // ======================heavy path ================================
    public static class pair {
        int heavyPath = 0;
        String psf = "";

        pair() {

        }

        pair(int heavyPath, String psf) {
            this.heavyPath = heavyPath;
            this.psf = psf;
        }
    }

    public static pair heavyPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return new pair(0, src + "");
        }

        vis[src] = true;
        pair myAns = new pair(-1, "");
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                pair recAns = heavyPath(graph, e.v, dest, vis);
                if (recAns.heavyPath != -1 && recAns.heavyPath + e.w > myAns.heavyPath) {
                    myAns.heavyPath = recAns.heavyPath + e.w;
                    myAns.psf = src + recAns.psf;
                }
            }
        }

        vis[src] = false;
        return myAns;
    }

    public static void hamintonainpathAndcycle(ArrayList<Edge>[] graph, int src, int orgscr, int edgecount,
            boolean[] vis,
            String psf, ArrayList<String> ans) {

        // if e = v-1
        if (edgecount == graph.length - 1) {
            // add the src in our answer
            psf += src;
            // check weather there is a directed edge betweeen curr vertex and orignal src
            int idx = findEdge(graph, src, orgscr);

            // if edge found the hamintonaincycle
            if (idx != -1) {
                psf += "*";
            }
            ans.add(psf);
            return;

        }

        // mark the vertex
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v]) {
                hamintonainpathAndcycle(graph, e.v, orgscr, edgecount + 1, vis, psf + src, ans);
            }
        }
        // unmark the vertex
        vis[src] = false;
    }

    // get conected components
    public static void dfs_compo(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src])
            if (!vis[e.v])
                dfs_compo(graph, e.v, vis);
    }

    public static void gcc(ArrayList<Edge>[] graph) {
        int N = graph.length;
        boolean[] vis = new boolean[N];

        int components = 0;
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                components++;
                dfs_compo(graph, i, vis);
            }
        }

    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        // bidirectional graph
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    // ================================BFS======================================
    public static void bfs(ArrayList<Edge>[] graph, int src, boolean vis[]) {
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.println("Level" + level + "--->");

            while (size-- > 0) {
                int vtx = que.removeFirst();
                if (vis[vtx] == true) {
                    System.out.println("Cycle present");
                    continue;
                }

                System.out.print(vtx + ",");
                vis[vtx] = true;

                // put the negibhour in queue
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v]) {
                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println();
        }
    }

    // ====================================BFSWITHOUTCYCLE==============================
    public static void bfs_withoutcycle(ArrayList<Edge>[] graph, int src, boolean vis[]) {
        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);
        vis[src] = true;
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.println("Level" + level + "--->");

            while (size-- > 0) {
                int vtx = que.removeFirst();
                System.out.print(vtx + ",");
                // put the negibhour in queue
                for (Edge e : graph[vtx]) {
                    if (!vis[e.v]) {
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }
                }
            }
            level++;
            System.out.println();
        }
    }

    public static void bipartite(ArrayList<Edge>[] graph, int src, int[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        // No Color : -1 , Red : 0, Green : 1
        int color = 0;
        boolean isCycle = false, isBipartite = true;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                if (vis[rvtx] != -1) {
                    isCycle = true;
                    if (color != vis[rvtx]) // conflict
                        isBipartite = false;
                    continue;
                }

                vis[rvtx] = color;
                for (Edge e : graph[rvtx]) {
                    if (vis[e.v] == -1) {
                        que.addLast(e.v);
                    }
                }
            }

            color = (color + 1) % 2;
        }

        if (!isCycle)
            System.out.println("Bipartite Graph with no cycle");
        else {
            if (isBipartite)
                System.out.println("Bipartite Graph with Even Length cycle");
            else
                System.out.println("Non Bipartite Graph with Odd Length cycle");
        }
    }

    public static void constructGraph() {
        // int V = 7;
        int V = 8;
        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);

        addEdge(graph, 2, 7, 2);
        addEdge(graph, 2, 8, 4);
        addEdge(graph, 7, 8, 3);

        // addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // addEdge(graph, 0, 6, 3);
        boolean[] vis = new boolean[V];
        // pair ans = heavyPath(graph, 0, 6, vis);
        // System.out.println(ans.heavyPath + " @ " + ans.psf);
        // ArrayList<String> ans = new ArrayList<>();
        // hamintonainpathAndcycle(graph, 0, 0, 0, vis, "", ans);
        // System.out.println(ans);

    }

    public static void main(String[] args) {
        constructGraph();

    }
}