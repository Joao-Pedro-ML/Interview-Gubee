package br.com.gubee.interview.model.dto;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroDTO {

    private UUID id;

    @NotEmpty
    private String name;

    @NotNull
    private Race race;

    private boolean enabled;

    @JsonProperty("powerStats")
    @NotNull
    private PowerStats powerStats;
}