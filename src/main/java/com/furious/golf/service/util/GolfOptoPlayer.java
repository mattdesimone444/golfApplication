package com.furious.golf.service.util;

import java.util.Random;

public class GolfOptoPlayer {
    String siteId;
    String playerName;
    int salary;
    double proj;
    double ownership;
    double minExposure;
    double maxExposure;
    double value;
    int hash;
    double actual;
    String playerId;

    public GolfOptoPlayer() {
        Random r = new Random();
        this.hash =   r.nextInt((1999999 - 1000000) + 1) + 1000000;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getProj() {
        return proj;
    }

    public void setProj(double proj) {
        this.proj = proj;
    }

    public double getOwnership() {
        return ownership;
    }

    public void setOwnership(double ownership) {
        this.ownership = ownership;
    }

    public double getMinExposure() {
        return minExposure;
    }

    public void setMinExposure(double minExposure) {
        this.minExposure = minExposure;
    }

    public double getMaxExposure() {
        return maxExposure;
    }

    public void setMaxExposure(double maxExposure) {
        this.maxExposure = maxExposure;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "GolfOptoPlayer{" +
            "siteId='" + siteId + '\'' +
            ", playerName='" + playerName + '\'' +
            ", salary=" + salary +
            ", proj=" + proj +
            ", ownership=" + ownership +
            ", minExposure=" + minExposure +
            ", maxExposure=" + maxExposure +
            ", value=" + value +
            ", hash=" + hash +
            ", actual=" + actual +
            ", playerId='" + playerId + '\'' +
            '}';
    }
}
