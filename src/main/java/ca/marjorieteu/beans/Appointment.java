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
	private int apptId;
	private int ownerId;
	private int petID;
	private String reasonOfVisit;
	private String date;
	private String time;
}
