package lk.ijse.cmjd113.AirTicketCollector.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.cmjd113.AirTicketCollector.entities.FlightEntity;

@Repository
public interface FlightDao extends JpaRepository<FlightEntity, String> {}
