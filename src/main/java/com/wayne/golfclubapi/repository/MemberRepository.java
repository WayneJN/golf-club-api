package com.wayne.golfclubapi.repository;

import com.wayne.golfclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository
        extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

}
