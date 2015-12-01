package website.julianrosser.movies;

/**
 * Class which holds movie information
 */

public class Movie {

    private String title;
    private String poster_url;
    private String summary;
    private int id;
    private String release_date;
    private float popularity;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public Movie(String name, String url, String overview, int ID, String release, double vote_ave, int vote_cou) {

        title = name;
        poster_url = url;
        summary = overview;
        id = ID;
        vote_average = vote_ave;
        vote_count = vote_cou;
        release_date = release;

    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrlSize500() {
        return  "http://image.tmdb.org/t/p/w500/" + poster_url;
    }

    public String getPosterUrlSize342() {
        return  "http://image.tmdb.org/t/p/w342/" + poster_url;
    }

    public String getSummary() {
        return summary;
    }

    public int getID() {
        return id;
    }

    public String getRelease_date() {
        return "Released: " + release_date;
    }

    public String getVoteString() {
        return "Rating: " + String.format( "%.2f", vote_average) + " (" + vote_count + " votes)";
    }

}
