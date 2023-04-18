package com.CGMspringboot.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "user_info")
public abstract class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL, mappedBy = "userInfo")
    private AppUser appUser;
}
