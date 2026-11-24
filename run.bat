@echo off
setlocal
chcp 65001 >nul
set "MAVEN_OPTS=-Dfile.encoding=UTF-8"

where mvn >nul 2>nul
if errorlevel 1 (
    set "MVN=%LOCALAPPDATA%\Programs\apache-maven-3.10.2\bin\mvn.cmd"
    if not exist "%LOCALAPPDATA%\Programs\apache-maven-3.10.2\bin\mvn.cmd" (
        set "MVN=%LOCALAPPDATA%\Programs\apache-maven-3.9.11\bin\mvn.cmd"
    )
    if not exist "%MVN%" (
        echo Maven was not found. Install Maven 3.9 or newer and try again.
        set "RESULT=1"
        goto finish
    )
) else (
    set "MVN=mvn"
)

call "%MVN%" clean compile
set "RESULT=%ERRORLEVEL%"
if not "%RESULT%"=="0" goto finish
call "%MVN%" exec:java
set "RESULT=%ERRORLEVEL%"

:finish
echo.
echo Press any key to close this window...
pause >nul
endlocal & exit /b %RESULT%
