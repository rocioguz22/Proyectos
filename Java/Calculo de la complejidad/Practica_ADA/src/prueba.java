import java.util.Random;

public class prueba {
	
public static void main(String arg[]) {
	
	long [] n = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
	long [] t1 = new long [n.length];
	Temporizador t = new Temporizador ();
	
	for (int i = 0; i < n.length; i++) {
		t.reiniciar();
		t.iniciar();
		referenciacte(n[i]);
		t.parar();
		t1[i] =(t.tiempoPasado());
		System.out.println(t1[i]);
	}
	
	System.out.println(t1);

}
//Funcion cte
	private static void referenciacte (long n) {
		int x=50;
		x++;
		}
	
	// Funcion logaritmica
	private static void referencialog (long n) {
		int x = 1;
		while (x < n) {
			x*=2;
		}
	}
	
	// Funcion lineal
	private static void referencialineal (long n) {
		int x = 0;
		for (int i = 1; i <= n; i++) {
			x=0;
		}
	}
	
	// Funcion nlogaritmo
	private static void referencianlog (long n) {
		int i = 1;
		for (int e = 1; e <= n; e++) {
			while (i < n) {
				i = i * 2;
			}
		}
	}
	
	// Funcion cuadratica
	private static void referenciacuadratica (long n) {
		int x = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				x++;
			}
		}
	}
	
	private static void referenciacubica (long n) {
		int x = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					x++;
				}
			}
		}
	}
	
	private static void referenciaexponencial (long n) {
		int n1 = 0;
		int n2 = 1;
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = n1 + n2;
			n1 = n2;
			n2 = sum;
		}
	}
	
	public static void referenciaexponencial (Long n) {
        Random alea = new Random();
        long b = raiz(n,alea.nextInt(n.intValue()));
    }

    private static long raiz (long n, long nn) {
        long a;
        if (nn == 0) {
            a = 1;
        } else {
            a = (n / (raiz(n,nn-1)) + (raiz(n,nn-1))) / (long) 2;
        }
        return a;
    }
	
	// La referencia factorial no esta creada ya que si no se cumple ninguna del resto de las condiciones, sera NF
}