package com.shyinyong.business.repository;

import com.shyinyong.business.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}