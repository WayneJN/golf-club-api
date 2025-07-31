package com.wayne.golfclubapi.controller;

import com.wayne.golfclubapi.data.MemberData;
import com.wayne.golfclubapi.entity.Member;
import com.wayne.golfclubapi.service.MemberService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tournamentStartDate
    ) {
        Specification<Member> spec = Specification.allOf();

        if (name != null) spec = spec.and(MemberData.hasName(name));
        if (phone != null) spec = spec.and(MemberData.hasPhone(phone));
        if (tournamentStartDate != null) spec = spec.and(MemberData.hasTournamentStartDate(tournamentStartDate));

        return ResponseEntity.ok(service.searchMembers(spec));

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
