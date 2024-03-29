package com.systemdesign.notificationservice.repository;

import com.systemdesign.notificationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDataDao extends JpaRepository<User,Long> {
    @Query("SELECT u.emailId from User u where u.userId = :userId")
    String findEmailByUserId(@Param("userId")String userId);

}
