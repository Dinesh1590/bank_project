package com.bankproject.bankofAp.repository;

import com.bankproject.bankofAp.entity.LoginEntity;
import com.bankproject.bankofAp.entity.SignUpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends CrudRepository<LoginEntity, String> {

    LoginEntity findByEmail(String email);
    LoginEntity findByUsername(String username);


}

