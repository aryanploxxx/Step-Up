package com.example.barebears;

public class UserhelperClass  {

    String username;
    int target,step_count,calories,points;

    public UserhelperClass() {
    }

    public UserhelperClass(String username, int target, int step_count, int calories, int points) {
        this.username = username;
        this.target = target;
        this.step_count = step_count;
        this.calories = calories;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getstep_count() {
        return step_count;
    }

    public void setstep_count(int step_count) {
        this.step_count = step_count;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
