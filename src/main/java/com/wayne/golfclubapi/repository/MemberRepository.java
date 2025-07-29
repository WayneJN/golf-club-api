package com.yourorg.golfclubapi.repository;

import com.yourorg.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository offering basic CRUD and pagination.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
