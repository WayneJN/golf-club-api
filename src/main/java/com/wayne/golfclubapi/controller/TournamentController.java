
package com.wayne.golfclubapi.controller;

import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> list = tournamentService.getAllTournaments();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tournament> createTournament(
            @RequestBody Tournament t
    ) {
        Tournament saved = tournamentService.createTournament(t);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(
            @PathVariable Long id,
            @RequestBody Tournament tournamentDetails) {

        return tournamentService.updateTournament(id, tournamentDetails)
                .map(updated -> ResponseEntity.ok(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        boolean deleted = tournamentService.deleteTournament(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> addMember(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        return tournamentService.addMemberToTournament(tournamentId, memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> removeMember(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {

        return tournamentService.removeMemberFromTournament(tournamentId, memberId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // GET /api/tournaments?memberId=5
    @GetMapping(params = "memberId")
    public ResponseEntity<List<Tournament>> byMember(
            @RequestParam Long memberId
    ) {
        return ResponseEntity.ok(
                tournamentService.findByParticipant(memberId)
        );
    }

}
