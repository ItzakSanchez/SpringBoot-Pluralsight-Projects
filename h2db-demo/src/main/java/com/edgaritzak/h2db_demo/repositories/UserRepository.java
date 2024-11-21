package com.edgaritzak.h2db_demo.repositories;

import org.springframework.data.repository.CrudRepository;
import com.edgaritzak.h2db_demo.models.Users;

public interface UserRepository extends CrudRepository<Users, Long>{

}
