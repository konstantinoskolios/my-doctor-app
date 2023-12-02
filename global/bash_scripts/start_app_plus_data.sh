#!/bin/bash
./docker_start.sh

echo "Docker script is running in the background"

./citizen_mock_data.sh

read -p "You are ready to run the app. Do you want to do it manually or proceed with that? (Press '1' to start the app on local port 8500): " choice

# Check the user's response
if [ "$choice" == "1" ]; then
  cd ..
  ./gradlew bootRun
else
  # Exit the script
  echo "Closing the script..."
fi
