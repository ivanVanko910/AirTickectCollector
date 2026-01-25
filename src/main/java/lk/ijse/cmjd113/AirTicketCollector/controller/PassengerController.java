package lk.ijse.cmjd113.AirTicketCollector.controller;

import lk.ijse.cmjd113.AirTicketCollector.dto.PassengerDTO;
import lk.ijse.cmjd113.AirTicketCollector.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassengerDTO> savePassenger(@RequestBody PassengerDTO passengerDTO) {
        return new  ResponseEntity<>(passengerService.savePassenger(passengerDTO),HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassengerDTO> getSelectedPassenger(@PathVariable("id") String passengerId) {
        var passengerDTO = new PassengerDTO(
                passengerId,
                "Isuru Udayanga",
                28,
                "Male",
                1,
                "BKG-001"
        );
        return new ResponseEntity<>(passengerDTO,HttpStatus.OK);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
       List<PassengerDTO> allPassengers = List.of(
               new PassengerDTO("PAS-001", "John Doe", 34, "Male", 15, "BKG-002"),
               new PassengerDTO("PAS-002", "Jane Smith", 32, "Female", 16, "BKG-003")
       );
       return new ResponseEntity<>(allPassengers,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Void>  deletePassenger(@PathVariable("id") String passengerId) {
        System.out.println("Deleted passenger Id is "+passengerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePassenger(@PathVariable("id") String passengerId,@RequestBody PassengerDTO passengerDTO) {
        passengerDTO.setPassengerId(passengerId);
        System.out.println("Updated ID is : "+passengerId);
        System.out.println("Updated Passenger is : "+passengerDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
