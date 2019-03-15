package com.lind.basic.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LindDemoUserRepository extends JpaRepository<LindDemoUserModel, Long> {
}
