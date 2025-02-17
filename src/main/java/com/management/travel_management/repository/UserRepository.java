package com.management.travel_management.repository;

import com.management.travel_management.model.Login;
import com.management.travel_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmpNo(int empNo);

    @Query(value = "select * from tour_details where tour_details.start_date >= :start_date AND tour_details.end_date <= :end_date", nativeQuery = true)
    List<User> findAllByRangeDate(@Param("start_date") Date startDate, @Param("end_date") Date endDate);

    @Query(value = "select * from tour_details where emp_no = ?1",nativeQuery = true)
    List<User> reportByEmpNo(@Param("emp_no") int empNo);


}
