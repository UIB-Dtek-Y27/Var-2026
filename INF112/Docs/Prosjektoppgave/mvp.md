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