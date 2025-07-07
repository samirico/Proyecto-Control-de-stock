package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.dao.CategoriaDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;

public class CategoriaController {
	
	/*Esta clase nos sirve para realizar la conexion entre la view y la fuente de datos que contiene el modelo
	 * entonces haremos lo mismo que en la clase productoController que sera iniciar una conexion desde el 
	 * pool de conexiones*/
	
	//Creamos un objeto de tipo CategoriaDAO: 
	private CategoriaDAO categoriaDAO; 
	
	//Creamos el constructor que inicializara la conexion: 
	public CategoriaController() {
		var factory = new ConnectionFactory(); //almacenamos en factory una nueva ConnectionFactory
		this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion()); //le asignamos al objeto la conexion
		//this.categoriaDAO = new CategoriaDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Categoria> listar() {
		// TODO
		/*la clase CategoriaDAO es la que contiene la logica entonces retornamos el listado como un metodo de esta clas*/
		//return new ArrayList<>();
		return CategoriaDAO.listar();
	}

	/*Este metodo funciona pero crea multiples querys de acceso a la DB y es una mala practica
    public List<Categoria> cargaReporte() {
        // TODO
    	/*devolvemos todas las categorias disponibles usando el metodo listar que esta arriba, el mismo que llama al
    	metodo listar() de la clase CategoriaDAO*
        return this.listar();
    }
    */
	 public List<Categoria> cargaReporte() {
	        
		 //Con este metodo lo que haremos es listar directamente las categorias con sus productos, la union de las 2 tablas
	        return this.categoriaDAO.listarConProductos();
	 }

}
