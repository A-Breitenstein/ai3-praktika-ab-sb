#!/bin/bash

usage(){
	echo "
	frename.sh [-h] | <String>
	
	-h		:Zeigt den Hilfetext an.
	<String>	:An alle Dateien in dem aktuellen Verzeichnis,
			 wird der String rangehangen."
}

name(){
	# die schleife nimmt sich elem f¨¹r elem aus der ls operation
	for elem in *;do

		mv "$elem" "$elem$string"
		echo $elem '>>>' $elem$string
	done
}

if [ $# -gt 1 ];then
	usage
	exit 1
fi

case $1 in
	"-h")     
		usage
		exit 1
		;;
	"")
		usage
		exit 1
		;;	

	*)	
		string=$1
		name
		;;	
esac
