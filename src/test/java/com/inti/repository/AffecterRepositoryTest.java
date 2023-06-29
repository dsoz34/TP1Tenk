package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.models.Destination;
import com.inti.models.Hotel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AffecterRepositoryTest {

	
	@Autowired
	IDestinationRepository idr;
	
	@Autowired
	IHotelRepository ihr;
	
	@BeforeAll
	public static void debut()
	{
		
	}
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	public void AffecterHotelToDestination()
	{
		// GIVEN
		Destination d1 = new Destination(123456, 456789);
		Hotel h1 = new Hotel("Palace", 5);
		Destination destinationSaved = idr.save(d1);
		Hotel hotelSaved = ihr.save(h1);
		
		// WHEN
		hotelSaved.setDestination(destinationSaved);
		Hotel hoteldestine = ihr.save(hotelSaved);
		Destination destinationhotelee = idr.getReferenceById(destinationSaved.getIdDestination());
		
		// THEN 
		assertThat(hoteldestine).isNotNull();
		assertThat(hoteldestine.getIdHotel()).isGreaterThan(0);
		assertThat(destinationhotelee).isNotNull();
		assertThat(destinationhotelee.getIdDestination()).isGreaterThan(0);
		
		assertThat(hoteldestine.getDestination()).isEqualTo(destinationhotelee);
//		assertThat(destinationhotelee.getListeH()).contains(hoteldestine);
			
	}
	
}
