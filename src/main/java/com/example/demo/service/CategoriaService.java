package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.CategoriaEntity;

@Service
public interface CategoriaService {
	
	List<CategoriaEntity>obtenerTodasCategorias();
}
