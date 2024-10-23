package com.example.demo.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.UsuarioEntity;


public interface UsuarioService {
	
	void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto);
	boolean validarUsuario(UsuarioEntity usuarioEntity);
	
}
