package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(name = "started_at")
    private LocalDate startedAd;

    public Teacher(String name, LocalDate startedAd) {
        this.name = name;
        this.startedAd = startedAd;
    }

    public Teacher() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartedAd() {
        return startedAd;
    }

    public void setStartedAd(LocalDate startedAd) {
        this.startedAd = startedAd;
    }
}
