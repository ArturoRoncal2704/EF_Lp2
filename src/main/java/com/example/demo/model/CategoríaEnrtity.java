package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_categoria")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoríaEnrtity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id",nullable = false)
	private Integer catId;
	
	@Column(name = "nombre_categoria", nullable =false, length = 45)
	private String nombreCat;
 
}
