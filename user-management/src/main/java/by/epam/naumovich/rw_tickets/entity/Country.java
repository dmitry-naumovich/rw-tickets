package by.epam.naumovich.rw_tickets.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This simple entity bean class describes the country.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

	private String code;
	private String name;
}
