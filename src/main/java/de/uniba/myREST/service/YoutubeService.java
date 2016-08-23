package de.uniba.myREST.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * YoutubeService class is primarily responsible for providing the users a search functionality for Youtube videos with a search query
 * Created by chandan on 23.08.16.
 */

@Path("/youtube/search")
public class YoutubeService {


    /**
     * Declaring the Java Util Logger Object for enabling logging in the YoutubeService class
     */
    private static Logger loggerYoutubeService = Logger.getLogger(YoutubeService.class.getName());




    /**
     * Method getYoutubeVideos is primary web method providing the RESTful service for producing the Youtube videos on demand
     * @param searchQuery
     * @return
     */
    @GET
    @Path("/search")
    @Consumes(TEXT_PLAIN)
    @Produces(APPLICATION_JSON)
    public Response getYoutubeVideos(@QueryParam("searchQuery") String searchQuery){





    }

}
