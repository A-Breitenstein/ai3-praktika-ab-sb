#!/bin/bash

targetHost=127.0.0.1
delayInSec=5

while true; do

	result=$( ping -c 1 -W $delayInSec $targetHost | grep "received" )
	echo $result
	sleep $delayInSec

done
