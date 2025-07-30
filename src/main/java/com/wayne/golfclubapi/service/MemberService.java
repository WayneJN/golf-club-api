package com.wayne.golfclubapi.service;

import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> updateMember(Long id, Member details) {
        return memberRepository.findById(id)
                .map(existing -> {
                    existing.setName(details.getName());
                    existing.setEmail(details.getEmail());
                    existing.setMembershipStartDate(details.getMembershipStartDate());
                    return memberRepository.save(existing);
                });
    }

    public boolean deleteMember(Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return true;
                })
                .orElse(false);
    }
}
