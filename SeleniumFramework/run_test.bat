set projectpath=%~dp0

set PATH=%projectpath%..\apache-maven-3.8.6\bin;%PATH%;

cd %projectpath%

cmd /k "mvn test -DsuiteXmlFile=run_local.xml"