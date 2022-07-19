package marjorieTeu.barkpointment.beans;

import java.sql.Time;
import java.sql.Date;

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
	private Date apptDate;
	private Time apptTime;
	private int ownerID;
	private int petID;

}
