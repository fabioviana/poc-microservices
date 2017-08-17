#!/bin/ash

c=0
while :
do
    curl -s $1
    if [[ $? -eq 0 ]]; then
    	echo $1 ready
    	break
	else
		echo $1 NOT ready
		if [[ "$c" -gt "$2" ]]; then
			echo timeout
			exit 1
		fi
		c=$((c+1))
		sleep 1
	fi
done

CLI="$@"
ARGS="$1 $2"
C=${#ARGS}

exec ${CLI:$C}