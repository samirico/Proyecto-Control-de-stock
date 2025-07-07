package com.samir.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.samir.factory.ConnectionFactory;

public class PruebPoolDeConexiones {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		for(int i = 0; i < 20; i++) {
			Connection conexion = connectionFactory.recuperaConexion();
			System.out.println("Abriendo la conexion de numero " + (i+1));
		}
		
	}
	
}
