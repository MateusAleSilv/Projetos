package com.mktech.gerenciadorsalareuniao.repository;

import com.mktech.gerenciadorsalareuniao.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


}
