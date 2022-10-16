package Exercises_02.PizzaCalories_04;

public class Dough {

    private String flourType;
    private String backingTechnique;
    private double weight;

    public Dough(String flourType, String backingTechnique, double weight) {
        setFlourType(flourType);
        setBackingTechnique(backingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBackingTechnique(String backingTechnique) {
        switch (backingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.backingTechnique = backingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }

    }

    public double calculateCalories() {
        double flourTypeCoefficient = 0;
        if (this.flourType.equals("White")){
            flourTypeCoefficient = 1.5;
        }else if (this.flourType.equals("Wholegrain")){
            flourTypeCoefficient = 1;
        }
        double backingTechniqueCoefficient = 0;
        if (this.backingTechnique.equals("Crispy")){
            backingTechniqueCoefficient = 0.9;
        }else if (this.backingTechnique.equals("Chewy")){
            backingTechniqueCoefficient = 1.1;
        }else if (this.backingTechnique.equals("Homemade")){
            backingTechniqueCoefficient = 1;
        }
        return (2 * this.weight) * flourTypeCoefficient * backingTechniqueCoefficient;
    }
}
