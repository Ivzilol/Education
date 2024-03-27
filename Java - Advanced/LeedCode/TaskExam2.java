public class TaskExam2 {

    public static void main(String[] args) {
        TaskExam2 taskExam2 = new TaskExam2();
        taskExam2.solution(new int[]{3, 2, 1, 1, 2, 3, 1});
        taskExam2.solution(new int[]{4, 1, 4, 1});
    }

    public int solution(int[] A) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : A) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int minSteps = Integer.MAX_VALUE;
        for (int target = min; target <= max; target++) {
            int steps = 0;
            for (int num : A) {
                steps += Math.abs(target - num);
            }
            minSteps = Math.min(minSteps, steps);
        }
        System.out.println(minSteps);
        return minSteps;
    }
}
