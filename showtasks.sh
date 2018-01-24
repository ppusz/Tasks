#!/bin/bash

open_site() {
    echo "Opening with default browser"
    xdg-open http://localhost:8080/crud/v1/task/getTasks
}

runcrud_message=$(./runcrud.sh)
echo "$runcrud_message"
grep -qi "There were errors" <<< $runcrud_message
if [ "$?" != "0" ]; then
    open_site
else
   echo "Errors found inside of runscrud.sh script"
   exit 1
fi