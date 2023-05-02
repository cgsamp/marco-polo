#!/bin/bash

while true; do
  curl --max-time 1 http://10.220.48.230/marco
  echo
  sleep 0.1
done
