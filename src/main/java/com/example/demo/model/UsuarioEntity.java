package com.example.demo.model;




import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="tb_usuario")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

	@Id
	@Column(name = "email", nullable = false, length =60)
	private String email;
	
	@Column(name = "nombre", length = 60, nullable = false)
	private String nombre;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "apellido", length = 60, nullable = false)
	private String apellido;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_nacimiento")
	private Date fechaNaci;
	
	@Column(name = "url_imagen")
	private String urlImagen;
	
	
}
