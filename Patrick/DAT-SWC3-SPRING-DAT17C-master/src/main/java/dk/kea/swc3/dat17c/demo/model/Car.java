package dk.kea.swc3.dat17c.demo.model;

public class Car {
    private String brand;
    private String color;
    private Integer doors;

    public Car(String brand, String color, Integer doors){
        this.brand = brand;
        this.color = color;
        this.doors = doors;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }
}
