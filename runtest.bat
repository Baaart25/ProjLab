@echo off

call compile.bat
set expected=.\expected_out
set input=.\input
set cp=.\out
set mainclass=hu.grdg.projlab.Proto
set arg1=%1

set inp_file=%input%\%arg1%.txt
set out_file=%expected%\%arg1%_expected.txt
if not exist %inp_file% (
    echo Input does not exists
    goto end
)


java -cp %cp% %mainclass% > out_temp < %inp_file%
fc out_temp %out_file%
echo ---------------------------------------
if errorlevel 1 goto err
echo Test PASSED
goto end

:err
echo Test FAILED


:end
del /Q out_temp