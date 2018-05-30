package com.redhat.raffle.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.raffle.bean.Attendee;
import com.redhat.raffle.repository.RaffleRepository;

@RestController
public class AttendeeController {

	@Autowired
	private RaffleRepository raffleRepository;

	@GetMapping("/raffle/attendees")
	public List<Attendee> getAttendees() {
		return raffleRepository.findAll();
	}
	
	@GetMapping("/raffle/randomizedattendees")
	public List<Attendee> getRandomizedAttendees() {
		List<Attendee> attendees = raffleRepository.findAll();
		Collections.shuffle(attendees);
		return attendees;
	}

	@GetMapping("/raffle/scanAttendee/{scannedValue}")
	public Attendee scanAttendee(@PathVariable String scannedValue) {
		String[] parsedScannedValue = scannedValue.split("\\|");
		String lastName = parsedScannedValue[1];
		String firstName = parsedScannedValue[2];
		String uid = parsedScannedValue[0];

		Attendee attendee = new Attendee();
		attendee.setId(uid);
		attendee.setFirstName(firstName);
		attendee.setLastName(lastName);
		attendee.setScannedValue(scannedValue);

		return raffleRepository.save(attendee);
	}
}