// <hawsh, HAW-Shell. In Funktionalität stark eingeschraenkte Shell.>
// <Breitenstein, Bartel>
// <11.11.2012>
//v: 0.01

//includes
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/wait.h> 

#define FALSE 0
#define TRUE 1
#define ZERO 0
#define EQUAL 0
#define VERSION 0.01

//DEKLARATIONS ggf. DEFINITIONS
char command[10000];
char quit = 0;
pid_t PIDstatus;
int status;
char *params[] ={"sven0r",NULL};

char *quitString = "quit", *version = "version", *help = "help";

//PROTOTYPES
void read_command(char*);
void type_prompt();
void print_help();
void print_version();
void cutOffUnd(char*);
void getErrMsg(int);
char lastZeichenUnd(char*);
char firstZeichenSlash(char*);
char valid_command(char*);


//MAIN
int main(int argc, char **argv, char **envp){
	while (quit == FALSE){
		
		//wait3(NULL, WUNTRACED, NULL);
		type_prompt();
		read_command(command);
	      

		

                                
		if(strcmp(command,quitString) == EQUAL){
		    quit = TRUE;
		}else if(strcmp(command,version) == EQUAL){
                    print_version();
		}else if(strcmp(command,help) == EQUAL){
		    print_help();                      
		}else if(firstZeichenSlash(command)){
                    int i = chdir(command);
                    if(i){
		      printf("Das Verzeichnis existiert nicht, möglicher Fehler im Pfad: %s\n",command);
		    }
                    
		}else{
			if(valid_command(command)){
			
			  PIDstatus = fork();
						  
			  if(lastZeichenUnd(command)){
			    
			    cutOffUnd(command);
			    
				  if(PIDstatus < 0){
				  printf("Unable to fork");

					  
				  }else{
				    printf("\n");
				      if(PIDstatus == 0){
					  //printf("%d  child \n",PIDstatus);
					  int errNr = execvp(command,params);
					  getErrMsg(errNr);
					  exit(0);
					  
				      }else{
					  //printf("%d parent \n",PIDstatus);
									
				      }
				    
				  }
				  
			  }else{ 

				  if(PIDstatus > 0){
					  //printf("%d parent \n",PIDstatus);
					  waitpid(PIDstatus, &status, 0);
				  }else{
					  //printf("%d child\n",PIDstatus);
					  int errNr =  execvp(command,params);
					  getErrMsg(errNr);
					  exit(0);
					  
					  
				  }
			  }
			}else{
			  printf("Not a valid command : %s\n",command);
			}
		}
	}
}

	//letztes Zeichen bestimmen
	char lastZeichenUnd(char *command){
            const int iLaenge = strlen(command);
      
            return (command[iLaenge-1] == '&');
	}
	
	char valid_command(char *command){
	  const int iLaenge = strlen(command);
	  char k = TRUE, z = ' ';
	  int i = 0;
	  if(iLaenge > 0){  
	    for(i; i < iLaenge-1 && k; i++){
	    z = command[i];
	      if(z == '&' || z == '%' || z == '"')
		k = FALSE ;
	      
	    }
	    
	  }else{
	    k = FALSE;
	  }
	    return k;
	}
	
	void cutOffUnd(char *command){
	    const int iLaenge = strlen(command);
            command[iLaenge-1] = 0x0;
	}
	
	char firstZeichenSlash(char *command){
            return (command[0] == '/');
	}

	void read_command(char *command){
		
            fgets(command,100,stdin);
            const int iLaenge = strlen(command);
            command[iLaenge-1] = 0x0;

	}

	void type_prompt(){
            printf("%s - Was willst Du %s ?>",getcwd(command,sizeof(command)),getlogin());
            
	}
        void print_version(){
                printf("version: %.2f\n",VERSION);
        }
	
	void print_help(){
            printf("Du bekommst nichts Hilfe!\n");
	}
	
  
	void getErrMsg(int errnum)
      {

	  switch ( errnum ) {

      #ifdef EACCES
	      case EACCES :
	      {
		  printf( "EACCES Permission denied\n");
	      }
      #endif

      #ifdef EPERM
	      case EPERM :
	      {
		  printf( "EPERM Not super-user\n");
	      }
      #endif

      #ifdef E2BIG
	      case E2BIG :
	      {
		  printf( "E2BIG Arg list too long\n");
	      }
      #endif

      #ifdef ENOEXEC
	      case ENOEXEC :
	      {
		  printf( "ENOEXEC Exec format error\n");
	      }
      #endif

      #ifdef EFAULT
	      case EFAULT :
	      {
		  printf( "EFAULT Bad address\n");
	      }
      #endif

      #ifdef ENAMETOOLONG
	      case ENAMETOOLONG :
	      {
		  printf( "ENAMETOOLONG path name is too long\n");
	      }
      #endif

      #ifdef ENOENT
	      case ENOENT :
	      {
		  printf( "ENOENT No such file or directory\n");
	      }
      #endif

      #ifdef ENOMEM
	      case ENOMEM :
	      {
		  printf( "ENOMEM Not enough core\n");
	      }
      #endif

      #ifdef ENOTDIR
	      case ENOTDIR :
	      {
		  printf( "ENOTDIR Not a directory\n");
	      }
      #endif

      #ifdef ELOOP
	      case ELOOP :
	      {
		  printf( "ELOOP Too many symbolic links\n");
	      }
      #endif

      #ifdef ETXTBSY
	      case ETXTBSY :
	      {
		  printf( "ETXTBSY Text file busy\n");
	      }
      #endif

      #ifdef EIO
	      case EIO :
	      {
		  printf( "EIO I/O error\n");
	      }
      #endif

      #ifdef ENFILE
	      case ENFILE :
	      {
		  printf( "ENFILE Too many open files in system\n");
	      }
      #endif

      #ifdef EINVAL
	      case EINVAL :
	      {
		  printf( "EINVAL Invalid argument\n");
	      }
      #endif

      #ifdef EISDIR
	      case EISDIR :
	      {
		  printf( "EISDIR Is a directory\n");
	      }
      #endif

      #ifdef ELIBBAD
	      case ELIBBAD :
	      {
		  printf( "ELIBBAD Accessing a corrupted shared lib\n");
	      }
      #endif
	      
	      default :
	      {
		  
		 printf("Fehler unbekannt, Du kikst as´n nöchtern Kalf! wa?\n");
	      }
	  }
    }
		      