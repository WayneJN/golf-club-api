package com.yourorg.golfclubapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a golf club member.
 */
@Entity                                // Mark this POJO as a JPA-managed table. Automatically generate SQL for CRUD
@Table(name = "members")               // Optional: specify the table name, otherwise it would resort to table "member"
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // Auto-generated primary key

    @Column(nullable = false)
    private String name;                // Full member name

    @Column(nullable = false, unique = true)
    private String email;               // Unique email for contact

    private String address;             // Mailing address

    private String phone;               // Contact phone number

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;        // When membership begins

    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;     // Membership length in months

    // Default constructor for JPA
    public Member() {}

    // Convenience constructor
    public Member(String name, String email, String address, String phone,
                  LocalDate startDate, Integer durationMonths) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.startDate = startDate;
        this.durationMonths = durationMonths;
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public Integer getDurationMonths() { return durationMonths; }
    public void setDurationMonths(Integer durationMonths) { this.durationMonths = durationMonths; }
}
