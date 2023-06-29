package com.inti.models;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Entity@Table
@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Destination {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDestination;
	
	@NonNull
	private long longitude;
	
	@NonNull
	private long latitude;
	
	
	@Exclude
	@OneToMany(mappedBy = "destination")
	private List<Hotel> listeH;
	
	
	// exclude tostring
	
}
