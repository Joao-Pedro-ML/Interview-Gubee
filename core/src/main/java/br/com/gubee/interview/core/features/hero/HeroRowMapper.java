package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class HeroRowMapper implements RowMapper<HeroDTO> {

    @Override
    public HeroDTO mapRow(ResultSet rs, int i) throws SQLException {

        return HeroDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .race(Race.valueOf(rs.getString("race")))
                .enabled(rs.getBoolean("enabled"))
                .powerStats(PowerStats.builder()
                        .id(UUID.fromString(rs.getString("power_stats_id")))
                        .strength(rs.getInt("strength"))
                        .agility(rs.getInt("agility"))
                        .dexterity(rs.getInt("dexterity"))
                        .intelligence(rs.getInt("intelligence"))
                        .build())
                .build();
    }
}