package Traning04;

public class Clock_09 {
    public static void main(String[] args) {
        for (int hours = 0; hours < 24; hours++) {
            for (int minutes = 0; minutes < 60; minutes++) {
                for (int seconds = 0; seconds < 60; seconds++)
                System.out.println(hours + " : " + minutes + " : " + seconds);
            }
        }
    }
}
