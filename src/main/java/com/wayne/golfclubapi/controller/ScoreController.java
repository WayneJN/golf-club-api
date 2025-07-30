package com.wayne.golfclubapi.controller;

import com.wayne.golfclubapi.entity.Score;
import com.wayne.golfclubapi.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> list = scoreService.getAllScores();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        return scoreService.getScoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score) {
        Score saved = scoreService.createScore(score);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> updateScore(
            @PathVariable Long id,
            @RequestBody Score scoreDetails) {

        return scoreService.updateScore(id, scoreDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        boolean deleted = scoreService.deleteScore(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping(params = "tournamentId")
    public ResponseEntity<List<Score>> byTournament(@RequestParam Long tournamentId) {
        return ResponseEntity.ok(scoreService.getByTournamentId(tournamentId));
    }

    @GetMapping(params = "memberId")
    public ResponseEntity<List<Score>> byMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(scoreService.getByMemberId(memberId));
    }

}
