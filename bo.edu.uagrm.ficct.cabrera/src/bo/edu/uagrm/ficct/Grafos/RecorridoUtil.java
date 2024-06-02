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
public class RecorridoUtil {

	private final List<Boolean> listaDeMarcado;

	public RecorridoUtil(int nroDeVertices) {
		this.listaDeMarcado = new ArrayList<>();
		for (int i = 0; i < nroDeVertices; i++) {
			this.listaDeMarcado.add(Boolean.FALSE);

		}
	}
	
	public boolean estaMarcado(int posDeVertice) {
		return this.listaDeMarcado.get(posDeVertice);
	}
	
	public void marcar(int posDeVertice) {
		this.listaDeMarcado.set(posDeVertice, Boolean.TRUE);
	}
	
	public void desmarcarTodos() {
		for (int i = 0; i < this.listaDeMarcado.size(); i++) {
			this.listaDeMarcado.set(i, Boolean.FALSE);
		}
	}
	
	public boolean estanTodosMarcados() {
		for (Boolean marcado: this.listaDeMarcado) {
			if (!marcado) {
				return false;
			}
		}
		return true;
	}
	
	

}
