import java.util.ArrayList;

/**
 * 
 * @author ***** Jose A. Onieva *******
 *
 */


/*          {
 * 			{  0                                      si i = 0 o j = 0
 *          {
 * F(i, j)  { F (i − 1,j)                             si j < wi
 *          {
 *          { max(F(i-1,j), F(i-1,j-(ki*wi))+ ki*vi   si j>= ki*wi^0 < ki <=qi
 *          {
 *
 *
 */




//Desarrollamos una matriz que cumpla esas condiciones.Una vez determinada
//la matriz, a partir de la información que contiene pueden identificarse los ítems
//que deben seleccionarse

public class MochilaPD extends Mochila {

	public SolucionMochila resolver(ProblemaMochila pm) {
		SolucionMochila sm=null;
		//Creamos la matriz
		int f = pm.size()+1;
		int c = pm.getPesoMaximo()+1;
		int [][] matriz = new int [f][c];
		//Array de items
		ArrayList <Item> item = pm.getItems();
		//Peso maximo que se puede meter en la mochila
		int mochila = pm.getPesoMaximo();


		//La primera condición si i = 0 o j = 0

		for(int i=0; i<f;i++) {

			matriz[i][0]=0;

		}
		for(int j=0; j<c;j++) {

			matriz[0][j]=0;

		}
		//Creamos dos bucles for para meter los valores dentro de la matriz
		for(int h=1;h<f;h++){
			for(int r=1;r<c;r++) {
				// Segundo
				int peso = item.get(h-1).peso;
				if(r < peso) {
					matriz[h][r]=matriz[h-1][r];
				}else {
					for (int i = 1; i <= item.get(i-1).unidades && r >= peso*i ; i++) {
						matriz[h][r] = Math.max(matriz[h - 1][r], matriz[h - 1][r - peso*i] + peso*i);

					}
				}
			}
		}

		for(int i=0;i<f;i++){
			for(int j=0;j<c;j++){
				System.out.print(matriz[i][j]);
				System.out.print(" ");
				if(j== c-1){
					System.out.println();
				}

			}
		}

		//Una vez tenemos creada la matriz seleccionamos el resultado

		int i = pm.items.size();
		int j = mochila;
		int [] solucion = new int [i]; // Array del tamaño de el número de items para hacer la sol

		while(i > 0 && j > 0) { // Mientras queden items y quede peso por repartir
			if(matriz[i][j] != matriz[i-1][j]) {
				for(int h= pm.items.get(i-1).unidades;h>0 && j>= pm.items.get(i-1).peso && matriz[i][j] != matriz [i-1][j];--h) {
					// El for lo que estudia es que el peso del item no sea mayor al de la mochila entonces no lo mete tantas veces como items haya
					// Tambien
					solucion[i-1]++;
					j = j - pm.items.get(i-1).peso;
				}
			}

			i--;

		}

		int peso = pm.sumaPesos(solucion);
		int beneficios =pm.sumaValores(solucion);

		sm = new SolucionMochila (solucion , peso, beneficios);

		return sm;
	}
}