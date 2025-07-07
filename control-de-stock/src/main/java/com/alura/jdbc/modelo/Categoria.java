package com.alura.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	/*Empezamos creando esta clase modelo Categoria ya que tenemos una nueva tabla(categoria) en la DB.
	 * RECORDAR QUE: controller se encarga de la conexión entre la view y la fuente de datos que contiene el modelo
	 * 
	 * Creamos los atributos que tienen que ser igual a los campos que tenemos en la tabla: .*/
	private Integer id;
	private String nombre; /*ahora vamos revisando cada clase de los otros paquetes y vamos corrigiendo
	 						empezando en ControlDeStockFrame que cambiaremos la List de object por List de Categoria*/
	
	//Creamos una referencia de Producto dentro de Categoria
	private List<Producto> productos;
	
	//Creamos el constructor que se necesita en la clase CategoriaController en el metodo listar para obtener id y nombre
	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	/*si no sobreEscribimos el metodo toString lo que nos mostrara seran las referencias de los objetos, no los nombres*/
	@Override
	public String toString() {
		return this.nombre;
	}

	public Integer getId() {
		return this.id;
	}

	//Creamos el metodo para relacionar la Categoria con Producto
	public void agregar(Producto producto) {
		if(this.productos == null) {
			this.productos = new ArrayList<>();
		}
		
		this.productos.add(producto);
		
	}

	public List<Producto> getProductos() {
		return this.productos;
	}
	
}
