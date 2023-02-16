package ExamPreparetion_01;

import java.util.*;

public class ClimbThePeaks_01_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputDailyPortion = scanner.nextLine();
        String inputDailyStamina = scanner.nextLine();

        ArrayDeque<Integer> portion = new ArrayDeque<>();
        ArrayDeque<Integer> stamina = new ArrayDeque<>();

        Arrays.stream(inputDailyPortion.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(portion::push);
        Arrays.stream(inputDailyStamina.split(",\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(stamina::offer);
        List<String> conquerPeaks = new ArrayList<>();
        List<Integer> peaks = new ArrayList<>();
        peaks.add(80);
        peaks.add(90);
        peaks.add(100);
        peaks.add(60);
        peaks.add(70);

        int daysCount = 0;
        while (daysCount < 7) {
            daysCount++;
            int currentPortion = portion.peek();
            int currentStamina = stamina.peek();
            int sum = currentPortion + currentStamina;
            if (peaks.isEmpty()) {
                break;
            }
            int peaksForConquer = peaks.get(0);
            if (sum >= peaksForConquer) {
                if (peaks.get(0) == 80) {
                    conquerPeaks.add("Vihren");
                } else if (peaks.get(0) == 90) {
                    conquerPeaks.add("Kutelo");
                } else if (peaks.get(0) == 100) {
                    conquerPeaks.add("Banski Suhodol");
                } else if (peaks.get(0) == 60) {
                    conquerPeaks.add("Polezhan");
                } else if (peaks.get(0) == 70) {
                    conquerPeaks.add("Kamenitza");
                }
                peaks.remove(0);
            }
            portion.pop();
            stamina.poll();
        }
        if (peaks.isEmpty()) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
            System.out.println("Conquered peaks:\n" +
                    "Vihren\n" +
                    "Kutelo\n" +
                    "Banski Suhodol\n" +
                    "Polezhan\n" +
                    "Kamenitza\n");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
            if (!conquerPeaks.isEmpty()) {
                System.out.println("Conquered peaks:");
                for (String currentPeaks : conquerPeaks) {
                    System.out.println(currentPeaks);
                }
            }
        }
    }
}
