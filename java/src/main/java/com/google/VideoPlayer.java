package com.google;

import java.util.Collections;
import java.util.List;
import java.math.*;
import java.util.Comparator;
public class VideoPlayer {
  private final VideoLibrary videoLibrary;
  private Video currentVideo;
  private boolean isPaused;
  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
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
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
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