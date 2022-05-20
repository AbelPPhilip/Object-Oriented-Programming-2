package tests;



import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;

/**
 * 
 * You need student tests if you are asking for help during
 * office hours about bugs in your code. Feel free to use
 * tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void test() {
		
		//Creation of Video 1
		String title = "MrBeast";
		String url = "https://www.youtube.com/ifVdifeigf";
		int durationInMinutes = 45;
		Genre genre = Genre.Comedy;
		
		Video v1 = new Video(title, url, durationInMinutes, genre);
		
		//Test if the object is actually created
		System.out.println("Details of Video 1:");
		System.out.println(v1);
		
		 
		//Test if comments are added
		v1.addComments("Funny!");
		v1.addComments("Awesome");
		v1.addComments("bad");
		System.out.println("The comments of Video 1");
		System.out.println(v1.getComments());
		
		//Creating video 2 
		Video v2 = new Video("KSi","www.yt.com/shwuhwuh",30,Genre.Comedy);
        //Testing CompareTo Method
		System.out.println("Testing compareTo Method");
	    System.out.println(v1.compareTo(v2));
	    
	    //Creating video 3
	    Video v3 = new Video("KSi","www.video.com/uhedbefhd",10,Genre.Comedy);
	    
	    //Testing Copy Constructor 
	    Video v4 = new Video(v1);
	    System.out.println("Details of copied Video object:\n" + v4);
	    System.out.println("If both details of video 1 and video 4 are equals:"+v1.toString().equals(v4.toString()));
	    
	    //Testing Video EqualsTo Method
	    System.out.println("If video 1 is equals to video3: "+ v1.equals(v3));
	    System.out.println("If video 2 is equal to video 3: "+v2.equals(v3));
	    
	    //Creating PlayList 
	    System.out.println();
	    System.out.println("-------Playlist Test-----");
	    Playlist p1 = new Playlist("playlist1");
	    //Testing Add to Playlist method
	    p1.addToPlaylist("Video 1");
	    //Checking videotitles list if Video 1 has been added to Playlist
	    System.out.println("Videos in Playlist" + p1.getPlaylistVideosTitles());
	    //Adding a few more videos to playlist
	    p1.addToPlaylist("Video 2");
	    p1.addToPlaylist("Video 3");
	    //Adding videos with same titles
	    p1.addToPlaylist("Video 1");
	    p1.addToPlaylist("Video 5");
	    p1.addToPlaylist("Video 6");
	    p1.addToPlaylist("Video 6");
	    System.out.println("\n Videos in Playlist" + p1.getPlaylistVideosTitles());
	    //Removing Video 1 from the playlist
	    p1.removeFromPlaylistAll("Video 1");
	    System.out.println("Videos in Playlist (After video 1 removed)"+p1.getPlaylistVideosTitles());
	    //Removing a nonexistent variable
	    System.out.println("Able to remove a non-existent variable :" +p1.removeFromPlaylistAll("not there"));
	    //Copy constructor
	    Playlist p2 = new Playlist(p1);
	    System.out.println("Copy playlist details :\n"+p2.toString());
	    System.out.println();
	    //Shuffling Test
	    p1.shuffleVideoTitles(null);
	    System.out.println("Shuffles playlist with null parameter"+p1.getPlaylistVideosTitles());
	    System.out.println("====TubeVideosManager====");
	    //Creating tubeManager
	    TubeVideosManager t1 = new TubeVideosManager();
	    
	    //Adding Videos to Database
	    t1.addVideoToDB("MrBeast","www.yt.com/uhfeuh",20,Genre.FilmAnimation);
	    t1.addVideoToDB("KSI","www.yt.com/uhfrrrh",30,Genre.Educational);
	    t1.addVideoToDB("HowtoBasic","www.yt.com/uhfeuhw",45,Genre.Documentary);
	    t1.addVideoToDB("Parsi","www.yt.com/ufdsdfuh",2,Genre.Comedy);
	    t1.addVideoToDB("Soccer","\"www.yt.com/yrhuyrgf",90,Genre.Documentary);
	    //testing if Videos are added to the Database
	    System.out.println("All videos in the database:\n"+t1.getAllVideosInDB());
	    //Adding Playlist to playlist list
	    t1.addPlaylist("FilmAnimation");
	    t1.addPlaylist("Education");
	    t1.addPlaylist("Documentary");
	    t1.addPlaylist("Comedy");
	    //testing if playlists
	    System.out.println("All Playlists in Playlists:\n"+t1.getPlaylistsNames());
	    
	    //Testing Find Video method
	    System.out.println("Finding videos in the database");
	    if (t1.findVideo("HowtoBasic")!=null) {
	    	System.out.println("HowtoBasic is found in the Database");
	    }
	    else {
	    	System.out.println("HowtoBasic is not found");
	    }
	    
	    if (t1.findVideo("nonexistenr")!=null) {
	    	System.out.println("nonexistent is found in the Database");
	    }
	    else {
	    	System.out.println("nonexistent is not found");
	    }
	    
	    //Adding Comments 
	    t1.addComments("MrBeast","How Cool");
	    t1.addComments("MrBeast", "SO Danm cool");
	    t1.addComments("HowtoBasic", "Complicated");
	    System.out.println("Comments added on MrBeast video"+t1.getAllVideosInDB().get(0).getComments());
	    //Trying to add comments on nonexistent video
	    t1.addComments("Racism", "ewww");
	    
	    //addVideoToPlaylist method
	    System.out.println("Adding MrBeast to FilmAnimation");
	    t1.addVideoToPlaylist("MrBeast", "FilmAnimation");
	    System.out.println(t1.getPlaylist("FilmAnimation"));
	    System.out.println("Adding KSI to Education");
	    t1.addVideoToPlaylist("KSI", "Education");
	    System.out.println(t1.getPlaylist("Education"));
	    
	    //Search for Videos test 
	    System.out.println(t1.searchForVideos("Education", null, 60, null));
	    System.out.println(t1.searchForVideos("FilmAnimation", "MrBeast", 100, null));
	    System.out.println("DONE WITH STUDENT TEST");
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	}

}
