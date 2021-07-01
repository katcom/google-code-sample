package com.google;

import java.util.Collections;
import java.util.List;
import java.math.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
public class VideoPlayer {
  private final VideoLibrary videoLibrary;
  private Video currentVideo;
  private boolean isPaused;
  private HashMap<String,List<Video>> playlists;
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    playlists = new HashMap<String,List<Video>>();

  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    List<Video> videos =this.videoLibrary.getVideos();
    Collections.sort(videos,new Comparator<Video>(){
        public int compare(Video video1,Video video2){
          return video1.getTitle().compareTo(video2.getTitle());
        }
      
    });
    for(int j=0;j<videos.size();j++){
      Video video = videos.get(j);
      String entry = video.getTitle();
      entry+= " (" + video.getVideoId() +")";

      entry += " [";
      List<String> tags = video.getTags();
      for(int i=0;i<tags.size();i++){
          entry += tags.get(i);
          if(i != tags.size() -1)
            entry += " ";
      }
      entry+="]";

      System.out.println(entry);
    }
  }

  public void playVideo(String videoId) {
    Video video = videoLibrary.getVideo(videoId);
    if(video == null){
      System.out.println("Cannot play video: Video does not exist");
    }else{
      if(currentVideo != null){
        //System.out.println("Stopping video: "+currentVideo.getTitle());
        stopVideo();
      }
      currentVideo=video;
      System.out.println("Playing video: "+currentVideo.getTitle());
    }
  }

  public void stopVideo() {
    //System.out.println("stopVideo needs implementation");
    if(currentVideo == null){
      System.out.println("Cannot stop video: No video is currently playing");
    }else{
      System.out.printf("Stopping video: %s\n",currentVideo.getTitle());
      currentVideo = null;
      isPaused = false;
    }
  }

  public void playRandomVideo() {
    List<Video> videos = videoLibrary.getVideos();
    int newVideoIndex = (int)Math.floor((videos.size() * Math.random()));
    String newVideoId = videos.get(newVideoIndex).getVideoId();
    playVideo(newVideoId);
    //System.out.println("playRandomVideo needs implementation");
  }

  public void pauseVideo() {
    if(currentVideo == null){
      System.out.println("Cannot pause video: No video is currently playing");
    }else if(isPaused){
      System.out.printf("Video already paused: %s\n",currentVideo.getTitle());
    }else{
      System.out.printf("Pausing video: %s\n",currentVideo.getTitle());
      isPaused = true;
    }
  }

  public void continueVideo() {
    //System.out.println("continueVideo needs implementation");
    if(currentVideo == null){
      System.out.println("Cannot continue video: No video is currently playing");
    }else if(!isPaused){
      System.out.println("Cannot continue video: Video is not paused");
    }else{
      System.out.printf("Continuing video: %s\n",currentVideo.getTitle());
      isPaused = false;
    }
    
  }

  public void showPlaying() {
    //System.out.println("showPlaying needs implementation");
    if(currentVideo == null){
      System.out.println("No video is currently playing");
    }else{
      String info = "Currently playing: " + getVideoSummary(currentVideo);
      info += isPaused?" - PAUSED":"";
      System.out.print(info);
    }
  }
  private String getTagInfo(Video video){
    String tagsInfo = "[";
    List<String> tags =video.getTags();
    for(int i=0;i<tags.size();i++){
      tagsInfo += tags.get(i);
      if(i!=tags.size()-1)
        tagsInfo += " ";
    }
    tagsInfo += "]";
    return tagsInfo;
  }
  private String getVideoSummary(Video video){
    return String.format("%s (%s) %s", video.getTitle(),video.getVideoId(),getTagInfo(video));
  }

  public void createPlaylist(String playlistName) {
    if(playlists == null){
      playlists = new HashMap<String,List<Video>>();
    }
    if(playlists.get(playlistName) == null){
      playlists.put(playlistName, new ArrayList<Video>());
      System.out.printf("Successfully created new playlist: %s\n",playlistName);
    }else{
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    //System.out.println("addVideoToPlaylist needs implementation");
    List<Video> playlist = null;
    for(String name: playlists.keySet()){
      if(name.toLowerCase().equals(playlistName.toLowerCase())){
        playlist = playlists.get(name);
        break;
      }
    }

    if(playlist == null){
      System.out.printf("Cannot add video to %s: Playlist does not exist\n",playlistName);
      return;
    }
    
    boolean isFound = false;
    for(Video v:playlist){
      if(v.getVideoId().equals(videoId)){
        isFound = true;
        break;
      }
    }
    if(!isFound){
      Video v = videoLibrary.getVideo(videoId);
      if(v == null){
        System.out.printf("Cannot add video to %s: Video does not exist\n",videoId);
        return;
      }  
      System.out.printf("Added video to %s: %s\n",playlistName,v.getTitle());
    }else{
      System.out.printf("Cannot add video to %s: Video already added\n",playlistName);
    }
  }

  public void showAllPlaylists() {
    //System.out.println("showAllPlaylists needs implementation");
    if(playlists == null || playlists.size() == 0){
      System.out.println("No playlists exist yet");
    }else{
      System.out.printf("Showing all playlists:");
      List<String> playlistNames = new ArrayList<>(playlists.keySet());
      Collections.sort(playlistNames,String::compareTo);
      for(String listName :playlistNames){
        System.out.println(listName);
      }
    }
    
  }

  public void showPlaylist(String playlistName) {
    //System.out.println("showPlaylist needs implementation");
    List<Video> list = null;
    if(playlists == null || playlists.size() == 0){
      System.out.printf("Cannot show Playlist %s: Playlist does not exist\n",playlistName);
      return;
    }
    for(String name: playlists.keySet()){
      if(name.toLowerCase().equals(playlistName.toLowerCase())){
        list = playlists.get(name);
        break;
      }
    }
    if(list == null){
      System.out.printf("Cannot show Playlist %s: Playlist does not exist\n",playlistName);
      return;
    }
    System.out.printf("Showing playlist: %s\n",playlistName);
    if(list.size() == 0){
      System.out.println("No videos here yet");
      
    }else{
      for(Video video:list){
        System.out.println(getVideoSummary(video));
      }
    }
  }
  private List<Video> getPlayList(String playlistName){
    List<Video> list = null;
    for(String name: playlists.keySet()){
      if(name.toLowerCase().equals(playlistName.toLowerCase())){
        list = playlists.get(playlistName);
        break;
      }
    }
    return list;
  }
  private Video getVideoFromPlaylist(List<Video> playlist,String videoId){
    for(Video video:playlist){
      if(video.getVideoId().equals(videoId))
        return video;
    }
    return null;
  }
  public void removeFromPlaylist(String playlistName, String videoId) {
    //System.out.println("removeFromPlaylist needs implementation");
    List<Video> list = getPlayList(playlistName);
    if(list == null){
      System.out.printf("Cannot add video to %s: Playlist does not exist\n",playlistName); 
      return;
    }
    
    boolean isFound = false;
    Video removedVideo = null;
    for(Video video:list){
      if(video.getVideoId().equals(videoId)){
        removedVideo = video;
        isFound = true;
        break;
      }
    }

    if(isFound){
      list.remove(removedVideo);
      System.out.printf("Removed video from %s",removedVideo.getTitle());
    }else{
      System.out.printf("Cannot add video to %s: Video is not in playlist\n",removedVideo.getTitle()); 
    }
  }

  public void clearPlaylist(String playlistName) {
    //System.out.println("clearPlaylist needs implementation");
    List<Video> list = getPlayList(playlistName);
    if(list == null){
      System.out.printf("Cannot clear playlist %s: Playlist does not exist\n",playlistName);
    }else{
      if(playlists.size() == 0){
        System.out.println("No videos here yet.");
        return;
      }
      for(String key:playlists.keySet()){
        if(key.toLowerCase().equals(playlistName.toLowerCase())){
          playlists.put(key, new ArrayList<Video>());
          System.out.printf("Successfully removed all videos from %s\n",playlistName);
          break;
        }
      }

    }
  }

  public void deletePlaylist(String playlistName) {
    boolean isFound = true;
    for(String key:playlists.keySet()){
      if(key.toLowerCase().equals(playlistName.toLowerCase())){
        playlists.remove(key);
        System.out.printf("Deleted playlist: %s",playlistName);
        return;
      }
    }
    System.out.printf("Cannot delete playlist %s: Playlist does not exist\n",playlistName);
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}