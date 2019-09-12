package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A Golfer.
 */
@Entity
@Table(name = "golfer")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "golfer")
public class Golfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "age")
    private Integer age;

    @Column(name = "residence_city")
    private String residenceCity;

    @Column(name = "residence_state")
    private String residenceState;

    @Column(name = "plays_from_city")
    private String playsFromCity;

    @Column(name = "plays_from_state")
    private String playsFromState;

    @Column(name = "turned_pro")
    private Integer turnedPro;

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

    public Golfer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public Golfer height(Double height) {
        this.height = height;
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Golfer weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public Golfer age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public Golfer residenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
        return this;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getResidenceState() {
        return residenceState;
    }

    public Golfer residenceState(String residenceState) {
        this.residenceState = residenceState;
        return this;
    }

    public void setResidenceState(String residenceState) {
        this.residenceState = residenceState;
    }

    public String getPlaysFromCity() {
        return playsFromCity;
    }

    public Golfer playsFromCity(String playsFromCity) {
        this.playsFromCity = playsFromCity;
        return this;
    }

    public void setPlaysFromCity(String playsFromCity) {
        this.playsFromCity = playsFromCity;
    }

    public String getPlaysFromState() {
        return playsFromState;
    }

    public Golfer playsFromState(String playsFromState) {
        this.playsFromState = playsFromState;
        return this;
    }

    public void setPlaysFromState(String playsFromState) {
        this.playsFromState = playsFromState;
    }

    public Integer getTurnedPro() {
        return turnedPro;
    }

    public Golfer turnedPro(Integer turnedPro) {
        this.turnedPro = turnedPro;
        return this;
    }

    public void setTurnedPro(Integer turnedPro) {
        this.turnedPro = turnedPro;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Golfer)) {
            return false;
        }
        return id != null && id.equals(((Golfer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Golfer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", age=" + getAge() +
            ", residenceCity='" + getResidenceCity() + "'" +
            ", residenceState='" + getResidenceState() + "'" +
            ", playsFromCity='" + getPlaysFromCity() + "'" +
            ", playsFromState='" + getPlaysFromState() + "'" +
            ", turnedPro=" + getTurnedPro() +
            "}";
    }
}
