package com.inti.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity@Table
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Voyageur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVoyageur;
	
	@NonNull
	private String nom;
	
	@NonNull
	private String prenom;
	
	@NonNull
	private int age;
	
	
	// exclude tostring
	
}
