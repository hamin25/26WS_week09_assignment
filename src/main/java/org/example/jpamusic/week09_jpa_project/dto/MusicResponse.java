package org.example.jpamusic.week09_jpa_project.dto;

import org.example.jpamusic.week09_jpa_project.entity.Music;

public record MusicResponse (
        Long id, String title, String artist, String category, Integer releaseYear, Double rating
){
    public static MusicResponse from(Music music){
        return new MusicResponse (music.getId(),music.getTitle(), music.getArtist(), music.getCategory(), music.getReleaseYear(), music.getRating());
    }
}
