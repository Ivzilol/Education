package Lab_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Songs_04 {
    static class Song {
        String typeList;
        String name;
        String time;

        public void setTypeList(String typeList) {
            this.typeList = typeList;
        }

        public String getTypeList() {
            return typeList;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Song> listSongs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<String> inputSongs = List.of(scanner.nextLine().split("_"));
            String inputType = inputSongs.get(0);
            String inputName = inputSongs.get(1);
            String inputTime = inputSongs.get(2);

            Song currentSong = new Song();
            currentSong.setTypeList(inputType);
            currentSong.setName(inputName);
            currentSong.setTime(inputTime);
            listSongs.add(currentSong);
        }
        String command = scanner.nextLine();
        if (command.equals("all")) {
            for (Song item : listSongs) {
                System.out.println(item.getName());
            }
        } else {
            for (Song item : listSongs) {
                if (item.getTypeList().equals(command)) {
                    System.out.println(item.getName());
                }
            }
        }
    }
}