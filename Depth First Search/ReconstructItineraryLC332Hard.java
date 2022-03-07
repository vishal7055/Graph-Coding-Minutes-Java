/*
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]

Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.

*/

//Graph DFS Based Question - TECH DOSE Solution
class Solution
{
    public List<String> findItinerary(List<List<String>> tickets)
    {
        List<String> answer = new ArrayList<String>();
        if(tickets == null || tickets.isEmpty())
        {
            return answer;
        }
        
        Map<String, PriorityQueue<String>> adjList = new HashMap<>();
        for(List<String> ticket : tickets)
        {
            String departure = ticket.get(0);
            String arrival = ticket.get(1);
            
            if(!adjList.containsKey(departure))
            {
                adjList.put(departure, new PriorityQueue<>());
            }
            adjList.get(departure).offer(arrival);
        }
        
        dfs("JFK", adjList, answer);
        //Collections.reverse(answer);
        return answer;
    }
    
    private void dfs(String node, Map<String, PriorityQueue<String>> adjList, List<String> answer)
    {
        PriorityQueue<String> listOfNeighbours = adjList.get(node);
        
        while(listOfNeighbours != null && !listOfNeighbours.isEmpty())
        {
            String neighbour = listOfNeighbours.poll();
            dfs(neighbour, adjList, answer);
        }
        answer.add(0,node);
    }
}
