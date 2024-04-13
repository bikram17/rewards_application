package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Busybox.rewards.application.model.Permission;

public interface permissionRepository extends JpaRepository<Permission, Long>{

}
