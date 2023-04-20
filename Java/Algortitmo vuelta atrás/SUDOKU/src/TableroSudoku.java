// ALUMNO:
// GRUPO: 

import java.util.*;


public class TableroSudoku implements Cloneable {

	// constantes relativas al n? de filas y columnas del tablero
	protected static final int MAXVALOR=9;
	protected static final int FILAS=9;
	protected static final int COLUMNAS=9;

	protected static Random r = new Random();

	protected int celdas[][]; // una celda vale cero si esta libre.

	public TableroSudoku() {
		celdas = new int[FILAS][COLUMNAS]; //todas a cero.
		//Creamos un tablero donde todos sus elementos estan a cero si tenemos que
		// volver atr?s debemos ponerlo a cero para dejarlo en su antiguo estado
	}

	// crea una copia de su parametro
	public TableroSudoku(TableroSudoku uno) {
		TableroSudoku otro = (TableroSudoku) uno.clone();
		this.celdas = otro.celdas;
	}

	// crear un tablero a parir de una configuraci\u00D3n inicial (las celdas vac\u00EDas
	// se representan con el caracter ".".
	public TableroSudoku(String s) {
		this();
		if(s.length() != FILAS*COLUMNAS) {
			throw new RuntimeException("Construcci?n de sudoku no v?lida.");
		} else {
			for(int f=0;f<FILAS;f++)
				for(int c=0;c<COLUMNAS;c++) {
					Character ch = s.charAt(f*FILAS+c);
					celdas[f][c] = (Character.isDigit(ch) ? Integer.parseInt(ch.toString()) : 0 );
				}
		}
	}


	/* Realizar una copia en profundidad del objeto
	 * @see java.lang.Object#clone()
	 */
	public Object clone()  {
		TableroSudoku clon;
		try {
			clon = (TableroSudoku) super.clone();
			clon.celdas = new int[FILAS][COLUMNAS];
			for(int i=0; i<celdas.length; i++)
				System.arraycopy(celdas[i], 0, clon.celdas[i], 0, celdas[i].length);
		} catch (CloneNotSupportedException e) {
			clon = null;
		}
		return clon;
	}

	/* Igualdad para la clase
	 * @see java.lang.Object#equals()
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TableroSudoku) {
			TableroSudoku otro = (TableroSudoku) obj;
			for(int f=0; f<FILAS; f++)
				if(!Arrays.equals(this.celdas[f],otro.celdas[f]))
					return false;
			return true;
		} else
			return false;
	}



	public String toString() {
		String s = "";

		for(int f=0;f<FILAS;f++) {
			for(int c=0;c<COLUMNAS;c++)
				s += (celdas[f][c]==0 ? "." : String.format("%d",celdas[f][c]));
		}
		return s;
	}


	// devuelva true si la celda del tablero dada por fila y columna est\u00E1 vac\u00EDa.
	protected boolean estaLibre(int fila, int columna) {
		return celdas[fila][columna] == 0;
	}

	// devuelve el n?mero de casillas libres en un sudoku.
	protected int numeroDeLibres() {
		int n=0;
		for (int f = 0; f < FILAS; f++)
			for (int c = 0; c < COLUMNAS; c++)
				if(estaLibre(f,c))
					n++;
		return n;
	}

	protected int numeroDeFijos() {
		return FILAS*COLUMNAS - numeroDeLibres();
	}

	// Devuelve true si @valor ya esta en la fila @fila.
	protected boolean estaEnFila(int fila, int valor) {
		boolean ok = false;
		for (int i = 0; i < FILAS; ++i) {
			if (celdas[fila][i] == valor) {
				ok = true;
			}
		}
		return ok;
	}

	// Devuelve true si @valor ya esta en la columna @columna.
	protected boolean estaEnColumna(int columna, int valor) {
		boolean ok = false;
		for (int i = 0; i < COLUMNAS; ++i) {
			if (celdas[i][columna] == valor) {
				ok = true;
			}
		}
		return ok;
	}


	// Devuelve true si @valor ya esta en subtablero al que pertence @fila y @columna.
	protected boolean estaEnSubtablero(int fila, int columna, int valor) {
		boolean esta = false;
		int fila_comenzar = fila - (fila % 3);
		int columna_comenzar = columna - (columna % 3);
		for (int i = fila_comenzar; i < (fila_comenzar + 3) && !esta; i++) {
			for (int j = columna_comenzar; j < (columna_comenzar + 3) && !esta; j++) {
				if (celdas[i][j] == valor) {
					esta = true;
				}
			}
		}
		return esta;
	}


	// Devuelve true si se puede colocar el @valor en la @fila y @columna dadas.
	protected boolean sePuedePonerEn(int fila, int columna, int valor) {
		boolean ok = false;
		if(estaEnColumna(columna,valor) == false && estaEnFila(fila,valor) == false && estaEnSubtablero(fila,columna,valor) == false){
			ok = true;
		}
		return ok;
	}




	protected void resolverTodos(List<TableroSudoku> soluciones, int fila, int columna) {

		if(numeroDeLibres() == 0){
			soluciones.add( new TableroSudoku(this));
		}else{
			// Tenemos que llenar el tablero hasta que este completo
			// Una vez encontramos un sitio donde podemos introducir un n?mero del uno al 9 vamos comprobando y desaciendo

			if(estaLibre(fila,columna)){
				int num = 1;
				while(num <= 9){
					if (sePuedePonerEn(fila, columna, num)) {

						celdas [fila][columna] = num;
						// A?adimos un posible numero

						int col;
						int  fil;

						if(columna == 8){
							col = 0;
							fil = fila +1;
						}else{
							fil = fila;
							col = columna +1;
						}
						resolverTodos(soluciones, fil, col);
						celdas [fila][columna] = 0;

					}
					num++;

				}


			} else {

				if(columna == 8){
					columna = 0;
					fila++;
				}else{
					columna++;
				}
				resolverTodos(soluciones, fila, columna);

			}

		}

	}


	public List<TableroSudoku> resolverTodos() {
		List<TableroSudoku> sols  = new LinkedList<TableroSudoku>();
		resolverTodos(sols, 0, 0);
		return sols;
	}

	public static void main(String arg[]) {
		TableroSudoku t = new TableroSudoku(
				".4....36263.941...5.7.3.....9.3751..3.48.....17..62...716.9..2...96.......312..9.");
		List<TableroSudoku> lt = t.resolverTodos();
		System.out.println(t);
		System.out.println(lt.size());
		for(Iterator<TableroSudoku> i= lt.iterator(); i.hasNext();) {
			TableroSudoku ts = i.next();
			System.out.println(ts);

		}

	}


}