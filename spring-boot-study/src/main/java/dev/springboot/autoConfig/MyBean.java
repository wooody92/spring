package dev.springboot.autoConfig;

public class MyBean {

    String name;

    int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "MyBean{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
