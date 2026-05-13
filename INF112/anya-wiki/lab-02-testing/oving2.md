---
title: Øving 2
---










# INF112 Øvelse 2, Våren 2025

# Andre øvelse
Hvis du eller gruppen din har gått glipp av noe i [første øvelse](../lab-01-intro/oving1), ta en titt på det først. Vi begynner med å teste TextAligner litt mer, så dere bør helst ha gjort den delen.

## Del -1: Flere ressurser

* [NerdSchoolBergen har en fin oversikt](https://github.com/nerdschoolbergen/all-about-testing-code) over testing, med flere øvelser. Dette er et praktisk rettet kurs som ble holdt for UiB-studenter for noen år siden, og øvelsene starter med ganske enkle ting. Fint sted å begynne om man blir stående fast på «gjett hvordan du tester dette».

## Del 0: Planlegging

Dere skal skrive en del tester (og kanskje annen kode) – tenk rask gjennom hvordan dere vil gjøre det. Sitte sammen i par hvor en skriver og en tenker? Prøve hver for dere og så se på det sammen og forklare tankegangen? 

## Del 1: Git, forks, merge requests

Git er et såkalt [distribuert versjonskontrollsystem](https://en.wikipedia.org/wiki/Distributed_version_control) hvor utviklere kan jobbe helt uavhengig av hverandre. Det har vi utnyttet da vi satte opp Øving 1 – Anya har laget det originale *upstream* prosjektet, og hver av dere har fått deres egen personlige [fork](https://en.wikipedia.org/wiki/Fork_(software_development)) som dere har jobbet med (og hvis du *ikke* har gjort det, gjør det før du går videre med denne øvelsen). (Se forøvrig ordliste nederst på siden.)

Git gjør det (relativt) enkelt å dele kode på tvers av forker. Du kan gjøre dette både i GitLabs web-grensesnitt og lokalt i din egen arbeidskopi (se mer om sistnevnte nederst). For å fortsette med denne øvelsen trenger du å oppdatere din egen fork med de nyeste endringene fra upstream-prosjektet (*merge* endringene fra *upstream*).

*Aller først: **commit og push** alt du har gjort med Øvelse 1.*

For å gjøre oppdateringen med GitLab, gå til nettsiden for prosjektet ditt (under https://git.app.uib.no/inf112/25v/ex/) og trykk *Update Fork*. Hvis alt går bra vil prosjektet ditt nå være oppdatert – men, hvis du har gjort endringer som er i *konflikt* med upstream (dvs. både du og jeg har endret samme deler av koden), så må du håndtere konflikten før du kan fortsette. Se nederst for mer informasjon om hvordan du gjør dette.

Du kan gjøre Del 2 uten å ha fått til mergingen (det vil være noen forskjeller i koden i forhold til det som er beskrevet, men feilene du skal finne er de samme), men for Del 3 trenger du oppdateringene.

*Forks, merging og konflikter er blant tingene du kommer borti oftest som programvareutvikler, så å håndtere dette er blant de nyttigste tingene du lærer i INF112. **Du kommer til å bli frustrert – men ikke gi opp!***

## Del 2: 🦆🐤🐤🐤🐤🐤

En viktig kunde har fått en alvorlig feil i [ande-simuleringssystemet sitt](https://git.app.uib.no/inf112/25v/textutils/-/tree/main/src/main/java/inf112/pond): Det dukker plutselig opp for mange andunger + at endene plutselig også begynner å gå fortere.

Simuleringen er satt opp slik:

* En Pond inneholder PondObjects – enten Duck eller Duckling
* Dette er en [stegvis simulering](https://en.wikipedia.org/wiki/Discrete-event_simulation) – for hvert steg i simuleringen kalles en `step` på alle objektene, som oppdaterer tilstanden deres
* For `Duck`, så er det meningen den skal flytte seg litt bortover, og så – for hvert 25. steg, «klekker» den en andunge, som fortsetter som selvstendig objekt, med samme retning og fart som moren.
* `Duckling` bare beveger seg foreløpig (vi har vært for opptatt med å finne den vanskelige feilen til å implementere at de vokser opp og lever selvstendige liv)

[`Main`](https://git.app.uib.no/inf112/24v/textutils/-/blob/main/src/main/java/inf112/pond/Main.java) inneholder en bitteliten main-metode som kan brukes for å se hva som skjer, f.eks:

```
                                                          …
                                                         🦆🐤                                                                     
                                                        🦆🐤🐤🐤                                                                    
                                                    🦆🐤🐤🐤                                                                        
                                                🦆🐤🐤🐤                                                                            
                                                …
```

Forventet resultat er ca. slik (med kjedelig repetisjon komprimert):
```
                                                       …
                                                        🦆
                                                       🦆
                                                      🦆
                                                      … 22 steg                                                                    
                                                    🦆🐤
                                                    … 24 steg                                                                    
                                                  🦆🐤🐤
                                                  … 24 steg                                                                        
                                                🦆🐤🐤🐤                                                                            
                                                …
```

(Du kan kjøre programmet med Maven på kommandolinjen med `mvn exec:java`.)

Prosjektet har dessverre ingen tester (vi har vært for opptatt med å legge til features og finne emojis til å ha rukket å gjøre noe med testing og sånn…).

*Pga. forretningshemmeligheter og slikt vil kunden helst at dere, om mulig, unngår å se på klassene i `pond.impl` pakken.*

* Diskuter hvilke tester dere vil trenge for å finne ut hva som er galt – særlig hvis dere ikke kan se implementasjonen.
* Hvordan vil dere håndtere testing av en stegvis simulering? Særlig når en del av de interessante tingene skjer et stykke inn i simuleringen (etter 25 steg)? ([Husk Given-When-Then](https://martinfowler.com/bliki/GivenWhenThen.html))
* Det kan godt være det er smart å begynne med å teste de enkleste tingene før dere går løs på hva som skjer i `step`
* Hele `pond`-pakken mangler tester, men den ligger inne i samme prosjektet som `TextAligner`, så dere slipper å sette opp JUnit – bare kopier og ta utgangspunkt i en av testene som [ligger der fra før](https://git.app.uib.no/inf112/25v/textutils/-/tree/main/src/test/java/inf112).

**HINT:** *Bugs er sosiale dyr* – det er påfallende ofte at det er mer enn bare én feil som forårsaker problemet.

## Del 3: TextUtils

[TextUtils](https://git.app.uib.no/inf112/25v/textutils) prosjektet er nå oppdatert med [en del implementasjoner av TextAligner](https://git.app.uib.no/inf112/25v/textutils/-/tree/main/src/main/java/inf112/impl). Hvis du ikke allerede har merget endringene fra upstream (Del 1), må gjøre det nå.

## Teste tester
Finn frem testene fra forrige øvelse, og prøv å kjøre dem på de [nye implementasjonene](https://git.app.uib.no/inf112/25v/textutils/-/tree/main/src/main/java/inf112/impl) av `TextAligner` – `AlignerA`–`AlignerH`. Du kan evt. bruke [denne klassen](https://git.app.uib.no/inf112/25v/textutils/-/blob/main/src/test/java/inf112/TestAligner.java) som utgangspunkt for å lage en test som sjekker alle implementasjonene fra `A`–`H`.

`AlignerA`–`H` inneholder alle forskjellige feil, med mulig unntak av `AlignerH` som kanskje er korrekt (bla. avhengig av hvilken oppførsel du forventer). Hvis det er implementasjoner (unntatt `H`) hvor *ingen* av testene feiler, så er ikke testene bra nok! Utvid med tester som avslører feilene – helst uten å se på `AlignerA`–`H` implementasjonene.

* Fant dere noen bugs dere ikke forventet? Eller som dere mener ikke egentlig er feil?
* Fant dere noen feil i `AlignerH`?
* Ville `E`, `F` eller `G` varianten vært ‘bra nok’?
 
Det går også an å ta en titt på [løsingen fra forrige uke](../lab-01-intro/losning1) (trykk på *SPOILERS*) for tips til hva som er / kan gå galt.


## Del 3: Refleksjon

Tenk litt gjennom hvordan dere håndterte øvelsen: fant dere en god måte å jobbe på? hadde alle mulighet til å bidra? hva gjorde dere om dere sto fast på praktiske problemer og trengte hjelp? gikk det greit å håndtere at du selv eller noen andre gjorde feil eller ikke fant ut av ting? hva lærte dere om hva som er lurt å teste og hvordan det er lurt å teste?


## Merge konflikter

Hvis du gjorde *Update Fork* i GitLab, og den fant en konflikt, vil den be deg om å *Resolve merge conflicts locally* ("locally" er her arbeidskopien som du har på din egen maskin).

Oppskriften er beregnet på kommandolinjen. Hvis du bruker et annet git-verktøy må du sjekke dokumentasjonen for å finne ut hvordan du gjør dette.

#### Steg 1: hente oppdateringene

```sh
git fetch https://git.app.uib.no/inf112/25v/textutils main
```

#### Steg 2: kombinere med dine egne endringer

```sh
$ git merge FETCH_HEAD
```

Du vil nå få beskjed om eventuelle konflikter:

```sh
$ git merge FETCH_HEAD
Auto-merging src/main/java/inf112/pond/Pond.java
CONFLICT (content): Merge conflict in src/main/java/inf112/pond/Pond.java
Automatic merge failed; fix conflicts and then commit the result.
```

Åpne filen(e) i editoren, og fiks konfliktene. Koden mellom `<<<<<<<<` og `========` er din egen, og koden mellom `========` og `>>>>>>>>` er fra upstream. I dette tilfellet kan du antakelig håndtere problemet ved å fjerne dine egne endringer og bruke mine. For mer informasjon, se f.eks. http://weblog.masukomi.org/2008/07/12/handling-and-avoiding-conflicts-in-git/ http://wiki.eclipse.org/EGit/User_Guide#Resolving_a_merge_conflict for hjelp.

Når du tror du har fikset konfliktene er det lurt å kompilere og kjøre koden, så du ser at det funker:

```sh
$ mvn compile
$ mvn test
```

Koden bør kompilere før du committer, men du kan evt. vente med å fikse testene. Det er best å gjøre minst mulig arbeid mens du holder på å fikse konflikter.

#### Steg 3: Commit den mergede koden

Legg til alle filene du har endret med `git add`, og `git commit` endringene:

```sh
$ git add src/main/java/inf112/pond/Pond.java
$ git commit -m "merge upstream"
```
#### Steg 4: Push endringene

```sh
$ git push
```


## Avansert oppsett
<details><summary><b>GUIDE</b>: Sette opp arbeidskopi til å merge fra upstream</summary>

*  Vi antar at du har klonet/lastet ned din egen fork på vanlig måte (Import Projects from Git, eller ```git clone```), fra git.app.uib.no/inf112/25v/ex/DITT_BRUKERNAVN_textutils.git

Kopien du jobber på vil da være satt opp med din egen fork som *origin*, og Git vil pushe og pulle derfra. Vi skal nå legge til en «remote» til, slik at du har to «remotes»: *origin*, som er din egen fork, og *upstream* som er den du forket fra.

* Om du bruker kommandolinjen: gjør ```git remote add upstream https://git.app.uib.no/inf112/25v/textutils.git```. 

* I Eclipse: høyreklikk på prosjektet ditt, velg *Team → Show in Repositories View*.

    * Du får opp en tab et sted med tittel "Git Repositories", hvor prosjektet ditt er valgt.

    * Høyreklikk på *Remotes*, velg *Create Remote*

    * Velg navn (f.eks. "upstream"), og *Configure pull*, trykk *OK*.

    * I neste dialog, fyll inn ```https://git.app.uib.no/inf112/25v/textutils.git```

   * Velg *Save*.

##### Oppdateringer
For å få nye oppdateringer fra Anya (eller mer generelt, *upstream*), gjør

* På kommandolinjen:
   * ```git pull upstream main``` *eller*
   * ```git fetch upstream main``` etterfulgt av ```git merge upstream/main``` (eller ```git rebase upstream/main```)
* I Eclipse:
   * *Team → Pull...* og velg *upstream*.

(Du kan pulle fra Eclipse selv om du gjorde oppsettet fra kommandolinjen, og omvendt.)

#### Konflikt
Det kan godt være du får en konflikt hvis du har endret noen av de samme filene som er endret i upstream. Denne må i såfall håndteres før du kan fortsette utviklingen. Om nødvendig, se om noen kan hjelpe deg, se etter løsning på Stack Overflow, eller prøv en av disse guidene: http://weblog.masukomi.org/2008/07/12/handling-and-avoiding-conflicts-in-git/ http://wiki.eclipse.org/EGit/User_Guide#Resolving_a_merge_conflict

</details>

## Ordliste
### Versjonskontroll / Git

* [Repositorium / *repository* / *repo*](https://en.wikipedia.org/wiki/Repository_(version_control)) – samling av kode og data, med metadata og versjonshistorikk. Med Git er det vanlig at hvert prosjekt har sitt eget repositorium.

* Prosjekt / *project* (i GitLab/GitHub/o.l.) – kombinerer repositorium med andre data og tjenester som er nyttige for utvikling: issue tracking, wiki, websider, kontinuerlig testing, integrasjon med andre verktøy, online browsing og editering osv.

* Arbeidskopi / *working copy* – kopi av repositoriet som man jobber med (editerer filer, kompilerer, kjører osv). I Git er arbeidskopien også et komplett repositorium (en *klone* av hovedrepositoriet); i andre systemer kan det være at arbeidskopien bare inneholder versjonen man jobber med.

* Klone / *clone* – en kopi av et repositorium; arbeidskopien er f.eks. en klone. Repositoriet man har klonet blir vanligvis referert til som *origin*.

* *Fork* – en kopi av et prosjekt (*upstream*-prosjektet). Som oftest lager man en fork når man vil gjøre egen utvikling på et prosjekt uten å være formelt med i utviklingsteamet til prosjektet. GitLab holder rede på forking, slik at det er lett å dele kode mellom fork og upstream.

* *Commit* – et sett med endringer, med en tilhørende forklarende tekst (commit-melding). Man kan se tidligere commits på et prosjekt ved å bla bakover i historien.

* *Push* – sende commits man har gjort i arbeidskopien til det sentrale repositoriet (origin). Etter push er arbeidskopien og origin like (normalt sett) – hvis origin har andre endringer må man gjør *pull* før *push*.

* *Pull* – hente commits (som andre har gjort) fra origin til arbeidskopien.
