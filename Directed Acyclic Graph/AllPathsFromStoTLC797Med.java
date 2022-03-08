/*

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]

*/

//Graph DFS Based Approach
//CodeBix Solution
//We have not used visited array because graph is acyclic
class Solution
{
    public List<List<Integer>> allPathsSourceTarget(int[][] graph)
    {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        ans.add(0); //SInce we are starting from zero
        dfs(0, graph.length - 1, graph, ans, list);
        return list;
    }
    
    private void dfs(int src, int dest, int[][] graph, List<Integer> ans, List<List<Integer>> list)
    {
        //When we reach destination
        if(src == dest)
        {
            list.add(new ArrayList(ans));
            return;
        }
        
        for(int nbr : graph[src])
        {
            ans.add(nbr);
            dfs(nbr, dest, graph, ans, list);
            ans.remove(ans.size() - 1); //Backtrack
        }
    }
}


//BFS Solution
class Solution 
{
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) 
    {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<List<Integer>> q = new LinkedList<>();
        
        List<Integer> currList = new ArrayList<>();
        currList.add(0);
        
        q.add(currList);
        
        while(!q.isEmpty())
        {
            currList = q.poll();
             
            int v = currList.get(currList.size() - 1);
            if(v == graph.length - 1)
            {
                ans.add(currList);
                continue;
            }
            
            for(int i : graph[v])
            {
                List<Integer> temp = new ArrayList<>(currList);
                temp.add(i);
                q.add(temp);
            }
        }
        return ans;
    }

}
