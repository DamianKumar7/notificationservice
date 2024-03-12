package com.systemdesign.notificationservice.repository;

import com.systemdesign.notificationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u.emailId FROM User u WHERE u.userId = :userId")
    String findEmailByUserId(@Param("userId") String userId);
}
