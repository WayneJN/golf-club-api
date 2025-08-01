package com.wayne.golfclubapi.service;

import com.wayne.golfclubapi.entity.Tournament;
import com.wayne.golfclubapi.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private TournamentService tournamentService;

    @Test
    void testFindByParticipant_returnsMatchingTournaments() {
        Long memberId = 42L;

        Tournament t1 = new Tournament();
        t1.setId(1L);
        t1.setLocation("St. John's");

        Tournament t2 = new Tournament();
        t2.setId(2L);
        t2.setLocation("Corner Brook");

        List<Tournament> mockResults = List.of(t1, t2);

        when(tournamentRepository.findAll(any(Specification.class)))
                .thenReturn(mockResults);

        List<Tournament> results = tournamentService.findByParticipant(memberId);

        assertEquals(2, results.size());
        assertEquals("St. John's", results.get(0).getLocation());
    }
}

