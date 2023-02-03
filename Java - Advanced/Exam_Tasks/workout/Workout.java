package ExamPreparetion_01.workout;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    String type;
    private int exerciseCount;
    private List<Exercise> exercises;


    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        if (this.exercises.size() < this.exerciseCount) {
            this.exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle) {
        for (Exercise exercise : this.exercises) {
            if (exercise.getName().equals(name) && exercise.getMuscle().equals(muscle)) {
                return this.exercises.remove(exercise);
            }
        }
        return false;
    }

    public Exercise getExercise(String name, String muscle) {
        for (Exercise exercise : this.exercises) {
            if (exercise.getName().equals(name) && exercise.getMuscle().equals(muscle)) {
                return exercise;
            }
        }
        return null;
    }

    public Exercise getMostBurnedCaloriesExercise() {
        Exercise exercise = null;
        int mostBurnedCalories = Integer.MIN_VALUE;
        for (Exercise currentExercise : this.exercises) {
            if (currentExercise.getBurnedCalories() > mostBurnedCalories) {
                mostBurnedCalories = currentExercise.getBurnedCalories();
                exercise = currentExercise;
            }
        }
        return exercise;
    }

    public int getExerciseCount() {
        return this.exercises.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Workout type: %s", this.type));
        builder.append(System.lineSeparator());

        for (Exercise exercise : this.exercises) {
            builder.append(exercise);
            builder.append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
