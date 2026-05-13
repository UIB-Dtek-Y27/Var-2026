# Rapport – innlevering 4
**Team:** **A-Laget**

**Medlemmer:**
* Isak Graarud
* Johannes Helle Moe
* Theodor Flornes
* Tuva Kvamme
* Synne Hermansen
* Bernhard Bors

---
## **Spillbeskrivelse**
Vårt spill er et Mario Bros-inspirert plattform-spill. Spilleren navigerer gjennom flere nivåer hvor målet er å løse hver level ved å overkomme hindringer, beseire fiender og til slutt komme til målområdet.


Viktige aspekter i spillet:
1. Spillfigur:
* Spilleren kan styres med piltaster - gå til høyre/venstre, hoppe.
* Underveis kan man få "power-ups" ved å hoppe på lucky blocks som endrer spillerens egenskaper.
2. Spillverden:
* 2D-Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»).
* Vegg – vertikal flate som spilleren ikke kan gå gjennom.
* Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle.
* Verden er større enn skjermen, og skjermen beveger seg bortover med spilleren (á la Mario Bros).
3. Fiender:
* Fiender (zombier) som beveger seg og er skadelige ved berøring.
* Kan bekjempes ved å hoppe på dem.
4. Spillmoduser:
* I hovedmenyen kan spilleren velge mellom Story modus og enkelt level-modus.
* I Story mode spiller man gjennom alle leveler i kronologisk rekkefølge. Om spilleren dør mister man progresjonen og må starte fra start igjen.
* I Level mode kan spiller velge hvilket level en vil spille, og spille seg gjennom ett level om gangen.
5. Død og progresjon
* Om man blir tatt av zombiene mister man ett av tre liv. Om spilleren ikke har liv igjen eller faller utenfor verden må man starte levelen på nytt.
6. Ressurser
* Ved å finne lucky blocks kan man oppnå power-ups - enten at karakteren blir raskere eller får et liv tilbake.
* Man starter med tre liv - power-ups kan gi ekstra liv om man har mistet ett.
7. Score og Leaderboard
* En spillers score opparbeides basert på tid brukt, zombies drept og powerups plukket opp.
* De beste scorene lagres i en leaderboard-oversikt.
* Det finnes ulike leaderboards til forskjellige spillmoduser og leveler.
8. Brukergrensesnitt og stil:
* Spillet har en klassisk, Mario-aktig tegneserie-estetikk.
* Hovedmeny med innstillinger, mulighet til å spille story mode, oversikt over nivåer, credtits, settings og highscores.


---
## Utviklingsprosess

Vi bruker en slags Scrum/Kanban hybrid og setter milepæler som vi jobber mot. De består av flere TODOS som utgjør en større feature eller MVP.

