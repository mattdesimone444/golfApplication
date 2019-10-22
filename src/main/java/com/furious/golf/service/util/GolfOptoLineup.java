package com.furious.golf.service.util;

public class GolfOptoLineup {
    private GolfOptoPlayer g1;
    private GolfOptoPlayer g2;
    private GolfOptoPlayer g3;
    private GolfOptoPlayer g4;
    private GolfOptoPlayer g5;
    private GolfOptoPlayer g6;
    int salary;
    double projPoints;
    double ownership;
    double actual;
    int hash;

    public GolfOptoPlayer getG1() {
        return g1;
    }

    public void setG1(GolfOptoPlayer g1) {
        this.g1 = g1;
    }

    public GolfOptoPlayer getG2() {
        return g2;
    }

    public void setG2(GolfOptoPlayer g2) {
        this.g2 = g2;
    }

    public GolfOptoPlayer getG3() {
        return g3;
    }

    public void setG3(GolfOptoPlayer g3) {
        this.g3 = g3;
    }

    public GolfOptoPlayer getG4() {
        return g4;
    }

    public void setG4(GolfOptoPlayer g4) {
        this.g4 = g4;
    }

    public GolfOptoPlayer getG5() {
        return g5;
    }

    public void setG5(GolfOptoPlayer g5) {
        this.g5 = g5;
    }

    public GolfOptoPlayer getG6() {
        return g6;
    }

    public void setG6(GolfOptoPlayer g6) {
        this.g6 = g6;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getProjPoints() {
        return projPoints;
    }

    public void setProjPoints(double projPoints) {
        this.projPoints = projPoints;
    }

    public double getOwnership() {
        return ownership;
    }

    public void setOwnership(double ownership) {
        this.ownership = ownership;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }
}
