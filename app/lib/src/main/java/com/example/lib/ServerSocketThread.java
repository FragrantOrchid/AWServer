package com.example.lib;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;


import org.json.JSONObject;

public class ServerSocketThread extends Thread{
    private BufferedReader in;
    private PrintWriter pw;
    private Socket socket;
    public ServerSocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);

            String content;

            while ((content = in.readLine()) != null) {
                //4.和客户端通信
                JSONObject jsonObject = new JSONObject(content);
                JSONObject returnJsonObject = new JSONObject();
                UUID uuid = (UUID) jsonObject.get("uuid");
                Player P = AllPlayers.getPlayer(uuid);
                Player enemy = AllPlayers.getEnemyPlayer(uuid);
                //构建返回数据的json
                switch ((int)jsonObject.get("code")){
                    case 8:

                        Player player = new Player(uuid);
                        AllPlayers.addPlayer(player);

                        returnJsonObject.put("code",108);
                        break;
                    case 208:
                        if(AllPlayers.getEnemyPlayer(uuid) == null){
                            returnJsonObject.put("code",308);
                        } else {
                            returnJsonObject.put("code",408);
                        }
                        break;
                    case 508:

                        P.score = (int) jsonObject.get("score");
                        P.gameover = (boolean) jsonObject.get("gameover");
                        returnJsonObject.put("code",608);

                        returnJsonObject.put("score",enemy.score);
                        returnJsonObject.put("gameover",enemy.gameover);
                        break;
                    case 708:
                        P.score = (int) jsonObject.get("score");
                        P.gameover = true;
                        returnJsonObject.put("code",808);
                        returnJsonObject.put("score",enemy.score);
                        break;
                    default:
                        break;
                }
                String returnString = returnJsonObject.toString();
                pw.println(returnString);





            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
