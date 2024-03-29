package ca.marjorieteu.beans;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	private int apptID;
	private String date;
	private String time;
	private int ownerID;
	private int dogID;
	private String dogName;
	private String reasonOfVisit;

	public Appointment(String date, String time, int ownerID, int dogID, String reasonOfVisit) {
		this.date = date;
		this.time = time;
		this.ownerID = ownerID;
		this.dogID = dogID;
		this.reasonOfVisit = reasonOfVisit;
	}
	
	public Appointment(String date, String time, int ownerID, int dogID, String dogName, String reasonOfVisit) {
		this.date = date;
		this.time = time;
		this.ownerID = ownerID;
		this.dogID = dogID;
		this.dogName = dogName;
		this.reasonOfVisit = reasonOfVisit;
	}
}
