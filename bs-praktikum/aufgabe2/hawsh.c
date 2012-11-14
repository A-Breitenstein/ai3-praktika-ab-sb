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
char command[100];
char quit = 0;
pid_t PIDstatus;
int status;
char *params[] = {NULL};

char *quitString = "quit", *version = "version", *help = "help";

//PROTOTYPES
void read_command(char *command);
void type_prompt();
void print_help();
void print_version();
char lastZeichenUnd(char *command);
char firstZeichenSlash(char *command);

//MAIN
int main(int argc, char **argv, char **envp){
	while (quit == FALSE){
 
                
		type_prompt();
		read_command(command);
                printf(getcwd(command,sizeof(command)));
                
		if(strcmp(command,quitString) == EQUAL){
			quit = TRUE;
		}else if(strcmp(command,version) == EQUAL){
                    print_version();
		}else if(strcmp(command,help) == EQUAL){
			print_help();
                        
                                
		//}else if(firstZeichenSlash(command) == EQUAL){
			//changeDIR someHow
                    
		}else{
			if(lastZeichenUnd(command)){
				PIDstatus = fork();

				if(PIDstatus < 0){
				printf("Unable to fork");

					if(PIDstatus == 0){
						execve(command,params,0);
					}
				}
			}else{
                                PIDstatus = fork();
                                printf("%d\n",PIDstatus);
				if(PIDstatus > 0){
					//waitpid(PIDstatus, &status, 0);
				}else{
					execve("ls",params,envp);
                                        
				}
			}
		}
	}
}

	//letztes Zeichen bestimmen
	char lastZeichenUnd(char *command){
            const int iLaenge = strlen(command);
            
            return (command[iLaenge-1] == '&' );
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
            printf("prompt!>");
	}
        void print_version(){
                printf("version: %.2f\n",VERSION);
        }
	
	void print_help(){
            printf("Du bekommst nichts Hilfe!\n");
	}