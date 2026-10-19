@echo off
:: Recibir el nombre del proyecto como parámetro desde IntelliJ
rem set PROYECTO_NAME=%1
for %%I in (%cd%) do set PROYECTO_NAME=%%~nxI

if "%PROYECTO_NAME%"=="" (
    echo [ERROR] No se ha proporcionado el nombre del proyecto.
    echo Uso manual: build-native.bat NombreDelProyecto
    pause
    exit /b
)

echo =======================================================
echo  AUTOMATIZACION GRAALVM - PROYECTO: %PROYECTO_NAME%
echo =======================================================

:: 1. Limpieza de binarios previos en la raiz
echo [1/4] Limpiando binarios antiguos...
del /q *.exe *.obj *.pdb *.lib *.exp *.dll 2>nul

:: 2. Cargar las variables de entorno de Visual Studio 2022 (MSVC)
echo [2/4] Cargando entorno de Visual Studio 2022...
:: Usamos /v:off para evitar que vcvars64 altere el flujo del script
call "C:\Program Files (x86)\Microsoft Visual Studio\2022\BuildTools\VC\Auxiliary\Build\vcvars64.bat" >nul

:: 3. Ejecutar el java y el Agente de GraalVM
echo [3/4] Ejecutar el jar con el Agente de GraalVM
java -agentlib:native-image-agent=config-output-dir=src\META-INF\native-image -jar out\artifacts\%PROYECTO_NAME%.jar

:: 4. Compilar la aplicación nativa usando GraalVM detectando el JAR dinámicamente
echo [4/4] Compilando imagen nativa con GraalVM...

if not exist out\native mkdir out\native

native-image -Os -Djava.awt.headless=false -H:ConfigurationFileDirectories=src\META-INF\native-image -H:Path=out\native -H:NativeLinkerOption=/ENTRY:mainCRTStartup -H:Name=%PROYECTO_NAME% -jar out\artifacts\%PROYECTO_NAME%.jar


echo =======================================================
echo ¡Proceso finalizado con exito! Puedes cerrar esta ventana.
echo =======================================================
pause