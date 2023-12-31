package com.example.demo.direct;

import cn.hutool.core.thread.ThreadUtil;
import com.example.demo.fanout.FanoutSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class DirectReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutSender.class);

    @RabbitListener(queues = "#{directQueue1.name}")
    public void receive1(String in) {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{directQueue2.name}")
    public void receive2(String in) {
        receive(in, 2);
    }

    private void receive(String in, int receiver) {
        StopWatch watch = new StopWatch();
        watch.start();
        LOGGER.info("instance {} [x] Received '{}'", receiver, in);
        doWork(in);
        watch.stop();
        LOGGER.info("instance {} [x] Done in {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char ch: in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);  // 点越多，线程休息更久
            }
        }
    }
}
