package com.wayne.golfclubapi.repository;

import com.wayne.golfclubapi.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TournamentRepository
        extends JpaRepository<Tournament, Long>, JpaSpecificationExecutor<Tournament> {

}
