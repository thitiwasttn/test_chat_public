package com.thitiwas.test.chat.testchat.repository;

import com.thitiwas.test.chat.testchat.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByAndChannelIdOrderByIdDesc(Integer channelId, Pageable pageable);
}
