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

import com.inti.models.Hotel;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HotelRepositoryTest {
	
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
	public void saveHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("palace", 5);
		
		// WHEN
		Hotel hotelSaved = ihr.save(h1);
		
		// THEN
		assertThat(hotelSaved).isNotNull();
		assertThat(hotelSaved.getIdHotel()).isGreaterThan(0);
	}
	
	@Test
	public void saveClientWithArgsFacultativesTest() {
		
		//GIVEN
		Hotel h1 = new Hotel();
		h1.setNom("lalu");
		
		//WHEN 
		Hotel hotelsaved = ihr.save(h1);
		
		//THEN
		assertThat(hotelsaved).isNotNull();
		assertThat(hotelsaved.getIdHotel()).isGreaterThan(0);
		assertThat(hotelsaved.getNom()).isEqualTo("lalu");
		assertThat(hotelsaved.getNbEtoile()).isEqualTo(0);
		
		
	}
	
	

	
	@Test
	public void saveHotelExceptionParamObligatoireTest()
	{
		// GIVEN
		Hotel h1 = new Hotel();
		h1.setNbEtoile(55);
		
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.save(h1));
	}
	

	

	
	@Test
	public void deleteHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("place", 5);
		Hotel hotelSaved = ihr.save(h1);
		
		// WHEN
		ihr.delete(hotelSaved);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.getReferenceById(h1.getIdHotel()));
	}
	
	@Test
	public void deleteHotelNullTest()
	{
		// GIVEN
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.delete(null));
	}
	
	@Test
	public void updateHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("lalu", 5);
		Hotel hotelSaved = ihr.save(h1);
				
		// WHEN
		hotelSaved.setNbEtoile(2);
		Hotel hotelModified = ihr.save(hotelSaved);
		
		// THEN
		assertThat(hotelModified).isNotNull();
		assertThat(hotelModified.getNbEtoile()).isEqualTo(2);
	}
	
	
	@Test
	public void updateHotelNullTest()
	{
		// GIVEN
		Hotel h1 = null;
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> h1.setNbEtoile(4));
	}
	
	@Test
	public void getHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("fiesta", 4);
		Hotel hotelSaved = ihr.save(h1);
				
		// WHEN
		Hotel hotelGet = ihr.getReferenceById(hotelSaved.getIdHotel());
		
		// THEN
		assertThat(hotelGet).isNotNull();
		assertThat(hotelGet.getNom()).isEqualTo("fiesta");
		assertThat(hotelGet).isEqualTo(hotelSaved);
	}

	
	@Test
	public void getAllHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("quatre", 4);
		Hotel h2 = new Hotel("trois", 3);
		Hotel hotelSaved1 = ihr.save(h1);
		Hotel hotelSaved2 = ihr.save(h2);
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isNotEmpty();  // c quoi ca
		assertThat(listeH).hasSize(2);
		assertThat(listeH.get(0).getClass()).hasSameClassAs(Hotel.class);
	}
	
	@Test
	public void getHotelListeVideTest()
	{
		// GIVEN   
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isEmpty();
		assertThat(listeH).hasSize(0);
	}


}
