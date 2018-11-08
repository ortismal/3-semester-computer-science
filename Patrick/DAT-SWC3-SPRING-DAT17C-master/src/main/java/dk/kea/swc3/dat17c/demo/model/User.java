package dk.kea.swc3.dat17c.demo.model;

public class User {
    private String name;
    private Integer age;
    private Character gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
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

    public User(String name, Integer age, Character gender) {
        name = name.toLowerCase();
        this.name = name.substring(0,1).toUpperCase() + name.substring(1);
        this.age = age;
        this.gender = gender;
    }
}
