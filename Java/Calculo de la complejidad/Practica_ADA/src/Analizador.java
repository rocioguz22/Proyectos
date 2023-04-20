import java.util.Arrays;

public class Analizador {
	
	public static void main(String arg []) {
		
		int alg=8;
		ejecutar(alg);
		
	}
	
	
	public static void ejecutar (int m) {
		
		long[] n = algoritm(m);
		long [] t1 = new long [n.length];
		long [] t2 = new long [n.length];
		Temporizador t = new Temporizador ();
		double [] ratios = new double [n.length];
		
		for(int g=0;g<9;g++) {
			
			for (int i = 0; i < n.length; i++) {
			
				t.reiniciar();
				t.iniciar();
				Algoritmo.f(n[i]);
				t.parar();
				t1[i] += (t.tiempoPasado());
			
			}
		}
		
		t2 = tiempo_auxiliar(m);
	
		for(int y = 0;y<n.length; y ++) {
			
			ratios[y] =(double) (t1[y]/9) / t2[y];
		}
		
		salida(m,ratios);
	}
	
	
private static void salida(int n,double [] ratios) {
	
	boolean stop = compara(ratios,n);
	int sol =0;
	sol = n;
	
	
	if(stop == true) {
		
	if (sol==1) {
		System.out.println("1");
	} else if (sol==2) {
		System.out.println("NLOGN");
	} else if (sol==3) {
		System.out.println("LOGN");
	} else if (sol==4) {
		System.out.println("N");
	} else if (sol==5) {
		System.out.println("N2");
	} else if (sol==6) {
		System.out.println("N3");
	} else if (sol==7) {
		System.out.println("2N");
	} else {
		System.out.println("NF");
	}
	
	
	
	}else {
		--sol;
		if(sol> 0) {
		ejecutar(sol);
	}
	
	}
}
	
	
	public static boolean compara(double [] ratios,int alg) {
		boolean solucion = true; 
		double aux1 =0;
		double aux2 = 0;
		int sol = alg;
		
		 if (sol==7 || sol == 8) {
			aux1=0.82;
			aux2=1.2;
		} else if(sol == 3){
			aux1=0.85;
			aux2=1.07;
			
		}else if (sol == 2) {
			aux1=0.7;
			aux2=1.13;
		}else{
			aux1=0.78;
			aux2=1.1125;
		}
		System.out.println(alg);
		System.out.println(Arrays.toString(ratios));
		
		 
		for (int i = 3; i < ratios.length - 1 && solucion; i++) {
			double diferencia = ratios[i] / ratios[i + 1];
			if (diferencia < aux1 || diferencia > (aux2)) {
				solucion = false;
			}
		}
		
		return solucion;
	}
	
	
	public static long [] algoritm (int n) {
		
		long [] n1 = {1,2,3,4,5}; 
		if(n == 7) {
			n1 = new long [] {5,6,7,8,9};
		}else if(n == 6) {
			n1 = new long [] {100,150,200,250,300,350,400,450}; 
		}else if(n == 5) {
			n1 = new long [] {500,600,700,800,900,1000}; 
		}else if(n == 4) {
			n1 = new long [] {1000,2000,3000,4000,5000,6000,7000,8000}; 
		}else if(n == 3) {
			n1 = new long [] {1000,2000,3000,4000,5000,6000,7000,8000};
		}else if(n == 2) {
			n1 = new long [] {1000,2000,3000,4000,5000,6000,7000,8000};
		}else if(n == 1) {
			n1 = new long [] {1000,2000,3000,4000,5000,6000,7000,8000};
		}
		
		return n1;
		
	}
	
	public static long [] tiempo_auxiliar (int n) {
		
		long [] n2 = {1, 2, 6, 24, 120}; 
		if(n == 7) {
			n2 = new long [] {32, 64, 128,256,512};
		}else if(n == 6) {
			n2 = new long[] {1000000, 3375000, 8000000, 15625000, 27000000, 42875000, 64000000, 91125000};
		}else if(n == 5) {
			n2 = new long [] {250000, 360000, 490000, 640000, 810000, 1000000}; 
		}else if(n == 4) {
			n2 = new long [] {1000,2000,3000,4000,5000,6000,7000,8000};
		}else if(n == 3) {
			n2 = new long [] {6, 7, 8, 8, 8, 8, 8, 8}; 
		}else if(n == 2) {
			n2 = new long [] {6907, 15201, 24019, 33176, 42585, 52197, 61975, 71897};
		}else if(n == 1) {
			n2 = new long[] {1000,2000,3000,4000,5000,6000,7000,8000};
		}
		return n2;
	}
	
}