package com.bankproject.bankofAp.repository;

import com.bankproject.bankofAp.entity.SignUpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepo extends CrudRepository<SignUpEntity, String> {

   /* @Query(value = "SELECT u FROM User u WHERE u.email = :email",nativeQuery = true)
    SignUpEntity findByEmail(@Param("email") String email);
    @Query(value = "SELECT u FROM User u WHERE u.username = :username",nativeQuery = true)
    SignUpEntity findByEmail(@Param("email") String email);*/

    SignUpEntity findByEmail(String email);
    SignUpEntity findByUsername(String username);


}
