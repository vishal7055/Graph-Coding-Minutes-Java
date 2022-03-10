/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Input: numCourses = 1, prerequisites = []
Output: [0]
 
*/

class Solution 
{
    public int[] findOrder(int numCourses, int[][] prerequisites)
    {
        //Start - Create Graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < numCourses; i++)
        {
            graph.add(new ArrayList<Integer>());
        }
        
        for(int[] pre : prerequisites)
        {
            int courseRequiredBefore = pre[1];
            int course = pre[0]; //edges will be from courseReqdBefore -> course
            
            List<Integer> list = graph.get(courseRequiredBefore);
            list.add(course);
        }
        //End - Create Graph
        
        //Start - Check for Cycle
        boolean[] st = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        
        
        //Check if cycle exists
        for(int i = 0; i < numCourses; i++)
        {
            if(!visited[i])
            {
                if(hasCycle(i, visited, graph, st))
                {
                    return new int[0];
                }
            }
        }
        //End - Check for Cycle
        
        //Start - Topological Sort using DFS
        Stack<Integer> stack = new Stack<Integer>();
        visited = new boolean[numCourses];
        
        for(int i = 0; i < numCourses; i++)
        {
            if(!visited[i])
            {
                dfs(i, visited, graph, stack);
            }
        }
        //End - Topological Sort using DFS
        
        //Creating resultant array
        int[] res = new int[numCourses];
        int i = 0;
        while(!stack.isEmpty())
        {
             res[i++] = stack.pop();
        }
       
        return res;
    }

    //Check for Cycle in Graph - Course Schedule I Type
    private boolean hasCycle(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, boolean[] stack)
    {
        visited[node] = true;
        stack[node] = true;
        
        for(int nbr : adj.get(node))
        {
            if(!visited[nbr])
            {
                boolean isNeighbourCycle = hasCycle(nbr, visited, adj, stack);
                if(isNeighbourCycle)
                {
                    return true;
                }
            }
            else
            {
                if(visited[nbr] && stack[nbr])
                {
                    return true;
                }
            }
        }
        stack[node] = false;
        return false;
    }
    
    //Perform Topological sort using DFS
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack)
    {
        visited[node] = true;
        
        for(int nbr : adj.get(node))
        {
            if(!visited[nbr])
            {
                dfs(nbr, visited, adj, stack);
            }
        }
        
        stack.push(node);
    }
}
