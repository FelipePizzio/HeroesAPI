package com.digitalinnovationone.HerosAPI.controller;

import com.digitalinnovationone.HerosAPI.document.Heroes;
import com.digitalinnovationone.HerosAPI.repository.HeroesRepository;
import com.digitalinnovationone.HerosAPI.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovationone.HerosAPI.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j

public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController (HeroesService heroesService, HeroesRepository heroesRepository){
        this.heroesRepository = heroesRepository;
        this.heroesService = heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllHeroes() {
        log.info("requesting all heroes");
        return heroesService.findAllHeroes();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/id")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable String id) {
        log.info("requesting the hero {}", id);
        return heroesService.findHeroById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero has rise");
        return heroesService.saveHero(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/id")
    @ResponseStatus(code = HttpStatus.GONE)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id) {
        heroesService.deleteHeroById(id);
        log.info("the hero {} has fallen", id);
        return Mono.just(HttpStatus.GONE);
    }
}
