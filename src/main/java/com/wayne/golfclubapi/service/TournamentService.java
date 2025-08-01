package com.wayne.golfclubapi.service;

import com.wayne.golfclubapi.data.TournamentData;
import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.repository.MemberRepository;
import com.wayne.golfclubapi.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository,
                             MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> updateTournament(Long id, Tournament details) {
        return tournamentRepository.findById(id)
                .map(existing -> {
                    existing.setStartDate(details.getStartDate());
                    existing.setEndDate(details.getEndDate());
                    existing.setLocation(details.getLocation());
                    existing.setEntryFee(details.getEntryFee());
                    existing.setCashPrize(details.getCashPrize());
                    return tournamentRepository.save(existing);
                });
    }

    public boolean deleteTournament(Long id) {
        return tournamentRepository.findById(id)
                .map(t -> {
                    tournamentRepository.delete(t);
                    return true;
                })
                .orElse(false);
    }

    public Optional<Tournament> addMemberToTournament(Long tournamentId, Long memberId) {
        Optional<Tournament> optT = tournamentRepository.findById(tournamentId);
        Optional<Member> optM = memberRepository.findById(memberId);

        if (optT.isPresent() && optM.isPresent()) {
            Tournament t = optT.get();
            t.getParticipants().add(optM.get());
            return Optional.of(tournamentRepository.save(t));
        }
        return Optional.empty();
    }

    public Optional<Tournament> removeMemberFromTournament(Long tournamentId, Long memberId) {
        Optional<Tournament> optT = tournamentRepository.findById(tournamentId);
        Optional<Member> optM = memberRepository.findById(memberId);

        if (optT.isPresent() && optM.isPresent()) {
            Tournament t = optT.get();
            t.getParticipants().remove(optM.get());
            return Optional.of(tournamentRepository.save(t));
        }
        return Optional.empty();
    }

    public List<Tournament> search(LocalDate startDate, String location, Long memberId) {
        Specification<Tournament> spec = Specification.<Tournament>unrestricted()
                .and(TournamentData.hasStartDate(startDate))
                .and(TournamentData.hasLocation(location))
                .and(TournamentData.hasParticipant(memberId));

        return tournamentRepository.findAll(spec);
    }

    public List<Tournament> findByParticipant(Long memberId) {
        return tournamentRepository.findAll(TournamentData.hasParticipant(memberId));
    }



}