Flyten ser slik ut:
1. Finn en task i [Trello](https://trello.com/b/UkQgtCrS/inf112-a-laget).
2. Finn ut av hva som må til for å lage den featuren. Spør gjerne andre medlemmer om de har peiling på det eller har noe input.
3. Implementer featuren i en egen branch - lag også gjerne tester.
4. Lag en Merge Request i Git. Med mindre det er en veldig liten ting burde minst én annen reviewe forespørselen.
5. Merge inn i Main.
6. Legg til litt dokumentasjon slik at andre kan skjønne hvordan man bruker det.
7. Oppdater Trello. Fant du noen nye ting vi mangler? Er det noe som blokkerer og gjør at du ikke kan fullføre? Mangler featuren noe som bør legges til senere? Fant du bugs?

---
## MVP

1. **Splash Screen** som presenterer gruppa og prosjektet mens spillet laster, med fade-animasjon og overgang til hovedmeny.

2. **Hovedmeny** med følgende navigasjon:
    - **Story Mode** – starter spilleren i en navneskjerm før historiesekvens og level 1
    - **Levels** – lar spilleren velge enkeltlevel og registrere navn
    - **How To Play** – forklarer kontroller og spillmoduser
    - **Settings** – endre oppløsning, musikk- og SFX-volum
    - **Scoreboard** – viser leaderboard for story mode og enkeltleveler
    - **Credits** – viser bidragsytere
    - **Exit** – avslutter applikasjonen

3. **Spillverden med fysikk**
    - 2D-plattformspill med Box2D-fysikk og TiledMap-kart
    - Spiller kan bevege seg horisontalt, hoppe, spurte og falle
    - Kamera følger spilleren gjennom nivået
    - Parallax-bakgrunn gir dybdefølelse

4. **Fiender og kamp**
    - Zombie-fiender som patruljerer og gir skade ved berøring
    - Chonker-fiender med tyngre bevegelse og høyere HP
    - Fiender beseires ved å hoppe på hodet deres
    - Spilleren har tre liv med midlertidig udødelighet etter skade

5. **Lucky Blocks og Power-ups**
    - Lucky blocks skjuler power-ups som aktiveres ved kollisjon
    - HealPickup gjenoppretter ett liv
    - SpeedBoostPickup øker farten midlertidig

6. **Lyd koblet til hendelser**
    - Musikk og SFX via event-buss – hopp, skade, fiende drept, meny, gameplay

7. **Score og Leaderboard**
    - Score basert på tid, drepte fiender og plukket opp power-ups
    - Separate leaderboards for story mode og hvert enkeltlevel
    - Scores lagres persistent med JSON

8. **Story Mode**
    - Historiesekvens med lysbildefremvisning og musikk før level 1
    - Spilleren spiller gjennom alle nivåer i rekkefølge
    - Akkumulert tid bæres videre mellom nivåer
    - Dør man mister man all progresjon

9. **Game Over og nivåavslutning**
    - Game Over-skjerm med mulighet for å prøve igjen eller returnere til meny
    - Victory-sekvens ved fullført nivå før overgang til leaderboard eller neste nivå
    - Pause-skjerm med frosset spillverden i bakgrunnen
---
## Brukerhistorier

### Menyer og navigasjon
#### 1. Splash Screen
* **Som spiller ønsker jeg å se en splashscreen når jeg starter spillet som vises i noen sekunder.**
* Her står gruppenavn, spillnavn og framework (libGDX),
  fordi jeg ønsker en profesjononell introduksjon som viser hvem som har laget spillet og hvilken teknologi som brukes.
    * **Akseptansekriterie:**
        * Når spillet åpnes skal man umiddelbart se splash screen med relevant info.
        * Skjermen forsvinner automatisk etter et gitt antall sekunder, og går over i startmenyen.
    * **Arbeidskrav:**
        * Opprett SplashScreen-klasse som implementerer Screen.
        * Legg til visning av gruppenavn, spillnavn og LibGDX.
        * Implementer timer som automatisk bytter til MainMenuScreen etter noen sekunder.

#### 2. Startmeny og instillinger
* **Som spiller vil jeg kunne starte et nytt spill for å komme i gang,**
  fordi jeg vil begynne å spille uten unødvendige steg.
* **Som spiller vil jeg kunne velge hvilket level jeg vil spille,**
    fordi jeg vil spille en gitt level uten å måtte spille meg gjennom andre leveler først.
* **Som spiller vil jeg gå inn i innstillinger og endre resolution, eller skru av og på musikk og sfx,**
  fordi jeg ønsker å tilpasse lydnivå og oppløsning etter eget ønske eller situasjon.
* **Som spiller vil jeg kunne gå inn i How-To-Play,**
  fordi jeg vil ha en oversiktlig måte å lære eller friske opp brukerkontroller.
* **Som spiller vil jeg kunne se Leaderboardet,**
    fordi jeg vil kunne se om jeg har klart å slå min egen rekord.
* **Som spiller vil jeg kunne se credits,** fordi jeg vil kunne se hvem som krediteres for spillet.
* **Som spiller vil jeg kunne avslutte spillet,**
  fordi jeg trenger en ryddig måte å avslutte når jeg er ferdig med å spille.
    * **Akseptanskriterie:**
        * Etter splash screen vises det en meny med fungerende knapper som sender til riktig state.
        * Story Mode ber om spillernavn og starter spilleren på level 1.
        * Pick Level viser en meny med mulighet til å velge ønsket level.
        * Innstillingene lar spilleren skru musikk/SFX av og på, og endre oppløsning.
        * Credits viser oversikt over hvilke utviklere som har bidratt med hva.
        * Leaderboard viser en oversikt over de beste scorene
        * Exit-knappen lukker applikasjonen på en trygg måte.
    * **Arbeidskrav**
        * Opprett MainMenuScreen med knapper: Story Mode, Pick Level, Settings, How To Play, Exit
        * Opprett Story Mode knapp som sender spillet inn i StoryModeScreen.
        * Opprett Pick Level knapp som sender spillet inn i PickLevelScreen.
        * Opprett SettingsScreen med toggles for musikk/SFX og dropdown for oppløsning
        * Koble oppløsningsvalg til Gdx.graphics.setWindowMode()
        * Lagre innstillinger med Preferences og last dem ved oppstart.
        * Koble Exit-knapp til Gdx.app.exit()

#### 3. Levelmeny
* **Som spiller ønsker jeg en skjerm for å kunne velge level når jeg trykker start i hovedmenyen,**
  fordi jeg ønsker å kunne velge mellom de forskjellige nivåene.
    * **Akseptansekriterie:**
        * Etter å ha trykket "Pick Level" vises en skjerm med mulighet for valg av level.
    * **Arbeidskrav:**
        * Opprett PickLevelScreen med én knapp per tilgjengelige level
        * Koble hver knapp til å laste riktig level

### Bevegelse og Verden

#### 4. Grunnleggende bevegelse
* **Som spiller vil jeg kunne styre figuren min horisontalt og hoppe ved hjelp av tastaturet,**
  fordi jeg trenger å navigere forbi hindringer og fiender på brettet.
    * **Akseptansekriterier:**
        * Spilleren flytter seg høyre/venstre ved bruk av piltaster eller A/D.
        * Spilleren hopper ved bruk av Space, W eller pil opp.
        * Karakteren bytter til riktig animasjon (løpe, hoppe, stå i ro) basert på bevegelse.
    * **Arbeidskrav:**
        * Implementer horisontal bevegelse med med A/D og piltaster.
        * Implementer hopp med Space, W og pil opp.
        * Koble bevegelsestilstand til animasjoner (idle, run, jump)
        * Sørg for at figuren speiles riktig basert på bevegelsesretning

#### 5. Fysikk og kollisjon
* **Som spiller forventer jeg at figuren min stoppes av vegger, plattformer og tyngdekraft,**
  fordi spillverdenen må føles fysisk logisk slik at jeg ikke faller gjennom bakken.
    * **Akseptansekriterier:**
        * Karakteren faller nedover når den ikke står på fast grunn.
        * Karakteren kan ikke bevege seg gjennom faste blokker i kartet.
        * Spilleren kan kun hoppe når figuren står på bakken.
    * **Arbeidskrav:**
        * Sett opp Box2D-verden med tyngdekraft
        * Opprett statiske kropper for bakke, vegge og plattformer fra TiledMap
        * Begrens hopp til kun når spilleren har bakkekontakt (isGrounded-flag)

#### 6. Kamerastyring (Scrolling)
* **Som spiller vil jeg at kameraet skal følge etter spillfiguren min når jeg beveger meg,**
  fordi jeg alltid trenger å se området rundt meg og hva som kommer lenger fremme på brettet.
    * **Akspetansekriterie:**
        * Kameraet sentreres rundt spilleren (eller følger etter med en viss forskyvning) under bevegelse.
        * Kameraet stopper ved kartets ytterkanter slik at man ikke ser utsiden av spillverdenen.
    * **Arbeidskrav:**
        * Implementer kamera som følger spillerens X/Y-posisjon.
        * Legg til grenser slik at kamera stopper ved kartets ytterkanter.

### Mekanikk og Kamp

#### 7. Angrep og Bekjempelse
* **Som spiller vil jeg kunne uskadeliggjøre zombier ved å hoppe på hodet deres,**
  fordi jeg trenger en metode for å rydde vei uten å ta skade selv.
    * **Akseptansekriterier:**
        * Når spillerens bunn-hitbox (føttene) treffer fiendens topp-hitbox, tar fienden skade.
        * Fienden dør og forsvinner fra kartet når HP er 0.
    * **Arbeidskrav:**
        * Definer separat bunn-hitbox på spiller og topp-hitbox på fiender.
        * Implementer kollisjonslytter som sjekker om spiller treffer en fiende ovenfra.
        * Gi fienden skadesystem og fjern den fra verden når HP = 0
        * Gi spilleren et lite hopp-boost etter vellykket tramp.

#### 8. Skade og Liv (HP)
* **Som spiller vil jeg ha tre liv. Jeg vil miste liv hvis jeg går inn i en fiende, og få en kort periode med udødelighet etterpå,**
  fordi spillet skal være utfordrende, men også gi meg litt tid til å reagere etter å ha tatt skade.
    * **Akseptansekriterier:**
        * Kollisjon med en fiende reduserer spillerens HP.
        * Spilleren får et kort tidsvindu (hitTimer) hvor de ikke kan ta ytterligere skade.
    * **Arbeidskrav**
        * Implementer HP-system med maks 3 liv på spilleren.
        * Implementer hitTimer som gir midlertidig udødelighet etter skade.
        * Visualiser gjenværende liv med hjerter i HUD-en

#### 9. Lucky Blocks og Power-ups
* **Som spiller ønsker jeg å finne lucky blocks på brettet hvor jeg kan plukke opp power-ups,**
  fordi jeg ønsker fordeler som gjør det lettere å overleve mot fiendene.
    * **Akseptansekriterier:**
        * Power-ups ligger skjult i "lucky-blocks" på kartet.
        * Når spilleren kolliderer med en power-up, fjernes den fra kartet og en effekt påføres (f.eks. gjenopprette HP).
    * **Arbeidskrav**
        * Implementer LuckyBlock-klasse som skjuler en power-up
        * Implementer HealPickup som restorerer 1 HP ved kollisjon.
        * Implementer SpeedBoostPickup som øker fart midlertidig.
        * Fjern power-up fra verden etter at den er plukket opp.

#### 10. Game Over
* **Som spiller forventer jeg å se en Game-Over-skjerm hvis jeg dør, med mulighet til å prøve igjen,**
  fordi jeg raskt vil tilbake i spillet.
    * **Akseptansekriterier:**
        * Når spillerens HP er 0 eller lavere, fryses spillet, og en "Game Over"-skjerm vises.
        * Skjermen inneholder en knapp for å restarte brettet.
    * **Arbeidskrav**
        * Opprett GameOverScreen med "Prøv igjen"-knapp
        * Trigger GameOverScreen når spillerens HP når 0.
        * Koble "Prøv igjen"-knapp til å laste samme level på nytt

#### 11. Fullføre nivå
* **Som spiller vil jeg nå et målpunkt i enden av brettet,**
  fordi jeg vil kjenne på en følelse av progresjon.
  * **Akseptansekriterie:**
    * Kollisjon med mål-flagget på brettet trigger en level-overgang.
  * **Arbeidskrav:**
    * Plasser målflagg som objekt i TiledMap
    * Implementer kollisjonslytter for kontakt mellom spiller og målflagg
    * Vis en kort "Level Complete"-skjerm ved fullføring
    * Gå videre til neste level eller tilbake til levelmenyen

---
## Retroperspektiv

### Hva fungerer bra

Rollene i teamet fungerer fortsatt godt og det er heller ikke nå identifisert behov for nye roller.
* **Team Lead (Isak)** passer på at alle har oppgaver og fordeler nye oppgaver slik at ingen står uten noe å gjøre.
* **Design-ansvarlig (Theodor)** har fortsatt å bidra sterkt både med grafikk og funksjonalitet.
* **Test Queen (Tuva)** holder fokus på testing og jobber aktivt med å øke testdekningen.
* **DJ (Johannes)** har implementert vår audiomanager spillmusikk, lydeffekter og flere nyttige utils.
* **Struktur-ansvarlig (Synne)** passer på at vi følger MVC, SOLID og at koden holdes modulær. Refaktorerer om nødvendig.
* **Rapport-ansvarlig (Bernhard)** fortsetter å skrive dokumentasjon og rapporter, samt følger opp at vi møter oppgavekriterier.

Teamet er fornøyd med prosjektmetodikken og valg som er tatt. Gruppedynamikken er fortsatt sterk, med god kommunikasjon og jevn fordeling av innsats. Alle er involvert og bidrar aktivt med ideer og til gjennomføringen.
Vi har fast fysisk møte én gang i uka. På møte har vi Stand Up hvor hver enkelt går gjennom hva de har gjort siden sist. Dette sørger for at alle bidrar og er klar over hva andre gjør. 
På møter fordeler vi oppgaver og diskuterer strategiske valg. Dette gir oss en felles forståelse for hvilken retning vi skal ta videre.
Møter er også en fin anledning til å ta opp eventuelle usikkerheter eller uenigheter, og avklare disse så raskt som mulig.
Utenom møter kommuniserer vi gjennom Discord eller fysisk på skolen.

Vi har kommet til et produkt vi er godt fornøyd med. Alle kritiske funksjoner er på plass, og spillet føles nå mer helhetlig og komplett. Vi synes selv det er gøy å spille.

### Hva vi har gjort siden sist
* Fortsatt refaktorisering  av prosjektet til å følge **MVC- og SOLID-arkitektur**, som har gitt bedre struktur, og gjort det lettere å legge til nye features.
* Fjernet bugs knyttet til hopp-funksjon og knappetrykk i menyer.
* Laget nytt map for level 3.
* Skrevet flere tester for å øke test coverage.
* Tegnet nye sprites for power-ups.
* Implementert et score- og leaderboard-system for å gjøre spillet mer kompetetivt.
* Skrevet dokumentasjon og oppdatert javadoc inn mot siste innlevering.
* Brukt SpotBugs og SonarQube for å fange bugs og dårlig kode.
* Ryddet i resources-mappen for å fjerne gamle sprites og lyder som ikke lenger er i bruk.


### Forbedringspotensial
* Legge til flere features og mer innhold.
  * For eksempel stiger, vann med annerledes physics, flere power ups, flere leveler, jump pads osv osv
  * Større variasjon i fiendetyper og hindringer ville gitt en rikere opplevelse.
* Skrive tester tidligere slik at man slipper å sitte igjen med masse testskriving på en gang.
* Planlegge arkitektur nøye med MVC og SOLID fra start kunne spart oss for en del refaktorisering og redusert flaskehalser der personer må vente på refaktorisering.

--- 
### Avsluttende retrospektiv for hele prosjektet
Sett i helhet er teamet godt fornøyd med hvordan prosjektet har utviklet seg fra start til slutt. 
Vi startet med en enkel prototype og har endt opp med et komplett spill med flere nivåer, spillmoduser, leaderboard og lydeffekter.

**Hva vi har gjort bra:**
* Det vi er mest fornøyd med er arkitekturen og kodestrukturen vi har landet på. Prosjektet følger MVC og SOLID-prinsippene, og koden er modulær og lett å utvide.
  * Dette har gjort det mulig å legge til nye features uten å bryte eksisterende funksjonalitet.
  *  Komponent-mønsteret for entiteter, event-bussen for løs kobling mellom lag, og et gjennomgående interface-hierarki er valg vi er stolte av.
* Samarbeidet i teamet har fungert svært godt gjennom hele prosjektet.
  * Faste ukentlige møter med stand-up har sørget for at alle har vært informert og involvert.
  * Rollene har vært tydelige og alle har bidratt aktivt.
  * Vi har hatt en åpen kultur for å ta opp uenigheter tidlig, noe som har unngått større konflikter.
* Vi er også fornøyde med at spillet faktisk er gøy å spille.
  * Spillmekanikken med fysikk, fiende, lucky blocks og power-ups gir en helhetlig spillopplevelse som vi er stolte av å ha bygget fra bunnen av.

**Hva vi ville gjort annerledes:**
* Særlig tidlig i prosjektet gikk mye tid til å forstå hvordan Box2D håndterer kollisjon, kroppsdestuksjon og kontaktlyttere. Dette forsinket arbeidet med spillmekanikk i de første sprintene.
* Definere "definition of done" tidlig - hva betyr det at en brukerhistorie er ferdig?
  * Inkluderer det testing dokumentasjon og godkjenning.
* Vi ville også skrevet tester tidligere og mer kontinuerlig.

### Plan videre
* Utvide spillets innhold med flere features og flere leveler.
* Launche på Steam
---
### Kode
**Arkitektur:**
* Prosjektet følger tydelig MVC og SOLID med separate model-, view- og controller-lag.
* Eventbussen benyttes for å koble ulike lag sammen uten for tett kobling. Audio og Entity states er blant lytterne til bussen.
* Audio ligger separert i en egen pakke og lytter til eventbusen for å spille av Musikk/SFX, i stedet for å ha direkte innsyn i klasser.
* Screens-pakken bygger opp og samler alle skjermer ett sted.

**Kodekvalitet**
* Vi opplever koden som modulær og lett å jobbe med.
* Prosjektet inneholder interfacer der vi har vurdert det som nødvendig, samt abstrakte klasser for å unngå duplikat kode (DRY).
* Vi har refaktorisert store deler av prosjektet slik at det skal være enkelt å forstå og utvide prosjektet senere.

**Testdekning**
* Vi har 81% testdekning.
* Enhetstester dekker primært forretningslogikk som ScoreCalculator, LeaderboardManager og spillermekanikk.
* UI og rendering er ikke testet automatisk da LibGDX krever en kjørende applikasjon.

---
## Klassediagram
[Klassediagram](klassediagram.png)

---
## Møtereferater

Møtereferatene våre ligger i denne google doc-en:

[Møtereferater A-laget](https://docs.google.com/document/d/1DTBr6Ew_FQDBkRHb4hHOdzjmSTYw7m4FUMoYYwyxgII/edit?usp=sharing)





