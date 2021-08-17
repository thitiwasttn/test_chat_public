package com.thitiwas.test.chat.testchat.repository;

import com.thitiwas.test.chat.testchat.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
