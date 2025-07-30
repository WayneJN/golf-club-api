// src/main/java/com/yourorg/golfclubapi/controller/ScoreController.java
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
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Long id) {
        return scoreService.getScoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
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
}
