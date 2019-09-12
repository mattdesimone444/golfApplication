package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A SGTeeToGreen.
 */
@Entity
@Table(name = "sg_tee_to_green")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sgteetogreen")
public class SGTeeToGreen implements Serializable {

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
    private Float hole1;

    @Column(name = "hole_2")
    private Float hole2;

    @Column(name = "hole_3")
    private Float hole3;

    @Column(name = "hole_4")
    private Float hole4;

    @Column(name = "hole_5")
    private Float hole5;

    @Column(name = "hole_6")
    private Float hole6;

    @Column(name = "hole_7")
    private Float hole7;

    @Column(name = "hole_8")
    private Float hole8;

    @Column(name = "hole_9")
    private Float hole9;

    @Column(name = "hole_10")
    private Float hole10;

    @Column(name = "hole_11")
    private Float hole11;

    @Column(name = "hole_12")
    private Float hole12;

    @Column(name = "hole_13")
    private Float hole13;

    @Column(name = "hole_14")
    private Float hole14;

    @Column(name = "hole_15")
    private Float hole15;

    @Column(name = "hole_16")
    private Float hole16;

    @Column(name = "hole_17")
    private Float hole17;

    @Column(name = "hole_18")
    private Float hole18;

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

    public SGTeeToGreen playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public SGTeeToGreen tournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
        return this;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SGTeeToGreen courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Float getHole1() {
        return hole1;
    }

    public SGTeeToGreen hole1(Float hole1) {
        this.hole1 = hole1;
        return this;
    }

    public void setHole1(Float hole1) {
        this.hole1 = hole1;
    }

    public Float getHole2() {
        return hole2;
    }

    public SGTeeToGreen hole2(Float hole2) {
        this.hole2 = hole2;
        return this;
    }

    public void setHole2(Float hole2) {
        this.hole2 = hole2;
    }

    public Float getHole3() {
        return hole3;
    }

    public SGTeeToGreen hole3(Float hole3) {
        this.hole3 = hole3;
        return this;
    }

    public void setHole3(Float hole3) {
        this.hole3 = hole3;
    }

    public Float getHole4() {
        return hole4;
    }

    public SGTeeToGreen hole4(Float hole4) {
        this.hole4 = hole4;
        return this;
    }

    public void setHole4(Float hole4) {
        this.hole4 = hole4;
    }

    public Float getHole5() {
        return hole5;
    }

    public SGTeeToGreen hole5(Float hole5) {
        this.hole5 = hole5;
        return this;
    }

    public void setHole5(Float hole5) {
        this.hole5 = hole5;
    }

    public Float getHole6() {
        return hole6;
    }

    public SGTeeToGreen hole6(Float hole6) {
        this.hole6 = hole6;
        return this;
    }

    public void setHole6(Float hole6) {
        this.hole6 = hole6;
    }

    public Float getHole7() {
        return hole7;
    }

    public SGTeeToGreen hole7(Float hole7) {
        this.hole7 = hole7;
        return this;
    }

    public void setHole7(Float hole7) {
        this.hole7 = hole7;
    }

    public Float getHole8() {
        return hole8;
    }

    public SGTeeToGreen hole8(Float hole8) {
        this.hole8 = hole8;
        return this;
    }

    public void setHole8(Float hole8) {
        this.hole8 = hole8;
    }

    public Float getHole9() {
        return hole9;
    }

    public SGTeeToGreen hole9(Float hole9) {
        this.hole9 = hole9;
        return this;
    }

    public void setHole9(Float hole9) {
        this.hole9 = hole9;
    }

    public Float getHole10() {
        return hole10;
    }

    public SGTeeToGreen hole10(Float hole10) {
        this.hole10 = hole10;
        return this;
    }

    public void setHole10(Float hole10) {
        this.hole10 = hole10;
    }

    public Float getHole11() {
        return hole11;
    }

    public SGTeeToGreen hole11(Float hole11) {
        this.hole11 = hole11;
        return this;
    }

    public void setHole11(Float hole11) {
        this.hole11 = hole11;
    }

    public Float getHole12() {
        return hole12;
    }

    public SGTeeToGreen hole12(Float hole12) {
        this.hole12 = hole12;
        return this;
    }

    public void setHole12(Float hole12) {
        this.hole12 = hole12;
    }

    public Float getHole13() {
        return hole13;
    }

    public SGTeeToGreen hole13(Float hole13) {
        this.hole13 = hole13;
        return this;
    }

    public void setHole13(Float hole13) {
        this.hole13 = hole13;
    }

    public Float getHole14() {
        return hole14;
    }

    public SGTeeToGreen hole14(Float hole14) {
        this.hole14 = hole14;
        return this;
    }

    public void setHole14(Float hole14) {
        this.hole14 = hole14;
    }

    public Float getHole15() {
        return hole15;
    }

    public SGTeeToGreen hole15(Float hole15) {
        this.hole15 = hole15;
        return this;
    }

    public void setHole15(Float hole15) {
        this.hole15 = hole15;
    }

    public Float getHole16() {
        return hole16;
    }

    public SGTeeToGreen hole16(Float hole16) {
        this.hole16 = hole16;
        return this;
    }

    public void setHole16(Float hole16) {
        this.hole16 = hole16;
    }

    public Float getHole17() {
        return hole17;
    }

    public SGTeeToGreen hole17(Float hole17) {
        this.hole17 = hole17;
        return this;
    }

    public void setHole17(Float hole17) {
        this.hole17 = hole17;
    }

    public Float getHole18() {
        return hole18;
    }

    public SGTeeToGreen hole18(Float hole18) {
        this.hole18 = hole18;
        return this;
    }

    public void setHole18(Float hole18) {
        this.hole18 = hole18;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SGTeeToGreen)) {
            return false;
        }
        return id != null && id.equals(((SGTeeToGreen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SGTeeToGreen{" +
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
