package de.uniba.myREST.engine;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import de.uniba.myREST.response.YoutubeResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class YoutubeEngine is responsible for establishing connection with youtube with Global Youtube Object and retrieve the desired search result for us
 * Created by chandan on 23.08.16.
 */
public class YoutubeEngine {

    /**
     * Declaring Java Util Logger object for enabling logging in the YoutubeEngine class
     */
    private static Logger loggerYoutubeEngine = Logger.getLogger(YoutubeEngine.class.getName());

    /**
     * Initializing global variables for property file name and no of Search results.
     * Property File will be used for authentication purpose for our project in Google Developer Console with pre-established API Key.
     */
    private static final String propertyFileName = "youtube.properties";
    private static final long noOfVideosRequired = 25;

    /**
     * Defining a Global Instance of the Youtube Object which will be used for API Request to Youtube Data API
     */
    private static YouTube youTube;


    public static List<YoutubeResponse> getYoutubeVideosFromEngine(String searchQuery){

        /**
         * Declaring a List of Objects to contain the Youtube Videos
         */
        List<YoutubeResponse> youtubeVideoObjectList = new LinkedList<>();

        loggerYoutubeEngine.setLevel(Level.ALL);
        loggerYoutubeEngine.info("Class YoutubeEngine: Start Logging");

        Properties youtubeProperties = new Properties();
        try {
            InputStream inputStream = YoutubeEngine.class.getResourceAsStream("/"+propertyFileName);
            youtubeProperties.load(inputStream);



        }catch(IOException e){
            loggerYoutubeEngine.log(Level.SEVERE,"Class YoutubeEngine: Cannot read necessary properties"+e.getCause());
        }


        try {

            /**
             * Defining Youtube Data Api request through the object of Youtube.
             */
            youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-RESTfulAPI").build();

            /**
             * Define the API Request for retrieving search result from Youtube
             */
            YouTube.Search.List searchList = youTube.search().list("id,snippet");

            /**
             * Defining Developer key retrieved from the Google Cloud Console
             * Defining the Query String for the search
             * Defining that we only want video references from the search.
             * Defining that we only want a fixed no of videos
             * Defining the data we expect about each video object
             */
            String apiKey  = youtubeProperties.getProperty("youTube.apiKey");
            searchList.setKey(apiKey);
            searchList.setQ(searchQuery);
            searchList.setType("video");
            searchList.setMaxResults(noOfVideosRequired);
            searchList.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/defualt/url)");

            /**
             * Calling the Youtube Data Api to get the Video Objects and store them in a List of SearchResult
             * SearchResult data type has been defined in Youtube Data Api
             */
            SearchListResponse videoSearchResponse = searchList.execute();
            List<SearchResult> searchResultList = videoSearchResponse.getItems();

            /**
             * Extracting the Video Data as a list of YoutubeResponse type using Constructor
             */
            if(searchResultList.isEmpty()){
                loggerYoutubeEngine.log(Level.SEVERE,"We have not found any matching Videos from Youtube Data API");
            } else
            for(SearchResult eachVideoObject:searchResultList){

                if(eachVideoObject.getKind().equals("youtube#video")) {

                    youtubeVideoObjectList.add(new YoutubeResponse(eachVideoObject.getId().toString(),
                            eachVideoObject.getSnippet().getTitle().toString(),
                            eachVideoObject.getSnippet().getThumbnails().getDefault().getUrl().toString()));
                }
            }

        }catch(GoogleJsonResponseException gJRE){
            loggerYoutubeEngine.log(Level.SEVERE,"There was a service disruption"+gJRE.getCause()+gJRE.getMessage());
        }
        catch (IOException eIO){
            loggerYoutubeEngine.log(Level.SEVERE,"There was an IO Error"+eIO.getCause()+eIO.getMessage());
        }

        loggerYoutubeEngine.info("Class YoutubeEngine: Done logging");
        return youtubeVideoObjectList;

    }



}
