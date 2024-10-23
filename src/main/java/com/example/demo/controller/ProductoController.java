package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.ProductoEntity;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import com.example.demo.model.CategoriaEntity;


@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/lista")
	public String listarProducto(Model model) {
		List<ProductoEntity> listarProducto = productoService.listarProducto();
		model.addAttribute("listaprod", listarProducto);
		return "lista_productos";
	}
	
	@GetMapping("/registrar_producto")
	public String mostrarRegistrarProducto(Model model) {
		List<CategoriaEntity>listaCategoria = categoriaService.obtenerTodasCategorias();
		model.addAttribute("listaCategoria",listaCategoria);
		model.addAttribute("producto", new ProductoEntity());
		return "registrar_producto";
	}	
	@PostMapping("/registrar_producto")
	public String registrarProducto(@ModelAttribute("producto")ProductoEntity productoEntity,
			Model model) {
		productoService.crearProducto(productoEntity);
		return "redirect:/lista";  
	}
	@GetMapping("/detalle_producto/{id}")
	public String verDetalle(Model model, @PathVariable("id") Integer id) {
	    ProductoEntity producto = productoService.buscarProductoPorId(id);
	    model.addAttribute("producto",producto);
	    return "detalle_producto";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmpleado(Model model, @PathVariable("id") Integer id) {
		productoService.eliminarProducto(id);
		return "redirect:/lista";
	}
	@GetMapping("/editar_producto/{id}")
	public String mostrarActualizar(@PathVariable("id")Integer id, Model model) {
		ProductoEntity producto = productoService.buscarProductoPorId(id);
		List<CategoriaEntity>listaCategoria = categoriaService.obtenerTodasCategorias();
		model.addAttribute("listaCategoria",listaCategoria);
		model.addAttribute("producto", producto);
		return "editar_producto";
	}
	
	@PostMapping("/editar_producto/{id}")
	public String actualizarProducto(@PathVariable("id")Integer id, 
			@ModelAttribute("empleado") ProductoEntity producto, Model model) {
		productoService.actualizarProducto(id, producto);
		return "redirect:/lista";
	}
}
