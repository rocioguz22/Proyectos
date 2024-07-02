
public class AST {	
	public String raiz;   
	public AST izq;
	public AST der;
	public String v;
	public String f;
	
	public AST (String r, AST i, AST d){
		raiz = r;
		izq = i;
		der = d;
	}
	
	public String gc(){
		String res = "";
		String left = "";
		String right = "";
		String temp = "";
		String aux = "";
		String et = "";
		String s = "";
		TablaSimbolos.Tipo tipo;

		switch(raiz) {
			case "ini":
				izq.gc();
				break;
			case "sent":
				izq.gc();
				if(der != null){
					der.gc();
				}
				break;
			case "print":
				left = izq.gc();
				if(left.equals("c")){
						TablaSimbolos.insertar(left,TablaSimbolos.Tipo.BOOLEAN);
				}
				aux = "print";
				if (TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_CHAR || izq.raiz.equals("castChar")){
					aux += "c";
				}
				if(TablaSimbolos.esArray(left)){
					temp = Generador.nuevaVariable();	
					for (int i = 0; i < TablaSimbolos.gettam(left); i++){
						PLXC.out.println("\t" + "$" + temp + " = " + left + "[" + i + "];");
						PLXC.out.println("\t"+ aux + " $" + temp + ";");
					}
				}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.STRING){
						temp = Generador.nuevaVariable();
						PLXC.out.println("\t" + "$" + temp + " = 0;");
						et = Generador.nuevaEtiqueta();
						PLXC.out.println(et+":");
						v = Generador.nuevaEtiqueta();
						f = Generador.nuevaEtiqueta();
						PLXC.out.println("\tif ($" + temp + " < $$" + left + "_length) goto " + v +";");
						PLXC.out.println("\tgoto " + f + ";");
						PLXC.out.println(v+":");
						String g = Generador.nuevaVariable();
						if(left.charAt(0) == 't'){
							PLXC.out.println("\t$"+ g + " = $"+ left+ "[$"+temp+"];");
						}else{
							PLXC.out.println("\t$"+ g + " = "+ left+ "[$"+temp+"];");
						}
						PLXC.out.println("\twritec $"+g+";");
						PLXC.out.println("\t$"+ temp + " = $"+ temp+ " + 1;");
						PLXC.out.println("\tgoto " + et + ";");
						PLXC.out.println(f+":");
						PLXC.out.println("\twritec 10;");
				}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.BOOLEAN){
					v = izq.v;
					f = izq.f;
				//--------------------------------------------------
				et = Generador.nuevaEtiqueta();
				PLXC.out.println(v+":");

				PLXC.out.println("\twritec 116;");
				PLXC.out.println("\twritec 114;");
				PLXC.out.println("\twritec 117;");
				PLXC.out.println("\twritec 101;");
				PLXC.out.println("\twritec 10;");
				PLXC.out.println("\tgoto " + et + ";");
				PLXC.out.println(f+":");
				PLXC.out.println("\twritec 102;");
				PLXC.out.println("\twritec 97;");
				PLXC.out.println("\twritec 108;");
				PLXC.out.println("\twritec 115;");
				PLXC.out.println("\twritec 101;");
				PLXC.out.println("\twritec 10;");
				PLXC.out.println(et+":");


				}else{
					if(!TablaSimbolos.estaIdent(left)){
						Errores.noDeclarada(left);
					}else{
						if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.CHAR || izq.raiz.equals("castChar")){
							PLXC.out.println("\tprintc " + left + ";");
						}else{
							PLXC.out.println("\tprint " + left + ";");
						}			
					}
				}
				break;

			case "dimplica":

			String v1 , v2 ,f1 , f2;
			left = izq.raiz;
			right = der.raiz;
			if(izq.raiz.equals("true") || izq.raiz.equals("false") ){
				v1 = Generador.nuevaEtiqueta();
					f1 = Generador.nuevaEtiqueta();
					et = Generador.nuevaEtiqueta();
					aux = Generador.nuevaEtiqueta();
					v2 = Generador.nuevaEtiqueta();
					f2 = Generador.nuevaEtiqueta();
					temp = Generador.nuevaVariable();

					if(left.equals("true")){
						PLXC.out.println("\tgoto " + v1 + ";");
					}else{
						PLXC.out.println("\tgoto " + f1 + ";");
					}

					PLXC.out.println(v1+":");
					PLXC.out.println("\t" + "$" + temp + " = 1;");
					PLXC.out.println(f1+":");
					if(right.equals("true")){
						PLXC.out.println("\tgoto " + et + ";");
					}else{
						PLXC.out.println("\tgoto " + aux + ";");
					}
					PLXC.out.println(et+":");
					PLXC.out.println("\tif ($" + temp+ " == 1) goto " + v2 + ";");
					PLXC.out.println("\tgoto " + f2 + ";");
					PLXC.out.println(aux+":");
					PLXC.out.println("\tif ($" + temp+ " == 1) goto " + f2 + ";");
					PLXC.out.println("\tgoto " + v2 + ";");
					v = v2;
					f = f2;
			}else{

				temp = Generador.nuevaVariable();	
				izq.gc();
				v1 = izq.v;
				f1 = izq.f;
				PLXC.out.println(v1+":");
				PLXC.out.println("\t" + "$" + temp + " = 1;");
				PLXC.out.println(f1+":");
				der.gc();
				v2 = der.v;
				f2 = der.f;
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println(v2+":");
				PLXC.out.println("\tif ($" + temp+ " == 1) goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				PLXC.out.println(f2+":");
				PLXC.out.println("\tif ($" + temp+ " == 1) goto " + f + ";");
				PLXC.out.println("\tgoto " + v + ";");

			}
				break;

			case "printArray":
				temp = Generador.nuevaVariable();
				left = izq.gc();
				tipo = TablaSimbolos.tipo(left);
				String temp2 = "$" + Generador.nuevaVariable();
				TablaSimbolos.insertar(temp2, tipo);
				int index = Generador.getCurrentIndex();
				for (int i = 0; i < index; i++){
					PLXC.out.println("\t" + temp2 + " = " + temp + "[" + i + "];");
					if(tipo == TablaSimbolos.Tipo.CHAR){
						PLXC.out.println("\tprintc " + temp2 + ";");
					}else{
						PLXC.out.println("\tprint " + temp2 + ";");
					}
				}
				Generador.resetIndex();
				break;
			case "pArrayIni":
				temp = "$" + Generador.getVariable();
				left = izq.gc();
				aux = Generador.getindex();
				PLXC.out.println("\t" + temp + "[" + aux + "] = " + left + ";");
				TablaSimbolos.insertar(temp, TablaSimbolos.tipo(left));
				if (der != null) {
					der.gc(); 
				}
				res = temp;
				break;
			case "frase":
				res += izq.raiz;
				temp = Generador.nuevaVariable();
				int cont = 0;
				
				for(int i= 0;i< res.length();i++){
					if(res.charAt(i) == '\\' && res.charAt(i+1) == '\\'){
						i = i + 1;
						int x = 92;
						PLXC.out.println("\t" + "$" + temp + "[" + cont + "]" + " = "+ x + ";");
						cont++;
					}else{
						char c = res.charAt(i);
        				char valor = (char) c;
						int x = (int) valor;
						PLXC.out.println("\t" + "$" + temp + "[" + cont + "]" + " = "+ x + ";");
						cont++;
					}

				}

				PLXC.out.println("\t" + "$$" + temp + "_length = "+cont+";");
				res = temp;

				if(!TablaSimbolos.estaIdent(res)){
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.STRING);
				}

				break;
				case "num":
				res += izq.raiz;
				if(!TablaSimbolos.estaIdent(res)){
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.INT);
				}
				break;
			case "true":
			res += izq.raiz;
			if(!TablaSimbolos.estaIdent(res)){
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.BOOLEAN);
				}
			break;
			case "false":
			res += izq.raiz;
			if(!TablaSimbolos.estaIdent(res)){
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.BOOLEAN);
				}
			break;
			case "real":
				res += izq.raiz;
				if(!TablaSimbolos.estaIdent(res)){
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.FLOAT);
				}
				break;
			case "castChar":
				aux = izq.gc();
				res += aux;
				break;	
			case "castInt":
				aux = izq.gc();
				v = izq.v;
				f = izq.f;
				temp = Generador.nuevaVariable();
				PLXC.out.println(v+":");
				PLXC.out.println("\t$" + temp + " = 1;");
				PLXC.out.println(f+":");
				res = "$" + temp;
				TablaSimbolos.insertar(res, TablaSimbolos.Tipo.INT);
				break;
			case "castFloat":
				aux = izq.gc();
				aux = ("(float) " + aux);
				v = Generador.nuevaVariable();
				PLXC.out.println("\t$" + v + " = " + aux + ";");
				res = "$" + v;
				TablaSimbolos.insertar(res, TablaSimbolos.Tipo.FLOAT);							
				break;
			case "castBool":
				aux = izq.gc();
				temp = Generador.getVariable();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif ($" + temp+ " == 1) goto " + f + ";");
				PLXC.out.println("\tgoto " + v + ";");
											
				break;
			case "length":
				aux = izq.raiz;
				if(TablaSimbolos.tipo(aux) == TablaSimbolos.Tipo.ARRAY_CHAR 
					|| TablaSimbolos.tipo(aux) == TablaSimbolos.Tipo.ARRAY_INT 
					|| TablaSimbolos.tipo(aux) == TablaSimbolos.Tipo.ARRAY_FLOAT){
					res = "$" + aux + "_length";
					TablaSimbolos.insertar(res, TablaSimbolos.Tipo.INT);
				}
				break;
			case "exits":
			left = izq.izq.raiz;
				PLXC.out.println("\t"+ 	left + " = 0;");
				et = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println(et+ ":");
				der.gc();
				v = der.v;
				PLXC.out.println(der.f+ ":");
				PLXC.out.println("\tif (" + left + " == 1) goto " + f + ";");
				PLXC.out.println("\t"+ 	left + " = 1;");
				PLXC.out.println("\tgoto " + et + ";");





			break;
			case "arrayInt":
				aux = izq.raiz;
				Integer tam = Integer.parseInt(der.raiz);				
				if(!TablaSimbolos.estaIdent(aux)){
					TablaSimbolos.insertar(aux, TablaSimbolos.Tipo.ARRAY_INT);
					TablaSimbolos.declarartam(aux, tam);
				}else{
					Errores.varDeclarada(aux);
				}
				PLXC.out.println("\t" + "$" + aux + "_length = " + tam + ";");
				break;
			case "arrayChar":
				aux = izq.raiz;
				tam = Integer.parseInt(der.raiz);
				if(!TablaSimbolos.estaIdent(aux)){
					TablaSimbolos.insertar(aux, TablaSimbolos.Tipo.ARRAY_CHAR);
					TablaSimbolos.declarartam(aux, tam);
				}else{
					Errores.varDeclarada(aux);
				}
				PLXC.out.println("\t" + "$" + aux + "_length = " + tam + ";");
				break;
			case "arrayFloat":
				aux = izq.raiz;
				tam = Integer.parseInt(der.raiz);
				if(!TablaSimbolos.estaIdent(aux)){
					TablaSimbolos.insertar(aux, TablaSimbolos.Tipo.ARRAY_FLOAT);
					TablaSimbolos.declarartam(aux, tam);
				}else{
					Errores.varDeclarada(aux);
				}
				PLXC.out.println("\t" + "$" + aux + "_length = " + tam + ";");
				break;	
			case "ascii":
				res += izq.raiz;
				if(!(res.charAt(0) == '\\' && res.charAt(1)=='u')){ 
					if(res.length() == 1){
						s = String.valueOf((int) res.charAt(0));
					}else{
						char especial = res.charAt(0);
						switch(especial){
							case 'n':
								s = "10";
								break;
							case 't':
								s = "9";
								break;
							case 'r':
								s = "13";
								break;
							case '\\':
								s = "92";
								break;
							case '\'':
								s = "39";
								break;
							case '\"':
								s = "34";
								break;
							case 'f':
								s = "12";
								break;
							case 'b':
								s = "8";
								break;
						}
					}
				}else {
					s = Integer.decode("0x" + res.substring(2,6)).toString();
				} 
				if (!TablaSimbolos.estaIdent(s)){
					TablaSimbolos.insertar(s, TablaSimbolos.Tipo.CHAR);

				}else {
					TablaSimbolos.setTipo(s, TablaSimbolos.Tipo.CHAR);
				}
				res = s;
				break;
			case "por":
				left = izq.gc();
				right = der.gc();
				tipo = TablaSimbolos.tipo(left);
				temp = Generador.nuevaVariable();
				if (tipo == TablaSimbolos.Tipo.FLOAT || TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT){
					tipo = TablaSimbolos.Tipo.FLOAT;
					TablaSimbolos.setTipo(left, TablaSimbolos.Tipo.FLOAT);
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.FLOAT);
					PLXC.out.println("\t$" + temp + " = "+ left + " *r " + right + ";" );
				}else{
					PLXC.out.println("\t$" + temp + " = " + left + " * " + right + ";" );}
				if (tipo == TablaSimbolos.Tipo.CHAR){
					tipo = TablaSimbolos.Tipo.INT;
				}				
				TablaSimbolos.insertar("$" + temp, tipo);
				res += "$" + temp;				
				break;
			case "mas":
				left = izq.gc();
				right = der.gc();
				temp = Generador.nuevaVariable();				
				if((der.raiz.equals("castInt") || izq.raiz.equals("castInt"))){
					tipo = TablaSimbolos.Tipo.INT;
				}else{
					tipo = resuelveTipo(right, left);
				}
				TablaSimbolos.setTipo(left, tipo);
				TablaSimbolos.setTipo(right, tipo);
				if(tipo == TablaSimbolos.Tipo.FLOAT){
					PLXC.out.println("\t$" + temp + " = "+ left + " +r " + right + ";" );
				}else{
					PLXC.out.println("\t$" + temp + " = " + left + " + " + right + ";" );
				}	
				TablaSimbolos.insertar("$" + temp, tipo);
				res += "$" + temp;				
				break;
			case "menos":
				left = izq.gc();
				right = der.gc();
				tipo = TablaSimbolos.tipo(left);
				temp = Generador.nuevaVariable();
				if (tipo == TablaSimbolos.Tipo.FLOAT || TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT){
					tipo = TablaSimbolos.Tipo.FLOAT;
					TablaSimbolos.setTipo(left, TablaSimbolos.Tipo.FLOAT);
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.FLOAT);
					PLXC.out.println("\t$" + temp + " = "+ left + " -r " + right + ";" );
				}else{
					PLXC.out.println("\t$" + temp + " = " + left + " - " + right + ";" );
				}	
				if (tipo == TablaSimbolos.Tipo.CHAR){
					tipo = TablaSimbolos.Tipo.INT;
				}			
				TablaSimbolos.insertar("$" + temp, tipo);
				res += "$" + temp;				
				break;
			case "menosUnario":
				aux = izq.gc();
				left = "-" + aux;
				temp = Generador.nuevaVariable();
				PLXC.out.println("\t$" + temp + " = " + left + ";");
				TablaSimbolos.insertar("$" + temp, TablaSimbolos.tipo(aux));
				res += "$" + temp;
				break;
			case "asig":
				left = izq.raiz;
				right = der.gc();
				if (!TablaSimbolos.estaIdent(left)){
					Errores.noDeclarada(left);
				}
				if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.BOOLEAN){
					if(right.equals("true") || right.equals("false")){
						v = Generador.nuevaEtiqueta();
						f = Generador.nuevaEtiqueta();
						et = Generador.nuevaEtiqueta();
	
						if(right.equals("true")){
						PLXC.out.println("\tgoto " + v + ";");
					}else{
					PLXC.out.println("\tgoto " + f + ";");
				}
					}else if(right == "hola"){
						
						
						//ALGO tengo q hacer para la doble igualdad
						
					}else{
						v = der.v;
						f = der.f;
						et = Generador.nuevaEtiqueta();
					}

					PLXC.out.println(v+":");
					PLXC.out.println("\t"+ left + " = 1;");
					PLXC.out.println("\tgoto " + et + ";");
					PLXC.out.println(f+":");
					PLXC.out.println("\t"+ left + " = 0;");
					PLXC.out.println(et+":");
					
				}else{
					if (TablaSimbolos.tipo(left) != TablaSimbolos.tipo(right) && !comprobarCasteo(left, der.raiz, right)){
						Errores.noTipo();
				} else {
						PLXC.out.println("\t" + left + " = " + right + ";");
				}

				}
				 if (!der.raiz.equals("asig")){
					res = right;
				} else {
					res = left;
				}				
				break;
			case "arrayAsig":
				left = izq.gc(); 
				if(left.equals("")){
					left = izq.raiz;
				}			
				aux = izq.izq.gc(); 
				if(aux.equals("")){
					aux = izq.izq.raiz;
				}
				tipo = TablaSimbolos.tipo(left);
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + aux + " < 0) goto " + v + ";");
				PLXC.out.println("\tif ("+ TablaSimbolos.gettam(left) + " < " + aux + ") goto " + v + ";");
				PLXC.out.println("\tif ("+ TablaSimbolos.gettam(left) + " == " + aux + ") goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				PLXC.out.println(v + ":");
				PLXC.out.println("\terror;\n\thalt;");
				PLXC.out.println(f + ":");
				right = der.gc();
				if(comprobarTipoArray(tipo, right)){
					PLXC.out.println("\t" + left + "[" + aux + "] = " + right + ";");
				}
				break;
			case "arrayPos":
				left = izq.raiz; 
				right = der.gc();
				if (right.equals("")){
					right = der.raiz;
				}
				temp = Generador.nuevaVariable();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				tipo = resuelveTipo(TablaSimbolos.tipo(left));
				PLXC.out.println("\tif (" + right + " < 0) goto " + v + ";");
				PLXC.out.println("\tif ("+ TablaSimbolos.gettam(left) + " < " + right + ") goto " + v + ";");
				PLXC.out.println("\tif ("+ TablaSimbolos.gettam(left) + " == " + right + ") goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				PLXC.out.println(v + ":");
				PLXC.out.println("\terror;\n\thalt;");
				PLXC.out.println(f + ":");
				PLXC.out.println("\t" + "$" + temp + " = " + left + "[" + right + "];");
				TablaSimbolos.insertar("$" + temp, tipo);
				res += "$" + temp;
				break;
			case "iniArray":
				left = izq.raiz;
				temp = Generador.getVarArray();
				der.gc();
				aux = Generador.getVarArray();
				for (int i = 0; i < Generador.getCurrentIndex(); i++){
					PLXC.out.println("\t" + "$" + aux + " = " + "$" + temp + "[" + i + "];");
					PLXC.out.println("\t" + left + "[" + i + "] = $" + aux + ";");
				}
				Generador.resetIndex();
				break;
			case "arrayIni":
				temp = Generador.getCurrentVarArray();
				left = izq.gc();	
				PLXC.out.println("\t" + "$" + temp + "[" + Generador.getindex() + "]" + " = " + left + ";");
				if(der != null) der.gc();						
				break;
			case "iniAsigArrayCh":
				izq.gc();
				der.gc();
				break;
			case "div":
				right = der.gc();
				left = izq.gc();
				tipo = TablaSimbolos.tipo(left);
				temp = Generador.nuevaVariable();
				if (tipo == TablaSimbolos.Tipo.FLOAT || TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT){
					tipo = TablaSimbolos.Tipo.FLOAT;
					TablaSimbolos.setTipo(left, TablaSimbolos.Tipo.FLOAT);
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.FLOAT);
					PLXC.out.println("\t$" + temp + " = "+ left + " /r " + right + ";" );
				}else{
					PLXC.out.println("\t$" + temp + " = " + left + " / " + right + ";" );
				}
				if (tipo == TablaSimbolos.Tipo.CHAR){
					tipo = TablaSimbolos.Tipo.FLOAT;
				}			
				TablaSimbolos.insertar("$" + temp, tipo);
				res += "$" + temp;				
				break;
			case "ident":
				left = izq.raiz;
				if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.BOOLEAN){
					v = Generador.nuevaEtiqueta();
					f = Generador.nuevaEtiqueta();
					PLXC.out.println("\tif (1  == " + left + ") goto " + v + ";");
					PLXC.out.println("\tgoto " + f + ";");
				}
				res += left;
				break;
			case "if":
				izq.gc();
				PLXC.out.println( izq.v + ":");
				der.v = izq.v;
				der.f = izq.f;
				der.gc();
				break;
			case "igual":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + left + " == " + right + ") goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				res = "c";
				break;
			case "else":
				String l = Generador.nuevaEtiqueta();
				izq.gc();
				PLXC.out.println("\tgoto " + l + ";");
				PLXC.out.println( f + ":");
				if(der!=null){
					der.gc();
				}
				PLXC.out.println( l + ":");
				break;
			case "menorIgual":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + right + " < " + left + ") goto " + f +";");
				PLXC.out.println("\tgoto " + v + ";");
				res = "c";
				break;
			case "mayorIgual":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + left + " < " + right + ") goto " + f +";");
				PLXC.out.println("\tgoto " + v + ";");
				res = "c";
				break;
			case "distinto":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + left + " == " + right + ") goto " + f + ";");
				PLXC.out.println("\tgoto " + v + ";");
				res = "c";
				break;
			case "and":
				izq.gc();
				PLXC.out.println(izq.v + ":");
				der.gc();
				PLXC.out.println(izq.f + ":");
				PLXC.out.println("\tgoto " + der.f + ";");
				v = der.v;
				f = der.f;
				res = "c";
				break;
			case "or":
				izq.gc();
				PLXC.out.println(izq.f + ":");
				der.gc();
				PLXC.out.println(izq.v + ":");
				PLXC.out.println("\tgoto " + der.v + ";");
				v = der.v;
				f = der.f;
				res = "c";
				break;
			case "not":
				izq.gc();
				v = izq.f;
				f = izq.v;
				break;
			case "mayor":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + right + " < " + left + ") goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				break;
			case "menor":
				left = izq.gc();
				right = der.gc();
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif (" + left + " < " + right + ") goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				break;
			case "int":
				izq.gc();
				break;
			case "boolean":
				izq.gc();
				break;
			case "string":
				izq.gc();
				break;
			case "char":
				izq.gc();
				break;
			case "float":
				izq.gc();
				break;	
			case "floatIdent":
				TablaSimbolos.insertar(der.raiz, TablaSimbolos.Tipo.FLOAT);
				if (izq != null){
					izq.gc();
				}
				break;
			case "asigChar":
				right = der.raiz;
				if (!TablaSimbolos.estaIdent(right)){
					TablaSimbolos.insertar(right, TablaSimbolos.Tipo.CHAR);
				}else if (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.CHAR){
					Errores.varDeclarada(right);
				}else {
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.CHAR);
				}
				PLXC.out.println("\t" + right + " = " + der.izq.gc() + ";");
				if(izq != null){
					izq.gc();
				}
				break;
			case "asigInt":
				right = der.raiz;
				if (!TablaSimbolos.estaIdent(right)){
					TablaSimbolos.insertar(right, TablaSimbolos.Tipo.INT);
				}else if (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.INT){
					Errores.varDeclarada(right);
				}else {
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.INT);
				}
				PLXC.out.println("\t" + right + " = " + der.izq.gc() + ";");
				if(izq != null){
					izq.gc();
				}
				break;
				case "asigBoolean":

					//ESTUDIO DE QUE NOS PASAN
					/*
					 *  IDENT:i ASIG exp:e						{: RESULT = new AST("asigBoolean", null, new AST(i,e,null)); :}
					 * 
					 * 
					 *  DENT:i ASIG exp:e COMA listaString:l	{: RESULT = new AST("asigBoolean", l, new AST(i,e,null)); :}
					 * 
					 *  izq = continuacion del código
					 * 	der.raiz = variable, (nombre de la variable) ejemplo a b c ...
					 *
					 * 	e = true o false = der.izq;
					 * 
					 */

				right = der.raiz;
				if (!TablaSimbolos.estaIdent(right)){
					TablaSimbolos.insertar(right, TablaSimbolos.Tipo.BOOLEAN);
				}else if (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.BOOLEAN){
					Errores.varDeclarada(right);
				}else {
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.BOOLEAN);
				}

				aux = der.izq.gc();

				if(der.izq.v != null && der.izq.f != null){
					v = der.izq.v;
					f = der.izq.f;
				}else{
					v = Generador.nuevaEtiqueta();
					f = Generador.nuevaEtiqueta();
				}

				et = Generador.nuevaEtiqueta();

				if(aux.equals("true")){
					PLXC.out.println("\tgoto " + v + ";");
				}else{
					PLXC.out.println("\tgoto " + f + ";");
				}
				
				PLXC.out.println(v+":");
				PLXC.out.println("\t"+ right + " = 1;");
				PLXC.out.println("\tgoto " + et + ";");
				PLXC.out.println(f+":");
				PLXC.out.println("\t"+ right + " = 0;");
				PLXC.out.println(et+":");


				
				if(izq != null){
					izq.gc();
				}
				break;
			case "asigString":
				right = der.raiz;
				if (!TablaSimbolos.estaIdent(right)){
					TablaSimbolos.insertar(right, TablaSimbolos.Tipo.STRING);
				}else if (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.STRING){
					Errores.varDeclarada(right);
				}else {
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.STRING);
				}

				//LOCURAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

				//right = a
				//der.izq.gc();  ------ t0
				//------------------------------
				String array = der.izq.gc();

				PLXC.out.println("# asignar array "+ right + " <- $"+ array + ";");
				PLXC.out.println("\t$" + right + "_lenght = 0;");
				temp = Generador.nuevaVariable();
				PLXC.out.println("\t" + "$" + temp + " = 0;");
				et = Generador.nuevaEtiqueta();
				PLXC.out.println(et+":");
				v = Generador.nuevaEtiqueta();
				f = Generador.nuevaEtiqueta();
				PLXC.out.println("\tif ($" + temp + " < $$" + array + "_length) goto " + v + ";");
				PLXC.out.println("\tgoto " + f + ";");
				PLXC.out.println(v+":");
				String g = Generador.nuevaVariable();
				PLXC.out.println("\t$"+ g + " = $"+ array + "[$"+temp+"];");
				PLXC.out.println(Generador.nuevaEtiqueta()+":");
				PLXC.out.println("\t"+right+"[$"+right+"_length] = $"+g+";");
				PLXC.out.println("\t$"+ right + "_length = $"+ right+ "_lenght + 1;");
				PLXC.out.println(Generador.nuevaEtiqueta()+":");
				PLXC.out.println("\t$"+ temp + " = $"+ temp+ " + 1;");
				PLXC.out.println("\tgoto " + et + ";");
				PLXC.out.println(f+":");


				if(izq != null){
					izq.gc();
				}
				break;
				case "asigFloat":
				right = der.raiz;
				if (!TablaSimbolos.estaIdent(right)){
					TablaSimbolos.insertar(right, TablaSimbolos.Tipo.FLOAT);
				}else if (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT){
					Errores.varDeclarada(right);
				}else {
					TablaSimbolos.setTipo(right, TablaSimbolos.Tipo.FLOAT);
				}
				PLXC.out.println("\t" + right + " = " + der.izq.gc() + ";");
				if(izq != null){
					izq.gc();
				}
				break;
			
			case "intIdent":
				TablaSimbolos.insertar(der.raiz, TablaSimbolos.Tipo.INT);
				if (izq != null){
					izq.gc();
				}
				break;
				case "stringIdent":
				TablaSimbolos.insertar(der.raiz, TablaSimbolos.Tipo.STRING);
				if (izq != null){
					izq.gc();
				}
				break;
			case "booleanIdent":
			TablaSimbolos.insertar(der.raiz, TablaSimbolos.Tipo.BOOLEAN);
				if (izq != null){
					izq.gc();
				}
				break;
			case "charIdent":
				if (!TablaSimbolos.estaIdent(der.raiz)){
					TablaSimbolos.insertar(der.raiz, TablaSimbolos.Tipo.CHAR);
				}else if (TablaSimbolos.tipo(der.raiz) == TablaSimbolos.Tipo.CHAR){
					Errores.varDeclarada(der.raiz);
				}else {
					TablaSimbolos.setTipo(der.raiz, TablaSimbolos.Tipo.CHAR);
				}
				if(izq != null){
					izq.gc();
				}
				break;
			case "while":
			    et = Generador.nuevaEtiqueta();
				PLXC.out.println(et + ":");
				izq.gc();
				PLXC.out.println(izq.v + ":");
				der.gc();
				PLXC.out.println("\tgoto " + et + ";");
				PLXC.out.println(izq.f + ":");
				break;
			case "doWhile":
				et = Generador.nuevaEtiqueta();
				PLXC.out.println(et + ":");
				der.gc();
				izq.gc();
				PLXC.out.println(izq.v + ":");
				PLXC.out.println("\tgoto " + et + ";");
				PLXC.out.println(izq.f + ":");
				break;
			case "for":			    
				aux = izq.gc();
				if(izq.raiz.equals("expFor")) PLXC.out.println(izq.v + ":");
				der.gc(); 
				PLXC.out.println("\tgoto " + aux + ";");
				PLXC.out.println(izq.f + ":");
				break;
			case "expFor":
				if (der.izq != null) {
					der.izq.gc(); 
				}
				et = Generador.nuevaEtiqueta();
				PLXC.out.println(et + ":");
				izq.gc(); //expfor
				String et2 = Generador.nuevaEtiqueta();
				PLXC.out.println(et2 + ":");
				if (der.der!=null) {
					der.der.gc(); 
				}
				PLXC.out.println("\tgoto " + et + ";");
				v = izq.v;
				f = izq.f;
				s = et2;
				res = s;
				break;
		}
		
		return res;
	}



	private TablaSimbolos.Tipo resuelveTipo (TablaSimbolos.Tipo tipo){
		TablaSimbolos.Tipo result = TablaSimbolos.Tipo.INT;
		switch (tipo) {
			case ARRAY_CHAR:
				return TablaSimbolos.Tipo.CHAR;
			case ARRAY_INT:
				return TablaSimbolos.Tipo.INT;
			case ARRAY_FLOAT:
				return TablaSimbolos.Tipo.FLOAT;
			default:
				break;
		}
		return result;
	}

	private TablaSimbolos.Tipo resuelveTipo(String right, String left) {
		TablaSimbolos.Tipo tipo = TablaSimbolos.Tipo.INT;
		if(TablaSimbolos.tipo(left) == TablaSimbolos.tipo(right)){
			if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_CHAR){
				tipo = TablaSimbolos.Tipo.CHAR;
			}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_INT){	
				tipo = TablaSimbolos.Tipo.INT;
			}else {
				tipo = TablaSimbolos.tipo(left);
			}
		}else if(((TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.FLOAT) && (TablaSimbolos.tipo(right) != TablaSimbolos.Tipo.STRING))
				|| ((TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT) && (TablaSimbolos.tipo(left) != TablaSimbolos.Tipo.STRING))){	
			tipo = TablaSimbolos.Tipo.FLOAT;
		}else if((TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.CHAR) && (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.INT) 
				|| (TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.INT) && (TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.CHAR)){
			tipo = TablaSimbolos.Tipo.INT;
		}else{
			Errores.noTipo();
		}
		return tipo;
	}

	private boolean comprobarTipoArray(TablaSimbolos.Tipo tipo, String right) {
		boolean result = false;
		if(tipo == TablaSimbolos.Tipo.ARRAY_INT && TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.INT){
			result = true;
		}else if(tipo == TablaSimbolos.Tipo.ARRAY_CHAR && TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.CHAR){
			result = true;
		}else if(tipo == TablaSimbolos.Tipo.ARRAY_CHAR && TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.INT){
			result = true;
		}else if(tipo == TablaSimbolos.Tipo.ARRAY_FLOAT && TablaSimbolos.tipo(right) == TablaSimbolos.Tipo.FLOAT){
			result = true;
		}else{
			Errores.noTipo();
		}
		return result;
	}

	private boolean comprobarCasteo(String left, String raiz, String derecha) {
		boolean result = false;
		if(raiz.equals("castChar")){
			if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.CHAR || TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.INT){
				result = true;
			}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_CHAR || TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_INT){
				result = true;
			}
		}else if(raiz.equals("castInt")){
			if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.INT){
				result = true;
			}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_INT){
				result = true;
			}
		}else if(raiz.equals("castFloat")){
			if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.FLOAT){
				result = true;
			}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.ARRAY_FLOAT){
				result = true;
			}
		}else if(TablaSimbolos.tipo(left) == TablaSimbolos.Tipo.FLOAT && TablaSimbolos.tipo(derecha) == TablaSimbolos.Tipo.INT){
			result = true;
		}
		return result;
	}

}

