/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Asrock
 */
public class BFS {

	private RecorridoUtil controlDeMarcado;
	private Grafo grafoARecorrer;
	private List<Integer> recorrido;
	
	public BFS(Grafo elGrafoARecorrer, int posDeVerticeDePartida) {
		this.grafoARecorrer = elGrafoARecorrer;
		this.controlDeMarcado = new RecorridoUtil(grafoARecorrer.cantidadDeVertices());
		this.recorrido = new ArrayList<>();
		this.realizarRecorridoBFS(posDeVerticeDePartida);
	}
	
	private void realizarRecorridoBFS(int posDeVerticeDePartida) {
		this.grafoARecorrer.validarVertice(posDeVerticeDePartida);
		Queue<Integer> colaDeVertices = new LinkedList<>();
		colaDeVertices.offer(posDeVerticeDePartida);
		this.controlDeMarcado.marcar(posDeVerticeDePartida);
		do {
			int posVerticeEnTurno = colaDeVertices.poll();
			this.recorrido.add(posVerticeEnTurno);
			Iterable<Integer> itAdyacentesEnTurno = grafoARecorrer.adyacentesDeVertice(posVerticeEnTurno);
			for (Integer adyacenteEnTurno : itAdyacentesEnTurno) {
				if (!this.controlDeMarcado.estaMarcado(adyacenteEnTurno)) {
					colaDeVertices.offer(adyacenteEnTurno);
					this.controlDeMarcado.marcar(adyacenteEnTurno);
				}
			}
		} while (!colaDeVertices.isEmpty());
	}
	
	public Iterable<Integer> getRecorrido() {
		return this.recorrido;
	}
	
	public boolean hayCaminoAlVertice(int posDeVerticeDestino) {
		return this.controlDeMarcado.estaMarcado(posDeVerticeDestino);
	}
	
	public boolean hayCaminoATodos() {
		return this.controlDeMarcado.estanTodosMarcados();
	}
	
	/*public RecorridoUtil GetControlDeMarcado() {
		return controlDeMarcado;
	}*/
}
