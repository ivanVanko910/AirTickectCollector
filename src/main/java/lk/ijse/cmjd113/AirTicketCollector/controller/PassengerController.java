package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {
    private final PassengerService passengerService;
    // Constructor with @Qualifier to specify the service implementation
    public PassengerController(@Qualifier ("ServiceOne") PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    // Save a passenger
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePassenger(@RequestBody PassengerDTO passengerDTO) {
        passengerService.savePassenger(passengerDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }
    // Get Selected Passenger
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassengerDTO> getSelectedPassenger(@PathVariable ("id") String passengerId) {
       return new ResponseEntity<>(passengerService.getSelectedPassenger(passengerId), HttpStatus.OK);
    }
    // Get All Passengers
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        return new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
    }
    // Delete a passenger
    @DeleteMapping("/{id}")
    public ResponseEntity <Void>  deletePassenger(@PathVariable ("id") String passengerId) {
        passengerService.deletePassenger(passengerId);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Update a passenger
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassenger(@PathVariable ("id") String passengerId, @RequestBody PassengerDTO passengerDTO) {
      passengerService.updatePassenger(passengerId,passengerDTO);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
