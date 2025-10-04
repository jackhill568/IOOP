#!/bin/bash

set -e

LAB=$1

if [ -z "$LAB" ]; then
  echo "Usage: $0 [LabA|LabB]"
  exit 1
fi

SRC_DIR="Lab$LAB/src"
BIN_DIR="Lab$LAB/bin"

if [ ! -d "$SRC_DIR" ]; then
  echo "Source directory not found: $SRC_DIR"
  exit 1
fi

echo "Compiling..."
javac -d "$BIN_DIR" "$SRC_DIR"/*.java

echo "Done"

echo "Available classes to run:"
CLASS_FILES=("$BIN_DIR"/*.class)
for i in "${!CLASS_FILES[@]}"; do
  CLASS_NAME=$(basename "${CLASS_FILES[$i]}" .class)
  echo "$((i + 1)). $CLASS_NAME"
done

read -p "Select class to run [1-${#CLASS_FILES[@]}]: " CHOICE

INDEX=$((CHOICE - 1))

if [ -z "${CLASS_FILES[$INDEX]}" ]; then
  echo "Invalid choice."
  exit 1
fi

CLASS_TO_RUN=$(basename "${CLASS_FILES[$INDEX]}" .class)

echo "Running $CLASS_TO_RUN..."
cd "$BIN_DIR"
java "$CLASS_TO_RUN"
