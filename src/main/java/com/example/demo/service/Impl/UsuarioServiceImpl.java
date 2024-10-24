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
	public boolean validarUsuario(UsuarioEntity usuarioFormulario) {
		// TODO Auto-generated method stub
		UsuarioEntity usuarioEncontrado = usuarioRepository
				.findByEmail(usuarioFormulario.getEmail());
		if(usuarioEncontrado == null) {
			return false;
		}
		if(!Utilitarios.checkPassword(usuarioFormulario.getPassword() , usuarioEncontrado.getPassword())) {
			return false;
		}
		
		return true;
	}

	@Override
	public UsuarioEntity buscarUsuarioPorEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(email);
	}
	
	
	



}
