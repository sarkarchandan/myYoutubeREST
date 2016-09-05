package de.uniba.myREST.engine;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import de.uniba.myREST.response.YoutubeResponse;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Establish connection with Youtube Data Api v3 and fetch desired no of video resource objects
 * @author Created by chandan on 23.08.16.
 */
public class YoutubeEngine {

    /*
     * Declaring Java Util Logger object for enabling logging in the YoutubeEngine class
     */
    private static Logger loggerYoutubeEngine = Logger.getLogger(YoutubeEngine.class.getName());


    /*
     * We are defining a Global Instance of the Youtube Object which will be used for API Request to Youtube Data Api v3
     */
    private static YouTube youTube;

    /*
     * User's Client ID, Client Secret and Refresh Token for the registered project with Google Developer Console should go here.
     */
    private static String clientID = "862649508795-f16sicfh2gf1129dh7p4nr6on49mv4io.apps.googleusercontent.com";
    private static String clientSecret = "tiMBfl6J6udwBXPaPTrG_tj6";
    private static String refreshToken = "1/tUebO5PoBaWubw32MYffPYYApOrOZqy0ZIlpSQoUaNw";


    /**
     * Establish Authenticated Connection with Youtube Object and retrieve the desired no of search result for a search query
     * @param searchQuery {String}
     * @param noOfVideoResourcesRequired {long}
     * @return List<YoutubeResponse>
     */
    public static List<YoutubeResponse> getYoutubeVideosFromEngine(String searchQuery,long noOfVideoResourcesRequired){


        List<YoutubeResponse> youtubeVideoObjectList = new LinkedList<>();


        loggerYoutubeEngine.setLevel(Level.ALL);
        loggerYoutubeEngine.info("Class YoutubeEngine/Method getYoutubeVideosFromEngine: Start Logging");



        try {

            /*
             * Creating credentials for authenticated API Call
             */
            Credential credential;
            credential = new GoogleCredential.Builder()
                    .setTransport(new NetHttpTransport())
                    .setJsonFactory(new JacksonFactory())
                    .setClientSecrets(clientID,clientSecret)
                    .build();
            credential.setRefreshToken(refreshToken);

            /*
             * Here we are binding our global Youtube object with HTTP_TRANSPORT, JSON_FACTORY and credential object
             * to make it ready for initiating the connection with Youtube Data Api v3
             */
            youTube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,credential)
                    .setApplicationName("newRESTProject")
                    .build();


            loggerYoutubeEngine.info("Class YoutubeEngine/Method getYoutubeVideosFromEngine: Credential Built");

            /*
             * Define the API Request for retrieving search result from Youtube using search method
             */
            YouTube.Search.List searchList = youTube.search().list("id,snippet");

            /*
             * Fetching temporary access token from the credential object
             * Defining the Query String for the search
             * Defining that we only want video references from the search.
             * Defining that we only want a fixed no of videos
             */

            searchList.setOauthToken(credential.getAccessToken());
            searchList.setQ(searchQuery);
            searchList.setType("video");
            searchList.setMaxResults(noOfVideoResourcesRequired);


            /*
             * Calling the Youtube Data Api to get the Video Objects and store them in a List of SearchResult
             * SearchResult data type has been defined in Youtube Data Api
             */
            SearchListResponse searchResponse = searchList.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            loggerYoutubeEngine.info("Class YoutubeEngine/Method getYoutubeVideosFromEngine: Request initiated");
            /*
             * Extracting the Video Data as a list of YoutubeResponse type using Constructor
             */

            if(searchResultList==null){
                loggerYoutubeEngine.log(Level.SEVERE,"Class YoutubeEngine/Method getYoutubeVideosFromEngine: Matching video list is not found from Youtube Data API");

            } else
                loggerYoutubeEngine.info("Class YoutubeEngine/Method getYoutubeVideosFromEngine:: We have got a matching video list");

            /*
             * Calling generateYoutubeResponseObjectList method to return a list of YoutubeResponse objects
             */
            youtubeVideoObjectList = generateYoutubeResponseObjectList(searchResultList.iterator());


        }catch(GoogleJsonResponseException gJRE){
            loggerYoutubeEngine.log(Level.SEVERE,"Class YoutubeEngine/Method getYoutubeVideosFromEngine: There was a service disruption"+gJRE.getCause()+gJRE.getMessage());
        }
        catch (IOException eIO){
            loggerYoutubeEngine.log(Level.SEVERE,"Class YoutubeEngine/Method getYoutubeVideosFromEngine: There was an IO Error"+eIO.getCause()+eIO.getMessage());
        }


        return youtubeVideoObjectList;

    }


    /**
     * Method: Static method generateYoutubeResponseObjectList returns the video data as list of YoutubeResponse objects.
     * @param iteratorSearchResults {Iterator<SearchResult>}
     * @return List<YoutubeResponse>
     */
    private static List<YoutubeResponse> generateYoutubeResponseObjectList(Iterator<SearchResult> iteratorSearchResults) {

        List<YoutubeResponse> localVideoList = new LinkedList<>();


        if (!iteratorSearchResults.hasNext()) {
            loggerYoutubeEngine.info("Class YoutubeEngine/Methdo generateYoutubeResponseObjectList: No matching videos found with the Query");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            /*
             * Since Youtube Data Api v3 has many other resources to offer apart from videos, this check is a good practice
             * We are checking the resource against the Kind field of the resource schema.
             */
            if (rId.getKind().equals("youtube#video")) {

                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                localVideoList.add(new YoutubeResponse(rId.getVideoId().toString(),
                        singleVideo.getEtag().toString(),
                        singleVideo.getSnippet().getTitle().toString(),
                        singleVideo.getSnippet().getPublishedAt().toString(),
                        singleVideo.getSnippet().getChannelId().toString(),
                        singleVideo.getSnippet().getChannelTitle().toString(),
                        singleVideo.getSnippet().getDescription().toString(),
                        thumbnail.getUrl().toString(),
                        "https://www.youtube.com/watch?v="+rId.getVideoId().toString()));

            }
        }
        loggerYoutubeEngine.info("Class YoutubeEngine/Methdo generateYoutubeResponseObjectList: Done logging");
        return localVideoList;
    }




}
