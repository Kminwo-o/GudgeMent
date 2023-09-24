package com.example.gudgement.game.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;

    @OneToMany(mappedBy = "gameRoom")
    private List<GameUser> users = new ArrayList<>();

    @Builder
    public GameRoom (Long roomId, String roomNumber,List<GameUser> users){
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.users = users;
    }
}
