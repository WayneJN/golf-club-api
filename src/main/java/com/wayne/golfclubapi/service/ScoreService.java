package com.wayne.golfclubapi.service;

import com.wayne.golfclubapi.entity.Score;
import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.repository.ScoreRepository;
import com.wayne.golfclubapi.repository.MemberRepository;
import com.wayne.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public ScoreService(
            ScoreRepository scoreRepository,
            MemberRepository memberRepository,
            TournamentRepository tournamentRepository
    ) {
        this.scoreRepository      = scoreRepository;
        this.memberRepository     = memberRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Optional<Score> getScoreById(Long id) {
        return scoreRepository.findById(id);
    }

    public Score createScore(Score score) {
        // Validate member exists
        Member member = memberRepository.findById(score.getMember().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid member ID: " + score.getMember().getId()
                ));

        // Validate tournament exists
        Tournament tournament = tournamentRepository.findById(score.getTournament().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid tournament ID: " + score.getTournament().getId()
                ));

        // Attach managed entities and save
        score.setMember(member);
        score.setTournament(tournament);
        return scoreRepository.save(score);
    }

    public Optional<Score> updateScore(Long id, Score details) {
        return scoreRepository.findById(id)
                .map(existing -> {
                    // Validate member on update
                    Member member = memberRepository.findById(details.getMember().getId())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "Invalid member ID: " + details.getMember().getId()
                            ));

                    // Validate tournament on update
                    Tournament tournament = tournamentRepository.findById(details.getTournament().getId())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "Invalid tournament ID: " + details.getTournament().getId()
                            ));

                    existing.setMember(member);
                    existing.setTournament(tournament);
                    existing.setStrokes(details.getStrokes());
                    existing.setRecordedAt(details.getRecordedAt());
                    return scoreRepository.save(existing);
                });
    }

    public boolean deleteScore(Long id) {
        return scoreRepository.findById(id)
                .map(score -> {
                    scoreRepository.delete(score);
                    return true;
                })
                .orElse(false);
    }

    public List<Score> getByTournamentId(Long tournamentId) {
        return scoreRepository.findByTournamentId(tournamentId);
    }

    public List<Score> getByMemberId(Long memberId) {
        return scoreRepository.findByMemberId(memberId);
    }
}
