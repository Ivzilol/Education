package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static robotService.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseService implements Service{

    private String name;

    private int capacity;

    private Collection<Supplement> supplements;

    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.robots.size() < this.capacity) {
            this.robots.add(robot);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : this.robots) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {
        int sumHardness = 0;
        for (Supplement supplement : this.supplements) {
            sumHardness += supplement.getHardness();
        }
        return sumHardness;
    }

    @Override
    public String getStatistics() {
        StringBuilder forPrint= new StringBuilder();
        forPrint.append(String.format("%s %s:", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        String report = robots.isEmpty()
                ? "none"
                : robots.stream()
                .map(Robot::getName)
                .collect(Collectors.joining(" "));
        forPrint.append(String.format("Robots: %s", report))
                .append(System.lineSeparator());

        int supplimentsCount= (int) supplements.stream().count();
        forPrint
                .append(String.format("Supplements: %d Hardness: %d",
                        supplimentsCount, this.sumHardness()))
                .append(System.lineSeparator());
        return forPrint.toString().trim();
    }
}
