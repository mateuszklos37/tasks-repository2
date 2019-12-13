call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Running crud WAR file error
goto fail

:runbrowser
call "%CHROME%"\Application\chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Opening website error
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished