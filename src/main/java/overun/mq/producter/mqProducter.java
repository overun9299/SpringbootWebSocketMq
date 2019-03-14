package overun.mq.producter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 */
@Component
public class mqProducter {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 推送routingKey为direct的队列
     * @param msg
     */
    public void sender(String msg){
        System.out.println("推送消息(direct)"+msg);
        rabbitTemplate.convertAndSend("direct", msg);
    }


    /**
     * 按flowExchange规则进行推送
     * flowExchange的匹配规则为 ".*" 只能辐射一层
     * @param msg
     */
    public void senderFlow(String msg) {
        System.out.println("推送消息(匹配规则为flowExchange)"+msg);
        rabbitTemplate.convertAndSend("flowExchange", "overun.flow.soap", msg);
    }

    /**
     * 按geeExchange规则进行推送
     * flowExchange的匹配规则为 ".#" 可以辐射多层
     * @param msg
     */
    public void senderGee(String msg) {
        System.out.println("推送消息(匹配规则为geeExchange)"+msg);
        rabbitTemplate.convertAndSend("geeExchange", "overun.gee.soap.g", msg);
    }
}
