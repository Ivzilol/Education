package Exercise_07;

import java.util.*;


public class StudentAcademy_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> mapEvaluation = new LinkedHashMap<>();
        for (int i = 1; i <= n; i++) {
            String name = scanner.nextLine();
            double evaluation = Double.parseDouble(scanner.nextLine());
            if (!mapEvaluation.containsKey(name)) {
                mapEvaluation.put(name, new ArrayList<>());
                mapEvaluation.get(name).add(evaluation);
            } else {
                List<Double> currentEvaluation = mapEvaluation.get(name);
                currentEvaluation.add(evaluation);
                mapEvaluation.put(name, currentEvaluation);
            }
        }
        double average = 0;
        Map<String, Double> finalEvaluation = new LinkedHashMap<>();
        for (Map.Entry<String, List<Double>> entry : mapEvaluation.entrySet()) {
            average = 0;
            for (int i = 0; i <= entry.getValue().size() - 1; i++) {
                average += entry.getValue().get(i);
            }
            average = average / entry.getValue().size();
            if (average >= 4.50) {
                finalEvaluation.put(entry.getKey(), average);
            }
        }
        finalEvaluation.entrySet().forEach(entry -> System.out.printf("%s -> %.2f\n", entry.getKey(), entry.getValue()));
    }
}
