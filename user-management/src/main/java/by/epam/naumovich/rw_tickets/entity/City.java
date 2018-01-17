package by.epam.naumovich.rw_tickets.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This simple entity bean class describes the city.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
@NoArgsConstructor // Empty constructor may be used by JAXB, for instance
@Builder
public class City {

	private String code;
	private String country;
	private String name;
}
