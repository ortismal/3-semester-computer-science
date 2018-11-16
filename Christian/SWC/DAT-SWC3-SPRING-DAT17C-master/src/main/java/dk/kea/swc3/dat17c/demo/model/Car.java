package dk.kea.swc3.dat17c.demo.model;

import javax.persistence.*;

@Entity

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String color;
    private Integer doors;
    private Integer speed;

    @OneToOne
    private User user;

    public Car(){

    }

    public Car(String brand, String color, Integer doors, Integer speed, User user) {
        this.brand = brand;
        this.color = color;
        this.doors = doors;
        this.speed = speed;
        this.user = user;
    }

    public Car(String brand, String color, Integer doors, Integer speed) {
        this.brand = brand;
        this.color = color;
        this.doors = doors;
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
