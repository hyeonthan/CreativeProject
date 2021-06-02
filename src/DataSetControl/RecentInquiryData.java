package DataSetControl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import DTO.DestinationDTO;

public class RecentInquiryData {
    //  String(userId)를 key 값으로 queue(여행지DTO가 들어간)를 put 한다.
    private static HashMap<String, Queue<DestinationDTO>> recentListHashMap;
    private static Queue<DestinationDTO> queue;
    public RecentInquiryData(){
        recentListHashMap = new HashMap<String, Queue<DestinationDTO>>();
        queue = new LinkedList<DestinationDTO>();
    }
    public static void setRecentList (String userId, DestinationDTO destinationDTO){
        queue.add(destinationDTO);
        if(queue.size() > 5){
            queue.poll();
        }
        recentListHashMap.put(userId, queue);
    }
    public static Queue<DestinationDTO> getRecentList(String userId){
        Queue<DestinationDTO> tempQueue = recentListHashMap.get(userId);
        return tempQueue;
    }
}
