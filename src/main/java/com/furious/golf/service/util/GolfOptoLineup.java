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

    public GolfOptoLineup() {
    }

    public GolfOptoLineup(GolfOptoLineup mainLineup) {
        this.g1 = mainLineup.getG1();
        this.g2 = mainLineup.getG2();
        this.g3 = mainLineup.getG3();
        this.g4 = mainLineup.getG4();
        this.g5 = mainLineup.getG5();
        this.g6 = mainLineup.getG6();
    }

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

    public int getHash() {
        //System.out.println(this.qb.getProj() + " | " + this.rb1.getProj() + " | " + this.rb2.getProj());
        return this.g1.getHash()
            + this.g2.getHash()
            + this.g3.getHash()
            + this.g4.getHash()
            + this.g5.getHash()
            + this.g6.getHash();

    }

    public double getOwnership() {
        return this.g1.getOwnership()
            + this.g2.getOwnership()
            + this.g3.getOwnership()
            + this.g4.getOwnership()
            + this.g5.getOwnership()
            + this.g6.getOwnership();
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


    public void setOwnership(double ownership) {
        this.ownership = ownership;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }



    public void setHash(int hash) {
        this.hash = hash;
    }
    public int getSalary() {
        int salary = 0;
        if (this.g1 != null) {
            salary = salary + g1.getSalary();
        }
        if (this.g2 != null) {
            salary = salary + g2.getSalary();
        }
        if (this.g3 != null) {
            salary = salary + g3.getSalary();
        }
        if (this.g4 != null) {
            salary = salary + g4.getSalary();
        }
        if (this.g5 != null) {
            salary = salary + g5.getSalary();
        }
        if (this.g6 != null) {
            salary = salary + g6.getSalary();
        }

        return salary;
    }

    @Override
    public String toString() {
        return "GolfOptoLineup{" +
            "g1=" + g1 +
            ", g2=" + g2 +
            ", g3=" + g3 +
            ", g4=" + g4 +
            ", g5=" + g5 +
            ", g6=" + g6 +
            ", salary=" + salary +
            ", projPoints=" + projPoints +
            ", ownership=" + ownership +
            ", actual=" + actual +
            ", hash=" + hash +
            '}';
    }
}
