package com.wayne.golfclubapi.data;

import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.entity.Tournament;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MemberData {

    public static Specification<Member> hasName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Member> hasPhone(String phone) {
        return (root, query, cb) ->
                cb.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Member> hasTournamentStartDate(LocalDate date) {
        return (root, query, cb) -> {
            Join<Member, Tournament> join = root.join("tournaments");
            return cb.equal(join.get("startDate"), date);
        };
    }
}
