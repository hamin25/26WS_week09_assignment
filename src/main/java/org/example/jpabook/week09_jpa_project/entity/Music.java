package org.example.jpabook.week09_jpa_project.entity;


import jakarta.persistence.*;
import org.example.jpabook.week09_jpa_project.dto.MusicRequest;

@Entity
@Table(name="music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 200)
    private String artist;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private Double rating;



    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getCategory() { return category; }
    public Integer getReleaseYear() { return releaseYear; }
    public Double getRating(){return rating;}

    protected Music(){}

    public void update(MusicRequest request){
        this.title = request.title();
        this.artist = request.artist();
        this.category = request.category();
        this.releaseYear = request.releaseYear();
        this.rating = request.rating();
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
