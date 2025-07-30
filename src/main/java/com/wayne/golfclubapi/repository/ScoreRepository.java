package com.wayne.golfclubapi.repository;

import com.wayne.golfclubapi.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findByTournamentId(Long tournamentId);

    List<Score> findByMemberId(Long memberId);
}
