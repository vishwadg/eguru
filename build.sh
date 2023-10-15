#!/usr/bin/env bash

folders=(
  authentication-service
  tutor-service
  student-service
)

for folder in ${folders[@]}
 do
      docker build . --build-arg build_folder=${folder} -t "vishwaghimire/${folder}:latest"
done