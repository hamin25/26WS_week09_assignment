package org.example.jpamusic.week09_jpa_project.service;

import org.example.jpamusic.week09_jpa_project.dto.MusicRequest;
import org.example.jpamusic.week09_jpa_project.dto.MusicResponse;
import org.example.jpamusic.week09_jpa_project.entity.Music;
import org.example.jpamusic.week09_jpa_project.repository.MusicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class MusicService {
    private final MusicRepository repository;
    public MusicService(MusicRepository repository){
        this.repository = repository;
    }

    public MusicResponse create(MusicRequest request){
        Music saved = repository.save(new Music(request));
        return MusicResponse.from(saved);
    }

    public List<MusicResponse> findAll() { return repository.findAll().stream().map(MusicResponse::from).toList();}

    public MusicResponse findById(Long id){ return MusicResponse.from(findEntity(id));}

    private Music findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Music not found: " + id
                ));
    }

    public MusicResponse update(Long id, MusicRequest request){
        Music music = findEntity(id);
        music.update(request);
        return MusicResponse.from(repository.save(music));
    }

    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Music not found: "+id);
        }
        repository.deleteById(id);
    }


}
