/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asrock
 */
public class DFS {

	private RecorridoUtil controlDeMarcado;
	private Grafo grafoARecorrer;
	private List<Integer> recorrido;

	public DFS(Grafo elGrafoARecorrer, int posDeVerticeDePartida) {
		this.grafoARecorrer = elGrafoARecorrer;
		this.controlDeMarcado = new RecorridoUtil(grafoARecorrer.cantidadDeVertices());
		this.recorrido = new ArrayList<>();
		this.realizarRecorridoDFS(posDeVerticeDePartida);
	}

	public void realizarRecorridoDFS(int posVerticeEnTurno) {
		this.grafoARecorrer.validarVertice(posVerticeEnTurno);
		this.controlDeMarcado.marcar(posVerticeEnTurno);
		this.recorrido.add(posVerticeEnTurno);
		Iterable<Integer> itAdyacentesEnTurno = grafoARecorrer.adyacentesDeVertice(posVerticeEnTurno);
		for (Integer adyacenteEnTurno : itAdyacentesEnTurno) {
			if (!this.controlDeMarcado.estaMarcado(adyacenteEnTurno)) {
				realizarRecorridoDFS(adyacenteEnTurno);
			}
		}
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
