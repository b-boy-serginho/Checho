/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
            throws ExcepcionesGrafos, ExcepcionAristaYaExiste, ExceptionesNroVerticeInvalidos,
            ExceptionAristaYaExiste, ExceptionAristaNoExiste {
      Grafo grafo1=new Grafo(9);
       grafo1.insertarArista(0, 2);
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
       grafo1.insertarArista(8, 1);
       //System.out.println(bfsGrafo.cantidadDeIslasEnElGrafo());
       
       Digrafo digrafo1=new Digrafo(7);
       /*digrafo1.insertarArista(0, 1);
       digrafo1.insertarArista(1, 3);
       digrafo1.insertarArista(2, 0);
       digrafo1.insertarArista(2, 1);
       digrafo1.insertarArista(2, 3);
       digrafo1.insertarArista(6, 3);
       digrafo1.insertarArista(4, 5);
       digrafo1.insertarArista(7, 7);*/
      digrafo1.insertarArista(0,2);
      digrafo1.insertarArista(1,2);
      digrafo1.insertarArista(1,0);
      digrafo1.insertarArista(3,0);
      digrafo1.insertarArista(5,6);
      digrafo1.insertarArista(6,6);
      
     
      
      CiclosEnDigrafo ciclos=new CiclosEnDigrafo(digrafo1);
      //System.out.println(ciclos.hayCiclos());
      DFS camino =new DFS(grafo1,0);
       System.out.println("---------------------------------");
      System.out.println(camino.BuscarCamino(1, 3));
      System.out.println("---------------------------------");
      
      
       
      
       
       
       
       MostrarIslasEnDigrafo aa=new MostrarIslasEnDigrafo(digrafo1);
       System.out.println(aa.mostrarIslas());
       System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
     /*digrafo1.insertarArista(2, 0);
     digrafo1.insertarArista(2,3);*/
     DigrafoPesado digrafoPesado1=new DigrafoPesado(6);
       digrafoPesado1.insertarArista(0, 1, 50);
       digrafoPesado1.insertarArista(0, 5, 100);
       digrafoPesado1.insertarArista(0, 2, 10);
       digrafoPesado1.insertarArista(0, 4, 60);
       digrafoPesado1.insertarArista(1, 3, 50);
       digrafoPesado1.insertarArista(1, 4, 15);
       digrafoPesado1.insertarArista(2, 1, 5);
       digrafoPesado1.insertarArista(3, 0, 80);
       digrafoPesado1.insertarArista(3, 5, 20);
       digrafoPesado1.insertarArista(4, 5, 20);
       digrafoPesado1.insertarArista(5, 1, 40);
       digrafoPesado1.insertarArista(5, 2, 70);
       Disjktra da=new Disjktra(digrafoPesado1);
       System.out.println(da.caminosMasCortos(0, 5));
       
       
      GrafoPesado grafoPesado=new GrafoPesado(10);
      grafoPesado.insertarArista(0, 1, 5);
      grafoPesado.insertarArista(0, 3, 8);
      grafoPesado.insertarArista(0, 2, 10);
      grafoPesado.insertarArista(1, 5, 5);
      grafoPesado.insertarArista(1, 3, 6);
      grafoPesado.insertarArista(2, 3, 7);
      grafoPesado.insertarArista(2, 4, 8);
      grafoPesado.insertarArista(2, 7, 15);
      grafoPesado.insertarArista(3, 5, 11);
      grafoPesado.insertarArista(3, 4, 5);
      grafoPesado.insertarArista(4, 6, 4);
      grafoPesado.insertarArista(4, 7, 3);
      grafoPesado.insertarArista(5, 8, 7);
      grafoPesado.insertarArista(5, 6, 9);
      grafoPesado.insertarArista(6, 8, 4);
      grafoPesado.insertarArista(6, 9, 6);
      grafoPesado.insertarArista(6, 7, 12);
      grafoPesado.insertarArista(7, 9, 12);
      grafoPesado.insertarArista(8, 9, 7);
      
      
      
       
       System.out.println("********EJERCICIOS SOBRE GRAFOS Y DIGRAFOS,PESADOS Y NO PESADOS**********");
       
       System.out.println("***GRAFO NO DIRIGIO USADO ACTUALMENTE****");
       System.out.println(grafo1.mostraElGrafo());
       System.out.println("***GRAFO DIRIGIO USADO ACTUALMENTE****");
       System.out.println(digrafo1.mostraElGrafo());
       System.out.println("***GRAFO PESADO DIRIGIO USADO ACTUALMENTE****");
        System.out.println(digrafoPesado1.mostraElGrafo());
        System.out.println("***GRAFO NO DIRIGIO PESADO USADO ACTUALMENTE****");
        System.out.println(grafoPesado.mostraElGrafo());
       System.out.println("***************************************");
       System.out.println("****------------------ESCOJA EL NUMERO DE PREGUNTA-----------------------------");
       System.out.println("1:-Para un grafo no dirigido implementar el método de eliminar vértice.");
       System.out.println("2:-Para un grafo dirigido implementar método o clase para encontrar si hay ciclos "
               + "sin usar \n" +"matriz de caminos.");
       System.out.println("3:- EL METODO NO SE ENCUENTRA IMPLEMENTADO");
       System.out.println("4:-Para un grafo dirigido implementar un método o clase para determinar desde que"
               + " vértices\n" +"se puede llegar a un vértice, pero sin ejecutar más de una vez un recorrido.");
       System.out.println("5:-  Para un grafo dirigido solo usando como base la lógica de un recorrido (dfs o bfs)"
               + " encuentre \n" +"desde que vértices se puede llegar a un vértice a, sin importar las veces que "
               + "ejecute el\n" +"recorrido elegido");
       System.out.println("6:- Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido "
               + "tiene \n" +"ciclos");
       
       System.out.println("7:- Para un grafo dirigido implementar un algoritmo para encontrar si es débilmente conexo");
       System.out.println("8:- EL METODO NO SE ENCUENTRA IMPLEMENTADO");
       System.out.println("9:- Para un grafo no dirigido implementar un algoritmo para encontrar el número "
               + "de islas que \n" +"hay en el grafo");
       System.out.println("10:-  Para un grafo dirigido implementar un algoritmo para encontrar el número de islas "
               + "que hay \n" +"en el grafo");
       System.out.println("11. Para un grafo dirigido implementar el algoritmo de Wharsall,"
               + " que luego muestre entre que \n" +"vértices hay camino");
       System.out.println("12:- NO SE ENCUENTRA IMPLEMENTADO EL ALGORITMO DE FLOYD WARSHALL");
       System.out.println("13. Para un grafo dirigido implementar un algoritmo que retorne cuántas componentes"
               + " \n" +"fuertemente conexas");
       System.out.println("14:- Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre cual "
               + "es el \n" +"camino de costo mínimo entre un vértice a y b y cual el costo");
       System.out.println("15:- 15. Para un grafo dirigido pesado implementar el algoritmo de Dijkstra que muestre"
               + " con que \n" +"vértices hay caminos de costo mínimo partiendo desde un vértice v, con qué costo y "
               + "cuáles \n" +"son los caminos.");
       System.out.println("16.EL ALGORITMO DE KRUSKAL NO SE ENCUENTRA IMPLEMENTADO");
       System.out.println("17:-Para un grafo no dirigido pesado implementar el algoritmo de Prim que muestre"
               + " cual es el \n" +"grafo encontrado por el algoritmo");
       System.out.println("18. Para un grafo dirigido implementar al algoritmo de ordenamiento topológico."
               + " Debe mostrar \n" +"cual es el orden de los vértices según este algoritmo.");
       System.out.println("19. Para un grafo dirigido pesado implementar el algoritmo de Ford-Fulkerso");
       
       System.out.println("---------------------------------------");
       System.out.println("Que numero de pregunta quiere resolver: ");
       Scanner numero=new Scanner(System.in);
       int pregunta=numero.nextInt();
       
       switch(pregunta){
           case 1:
                 System.out.println("Ingrese la posicion del vertice a eliminar");
                 numero=new Scanner(System.in);
                 grafo1.eliminarVertice(numero.nextInt());
                 System.out.println("El grafo con el vertice eliminado es el siguiente");
                 System.out.println(grafo1.mostraElGrafo());
                 
                break;
           case 2:
                Warshall w=new Warshall(digrafo1);
                System.out.println("Ciclos en el digrafo:= "+w.hayCiclos());
                 break;
           case 3:
                System.out.println("EL MÉTODO NO SE ENCUENTRA IMPLEMENTADO");
                 break;
           case 4:
               System.out.println("Ingrese el vertice al que quiere llegar: ");
               numero=new Scanner(System.in);
               BFS a=new BFS(digrafo1,0);
               System.out.println("Desde el vertice "+numero.nextInt()+"llego a : "+a.desdeQueVerticesLlegoA(numero.nextInt()));
               break;
           case 5:
               System.out.println("Ingrese el vertice al que quiere llegar: ");
               numero=new Scanner(System.in);
               BFS x=new BFS(digrafo1,0);
               System.out.println("Desde el vertice "+numero.nextInt()+"llego a : "+x.desdeDondePuedoLlegarAUsandoVariosRecorridos(digrafo1, pregunta));
               break;
           case 6:
                w=new Warshall(digrafo1);
                System.out.println("Ciclos en el digrafo:= "+w.hayCiclos());  
               break;
           case 7:
                  DebilmenteConexo d=new DebilmenteConexo(digrafo1);
                  System.out.println("¿El digrafo dado fuertemente conexo?:="+ d.esDebilmenteConexo());
               break;
           case 8:
                System.out.println("El MÉTODO NO SE ENCUENTRA IMPLEMENTADO");
               break;
           case 9:
                x=new BFS(grafo1,0);
                System.out.println("El numero de islas en el grafo es "+x.cantidadDeIslasEnElGrafo());
               break;
           case 10:
                DFS m=new DFS(digrafo1,0);
                System.out.println("La catidad de islas en el digrafo es "+m.cantidadDeIslasEnDigrafo(digrafo1));
               break;
           case 11:
                w=new Warshall(digrafo1);
                System.out.println(w.matrizDeCaminos());
               break;
           case 12:
                System.out.println("EL MÉTODO NO SE ENCUENTRA IMPLEMENTADO");
               break;
           case 13:
                ComponentesFuertementeConexas com=new ComponentesFuertementeConexas(digrafo1);
                System.out.println(com.listaComponentesConexas());
               break;
               
           case 14:
               System.out.println("Ingrese el vertice De partida");
               numero=new Scanner(System.in);
               System.out.println("Ingrese el vertice De Destino");
               Scanner numero2=new Scanner(System.in);
               Disjktra dis=new Disjktra(digrafoPesado1);
               System.out.println(dis.caminosMasCortos(numero.nextInt(),numero2.nextInt()));
               break;
           case 15:
               System.out.println("Ingrese el vertice de partida para iniciar Disjktra: ");
               numero=new Scanner(System.in);
               DisjktraATodos disa=new DisjktraATodos(digrafoPesado1);
               System.out.println(disa.caminosMasCortos(numero.nextInt()));
               break;
           case 16:
                System.out.println("EL ALGORITMO DE KRUSKAL NO SE ENCUENTRA IMPLEMENTADO");
               break;
           case 17:
                Prim p=new Prim(grafoPesado);
                System.out.println("EL ARBOL EXPANSION DE COSTO MINIMO CON EL ALGITMO DE PRIM ES: ");
                System.out.println(p.arbol().mostraElGrafo());
               
               break;
           case 18:
                OrdenamientoTopologico or=new OrdenamientoTopologico(digrafo1);
                System.out.println(or.ordenamiento());
               
               break;
           case 19:
               System.out.println("EL ALGORITMO DE FORD FULKERSON NO SE ENCUENTRA IMPLEMENTADO");
               break;
           default:
               System.out.println("ingrese una opcion valida");
             
       } 
       
       
     //digrafo1.insertarArista(4, 5);
     //digrafo1.insertarArista(5, 0);
     //digrafo1.insertarArista(7,6);
     //digrafo1.insertarArista(4, 5);
    
     /*DFS miDfs=new DFS(digrafo1,0);
     
        
       
       BFS reco=new BFS(digrafo1, 0);
       System.out.println(reco.mostrarRecorridoBFS());
       Warshall caminos=new Warshall(digrafo1);
       System.out.println(caminos.matrizDeCaminos());
    
       
       //reco.mostrarIslasEnUnGrafo(grafo1);
       //BFS re=new BFS(grafo1,0);
      // System.out.println(re.mostrarRecorridoBFS());
       //Conexo esConexo=new Conexo(grafo1);
       //System.out.println(reco.ciclosEnDigrafo(digrafo1));
       //System.out.println(reco.cantidadDeIslasEnDigrafo(digrafo1));
       //System.out.println(reco.desdeQueVerticesLlegoA(5));
       //System.out.println(reco.tieneCiclosElDigrafo());
       //System.out.println(reco.unDigrafoEsDebilmenteConexo(digrafo1));
       System.out.println(miDfs.cantidadDeIslasEnUnDigrafo2());
       Disjktra dis=new Disjktra(digrafoPesado1);
       DisjktraATodos disj=new DisjktraATodos(digrafoPesado1);
       System.out.println(disj.caminosMasCortos(0));
       //System.out.println(reco.matrizDeCaminos(digrafo1));
       
      Prim prim=new Prim(grafoPesado);
      //grafoPesado=prim.arbol();
      System.out.println(prim.arbol().mostraElGrafo());
      
              
      
      
       //System.out.println(esConexo.esConexo());*/
     
     
     Warshall w=new Warshall(grafo1);
     System.out.println(w.matrizDeCaminos());
    }
    
}
