@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM ----------------------------------------------------------------------------

@IF "%DEBUG%" == "" @ECHO OFF
@SETLOCAL ENABLEDELAYEDEXPANSION ENABLEEXTENSIONS

SET ERROR_CODE=0

@REM Get directory of this script without trailing backslash
SET "MAVEN_PROJECTBASEDIR=%~dp0"
IF "!MAVEN_PROJECTBASEDIR:~-1!"=="\" SET "MAVEN_PROJECTBASEDIR=!MAVEN_PROJECTBASEDIR:~0,-1!"

IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties" (
    ECHO Error: %MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties does not exist.
    GOTO error
)

@REM Find java.exe
IF DEFINED JAVA_HOME (
    SET "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) ELSE (
    SET "JAVA_EXE=java.exe"
)

"%JAVA_EXE%" -version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    IF EXIST "%USERPROFILE%\.jdks\ms-21.0.12.1\bin\java.exe" (
        SET "JAVA_EXE=%USERPROFILE%\.jdks\ms-21.0.12.1\bin\java.exe"
    ) ELSE (
        FOR /D %%D IN ("%USERPROFILE%\.jdks\*") DO (
            IF EXIST "%%D\bin\java.exe" SET "JAVA_EXE=%%D\bin\java.exe"
        )
    )
)

SET "WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"
SET "WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain"

"%JAVA_EXE%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" -classpath "%WRAPPER_JAR%" %WRAPPER_LAUNCHER% %*
IF %ERRORLEVEL% NEQ 0 GOTO error
GOTO end

:error
SET ERROR_CODE=1

:end
@ENDLOCAL & SET ERROR_CODE=%ERROR_CODE%
exit /B %ERROR_CODE%
