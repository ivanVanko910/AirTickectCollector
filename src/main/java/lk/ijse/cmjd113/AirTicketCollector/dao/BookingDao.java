package lk.ijse.cmjd113.AirTicketCollector.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;

@Repository
public interface BookingDao extends JpaRepository<BookingEntity, String> {}
