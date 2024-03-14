package com.javaweb.springbootnonjwt.repository.entity;

public class UserRoleEntity {
    private long id;
    private RoleEntity role;
    private UserEntity user;

    public UserRoleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
