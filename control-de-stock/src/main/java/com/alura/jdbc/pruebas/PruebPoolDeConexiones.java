package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebPoolDeConexiones {

	public static void main(String[] args) throws SQLException {
		
		//creamos un objeto de la clase ConnectionFactory llamando al constructor vacio que se encarga de las conexiones
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		//con ayuda de este for simularemos multiples conexiones en este caso 20 conexiones
		//HAY QUE TENER EN CUENTA QUE HEMOS PUESTO UN LIMITE DE 10 CONEXIONES
		for(int i = 0; i < 20; i++) {
			//inicializamos la conexion llamando al metodo para abrir
			Connection conexion = connectionFactory.recuperaConexion();
			System.out.println("Abriendo la conexion de numero " + (i+1));
			/*lo que pasara es que gracias al limite del pool de conexiones(10) solo se crearan 20 conexiones
			 * pero solo se abriran 10 y se mantendra atento, en lo que una conexion este disponible, otra
			 * requisicion ocupara su lugar y asi sucesivamente. Es como una cola pero las cosas pasan muy rapido  
			 * por lo que no sera notorio*/
		}
		
	}
	
}
