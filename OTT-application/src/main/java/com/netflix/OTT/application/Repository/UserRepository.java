package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u")
    public List<User> getAllUSers();

    @Query(value = "SELECT u FROM User u where u.user_id=:id")
    public User getUserById(@Param ("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (email, password, subs_id, phone) VALUES (:#{#user.email}, :#{#user.password}, " +
            ":#{#user.subscriptions.subs_id}, :#{#user.phone})", nativeQuery = true)
    public void insertUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user  SET email = :#{#user.email}, password = :#{#user.password}, " +
            "subs_id = :#{#user.subscriptions.subs_id}, phone= :#{user.phone} WHERE user_id = :#{#user.user_id}", nativeQuery = true)
    public void updateUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user  WHERE user_id = :id", nativeQuery = true)
    public void deleteUser(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update user set subs_id= :subsid where user_id= :userid", nativeQuery = true)
    void updateSubscriptionforUser(@Param("userid")Integer userid, @Param("subsid")Integer subsid);

    @Transactional
    @Modifying
    @Query(value = "update user set phone= :phone where user_id= :userid", nativeQuery = true)
    void updatePhoneForUSers(@Param("userid") Integer userId, @Param("phone") String phone);
}
