// src/main/java/com/yourorg/golfclubapi/repository/MemberRepository.java
package com.wayne.golfclubapi.repository;

import com.wayne.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

public interface MemberRepository
        extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByEmail(String email);
}
