package com.example.demo.controller;

import com.example.demo.nosql.document.MemberReadHistory;
import com.example.demo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService readHistoryService;

    @PostMapping ("/create")
    public int create(@RequestBody MemberReadHistory memberReadHistory) {
        System.out.println("in controller" + memberReadHistory);

        return readHistoryService.create(memberReadHistory);
    }

    @PostMapping("/delete")
    public int delete(@RequestBody List<String> ids) {
        return readHistoryService.delete(ids);
    }

    @GetMapping("/list")
    public List<MemberReadHistory> list(@RequestParam Long memberId) {
        return readHistoryService.list(memberId);
    }
}
