package com.sparta.newspeed.repository;

import com.sparta.newspeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
