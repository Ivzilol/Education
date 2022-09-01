package Lab_03;

import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniParty_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> vip = new TreeSet<>();
        Set<String> regular = new TreeSet<>();

        String guestId = scanner.nextLine();

        while (!guestId.equals("PARTY")){
            if (Character.isDigit(guestId.charAt(0))){
                vip.add(guestId);
            }else {
                regular.add(guestId);
            }
            guestId = scanner.nextLine();
        }
        String guestedCome = scanner.nextLine();
        while (!guestedCome.equals("END")){
            vip.remove(guestedCome);
            regular.remove(guestedCome);
            guestedCome = scanner.nextLine();
        }
        System.out.printf("%d\n", (vip.size() + regular.size()));
        for (String vipId : vip){
            System.out.println(vipId);
        }
        for (String regularId : regular){
            System.out.println(regularId);
        }
    }
}
