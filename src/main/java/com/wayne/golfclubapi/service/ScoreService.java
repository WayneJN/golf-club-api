package com.wayne.golfclubapi.service;

import com.wayne.golfclubapi.entity.Score;
import com.wayne.golfclubapi.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Optional<Score> getScoreById(Long id) {
        return scoreRepository.findById(id);
    }

    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    public Optional<Score> updateScore(Long id, Score details) {
        return scoreRepository.findById(id)
                .map(existing -> {
                    existing.setTournament(details.getTournament());
                    existing.setMember(details.getMember());
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
}
