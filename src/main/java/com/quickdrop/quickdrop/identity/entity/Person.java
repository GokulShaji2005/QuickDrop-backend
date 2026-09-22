package com.quickdrop.quickdrop.identity.entity;

import com.quickdrop.quickdrop.common.entity.Auditable;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String mobile;

    public long getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
