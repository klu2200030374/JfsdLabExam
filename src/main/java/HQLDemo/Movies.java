package HQLDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="movie_table") // Changed to 'movie_table' for clarity
public class Movies
{
	@Id
	private int id;

	@Column(name="title", length=100, nullable=false)
	private String title;

	@Column(name="director", length=100, nullable=false)
	private String director;

	@Column(name="genre", length=50, nullable=false)
	private String genre;

	@Column(name="release_year", nullable=false)
	private int releaseYear;

	@Column(name="rating", nullable=false)
	private double rating;

	@Column(name="available", nullable=false)
	private boolean available; // true for available, false for not available

	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Movies [id=" + id + ", title=" + title + ", director=" + director + ", genre=" + genre 
		        + ", releaseYear=" + releaseYear + ", rating=" + rating + ", available=" + available + "]";
	}
}
