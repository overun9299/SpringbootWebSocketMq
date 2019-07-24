package overun.socker;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: WebSocketServerPool
 * @Description:
 * @author: 薏米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/7/23 10:23
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
public class WebSocketServerPool {


    /** 定一日志 */
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerPool.class);

    /** 定义线程安全map */
    private static Map<String ,WebSocketServer> monitorWebSocketMap = new ConcurrentHashMap();

    /** 存储socket实例可以用线程安全的map也可以用线程安全的array */
    /** private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>(); */


    /**
     * 存放socket实例
     * @param identif 唯一标识
     * @param socketServer
     */
    public static void put(String identif , WebSocketServer socketServer) {
        if (StringUtils.isNotBlank(identif)) {
            if (!monitorWebSocketMap.containsKey(identif) && socketServer!=null) {
                logger.warn("放入socket池中identif："+identif);
                monitorWebSocketMap.put(identif ,socketServer );
            }
        }
    }

    /**
     * 移除socket实例
     * @param identif
     */
    public static void remove(String identif) {
        monitorWebSocketMap.remove(identif);
    }

    /**
     * 发送消息
     * @param identif
     * @param message
     */
    public static void sendMessage(String identif , String message) {

        try {
            for (Map.Entry<String ,WebSocketServer> map : monitorWebSocketMap.entrySet()) {
                if (map.getKey().contains(identif)) {
                    map.getValue().sendMessage(message);
                }
            }
        } catch (Exception e) {
            logger.error("给浏览器端推送监控信息异常：" + e.getMessage());
        }
    }

}
