package com.example.demo.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.example.demo.direct.DirectSender;
import com.example.demo.fanout.FanoutSender;
import com.example.demo.simple.SimpleSender;
import com.example.demo.topic.TopicSender;
import com.example.demo.work.WorkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkSender workSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private DirectSender directSender;
    @Autowired
    private TopicSender topicSender;

    @GetMapping("/simple")
    public void simpleTest() {
        for (int i=0; i<10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
    }

    @GetMapping("/work")
    public void workTest() {
        for (int i=0; i<10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
    }

    @GetMapping("/fanout")
    public void fanoutTest() {
        for(int i=0;i<10;i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
    }

    @GetMapping("/direct")
    public void direct() {
        for(int i=0;i<10;i++) {
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
    }

    @GetMapping("/topic")
    public void topic() {
        for(int i=0;i<10;i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
    }

}
