package com.management.travel_management.repository;

import com.management.travel_management.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {


    @Query(value = "SELECT * FROM login WHERE emp_no = :empNo", nativeQuery = true)
    Login findUser(@Param("empNo") int empNo);
}
