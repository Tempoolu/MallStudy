package com.example.demo.service.impl;

import com.example.demo.nosql.document.MemberReadHistory;
import com.example.demo.nosql.repository.MemberReadHistoryRepository;
import com.example.demo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
    @Autowired
    private MemberReadHistoryRepository readHistoryRepo;

    public int create(MemberReadHistory memberReadHistory){
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        System.out.println("in service" + memberReadHistory);
        readHistoryRepo.save(memberReadHistory);
        return 1;
    }

    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for(String id:ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        readHistoryRepo.deleteAll(deleteList);
        return ids.size();
    }

    public List<MemberReadHistory> list(Long memberId) {
        return readHistoryRepo.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
