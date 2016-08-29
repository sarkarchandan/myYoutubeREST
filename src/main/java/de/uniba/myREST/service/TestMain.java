package de.uniba.myREST.service;

import com.google.api.services.youtube.model.SearchResult;
import de.uniba.myREST.engine.YoutubeEngine;
import de.uniba.myREST.response.YoutubeResponse;

import javax.ws.rs.core.GenericEntity;
import java.util.LinkedList;
import java.util.List;

/**
 * TestMain is Class designed for the testing of our interaction with Youtube Data Api
 * Created by chandan on 23.08.16.
 */

public class TestMain {

    public static void main (String[] args){

        List<YoutubeResponse> newList = new LinkedList<YoutubeResponse>();
        newList=YoutubeEngine.getYoutubeVideosFromEngine("Berlin",10);

        for (YoutubeResponse newResponse: newList){
            System.out.println(newResponse.getVideoId());
            System.out.println(newResponse.getVideoETag());
            System.out.println(newResponse.getVideoChannelId());
            System.out.println(newResponse.getVideoChannelTitle());
            System.out.println(newResponse.getVideoTitle());
            System.out.println(newResponse.getVideoDescription());
            System.out.println(newResponse.getVideoPublishedAt());
            System.out.println(newResponse.getVideoThumbnailURI());
            System.out.println(newResponse.getVideoURI());
        }
    }
}
