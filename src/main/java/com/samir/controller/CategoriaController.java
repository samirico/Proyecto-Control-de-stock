package com.samir.controller;

import java.util.ArrayList;
import java.util.List;

import com.samir.dao.CategoriaDAO;
import com.samir.factory.ConnectionFactory;
import com.samir.modelo.Categoria;

public class CategoriaController {
	
	private CategoriaDAO categoriaDAO; 
	
	public CategoriaController() {
		var factory = new ConnectionFactory();
		this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion());
	}

	public List<Categoria> listar() {
		return CategoriaDAO.listar();
	}

	public List<Categoria> cargaReporte() {
	        return this.categoriaDAO.listarConProductos();
	 }

}
