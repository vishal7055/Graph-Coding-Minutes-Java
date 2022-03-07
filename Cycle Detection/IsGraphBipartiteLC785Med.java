/*
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

*/
class Solution {
    public boolean isBipartite(int[][] graph)
    {
        //Convert 2D Array to ArrayList
        int V = graph.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < V; i++)
        {
            adj.add(new ArrayList<Integer>());
        }
        
        int i = 0;
        for(int[] g : graph)
        {
            List<Integer> list = adj.get(i);
            for(int item : g)
            {
              list.add(item);
            }
            i++;
        }
        
        int[] visited = new int[V];
        int color = 1;
        for(int j = 0; j < V; j++)
        {
            //Check for every component
            if(visited[j] == 0)
            {
                if(!dfs(adj, visited, j, -1 ,color))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean dfs(ArrayList<ArrayList<Integer>> adj, int[] visited, int node, int parent, int color)
    {
        visited[node] = color; // 0 - Unvisited, 1 - Visited with color 1 and 2 - Visited with color 2
        
        for(int nbr : adj.get(node))
        {
            if(visited[nbr] == 0)
            {
                boolean subproblem  = dfs(adj, visited, nbr, node, 3 - color);
                if(!subproblem)
                {
                    return false;
                }
            }
            else if(nbr != parent && visited[nbr] == color)
            {
                return false;
            }
        }
        return true;
    }
}
