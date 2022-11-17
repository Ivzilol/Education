package Exercises_06.Google_07;

public class Child {
    private String childName;
    private String childBirthday;

    public Child(String name, String childBirthday) {
        this.childName = name;
        this.childBirthday = childBirthday;
    }
    @Override
    public String toString(){
        return childName + " " + childBirthday;
    }
}
