package com.samir.controller;

import java.sql.*;
import java.util.*;

import com.samir.dao.ProductoDAO;
import com.samir.factory.ConnectionFactory;
import com.samir.modelo.Categoria;
import com.samir.modelo.Producto;

public class ProductoController {
	
	private ProductoDAO productoDAO;
	
	public ProductoController() {
		var factory = new ConnectionFactory();
        this.productoDAO = new ProductoDAO(factory.recuperaConexion());
	}
	
	public void modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		this.productoDAO.modificar(nombre, descripcion, cantidad, id);	
	}

	public int eliminar(Integer id) throws SQLException {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try(statement){
				statement.setInt(1, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		}
	}

	public List<Producto> listar(){
		return productoDAO.listar();
	}
	
	public List<Producto> listar(Categoria categoria){
		return productoDAO.listar(categoria.getId());
	}
	

	public void guardar(Producto producto, Integer categoriaId){
		
		producto.setCategoriaId(categoriaId);

		productoDAO.guardar(producto);

	}

}
