package Exercises_08.ComparingObjects_05;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public int compareTo(Person person2) {
        int result = this.name.compareTo(person2.getName());
        if (result == 0) {
            result = Integer.compare(this.age, person2.getAge());
        }
        if (result == 0) {
            result = this.town.compareTo(person2.getTown());
        }
        return result;
    }
}
