package overun.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import overun.socker.WebSocketServer;

import java.io.IOException;

/**
 * 消费者
 */

@Component
public class mqCustomer {

    @RabbitHandler
    @RabbitListener(queues = "direct")
    public void receive(String msg) {
        System.out.println("成功消费direct: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "overun.flow")
    public void flow(String msg) {
        System.out.println("成功消费overun.flow: "+msg);
    }


    @RabbitHandler
    @RabbitListener(queues = "overun.gee")
    public void gee(String msg) {
        try {
            WebSocketServer.sendInfo(msg,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("成功消费overun.gee: "+msg);
    }
}
