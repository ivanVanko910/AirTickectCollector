package lk.ijse.cmjd113.AirTicketCollector.controller;


import lk.ijse.cmjd113.AirTicketCollector.dto.BookingDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingDataController {

  private final BookingService bookingService;
  // Add a new booking
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void>addBooking(@RequestBody BookingDTO booking) {
    bookingService.saveBooking(booking);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  // Get a specific booking by ID
  @GetMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookingDTO>getBooking(@PathVariable String bookingId) {
    return new ResponseEntity<>(bookingService.getBooking(bookingId), HttpStatus.OK);
  }
  // Get all bookings
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public  ResponseEntity<List<BookingDTO>> getAllBookings(){
    return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
  }
  // Delete a booking by ID
  @DeleteMapping("/{bookingId}")
  public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
    bookingService.deleteBooking(bookingId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  // Update a booking by ID
  @PatchMapping("/{bookingId}")
  public ResponseEntity<Void> updateBooking(@PathVariable String bookingId,@RequestBody BookingDTO bookingData){
    bookingService.updateBooking(bookingId, bookingData);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
