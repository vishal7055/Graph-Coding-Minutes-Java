/* A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if
no such sequence exists.

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

*/

//Solved using Graph BFS (Shortest path between any two nodes) - TechDose
//For searching shortest path between any two nodes,,BFS needs to be applied in Graph not DFS
class Solution
{
    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set set = new HashSet();
        boolean isPresent = false;
        
        //Check if endWord is present in wordList
        for(String word : wordList)
        {
            if(endWord.compareTo(word) == 0)
            {
                isPresent = true;
            }
            set.add(word);
        }
        
        //If not present return zero 
        if(!isPresent)
        {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int depth = 0;
        
        while(!queue.isEmpty())
        {
            depth++;
            int size = queue.size(); // No. of elements at a particular level
            while(size-- > 0)
            {
                String current = queue.poll();
                //Check for all possible words
                for(int i = 0; i < current.length(); i++)
                {
                    StringBuilder temp1 = new StringBuilder(current);
                    for(char ch = 'a'; ch <= 'z'; ch++) //Try all possible characters
                    {
                        temp1.setCharAt(i, ch);
                        String temp = temp1.toString();
                        
                        //If after permutating,,we find same word then continue
                        if(current.compareTo(temp) == 0)
                        {
                            continue;
                        }
                        
                        //If we find endWord,,then return depth
                        if(temp.compareTo(endWord) == 0)
                        {
                            return depth + 1;
                        }
                        
                        //Elase check if word is present in set,if yes then add to queue and finally remove from set
                        if(set.contains(temp))
                        {
                            queue.add(temp);
                            set.remove(temp);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}
