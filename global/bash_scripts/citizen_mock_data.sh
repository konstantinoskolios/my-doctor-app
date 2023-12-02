#!/bin/bash

# Run the Python script to mock data for citizens
python ../src/main/resources/scripts/mock_data.py

# shellcheck disable=SC2181
if [ $? -eq 0 ]; then
  echo "Successfully mocked data for citizens."
else
  echo "Error: Mobile phone number, Tax Number, or Social Security Number conflicts with an existing record."
  echo "Please try running the script again or consider avoiding it because you already have mock data for citizens."
fi
