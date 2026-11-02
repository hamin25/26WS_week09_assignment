package org.example.jpamusic.week09_jpa_project.controller;

import jakarta.validation.Valid;
import org.example.jpamusic.week09_jpa_project.dto.MusicRequest;
import org.example.jpamusic.week09_jpa_project.dto.MusicResponse;
import org.example.jpamusic.week09_jpa_project.repository.MusicRepository;
import org.example.jpamusic.week09_jpa_project.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {
    private final MusicService service;
    public MusicController(MusicService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MusicResponse> create (@Valid @RequestBody MusicRequest request){
        MusicResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/music"+created.id())).body(created);
    }

    @GetMapping
    public List<MusicResponse> findAll() { return service.findAll();}

    @GetMapping("/{id}")
    public MusicResponse findById(@PathVariable Long id) { return service.findById(id);}

    @PutMapping("/{id}")
    public MusicResponse update(@PathVariable Long id, @Valid @RequestBody MusicRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
