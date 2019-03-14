package overun.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import overun.mq.producter.mqProducter;

@Controller
public class mqController {

    @Autowired
    private mqProducter sender;

    @RequestMapping("direct")
    @ResponseBody
    public void test_sender() {
        sender.sender("支付订单号-s："+System.currentTimeMillis());
    }

    @RequestMapping("topic1")
    @ResponseBody
    public void flow_sender() {
        sender.senderFlow("支付订单号-推送消息(匹配规则为flowExchange)："+System.currentTimeMillis());
    }

    @RequestMapping("topic2")
    @ResponseBody
    public void gee_sender(String s) {
        sender.senderGee("支付订单号-推送消息(匹配规则为flowExchange)："+System.currentTimeMillis()+s);
    }
}
