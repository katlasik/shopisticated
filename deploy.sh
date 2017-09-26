#!/bin/bash

rm logs/frontend.log logs/backend.log
touch logs/frontend.log logs/backend.log

./shopisticated-frontend/node_modules/http-server/bin/http-server -p 8788 ./shopisticated-frontend/dist &> logs/frontend.log & 
java -jar ./shopisticated-backend/build/libs/shopisticated-backend-0.1.0.jar &> logs/backend.log &

echo "Application started on http://localhost:8788"
