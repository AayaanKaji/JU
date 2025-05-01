#!/bin/bash

set -e  # Exit immediately on any error

SRC_DIR="$(pwd)/.."
LIB_DIR="$SRC_DIR/WEB-INF/lib"
CLASSES_DIR="$SRC_DIR/WEB-INF/classes"
JAVAC=javac

echo "[INFO] Cleaning previous .class files..."
if [ -d "$CLASSES_DIR" ]; then
    rm -rf "$CLASSES_DIR"
fi
mkdir -p "$CLASSES_DIR"

echo "[INFO] Compiling Java source files..."
find "$SRC_DIR" -type f -name "*.java" -print0 | \
  xargs -0 --no-run-if-empty $JAVAC -cp "$LIB_DIR/*" -d "$CLASSES_DIR" -Xlint:unchecked -sourcepath "$SRC_DIR"

echo "[SUCCESS] Compilation complete."
