set projectpath=%~dp0
title Generate Allure Report
cmd /k "allure serve %projectpath%TestReport\allure-results"