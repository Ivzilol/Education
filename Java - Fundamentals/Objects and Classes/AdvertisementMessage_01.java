package Exercise_06;

import java.util.Random;
import java.util.Scanner;

public class AdvertisementMessage_01 {
    static class AdvertisementMessage {
        String phrases;
        String events;
        String authors;
        String cities;

        public AdvertisementMessage(String phrases, String events, String authors, String cities) {
            this.phrases = phrases;
            this.events = events;
            this.authors = authors;
            this.cities = cities;
        }

        public String getPhrases() {
            return phrases;
        }

        public void setPhrases(String phrases) {
            this.phrases = phrases;
        }

        public String getEvents() {
            return events;
        }

        public void setEvents(String events) {
            this.events = events;
        }

        public String getAuthors() {
            return authors;
        }

        public void setAuthors(String authors) {
            this.authors = authors;
        }

        public String getCities() {
            return cities;
        }

        public void setCities(String cities) {
            this.cities = cities;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] randomPhrases = {"Excellent product.", "Such a great product.", "I always use that product.",
                "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
        String[] randomEvents = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!",
                "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] randomAuthors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] randomCities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};
        Random rnd = new Random();
        for (int i = 1; i <= n; i++) {
            int randomPhraseNum = rnd.nextInt(randomPhrases.length);
            int randomEventsNum = rnd.nextInt(randomEvents.length);
            int randomAuthorsNum = rnd.nextInt(randomAuthors.length);
            int randomCitiesNum = rnd.nextInt(randomCities.length);
            System.out.println(String.join(" ", randomPhrases[randomPhraseNum] + " "
                    + randomEvents[randomEventsNum] + " " + randomAuthors[randomAuthorsNum] + " - "
                    + randomCities[randomCitiesNum]));
        }
    }
}
