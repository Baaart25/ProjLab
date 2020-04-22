# ProjLab - Proto readme

## 1. Programozás
Semmit extra nincsenek hülye fvk mint a skeletonban. Az előző doksiban meghatározott kimeneti nyelv
üzeneteit a `ProtoIO.output` vagy `ProtoIO.outputf` fügvénnyel lehetséges. Az összes doksiban meghatározott üzenet
(az elgépelések elkerülés végett) a `ProtoIO.OutputMessages.OUTPUT_NAME` string konstansokként lesznek
elérhetőek. Mindent úgy kéne ahogy a multkori doksiban van.  
__Minden osztály, metódus fölé javadoc comment pls.__

## 2. Tesztelés
A múltkor megírt tesztesetekhez megkell csinálni a *bemenet.txt*-ket és az *elvárt-kimenet.txt*-ket.
Ezeke az `input` és `expected_out` mappában *teszeset_teljes_neve*.txt néven kellenének.  
A tesztesetek futtatásának megkönnyítéséhez lett létrehozva a (windows alatt működő) 
`runtest.bat` script. Így a tesztesetek futtatása a `runtest.bat <teszt_eset_neve_.txt_nelkul>`.
A sikeres futást a Test PASSED, a sikertelent a Test FAILED jelzi.  
Példa: `> runtest.bat player_steps_on_hole_tile`
