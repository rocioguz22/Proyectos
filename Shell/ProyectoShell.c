/*------------------------------------------------------------------------------
Proyecto Shell de UNIX. Sistemas Operativos
Grados I. Inform�tica, Computadores & Software
Dept. Arquitectura de Computadores - UMA

Algunas secciones est�n inspiradas en ejercicios publicados en el libro
"Fundamentos de Sistemas Operativos", Silberschatz et al.

Para compilar este programa: gcc ProyectoShell.c ApoyoTareas.c -o MiShell
Para ejecutar este programa: ./MiShell
Para salir del programa en ejecuci�n, pulsar Control+D
------------------------------------------------------------------------------*/

#include "ApoyoTareas.h" // Cabecera del m�dulo de apoyo ApoyoTareas.c
 
#define MAX_LINE 256 // 256 caracteres por l�nea para cada comando es suficiente
#include <string.h>  // Para comparar cadenas de cars. (a partir de la tarea 2)

//Lista de tareas
job* tasks;


//Función manejador que imforma de los comandos en segundo plano 
//En base a su estado mando un mensaje u otro
void manejador (int signal){
  //La señal SIGCHLD
  block_SIGCHLD();
  job* t;
  int status;
  int info;
  int pwait = 0;
  enum status status_res;

    //Recorremos la lista
  for(int i= 1;i <= list_size(tasks);i++){

    //Tenemos una función de ayuda
    t  = get_item_bypos(tasks,i);
    pwait =waitpid(t->pgid,&status, WUNTRACED | WNOHANG | WCONTINUED );

    if(pwait == t->pgid){

      status_res = analyze_status(status, &info);

      if(status_res == FINALIZADO){
        printf("\nComando %s ejecutado en segundo plano con PID %d ha concluido\n",t-> command,t->pgid);
        delete_job(tasks,t);

      }else if(status_res == SUSPENDIDO){

        printf("\nComando %s ejecutado en segundo plano con PID %d ha concluido\n",t-> command,t->pgid);
        t->ground = DETENIDO;

      }else{
        printf("\nComando %s ejecutado en segundo plano con PID %d ha concluido\n",t-> command,t->pgid);
        t->ground = REANUDADO; 
        delete_job(tasks,t);
        //Se reanuda se termina y se borra
      }
    }
  }
  unblock_SIGCHLD(); 
}



// --------------------------------------------
//                     MAIN          
// --------------------------------------------

