/*

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

*/

//Check Cycle in Directed Graph
class Solution
{
    public boolean canFinish(int numCourses, int[][] prerequisites)
    {
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
        
        boolean[] visited = new boolean[numCourses];
        boolean[] stack = new boolean[numCourses]; // For maintaining current path element
        
        for(int i = 0; i < numCourses; i++)
        {
            if(!visited[i])
            {
                //If cycle exist,then we cannot complete all courses,,hence return false
                if(hasCycle(i, visited, stack, graph))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    //DFS 
    private boolean hasCycle(int node, boolean[] visited, boolean[] stack, ArrayList<ArrayList<Integer>> list)
    {
        visited[node] = true;
        stack[node] = true;
        
        for(int nbr : list.get(node))
        {
             if(!visited[nbr])
             {
                 boolean isDFSNeighbourFound = hasCycle(nbr, visited, stack, list);
                 if(isDFSNeighbourFound)
                 {
                     return true;
                 }
             }
            else if(visited[nbr] && stack[nbr])
            {
                //IF a particular neighbour is visited and also present in current path,,means cycle exist, hence return true
                return true;
            }
        }
       stack[node] = false;
       return false;
    }
}
