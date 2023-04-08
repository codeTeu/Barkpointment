package ca.marjorieteu.beans;

import java.util.Date;

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
	private int ownerID;
	private int petID;
	private String reasonOfVisit;
	private Date date;
	private String time;
}
