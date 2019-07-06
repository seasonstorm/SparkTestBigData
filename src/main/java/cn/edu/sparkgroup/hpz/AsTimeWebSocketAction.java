package cn.edu.sparkgroup.hpz;


import org.apache.struts2.ServletActionContext;
import redis.clients.jedis.Jedis;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws/websocket")
public class AsTimeWebSocketAction {
    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException, InterruptedException
    {
        Jedis jedis=new Jedis("localhost");
        System.out.println("***************");
        session.getBasicRemote().sendText("  this is  message");
        while(true){
            Thread.sleep(2500);
            session.getBasicRemote().sendText(jedis.get("flag").toString());
            System.out.println(jedis.get("flag").toString());
        }
    }

    @OnOpen
    public void onOpen(){
        System.out.println(" client connected ");
    }

    @OnClose
    public void onClose(){
        System.out.println(" connection closed ");
    }
}
