package Exercises_03;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserLogs_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<String, LinkedHashMap<String, Integer>> data = new TreeMap<>();
        String input = scanner.nextLine();

        while (!input.equals("end")){
            String[] inputParts = input.split("\\s+");
            String ip = inputParts[0].split("=")[1];
            //String massage = inputParts[1].split("=")[1];
            String username = inputParts[2].split("=")[1];
            if (!data.containsKey(username)){
                data.put(username, new LinkedHashMap<>(){{
                    put(ip, 1);
                }});
            }else {
                Map<String, Integer> currentIps = data.get(username);
                if (currentIps.containsKey(ip)){
                    int currentTime = currentIps.get(ip);
                    currentIps.put(ip, currentTime + 1);
                }else {
                    currentIps.put(ip, 1);
                }
            }
            input = scanner.nextLine();
        }
        data.keySet().forEach(username -> {
            System.out.println(username + ": ");
            Map<String, Integer> ips = data.get(username);
            AtomicInteger countSize = new AtomicInteger();
            ips.entrySet().forEach(entry -> {
                if (countSize.get() != ips.size() - 1){
                    System.out.print(entry.getKey() + " => " + entry.getValue() + ", ");
                }else {
                    System.out.print(entry.getKey() + " => " + entry.getValue() + ".");
                }
                countSize.getAndIncrement();
            });
            System.out.println();
        });
    }
}
