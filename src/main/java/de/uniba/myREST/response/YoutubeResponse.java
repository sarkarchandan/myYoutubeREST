package de.uniba.myREST.response;

import com.google.api.client.util.DateTime;

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

    private String videoId;
    private String videoETag;
    private String videoTitle;
    private DateTime videoPublishedAt;
    private String videoChannelId;
    private String videoChannelTitle;
    private String VideoDescription;
    private String videoThumbnailURL;


    /**
     * Constructor for class YoutubeResponse
     * @param videoId
     * @param videoETag
     * @param videoTitle
     * @param videoPublishedAt
     * @param videoChannelId
     * @param videoChannelTitle
     * @param videoDescription
     * @param videoThumbnailURL
     */
    public YoutubeResponse(String videoId, String videoETag,
                           String videoTitle, DateTime videoPublishedAt,
                           String videoChannelId, String videoChannelTitle,
                           String videoDescription, String videoThumbnailURL) {
        this.loggerYoutubeResponse = loggerYoutubeResponse;
        this.videoId = videoId;
        this.videoETag = videoETag;
        this.videoTitle = videoTitle;
        this.videoPublishedAt = videoPublishedAt;
        this.videoChannelId = videoChannelId;
        this.videoChannelTitle = videoChannelTitle;
        VideoDescription = videoDescription;
        this.videoThumbnailURL = videoThumbnailURL;
    }

    /**
     * Getter and Setter methods for the class YoutubeResponse instance variables
     * @return
     */
    public String getVideoId() {
        return videoId;
    }

    public String getVideoETag() {
        return videoETag;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public DateTime getVideoPublishedAt() {
        return videoPublishedAt;
    }

    public String getVideoChannelId() {
        return videoChannelId;
    }

    public String getVideoChannelTitle() {
        return videoChannelTitle;
    }

    public String getVideoDescription() {
        return VideoDescription;
    }

    public String getVideoThumbnailURL() {
        return videoThumbnailURL;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoETag(String videoETag) {
        this.videoETag = videoETag;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public void setVideoPublishedAt(DateTime videoPublishedAt) {
        this.videoPublishedAt = videoPublishedAt;
    }

    public void setVideoChannelId(String videoChannelId) {
        this.videoChannelId = videoChannelId;
    }

    public void setVideoChannelTitle(String videoChannelTitle) {
        this.videoChannelTitle = videoChannelTitle;
    }

    public void setVideoDescription(String videoDescription) {
        VideoDescription = videoDescription;
    }

    public void setVideoThumbnailURL(String videoThumbnailURL) {
        this.videoThumbnailURL = videoThumbnailURL;
    }
}
