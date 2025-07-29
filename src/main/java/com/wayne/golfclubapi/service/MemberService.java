package com.yourorg.golfclubapi.service;

import com.yourorg.golfclubapi.entity.Member;
import com.yourorg.golfclubapi.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Business logic around Member operations.
 */
@Service
public class MemberService {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    /**
     * Fetch all members.
     */
    public List<Member> getAll() {
        return repo.findAll();
    }

    /**
     * Find one by id.
     */
    public Optional<Member> getById(Long id) {
        return repo.findById(id);
    }

    /**
     * Create or update a member.
     */
    public Member save(Member m) {
        return repo.save(m);
    }

    /**
     * Delete by id.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
