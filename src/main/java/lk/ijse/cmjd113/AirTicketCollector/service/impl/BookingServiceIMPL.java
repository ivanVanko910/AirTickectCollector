package lk.ijse.cmjd113.AirTicketCollector.service.impl;

import lk.ijse.cmjd113.AirTicketCollector.dao.BookingDao;
import lk.ijse.cmjd113.AirTicketCollector.dao.FlightDao;
import lk.ijse.cmjd113.AirTicketCollector.dao.UserDao;
import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;
import lk.ijse.cmjd113.AirTicketCollector.entities.BookingEntity;
import lk.ijse.cmjd113.AirTicketCollector.entities.FlightEntity;
import lk.ijse.cmjd113.AirTicketCollector.entities.UserEntity;
import lk.ijse.cmjd113.AirTicketCollector.exception.NotFoundException;
import lk.ijse.cmjd113.AirTicketCollector.service.BookingService;
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
public class BookingServiceIMPL implements BookingService {

    private final BookingDao bookingDao;
    private final FlightDao flightDao;
    private final UserDao userDao;
    private final Mapper mapper;

    @Override
    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        bookingDTO.setBookingId(IDGenerate.bookingId());
        BookingEntity bookingEntity = mapper.toBookingEntity(bookingDTO);

        if (bookingDTO.getFlightId() != null) {
            FlightEntity flightEntity = flightDao.findById(bookingDTO.getFlightId())
                    .orElseThrow(() -> new NotFoundException("Flight not found"));
            bookingEntity.setFlightId(flightEntity);
        }

        if (bookingDTO.getUserId() != null) {
            UserEntity userEntity = userDao.findById(bookingDTO.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            bookingEntity.setUser(userEntity);
        }

        BookingEntity savedBooking = bookingDao.save(bookingEntity);
        return convertToDTO(savedBooking);
    }

    @Override
    public void updateBooking(String bookingId, BookingDTO bookingDTO) {
        BookingEntity existingBooking = bookingDao.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));

        existingBooking.setBookingDate(bookingDTO.getBookingDate());
        existingBooking.setSeatCount(bookingDTO.getSeatCount() != null ? bookingDTO.getSeatCount() : 0);
        existingBooking.setTotalAmount(bookingDTO.getTotalAmount() != null ? bookingDTO.getTotalAmount().doubleValue() : 0.0);
        existingBooking.setStatus(bookingDTO.getStatus());

        if (bookingDTO.getFlightId() != null) {
            FlightEntity flightEntity = flightDao.findById(bookingDTO.getFlightId())
                    .orElseThrow(() -> new NotFoundException("Flight not found"));
            existingBooking.setFlightId(flightEntity);
        }

        if (bookingDTO.getUserId() != null) {
            UserEntity userEntity = userDao.findById(bookingDTO.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            existingBooking.setUser(userEntity);
        }

        bookingDao.save(existingBooking);
    }

    @Override
    public void deleteBooking(String bookingId) {
        BookingEntity bookingEntity = bookingDao.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        bookingDao.delete(bookingEntity);
    }

    @Override
    public BookingDTO getBooking(String bookingId) {
        BookingEntity bookingEntity = bookingDao.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found"));
        return convertToDTO(bookingEntity);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingDao.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = mapper.toBookingDTO(bookingEntity);
        if (bookingEntity.getFlightId() != null) {
            bookingDTO.setFlightId(bookingEntity.getFlightId().getFlightNo());
        }
        if (bookingEntity.getUser() != null) {
            bookingDTO.setUserId(bookingEntity.getUser().getUserId());
        }
        return bookingDTO;
    }
}
