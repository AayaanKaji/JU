@echo off
setlocal enabledelayedexpansion

set "SRC_DIR=%CD%\.."
set "LIB_DIR=%SRC_DIR%\WEB-INF\lib"
set "CLASSES_DIR=%SRC_DIR%\WEB-INF\classes"
set "JAVAC=javac"

echo [INFO] Cleaning previous .class files...
if exist "%CLASSES_DIR%" (
    rmdir /s /q "%CLASSES_DIR%"
)
mkdir "%CLASSES_DIR%"

echo [INFO] Compiling Java source files...
set "FILES="
for /R "%SRC_DIR%" %%f in (*.java) do (
    set "FILES=!FILES! "%%f""
)

if not "!FILES!"=="" (
    %JAVAC% -cp "%LIB_DIR%\*" -d "%CLASSES_DIR%" -Xlint:unchecked -sourcepath "%SRC_DIR%" !FILES!
    echo [SUCCESS] Compilation complete.
) else (
    echo [INFO] No Java source files found.
)

endlocal
