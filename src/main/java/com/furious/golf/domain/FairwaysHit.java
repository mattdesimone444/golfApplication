package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A FairwaysHit.
 */
@Entity
@Table(name = "fairways_hit")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "fairwayshit")
public class FairwaysHit implements Serializable {

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
    private String hole1;

    @Column(name = "hole_2")
    private String hole2;

    @Column(name = "hole_3")
    private String hole3;

    @Column(name = "hole_4")
    private String hole4;

    @Column(name = "hole_5")
    private String hole5;

    @Column(name = "hole_6")
    private String hole6;

    @Column(name = "hole_7")
    private String hole7;

    @Column(name = "hole_8")
    private String hole8;

    @Column(name = "hole_9")
    private String hole9;

    @Column(name = "hole_10")
    private String hole10;

    @Column(name = "hole_11")
    private String hole11;

    @Column(name = "hole_12")
    private String hole12;

    @Column(name = "hole_13")
    private String hole13;

    @Column(name = "hole_14")
    private String hole14;

    @Column(name = "hole_15")
    private String hole15;

    @Column(name = "hole_16")
    private String hole16;

    @Column(name = "hole_17")
    private String hole17;

    @Column(name = "hole_18")
    private String hole18;

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

    public FairwaysHit playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public FairwaysHit tournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
        return this;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public FairwaysHit courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getHole1() {
        return hole1;
    }

    public FairwaysHit hole1(String hole1) {
        this.hole1 = hole1;
        return this;
    }

    public void setHole1(String hole1) {
        this.hole1 = hole1;
    }

    public String getHole2() {
        return hole2;
    }

    public FairwaysHit hole2(String hole2) {
        this.hole2 = hole2;
        return this;
    }

    public void setHole2(String hole2) {
        this.hole2 = hole2;
    }

    public String getHole3() {
        return hole3;
    }

    public FairwaysHit hole3(String hole3) {
        this.hole3 = hole3;
        return this;
    }

    public void setHole3(String hole3) {
        this.hole3 = hole3;
    }

    public String getHole4() {
        return hole4;
    }

    public FairwaysHit hole4(String hole4) {
        this.hole4 = hole4;
        return this;
    }

    public void setHole4(String hole4) {
        this.hole4 = hole4;
    }

    public String getHole5() {
        return hole5;
    }

    public FairwaysHit hole5(String hole5) {
        this.hole5 = hole5;
        return this;
    }

    public void setHole5(String hole5) {
        this.hole5 = hole5;
    }

    public String getHole6() {
        return hole6;
    }

    public FairwaysHit hole6(String hole6) {
        this.hole6 = hole6;
        return this;
    }

    public void setHole6(String hole6) {
        this.hole6 = hole6;
    }

    public String getHole7() {
        return hole7;
    }

    public FairwaysHit hole7(String hole7) {
        this.hole7 = hole7;
        return this;
    }

    public void setHole7(String hole7) {
        this.hole7 = hole7;
    }

    public String getHole8() {
        return hole8;
    }

    public FairwaysHit hole8(String hole8) {
        this.hole8 = hole8;
        return this;
    }

    public void setHole8(String hole8) {
        this.hole8 = hole8;
    }

    public String getHole9() {
        return hole9;
    }

    public FairwaysHit hole9(String hole9) {
        this.hole9 = hole9;
        return this;
    }

    public void setHole9(String hole9) {
        this.hole9 = hole9;
    }

    public String getHole10() {
        return hole10;
    }

    public FairwaysHit hole10(String hole10) {
        this.hole10 = hole10;
        return this;
    }

    public void setHole10(String hole10) {
        this.hole10 = hole10;
    }

    public String getHole11() {
        return hole11;
    }

    public FairwaysHit hole11(String hole11) {
        this.hole11 = hole11;
        return this;
    }

    public void setHole11(String hole11) {
        this.hole11 = hole11;
    }

    public String getHole12() {
        return hole12;
    }

    public FairwaysHit hole12(String hole12) {
        this.hole12 = hole12;
        return this;
    }

    public void setHole12(String hole12) {
        this.hole12 = hole12;
    }

    public String getHole13() {
        return hole13;
    }

    public FairwaysHit hole13(String hole13) {
        this.hole13 = hole13;
        return this;
    }

    public void setHole13(String hole13) {
        this.hole13 = hole13;
    }

    public String getHole14() {
        return hole14;
    }

    public FairwaysHit hole14(String hole14) {
        this.hole14 = hole14;
        return this;
    }

    public void setHole14(String hole14) {
        this.hole14 = hole14;
    }

    public String getHole15() {
        return hole15;
    }

    public FairwaysHit hole15(String hole15) {
        this.hole15 = hole15;
        return this;
    }

    public void setHole15(String hole15) {
        this.hole15 = hole15;
    }

    public String getHole16() {
        return hole16;
    }

    public FairwaysHit hole16(String hole16) {
        this.hole16 = hole16;
        return this;
    }

    public void setHole16(String hole16) {
        this.hole16 = hole16;
    }

    public String getHole17() {
        return hole17;
    }

    public FairwaysHit hole17(String hole17) {
        this.hole17 = hole17;
        return this;
    }

    public void setHole17(String hole17) {
        this.hole17 = hole17;
    }

    public String getHole18() {
        return hole18;
    }

    public FairwaysHit hole18(String hole18) {
        this.hole18 = hole18;
        return this;
    }

    public void setHole18(String hole18) {
        this.hole18 = hole18;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FairwaysHit)) {
            return false;
        }
        return id != null && id.equals(((FairwaysHit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FairwaysHit{" +
            "id=" + getId() +
            ", playerId=" + getPlayerId() +
            ", tournamentId=" + getTournamentId() +
            ", courseId=" + getCourseId() +
            ", hole1='" + getHole1() + "'" +
            ", hole2='" + getHole2() + "'" +
            ", hole3='" + getHole3() + "'" +
            ", hole4='" + getHole4() + "'" +
            ", hole5='" + getHole5() + "'" +
            ", hole6='" + getHole6() + "'" +
            ", hole7='" + getHole7() + "'" +
            ", hole8='" + getHole8() + "'" +
            ", hole9='" + getHole9() + "'" +
            ", hole10='" + getHole10() + "'" +
            ", hole11='" + getHole11() + "'" +
            ", hole12='" + getHole12() + "'" +
            ", hole13='" + getHole13() + "'" +
            ", hole14='" + getHole14() + "'" +
            ", hole15='" + getHole15() + "'" +
            ", hole16='" + getHole16() + "'" +
            ", hole17='" + getHole17() + "'" +
            ", hole18='" + getHole18() + "'" +
            "}";
    }
}
