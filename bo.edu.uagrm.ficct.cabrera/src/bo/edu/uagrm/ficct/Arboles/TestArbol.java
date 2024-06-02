/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

import java.util.Scanner;

/**
 *
 * @author Asrock
 */
public class TestArbol {

	public static void main(String[] args) throws OrdenInvalidoExcepcion, NewException {
		// TODO code application logic here
		IArbolBusqueda<Integer, String> arbolPrueba = new ArbolBinarioBusqueda<>();
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Elija un tipo de arbol (ABB, AVL, AMV): ");
		
		String tipoArbol = entrada.next();
		switch (tipoArbol) {
			case "ABB":
				arbolPrueba = new ArbolBinarioBusqueda<>();
				break;
			case "AVL":
				arbolPrueba = new AVL<>();
				break;
			case "AMV":
				arbolPrueba = new ArbolMViasBusqueda<>(4);
				break;
			default:
				System.out.println("Tipo de arbol invalido. Usando arbol AVL");
				arbolPrueba = new AVL<>();
				break;
		}
		//IArbolBusqueda<Integer, String> arbolPrueba = new ArbolBinarioBusqueda<>();
//		arbolPrueba.insertar( 50, "Azul");
//		arbolPrueba.insertar(78, "Naranja");
//		arbolPrueba.insertar(44, "Zapallo");
//		arbolPrueba.insertar(30, "Jeans");
//		arbolPrueba.insertar(44, "Amarillo");
//		arbolPrueba.insertar(20, "Negro"); //
		/*arbolPrueba.insertar(25, "Café");
		arbolPrueba.insertar(24, "Camisa");
		arbolPrueba.insertar(23, "Mesa");
		arbolPrueba.insertar(28, "TV");
		arbolPrueba.insertar(74, "Banana");
		arbolPrueba.insertar(120, "Arroz");
		arbolPrueba.insertar(35, "Blusa");
		arbolPrueba.insertar(111, "Zapato");
		arbolPrueba.insertar(90, "Portatil");
		arbolPrueba.insertar(81, "Llaves");
		arbolPrueba.insertar(71, "Mouse");
		arbolPrueba.insertar(100, "Cables");
		arbolPrueba.insertar(89, "Termico");
		arbolPrueba.insertar(51, "Gato");*/
	
                arbolPrueba.insertarRecursivo(50, "Azul");
                arbolPrueba.insertarRecursivo(60, "Azul");
		//System.out.println(arbolPrueba);
		//arbolPrueba.mostrar(arbolPrueba);
		System.out.println("Recorrido en InOrden:   "+ arbolPrueba.recorridoEnInOrden());
		System.out.println("Recorrido en PreOrden:  "+ arbolPrueba.recorridoEnPreOrden());
		System.out.println("Recorrido en PostOrden: "+ arbolPrueba.recorridoEnPostOrden());
		System.out.println("Recorrido en Por Niveles: "+ arbolPrueba.recorridoPorNiveles());
		System.out.println();
		arbolPrueba.mostrar();
		
		/*System.out.println("Arbol despues de eliminar");
		
		arbolPrueba.eliminar(120);
		System.out.println("Recorrido en InOrden:   "+ arbolPrueba.recorridoEnInOrden());
		System.out.println("Recorrido en PreOrden:   "+ arbolPrueba.recorridoEnPreOrden());
		System.out.println("Recorrido en PostOrden: "+ arbolPrueba.recorridoEnPostOrden());
		System.out.println("Recorrido en Por Niveles: "+ arbolPrueba.recorridoPorNiveles());
		*/
		//System.out.println(arbolPrueba.cantidadDeHojas());
		
		//arbolPrueba.mostrar();
	}

}
