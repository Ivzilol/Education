package Exercises_01.TrafficLights_04;

public class Light {

    private Color color;

    public Light(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void changeColor() {
        if (this.color == Color.RED){
            this.color = Color.GREEN;
        }else if (this.color == Color.GREEN){
            this.color = Color.YELLOW;
        }else if (this.color == Color.YELLOW){
            this.color = Color.RED;
        }
    }
}
