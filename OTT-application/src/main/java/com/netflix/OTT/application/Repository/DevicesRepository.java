package com.netflix.OTT.application.Repository;

import com.netflix.OTT.application.Entity.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface DevicesRepository extends JpaRepository<Devices, Integer> {


    @Query(value = "select s from Devices s")
    List<Devices> getAllDeviceById();

    @Query(value = "select s from Devices s where device_id= :did")
    Devices getDeviceById(@Param("did")Integer did);

    @Transactional
    @Modifying
    @Query(value = "insert into devices (device_type, is_active, user_id) values (:devicetype, :isactive, :userid)", nativeQuery = true)
    void insertDevices(@Param("devicetype") String devicetype, @Param("isactive")Boolean isactive, @Param("userid") Integer uesrid);


    @Transactional
    @Modifying
    @Query(value = "delete from devices where device_id= :oldestDevice", nativeQuery = true)
    void deleteDevice(@Param("oldestDevice") Integer oldestDevice);

}
