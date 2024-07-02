import java.util.HashMap;


public class TablaSimbolos {
	public final static String ERROR_NOEXISTE = "ERROR6: Variable no declarada";
	public final static String ERROR_DECLARADA = "ERROR7: Esta variable ya ha sido declarada";

	public enum Tipo {
		CHAR, INT, FLOAT, STRING, BOOLEAN, ARRAY_CHAR, ARRAY_INT, ARRAY_FLOAT, ARRAY_STRING, ARRAY_CHAR_2D, ARRAY_INT_2D, ARRAY_STRING_2D
	}
	public static HashMap<String, Tipo> tablaTipos = new HashMap<String, Tipo>();

	public static void insertar(String ident, Tipo t) {
		if (!estaIdent(ident)) {
			tablaTipos.put(ident, t);
		}else if(ident.equals("c") && t == TablaSimbolos.Tipo.BOOLEAN){
			//Nada
		} else {
			Errores.varDeclarada(ident);
		}
	}
	public static boolean estaIdent(String ident) {
		return tablaTipos.containsKey(ident);
	}

	public static Tipo tipo (String ident) {
		if (!estaIdent(ident)) Errores.noVar(ident);
		return tablaTipos.get(ident);
	}

	public static void setTipo (String ident, Tipo t) {
		if (!estaIdent(ident)) Errores.noVar(ident);
		tablaTipos.replace(ident, t);
	}
	
	public static void imprimirValores() {
		System.out.println("Tabla de simbolos");
		for (String key : tablaTipos.keySet()) {
			System.out.println(key + " = " + tablaTipos.get(key));
		}
	}
	public static HashMap<String, Integer> arraySizes = new HashMap<String, Integer>();
	
	public static void declarartam(String ident, int tam) {
		if (estaIdent(ident)) {
			arraySizes.put(ident, tam);
		} 
	}
	public static int gettam(String ident) {
		if (!estaIdent(ident)) Errores.noVar(ident);
		return arraySizes.get(ident);
	}
	public static void imprimirtam() {
		System.out.println("Tabla de tamanios");
		for (String key : arraySizes.keySet()) {
			System.out.println(key + " = " + arraySizes.get(key));
		}
	}
	public static boolean esArray(String ident) {
		return arraySizes.containsKey(ident);
	}
	
}

	

