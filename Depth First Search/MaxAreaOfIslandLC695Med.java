/* 
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

*/

//Graph - DFS Traversal
//TC and SC : O(N *M)  CM SOlution
class Solution 
{
    public int maxAreaOfIsland(int[][] grid) 
    {
        int n = grid.length; //row length
        int m = grid[0].length; // column length
        boolean[][] visited = new boolean[n][m];
        int largest = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                //Traverse only if particular cell is not visited and contains value as 1
                if(!visited[i][j] && grid[i][j] == 1)
                {
                    int size = dfs(grid, visited, i, j, n, m);
                    largest = Math.max(largest, size);
                }
            }
        }
        return largest;
    }
    
    private int dfs(int[][] grid, boolean[][] visited, int i, int j, int n, int m)
    {
        visited[i][j] = true;
        int cs = 1;
        int[] dx = {1, -1, 0, 0}; //Neighbours of x
        int[] dy = {0, 0, 1, -1}; //Neighboirs of y
        
        //Traverse in four direction
        for(int k = 0; k < 4; k++)
        {
            int nbr_x = i + dx[k];
            int nbr_y = j + dy[k];
            
            //Check for boundary conditions
            if(nbr_x >= 0 && nbr_x < n && nbr_y >= 0 && nbr_y < m &&
               !visited[nbr_x][nbr_y] && grid[nbr_x][nbr_y] == 1)
            {
                cs += dfs(grid, visited, nbr_x, nbr_y, n, m);
            }
        }
        return cs;
    }
}
