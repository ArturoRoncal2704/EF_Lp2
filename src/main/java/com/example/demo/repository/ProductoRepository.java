package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.model.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer>{
	List<ProductoEntity> findByNombreProductoContainingIgnoreCase(String nombre);
;
}
