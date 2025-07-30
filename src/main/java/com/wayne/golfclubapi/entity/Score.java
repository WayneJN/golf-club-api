package com.wayne.golfclubapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the tournament
    @ManyToOne(optional = false)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    // Link to the member
    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private Integer strokes;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    public Score() {}

    public Score(Tournament tournament,
                 Member member,
                 Integer strokes,
                 LocalDateTime recordedAt) {
        this.tournament = tournament;
        this.member = member;
        this.strokes = strokes;
        this.recordedAt = recordedAt;
    }

    public Long getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getStrokes() {
        return strokes;
    }

    public void setStrokes(Integer strokes) {
        this.strokes = strokes;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
