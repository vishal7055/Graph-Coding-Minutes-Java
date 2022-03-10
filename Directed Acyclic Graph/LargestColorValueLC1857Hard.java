/*

There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.

*/

//Graph BFS Solution - Not understood
//Based on topological sort
class Solution 
{
    public int largestPathValue(String colors, int[][] edges)
    {
        int n = colors.length();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        char[] color = colors.toCharArray();
        for(int i = 0; i < n; i++)
        {
            graph.add(i, new ArrayList<Integer>());
        }
        int[] indegree = new int[n];
        
        for(int[] edge : edges)
        {
            int u = edge[0];
            int v = edge[1];
            indegree[v]++;
            graph.get(u).add(v);
        }
        
        int[][] map = new int[n][26];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++)
        {
            if(indegree[i] == 0)
            {
                q.add(i);
                map[i][color[i] - 'a'] = 1;
            }
        }
        
        int seen = 0;
        int res = 0;
        
        while(!q.isEmpty())
        {
            int node = q.poll();
            seen++;
            
            int max = getMax(map[node]);
            res = Math.max(max, res);
            
            for(int nbr : graph.get(node))
            {
                for(int i = 0; i < 26; i++)
                {
                    map[nbr][i] = Math.max(map[nbr][i], map[node][i] + (color[nbr] - 'a' == i ? 1 : 0));
                }
                indegree[nbr]--;
                
                if(indegree[nbr] == 0)
                {
                    q.add(nbr);
                }
            }
        }
        return seen == n ? res : -1;
        
    }
    
    private int getMax(int[] nums)
    {
        int num = nums[0];
        for(int i  : nums)
        {
            num = Math.max(i, num);
        }
        return num;
    }
}
