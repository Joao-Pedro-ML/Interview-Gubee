package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.dto.HeroesDTO;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }
    // Testado e funcionando
    // Requisição: curl -X POST -H "Content-Type: application/json" -d '{
    //  "name": "Wonder Woman",
    //  "agility": 8,
    //  "dexterity": 8,
    //  "strength": 8,
    //  "intelligence": 8,
    //  "race": "DIVINE"
    //}' http://localhost:8080/api/v1/heroes

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Validated @PathVariable UUID id) {
        try{
            HeroDTO hero = heroService.findById(id);
            return ok(hero);
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }
    // Testado e funcionando
    // Requisição: curl http://localhost:8080/api/v1/heroes/021c8fdb-f1ab-400d-858b-2eff9b5599e0

    @GetMapping
    public ResponseEntity<?> findByName(@Validated @RequestParam String name) {
        try {
            return ok(heroService.findByName(name));
        } catch (EmptyResultDataAccessException e) {
            return noContent().build();
        }
    }
    // Testado e funcionando
    // Requisição: curl 'http://localhost:8080/api/v1/heroes?name=Wonder+Woman'

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHero(
            @Validated @RequestBody HeroDTO hero, @Validated @PathVariable UUID id
    ) {
        try {
            heroService.updateHero(id, hero);
            return noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }

    }
    // Testado e funcionando

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Validated @PathVariable UUID id) {
        try {
            heroService.delete(id);
            return noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return notFound().build();
        }
    }
// Requisição: curl -X DELETE http://localhost:8080/api/v1/heroes/e484fd49-aa9a-40b4-9b21-e51782dcb22d

    @GetMapping("/compare/{id1}/{id2}")
    public ResponseEntity<?> compare(
            @Validated @PathVariable("id1") UUID id1, @Validated @PathVariable("id2") UUID id2
    ) {
        HeroesDTO heroes = heroService.compare(id1, id2);

        if (heroes == null) {
            return notFound().build();
        }

        return ok(heroes);
    }
    // Requisição: curl http://localhost:8080/api/v1/heroes/compare/0812e81f-a970-4313-a60c-aac5ea684875/ce384c88-945f-4004-bd7e-bbd46659564a

}
