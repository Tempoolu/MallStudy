package com.example.demo.service;

import com.example.demo.nosql.document.MemberReadHistory;

import java.util.List;

public interface MemberReadHistoryService {

    // 生成浏览记录
    int create(MemberReadHistory memberReadHistory);

    // 批量删除浏览记录
    int delete(List<String> ids);

    // 获取用户浏览历史记录
    List<MemberReadHistory> list(Long memberId);
}
