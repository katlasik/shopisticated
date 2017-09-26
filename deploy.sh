#!/bin/bash

rm logs/*
touch logs/frontend.log logs/backend.log

./shopisticated-frontend/node_modules/http-server/bin/http-server -p 8788 ./shopisticated-frontend/dist &> logs/frontend.log & PID1=$!
java -jar ./shopisticated-backend/build/libs/shopisticated-backend-0.1.0.jar &> logs/backend.log & PID2=$!

trap "kill $PID1 $PID2" SIGINT

echo "Application has started on http://localhost:8788"

wait

