# Projlab SkeletonTester dokumentáció

### 1. Használat
A teszteket a Skeleton osztályban, statikus fvként kell megvalósítnai, a main 
fvben beregisztrálni őket. A main a tesztek regisztrálása után a felhasználót kéredezgeti a tesztlések
futtásáról.  
Kód:
```JAVA
SkeletonTester.registerTest("PlayerStepOnUnstableIceTile", Skeleton::playerStepOnUnsatbleIceTile);
``` 
A tesztek megvalósítása a Skeleton osztály fvin keresztül történik.
Itt a megtervezett szekvencia lépéseit kéne leprogramozni.
A SkeletonTester működéséhez először jelezni kell, hogy új tesztelés kezdődött.
Ez a `SkeletonTester.beginTest("Test neve")` fvvel lehetséges.
A teszt végét a `SkeletonTester.endTest()`-el kell jelezni. Ezek elmulasztása _undefined behaviour_-t okoz.  
  
 A tesztek működése közben a `SkeletonTester` automatikusan megjeleníti a hívott függvény nevét és az ismert
paramétereket. Ehhez viszont szükség van a teszt során használt objektumok névvel beregisztrálásához a rendszerbe.
A regisztrálást a `SkeletonTester.addNamedReference(obj,"obj neve")`. A tesztelés során a meghívott függvényeket a 
`SkeletonTester.SkeletonTester.call` függvénnyel tudjuk. Ennek első paramétere mindíg az a példány aminek 
tagfüggvényének hívását jeleztük. A hívott fv. többi paraméterét a `call` fv.nek kell megadni, az eredeti sorrendben.
A kapott az addig beregisztrált NamedReferencek alapján írja ki. A hívás végét a `SkeletonTest.creturn()`-nel jelezzük. 
Erre a kimenet fancy megejelnítéséhez van szükség.
  
Példa:
```JAVA
public void setCurrentTile(IceTile newTile) {
       SkeletonTester.call(this, newTile);

       this.currentTile = newTile;

       SkeletonTester.creturn();
   }
```
  
### 2. Segédfüggvények

- Szám beolvasása: `SkeletonTester.askNumber("Valami szöveg")`
- Szöveg beolvasása: `SkeletonTester.askString("Valami message")`
  
Én ezeket konstruktorból hasznéltamn amikor be kellett állítani a változtatható értékeket.
  
### 3. Egyéb
Commentek minden fv folot legyenek pls, a nyelv nekem mind1 :D.
Pls a @author tagot töltsétek ki hogy tudjuk h ki csinálta (_**DANI** mindenki a saját nevét_)