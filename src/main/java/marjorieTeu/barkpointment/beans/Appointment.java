package marjorieTeu.barkpointment.beans;

import java.sql.Time;
import java.sql.Date;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class Appointment {
	private int apptID;
	private Date apptDate;
	private Time apptTime;
	private int ownerID;
	private int petID;

	public Appointment() {

	}
}
