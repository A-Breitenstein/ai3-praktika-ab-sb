/*

Programm erstellt eine Datei auf anfrage eines Dateinamens.
Die Datei wird mit den Zugriffsrechten '0700'(Zugriff nur für Besittzer)
erzeugt.
Die erfolgreiche oder fehlgeschlagene Ausführung muss als Meldung
ausgegeben werden. 

<Breitenstein, Bartel> # <13.10.2012>
*/

//includes
#include <stdio.h>

//DEFINITIONS
char[] msg_ask_filename = "Name der neuen Datei: ";
char[31] filename;

//PROTOTYPES


//MAIN
int main(){

	int fd;

	printf(msg_ask_filename);
	filename = getchar();
	fd = creat(filename,0700);

	if(-1 == fh){
		printf("Fehler beim erstellen der Datei: %s\n", filename);
	}else{
		printf("Die Datei %s wurde erfolgreich angelegt\n", filename);
		close(fh);
	}


	return 0;
}
