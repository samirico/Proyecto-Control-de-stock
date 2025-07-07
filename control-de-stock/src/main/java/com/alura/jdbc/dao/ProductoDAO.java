package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {

	private Connection con;

	public ProductoDAO(Connection con) {
		this.con = con;
	}

	// Como esta sera la clase que trabaje directamente con la clase Producto,
	// traeremos los metodos que acceden a ella
	// como guardar, listar, etc . Esto tambien con la finalidad de refactorizar
	// codigo(mas legible)

	/*
	 * como queremos quitar el throw SQLExcepcion de la mayor cantidad de clases
	 * posibles, lo eliminamos de aqui tambien y en su lugar como ya esta dentro de
	 * un try catch y no queremos que se siga expandiendo el throw encapsularemos la
	 * excepcion en un RunTimeExcepcion
	 */
	// public void guardar(Producto producto) throws SQLException {
	public void guardar(Producto producto) {
		// ya no es necesario crear una nueva conexion pues la propia clase lo hace en
		// el constructor
		// final Connection con = new ConnectionFactory().recuperaConexion();

		try {
			//con.setAutoCommit(false);

			final PreparedStatement statement = con.prepareStatement(
				//RECORDAR, los nombres de los campos despues de producto deben coincidir con los de la DB, revisar
					"INSERT INTO PRODUCTO (nombre, descripcion, cantidad, categoria_id) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getCategoriaId());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet){
					while(resultSet.next()) {
						producto.setId(resultSet.getInt(1));
						
						System.out.println(String.format("Fue insertado el producto: %s", producto));
					}
				}
				//con.commit();
			}
		} catch (Exception e) {
			// System.out.println("ROOLBACK de la transaccion");
			// con.rollback();
			throw new RuntimeException(e);
		}

	}

	/*private void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());

		statement.execute();

		try (ResultSet resultSet = statement.getGeneratedKeys();) {
			while (resultSet.next()) {
				producto.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue insertado el producto de %s", producto));
			}
		}
	}*/

	/*
	public List<Map<String, String>> listar() {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			
			try(statement){
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				List<Map<String, String>> resultado = new ArrayList<>();

				while(resultSet.next()){
					Map<String, String> fila = new HashMap<>();
					fila.put("ID", String.valueOf(resultSet.getInt("ID")));
					fila.put("NOMBRE", String.valueOf(resultSet.getString("NOMBRE")));
					fila.put("DESCRIPCION", String.valueOf(resultSet.getString("DESCRIPCION")));
					fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
					
					resultado.add(fila);
				}
				
				return resultado;
				
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	*/
	
	public List<Producto> listar() {
		//final Connection con = new ConnectionFactory().recuperaConexion();
		List<Producto> resultado = new ArrayList<>();
		try{
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();

				try(resultSet){
					while(resultSet.next()){
						Producto fila = new Producto(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("DESCRIPCION"),
								resultSet.getInt("CANTIDAD"));
						
						resultado.add(fila);
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
		
	}

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		//final Connection con = new ConnectionFactory().recuperaConexion();
		try{
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
					+ "NOMBRE = ? "
					+ ", DESCRIPCION = ?"
					+ ", CANTIDAD = ?"
					+ " WHERE ID = ?");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Producto> listar(Integer categoriaId) {
		//repetimos toda la logica del metodo listar sin parametros, pero con algunos cambios
		List<Producto> resultado = new ArrayList<>();
		try{
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD "
					+ "FROM PRODUCTO"
					+ "WHERE CATEGORIA_ID = ?");
			
			try(statement){
				
				statement.setInt(1, categoriaId);
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();

				try(resultSet){
					while(resultSet.next()){
						Producto fila = new Producto(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("DESCRIPCION"),
								resultSet.getInt("CANTIDAD"));
						
						resultado.add(fila);
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
}
