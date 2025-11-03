/*
PRN: 123B1F021
NAME: Shreyash Surendra Desai
DATE: 21/07/2025
ASSIGNMENT 2:
PROBLEM STATEMENT:
Movie Recommendation System Optimization
A popular OTT platform, StreamFlix, offers personalized recommendations by sorting movies
based on user preferences, such as IMDB rating, release year, or watch time popularity.
However, during peak hours, sorting large datasets slows down the system.
As a backend engineer, you must:
● Implement Quicksort to efficiently sort movies based on various user-selected
parameters.
● Handle large datasets containing of movies while maintaining fast response times
*/

//SOLUTION:
import java.util.Scanner;

class Movie {
    String name;
    double rating;
    int year;
    int views;

    public Movie(String name, double rating, int year, int views) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.views = views;
    }
}

public class Assignment2 {

    // QuickSort function to sort array based on selected parameter
    public static void quickSort(Movie[] movies, int left, int right, String criterion) {
        if (left < right) {
            int pivotPos = partition(movies, left, right, criterion);
            quickSort(movies, left, pivotPos - 1, criterion);
            quickSort(movies, pivotPos + 1, right, criterion);
        }
    }

    // Partition function for QuickSort
    private static int partition(Movie[] movies, int low, int high, String criterion) {
        Movie pivot = movies[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean shouldSwap = false;

            // Decide if swap is needed based on criterion
            switch (criterion) {
                case "rating":
                    if (movies[j].rating <= pivot.rating) shouldSwap = true;
                    break;
                case "year":
                    if (movies[j].year <= pivot.year) shouldSwap = true;
                    break;
                case "views":
                    if (movies[j].views <= pivot.views) shouldSwap = true;
                    break;
            }

            if (shouldSwap) {
                i++;
                Movie temp = movies[i];
                movies[i] = movies[j];
                movies[j] = temp;
            }
        }

        // Place pivot in the correct position
        Movie temp = movies[i + 1];
        movies[i + 1] = movies[high];
        movies[high] = temp;

        return i + 1;
    }

    // Print the films in tabular format
    public static void displayFilms(Movie[] movies) {
        System.out.printf("%-20s %-8s %-6s %-6s%n", "Title", "Rating", "Year", "Views");
        System.out.println("-----------------------------------------------");
        for (Movie f : movies) {
            System.out.printf("%-20s %-8.1f %-6d %-6d%n", f.name, f.rating, f.year, f.views);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Movie[] movies = {
                new Movie("Avengers", 8.5, 2012, 9500),
                new Movie("Inception", 8.8, 2010, 8700),
                new Movie("Titanic", 7.8, 1997, 12000),
                new Movie("Matrix", 8.7, 1999, 9800),
                new Movie("Interstellar", 8.6, 2014, 7300),
                new Movie("Joker", 8.4, 2019, 8200),
                new Movie("Avatar", 7.9, 2009, 15000),
                new Movie("Gladiator", 8.5, 2000, 6600),
                new Movie("Up", 8.2, 2009, 5400),
                new Movie("Coco", 8.4, 2017, 4700)
        };

        System.out.print("Enter sorting criterion (rating/year/views): ");
        String criterion = scanner.next().toLowerCase();

        if (!criterion.equals("rating") && !criterion.equals("year") && !criterion.equals("views")) {
            System.out.println("Invalid option! Exiting program.");
            scanner.close();
            return;
        }

        long startTime = System.nanoTime();
        quickSort(movies, 0, movies.length - 1, criterion);
        long endTime = System.nanoTime();

        System.out.println("\n--- Movies Sorted by " + criterion + " ---");
        displayFilms(movies);

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("\nSorting completed in %.3f ms%n", durationMs);

        scanner.close();
    }
}

