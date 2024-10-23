package com.example.demo.service.Impl;




import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Utilitarios;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	
	
	private final UsuarioRepository usuarioRepository;
	
	
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, MultipartFile foto) {
		// TODO Auto-generated method stub
		String nombreFoto = Utilitarios.guardarImagen(foto);
		usuarioEntity.setUrlImagen(nombreFoto);
		
		String passwordHash = Utilitarios.extraerHash(usuarioEntity.getPassword());
		usuarioEntity.setPassword(passwordHash);
		
		usuarioRepository.save(usuarioEntity);
		
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioEntity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	



}
