package com.wayne.golfclubapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "tournaments")
public class Tournament {

    //For testing//
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Setter
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Setter
    @Column(nullable = false)
    private String location;

    @Setter
    @Column(name = "entry_fee", nullable = false)
    private BigDecimal entryFee;

    @Setter
    @Column(name = "cash_prize", nullable = false)
    private BigDecimal cashPrize;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "tournament_member",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> participants = new HashSet<>();

    public Tournament() {}

    public Tournament(LocalDate startDate,
                      LocalDate endDate,
                      String location,
                      BigDecimal entryFee,
                      BigDecimal cashPrize) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
    }


}
