package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A DistanceOfApproach.
 */
@Entity
@Table(name = "distance_of_approach")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "distanceofapproach")
public class DistanceOfApproach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "tournament_id")
    private Long tournamentId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "hole_1")
    private Integer hole1;

    @Column(name = "hole_2")
    private Integer hole2;

    @Column(name = "hole_3")
    private Integer hole3;

    @Column(name = "hole_4")
    private Integer hole4;

    @Column(name = "hole_5")
    private Integer hole5;

    @Column(name = "hole_6")
    private Integer hole6;

    @Column(name = "hole_7")
    private Integer hole7;

    @Column(name = "hole_8")
    private Integer hole8;

    @Column(name = "hole_9")
    private Integer hole9;

    @Column(name = "hole_10")
    private Integer hole10;

    @Column(name = "hole_11")
    private Integer hole11;

    @Column(name = "hole_12")
    private Integer hole12;

    @Column(name = "hole_13")
    private Integer hole13;

    @Column(name = "hole_14")
    private Integer hole14;

    @Column(name = "hole_15")
    private Integer hole15;

    @Column(name = "hole_16")
    private Integer hole16;

    @Column(name = "hole_17")
    private Integer hole17;

    @Column(name = "hole_18")
    private Integer hole18;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public DistanceOfApproach playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public DistanceOfApproach tournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
        return this;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public DistanceOfApproach courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getHole1() {
        return hole1;
    }

    public DistanceOfApproach hole1(Integer hole1) {
        this.hole1 = hole1;
        return this;
    }

    public void setHole1(Integer hole1) {
        this.hole1 = hole1;
    }

    public Integer getHole2() {
        return hole2;
    }

    public DistanceOfApproach hole2(Integer hole2) {
        this.hole2 = hole2;
        return this;
    }

    public void setHole2(Integer hole2) {
        this.hole2 = hole2;
    }

    public Integer getHole3() {
        return hole3;
    }

    public DistanceOfApproach hole3(Integer hole3) {
        this.hole3 = hole3;
        return this;
    }

    public void setHole3(Integer hole3) {
        this.hole3 = hole3;
    }

    public Integer getHole4() {
        return hole4;
    }

    public DistanceOfApproach hole4(Integer hole4) {
        this.hole4 = hole4;
        return this;
    }

    public void setHole4(Integer hole4) {
        this.hole4 = hole4;
    }

    public Integer getHole5() {
        return hole5;
    }

    public DistanceOfApproach hole5(Integer hole5) {
        this.hole5 = hole5;
        return this;
    }

    public void setHole5(Integer hole5) {
        this.hole5 = hole5;
    }

    public Integer getHole6() {
        return hole6;
    }

    public DistanceOfApproach hole6(Integer hole6) {
        this.hole6 = hole6;
        return this;
    }

    public void setHole6(Integer hole6) {
        this.hole6 = hole6;
    }

    public Integer getHole7() {
        return hole7;
    }

    public DistanceOfApproach hole7(Integer hole7) {
        this.hole7 = hole7;
        return this;
    }

    public void setHole7(Integer hole7) {
        this.hole7 = hole7;
    }

    public Integer getHole8() {
        return hole8;
    }

    public DistanceOfApproach hole8(Integer hole8) {
        this.hole8 = hole8;
        return this;
    }

    public void setHole8(Integer hole8) {
        this.hole8 = hole8;
    }

    public Integer getHole9() {
        return hole9;
    }

    public DistanceOfApproach hole9(Integer hole9) {
        this.hole9 = hole9;
        return this;
    }

    public void setHole9(Integer hole9) {
        this.hole9 = hole9;
    }

    public Integer getHole10() {
        return hole10;
    }

    public DistanceOfApproach hole10(Integer hole10) {
        this.hole10 = hole10;
        return this;
    }

    public void setHole10(Integer hole10) {
        this.hole10 = hole10;
    }

    public Integer getHole11() {
        return hole11;
    }

    public DistanceOfApproach hole11(Integer hole11) {
        this.hole11 = hole11;
        return this;
    }

    public void setHole11(Integer hole11) {
        this.hole11 = hole11;
    }

    public Integer getHole12() {
        return hole12;
    }

    public DistanceOfApproach hole12(Integer hole12) {
        this.hole12 = hole12;
        return this;
    }

    public void setHole12(Integer hole12) {
        this.hole12 = hole12;
    }

    public Integer getHole13() {
        return hole13;
    }

    public DistanceOfApproach hole13(Integer hole13) {
        this.hole13 = hole13;
        return this;
    }

    public void setHole13(Integer hole13) {
        this.hole13 = hole13;
    }

    public Integer getHole14() {
        return hole14;
    }

    public DistanceOfApproach hole14(Integer hole14) {
        this.hole14 = hole14;
        return this;
    }

    public void setHole14(Integer hole14) {
        this.hole14 = hole14;
    }

    public Integer getHole15() {
        return hole15;
    }

    public DistanceOfApproach hole15(Integer hole15) {
        this.hole15 = hole15;
        return this;
    }

    public void setHole15(Integer hole15) {
        this.hole15 = hole15;
    }

    public Integer getHole16() {
        return hole16;
    }

    public DistanceOfApproach hole16(Integer hole16) {
        this.hole16 = hole16;
        return this;
    }

    public void setHole16(Integer hole16) {
        this.hole16 = hole16;
    }

    public Integer getHole17() {
        return hole17;
    }

    public DistanceOfApproach hole17(Integer hole17) {
        this.hole17 = hole17;
        return this;
    }

    public void setHole17(Integer hole17) {
        this.hole17 = hole17;
    }

    public Integer getHole18() {
        return hole18;
    }

    public DistanceOfApproach hole18(Integer hole18) {
        this.hole18 = hole18;
        return this;
    }

    public void setHole18(Integer hole18) {
        this.hole18 = hole18;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DistanceOfApproach)) {
            return false;
        }
        return id != null && id.equals(((DistanceOfApproach) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DistanceOfApproach{" +
            "id=" + getId() +
            ", playerId=" + getPlayerId() +
            ", tournamentId=" + getTournamentId() +
            ", courseId=" + getCourseId() +
            ", hole1=" + getHole1() +
            ", hole2=" + getHole2() +
            ", hole3=" + getHole3() +
            ", hole4=" + getHole4() +
            ", hole5=" + getHole5() +
            ", hole6=" + getHole6() +
            ", hole7=" + getHole7() +
            ", hole8=" + getHole8() +
            ", hole9=" + getHole9() +
            ", hole10=" + getHole10() +
            ", hole11=" + getHole11() +
            ", hole12=" + getHole12() +
            ", hole13=" + getHole13() +
            ", hole14=" + getHole14() +
            ", hole15=" + getHole15() +
            ", hole16=" + getHole16() +
            ", hole17=" + getHole17() +
            ", hole18=" + getHole18() +
            "}";
    }
}
