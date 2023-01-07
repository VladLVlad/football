package com.example.football.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "playOfNumber")
    private int playOfNumber;
    @Column(name = "age")
    private int age;
    @Column(name = "role")
    private String role;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "contract")
    private String contract;
    @Column(name = "cups")
    private String cups;
    @Column(name = "personalAwards")
    private String personalAwards;
    @Column(name = "statistics")
    private String statistics;
    @Column(name = "agent")
    private String agent;
    @Column(name = "clubCareer")
    private String clubCareer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "player")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToPlayer(Image image) {
        image.setPlayer(this);
        images.add(image);
    }

}
