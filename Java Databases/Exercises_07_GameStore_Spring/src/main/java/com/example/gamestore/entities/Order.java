package com.example.gamestore.entities;

import com.example.gamestore.entities.users.User;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User bayer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    public Order() {

    }

    public Order(User bayer, Set<Game> products) {
        this.bayer = bayer;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBayer() {
        return bayer;
    }

    public void setBayer(User bayer) {
        this.bayer = bayer;
    }

    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
}
