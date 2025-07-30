package com.wayne.golfclubapi.controller;

import com.yourorg.golfclubapi.entity.Member;
import com.yourorg.golfclubapi.service.MemberService;
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

    // GET /api/members
    @GetMapping
    public ResponseEntity<List<Member>> listAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET /api/members/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Member> getOne(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/members
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member m) {
        Member saved = service.save(m);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // DELETE /api/members/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
