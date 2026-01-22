@echo off
setlocal

set BASEDIR=%~dp0
set BASEDIR=%BASEDIR:~0,-1%

if not exist "%BASEDIR%\.mvn\wrapper\maven-wrapper.jar" (
  powershell -Command "Invoke-WebRequest -UseBasicParsing https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar -OutFile \"%BASEDIR%\.mvn\wrapper\maven-wrapper.jar\""
)

java ^
  -Dmaven.multiModuleProjectDirectory="%BASEDIR%" ^
  -classpath "%BASEDIR%\.mvn\wrapper\maven-wrapper.jar" ^
  org.apache.maven.wrapper.MavenWrapperMain %*
