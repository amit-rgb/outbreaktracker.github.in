package com.tracker.outbreaktracker.models;

public class CountryStats {
    private  String state;
private int diffPreviousDay;

    public int getDiffPreviousDay() {
        return diffPreviousDay;
    }

    public void setDiffPreviousDay(int diffPreviousDay) {
        this.diffPreviousDay = diffPreviousDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CountryStats{" +
                "state='" + state + '\'' +
                ", lastCount=" + lastCount +
                ", country='" + country + '\'' +
                '}';
    }

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private int lastCount;
    private  String country;
}
