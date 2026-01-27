package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import lk.ijse.cmjd113.AirTicketCollector.dao.AirportDao;
import lk.ijse.cmjd113.AirTicketCollector.dao.FlightDao;
import lk.ijse.cmjd113.AirTicketCollector.dto.FlightDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.AirportEntity;
import lk.ijse.cmjd113.AirTicketCollector.entities.FlightEntity;
import lk.ijse.cmjd113.AirTicketCollector.service.FlightService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceIMPL implements FlightService {

    private final FlightDao flightDao;
    private final AirportDao airportDao;
    private final Mapper mapper;

    @Override
    public FlightDTO saveFlight(FlightDTO flightDTO) {
        flightDTO.setFlightNo(IDGenerate.flightNo());
        FlightEntity flightEntity = mapper.toFlightEntity(flightDTO);

        if (flightDTO.getDepartureAirport() != null) {
            AirportEntity depAirport = airportDao.findById(flightDTO.getDepartureAirport())
                    .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
            flightEntity.setDepartureAirport(depAirport);
        }

        if (flightDTO.getArrivalAirport() != null) {
            AirportEntity arrAirport = airportDao.findById(flightDTO.getArrivalAirport())
                    .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
            flightEntity.setArrivalAirport(arrAirport);
        }

        FlightEntity savedFlight = flightDao.save(flightEntity);
        return convertToDTO(savedFlight);
    }

    @Override
    public void updateFlight(String flightId, FlightDTO flightDTO) {
        FlightEntity existingFlight = flightDao.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existingFlight.setDepartureTime(flightDTO.getDepartureTime());
        existingFlight.setArrivalTime(flightDTO.getArrivalTime());
        existingFlight.setTotalSeats(flightDTO.getTotalSeats());
        existingFlight.setAvailableSeats(flightDTO.getAvailableSeats());
        existingFlight.setBaseFare(flightDTO.getBaseFare());
        existingFlight.setStatus(flightDTO.getStatus());

        if (flightDTO.getDepartureAirport() != null) {
            AirportEntity depAirport = airportDao.findById(flightDTO.getDepartureAirport())
                    .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
            existingFlight.setDepartureAirport(depAirport);
        }

        if (flightDTO.getArrivalAirport() != null) {
            AirportEntity arrAirport = airportDao.findById(flightDTO.getArrivalAirport())
                    .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
            existingFlight.setArrivalAirport(arrAirport);
        }

        flightDao.save(existingFlight);
    }

    @Override
    public void deleteFlight(String flightId) {
        FlightEntity flightEntity = flightDao.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flightDao.delete(flightEntity);
    }

    @Override
    public FlightDTO getFlight(String flightId) {
        FlightEntity flightEntity = flightDao.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        return convertToDTO(flightEntity);
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightDao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private FlightDTO convertToDTO(FlightEntity flightEntity) {
        FlightDTO flightDTO = mapper.toFlightDTO(flightEntity);
        if (flightEntity.getDepartureAirport() != null) {
            flightDTO.setDepartureAirport(flightEntity.getDepartureAirport().getAirportId());
        }
        if (flightEntity.getArrivalAirport() != null) {
            flightDTO.setArrivalAirport(flightEntity.getArrivalAirport().getAirportId());
        }
        return flightDTO;
    }
}
