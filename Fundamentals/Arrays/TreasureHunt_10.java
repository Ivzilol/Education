package Exercise_03;

import org.w3c.dom.DOMStringList;

import java.awt.font.FontRenderContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TreasureHunt_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] chest = scanner.nextLine().split("\\|");
        String input = scanner.nextLine();
        String newLoot = "";
        String[] newChest = newLoot.split(" ");
        while (!input.equals("Yohoho!")){
            String[] chestParts = input.split(" ");
            String command = chestParts[0];
            if (command.equals("Loot")){
                for (int i = 1; i < chestParts.length; i++) {
                    String loot = chestParts[i];
                    boolean isMuch = true;
                        for (int j = 0; j < chest.length; j++) {
                        String items = chest[j];
                        if (Objects.equals(loot, items)){
                            isMuch = false;
                        break;
                        }
                    }
                    if (isMuch){

                        newLoot += (loot + " ");
                        System.out.println();
                    }
                }

            }else if (command.equals("Drop")){

            }else if (command.equals("Steal")){

            }





            input = scanner.nextLine();
        }
    }
}
