package com.inti.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Entity@Table
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHotel;
	
	@NonNull
	@Column(nullable = false)
	private String nom;
	
	@NonNull
	private int nbEtoile;
	
	
	@Exclude
	@ManyToOne
	@JoinColumn(name="id_destination")
	private Destination destination;
	// exclude tostring
	
	
	@Exclude
	@OneToMany(mappedBy = "hotel")
	private List<Reservation> listR;
	// exclude tostring
	
	
	@Exclude
	@OneToMany(mappedBy = "hotel")
	private List<Avis> listA;
	// exclude tostring
	
}
