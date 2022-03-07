/*
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.

Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true

Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true

Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false
*/

//Very musch similar to Max Area of Island
//TC : O(n * m)
class Solution 
{
    int[] DIR_X = {1, -1, 0, 0};
    int[] DIR_Y = {0, 0, 1, -1};
    
    public boolean containsCycle(char[][] grid) 
    {
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(!visited[i][j])
                {
                    if(dfs(i, j, -1, -1, n, m, visited, grid, grid[i][j]))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(int curX, int curY, int lastX, int lastY, int n, int m, boolean[][] visited, char[][] grid, char startChar)
    {
        visited[curX][curY] = true;
        //Visit all directions
        for(int k = 0; k < 4; k++)
        {
            int newX = curX + DIR_X[k];
            int newY = curY + DIR_Y[k];
             
            //Check for boundary conditions
            if(newX >= 0 && newX < n && newY >= 0 && newY < m)
            {
                //Dont visited the last point && only visit nodes that equals start char
                if(!(newX == lastX && newY == lastY) && grid[newX][newY] == startChar)
                {
                    //Still visited,,that means it has cycle
                    if(visited[newX][newY])
                    {
                        return true;
                    }
                    if(dfs(newX, newY, curX, curY, n, m, visited, grid, startChar))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

 
