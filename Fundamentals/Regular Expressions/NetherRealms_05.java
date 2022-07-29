package Exercise_09;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NetherRealms_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> demons = Arrays.stream(scanner.nextLine().split(" *,{1} *")).collect(Collectors.toList());
        Map<String, List<Double>> demonsStats = new LinkedHashMap<>();

        for (String currentDemon : demons) {
            List<Double> demonStats = new ArrayList<>();
            String regexHealth = "[^\\d\\+\\-*\\/\\.\"\" \"\"]";
            Pattern patternHealth = Pattern.compile(regexHealth);
            Matcher matcherHealth = patternHealth.matcher(currentDemon);
            StringBuilder demonName = new StringBuilder();
            while (matcherHealth.find()) {
                demonName.append(matcherHealth.group());
            }
            double demonHealth = 0;
            for (int indexHealth = 0; indexHealth < demonName.length(); indexHealth++) {
                char currentChar = demonName.charAt(indexHealth);
                demonHealth += currentChar;
            }
            demonStats.add(demonHealth);
            String regexDamage = "(?:\\+|-)?[0-9]+(?:\\.[0-9]+)?";
            Pattern patternDamage = Pattern.compile(regexDamage);
            Matcher matcherDamage = patternDamage.matcher(currentDemon);
            List<String> damageList = new ArrayList<>();
            while (matcherDamage.find()) {
                damageList.add(matcherDamage.group());
            }
            double demonDamage = 0;
            for (String s : damageList) {
                double currentNumber = Double.parseDouble(s);
                demonDamage += currentNumber;
            }
            String regexDamage2 = "[*\\/]";
            Pattern patternDamage2 = Pattern.compile(regexDamage2);
            Matcher matcherDamage2 = patternDamage2.matcher(currentDemon);
            StringBuilder multiOrDiv = new StringBuilder();
            while (matcherDamage2.find()) {
                multiOrDiv.append(matcherDamage2.group());
            }
            for (int indexMultiOrDiv = 0; indexMultiOrDiv < multiOrDiv.length(); indexMultiOrDiv++) {
                char currentSymbol = multiOrDiv.charAt(indexMultiOrDiv);
                if (currentSymbol == '*') {
                    demonDamage = demonDamage * 2;
                } else if (currentSymbol == '/') {
                    demonDamage = demonDamage / 2;
                }
            }
            demonStats.add(demonDamage);
            demonsStats.put(currentDemon, demonStats);
        }
       for (Map.Entry<String, List<Double>> entry : demonsStats.entrySet()){
           System.out.printf("%s - %.0f health, %.2f damage%n", entry.getKey(), entry.getValue().get(0),
                   entry.getValue().get(1));
       }
    }
}