int main(void)
{
      char inputBuffer[MAX_LINE]; // B�fer que alberga el comando introducido
      int background;         // Vale 1 si el comando introducido finaliza con '&'
      char *args[MAX_LINE/2]; // La l�nea de comandos (de 256 cars.) tiene 128 argumentos como m�x
                              // Variables de utilidad:
      int pid_fork, pid_wait; // pid para el proceso creado y esperado
      int status;             // Estado que devuelve la funci�n wait
      enum status status_res; // Estado procesado por analyze_status()
      int info;		      // Informaci�n procesada por analyze_status()

      job* aux;
      
      //La correcta cesi�n del terminal requiere desactivar las se�ales asociadas al terminal mediante la funci�n ignore_terminal_signals()
      //Tal como dice en la guia usamos
      
      ignore_terminal_signals();

      //Lo copiamos de la guía y a continuación debemos crear el manejador
      signal(SIGCHLD, manejador);

      //Lista de tareas
      tasks= new_list("tasks"); 

      

      while (1) // El programa termina cuando se pulsa Control+D dentro de get_command()
      {   		
        printf("COMANDO->");
        fflush(stdout);
        get_command(inputBuffer, MAX_LINE, args, &background); // Obtener el pr�ximo comando
        //inoutBuffer = Lo que escribo
        //args = se guarda lo que escriba com un array de string que guarda la frase porespacios
        
        
        if (args[0]==NULL) continue; // Si se introduce un comando vac�o, no hacemos nada
        //Si la variable args esta vacia no hemos creado nada por lo tanto volvemos al bucle
        
        //Creamos dos funciones para la fase dos
        
        //Primero debemos ver si el comando introducido es el que buscamos
        
        //comparar el valor que devuelve la funci�n; si es 0 entonces las cadenas son iguales.
        
        if(strcmp(args[0],"cd") == 0){
        chdir(args[1]);
        //Utilixamos el continue para que el bucle comience de nuevo
        continue;
        }
        
        if(strcmp(args[0],"logout") == 0){
        exit(0);
        }

        //Nuevos comandos fase 5
        
         if(strcmp(args[0],"jobs") == 0){
          if(empty_list(tasks) == 1){ //Devuelve 1 si la lista esta vacía
            printf("\nError.No hay tareas ejecutandose en segundo plano o suspendidas\n");
          }else{
            print_job_list(tasks);
          }
        continue;
        }

        if(strcmp(args[0],"fg") == 0){
           int pLista = 1;

          //Si pasamos un valor transformamos el string en int
        if(args[1] != NULL){
          char* ptr;
          pLista = strtol(args[1], &ptr,10);
        }

          aux = get_item_bypos(tasks,pLista);
          //Hasta este punto hemos conseguido el comando
          if(aux == NULL){
              printf("Error.No se encuentra en la lista esa posición");
              continue;
          }else{

            //Ceder el terminal
             set_terminal(aux ->pgid);

              if(aux ->ground == DETENIDO || aux ->ground == SEGUNDOPLANO){
                
                //Actualizar el estado
                  aux ->ground = PRIMERPLANO;

                //Enviamos señal
                killpg(aux->pgid,SIGCONT);
              }
              //Como en la lista solo hay comandos de segundo plano y detenidos
              //Al ser de primer plano debemos borrarlo de la lista
             
             waitpid(aux->pgid, &status, WUNTRACED); // WUNTRACED es para detectar que se ha suspendido
                
                set_terminal(getpid());

                status = analyze_status(status,&info);

                if (status == SUSPENDIDO) {

                    aux->ground = DETENIDO;

                } else {
                    delete_job(tasks,aux);
                }
          }
              continue;
              

        }

      

        if(strcmp(args[0],"bg") == 0){
          //El anunciado nos dice que si no se introduce ningún numero cogemos el primer valor de la lista
        int pLista = 1;
        if(args[1] != NULL){
          char* ptr;
          pLista = strtol(args[1], &ptr,10);
        }
          aux = get_item_bypos(tasks,pLista);

          //Conseguir ese comando en funcion a la posicion que se nos pide

          //La función devuelve null si no esta en la lista esa posicion

          if(aux == NULL){
              printf("Error.No se encuentra en la lista esa posición\n");
          }else{
              if(aux ->ground == DETENIDO){
                //Actualizar el estado
                  aux ->ground = SEGUNDOPLANO;

                //Enviamos señal
                killpg(aux->pgid,SIGCONT);
              }
          }
            continue;
        }
          
        //Hacemos un fork
        
        pid_fork = fork();
        
        if(pid_fork != 0){
        //PADRE
        
          if(background == 0){
          waitpid(pid_fork, &status , WUNTRACED);
        //Tenemos tres tipos FINALIZADO , REANUDADO O SUSPENDIDO debemos comprabar el estado para poder proseguir
        
        set_terminal (getpid());
        
        status_res = analyze_status(status, &info);
        //Guarda en la lista enumerada
        
        //Una vez que ya hemos analizado el estatus
        
        if(status_res == FINALIZADO){
        
             if(info != 255){
              printf("Comando %s ejecutado en primer plano con pid %d. Estado %s. Info: %d\n",args[0],getpid(),status_strings[status_res],info);
              //En la posicion 0 del array se guarda el comando
       
        }
        
        }
        else if(status_res == REANUDADO){
     //Ahora tenemos una variable que nos dice el estado por lo tanto
       
       printf("Comando %s ejecutado en primer plano con pid %d. Estado %s. Info: %d\n",args[0],getpid(),status_strings[status_res],info);
        
       }else{ 

        //SUSPENDIDO
        block_SIGCHLD();

        aux = new_job(pid_fork,args[0],DETENIDO);
        add_job(tasks,aux);     
        printf("Comando %s ejecutado en primer plano con pid %d. Estado %s. Info: %d\n",args[0],getpid(),status_strings[status_res],info);
        
        unblock_SIGCHLD();
       }
        
       }else{

         block_SIGCHLD();
        //Añadimos a la lista el comando para que el manejador la ejecute
        
        aux = new_job(pid_fork,args[0],SEGUNDOPLANO);
        add_job(tasks,aux);

        //Segundo plano
       
       printf("Comando %s ejecutado en segundo plano con pid %d\n",args[0],getpid());
        unblock_SIGCHLD();
 
       }
        
        }else{
        
          
        //HIJO
        
        new_process_group (getpid());
        
        if(background == 0){
        set_terminal(getpid());
        }
        
        restore_terminal_signals();
        
        execvp(args[0],args);
        printf("Error.Comando %s no encontrado\n",args[0]);
        exit(-1);
        
        }
        
        
    /* Los pasos a seguir a partir de aqu�, son:
       (1) Genera un proceso hijo con fork()
       (2) El proceso hijo invocar� a execvp()
       (3) El proceso padre esperar� si background es 0; de lo contrario, "continue" 
       (4) El Shell muestra el mensaje de estado del comando procesado 
       (5) El bucle regresa a la funci�n get_command()
    */
  } // end while
}
