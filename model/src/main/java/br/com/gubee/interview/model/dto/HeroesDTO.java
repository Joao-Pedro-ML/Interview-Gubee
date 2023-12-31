package br.com.gubee.interview.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroesDTO {

    private UUID hero1Id;
    private UUID hero2Id;

    private int strengthDiff;
    private int agilityDiff;
    private int dexterityDiff;
    private int intelligenceDiff;
}