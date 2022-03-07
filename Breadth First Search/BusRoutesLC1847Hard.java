/* You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

*/

//Solved using Graph BFS Shortest Path
//Pepcoding solution
class Solution
{
    public int numBusesToDestination(int[][] routes, int source, int target)
    {
        int n = routes.length;
        HashMap<Integer, ArrayList<Integer>> map  = new HashMap<>();
        
        //Prepare HashMap of busStopNo -> busNo
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < routes[i].length; j++)
            {
                ArrayList<Integer> list = map.getOrDefault(routes[i][j], new ArrayList<Integer>());
                list.add(i);
                map.put(routes[i][j], list);
            }
        }
        
        int level = 0;
        //For keeping track of bus visited
        HashSet<Integer> busvis = new HashSet<Integer>();
        //For keeping track of busstop visited
        HashSet<Integer> busstopvis = new HashSet<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(source);
        busstopvis.add(source);
        
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0; i < size; i++)
            {
                int busstopno = q.poll();
                if(busstopno == target)
                {
                    return level;
                }
                //Get list of buses passing thru a particular busstop
                ArrayList<Integer> buses = map.get(busstopno);
                
                //Iterate thru all available bus
                for(int bus : buses)
                {
                    //If visited,,then continue
                    if(busvis.contains(bus))
                    {
                        continue;
                    }
                    
                    //Iterate thru all busstops of a particular bus
                    int[] busstops = routes[bus];
                    for(int busstop : busstops)
                    {
                        //If busstop is visited,,then continue
                        if(busstopvis.contains(busstop))
                        {
                            continue;
                        }
                        
                        //add busstop to queue and mark it as visited
                        q.add(busstop);
                        busstopvis.add(busstop);
                    }
                    busvis.add(bus);
                }
            }
            level++;
        }
        
        return -1;
    }
}
