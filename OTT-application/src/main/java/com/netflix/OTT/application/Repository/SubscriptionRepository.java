package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Integer> {

    @Query(value = "select s from Subscriptions s")
    List<Subscriptions> getAllSubscriptions();

    @Query(value = "select s from Subscriptions s where subs_id= :id")
    Subscriptions getSubscriptionsById(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO subscriptions (plan_name, max_profiles, price, valid_from, valid_till, type) " +
            "VALUES (:plan_name, :max_profile, :price, :valid_from, :valid_till, :type)", nativeQuery = true)
    void insertSubscription(@Param("plan_name") String planName,
                            @Param("max_profile") int maxProfile,
                            @Param("price") int price,
                            @Param("valid_from") LocalDateTime validFrom,
                            @Param("valid_till") LocalDateTime validTill,
                            @Param("type") int type);


    @Modifying
    @Transactional
    @Query(value = "UPDATE subscriptions SET plan_name = :plan_name, max_profiles = :max_profile, " +
            "price = :price, valid_from = :valid_from, valid_till = :valid_till, type = :type " +
            "WHERE subs_id = :subs_id", nativeQuery = true)
    void updateSubscription(@Param("subs_id") int subsId,
                            @Param("plan_name") String planName,
                            @Param("max_profile") int maxProfile,
                            @Param("price") int price,
                            @Param("valid_from") LocalDateTime validFrom,
                            @Param("valid_till") LocalDateTime validTill,
                            @Param("type") int type);

    @Transactional
    @Modifying
    @Query(value = "delete from subscriptions where subs_id= :id", nativeQuery = true)
    void deleteSubscriptions(@Param("id") Integer id);
}
