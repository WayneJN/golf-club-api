package com.wayne.golfclubapi.data;

import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.entity.Tournament;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class TournamentData {

    public static Specification<Tournament> hasStartDate(LocalDate startDate) {
        return (root, query, cb) -> startDate == null ? null :
                cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<Tournament> hasLocation(String location) {
        return (root, query, cb) -> location == null ? null :
                cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%");
    }

    public static Specification<Tournament> hasParticipant(Long memberId) {
        return (root, query, cb) -> {
            if (memberId == null) return null;
            Join<Tournament, Member> participants = root.join("participants");
            return cb.equal(participants.get("id"), memberId);
        };
    }

}

