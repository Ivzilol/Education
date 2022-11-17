package Exam_preparation_02;

import java.util.*;

public class HeroesOfCodeAndLogic_03_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Integer>> heroesMap = new LinkedHashMap<>();
        for (int index = 0; index < n; index++) {
            String input = scanner.nextLine();
            String heroName = input.split(" ")[0];
            int HP = Integer.parseInt(input.split(" ")[1]);
            int MP = Integer.parseInt(input.split(" ")[2]);
            List<Integer> stats = new ArrayList<>();
            stats.add(HP);
            stats.add(MP);
            heroesMap.put(heroName, stats);
        }
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            String command = input.split(" - ")[0];
            switch (command) {
                case "CastSpell":
                    String heroName = input.split(" - ")[1];
                    int mpNeed = Integer.parseInt(input.split(" - ")[2]);
                    String spellName = input.split(" - ")[3];
                    if (!heroesMap.containsKey(heroName)) {
                        break;
                    }
                    List<Integer> manaPoints = heroesMap.get(heroName);
                    int currentHeal = manaPoints.get(0);
                    int currentMana = manaPoints.get(1);
                    int usedMana = manaPoints.get(1) - mpNeed;
                    List<Integer> newSpellList = new ArrayList<>();
                    if (usedMana < 0) {
                        System.out.printf("%s does not have enough MP to cast %s!\n", heroName, spellName);
                        newSpellList.add(currentHeal);
                        newSpellList.add(currentMana);
                        heroesMap.put(heroName, newSpellList);
                    } else {
                        System.out.printf("%s has successfully cast %s and now has %d MP!\n",
                                heroName, spellName, usedMana);
                        newSpellList.add(currentHeal);
                        newSpellList.add(usedMana);
                        heroesMap.put(heroName, newSpellList);
                    }
                    break;
                case "TakeDamage":
                    String attackedCharacter = input.split(" - ")[1];
                    int damage = Integer.parseInt(input.split(" - ")[2]);
                    String attacker = input.split(" - ")[3];
                    if (!heroesMap.containsKey(attackedCharacter)) {
                        break;
                    }
                    List<Integer> healPoints = heroesMap.get(attackedCharacter);
                    int lostHeal = healPoints.get(0) - damage;
                    int mana = healPoints.get(1);
                    List<Integer> newAmountList = new ArrayList<>();
                    if (lostHeal <= 0) {
                        System.out.printf("%s has been killed by %s!\n", attackedCharacter, attacker);
                        heroesMap.remove(attackedCharacter);
                    } else {
                        newAmountList.add(lostHeal);
                        newAmountList.add(mana);
                        heroesMap.put(attackedCharacter, newAmountList);
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!\n",
                                attackedCharacter, damage, attacker, lostHeal);
                    }
                    break;
                case "Recharge":
                    String nameHero = input.split(" - ")[1];
                    int amount = Integer.parseInt(input.split(" - ")[2]);
                    if (!heroesMap.containsKey(nameHero)) {
                        break;
                    }
                    List<Integer> amountList = heroesMap.get(nameHero);
                    int heal = amountList.get(0);
                    int manaCurrent = amountList.get(1);
                    int rechargeMana = amountList.get(1) + amount;
                    List<Integer> newManaList = new ArrayList<>();
                    if (rechargeMana > 200) {
                        rechargeMana = 200;
                        newManaList.add(heal);
                        newManaList.add(rechargeMana);
                        heroesMap.put(nameHero, newManaList);
                        System.out.printf("%s recharged for %d MP!\n", nameHero, 200 - manaCurrent);
                    } else {
                        newManaList.add(heal);
                        newManaList.add(rechargeMana);
                        heroesMap.put(nameHero, newManaList);
                        System.out.printf("%s recharged for %d MP!\n", nameHero, amount);
                    }
                    break;
                case "Heal":
                    String hero = input.split(" - ")[1];
                    int amountHeal = Integer.parseInt(input.split(" - ")[2]);
                    if (!heroesMap.containsKey(hero)) {
                        break;
                    }
                    List<Integer> healList = heroesMap.get(hero);
                    int healBeforeHealing = healList.get(0);
                    int healAfterHealing = healList.get(0) + amountHeal;
                    int manaHero = healList.get(1);
                    List<Integer> newHealList = new ArrayList<>();
                    if (healAfterHealing > 100) {
                        healAfterHealing = 100;
                        newHealList.add(healAfterHealing);
                        newHealList.add(manaHero);
                        heroesMap.put(hero, newHealList);
                        System.out.printf("%s healed for %d HP!\n", hero, 100 - healBeforeHealing);
                    } else {
                        newHealList.add(healAfterHealing);
                        newHealList.add(manaHero);
                        heroesMap.put(hero, newHealList);
                        System.out.printf("%s healed for %d HP!\n", hero, amountHeal);
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        for (Map.Entry<String, List<Integer>> entry : heroesMap.entrySet()) {
            System.out.printf("%s\n HP: %d\n MP: %d\n", entry.getKey(), entry.getValue().get(0),
                    entry.getValue().get(1));
        }
    }
}
