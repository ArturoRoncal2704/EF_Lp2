package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import pe.com.cibertec.Proyecto_biblioteca.model.entity.UsuarioEntity;
import pe.com.cibertec.Proyecto_biblioteca.service.UsuarioService;


@Controller
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@GetMapping("/registrar_usuario")
	public String mostrarRegistrarUsuario(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "registrar_usuario";
	}
	
	@PostMapping("/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioEntity, Model model) {
		usuarioService.crearUsuario(usuarioEntity);
		return "redirect:/";
	}

	@GetMapping("/")
	public String mostrarLogin(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}
	@PostMapping("/login")
	public String login(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario,
			Model model, HttpSession session) {
		
		boolean usuarioValidado = usuarioService.validarUsuario(usuarioFormulario);
		if(usuarioValidado) {
			session.setAttribute("usuario", usuarioFormulario.getEmail());
			return "redirect:/menu";
		}
		
		model.addAttribute("loginInvalido","No existe el usuario");
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}
	
}

