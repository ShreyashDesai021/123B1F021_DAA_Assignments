//ASSIGNMENT 2:
//        Movie Recommendation System Optimization
//        A popular OTT platform, StreamFlix, offers personalized recommendations by sorting movies
//        based on user preferences, such as IMDB rating, release year, or watch time popularity.
//        However, during peak hours, sorting large datasets slows down the system.
//        As a backend engineer, you must:
//        ● Implement Quicksort to efficiently sort movies based on various user-selected
//        parameters.
//        ● Handle large datasets containing of movies while maintaining fast response times
//
//        Code :
import java.util.Scanner;

class Film {
    String name;
    double rating;
    int year;
    int views;

    public Film(String name, double rating, int year, int views) {
        this.name = name;
        this.rating = rating;
        this.year = year;
        this.views = views;
    }
}

public class Assignment2 {

    // QuickSort function to sort array based on selected parameter
    public static void quickSort(Film[] films, int left, int right, String criterion) {
        if (left < right) {
            int pivotPos = partition(films, left, right, criterion);
            quickSort(films, left, pivotPos - 1, criterion);
            quickSort(films, pivotPos + 1, right, criterion);
        }
    }

    // Partition function for QuickSort
    private static int partition(Film[] films, int low, int high, String criterion) {
        Film pivot = films[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean shouldSwap = false;

            // Decide if swap is needed based on criterion
            switch (criterion) {
                case "rating":
                    if (films[j].rating <= pivot.rating) shouldSwap = true;
                    break;
                case "year":
                    if (films[j].year <= pivot.year) shouldSwap = true;
                    break;
                case "views":
                    if (films[j].views <= pivot.views) shouldSwap = true;
                    break;
            }

            if (shouldSwap) {
                i++;
                Film temp = films[i];
                films[i] = films[j];
                films[j] = temp;
            }
        }

        // Place pivot in the correct position
        Film temp = films[i + 1];
        films[i + 1] = films[high];
        films[high] = temp;

        return i + 1;
    }

    // Print the films in tabular format
    public static void displayFilms(Film[] films) {
        System.out.printf("%-20s %-8s %-6s %-6s%n", "Title", "Rating", "Year", "Views");
        System.out.println("-----------------------------------------------");
        for (Film f : films) {
            System.out.printf("%-20s %-8.1f %-6d %-6d%n", f.name, f.rating, f.year, f.views);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Film[] films = {
                new Film("Avengers", 8.5, 2012, 9500),
                new Film("Inception", 8.8, 2010, 8700),
                new Film("Titanic", 7.8, 1997, 12000),
                new Film("Matrix", 8.7, 1999, 9800),
                new Film("Interstellar", 8.6, 2014, 7300),
                new Film("Joker", 8.4, 2019, 8200),
                new Film("Avatar", 7.9, 2009, 15000),
                new Film("Gladiator", 8.5, 2000, 6600),
                new Film("Up", 8.2, 2009, 5400),
                new Film("Coco", 8.4, 2017, 4700)
        };

        System.out.print("Enter sorting criterion (rating/year/views): ");
        String criterion = scanner.next().toLowerCase();

        if (!criterion.equals("rating") && !criterion.equals("year") && !criterion.equals("views")) {
            System.out.println("Invalid option! Exiting program.");
            scanner.close();
            return;
        }

        long startTime = System.nanoTime();
        quickSort(films, 0, films.length - 1, criterion);
        long endTime = System.nanoTime();

        System.out.println("\n--- Movies Sorted by " + criterion + " ---");
        displayFilms(films);

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("\nSorting completed in %.3f ms%n", durationMs);

        scanner.close();
    }
}
