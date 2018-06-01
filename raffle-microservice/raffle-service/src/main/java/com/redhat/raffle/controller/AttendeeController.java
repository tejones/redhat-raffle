package com.redhat.raffle.controller;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.internal.compiler.impl.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.raffle.bean.Attendee;
import com.redhat.raffle.repository.RaffleRepository;

@RestController
public class AttendeeController {
	
	public static String EMPTY_STRING = "";

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
		String originalScannedvalue = scannedValue;
		String newScannedValue = scannedValue.replace("z_1", EMPTY_STRING);
		newScannedValue = newScannedValue.replace("_", EMPTY_STRING);
		String[] parsedScannedValue = newScannedValue.split("\\|");
		String lastName = parsedScannedValue[1];
		String firstName = parsedScannedValue[2];
		String uid = parsedScannedValue[0];

		Attendee attendee = new Attendee();
		attendee.setId(uid);
		attendee.setFirstName(firstName);
		attendee.setLastName(lastName);
		attendee.setScannedValue(originalScannedvalue);

		return raffleRepository.save(attendee);
	}
}