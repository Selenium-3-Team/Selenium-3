set projectpath=%~dp0

cd %projectpath%\src\test\resource\grid

cmd /k "docker-compose -f .\docker-compose.yml up -d"