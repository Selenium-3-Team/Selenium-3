set projectpath=%~dp0

set PATH=%projectpath%..\allure-2.19.0\bin;%PATH%;

title Generate Allure Report

cmd /k "allure serve %projectpath%TestReport\allure-results"
