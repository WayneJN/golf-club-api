package com.wayne.golfclubapi.repository;

import com.wayne.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhoneContaining(String phone);
}
