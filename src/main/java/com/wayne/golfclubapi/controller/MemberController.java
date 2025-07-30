package com.wayne.golfclubapi.controller;

import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exposes /api/members endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Member>> listAll() {
        return ResponseEntity.ok(service.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getOne(@PathVariable Long id) {
        return service.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member m) {
        Member saved = service.createMember(m);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(
            @PathVariable Long id,
            @RequestBody Member m
    ) {
        return service.updateMember(id, m)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.deleteMember(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
