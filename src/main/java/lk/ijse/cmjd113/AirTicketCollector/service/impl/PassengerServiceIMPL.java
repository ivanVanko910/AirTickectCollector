package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import lk.ijse.cmjd113.AirTicketCollector.dao.BookingDao;
import lk.ijse.cmjd113.AirTicketCollector.dao.PassengerDao;
import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;
import lk.ijse.cmjd113.AirTicketCollector.entities.PassengerEntity;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import lk.ijse.cmjd113.AirTicketCollector.util.IDGenerate;
import lk.ijse.cmjd113.AirTicketCollector.util.Mapper;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("ServiceOne")
@Primary
@RequiredArgsConstructor
@Transactional
public class PassengerServiceIMPL implements PassengerService {

    private final PassengerDao passengerDao;
    private final BookingDao bookingDao;
    private final Mapper mapper;

    @Override
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        passengerDTO.setPassengerId(IDGenerate.passengerId());
        PassengerEntity passengerEntity = mapper.toPassengerEntity(passengerDTO);

        if (passengerDTO.getBookingId() != null) {
            BookingEntity bookingEntity = bookingDao.findById(passengerDTO.getBookingId())
                    .orElseThrow(() -> new RuntimeException("Booking not found"));
            passengerEntity.setBookingId(bookingEntity);
        }

        PassengerEntity savedPassenger = passengerDao.save(passengerEntity);
        return convertToDTO(savedPassenger);
    }

    @Override
    public PassengerDTO getSelectedPassenger(String passengerId) {
        PassengerEntity passengerEntity = passengerDao.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        return convertToDTO(passengerEntity);
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerDao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deletePassenger(String passengerId) {
        PassengerEntity passengerEntity = passengerDao.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passengerDao.delete(passengerEntity);
    }

    @Override
    public void updatePassenger(String passengerId, PassengerDTO passengerDTO) {
        PassengerEntity existingPassenger = passengerDao.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existingPassenger.setFullName(passengerDTO.getFullName());
        existingPassenger.setAge(passengerDTO.getAge());
        existingPassenger.setGender(passengerDTO.getGender());
        existingPassenger.setSeatNumber(passengerDTO.getSeatNumber());

        if (passengerDTO.getBookingId() != null) {
            BookingEntity bookingEntity = bookingDao.findById(passengerDTO.getBookingId())
                    .orElseThrow(() -> new RuntimeException("Booking not found"));
            existingPassenger.setBookingId(bookingEntity);
        }

        passengerDao.save(existingPassenger);
    }

    private PassengerDTO convertToDTO(PassengerEntity passengerEntity) {
        PassengerDTO passengerDTO = mapper.toPassengerDTO(passengerEntity);
        if (passengerEntity.getBookingId() != null) {
            passengerDTO.setBookingId(passengerEntity.getBookingId().getBookingId());
        }
        return passengerDTO;
    }
}
