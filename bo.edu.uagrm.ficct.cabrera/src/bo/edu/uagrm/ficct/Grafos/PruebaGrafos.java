/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

/**
 *
 * @author Keila
 */
public class PruebaGrafos {

		/**
		 * @param args the command line arguments
		 */
		public static void main(String[] args) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
				// TODO code application logic here
				//Digrafo grafo1 = new Digrafo(7);
				
				/*grafo1.insertarArista(0, 2);
				grafo1.insertarArista(0, 4);
				grafo1.insertarArista(0, 1);
				grafo1.insertarArista(0, 3);
				grafo1.insertarArista(4, 2);
				grafo1.insertarArista(4, 1);
				grafo1.insertarArista(4, 3);
				grafo1.insertarArista(2, 1);
				grafo1.insertarArista(2, 3);
				grafo1.insertarArista(3, 1);
				grafo1.insertarArista(5, 6);
				grafo1.insertarArista(8, 1); */
				
				
				/*grafo1.insertarArista(0,3);
				grafo1.insertarArista(2,1);
				grafo1.insertarArista(5, 2);
				grafo1.insertarArista(3, 1);
				grafo1.insertarArista(4, 0); */
				
				
				//Grafo grafo1 = new Grafo(6);
				/*Digrafo grafo1 = new Digrafo(5);
				/*grafo1.insertarArista(0,4);
				grafo1.insertarArista(8,0);
				grafo1.insertarArista(3,4);
				grafo1.insertarArista(2,3);
				grafo1.insertarArista(1,5);
				grafo1.insertarArista(7,1);
				grafo1.insertarArista(6,1);
				grafo1.insertarArista(6,7);
				
				grafo1.insertarArista(0,1);
				grafo1.insertarArista(1,4);
				grafo1.insertarArista(1,3);
				grafo1.insertarArista(3,1);
				grafo1.insertarArista(4,2);
				grafo1.insertarArista(2,4);
				grafo1.insertarArista(2,2);*/
				
				/*
				grafo1.insertarArista(4,5);
				grafo1.insertarArista(7,6);
				grafo1.insertarArista(6,8);
				grafo1.insertarArista(7,8);
				
				grafo1.mostrarGrafo();
		//----------- CICLOS EN UN GRAFO y UN   DIGRAFO (funciona para ambos) -------------------		
				DFSCiclo unGrafo = new DFSCiclo(grafo1);
				System.out.println("\n Hay ciclos en el Grafo? " + unGrafo.encontrarCiclo() + "\n");
				
		//----------- ISLAS EN UN GRAFO y UN DIGRAFO (funciona para ambos) -------------------		
				DFSIslas Grafo = new DFSIslas(grafo1);
				int cantidad = Grafo.cantidadDeIslas();
				System.out.println(" Cuantas islas tiene el Grafo? " + cantidad+ " islas \n");
				
		//----------- MATRIZ DE CAMINOS (DIGRAFO)  -------------------		
				Warshall camino = new Warshall(grafo1);
				System.out.println("Matriz de Caminos: \n" + camino.matrizDeCaminos());  */
				
		//----------- GRAFOS PESADOS  -------------------	
		
				//GrafoPesado grafop1 = new GrafoPesado(6);
				DigrafoPesado grafop1 = new DigrafoPesado(6);
				grafop1.insertarArista(0, 1, 50);
				grafop1.insertarArista(0, 2, 10);
				grafop1.insertarArista(0, 4, 60);
				grafop1.insertarArista(0, 5, 100);
				grafop1.insertarArista(1, 3, 50);
				grafop1.insertarArista(1, 4, 15);
				grafop1.insertarArista(2, 1, 5);
				grafop1.insertarArista(3, 0, 80);
				grafop1.insertarArista(3, 5, 20);
				grafop1.insertarArista(4, 5, 20);
				grafop1.insertarArista(5, 1, 40);
				grafop1.insertarArista(5, 2, 70);
				
				  //*/
				
				
				
				//grafo1.mostrarDigrafo();
				//String muestra = grafo1.mostrarGrafoPesado();
				String muestra = grafop1.mostrarGrafoPesado();
				//grafo1.eliminarArista(0,4);
				//grafo1.eliminarVertice(4);
				System.out.println(muestra);
				
				Dijkstra digrafo = new Dijkstra(grafop1);
				String costoMinimo = digrafo.costoMinimo(0, 5);
				System.out.println("\n Digrafo " + costoMinimo + "\n");
				
				//grafo1.mostrarGrafoPesado();
				
				//DigrafoDebilmenteConexo unDigrafo = new DigrafoDebilmenteConexo(grafo1);

				//System.out.println("\n El Digrafo es debilmente conexo? " + unDigrafo.esDebilmenteConexo() + "\n");

		}

}
