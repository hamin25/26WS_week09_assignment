package org.example.jpabook.week09_jpa_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MusicRequest (
    @NotBlank @Size(max=200) String title,
    @NotBlank @Size(max=200) String artist,
    @NotBlank @Size(max=50) String category,
    @NotNull Integer releaseYear,
    @NotNull Double rating
) {}
