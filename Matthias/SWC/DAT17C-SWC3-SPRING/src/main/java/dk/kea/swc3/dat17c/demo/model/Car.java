package dk.kea.swc3.dat17c.demo.model;

public class Car {
    private String name;
    private Integer speed;
    private String color;

    public Car(String name, Integer speed, String color) {
        this.name = name;
        this.speed = speed;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
