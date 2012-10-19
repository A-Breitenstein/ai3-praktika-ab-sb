#!/bin/bash


usage() {
	
	echo "
	 try_host [-h|-s <sec>] <hostname>|<IP-Address>

	 -h		:Nur Ausgabe der \"Usage Message\"
	 -s <sec>	:Der Ping wird zyklisch alle <sec> Sekunden ausgef¨¹hrt
			 Fehlt die -s Option, wird der ping alle 10 Sekunden ausgef¨¹hrt."
	exit 1 
}

tryHost() {



	while true; do
        	# aus dem ping request den received teil extrahieren in dem das
		# ergebnis der ping funktion mit dem "|" zeichen an die grep funktion
		# geschickt wird.

		# mit der pipe ( | ) kann man funktions resultate an andere funktionen
		# als aufruf parameter schicken.
 
		result=$( ping -c 1 -W $delayInSec $targetHost | grep "received" )
		
		# mit ${varNameOhneDollar:POSITION:laenge} kann man substrings von einem
		# string erzeugen.
		# Hier wird der received Package count aus dem string result geholt
		receivedPackages=${result:23:1}
		# echo "ReceivedPackages:" $receivedPackages
		
		# wenn mehr als 1 paket das ziel erreicht hat dann ok sonst fail
		if [ $receivedPackages -gt 0 ]; then
			echo $targetHost OK
		else
			echo $targetHost FAILED
		fi
		
		# legt den prozess schlafen, hoffe ich ~~
		sleep $delayInSec
		
	done
}

# bei mehr als 3 angegeben aufruf parametern hilfe und exit
if [ $# -gt 3 ];then
	usage

fi

case $1 in
	"-h")
		usage
		;;
	#wenn der erste parameter -s dann muss der zweite eine zahl groe?er 0 sein
	"-s")	case $2 in
			"")
				usage
				;;
			 *)	
				if [ $2 -gt 0 ];then
					delayInSec=$2
				else
					usage
				fi
				;;
		esac 
	
		;;

	# kein parameter => hilfe
	"")	
		usage
		;;
	# nur der Hostname (im guten glauben an den user)
	 *)
		targetHost=$1
		delayInSec=10
		tryHost
		;;	
esac







# fuer den fall das -s X Hostname angegeben wird, muss der dritte parameter der hostname
# sein
case $3 in
	"")
		usage
	
		;;
	*) 	targetHost=$3
		tryHost
		;;
esac 


