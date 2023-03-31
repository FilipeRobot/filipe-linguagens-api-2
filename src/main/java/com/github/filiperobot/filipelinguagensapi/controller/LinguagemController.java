package com.github.filiperobot.filipelinguagensapi.controller;

import com.github.filiperobot.filipelinguagensapi.model.Linguagem;
import com.github.filiperobot.filipelinguagensapi.service.LinguagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class LinguagemController {
    @Autowired
    private LinguagemService service;

    @GetMapping("/")
    public ResponseEntity<String> bemVindo() {
        return ResponseEntity.ok("Bem vindo a minha API");
    }

    @GetMapping("/linguagens")
    public ResponseEntity<List<Linguagem>> buscarLinguagens() {
        return ResponseEntity.ok(service.buscarLinguagens());
    }

    @GetMapping("/linguagens/{id}")
    public ResponseEntity<Linguagem> buscarLinguagemPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarLinguagemPorId(id));
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Void> cadastrarLinguagens(@RequestBody Linguagem linguagem) {
        return ResponseEntity.created(service.cadastrarLinguagens(linguagem)).build();
    }

    @PutMapping("/linguagens/{id}")
    public ResponseEntity<Void> atualizarLinguagens(@RequestBody Linguagem linguagem, @PathVariable String id) {
        URI urlParaLinguagemAtualizada = service.atualizarLinguagens(linguagem, id);
        return ResponseEntity.noContent().location(urlParaLinguagemAtualizada).build();
    }

    @DeleteMapping("/linguagens/{id}")
    public ResponseEntity<Void> removerLinguagem(@PathVariable String id) {
        service.removerLinguagem(id);
        return ResponseEntity.noContent().build();
    }
}
