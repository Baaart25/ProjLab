# ProjLab - Proto readme

## 1. Programozás
Semmit extra nincsenek hülye fvk mint a skeletonban. Az előző doksiban meghatározott kimeneti nyelv
üzeneteit a `ProtoIO.output` fügvénnyel lehetséges. Az összes doksiban meghatározott üzenet
(az elgépelések elkerülés végett) a `ProtoIO.OutputMessages.OUTPUT_NAME` string konstansokként lesznek
elérhetőek. Mindent úgy kéne ahogy a multkori doksiban van.

## 2. Tesztelés
A múltkor megírt tesztesetekhez megkell csinálni a *bemenet.txt*-ket és az *elvárt-kimenet.txt*-ket.
Ezeke az `input` és `expected_out` mappában *teszeset_teljes_neve*.txt néven kellenének.

