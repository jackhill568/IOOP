#!/bin/bash

set -e

LAB=$1

if [ -z "$LAB" ]; then
  echo "Usage: $0 [A|B|C|D|E]"
  exit 1
fi

LAB_DIR="Lab$LAB"
SRC_DIR="$LAB_DIR/src"
BIN_DIR="$LAB_DIR/bin"

if [ ! -d "$SRC_DIR" ]; then
  echo "Source directory not found: $SRC_DIR"
  exit 1
fi

mkdir -p "$BIN_DIR"

echo "Compiling Java files for $LAB_DIR..."

# Compile recursively (handles packages too)
javac -d "$BIN_DIR" $(find "$SRC_DIR" -name "*.java")

echo "Done compiling."

echo "Available classes to run:"
# Find all class files in BIN_DIR (recursively)
mapfile -t CLASS_FILES < <(find "$BIN_DIR" -name "*.class" | sort)

for i in "${!CLASS_FILES[@]}"; do
  # Get fully qualified class name by stripping BIN_DIR and replacing slashes
  CLASS_NAME=$(echo "${CLASS_FILES[$i]}" | sed "s|^$BIN_DIR/||; s|/|.|g; s|\.class$||")
  echo "$((i + 1)). $CLASS_NAME"
done

read -p "Select class to run [1-${#CLASS_FILES[@]}]: " CHOICE

INDEX=$((CHOICE - 1))

if [ -z "${CLASS_FILES[$INDEX]}" ]; then
  echo "Invalid choice."
  exit 1
fi

CLASS_TO_RUN=$(echo "${CLASS_FILES[$INDEX]}" | sed "s|^$BIN_DIR/||; s|/|.|g; s|\.class$||")

echo "Running $CLASS_TO_RUN..."
cd "$BIN_DIR" java "$CLASS_TO_RUN"
