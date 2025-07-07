package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class CategoriaDAO {

	 private static Connection con;
	
	public CategoriaDAO(Connection con) {
		this.con = con;
	}

	public static List<Categoria> listar() {
		
		//Creamos una List de Categoria instanciando una nueva arrayList: 
				List<Categoria> resultado = new ArrayList<>();
				
				//Preparamos el statement que nos ayudara a ejecuutar comandos SQL en java: 
				//este obviamente va dentro de un try catch porque puede ocurrir que no encuentre dicha tabla u otras cosas
				try {
					
					//final para mandarlo en el try y hacer un try with resources
					final PreparedStatement statement = con.prepareStatement(
							"SELECT ID, NOMBRE FROM CATEGORIA");
					
					try(statement){
						/*esto devuelve un boolean, aunque hay una mejor forma de hacerlo y nos retorna 
						un acceso directo */ //statement.execute();: 
						
						//esta es la otra forma y lo almacenamos en un objeto resultSet de tipo ResultSet 
						final ResultSet resultSet = statement.executeQuery(); //final para mandarlo en try with resources
						
						try (resultSet){
							while(resultSet.next()) {
								/*Cada que se llama al metodo listar se crea un nuevo objeto de Categoria que se almacena en
								*categoria y luego se añade en la Lista de resultado y este se retorna a la llamada al metodo*/
								var categoria = new Categoria(resultSet.getInt("ID"), 
										resultSet.getString("NOMBRE"));
								
								resultado.add(categoria);
							}
						};
						
					}
					
				} catch (SQLException e) {
					//Encapsulamos la excepcion: 
					throw new RuntimeException(e);
				}
				
				return resultado;
				
	}

	public List<Categoria> listarConProductos() {
		
		/*El recurso INNER JOIN nos posibilita unificar 2 tablas que tienen columnas en comun y para nuestro caso 
		 * la tabla producto tiene la columna categoria_id, que hace referencia a la columna id de la tabla
		 * categoria
		 * Aunque para hacer el JOIN es necesario identificar las tablas para saber de que tabla es la columna 
		 * que estamos haciendo la referencia
		 * */
		
		List<Categoria> resultado = new ArrayList<>();
		
		try {
			
			//final para mandarlo en el try y hacer un try with resources
			final PreparedStatement statement = con.prepareStatement(
					/*le ponemos un alias a la categoria para poder identificarla y usarla dentro del JOIN
					 * ademas del id y nombre de categoria, añadimos lo que mostraremos que seria el nombre
					 * y cantidad de producto y tambien el id de producto para poder identificar*/
					"SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD FROM CATEGORIA C "
					+ "INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID");
			//Despues del ON es una condicion de la llave foranea 
			
			try(statement){
				/*esto devuelve un boolean, aunque hay una mejor forma de hacerlo y nos retorna 
				un acceso directo */ //statement.execute();: 
				
				//esta es la otra forma y lo almacenamos en un objeto resultSet de tipo ResultSet 
				final ResultSet resultSet = statement.executeQuery(); //final para mandarlo en try with resources
				
				try (resultSet){
					while(resultSet.next()) {
						/*Cada que se llama al metodo listar se crea un nuevo objeto de Categoria que se almacena en
						*categoria y luego se añade en la Lista de resultado y este se retorna a la llamada al metodo*/
						Integer categoriaId = resultSet.getInt("ID");
						String categoriaNombre = resultSet.getString("NOMBRE");
						var categoria = resultado //tomamos resultado que es un List de categoria
								.stream() //transformamos resultado en stream para hacer un filter(filtro(comparacion))
		//osea buscamos si en el listado ya tenemos una categoria con este id de la variable categoriaId						
								.filter(cat -> cat.getId().equals(categoriaId))
		//con findAny si ya existe la categoria, entonces lo almacenamos en la variable  var categoria, orElseGet
								//si aun no existe, si es la primera vez de la categoriaId entonces creamos
								//un nuevo objeto de categoria y lo agregamos a nuestro listado de resultado
								.findAny().orElseGet(() -> {
									Categoria cat = new Categoria(categoriaId, 
											categoriaNombre);
									
									resultado.add(cat);
									
									return cat;
								});
					
					/*fuera del lambda pero dentro del while, creamos una variable de tipo Producuto que almacenara
					  los campos de Producto: */
						Producto producto = new Producto(resultSet.getInt("P.ID"), 
								resultSet.getString("P.NOMBRE"), 
								resultSet.getInt("P.CANTIDAD"));
						
						categoria.agregar(producto);
						
					}
				};
				
			}
			
		} catch (SQLException e) {
			//Encapsulamos la excepcion: 
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

}
