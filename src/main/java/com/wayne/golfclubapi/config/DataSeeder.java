package com.wayne.golfclubapi.config;

import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.repository.MemberRepository;
import com.wayne.golfclubapi.repository.TournamentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataSeeder {

    private final MemberRepository memberRepository;
    private final TournamentRepository tournamentRepository;

    public DataSeeder(MemberRepository memberRepository, TournamentRepository tournamentRepository) {
        this.memberRepository = memberRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @PostConstruct
    public void seed() {
        System.out.println("🔄 Starting DataSeeder...");

        long memberCount = memberRepository.count();
        System.out.println("👥 Member count: " + memberCount);

        long tournamentCount = tournamentRepository.count();
        System.out.println("⛳ Tournament count: " + tournamentCount);

        if (memberCount == 0) {
            System.out.println("📥 Seeding members...");
            memberRepository.save(new Member("Wayne Gretzky", "wayne@golfclub.ca", "99 Ice Rink Blvd", "123-456-7890", LocalDate.of(2025, 7, 1), 12));
            memberRepository.save(new Member("Tiger Woods", "tiger@golfclub.ca", "18 Fairway Lane", "987-654-3210", LocalDate.of(2025, 6, 15), 6));
            System.out.println("✅ Members seeded.");
        } else {
            System.out.println("ℹ️ Members already exist — skipping.");
        }

        if (tournamentCount == 0) {
            System.out.println("📥 Seeding tournaments...");
            tournamentRepository.save(new Tournament(
                    LocalDate.of(2025, 8, 15),
                    LocalDate.of(2025, 8, 17),
                    "Pebble Beach",
                    BigDecimal.valueOf(100),
                    BigDecimal.valueOf(5000)
            ));
        }
    }}

          