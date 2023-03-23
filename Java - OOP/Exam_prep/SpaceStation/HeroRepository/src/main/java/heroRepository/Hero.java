package heroRepository;

public class Hero {
    private String name;
    private int level;

    public Hero(String name, int level) {
        this.setName(name);
        this.setLevel(level);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    private void setLevel(int level) {
        this.level = level;
    }
}
