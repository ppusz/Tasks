#!/bin/bash

open_site() {
    echo "Opening with default browser"
    xdg-open http://localhost:8080/crud/v1/task/getTasks
}

wait_for_tomcat() {
    tasks_current=$(systemctl show tomcat8 -p TasksCurrent --value)
    tasks_current=$(($tasks_current + 0))
    echo "$tasks_current"
    while [ $tasks_current -lt 30 ];    
    do
        sleep 1
        tasks_current=$(systemctl show tomcat8 -p TasksCurrent --value)
        tasks_current=$(($tasks_current + 0))
    done
}

runcrud_message=$(./runcrud.sh)
echo "$runcrud_message"
grep -qi "There were errors" <<< $runcrud_message
if [ "$?" != "0" ]; then
    wait_for_tomcat
    open_site
else
   echo "Errors found inside of runscrud.sh script"
   exit 1
fi