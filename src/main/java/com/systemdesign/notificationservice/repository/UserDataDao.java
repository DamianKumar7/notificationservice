package com.systemdesign.notificationservice.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDataDao {

    @PersistenceContext
    public EntityManager entityManager;

    public String getUserEmailFromId(String userId){
        String queryString = "select u.email_id from users u where u.user_id = :userId";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("userId",userId);
        String result = (String) query.getSingleResult();
        return result;
    }

}
