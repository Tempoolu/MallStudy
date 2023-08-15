package com.example.demo.nosql.repository;

import com.example.demo.nosql.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
