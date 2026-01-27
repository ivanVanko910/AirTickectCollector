package lk.ijse.cmjd113.AirTicketCollector.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.cmjd113.AirTicketCollector.entities.PassengerEntity;

@Repository
public interface PassengerDao extends JpaRepository<PassengerEntity, String> {}
