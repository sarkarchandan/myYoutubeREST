package de.uniba.myREST.service;

import de.uniba.myREST.engine.YoutubeEngine;
import de.uniba.myREST.response.YoutubeResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * YoutubeService class is primarily responsible for providing the users a search functionality for Youtube videos with a search query as a RESTful API
 * Created by chandan on 23.08.16.
 */


@Path("/youtubeVideos")
public class YoutubeService{


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

    public Response getYoutubeVideos(@QueryParam("searchQuery") String searchQuery,@QueryParam("noOfResources") long noOfResources){

        loggerYoutubeService.setLevel(Level.ALL);
        loggerYoutubeService.info("Class YoutubeService/Method getYoutubeVideos: Start Logging");

        /**
         * Getting the List of videos from the YoutubeEngine class and publishing as JSON type objects.
         */

        if (searchQuery==null||searchQuery.equals("")){
            loggerYoutubeService.log(Level.WARNING,"Class YoutubeService/Method getYoutubeVideos:: Empty search parameter");
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }

            GenericEntity<List<YoutubeResponse>> response
                    = new GenericEntity<List<YoutubeResponse>>(YoutubeEngine.getYoutubeVideosFromEngine(searchQuery,noOfResources)) {};

            //List<YoutubeResponse> response = YoutubeEngine.getYoutubeVideosFromEngine(searchQuery,noOfResources);


            loggerYoutubeService.info("Class YoutubeService/Method getYoutubeVideos: Class YoutubeService: Done logging");

            return Response.ok(response, MediaType.APPLICATION_JSON).build();


    }

}


