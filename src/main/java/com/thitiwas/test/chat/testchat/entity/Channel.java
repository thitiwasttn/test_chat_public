package com.thitiwas.test.chat.testchat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "channel")
@Data
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;


}
