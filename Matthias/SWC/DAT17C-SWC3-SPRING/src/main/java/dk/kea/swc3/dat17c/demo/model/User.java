package dk.kea.swc3.dat17c.demo.model;

public class User {
    private String name;
    private Integer age;
    private Character gender;

    public User(String name, int age, Character gender) {
        this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        this.age = age;
        this.gender = gender;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }
}
