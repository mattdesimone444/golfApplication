package com.furious.golf.service.util;

public class GolfPlayerProjections {
    String playerId;
    boolean locked;
    int max;
    String siteId;
    String playerName;
    double proj;
    String pos;
    double ownership;
    int salary;
    double actual;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getProj() {
        return proj;
    }

    public void setProj(double proj) {
        this.proj = proj;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public double getOwnership() {
        return ownership;
    }

    public void setOwnership(double ownership) {
        this.ownership = ownership;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    @Override
    public String toString() {
        return "GolfPlayerProjections{" +
            "playerId='" + playerId + '\'' +
            ", locked=" + locked +
            ", max=" + max +
            ", siteId='" + siteId + '\'' +
            ", playerName='" + playerName + '\'' +
            ", proj=" + proj +
            ", pos='" + pos + '\'' +
            ", ownership=" + ownership +
            ", salary=" + salary +
            ", actual=" + actual +
            '}';
    }
}
