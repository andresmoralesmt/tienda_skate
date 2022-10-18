package com.tienda.skate.controller;

import com.tienda.skate.model.Reservation;
import com.tienda.skate.model.dto.CountClient;
import com.tienda.skate.model.dto.CountStatus;
import com.tienda.skate.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/all")
    public List<Reservation> list() {
        return (List<Reservation>) service.findAll();
    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Reservation> list2() {
        return (List<Reservation>) service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> get(@PathVariable Integer id) {
        try {
            Reservation reservation = service.findById(id).get();
            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation add(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
    @GetMapping("/report-clients")
    public List<CountClient> getReportTopClients(){
        return service.getTopClients();
    }
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReportReservationsDate(@PathVariable("dateOne") String dateOne, @PathVariable("dateTwo") String dateTwo){
        return service.getReservationPeriod(dateOne, dateTwo);
    }
    @GetMapping("/report-status")
    public CountStatus getReportStatusReservations(){
        return  service.getReservationsStatus();
    }
    public ReservationController() {
    }
}
