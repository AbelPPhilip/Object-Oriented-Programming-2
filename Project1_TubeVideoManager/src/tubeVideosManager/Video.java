package tubeVideosManager;

import java.util.ArrayList;



/**
 * A video has a title, url, durationInMinutes and a genre. Comments about the
 * video are kept in an ArrayList (comments). For YouTube videos the url is the
 * one generated by the "Copy embed code" option.
 * 
 * @author UMCP CS Department
 *
 */
public class Video implements Comparable<Video> {
	private String title, url;
	private int durationInMinutes;
	private Genre videoGenre;
	private ArrayList<String> comments;

	/**
	 * Initializes a video object. If any parameter is null or if a string parameter
	 * is a blank (according to String class isBlank() method), the method will
	 * throw an IllegalArgumentException (with any message) and perform no
	 * processing. Also the same exception will be thrown if the duration is zero or
	 * negative.
	 * 
	 * @param title
	 * @param url
	 * @param durationInMinutes
	 * @param videoGenre
	 */
	public Video(String title, String url, int durationInMinutes, Genre videoGenre) {
		if (title== null||url==null||title.isBlank()||url.isBlank()|| durationInMinutes==0) {
			throw new IllegalArgumentException("Enter a valid value");
		}
		this.title = title;
		this.url = url;
		this.durationInMinutes = durationInMinutes;
		this.videoGenre = videoGenre;
		comments = new ArrayList <String>();
	}

	/**
	 * Initializes the Video object so changes to the parameter do not affect the
	 * current object. Your implementation must be efficient (avoid any unnecessary
	 * copies).
	 * 
	 * @param video
	 */
	public Video(Video video) {
		if (video == null) {
			throw new IllegalArgumentException("Enter a valid object");
		}
		this.title = video.getTitle();
		this.url = video.getUrl();
		this.durationInMinutes = video.getDurationInMinutes();
		this.videoGenre = video.getGenre();
		comments = video.getComments();
	}

	/**
	 * Get method for title
	 * 
	 * @return title string
	 */
	public String getTitle() {
		return title; 
		
	}

	/**
	 * Get method for url
	 * 
	 * @return url string
	 */
	public String getUrl() {
		return url; 
		
	}

	/**
	 * Get method for duration
	 * 
	 * @return duration
	 */
	public int getDurationInMinutes() {
		return durationInMinutes;
		
	}

	/**
	 * Get method for video genre
	 * 
	 * @return string with genre
	 */
	public Genre getGenre() {
		return videoGenre; 
		
	}

	/**
	 * Provided; please don't modify. toString for class
	 * 
	 * @return string with object info
	 */
	public String toString() {
		String answer = "Title: " + "\"" + title + "\"\n";

		answer += "Url: " + url + "\n";
		answer += "Duration (minutes): " + durationInMinutes + "\n";
		answer += "Genre: " + videoGenre + "\n";

		return answer;
	}

	/**
	 * Adds specified comments to the video. If the parameter is null or is a blank
	 * string (according to String class isBlank() method) the method will throw an
	 * IllegalArgumentException (with any message) and perform no processing.
	 * 
	 * @param comments
	 * @return true if comments added; false otherwise
	 */
	public boolean addComments(String comments) {
		if (comments==null || comments.isBlank()) {
			throw new IllegalArgumentException("Enter valid comments");
		}
		this.comments.add(comments);
		return true;
		
		
	}

	/**
	 * Returns copy so changes to the copy does not affect the original. Your
	 * implementation must be efficient (avoid any unnecessary copies).
	 * 
	 * @return ArrayList of strings
	 */
	public ArrayList<String> getComments() {
		ArrayList<String> copy = new ArrayList <String>();
		for (int i=0;i<comments.size();i++) {
			copy.add(comments.get(i));
		}
		return copy;
	}

	/**
	 * Videos will be compared using title. If we were to sort an ArrayList of
	 * Videos, they will appear in lexicographical (alphabetical) order (e.g, "A",
	 * "B", "C").
	 * 
	 * @return negative, 0, or positive value
	 */
	public int compareTo(Video video) {
		return this.getTitle().compareTo(video.getTitle());
	}

	/**
	 * Two Video objects are considered equal if they have the same title. Implement
	 * the method using the instanceof operator rather than using getClass().
	 * 
	 * @return true if objects are considered equal; false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		else if (!(obj instanceof Video )) {
			return false;
		}
		else {
			return this.getTitle().equals(((Video) obj).getTitle());
		}
		
	}
}
