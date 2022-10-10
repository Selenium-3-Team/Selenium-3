cd /D %~dp0
 
title Start Windows Desktop Node

set CHROME_DRIVER=-Dwebdriver.chrome.driver="..\Executables\chromedriver.exe"
set FIREFOX_DRIVER=-Dwebdriver.gecko.driver="..\Executables\geckodriver.exe"
set IE_DRIVER=-Dwebdriver.ie.driver="..\Executables\IEDriverServer.exe"

set CHROME_BROWSER=-browser "browserName=chrome, version=ANY, platform=WINDOWS, maxInstances=5"
set FIREFOX_BROWSER=-browser "browserName=firefox, version=ANY, platform=WINDOWS, maxInstances=5"
set IE_BROWSER=-browser "browserName=internet explorer, version=ANY, platform=WINDOWS, maxInstances=5"

set BROWSER_CONFIG=%CHROME_BROWSER% %FIREFOX_BROWSER% %IE_BROWSER%

set DRIVER_PATH=%CHROME_DRIVER% %FIREFOX_DRIVER% %IE_DRIVER%

set HUB_URL=http://192.168.1.12:4444/grid/register

java %DRIVER_PATH% -jar selenium-server-standalone-3.141.59.jar -role node -hub %HUB_URL% -port 5566 %BROWSER_CONFIG%

pause