package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.ProductoEntity;
import com.example.demo.model.UsuarioEntity;
import com.example.demo.model.CategoriaEntity;
import com.example.demo.service.CategoriaService;

import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.Impl.PdfService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PdfService pdfService;
	
	
	@GetMapping("/lista")
	public String listarProducto(Model model,HttpSession session) {
		if (session.getAttribute("usuario")== null) {
			return"redirect:/";
		}
		
		String correoSesion = session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEncontrado = usuarioService.buscarUsuarioPorEmail(
				correoSesion);
				
		model.addAttribute("foto",usuarioEncontrado.getUrlImagen());
		model.addAttribute("nombreUsuario", usuarioEncontrado.getNombre()); 
	
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
	
	
	@GetMapping("/generar_pdf")
    public ResponseEntity<InputStreamResource>generarPDf(HttpSession sesion) throws IOException{
		if (sesion.getAttribute("usuario") == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    }
		List<ProductoEntity> listaProductos = productoService.listarProducto();
		
		Map<String, Object> datosPdf = new HashMap<>();
	    datosPdf.put("productos", listaProductos);
		
	    ByteArrayInputStream pdfBytes = pdfService.generarPdf("template_pdf", datosPdf);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "inline; filename=productos.pdf");
	    
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(new InputStreamResource(pdfBytes));
	}







	
}
