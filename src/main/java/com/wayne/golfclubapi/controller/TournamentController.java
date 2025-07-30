
package com.wayne.golfclubapi.controller;

import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
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
}
