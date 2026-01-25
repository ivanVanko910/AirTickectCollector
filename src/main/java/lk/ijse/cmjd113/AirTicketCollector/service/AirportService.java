package lk.ijse.cmjd113.AirTicketCollector.service;

import java.util.List;

import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;

public interface AirportService {
    void saveAirport(AirportDTO airport);
    AirportDTO getSelectedAirport(String airportId);
    List<AirportDTO> getAllAirports();
    void deleteAirport(String airportId);
    void updateAirport(String airportId, AirportDTO updatedAirport);
}
