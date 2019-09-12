package com.furious.golf.domain;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TournamentPuttingAnalysis.
 */
@Entity
@Table(name = "tournament_putting_analysis")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tournamentputtinganalysis")
public class TournamentPuttingAnalysis implements Serializable {

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

    @Column(name = "round_one_id")
    private Long roundOneId;

    @Column(name = "round_two_id")
    private Long roundTwoId;

    @Column(name = "round_three_id")
    private Long roundThreeId;

    @Column(name = "round_four_id")
    private Long roundFourId;

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

    public TournamentPuttingAnalysis playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public TournamentPuttingAnalysis tournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
        return this;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public TournamentPuttingAnalysis courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRoundOneId() {
        return roundOneId;
    }

    public TournamentPuttingAnalysis roundOneId(Long roundOneId) {
        this.roundOneId = roundOneId;
        return this;
    }

    public void setRoundOneId(Long roundOneId) {
        this.roundOneId = roundOneId;
    }

    public Long getRoundTwoId() {
        return roundTwoId;
    }

    public TournamentPuttingAnalysis roundTwoId(Long roundTwoId) {
        this.roundTwoId = roundTwoId;
        return this;
    }

    public void setRoundTwoId(Long roundTwoId) {
        this.roundTwoId = roundTwoId;
    }

    public Long getRoundThreeId() {
        return roundThreeId;
    }

    public TournamentPuttingAnalysis roundThreeId(Long roundThreeId) {
        this.roundThreeId = roundThreeId;
        return this;
    }

    public void setRoundThreeId(Long roundThreeId) {
        this.roundThreeId = roundThreeId;
    }

    public Long getRoundFourId() {
        return roundFourId;
    }

    public TournamentPuttingAnalysis roundFourId(Long roundFourId) {
        this.roundFourId = roundFourId;
        return this;
    }

    public void setRoundFourId(Long roundFourId) {
        this.roundFourId = roundFourId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TournamentPuttingAnalysis)) {
            return false;
        }
        return id != null && id.equals(((TournamentPuttingAnalysis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TournamentPuttingAnalysis{" +
            "id=" + getId() +
            ", playerId=" + getPlayerId() +
            ", tournamentId=" + getTournamentId() +
            ", courseId=" + getCourseId() +
            ", roundOneId=" + getRoundOneId() +
            ", roundTwoId=" + getRoundTwoId() +
            ", roundThreeId=" + getRoundThreeId() +
            ", roundFourId=" + getRoundFourId() +
            "}";
    }
}
