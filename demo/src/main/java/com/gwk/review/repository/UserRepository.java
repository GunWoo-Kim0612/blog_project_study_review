package com.gwk.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwk.review.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
