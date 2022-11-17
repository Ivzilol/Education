package Exercise_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Articles2_04 {
    static class Articles2 {
        String title;
        String content;
        String author;

        public Articles2(String title, String content, String author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
        @Override
        public String toString (){
            return String.format("%s - %s: %s", this.title, this.content, this.author);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Articles2> articleList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String input = scanner.nextLine();
            String title = input.split(", ")[0];
            String content = input.split(", ")[1];
            String author = input.split(", ")[2];

            Articles2 article = new Articles2(title, content, author);
            articleList.add(article);
        }
        String command = scanner.nextLine();
        for (Articles2 article : articleList) {
            System.out.println(article.toString());
        }
    }
}
