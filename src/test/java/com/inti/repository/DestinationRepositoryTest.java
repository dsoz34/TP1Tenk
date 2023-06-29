package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.models.Destination;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DestinationRepositoryTest {
	
	@Autowired
	IDestinationRepository idr;
	
	@BeforeAll
	public static void debut()
	{
		
	}
	
	@BeforeEach
	public void setUp()
	{
		
	}
	
	@Test
	public void saveDestinationTest()
	{
		// GIVEN
		Destination d1 = new Destination(123456, 456789);
		
		// WHEN
		Destination destinationSaved = idr.save(d1);
		
		// THEN
		assertThat(destinationSaved).isNotNull();
		assertThat(destinationSaved.getIdDestination()).isGreaterThan(0);
	}
	
	@Test
	public void saveDestinationWithArgsFacultativesTest() {
		
		//GIVEN
		Destination d1 = new Destination();
		d1.setLatitude(45);
		
		//WHEN 
		Destination destinationsaved = idr.save(d1);
		
		//THEN
		assertThat(destinationsaved).isNotNull();
		assertThat(destinationsaved.getIdDestination()).isGreaterThan(0);
		assertThat(destinationsaved.getLatitude()).isEqualTo(45);
		assertThat(destinationsaved.getLongitude()).isEqualTo(0);
		
		
	}
	
	

	
	
	@Test
	public void deleteDestinationTest()
	{
		// GIVEN
		Destination d1 = new Destination(123456, 456789);
		Destination destinationSaved = idr.save(d1);
		
		// WHEN
		idr.delete(destinationSaved);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> idr.getReferenceById(d1.getIdDestination()));
	}
	
	@Test
	public void deleteDestinationNullTest()
	{
		// GIVEN
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> idr.delete(null));
	}
	
	@Test
	public void updateDestinationTest()
	{
		// GIVEN
		Destination d1 = new Destination(4, 5);
		Destination destinationSaved = idr.save(d1);
				
		// WHEN
		destinationSaved.setLatitude(2);
		Destination destinationModified = idr.save(destinationSaved);
		
		// THEN
		assertThat(destinationModified).isNotNull();
		assertThat(destinationModified.getLatitude()).isEqualTo(2);
	}
	
	
	@Test
	public void updateDestinationNullTest()
	{
		// GIVEN
		Destination d1 = null;
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> d1.setLatitude(4));
	}
	
	@Test
	public void getDestinationTest()
	{
		// GIVEN
		Destination d1 = new Destination(5, 4);
		Destination destinationSaved = idr.save(d1);
				
		// WHEN
		Destination destinationGet = idr.getReferenceById(destinationSaved.getIdDestination());
		
		// THEN
		assertThat(destinationGet).isNotNull();
		assertThat(destinationGet.getLatitude()).isEqualTo(4);
		assertThat(destinationGet).isEqualTo(destinationSaved);
	}

	
	@Test
	public void getAllDestinationTest()
	{
		// GIVEN
		Destination d1 = new Destination(1, 4);
		Destination d2 = new Destination(0, 3);
		Destination destinationSaved1 = idr.save(d1);
		Destination destinationSaved2 = idr.save(d2);
				
		// WHEN
		List<Destination> listeD = idr.findAll();
		
		// THEN
		assertThat(listeD).isNotEmpty(); 
		assertThat(listeD).hasSize(2);
		assertThat(listeD.get(0).getClass()).hasSameClassAs(Destination.class);
	}
	
	@Test
	public void getDestinationListeVideTest()
	{
		// GIVEN
				
		// WHEN 
		List<Destination> listeD = idr.findAll();
		
		// THEN
		assertThat(listeD).isEmpty();
		assertThat(listeD).hasSize(0);
	}
	
	


}
