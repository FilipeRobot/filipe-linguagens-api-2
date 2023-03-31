package com.github.filiperobot.filipelinguagensapi.service;

import com.github.filiperobot.filipelinguagensapi.model.Linguagem;
import com.github.filiperobot.filipelinguagensapi.repositories.LinguagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class LinguagemService {
    @Autowired
    private LinguagemRepository repository;

    public List<Linguagem> buscarLinguagens() {
        List<Linguagem> linguagens = repository.findByOrderByRanking();
        if (linguagens.size() < 1) {
            throw new RuntimeException("Nenhuma linguagem registrada!");
        }
        return linguagens;
    }

    public Linguagem buscarLinguagemPorId(String id) {
        Optional<Linguagem> linguagem = repository.findById(id);

        return linguagem.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Linguagem informada não encontrada!"));
    }

    public URI cadastrarLinguagens(Linguagem linguagem) {
        if (linguagem.getTitle() == null || linguagem.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title não informado, por favor informe o nome da linguagem.");
        }

        if (linguagem.getUrl() == null || linguagem.getUrl().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "url não informado, por favor informe um link para uma imagem" +
                    "representativa da linguagem.");
        }

        Linguagem linguagemSalva = repository.save(linguagem);

        return ServletUriComponentsBuilder.fromCurrentServletMapping().path("/linguagens/{id}").buildAndExpand(linguagemSalva.getUrl()).toUri();
    }

    public URI atualizarLinguagens(@RequestBody Linguagem linguagem, @PathVariable String id) {
        if (linguagem.getTitle().isBlank()) {
            throw new RuntimeException("\"title\" não informado, por favor informe o nome da linguagem.");
        }

        if (linguagem.getUrl().isBlank()) {
            throw new RuntimeException("\"url\" não informado, por favor informe um link para uma imagem" +
                    "representativa da linguagem.");
        }

        Linguagem linguagemAntiga = buscarLinguagemPorId(id);
        linguagem.setId(linguagemAntiga.getId());

        repository.save(linguagem);

        return ServletUriComponentsBuilder.fromCurrentServletMapping().path("/linguagens/{id}").buildAndExpand(linguagem.getId()).toUri();
    }

    public void removerLinguagem(String id) {
        Linguagem linguagem = buscarLinguagemPorId(id);
        repository.deleteById(linguagem.getId());
    }
}
