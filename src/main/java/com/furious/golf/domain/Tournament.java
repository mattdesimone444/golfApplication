package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Tournament.
 */
@Entity
@Table(name = "tournament")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tournament")
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "season")
    private Integer season;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "purse")
    private Double purse;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "pga_id")
    private Long pgaId;

    @Column(name = "pga_season_id")
    private Long pgaSeasonId;

    @Column(name = "loaded")
    private Boolean loaded;

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

    public Tournament name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeason() {
        return season;
    }

    public Tournament season(Integer season) {
        this.season = season;
        return this;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Tournament startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Tournament endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPurse() {
        return purse;
    }

    public Tournament purse(Double purse) {
        this.purse = purse;
        return this;
    }

    public void setPurse(Double purse) {
        this.purse = purse;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Tournament courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPgaId() {
        return pgaId;
    }

    public Tournament pgaId(Long pgaId) {
        this.pgaId = pgaId;
        return this;
    }

    public void setPgaId(Long pgaId) {
        this.pgaId = pgaId;
    }

    public Long getPgaSeasonId() {
        return pgaSeasonId;
    }

    public Tournament pgaSeasonId(Long pgaSeasonId) {
        this.pgaSeasonId = pgaSeasonId;
        return this;
    }

    public void setPgaSeasonId(Long pgaSeasonId) {
        this.pgaSeasonId = pgaSeasonId;
    }

    public Boolean isLoaded() {
        return loaded;
    }

    public Tournament loaded(Boolean loaded) {
        this.loaded = loaded;
        return this;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tournament)) {
            return false;
        }
        return id != null && id.equals(((Tournament) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tournament{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", season=" + getSeason() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", purse=" + getPurse() +
            ", courseId=" + getCourseId() +
            ", pgaId=" + getPgaId() +
            ", pgaSeasonId=" + getPgaSeasonId() +
            ", loaded='" + isLoaded() + "'" +
            "}";
    }
}
