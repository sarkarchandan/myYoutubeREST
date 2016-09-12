# myYoutubeREST

myYoutubeREST is a Java RESTful practice implementation built in open source RESTful Web Services framework Jersey. [Youtube Data Api v3](https://developers.google.com/youtube/v3/) has been used to build the implementation. Upon entering a keyword and no of return values,  implementation returns a defined no of Youtube videos serialized as JSON objects. Any web browser or REST Client e.g. Chrome Advanced REST Client could be used for it. This implementation is built in Linux environment. 

## Ongoing Work:

At this moment this Implementation creates an endpoint to search desired no of youtube videos with a search query parameter and return videos as useful JSON objects. I am working towards creating my interactive practice application where along with returning the response with respect to the  the passed parameters, endpoint will also make some suggestions which may appear useful and worth exploring to the user. 

I am exploring Youtube Data and Analytics API for creating such an application which will drive the user’s probably interactions with the application state. :octocat:

## Assumption
- This implementation creates Web Application Archive (.war) file and deploys the same in [Glassfish](https://glassfish.java.net/) container which can be downloaded from the link.
- It assumes that the user has a native installation of [Gradle](https://gradle.org/) or a [Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) pattern may also be used.

## Installation

Clone the [GitHub Repository](https://github.com/sarkarchandan/myYoutubeREST) 
```sh
$ git clone  https://github.com/sarkarchandan/myYoutubeREST
$ cd myYoutubeREST
```
## Usage
### Create Credentials
- Go to [Google Developers Console](https://console.developers.google.com)  and create a new Project and right after creating new Project generate _oAuth Client ID_ and _oAuth Client Secret_ for your Project. 
- Go to [oAuth 2.0 Playground](https://developers.google.com/oauthplayground/) and use the _oAuth Client ID_ and _oAuth Client Secret_ to generate a _Refresh Token_ for your Project which will be useful for oAuth 2.0 authenticated API Call to You tube Data Api. 
- A comprehensive guide on how to generate the credentials can be found [here](https://youtu.be/ADLmRWZqFOI?list=PLKHNxdZZDQH9Pd-76u1KNZkxvHhXDMxwq) .

### Import
- This implementation has been built using IntelliJ Idea but any IDE of user’s choice e.g. Eclipse can be used to import the project. Execute the corresponding gradle plug in to build the project workspace before importing.
```sh
$ gradle idea
```
OR
```sh
$ gradle eclipse
```
- Enter the _Client ID_ and _Client Secret_ to and generated _Refresh Token_ for the registered project with Google      Developer Console in the respective static fields of the **YoutubeEngine** class of the package _de.uniba.myREST.engine_
- Make sure that glassfish server container is running.
- Build the archive and deploy to the Container.
```sh
$ gradle build
$ gradle deploy
```
## Sample Execution
Open the web browser or Chrome Advanced REST Client and enter the URI with query string such as: `http://localhost:8080/youtubeVideos/search?searchQuery=Nexus&noOfResources=5`
### Sample Outcome
 ```
 {
"videoChannelId": "UCBJycsmduvYEL83R_U4JriQ"
"videoChannelTitle": "Marques Brownlee"
"videoDescription": "2016 Nexus Sailfish and Marlin are coming soon! AndroidPolice Exclusives: http://www.androidpolice.com/topics/news/exclusive/ Nexus Launcher apk: ..."
"videoETag": ""I_8xdZu766_FSaexEaDXTIfEWc0/DhC5bp2UFch0b-B9FlOrhW54Dwo""
"videoId": "C2p5aREEWvk"
"videoPublishedAt": "2016-08-10T21:19:27.000Z"
"videoThumbnailURI": "https://i.ytimg.com/vi/C2p5aREEWvk/default.jpg"
"videoTitle": "2016 Nexus Hype!"
"videoURI": "https://www.youtube.com/watch?v=C2p5aREEWvk"
}
```
# Known Issues
At this point **YoutubeEngine** class expects the credentials to be entered directly to the class body. I am working to come up with a better method for that.
**noOfResources** field is lacking validation as of now. I am working to add validation for it.
**I am open to any suggestions or comments.**
