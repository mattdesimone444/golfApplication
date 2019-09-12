package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A PuttingAnalysis.
 */
@Entity
@Table(name = "putting_analysis")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "puttinganalysis")
public class PuttingAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "puttin_analysis_id")
    private Long puttinAnalysisId;

    @Column(name = "longest")
    private String longest;

    @Column(name = "total")
    private String total;

    @Column(name = "less_three")
    private String lessThree;

    @Column(name = "less_ten")
    private String lessTen;

    @Column(name = "three_to_five")
    private String threeToFive;

    @Column(name = "five_to_seven")
    private String fiveToSeven;

    @Column(name = "seven_to_ten")
    private String sevenToTen;

    @Column(name = "four_to_eight")
    private String fourToEight;

    @Column(name = "ten_to_fifteen")
    private String tenToFifteen;

    @Column(name = "fifteen_to_twenty")
    private String fifteenToTwenty;

    @Column(name = "twenty_to_twenty_five")
    private String twentyToTwentyFive;

    @Column(name = "less_twenty_five")
    private String lessTwentyFive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPuttinAnalysisId() {
        return puttinAnalysisId;
    }

    public PuttingAnalysis puttinAnalysisId(Long puttinAnalysisId) {
        this.puttinAnalysisId = puttinAnalysisId;
        return this;
    }

    public void setPuttinAnalysisId(Long puttinAnalysisId) {
        this.puttinAnalysisId = puttinAnalysisId;
    }

    public String getLongest() {
        return longest;
    }

    public PuttingAnalysis longest(String longest) {
        this.longest = longest;
        return this;
    }

    public void setLongest(String longest) {
        this.longest = longest;
    }

    public String getTotal() {
        return total;
    }

    public PuttingAnalysis total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLessThree() {
        return lessThree;
    }

    public PuttingAnalysis lessThree(String lessThree) {
        this.lessThree = lessThree;
        return this;
    }

    public void setLessThree(String lessThree) {
        this.lessThree = lessThree;
    }

    public String getLessTen() {
        return lessTen;
    }

    public PuttingAnalysis lessTen(String lessTen) {
        this.lessTen = lessTen;
        return this;
    }

    public void setLessTen(String lessTen) {
        this.lessTen = lessTen;
    }

    public String getThreeToFive() {
        return threeToFive;
    }

    public PuttingAnalysis threeToFive(String threeToFive) {
        this.threeToFive = threeToFive;
        return this;
    }

    public void setThreeToFive(String threeToFive) {
        this.threeToFive = threeToFive;
    }

    public String getFiveToSeven() {
        return fiveToSeven;
    }

    public PuttingAnalysis fiveToSeven(String fiveToSeven) {
        this.fiveToSeven = fiveToSeven;
        return this;
    }

    public void setFiveToSeven(String fiveToSeven) {
        this.fiveToSeven = fiveToSeven;
    }

    public String getSevenToTen() {
        return sevenToTen;
    }

    public PuttingAnalysis sevenToTen(String sevenToTen) {
        this.sevenToTen = sevenToTen;
        return this;
    }

    public void setSevenToTen(String sevenToTen) {
        this.sevenToTen = sevenToTen;
    }

    public String getFourToEight() {
        return fourToEight;
    }

    public PuttingAnalysis fourToEight(String fourToEight) {
        this.fourToEight = fourToEight;
        return this;
    }

    public void setFourToEight(String fourToEight) {
        this.fourToEight = fourToEight;
    }

    public String getTenToFifteen() {
        return tenToFifteen;
    }

    public PuttingAnalysis tenToFifteen(String tenToFifteen) {
        this.tenToFifteen = tenToFifteen;
        return this;
    }

    public void setTenToFifteen(String tenToFifteen) {
        this.tenToFifteen = tenToFifteen;
    }

    public String getFifteenToTwenty() {
        return fifteenToTwenty;
    }

    public PuttingAnalysis fifteenToTwenty(String fifteenToTwenty) {
        this.fifteenToTwenty = fifteenToTwenty;
        return this;
    }

    public void setFifteenToTwenty(String fifteenToTwenty) {
        this.fifteenToTwenty = fifteenToTwenty;
    }

    public String getTwentyToTwentyFive() {
        return twentyToTwentyFive;
    }

    public PuttingAnalysis twentyToTwentyFive(String twentyToTwentyFive) {
        this.twentyToTwentyFive = twentyToTwentyFive;
        return this;
    }

    public void setTwentyToTwentyFive(String twentyToTwentyFive) {
        this.twentyToTwentyFive = twentyToTwentyFive;
    }

    public String getLessTwentyFive() {
        return lessTwentyFive;
    }

    public PuttingAnalysis lessTwentyFive(String lessTwentyFive) {
        this.lessTwentyFive = lessTwentyFive;
        return this;
    }

    public void setLessTwentyFive(String lessTwentyFive) {
        this.lessTwentyFive = lessTwentyFive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PuttingAnalysis)) {
            return false;
        }
        return id != null && id.equals(((PuttingAnalysis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PuttingAnalysis{" +
            "id=" + getId() +
            ", puttinAnalysisId=" + getPuttinAnalysisId() +
            ", longest='" + getLongest() + "'" +
            ", total='" + getTotal() + "'" +
            ", lessThree='" + getLessThree() + "'" +
            ", lessTen='" + getLessTen() + "'" +
            ", threeToFive='" + getThreeToFive() + "'" +
            ", fiveToSeven='" + getFiveToSeven() + "'" +
            ", sevenToTen='" + getSevenToTen() + "'" +
            ", fourToEight='" + getFourToEight() + "'" +
            ", tenToFifteen='" + getTenToFifteen() + "'" +
            ", fifteenToTwenty='" + getFifteenToTwenty() + "'" +
            ", twentyToTwentyFive='" + getTwentyToTwentyFive() + "'" +
            ", lessTwentyFive='" + getLessTwentyFive() + "'" +
            "}";
    }
}
