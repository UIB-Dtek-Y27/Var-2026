# Vurderingskriterier

## Design/prosjekt/prosess/rapportering (20%)

### Formalia

[ ] Prosjektets git-repositorium ligger under teamets gitlab-gruppe og har et relevant navn

[ ] `README.md`-fil, med navn på team-medlemmer, teamet og prosjektet, kort beskrivelse og brukerveiledning for spillet, teknisk info om hvordan koden kjøres, og informasjon om hvor grafikk/lyd er hentet fra (kilde/opphavsrett)

[ ] `doc/obligX.md`-fil med oversiktlig svar på oppgaver

[ ] Alt er oversiktlig og riktig format

### Team

[ ] Møtereferater (i, eller lenket til fra oblig-filen)

[ ] Teambeskrivelse og rollefordeling

[ ] Beskrivelse av prosjektmetodikk

[ ] Retrospektiv – hva var planlagt (av metodikk, etc), hva gjorde dere faktisk og hvorfor, hva vil dere evt. gjøre annerledes

[ ] Project board, issues etc. er oppdatert

[ ] Gruppedynamikk og kommunikasjon: alle meninger bli hørt, alle bidrar jevnt, tonen er god

[ ] Alle bidrar til normalt, godt arbeidsmiljø

### Git / versjonskontroll

[ ] Commits er ryddige

[ ] Ingen filer mangler

[ ] Innlevering er tagget riktig

[ ] Commit-meldinger er meningsfulle

[ ] `doc/obligX.md` har oversikt over evt. større endringer, eller forbedringer dere har blitt bedt om å gjøre

[ ] Jevn fordeling av commits mellom teammedlemmer

## Programvare, produkt og kvalitet (20%)

### Spesifikasjon

[ ] Overordnet beskrivelse av konsept, etc.

[ ] Brukerhistorier

[ ] Akseptansekriterier og arbeidsoppgaver (kort beskrivelse)

[ ] Hva inngår i MVP? Hva er evt. stretch goal?

### Produktleveranse

[ ] Koden sjekker ut og bygger

[ ] Kan bygges og kjøres på alle operativsystem

[ ] Kan bygges og testes ikke-interaktivt (dvs. på en server uten tilhørende skjerm/tastatur)

[ ] Kravene er oppfylt

[ ] Spillet er spillbart (trenger ikke være et *bra* eller *gøy* spill, men det må være mulig for at vanlig menneske å bruke det)

[ ] Teknisk dokumentasjon om oppsett – hvordan bygge og kjøre programmet osv. (i `README.md`, se punkt under *Formalia*)

[ ] `pom.xml` / `build.gradle` er oppdatert med korrekt prosjektnavn, `main`-klassenavn etc.

[ ] Teknisk beskrivelse av prosjektet og arkitekturen (inkl. klassediagram)

### Kodekvalitet
*God kodestil (formattering, meningsfulle variabelnavn, unngå code smells), koden er tilstrekkelig dokumentert. Hvis det er kommentarer i koden, skal de være meningsfylte.*

[ ] Følger single responsibility principle; high cohesion, low coupling

[ ] God bruk av interface, arv, kodegjenbruk – open-closed principle

[ ] Korrekt (evt.) bruk av arv – Liskov substitution principle

[ ] Unngå unødvendige avhengigheter i koden – interface segregation principle

[ ] Referer til abstraksjoner (interfaces), ikke konkrete klasser – dependency inversion principle

[ ] God navngivning

[ ] Public metoder dokumentert

[ ] Don't Repeat Yourself – bruk abstraksjon heller enn copy/paste

[ ] Unngå død og [råtnende kode](https://en.wikipedia.org/wiki/Software_rot)

### Testing

[ ] Test coverage

[ ] Tester som faktisk kan finne feil

[ ] Tester er automatiske

[ ] Automatiske tester kan kjøres «hodeløst»

[ ] Minimum 75% test coverage

### Konkrete krav

[ ] Spillet har forside/hjelpeside

[ ] MVC-design

[ ] Lyd koblet til hendelse

[ ] Objektfabrikker

[ ] Abstrakte objektfabrikker

[ ] Objekter som modifiserer oppførsel (powerups, etc)


(Andre konkrete krav (se *Krav til prosjektet*) er fordelt under de andre punktene)

