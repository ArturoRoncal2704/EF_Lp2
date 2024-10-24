package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_producto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer id;
	
	@Column(name = "nombre_producto",nullable = false, length = 45 )
	private String nombreProducto;
	
	@Column(name = "precio",nullable = false, length = 45)
	private Double precio;
	
	@Column(name = "stock",nullable = false, length = 45)
	private Integer Stock;
	
	@ManyToOne
	@JoinColumn(name ="categoria_id", nullable = false)
	private CategoriaEntity categoriaEntity;
}
