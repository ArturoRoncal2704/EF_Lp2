package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<ProductoEntity> listarProducto() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

	@Override
	public void crearProducto(ProductoEntity productoEntity) {
		// TODO Auto-generated method stub
		productoRepository.save(productoEntity);
	}

	@Override
	public void actualizarProducto(Integer id, ProductoEntity productoEntity) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if(productoEncontrado == null) {
			throw new RuntimeException("Producto no Encontrado");
		}
		try {
			productoEncontrado.setNombreProducto(productoEntity.getNombreProducto());
			productoEncontrado.setPrecio(productoEntity.getPrecio());
			productoEncontrado.setStock(productoEntity.getStock());
			productoEncontrado.setCategoriaEntity(productoEntity.getCategoriaEntity());
			
		productoRepository.save(productoEncontrado);

		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar");		}
	}

	@Override
	public void eliminarProducto(Integer id) {
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if(productoEncontrado == null) {
			throw new RuntimeException("Empleado no encontrado");
		}
		productoRepository.delete(productoEncontrado);
		
	}

	
}
