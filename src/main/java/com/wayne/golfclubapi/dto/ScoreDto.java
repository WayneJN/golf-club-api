package com.wayne.golfclubapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScoreDto {

    private Long id;

    @NotNull(message = "Member ID is required")
    private Long memberId;

    @NotNull(message = "Tournament ID is required")
    private Long tournamentId;

    @NotNull(message = "Score value is required")
    @Min(value = 0, message = "Score must be zero or positive")
    private Integer scoreValue;

    public ScoreDto() {
    }

    public ScoreDto(Long id,
                    Long memberId,
                    Long tournamentId,
                    Integer scoreValue) {
        this.id = id;
        this.memberId = memberId;
        this.tournamentId = tournamentId;
        this.scoreValue = scoreValue;
    }

}