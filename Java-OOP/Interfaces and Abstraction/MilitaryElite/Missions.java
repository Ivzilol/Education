package Exercises_04.MilitaryElite;

public interface Missions {
    String getCodeName();
    MissionState getState();
    void completeMission();
}
