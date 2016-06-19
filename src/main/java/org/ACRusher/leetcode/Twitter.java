package org.ACRusher.leetcode;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xiliang.zxl
 * @date 2016-06-19 下午12:20
 */
public class Twitter {
    /** Initialize your data structure here. */
    public Twitter() {

    }
    private AtomicLong time=new AtomicLong(0);
    /**
     * all users recent feed time line
     */
    private HashMap<Integer,LinkedList<Integer>> recentFeedMap=new HashMap<>();

    /**
     * all users post twitter feeds
     */
    private HashMap<Integer,TreeMap<Long,Integer>> postFeedMap=new HashMap<>();

    /**
     * all users subscribe map
     */
    private HashMap<Integer,TreeSet<Integer>> subscribeMap=new HashMap<>();

    /**
     * all users follower map
     */
    private HashMap<Integer,TreeSet<Integer>> followerMap=new HashMap<>();

    private final int MAX_RECENT_FEED_SIZE=10;



    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        //1. post to user feeds record
        TreeMap<Long,Integer> postFeeds=postFeedMap.get(userId);
        if(postFeeds==null) postFeeds=new TreeMap<>();
        postFeeds.put(time.incrementAndGet(),tweetId);
        postFeedMap.put(userId,postFeeds);
        //2. push to followers recent feed
        TreeSet<Integer> follower=followerMap.get(userId);
        if(follower!=null && !follower.isEmpty()){
            for(Integer uid : follower){
                LinkedList<Integer> recentFeed=recentFeedMap.get(uid);
                if(recentFeed==null) recentFeed=new LinkedList<>();
                recentFeed.addFirst(tweetId);
                if(recentFeed.size()>MAX_RECENT_FEED_SIZE){
                    recentFeed.removeLast();
                }
                recentFeedMap.put(uid,recentFeed);
            }
        }
        //3. update himself recent feed
        LinkedList<Integer> recentFeed=recentFeedMap.get(userId);
        if(recentFeed==null) recentFeed=new LinkedList<>();
        recentFeed.addFirst(tweetId);
        if(recentFeed.size()>MAX_RECENT_FEED_SIZE){
            recentFeed.removeLast();
        }
        recentFeedMap.put(userId,recentFeed);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
       List<Integer> result= recentFeedMap.get(userId);
        if(result==null) result=new ArrayList<>();
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId==followerId) return;
        //update subscribe list
        TreeSet<Integer> subscribeList=subscribeMap.get(followerId);
        if(subscribeList==null) subscribeList=new TreeSet<>();
        subscribeList.add(followeeId);
        subscribeMap.put(followerId,subscribeList);
        //update follower list
        TreeSet<Integer> followerList=followerMap.get(followeeId);
        if(followerList==null) followerList=new TreeSet<>();
        followerList.add(followerId);
        followerMap.put(followeeId,followerList);
        //rebuild recent feed
        rebuildRecentFeed(followerId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId==followerId) return;
        //update subscribe list
        TreeSet<Integer> subscribeList=subscribeMap.get(followerId);
        if(subscribeList==null) subscribeList=new TreeSet<>();
        subscribeList.remove(followeeId);
        subscribeMap.put(followerId,subscribeList);
        //update follower list
        TreeSet<Integer> followerList=followerMap.get(followeeId);
        if(followerList==null) followerList=new TreeSet<>();
        followerList.remove(followerId);
        followerMap.put(followeeId,followerList);
        //rebuild recent feed
        rebuildRecentFeed(followerId);
    }

    private void rebuildRecentFeed(Integer uid){
        TreeMap<Long,Integer> topFeed=new TreeMap<>();
        TreeSet<Integer> subscribeList=subscribeMap.get(uid);
        for(Integer userId : subscribeList){
            TreeMap<Long,Integer> treeMap=postFeedMap.get(userId);
            topFeed=mergeRecentFeed(topFeed,treeMap==null?new TreeMap():treeMap);
        }
        TreeMap<Long,Integer> treeMap=postFeedMap.get(uid);
        topFeed=mergeRecentFeed(topFeed,treeMap==null?new TreeMap():treeMap);

        LinkedList<Integer> linkedList=new LinkedList<>();
        for(Map.Entry<Long,Integer> entry: topFeed.entrySet()){
            linkedList.add(0,entry.getValue());
        }
        recentFeedMap.put(uid,linkedList);
    }

    private TreeMap<Long,Integer> mergeRecentFeed(TreeMap<Long,Integer> left,TreeMap<Long,Integer> right){
        TreeMap<Long,Integer> result=new TreeMap<>();
        Iterator<Map.Entry<Long,Integer>> leftIterator=left.descendingMap().entrySet().iterator();
        Iterator<Map.Entry<Long,Integer>> rightIterator=right.descendingMap().entrySet().iterator();
        Map.Entry<Long,Integer> leftEntry=null,rightEntry=null;
        while (result.size()<MAX_RECENT_FEED_SIZE){
            if(leftEntry==null && leftIterator.hasNext()) leftEntry=leftIterator.next();
            if(rightEntry==null && rightIterator.hasNext()) rightEntry=rightIterator.next();
            if(leftEntry==null && rightEntry==null) break;
            boolean useLeft=leftEntry==null?false:(rightEntry==null || leftEntry.getKey()>rightEntry.getKey());
            if(useLeft){
                result.put(leftEntry.getKey(),leftEntry.getValue());
                leftEntry=null;
            }else{
                result.put(rightEntry.getKey(),rightEntry.getValue());
                rightEntry=null;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Twitter twitter=new Twitter();
        twitter.postTweet(1,4);
        twitter.postTweet(2,5);
        twitter.unfollow(1,2);
        twitter.follow(1,2);
        System.out.println(twitter.getNewsFeed(1));
    }
}
