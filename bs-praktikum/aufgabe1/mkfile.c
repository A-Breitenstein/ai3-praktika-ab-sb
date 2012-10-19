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
#include <string.h>
//DEFINITIONS
char* msg_ask_filename = "Name der zu erstellenden Datei: ";
char filename[30];

//PROTOTYPES


//MAIN
int main(){

	int fh;

	printf(msg_ask_filename);
	//scanf("%s",filename);
	fgets(filename,31,stdin);
	int iLaenge = strlen(filename);
	//printf("Länge: %d\n", iLaenge);
	filename[iLaenge] = 0x0;
	fh = creat(filename,0700);

	if(-1 == fh){
		printf("Fehler beim erstellen der Datei: %s\n", filename);
	}else{
		printf("Die Datei %s wurde erfolgreich angelegt\n", filename);
		close(fh);
	}


	return 0;
}
