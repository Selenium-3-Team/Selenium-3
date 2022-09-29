cd /D %~dp0

title Generate Allure Report

allure serve %~dp0../TestReport/allure-results

pause
