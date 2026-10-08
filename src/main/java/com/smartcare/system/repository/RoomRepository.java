package com.smartcare.system.repository;

import com.smartcare.system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByIsAvailableTrue();//දැනට available කාමර පමණක් select kiriimata
}