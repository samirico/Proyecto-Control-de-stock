package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//public class CreaConexion {  y esto para refactorizar(convencion) tiene que estar en su propio paquete factory
public class ConnectionFactory{
	
	//Creamos un atributo privado de tipo DataSource
	private DataSource datasource;
	
	//Metodo constructor
	public ConnectionFactory() {
		//creamos una variable de la clase de c3p0(ComboPooledDataSource)
		var pooledDataSource = new ComboPooledDataSource(); 
		//el pool de conexiones sirve para abrir y reutilizar multiples conexiones para no tener problemas
		//usamos los metodos de la clase para setear URL, Usuario, Contraseña: 
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("crisam15!");
		
		//seteamos la cantidad maxima de conexiones para el pool 
		pooledDataSource.setMaxPoolSize(10);
		
		/*asignamos el poolDataSource al atributo de tipo DataSource: */
		this.datasource = pooledDataSource;
	}
	
	
	/*Como vamos a tratar la SQLExcepcion desde su lugar de origen, la trataremos aqui, lo que haremos es 
	 * dejar de mandar la excepc*/
/*creamos el metodo recuperaConexion de tipo Connection asi como la variable (con) que esta en otra clase*/
	//public Connection recuperaConexion() throws SQLException {
	public Connection recuperaConexion() {/*Esto es considerado como un patron de diseño
	llamado factory method que tiene como objetivo encapsular todo el codigo de creacion de un objeto especifico
	centralizando la logica en un solo punto*
	
		return DriverManager.getConnection(
				"jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"crisam15!");*/
		
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
