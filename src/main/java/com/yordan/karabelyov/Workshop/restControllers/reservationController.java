//package com.yordan.karabelyov.Workshop.restControllers;
//
//import com.yordan.karabelyov.Workshop.service.ReservationService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@RestController
//@RequestMapping("/new-reservation")
//public class reservationController {
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    ReservationService reservationService;
//
//
//    @GetMapping("/{date}")
//    public boolean getReservationAvailability(@PathVariable("date") String date) throws ParseException {
//        logger.info("Date received => {}", date);
//        Date converted = new SimpleDateFormat("dd.MM.yyyy").parse(date);
//        logger.info("Date converted => {}", converted);
//        return reservationService.isDateAvailable(converted);
////
//    }
//
//}
