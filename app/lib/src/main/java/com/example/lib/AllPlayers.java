package com.example.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AllPlayers {
    public static List<Player> players = new ArrayList<>();

    public static void addPlayer(Player P){
        for(Player player : players){
            if(player.enemyUUID == null){
                player.enemyUUID = P.uuid;
                P.enemyUUID = player.uuid;
            }
        }
        players.add(P);
    }
    public static Player getPlayer(UUID uuid){
        for(Player player : players){
            if(player.uuid.equals(uuid)){
                return player;
            }
        }
        return null;
    }
    public static Player getEnemyPlayer(UUID uuid){
        for(Player player : players){
            if(player.enemyUUID.equals(uuid)){
                return player;
            }
        }
        return null;
    }
}
