cd /D %~dp0

title Start Selenium Hub Server

set GRID_PATH="..\Grid\selenium-server-standalone-3.141.59.jar"

java -jar %GRID_PATH% -role hub -port 4444

pause
