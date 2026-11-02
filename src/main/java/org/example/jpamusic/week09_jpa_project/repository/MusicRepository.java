package org.example.jpamusic.week09_jpa_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.jpamusic.week09_jpa_project.entity.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
