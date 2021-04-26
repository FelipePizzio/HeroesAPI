package com.digitalinnovationone.HerosAPI.service;

import com.digitalinnovationone.HerosAPI.document.Heroes;
import com.digitalinnovationone.HerosAPI.repository.HeroesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository){
        this.heroesRepository = heroesRepository;
    }

    public Flux<Heroes> findAllHeroes(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findHeroById(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> saveHero(Heroes hero) {
        return Mono.justOrEmpty(this.heroesRepository.save(hero));
    }

    public Mono<Boolean> deleteHeroById(String id){
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
