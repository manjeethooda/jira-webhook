#!/bin/bash
echo "Stopping apiserver"

#cmd="cd .."
PORT_NUMBER=9000
cmd="lsof -i tcp:${PORT_NUMBER} | awk 'NR!=1 {print \$2}' | xargs -L1 bash -c 'if [ -s \$0 ] ; then kill \"\$0\"; else echo \"Apiserver not running\"; fi' "
echo $cmd
#lsof -i tcp:${PORT_NUMBER} | awk 'NR!=1 {print $2}' | xargs kill 
lsof -i tcp:${PORT_NUMBER} | awk 'NR!=1 {print $2}' | xargs -L1 bash -c 'if [ -s $0 ] ; then kill "$0"; else echo "Apiserver not running"; fi'