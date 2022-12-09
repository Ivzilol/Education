package softuni.exam.models.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;

    @ManyToOne(optional = false)
    private Agent agents;

    @ManyToOne(optional = false)
    private Apartment apartments;

    public Offer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Agent getAgents() {
        return agents;
    }

    public void setAgents(Agent agents) {
        this.agents = agents;
    }

    public Apartment getApartments() {
        return apartments;
    }

    public void setApartments(Apartment apartments) {
        this.apartments = apartments;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public Offer setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
        return this;
    }


}
