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
	private int petID;
	private String reasonOfVisit;

	public Appointment(String date, String time, int ownerID, int petID, String reasonOfVisit) {
		this.date = date;
		this.time = time;
		this.ownerID = ownerID;
		this.petID = petID;
		this.reasonOfVisit = reasonOfVisit;
	}
}
