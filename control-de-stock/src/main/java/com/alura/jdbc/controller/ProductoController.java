package com.alura.jdbc.controller;

import java.sql.*;
import java.util.*;

import com.alura.jdbc.dao.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class ProductoController {
	
	/*estamos creando en cada metodo una instancia de la clase ProductoDAO, y esta bien pero no es lo mejor
	 * y siguiendo con la refactorizacion, lo mejor seria crear una variable de la clase ProductoDAO como atributo
	 * y luego inicializarla en el constructor, asi no hay errores y todos pueden acceder a ella*/
	private ProductoDAO productoDAO;
	
	public ProductoController() {
		//productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
		var factory = new ConnectionFactory();
        this.productoDAO = new ProductoDAO(factory.recuperaConexion());
	}
	/*Tambien con el tema de throw SQLExcepcion, no esta bien mandar excepcion a otras clases, lo mejor es 
	 * tratarla dentro de la misma directamente por lo que iremos a la clase que manda esa excepcion 
	 * que seria ConnectionFactory y trataremos la excepcion ahi directamente asi ya no tendriamos mas problemas*/

	//***public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
	public void modificar(String nombre, String descripcion, Integer cantidad, Integer id) 
	/*throws SQLException*/ {
		//Connection con = new ConnectionFactory().recuperaConexion();
		//***final Connection con = new ConnectionFactory().recuperaConexion();
		//***try(con){
			//Statement statement = con.createStatement();
			/*statement.execute("UPDATE PRODUCTO SET "
					+ "NOMBRE = '" + nombre + "'"
					+ ", DESCRIPCION = '" + descripcion + "'"
					+ ", CANTIDAD = " + cantidad
					+ " WHERE ID = " + id);*/
			
				
			/*PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
					+ "NOMBRE = ? "
					+ ", DESCRIPCION = ?"
					+ ", CANTIDAD = ?"
					+ "WHERE ID = ?");*/
//***final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
//***					+ "NOMBRE = ? "
//***				+ ", DESCRIPCION = ?"
//***					+ ", CANTIDAD = ?"
//***					+ "WHERE ID = ?");
//***			try(statement){
//***				statement.setString(1, nombre);
//***				statement.setString(2, descripcion);
//***				statement.setInt(3, cantidad);
//***				statement.setInt(4, id);
//***				
//***				statement.execute();
//***				
//***				
//***				int updateCount = statement.getUpdateCount();
				
				//con.close();
				//statement.close();
				
				//***return updateCount;
		
				this.productoDAO.modificar(nombre, descripcion, cantidad, id);
				
	}

	public int eliminar(Integer id) throws SQLException {
		//Realizamos la conexion, usando el metodo
		/*De esta manera ya estaria eliminando datos de la base de datos aunque en el curso lo hacen de esta manera: 
		Connection con = new ConnectionFactory().recuperaConexion();							!
		Statement statement = con.createStatement();											!		
		statement.execute("DELETE FROM PRODUCTO WHERE ID = " + id);  */						  //!	
		//Connection con = new ConnectionFactory().recuperaConexion();
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			//Statement statement = con.createStatement();
			//statement.execute("DELETE FROM PRODUCTO WHERE ID = " + id);
			//PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			try(statement){
				statement.setInt(1, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				//con.close();
				//statement.close();
				
				return updateCount;
			}
		}
	}

	//public List<Map<String, String>> listar() throws SQLException{
	public List<Producto> listar(){
		
		/*Realizamos la conexion: */
		/*Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
				"root",
				"crisam15!");*/ /*esto lo pasamos a la clase ConnectionFactory, que es donde tendremos
				todo lo referente a la conexion a base de datos, en su lugar creamos una instancia del metodo
				que crea la conexion: */
		//Connection con = new ConnectionFactory().recuperaConexion();//esto mismo hacemos en la clase PruebaConexion
		//***final Connection con = new ConnectionFactory().recuperaConexion();
		
		/*los comandos como SELECT, INSERT, ENTRE OTROS en sql, son considerados como statement en java
		 * y en java tenemos el siguiente metodo que usa una clase de tipo statement: 
		 * cuidado con esto(muchos errores) funciono con java.mysql.Statement aunque tambien 
		 * se puede importar directamente*/
		//Statement statement = con.createStatement();
		//boolean resultado = statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		//statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
		
		//***try(con){
			
			//PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			//**final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			
			//***try(statement){
				//***statement.execute();
				
				/*para tener el resultado del statement, tenemos el siguiente metodo(getResultSet) que ademas
				 * nos devuelve un objeto de tipo ResultSet: */
				//***ResultSet resultSet = statement.getResultSet();
				/*ResultSet es un listado de resultado(List) y siempre que lo leeremos tenemos que saber
				 * cual es el proximo elemento desde la fila actual*/
				
				/*los statement devuelven listados por lo que creamos una lista de tipo Map para poder trabajar*/
				//***List<Map<String, String>> resultado = new ArrayList<>();
				
				 /*como hablamos de loop, podemos iterar este resultado*/
				//***while(resultSet.next()){
					//***Map<String, String> fila = new HashMap<>();
					//***fila.put("ID", String.valueOf(resultSet.getInt("ID")));
					//***fila.put("NOMBRE", String.valueOf(resultSet.getString("NOMBRE")));
					//***fila.put("DESCRIPCION", String.valueOf(resultSet.getString("DESCRIPCION")));
					//***fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
					
					//***resultado.add(fila);
					/*para cada registro de resultSet, transferimos la informacion al objeto de tipo Map y lo
					 * agregamos a un listado(List) que seria la variable resultado*/
				//***}
				
				/*es importante el uso de SQLException, en este caso lanzamos la exception aqui
				 * (throws SQLException) y la capturamos en cargarTabla que usa este metodo(listar)*/
				

				//con.close();
				//statement.close();
				
				//***return resultado; /*cerramos conexion y devolvemos la informacion que esta contenido en el List*/
				
		return productoDAO.listar();
				
	}
	
	
	/*Creamos un metodo que retorne una lista de producto y mandamos categoria por parametro
		ya que este sera el identificador para saber que productos pertenecen a una categoria*/
	public List<Producto> listar(Categoria categoria){
		//enviamos el id de la categoria porque queremos buscar todos los productos que pertenezcan a la misma categoria
		return productoDAO.listar(categoria.getId());
	}
	
	

	/*lo primero cambiaremos el tipo de dato(object es muy general)*/
    //public void guardar(Object producto) {
    //public void guardar(Map<String, String> producto) throws SQLException {
	/*como ya eliminamos los throw SQLExcepcion de las clases principales podemos quitarlo de aqui sin problemas*/
	public void guardar(Producto producto, Integer categoriaId){
    	/*Esto esta bien pero da un error de tipo SQL Injection(codigo que puede afectar nuestro programa a traves
    	 * de los textField) ya no crearemos un statement sino prepararemos para que pueda tratar esto
    	 * 
    	//Creamos instancia de la conexion a base de datos y creamos el statement(procesar sentencias(comandos) SQL)
    	Connection con = new ConnectionFactory().recuperaConexion();    	
    	
    	Statement statement = con.createStatement(); //lo almacenamos en una variable de tipo Statement
    	
    	//Insertamos igual que en mysql worckbench pero en java usando statement con el metodo execute: 
    	statement.execute("INSERT INTO PRODUCTO (nombre, descripcion, cantidad) " 
    			+ " VALUES('" +producto.get("NOMBRE") + "', '"
    			+ producto.get("DESCRIPCION") + "',"
    			+ producto.get("CANTIDAD") + ")", 
    			Statement.RETURN_GENERATED_KEYS);//esta constante de clase sirve para el ID(ya que es una clave autogenerada
    			//esta constante de clase genera una clave por lo tanto la podemos capturar: 
    	ResultSet resultSet = statement.getGeneratedKeys(); 
    	/*esto retorna un objeto tipo ResultSet, por lo tanto lo capturamos tambien, el resulset contiene el listado de id
    	que fueron generados ya que en un insert podemos insertar mas de 1 valor, por lo tanto se puede iterar: *
    	
    	*/
    	
    	/*Esta nueva forma preparedStatement tiene ventajas: nos proteje de riesgo de sufrir ataques de SQLInjection 
    	 * y tiene mejor legivilidad de codigo, mejoramos tambien los demas metodos*/
    	//String nombre = producto.get("NOMBRE");
    	//String descripcion = producto.get("DESCRIPCION");
    	//Integer cantidad = Integer.valueOf(producto.get("CANTIDAD"));
	/*String nombre = producto.getNombre();
	String descripcion = producto.getDescripcion();
	Integer cantidad = producto.getCantidad();esto ya no seria necesario pues el producto pasa directo como argumento*/
		
    	//vamos a establecer un limite por registro de 50 unidades
    //Integer maximaCantidad = 50;
    	
    	//Connection con = new ConnectionFactory().recuperaConexion();
    //**final Connection con = new ConnectionFactory().recuperaConexion();
    	//**try (con){
	    	//Para Manejar las transacciones de una base de datos:
	    //**con.setAutoCommit(false);
	    	//declaramos la query: 
	    	/*PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
	    			+ "(nombre, descripcion, cantidad)"
	    			+ "VALUES (?, ?, ?)", 
	    			Statement.RETURN_GENERATED_KEYS);*/
	    //**final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
	    		//**+ "(nombre, descripcion, cantidad)"
	    		//**+ "VALUES (?, ?, ?)", 
	    		//**Statement.RETURN_GENERATED_KEYS);
	    	
	    	/*la logica de el negocio es por ejemplo en una transaccion de banco, si intentas depositar en una cuenta
	    	 * dinero en 2 partes por decirlo asi y a mitad de la transaccion ocurre un error lo que deberia pasar 
	    	 * es que no se termine la transaccion y el dinero sea devuelto, por eso el uso de los commit y rollback
	    	 * para que en caso de error a mitad de registro por ejemplo de los limites que hemos puesto, ya no se 
	    	 * registre una parte sino o se registra todo o no se registra nada
	    	 * commit es registrar todo 
	    	 * rollback es devolver todo*/
	    	//**try (statement){
	    		
	    		/*
	    		do {
	        		/*como estamos estableciendo un limite, necesitamos saber el valor minimo entre la cantidad y la 
	        		maximaCantidad n que definimos como 50 y mandamos el minimo valor como argumento(a guardar en DB)*
	        		int cantidadParaGuardar = Math.min(cantidad, maximaCantidad);
	        		//ejecutaRegistro(nombre, descripcion, cantidad, statement);
	        	//ejecutaRegistro(nombre, descripcion, cantidadParaGuardar, statement);
	        		ejecutaRegistro(producto, statement);
	        		/*como hemos establecido limite y no podemos perder, por ejemplo si tenemos 70 cucharas, en el primer
	        		 * registro se guardaran 50 y en un segundo registro se tendrian que guardar el restante(20)*
	        		cantidad -= maximaCantidad;
	        	}while(cantidad > 0);
	    		*/
	        	
	    	//**ejecutaRegistro(producto, statement);
	    		
	        //**con.commit();
	        	
	    //**}catch (Exception e) {
	    	//**con.rollback();
	    //**}
	    	//statement.close();
	    	//con.close();
		//**}
	
		
		producto.setCategoriaId(categoriaId);
		/*aparte de cambiar aqui, tambien hay que añadir un nuevo espacio en el statement de guardar, revisar*/
		
		
		//ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
		productoDAO.guardar(producto);
		/*DAO es otro patron de diseño, su finalidad es tener un objeto que tiene como responsabilidad acceder
		 * a la base de datos y realizar las operaciones necesarias sobre una identidad(db, tabla)
		 * no necesariamente es a bases de datos, sino a cualqier fuente de datos ya que la idea es centralizar
		 * las operaciones para evitar replicacion de codigo, esto es una capa de PERSISTENCIA(REVISAR)*/
	
	}

	

	/*private void ejecutaRegistro(String nombre, String descripcion, Integer cantidad, PreparedStatement statement)
			throws SQLException {*/
	//como este tambien recibia nombre, descripcion y cantidad y estos ya estan siendo trabajados en la clase
	//Producto entonces mandamos como parametro directamente un Producto
	//**private void ejecutaRegistro(Producto producto, PreparedStatement statement)
		//**	throws SQLException {
		//Refactorizando el codigo, trasladaremos esta parte de codigo a un metodo: 
    	//seteamos los atributos de la query siguiendo el orden de los ?
	//statement.setString(1, nombre);
	//statement.setString(2, descripcion);
	//statement.setInt(3, cantidad);
		//**statement.setString(1, producto.getNombre());
		//**statement.setString(2, producto.getDescripcion());
		//**statement.setInt(3, producto.getCantidad());
    	
    	//**statement.execute();
    	
    	/*esto es conocido como try with resources y nos ayuda a que ya no tengamos que estar preocupandonos por 
    	 * abrir o cerrar los recursos ejemplo el resulSet deberia de cerrarse y con esto ya no nos preocupamos
    	 * de eso, tambien se puede hacer para la conexion, para el statement para ya no tener que preocuparnos
    	 * en poner siempre el close();*/
    	//**try(ResultSet resultSet = statement.getGeneratedKeys();){
    		//**while(resultSet.next()) {
        		/*El ID devuelve un INT por eso el metodo getInt y se manda 1 ya que en la tabla el ID es la columna 1*/
        		//Se puede dejar asi y se agrega directo  resultSet.getInt(1);    pero en el curso lo hacen con syso tambien: 
        		//System.out.println(String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
    			//seteamos el id que genera el resulSet generatedkey
    			//**producto.setId(resultSet.getInt(1));
    			//en la clase Producto sobreEscribimos el metodo toString
    			//**System.out.println(String.format("Fue insertado el producto de %s", producto));
        	//**}
    	//**}
    	/*De esa manera se hacia en la version 7 de java, ahora desde la version 9 tambien se puede crear una variable
    	 * final y declararla en el try directamente, de esta forma: 
    	 * final ResultSet resultSet = statement.getGeneratedKeys();
    	 * try(resulSet){
    	 * 	 //TODO
    	 * }
    	 * */
    	
	//**}

}
