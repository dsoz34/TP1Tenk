package com.inti.models;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Entity@Table
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVoyageur;
	
	@NonNull
	private LocalDate dateReservation;  // date
	
	@NonNull
	private int nbJours;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name="id_hotel")
	private Hotel hotel;
	// exclude tostring
	// exclude tostring
	
}
