package de.uniba.myREST.response;

import java.util.logging.Logger;

/**
 * Class YoutubeResponse is primarily responsible for returning JSON objects for every search result for Youtube videos
 * Created by chandan on 23.08.16.
 */
public class YoutubeResponse {

    /**
     * Declaring java Util Logging Object for enabling logging in the YoutubeResponse class
     */
    private Logger loggerYoutubeResponse = Logger.getLogger(YoutubeResponse.class.getName());

    private String videoID;
    private String videoTitle;
    private String videoThumbnail;

    /**
     * Default Constructor for class YoutubeResponse
     */
    public YoutubeResponse(){}


    /**
     * Getter methods for class YoutubeResponse instance variables
     * @return
     */
    public String getVideoID() {
        return videoID;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    /**
     * Setter methods for class YoutubeResponse instance variables
     * @param videoID
     */
    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
}
