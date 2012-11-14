// <hawsh, HAW-Shell. In Funktionalität stark eingeschraenkte Shell.>
// <Breitenstein, Bartel>
// <11.11.2012>
//v: 0.01

//includes
#include <stdio.h>
#include <string.h>

#define ZERO 0
#define VERSION 0.01

//DEFINITIONS
char[31] command;
bool quit = false;

//PROTOTYPES
void read_command(char *command);
void type_prompt();
void printHelp();
bool lastZeichenUnd(char *command);
bool firstZeichenSlash(char *command);

//MAIN
int main(){
	while (quit != true){
		type_prompt();
		read_command(command);

		if(command == "quit")){
			quit = true;
		}else if(command == "version"){
			printf("version: %f",VERSION);
		
		}else if(firstZeichenSlash(command))
			//changeDIR someHow
		}else if(command == "help"){
			printHelp();
		}else{
			if(lastZeichenUnd(command)){
				PIDstatus = fork();

				if(PIDstatus < 0){
					printf("Unable to fork");

					if(PIDstatus == 0){
						execve(command,0);
					}
				}
			}else{
				if(PIDstatus > 0){
					wait(PIDstatus, &status, 0);
				}else{
					execve(command,0);
				}
			}
		}
	}

	//letztes Zeichen bestimmen
	bool lastZeichenUnd(char *command){
		const int iLaenge = strlen(command);

		return (command[iLaenge-1] == '&' );
	}
	
	bool firstZeichenSlash(char *command){
		
		return(command[0] == '/')
	}

	void read_command(char *command){
		fgets(command,31,stdin);
	}

	void type_prompt(){
		printf("prompt!");
	}
	
	void printHelp(){
		printf("Du bekommst nichts Hilfe!");
	}