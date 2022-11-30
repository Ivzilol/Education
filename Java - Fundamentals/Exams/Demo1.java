import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Demo1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> healthMap = new LinkedHashMap<>();
        Map<String, Integer> manaMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String inputHeroes = scanner.nextLine();
            String heroName = inputHeroes.split("\\s+")[0];
            int health = Integer.parseInt(inputHeroes.split("\\s+")[1]);
            int mana = Integer.parseInt(inputHeroes.split("\\s+")[2]);
            healthMap.put(heroName, health);
            manaMap.put(heroName, mana);
        }

        String inputCommands = scanner.nextLine();
        while (!inputCommands.equals("End")) {
            String heroName = inputCommands.split("\\s+-\\s+")[1];
            switch (inputCommands.split("\\s+-\\s+")[0]) {
                case "CastSpell":
                    int manaNeeded = Integer.parseInt(inputCommands.split("\\s+-\\s+")[2]);
                    String spellName = inputCommands.split("\\s+-\\s+")[3];
                    if (manaNeeded > manaMap.get(heroName)) {
                        System.out.printf("%s does not have enough MP to cast %s!\n", heroName, spellName);
                    } else {
                        int newQuantityMana = manaMap.get(heroName) - manaNeeded;
                        manaMap.put(heroName, newQuantityMana);
                        System.out.printf("%s has successfully cast %s and now has %d MP!\n",
                                heroName, spellName, manaMap.get(heroName));
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(inputCommands.split("\\s+-\\s+")[2]);
                    String attacker = inputCommands.split("\\s+-\\s+")[3];
                    if (healthMap.get(heroName) > damage) {
                        int newQuantityHealth = healthMap.get(heroName) - damage;
                        healthMap.put(heroName, newQuantityHealth);
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!\n",
                                heroName, damage, attacker, newQuantityHealth);
                    } else {
                        System.out.printf("%s has been killed by %s!\n", heroName, attacker);
                        healthMap.remove(heroName);
                        manaMap.remove(heroName);

                    }
                    break;
                case "Recharge":
                    int amountMana = Integer.parseInt(inputCommands.split("\\s+-\\s+")[2]);
                    int currentQuantityMana = manaMap.get(heroName);
                    int newQuantityMana = currentQuantityMana + amountMana;
                    if (newQuantityMana <= 200) {
                        manaMap.put(heroName, newQuantityMana);
                        System.out.printf("%s recharged for %d MP!\n", heroName, amountMana);
                    } else {
                        manaMap.put(heroName, 200);
                        System.out.printf("%s recharged for %d MP!\n", heroName, 200 - currentQuantityMana);
                    }
                    break;
                case "Heal":
                    int amountHealth = Integer.parseInt(inputCommands.split("\\s+-\\s+")[2]);
                    int currentQuantityHealth = healthMap.get(heroName);
                    int newQuantityHealth = currentQuantityHealth + amountHealth;
                    if (newQuantityHealth <= 100) {
                        healthMap.put(heroName, newQuantityHealth);
                        System.out.printf("%s healed for %d HP!\n", heroName, amountHealth);
                    } else {
                        healthMap.put(heroName, 100);
                        System.out.printf("%s healed for %d HP!\n", heroName, 100 - currentQuantityHealth);
                    }
                    break;
            }
            inputCommands = scanner.nextLine();
        }
        for (Map.Entry<String, Integer> entry : healthMap.entrySet()) {
            System.out.printf("%s\n  HP: %d\n  MP: %d\n", entry.getKey(),
                    entry.getValue(), manaMap.get(entry.getKey()));
        }
    }
}