package org.example;

public class Lesson {

    private int id;
    private String name;
    private Homework hw;

    public Lesson(String name, Homework hw) {
        this.name = name;
        this.hw = hw;
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

    public Homework getHomework() {
        return hw;
    }

    public void setHomework(Homework hw) {
        this.hw = hw;
    }
}
