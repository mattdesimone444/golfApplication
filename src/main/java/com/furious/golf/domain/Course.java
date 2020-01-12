package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "par")
    private Integer par;

    @Column(name = "yardage")
    private Integer yardage;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "green_type")
    private String greenType;

    @Column(name = "hole_1_par")
    private Integer hole1Par;

    @Column(name = "hole_1_yardage")
    private Integer hole1Yardage;

    @Column(name = "hole_1_handicap")
    private Integer hole1Handicap;

    @Column(name = "hole_2_par")
    private Integer hole2Par;

    @Column(name = "hole_2_yardage")
    private Integer hole2Yardage;

    @Column(name = "hole_2_handicap")
    private Integer hole2Handicap;

    @Column(name = "hole_3_par")
    private Integer hole3Par;

    @Column(name = "hole_3_yardage")
    private Integer hole3Yardage;

    @Column(name = "hole_3_handicap")
    private Integer hole3Handicap;

    @Column(name = "hole_4_par")
    private Integer hole4Par;

    @Column(name = "hole_4_yardage")
    private Integer hole4Yardage;

    @Column(name = "hole_4_handicap")
    private Integer hole4Handicap;

    @Column(name = "hole_5_par")
    private Integer hole5Par;

    @Column(name = "hole_5_yardage")
    private Integer hole5Yardage;

    @Column(name = "hole_5_handicap")
    private Integer hole5Handicap;

    @Column(name = "hole_6_par")
    private Integer hole6Par;

    @Column(name = "hole_6_yardage")
    private Integer hole6Yardage;

    @Column(name = "hole_6_handicap")
    private Integer hole6Handicap;

    @Column(name = "hole_7_par")
    private Integer hole7Par;

    @Column(name = "hole_7_yardage")
    private Integer hole7Yardage;

    @Column(name = "hole_7_handicap")
    private Integer hole7Handicap;

    @Column(name = "hole_8_par")
    private Integer hole8Par;

    @Column(name = "hole_8_yardage")
    private Integer hole8Yardage;

    @Column(name = "hole_8_handicap")
    private Integer hole8Handicap;

    @Column(name = "hole_9_par")
    private Integer hole9Par;

    @Column(name = "hole_9_yardage")
    private Integer hole9Yardage;

    @Column(name = "hole_9_handicap")
    private Integer hole9Handicap;

    @Column(name = "hole_10_par")
    private Integer hole10Par;

    @Column(name = "hole_10_yardage")
    private Integer hole10Yardage;

    @Column(name = "hole_10_handicap")
    private Integer hole10Handicap;

    @Column(name = "hole_11_par")
    private Integer hole11Par;

    @Column(name = "hole_11_yardage")
    private Integer hole11Yardage;

    @Column(name = "hole_11_handicap")
    private Integer hole11Handicap;

    @Column(name = "hole_12_par")
    private Integer hole12Par;

    @Column(name = "hole_12_yardage")
    private Integer hole12Yardage;

    @Column(name = "hole_12_handicap")
    private Integer hole12Handicap;

    @Column(name = "hole_13_par")
    private Integer hole13Par;

    @Column(name = "hole_13_yardage")
    private Integer hole13Yardage;

    @Column(name = "hole_13_handicap")
    private Integer hole13Handicap;

    @Column(name = "hole_14_par")
    private Integer hole14Par;

    @Column(name = "hole_14_yardage")
    private Integer hole14Yardage;

    @Column(name = "hole_14_handicap")
    private Integer hole14Handicap;

    @Column(name = "hole_15_par")
    private Integer hole15Par;

    @Column(name = "hole_15_yardage")
    private Integer hole15Yardage;

    @Column(name = "hole_15_handicap")
    private Integer hole15Handicap;

    @Column(name = "hole_16_par")
    private Integer hole16Par;

    @Column(name = "hole_16_yardage")
    private Integer hole16Yardage;

    @Column(name = "hole_16_handicap")
    private Integer hole16Handicap;

    @Column(name = "hole_17_par")
    private Integer hole17Par;

    @Column(name = "hole_17_yardage")
    private Integer hole17Yardage;

    @Column(name = "hole_17_handicap")
    private Integer hole17Handicap;

    @Column(name = "hole_18_par")
    private Integer hole18Par;

    @Column(name = "hole_18_yardage")
    private Integer hole18Yardage;

    @Column(name = "hole_18_handicap")
    private Integer hole18Handicap;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPar() {
        return par;
    }

    public Course par(Integer par) {
        this.par = par;
        return this;
    }

    public void setPar(Integer par) {
        this.par = par;
    }

    public Integer getYardage() {
        return yardage;
    }

    public Course yardage(Integer yardage) {
        this.yardage = yardage;
        return this;
    }

    public void setYardage(Integer yardage) {
        this.yardage = yardage;
    }

    public String getCity() {
        return city;
    }

    public Course city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public Course state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCourseType() {
        return courseType;
    }

    public Course courseType(String courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getGreenType() {
        return greenType;
    }

    public Course greenType(String greenType) {
        this.greenType = greenType;
        return this;
    }

    public void setGreenType(String greenType) {
        this.greenType = greenType;
    }

    public Integer getHole1Par() {
        return hole1Par;
    }

    public Course hole1Par(Integer hole1Par) {
        this.hole1Par = hole1Par;
        return this;
    }

    public void setHole1Par(Integer hole1Par) {
        this.hole1Par = hole1Par;
    }

    public Integer getHole1Yardage() {
        return hole1Yardage;
    }

    public Course hole1Yardage(Integer hole1Yardage) {
        this.hole1Yardage = hole1Yardage;
        return this;
    }

    public void setHole1Yardage(Integer hole1Yardage) {
        this.hole1Yardage = hole1Yardage;
    }

    public Integer getHole1Handicap() {
        return hole1Handicap;
    }

    public Course hole1Handicap(Integer hole1Handicap) {
        this.hole1Handicap = hole1Handicap;
        return this;
    }

    public void setHole1Handicap(Integer hole1Handicap) {
        this.hole1Handicap = hole1Handicap;
    }

    public Integer getHole2Par() {
        return hole2Par;
    }

    public Course hole2Par(Integer hole2Par) {
        this.hole2Par = hole2Par;
        return this;
    }

    public void setHole2Par(Integer hole2Par) {
        this.hole2Par = hole2Par;
    }

    public Integer getHole2Yardage() {
        return hole2Yardage;
    }

    public Course hole2Yardage(Integer hole2Yardage) {
        this.hole2Yardage = hole2Yardage;
        return this;
    }

    public void setHole2Yardage(Integer hole2Yardage) {
        this.hole2Yardage = hole2Yardage;
    }

    public Integer getHole2Handicap() {
        return hole2Handicap;
    }

    public Course hole2Handicap(Integer hole2Handicap) {
        this.hole2Handicap = hole2Handicap;
        return this;
    }

    public void setHole2Handicap(Integer hole2Handicap) {
        this.hole2Handicap = hole2Handicap;
    }

    public Integer getHole3Par() {
        return hole3Par;
    }

    public Course hole3Par(Integer hole3Par) {
        this.hole3Par = hole3Par;
        return this;
    }

    public void setHole3Par(Integer hole3Par) {
        this.hole3Par = hole3Par;
    }

    public Integer getHole3Yardage() {
        return hole3Yardage;
    }

    public Course hole3Yardage(Integer hole3Yardage) {
        this.hole3Yardage = hole3Yardage;
        return this;
    }

    public void setHole3Yardage(Integer hole3Yardage) {
        this.hole3Yardage = hole3Yardage;
    }

    public Integer getHole3Handicap() {
        return hole3Handicap;
    }

    public Course hole3Handicap(Integer hole3Handicap) {
        this.hole3Handicap = hole3Handicap;
        return this;
    }

    public void setHole3Handicap(Integer hole3Handicap) {
        this.hole3Handicap = hole3Handicap;
    }

    public Integer getHole4Par() {
        return hole4Par;
    }

    public Course hole4Par(Integer hole4Par) {
        this.hole4Par = hole4Par;
        return this;
    }

    public void setHole4Par(Integer hole4Par) {
        this.hole4Par = hole4Par;
    }

    public Integer getHole4Yardage() {
        return hole4Yardage;
    }

    public Course hole4Yardage(Integer hole4Yardage) {
        this.hole4Yardage = hole4Yardage;
        return this;
    }

    public void setHole4Yardage(Integer hole4Yardage) {
        this.hole4Yardage = hole4Yardage;
    }

    public Integer getHole4Handicap() {
        return hole4Handicap;
    }

    public Course hole4Handicap(Integer hole4Handicap) {
        this.hole4Handicap = hole4Handicap;
        return this;
    }

    public void setHole4Handicap(Integer hole4Handicap) {
        this.hole4Handicap = hole4Handicap;
    }

    public Integer getHole5Par() {
        return hole5Par;
    }

    public Course hole5Par(Integer hole5Par) {
        this.hole5Par = hole5Par;
        return this;
    }

    public void setHole5Par(Integer hole5Par) {
        this.hole5Par = hole5Par;
    }

    public Integer getHole5Yardage() {
        return hole5Yardage;
    }

    public Course hole5Yardage(Integer hole5Yardage) {
        this.hole5Yardage = hole5Yardage;
        return this;
    }

    public void setHole5Yardage(Integer hole5Yardage) {
        this.hole5Yardage = hole5Yardage;
    }

    public Integer getHole5Handicap() {
        return hole5Handicap;
    }

    public Course hole5Handicap(Integer hole5Handicap) {
        this.hole5Handicap = hole5Handicap;
        return this;
    }

    public void setHole5Handicap(Integer hole5Handicap) {
        this.hole5Handicap = hole5Handicap;
    }

    public Integer getHole6Par() {
        return hole6Par;
    }

    public Course hole6Par(Integer hole6Par) {
        this.hole6Par = hole6Par;
        return this;
    }

    public void setHole6Par(Integer hole6Par) {
        this.hole6Par = hole6Par;
    }

    public Integer getHole6Yardage() {
        return hole6Yardage;
    }

    public Course hole6Yardage(Integer hole6Yardage) {
        this.hole6Yardage = hole6Yardage;
        return this;
    }

    public void setHole6Yardage(Integer hole6Yardage) {
        this.hole6Yardage = hole6Yardage;
    }

    public Integer getHole6Handicap() {
        return hole6Handicap;
    }

    public Course hole6Handicap(Integer hole6Handicap) {
        this.hole6Handicap = hole6Handicap;
        return this;
    }

    public void setHole6Handicap(Integer hole6Handicap) {
        this.hole6Handicap = hole6Handicap;
    }

    public Integer getHole7Par() {
        return hole7Par;
    }

    public Course hole7Par(Integer hole7Par) {
        this.hole7Par = hole7Par;
        return this;
    }

    public void setHole7Par(Integer hole7Par) {
        this.hole7Par = hole7Par;
    }

    public Integer getHole7Yardage() {
        return hole7Yardage;
    }

    public Course hole7Yardage(Integer hole7Yardage) {
        this.hole7Yardage = hole7Yardage;
        return this;
    }

    public void setHole7Yardage(Integer hole7Yardage) {
        this.hole7Yardage = hole7Yardage;
    }

    public Integer getHole7Handicap() {
        return hole7Handicap;
    }

    public Course hole7Handicap(Integer hole7Handicap) {
        this.hole7Handicap = hole7Handicap;
        return this;
    }

    public void setHole7Handicap(Integer hole7Handicap) {
        this.hole7Handicap = hole7Handicap;
    }

    public Integer getHole8Par() {
        return hole8Par;
    }

    public Course hole8Par(Integer hole8Par) {
        this.hole8Par = hole8Par;
        return this;
    }

    public void setHole8Par(Integer hole8Par) {
        this.hole8Par = hole8Par;
    }

    public Integer getHole8Yardage() {
        return hole8Yardage;
    }

    public Course hole8Yardage(Integer hole8Yardage) {
        this.hole8Yardage = hole8Yardage;
        return this;
    }

    public void setHole8Yardage(Integer hole8Yardage) {
        this.hole8Yardage = hole8Yardage;
    }

    public Integer getHole8Handicap() {
        return hole8Handicap;
    }

    public Course hole8Handicap(Integer hole8Handicap) {
        this.hole8Handicap = hole8Handicap;
        return this;
    }

    public void setHole8Handicap(Integer hole8Handicap) {
        this.hole8Handicap = hole8Handicap;
    }

    public Integer getHole9Par() {
        return hole9Par;
    }

    public Course hole9Par(Integer hole9Par) {
        this.hole9Par = hole9Par;
        return this;
    }

    public void setHole9Par(Integer hole9Par) {
        this.hole9Par = hole9Par;
    }

    public Integer getHole9Yardage() {
        return hole9Yardage;
    }

    public Course hole9Yardage(Integer hole9Yardage) {
        this.hole9Yardage = hole9Yardage;
        return this;
    }

    public void setHole9Yardage(Integer hole9Yardage) {
        this.hole9Yardage = hole9Yardage;
    }

    public Integer getHole9Handicap() {
        return hole9Handicap;
    }

    public Course hole9Handicap(Integer hole9Handicap) {
        this.hole9Handicap = hole9Handicap;
        return this;
    }

    public void setHole9Handicap(Integer hole9Handicap) {
        this.hole9Handicap = hole9Handicap;
    }

    public Integer getHole10Par() {
        return hole10Par;
    }

    public Course hole10Par(Integer hole10Par) {
        this.hole10Par = hole10Par;
        return this;
    }

    public void setHole10Par(Integer hole10Par) {
        this.hole10Par = hole10Par;
    }

    public Integer getHole10Yardage() {
        return hole10Yardage;
    }

    public Course hole10Yardage(Integer hole10Yardage) {
        this.hole10Yardage = hole10Yardage;
        return this;
    }

    public void setHole10Yardage(Integer hole10Yardage) {
        this.hole10Yardage = hole10Yardage;
    }

    public Integer getHole10Handicap() {
        return hole10Handicap;
    }

    public Course hole10Handicap(Integer hole10Handicap) {
        this.hole10Handicap = hole10Handicap;
        return this;
    }

    public void setHole10Handicap(Integer hole10Handicap) {
        this.hole10Handicap = hole10Handicap;
    }

    public Integer getHole11Par() {
        return hole11Par;
    }

    public Course hole11Par(Integer hole11Par) {
        this.hole11Par = hole11Par;
        return this;
    }

    public void setHole11Par(Integer hole11Par) {
        this.hole11Par = hole11Par;
    }

    public Integer getHole11Yardage() {
        return hole11Yardage;
    }

    public Course hole11Yardage(Integer hole11Yardage) {
        this.hole11Yardage = hole11Yardage;
        return this;
    }

    public void setHole11Yardage(Integer hole11Yardage) {
        this.hole11Yardage = hole11Yardage;
    }

    public Integer getHole11Handicap() {
        return hole11Handicap;
    }

    public Course hole11Handicap(Integer hole11Handicap) {
        this.hole11Handicap = hole11Handicap;
        return this;
    }

    public void setHole11Handicap(Integer hole11Handicap) {
        this.hole11Handicap = hole11Handicap;
    }

    public Integer getHole12Par() {
        return hole12Par;
    }

    public Course hole12Par(Integer hole12Par) {
        this.hole12Par = hole12Par;
        return this;
    }

    public void setHole12Par(Integer hole12Par) {
        this.hole12Par = hole12Par;
    }

    public Integer getHole12Yardage() {
        return hole12Yardage;
    }

    public Course hole12Yardage(Integer hole12Yardage) {
        this.hole12Yardage = hole12Yardage;
        return this;
    }

    public void setHole12Yardage(Integer hole12Yardage) {
        this.hole12Yardage = hole12Yardage;
    }

    public Integer getHole12Handicap() {
        return hole12Handicap;
    }

    public Course hole12Handicap(Integer hole12Handicap) {
        this.hole12Handicap = hole12Handicap;
        return this;
    }

    public void setHole12Handicap(Integer hole12Handicap) {
        this.hole12Handicap = hole12Handicap;
    }

    public Integer getHole13Par() {
        return hole13Par;
    }

    public Course hole13Par(Integer hole13Par) {
        this.hole13Par = hole13Par;
        return this;
    }

    public void setHole13Par(Integer hole13Par) {
        this.hole13Par = hole13Par;
    }

    public Integer getHole13Yardage() {
        return hole13Yardage;
    }

    public Course hole13Yardage(Integer hole13Yardage) {
        this.hole13Yardage = hole13Yardage;
        return this;
    }

    public void setHole13Yardage(Integer hole13Yardage) {
        this.hole13Yardage = hole13Yardage;
    }

    public Integer getHole13Handicap() {
        return hole13Handicap;
    }

    public Course hole13Handicap(Integer hole13Handicap) {
        this.hole13Handicap = hole13Handicap;
        return this;
    }

    public void setHole13Handicap(Integer hole13Handicap) {
        this.hole13Handicap = hole13Handicap;
    }

    public Integer getHole14Par() {
        return hole14Par;
    }

    public Course hole14Par(Integer hole14Par) {
        this.hole14Par = hole14Par;
        return this;
    }

    public void setHole14Par(Integer hole14Par) {
        this.hole14Par = hole14Par;
    }

    public Integer getHole14Yardage() {
        return hole14Yardage;
    }

    public Course hole14Yardage(Integer hole14Yardage) {
        this.hole14Yardage = hole14Yardage;
        return this;
    }

    public void setHole14Yardage(Integer hole14Yardage) {
        this.hole14Yardage = hole14Yardage;
    }

    public Integer getHole14Handicap() {
        return hole14Handicap;
    }

    public Course hole14Handicap(Integer hole14Handicap) {
        this.hole14Handicap = hole14Handicap;
        return this;
    }

    public void setHole14Handicap(Integer hole14Handicap) {
        this.hole14Handicap = hole14Handicap;
    }

    public Integer getHole15Par() {
        return hole15Par;
    }

    public Course hole15Par(Integer hole15Par) {
        this.hole15Par = hole15Par;
        return this;
    }

    public void setHole15Par(Integer hole15Par) {
        this.hole15Par = hole15Par;
    }

    public Integer getHole15Yardage() {
        return hole15Yardage;
    }

    public Course hole15Yardage(Integer hole15Yardage) {
        this.hole15Yardage = hole15Yardage;
        return this;
    }

    public void setHole15Yardage(Integer hole15Yardage) {
        this.hole15Yardage = hole15Yardage;
    }

    public Integer getHole15Handicap() {
        return hole15Handicap;
    }

    public Course hole15Handicap(Integer hole15Handicap) {
        this.hole15Handicap = hole15Handicap;
        return this;
    }

    public void setHole15Handicap(Integer hole15Handicap) {
        this.hole15Handicap = hole15Handicap;
    }

    public Integer getHole16Par() {
        return hole16Par;
    }

    public Course hole16Par(Integer hole16Par) {
        this.hole16Par = hole16Par;
        return this;
    }

    public void setHole16Par(Integer hole16Par) {
        this.hole16Par = hole16Par;
    }

    public Integer getHole16Yardage() {
        return hole16Yardage;
    }

    public Course hole16Yardage(Integer hole16Yardage) {
        this.hole16Yardage = hole16Yardage;
        return this;
    }

    public void setHole16Yardage(Integer hole16Yardage) {
        this.hole16Yardage = hole16Yardage;
    }

    public Integer getHole16Handicap() {
        return hole16Handicap;
    }

    public Course hole16Handicap(Integer hole16Handicap) {
        this.hole16Handicap = hole16Handicap;
        return this;
    }

    public void setHole16Handicap(Integer hole16Handicap) {
        this.hole16Handicap = hole16Handicap;
    }

    public Integer getHole17Par() {
        return hole17Par;
    }

    public Course hole17Par(Integer hole17Par) {
        this.hole17Par = hole17Par;
        return this;
    }

    public void setHole17Par(Integer hole17Par) {
        this.hole17Par = hole17Par;
    }

    public Integer getHole17Yardage() {
        return hole17Yardage;
    }

    public Course hole17Yardage(Integer hole17Yardage) {
        this.hole17Yardage = hole17Yardage;
        return this;
    }

    public void setHole17Yardage(Integer hole17Yardage) {
        this.hole17Yardage = hole17Yardage;
    }

    public Integer getHole17Handicap() {
        return hole17Handicap;
    }

    public Course hole17Handicap(Integer hole17Handicap) {
        this.hole17Handicap = hole17Handicap;
        return this;
    }

    public void setHole17Handicap(Integer hole17Handicap) {
        this.hole17Handicap = hole17Handicap;
    }

    public Integer getHole18Par() {
        return hole18Par;
    }

    public Course hole18Par(Integer hole18Par) {
        this.hole18Par = hole18Par;
        return this;
    }

    public void setHole18Par(Integer hole18Par) {
        this.hole18Par = hole18Par;
    }

    public Integer getHole18Yardage() {
        return hole18Yardage;
    }

    public Course hole18Yardage(Integer hole18Yardage) {
        this.hole18Yardage = hole18Yardage;
        return this;
    }

    public void setHole18Yardage(Integer hole18Yardage) {
        this.hole18Yardage = hole18Yardage;
    }

    public Integer getHole18Handicap() {
        return hole18Handicap;
    }

    public Course hole18Handicap(Integer hole18Handicap) {
        this.hole18Handicap = hole18Handicap;
        return this;
    }

    public void setHole18Handicap(Integer hole18Handicap) {
        this.hole18Handicap = hole18Handicap;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", par=" + getPar() +
            ", yardage=" + getYardage() +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", courseType='" + getCourseType() + "'" +
            ", greenType='" + getGreenType() + "'" +
            ", hole1Par=" + getHole1Par() +
            ", hole1Yardage=" + getHole1Yardage() +
            ", hole1Handicap=" + getHole1Handicap() +
            ", hole2Par=" + getHole2Par() +
            ", hole2Yardage=" + getHole2Yardage() +
            ", hole2Handicap=" + getHole2Handicap() +
            ", hole3Par=" + getHole3Par() +
            ", hole3Yardage=" + getHole3Yardage() +
            ", hole3Handicap=" + getHole3Handicap() +
            ", hole4Par=" + getHole4Par() +
            ", hole4Yardage=" + getHole4Yardage() +
            ", hole4Handicap=" + getHole4Handicap() +
            ", hole5Par=" + getHole5Par() +
            ", hole5Yardage=" + getHole5Yardage() +
            ", hole5Handicap=" + getHole5Handicap() +
            ", hole6Par=" + getHole6Par() +
            ", hole6Yardage=" + getHole6Yardage() +
            ", hole6Handicap=" + getHole6Handicap() +
            ", hole7Par=" + getHole7Par() +
            ", hole7Yardage=" + getHole7Yardage() +
            ", hole7Handicap=" + getHole7Handicap() +
            ", hole8Par=" + getHole8Par() +
            ", hole8Yardage=" + getHole8Yardage() +
            ", hole8Handicap=" + getHole8Handicap() +
            ", hole9Par=" + getHole9Par() +
            ", hole9Yardage=" + getHole9Yardage() +
            ", hole9Handicap=" + getHole9Handicap() +
            ", hole10Par=" + getHole10Par() +
            ", hole10Yardage=" + getHole10Yardage() +
            ", hole10Handicap=" + getHole10Handicap() +
            ", hole11Par=" + getHole11Par() +
            ", hole11Yardage=" + getHole11Yardage() +
            ", hole11Handicap=" + getHole11Handicap() +
            ", hole12Par=" + getHole12Par() +
            ", hole12Yardage=" + getHole12Yardage() +
            ", hole12Handicap=" + getHole12Handicap() +
            ", hole13Par=" + getHole13Par() +
            ", hole13Yardage=" + getHole13Yardage() +
            ", hole13Handicap=" + getHole13Handicap() +
            ", hole14Par=" + getHole14Par() +
            ", hole14Yardage=" + getHole14Yardage() +
            ", hole14Handicap=" + getHole14Handicap() +
            ", hole15Par=" + getHole15Par() +
            ", hole15Yardage=" + getHole15Yardage() +
            ", hole15Handicap=" + getHole15Handicap() +
            ", hole16Par=" + getHole16Par() +
            ", hole16Yardage=" + getHole16Yardage() +
            ", hole16Handicap=" + getHole16Handicap() +
            ", hole17Par=" + getHole17Par() +
            ", hole17Yardage=" + getHole17Yardage() +
            ", hole17Handicap=" + getHole17Handicap() +
            ", hole18Par=" + getHole18Par() +
            ", hole18Yardage=" + getHole18Yardage() +
            ", hole18Handicap=" + getHole18Handicap() +
            "}";
    }
}
