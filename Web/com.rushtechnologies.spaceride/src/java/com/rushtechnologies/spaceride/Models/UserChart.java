package com.rushtechnologies.spaceride.Models;

/**
 *
 * @author CARLOSHG
 */
public class UserChart {
    private int count;

    public UserChart() {
    }

    public UserChart(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserChart{" + "count=" + count + '}';
    }
}
