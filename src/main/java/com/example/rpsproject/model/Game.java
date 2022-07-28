package com.example.rpsproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String playerName;

    @Column
    private Move playerMove;

    @Column
    private Move computerMove;

    @Column
    private Status status;

    @Column
    private Result result;

    public Game (String playerName) {
        this.status = Status.Started;
        this.playerName = playerName;
    }

}
