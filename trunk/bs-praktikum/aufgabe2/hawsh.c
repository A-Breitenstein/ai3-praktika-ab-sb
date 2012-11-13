// <hawsh, HAW-Shell. In Funktionalität stark eingeschraenkte Shell.>
// <Breitenstein, Bartel> 
// <11.11.2012>

//includes
#include <stdio.h>
#include <string.h>

#define ZERO 0

//DEFINITIONS
char[31] command;
bool quit = false;

//PROTOTYPES
void read_command(char[] &command);
void type_prompt();
bool lastZeichenUnd(char[] &command);

//MAIN
int main(){
	while (quit != true){

		type_prompt();
		read_command(&command);

		if(command == "quit")){

			quit = true;
	
		}else{

			if(lastZeichenUnd(&command)){
		
	
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
	bool lastZeichenUnd(char[] &command){
		const int iLaenge = strlen(command);
		
		return (command[iLaenge] == '&' );	
	}

	void read_command(char[] &command){
		fgets(command,31,stdin);
	}
	
	void type_prompt(){
		printf("prompt!");
	}


