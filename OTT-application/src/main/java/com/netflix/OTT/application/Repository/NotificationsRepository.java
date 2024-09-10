package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {

    @Query(value = "select n from Notifications n")
    List<Notifications> getAllNotifications();

    @Query(value = "select n from Notifications n where notif_id= :id")
    Notifications getNotificationById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO notifications (message, profile_id) VALUES (:message, :userProfile)", nativeQuery = true)
    void notifcationinsert(@Param("message") String message, @Param("userProfile") Integer userProfileId);


    @Transactional
    @Modifying
    @Query(value = "update notifications set message= :msg, update_at= :updateat where notif_id= :nid", nativeQuery = true)
    void updateNotifications(@Param("msg") String msg, @Param("updateat")LocalDateTime updateat, @Param("nid")Integer nid);


    @Transactional
    @Modifying
    @Query(value = "update notifications set is_watched= :iswatched, update_at= :updateat where notif_id= :nid", nativeQuery = true)
    void updateiswatched(@Param("iswatched") boolean b, @Param("updateat")LocalDateTime updateat, @Param("nid")Integer nid);

    @Transactional
    @Modifying
    @Query(value = "delete from notifications where notif_id= :nid", nativeQuery = true)
    void deleteNotifications(@Param("nid") Integer nid);

}

