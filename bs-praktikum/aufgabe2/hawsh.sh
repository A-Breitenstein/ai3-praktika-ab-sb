#!/bin/bash 
# <hawsh, HAW-Shell. In Funktionalität stark eingeschraenkte Shell.>
# <Breitenstein, Bartel> 
# <11.11.2012>


#var initializiation
quit = false;

#Main---
main(){
while $quit != true; do

	type_prompt();
	read_command(&command);

	if command == "quit")
	then

		quit = true;
	
	else

		if lastZeichenUnd(command)
		then
	
			PIDstatus = fork();
			
			if PIDstatus < 0
			then
	
				echo "Unable to fork"	
	
				if PIDstatus == 0
				then
					execve(command,0)
				fi
			fi
	
		else
			if PIDstatus > 0
			then
				wait(PIDstatus, &status, 0)
			else
				execve(command,0)
			fi		
		fi
	
	fi

done
}
#letztes Zeichen berechnen
lastZeichenUnd(){
	
}
