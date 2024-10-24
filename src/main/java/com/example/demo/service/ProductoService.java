package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProductoEntity;

public interface ProductoService {
	
	List<ProductoEntity>listarProducto();
	ProductoEntity buscarProductoPorId(Integer id);
	void crearProducto(ProductoEntity productoEntity);
	void actualizarProducto(Integer id, ProductoEntity productoEntity);
	void eliminarProducto(Integer id);
	List<ProductoEntity> buscarProductoPorNombre(String nombre);
}
