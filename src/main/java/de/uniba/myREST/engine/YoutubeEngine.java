package de.uniba.myREST.engine;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import org.apache.http.auth.AUTH;

import java.io.IOException;
import java.io.InputStream;
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
    private static final int noOfVideosRequired = 10;

    /**
     * Defining a Global Instance of the Youtube Object which will be used for API Request to Youtube Data API
     */
    private static YouTube youTube;


    public void getYoutubeVideosFromEngine(String searchQuery){


        loggerYoutubeEngine.setLevel(Level.ALL);
        loggerYoutubeEngine.info("Class YoutubeEngine: Start Logging");

        Properties youtubeProperties = new Properties();
        try {
            InputStream inputStream = YoutubeEngine.class.getResourceAsStream("/home/chandan/dasLabor/meinLabor/myYoutubeREST/src/main/java" + propertyFileName);
            youtubeProperties.load(inputStream);
        }catch(IOException e){
            loggerYoutubeEngine.log(Level.SEVERE,"Class YoutubeEngine: Cannot read necessary properties"+e.getCause());
        }



        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-RESTfulAPI").build();





    }



}
