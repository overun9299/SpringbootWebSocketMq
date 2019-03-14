package overun.mq.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建队列
 */

@Configuration
public class queueConfig {

    /**
     *  ------------------------------
     * 交换机队列(直接使用routingKey匹配队列)   生产者-交换机-队列-消费者
     *  ------------------------------
     */

    /**
     * 创建routingKey为direct的队列
     * @return
     */
    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue("direct");
    }


    /**
     *  -------------------------
     * 交换机队列(按规则转发消息)  生产者-交换机-队列(有匹配规则)-消费者
     *  -------------------------
     */

    /**
     * 创建routingKey为overun.flow的队列
     * @return
     */
    @Bean
    public Queue flowQueue() {
        return new Queue("overun.flow");
    }

    /**
     * 创建routingKey为overun.gee的队列
     * @return
     */
    @Bean
    public Queue geeQueue() {
        return new Queue("overun.gee");
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange flowExchange() {
        return new TopicExchange("flowExchange");
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange geeExchange() {
        return new TopicExchange("geeExchange");
    }

    /**
     * 交换机匹配规则 *号只能向后多匹配一层路径
     * @param flowQueue
     * @param flowExchange
     * @return
     */
    @Bean
    public Binding bindingFlowExchange(Queue flowQueue, TopicExchange flowExchange) {
        return BindingBuilder.bind(flowQueue).to(flowExchange).with("overun.flow.*");
    }

    /**
     * 交换机匹配规则 #号可以向后匹配多层路径
     * @param geeQueue
     * @param geeExchange
     * @return
     */
    @Bean
    public Binding bindingGeeExchange(Queue geeQueue, TopicExchange geeExchange) {
        return BindingBuilder.bind(geeQueue).to(geeExchange).with("overun.gee.#");
    }


}
