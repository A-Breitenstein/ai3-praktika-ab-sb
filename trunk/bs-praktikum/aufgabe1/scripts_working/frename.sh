#!/bin/bash

usage(){
	echo "Help text ...."
}

rename(){
	# die schleife nimmt sich elem für elem aus der ls operation
	for elem in $( ls ); do
	
		mv $elem $elem"$string"
	done
}

if [ $# -gt 1 ];then
	usage
	exit 1
fi

case $1 in
	"")     
		usage
		exit 1
		
	;;	

	*)	
		string=$1
		rename

	;;	
esac
