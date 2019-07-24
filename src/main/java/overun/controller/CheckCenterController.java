package overun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import overun.socker.WebSocketServer;

import java.io.IOException;

@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public void pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
