#!/bin/bash

# Open Frontend terminal
cd FrontendVite && start "Frontend" "C:\Program Files\Git\bin\bash.exe" --login -i &

# Open Backend terminal
cd ../Backend && start "Backend" "C:\Program Files\Git\bin\bash.exe" --login -i &

# Open Docker terminal
cd .. && start "Docker" "C:\Program Files\Git\bin\bash.exe" --login -i &

# Open PSQL terminal
start "PSQL" "C:\Program Files\Git\bin\bash.exe" --login -i &

# Open Git terminal
start "Git" "C:\Program Files\Git\bin\bash.exe" --login -i & 