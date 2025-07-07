package com.alura.jdbc.modelo;

public class Producto {
	
	//Esta clase sera la representacion de la tabla producto que tenemos en nuestra base de datos
	
	//Creamos los campos de la tabla, que serian atributos en java
	private Integer id; 
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	
	private Integer categoriaId;
	
	//Creamos el constructor con estos 3 parametros porque son los que se usa en la clase ControlDeStokFrame 
	public Producto(String nombre, String descripcion, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		this.id = id; 
		this.nombre = nombre; 
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(Object id, Object nombre, Object descripcion, Object cantidad) {
		this.id = (Integer) id; 
		this.nombre = (String) nombre; 
		this.descripcion = (String) descripcion;
		this.cantidad = (Integer) cantidad;
	}

	//este constructor es para el apartado de ver reporte 
	public Producto(int id, String nombre, int cantidad) {
		this.id = id;
		this.nombre = nombre; 
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, descripcion: %s, cantidad: %s", 
				this.id,  //reemplazamos cada porcentaje por estos valores
				this.nombre, 
				this.descripcion, 
				this.cantidad);
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
		
	}

	public int getCategoriaId() {
		return this.categoriaId;
	}
}
