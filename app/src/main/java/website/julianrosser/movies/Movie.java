package website.julianrosser.movies;

/**
 * Class which holds movie information
 */

public class Movie {

    String title;
    String poster_url;
    String summary;
    int id;
    String release_date;
    float popularity;
    boolean video;
    float vote_average;
    float vote_count;

    public Movie(String name, String url, String overview, int ID) {

        title = name;
        poster_url = url;
        summary = overview;
        id = ID;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public String getSummary() {
        return summary;
    }

    public int getID() {
        return id;
    }
}
