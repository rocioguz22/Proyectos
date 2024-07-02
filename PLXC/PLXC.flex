
import java_cup.runtime.*;
%%
%xstate CARACTERES
%xstate STRINES
/*  Declaraciones */   
   
%cup 

%%   


/* Expresiones y reglas */

<YYINITIAL>{
	\'              {yybegin(CARACTERES); }
    \"              {yybegin(STRINES); }
   
   
    "+"                 { return new Symbol(sym.MAS); }
    "-"		            { return new Symbol(sym.MENOS); }
    "*"                 { return new Symbol(sym.POR); }
    "/"		            { return new Symbol(sym.DIV); }
    "=="                { return new Symbol(sym.IGUAL); }
    "="                 { return new Symbol(sym.ASIG); }
    "("                 { return new Symbol(sym.AP); }
    ")"                 { return new Symbol(sym.CP); }
    "{"                 { return new Symbol(sym.ALL); }
    "}"                 { return new Symbol(sym.CLL); }
    \[                  { return new Symbol(sym.AC); }
    \]                  { return new Symbol(sym.CC); }
    ";"                 { return new Symbol(sym.PYC); }
    "print"             { return new Symbol(sym.PRINT); }
    "if"                { return new Symbol(sym.IF); }
    "else"              { return new Symbol(sym.ELSE); }
    "while"             { return new Symbol(sym.WHILE); }
    "do"                { return new Symbol(sym.DO); }
    ">"                 { return new Symbol(sym.MAYOR); }
    "<"                 { return new Symbol(sym.MENOR); }
    ">="                { return new Symbol(sym.MAYORIGUAL); }
    "<="                { return new Symbol(sym.MENORIGUAL); }
    "!="                { return new Symbol(sym.DIF); }
    "!"                 { return new Symbol(sym.NOT); }
    "&&"                { return new Symbol(sym.AND); }
    "||"                { return new Symbol(sym.OR); }
    "for"               { return new Symbol(sym.FOR); }
    "int"               { return new Symbol(sym.INT); }
    "char"              { return new Symbol(sym.CHAR); }
    "boolean"           { return new Symbol(sym.BOOLEAN); }
    "true"              { return new Symbol(sym.TRUE,new String( yytext() ) ); }
    "false"             { return new Symbol(sym.FALSE , new String( yytext() )); }
    "float"             { return new Symbol(sym.FLOAT); }
    "string"            { return new Symbol(sym.STRING); }
    "<-->"               { return new Symbol(sym.IMPLICA); }
    "exists"            { return new Symbol(sym.EXITS); }
    ","                 { return new Symbol(sym.COMA); }
    "(char)"            { return new Symbol(sym.CASTCHAR); }
    "(int)"             { return new Symbol(sym.CASTINT); }
    "(boolean)"         { return new Symbol(sym.CASTBOOL); }
    "(float)"           { return new Symbol(sym.CASTFLOAT); }
    ".length"           { return new Symbol(sym.LENGTH); }	
[A-Za-z][A-Za-z0-9_]* {return new Symbol(sym.IDENT, new String(yytext())); }


    0|[1-9][0-9]*      { return new Symbol(sym.NUM, new String(yytext()) ); }
    0|[1-9][0-9]*\.[0-9]+([eE][+-]?[0-9]+)? { return new Symbol(sym.REAL, new String(yytext()) ); }
   
    \r|\n              {  }   
    \ |\t\f|\s         {  }  

}



<STRINES>{
    [A-Za-z0-9\\_]+  { return new Symbol(sym.FRASE, new String( yytext() )); }
    \"                                                     {yybegin(YYINITIAL); }
}   
    
<CARACTERES>{
    ([!-&((-~)]|(\\[bnfrt\"\\\']))|(\\u[0-9a-f]{4})     { return new Symbol(sym.ASCII, new String(yytext()) ); } 
    \'                                                     {yybegin(YYINITIAL); }
}

 .                  { throw new Error("Illegal character <"+yytext()+">"); }