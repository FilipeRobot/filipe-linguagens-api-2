package com.github.filiperobot.filipelinguagensapi.repositories;

import com.github.filiperobot.filipelinguagensapi.model.Linguagem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LinguagemRepository extends MongoRepository<Linguagem, String> {
    List<Linguagem> findByOrderByRanking();
}
