/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

/**
 *
 * @author Asrock
 */
public class DigrafoFuertementeConexo {
		private GrafoConexo digrafo;
		
		public DigrafoFuertementeConexo(Digrafo unDigrafo) {
				this.digrafo = new GrafoConexo(unDigrafo);
		}
		
		public boolean esFuertementeConexo() {
				return this.digrafo.esConexo();
		}
		
}
