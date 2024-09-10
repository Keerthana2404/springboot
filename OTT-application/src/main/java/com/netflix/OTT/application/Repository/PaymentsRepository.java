package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

    @Query(value = "select s from Payments s")
    List<Payments> getAllPayments();

    @Query(value = "select s from Payments s where s.payment_id= :pid")
    Payments getPaymentById(@Param("pid") Integer id);

    @Query(value = "select s from Payments s where s.user_payments= :uid")
    List<Payments> getPaymentByUserId(@Param("uid") Integer uid);

    @Transactional
    @Modifying
    @Query(value = "insert into payments (pay_type, amount, status, user_id, subs_id) values (" +
            ":type, :amount, :status, :userid, :subsid)", nativeQuery = true)
    void insertPayment(@Param("type") String type, @Param("amount") Integer amount,
                       @Param("status")String status, @Param("userid")Integer userid,
                       @Param("subsid")Integer subsid);

    @Transactional
    @Modifying
    @Query(value = "update payments set pay_type= :type, status= :status," +
            " user_id= :userid where payment_id= :pid", nativeQuery = true)
    void updatePayments(@Param("type") String type,
                        @Param("status")String status, @Param("userid")Integer userid,
                        @Param("pid")Integer pid);


    @Transactional
    @Modifying
    @Query(value = "delete from payments where payment_id= :pid", nativeQuery = true)
    void deletePayments(@Param("pid") Integer pid);


}
