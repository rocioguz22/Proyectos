
/**
 * 
 * @author ***** Jose A. Onieva ******* Asumimos que: a) Todos los items tienen
 *         un valor >=1 b) W>0
 */

public class MochilaFB extends Mochila {
//Hacemos la prueba para tres items

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm=null;
		int nitems = pm.size(); // Numéro de items
		int pesomochila = pm.pesoMaximo;

		// Calculamos el número de filas de la tabla para poder optar a todas las iteraciones
		// Si el elemento esta dos veces tomamos en cuenta (0,1,2) es decir n+1 veces
		// Debemos multiplicarlo para saber el total

		int combinaciones = 1;

		for(int i=0; i<nitems;i++) {

			combinaciones*= (pm.items.get(i).unidades)+1;

		}

		//Ya tenemos el número de veces que va a iterar nuestro programa ahora calculamos los arrays que se forman

		for (int j = 0;j < combinaciones; j ++) {
			int pos =j;
			// Como va de la primera fila a la última vamos obteniendo el número a descomponer
			int [] soluciones = new int [nitems];
			for(int h = 0; h < soluciones.length;h++) {
				int div = pm.items.get(h).unidades + 1;

				soluciones[h]= pos % div;

				pos = pos / (div);

			}



			//Una vez que yo calculo los resultados

			int peso = pm.sumaPesos(soluciones);
			int beneficios =pm.sumaValores(soluciones);

			if(peso <= pesomochila) {
				if(sm == null || sm.sumaValores < beneficios) {
					sm = new SolucionMochila (soluciones, peso, beneficios);
				}
			}

		}

		return sm; }
}
