package de.uniba.myREST.service;

import de.uniba.myREST.engine.YoutubeEngine;
import de.uniba.myREST.response.YoutubeResponse;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

/**
 * Created by chandan on 23.08.16.
 */
public class TestMain {

    public static void main (String[] args){



        YoutubeEngine youtubeEngineObject = new YoutubeEngine();
        for (YoutubeResponse response:youtubeEngineObject.getYoutubeVideosFromEngine("abcd")){

            System.out.println(response.getVideoTitle());

        }




    }



}
