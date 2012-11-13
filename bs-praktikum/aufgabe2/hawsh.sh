// <hawsh, HAW-Shell. In Funktionalität stark eingeschraenkte Shell.>
// <Breitenstein, Bartel> 
// <11.11.2012>


//var initializiation
quit = false;

//Main---
int main(){
while (quit != true){

	type_prompt();
	read_command(&command);

	if(command == "quit")){
	

		quit = true;
	
	}else{

		if(lastZeichenUnd(command)){
		
	
			PIDstatus = fork();
			
			if(PIDstatus < 0){
			
	
				echo "Unable to fork"	
	
				if(PIDstatus == 0){
				then
					execve(command,0)
				}
			}
	
		}else{
			if(PIDstatus > 0){
			
				wait(PIDstatus, &status, 0)
			}else{
				execve(command,0)
			}		
		}
	
	}

}

//letztes Zeichen berechnen
bool lastZeichenUnd(char[] *command){
	
}
