package com.springrestful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrestful.domain.User;

@Repository
public interface UserJPARepo extends JpaRepository<User, Integer> {
}
