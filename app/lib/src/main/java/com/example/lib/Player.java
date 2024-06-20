package com.example.lib;

import java.util.UUID;

public class Player {
    public UUID uuid;
    public UUID enemyUUID;
    public int score;
    public boolean gameover;
    public Player(UUID uuid) {
        this.uuid = uuid;
        score = 0;
        gameover = false;
        uuid = null;
    }

    public void setEnemyUUID(UUID enemyUUID) {
        this.enemyUUID = enemyUUID;
    }

    public boolean equals(Player p) {
        return this.uuid.equals(p.uuid);
    }
}
