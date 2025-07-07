package com.alura.jdbc.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.alura.jdbc.controller.CategoriaController;
import com.alura.jdbc.controller.ProductoController;

public class ReporteFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaReporte;
    private DefaultTableModel modelo;

    private CategoriaController categoriaController;
    //Creamos un atributo que es una referencia a la clase ProductoController: 
    //private ProductoController productoController;
    
    public ReporteFrame(ControlDeStockFrame controlDeStockFrame) {
        super("Reporte de produtos del stock");

        this.categoriaController = new CategoriaController();
        //inicializamos categoriaController en el constructor
        //this.productoController = new ProductoController();
        
        Container container = getContentPane();
        setLayout(null);

        tablaReporte = new JTable();
        tablaReporte.setBounds(0, 0, 600, 400);
        container.add(tablaReporte);

        modelo = (DefaultTableModel) tablaReporte.getModel();
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");

        cargaReporte();

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(controlDeStockFrame);
    }

    private void cargaReporte() {
        var contenido = categoriaController.cargaReporte();
        
        // TODO
        contenido.forEach(categoria -> {
        	modelo.addRow(new Object[] {categoria});
        	
        	//almacenamos en la variable productos el resultado del metodo listar
        	//var productos = this.productoController.listar(categoria);
        	var productos = categoria.getProductos();
        	
        	productos.forEach(producto -> modelo.addRow(
        			new Object[] {
        					"", //una columna vacia para que no salgan los datos debajo del nombre de la categoria
        					producto.getNombre(), //luego ya en la 2da columna agregamos los nombres 
        					producto.getCantidad() //y por ultimo las cantidades
        			}
        			));
        	
        });
    }

}
