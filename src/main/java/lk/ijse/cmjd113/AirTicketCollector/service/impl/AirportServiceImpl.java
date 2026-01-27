package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import lk.ijse.cmjd113.AirTicketCollector.dao.AirportDao;
import lk.ijse.cmjd113.AirTicketCollector.dto.AirportDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.AirportEntity;
import lk.ijse.cmjd113.AirTicketCollector.service.AirportService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportServiceImpl implements AirportService {
    private final AirportDao airportDao;
    private final Mapper mapper;

    @Override
    public void saveAirport(AirportDTO airport) {
        airport.setAirportId(IDGenerate.airportId());
        AirportEntity airportEntity = mapper.toAirportEntity(airport);
        airportDao.save(airportEntity);
    }

    @Override
    public AirportDTO getSelectedAirport(String airportId) {
        var foundAirport = airportDao.findById(airportId).orElseThrow(
            () -> new RuntimeException("No Airport Found"));
        return mapper.toAirportDTO(foundAirport);
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        return mapper.toAirportDTOList(airportDao.findAll());
    }

    @Override
    public void deleteAirport(String airportId) {
        var foundAirport = airportDao.findById(airportId).orElseThrow(
            () -> new RuntimeException("No Airport Found"));
        airportDao.delete(foundAirport);
    }

    @Override
    public void updateAirport(String airportId, AirportDTO updatedAirport) {
        AirportEntity existingAirport = airportDao.findById(airportId).orElseThrow(
            () -> new RuntimeException("No Airport Found"));
        existingAirport.setAirportCode(updatedAirport.getAirportCode());
        existingAirport.setName(updatedAirport.getName());
        existingAirport.setCity(updatedAirport.getCity());
        existingAirport.setCountry(updatedAirport.getCountry());
        airportDao.save(existingAirport);
    }
}
