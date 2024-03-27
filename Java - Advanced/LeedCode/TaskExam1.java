public class TaskExam1 {

    public static void main(String[] args) {
        TaskExam1 taskExam1 = new TaskExam1();
        taskExam1.solution(12);
    }

    public int[] solution(int N) {
        for (int i = 1; i < N; i++) {
            int B = N - i;
            if (notZero(i) && notZero(B)) {
                return new int[]{i, B};
            }
        }
        return null;
    }
    private boolean notZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}
