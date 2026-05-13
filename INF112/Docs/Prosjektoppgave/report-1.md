# Rapport – innlevering 1
**Team:** *A-Laget* – *Isak Graarud, Johannes Helle Moe, Theodor Flornes, Tuva Kvamme, Synne Hermansen, Bernhard Bors*

---

## Konseptbeskrivelse

### **Spillbeskrivelse**
Vårt spill er et Mario Bros-inspirert plattform-spill. Spilleren velger en karakter og navigerer gjennom flere nivåer hvor målet er å til slutt nå fram til en ond UiB-professor og bekjempe han.
Professoren har sluppet løs et virus som forhekser dyr og gjør dem til "zoombies".
For å finne kuren må spilleren finne professoren og bekjempe han.

Viktige aspekter i spillet:
1. Spillfigur:
  * Kan velge mellom seks karakterer med ulike styrker og svakheter
  * Spilleren kan styres med piltaster - gå til høyre/venstre, hoppe
  * Underveis kan man samle coins og "power-ups" som endrer spillerens egenskaper

2. Spillverden:
  * 2D-Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»)
  * Vegg – vertikal flate som spilleren ikke kan gå gjennom
  * Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle
  * Verden er større enn skjermen, og skjermen beveger seg bortover med spilleren (a la Mario Bros)
3. Fiender:
  * Fiender (zombifiserte dyr) som beveger seg og er skadelige ved berøring
  * Kan bekjempes ved å hoppe på dem eller ved bruk av spesielle evner spilleren plukker opp
  * UiB-professoren er siste hinder i spillet og mektigere en zoombiesene
4. Død og progresjon
  * For å låse opp ny level må man fullføre foregående level
  * Om man blir tatt av zoombiesene, eller faller utenfor verden må man starte levelen på nytt
  * Man mister ikke progresjon fra tidligere leveler ved å dø
5. Ressurser
  * Ved å plukke opp coins eller bekjempe fiender kan man oppnå power-ups - for eksempel at karakteren blir større og sterkere
  * Man starter med ett liv - power-ups gir ekstra liv. Om man blir tatt med power-ups mister man kreftene og er tilbake til ett liv
6. Brukergrensesnitt og stil:
  * Spillet har en klassisk, Mario-aktig tegneserie-estetikk
  * Hovedmeny med innstillinger, oversikt over nivåer og karakterer



---

## Roller
For å sikre god flyt i prosjektet har vi fordelt følgende ansvarsområder:

* **Team Lead: Isak** Ansvarlig for overordnet progresjon og fordeling av arbeidsoppgaver.
* **Test Queen: Tuva** Hovedansvar for å følge opp god kode-dekning.
* **Strukturansvarlig: Synne** Sikrer at koden følger MVC og forblir lesbar.
* **Rapportansvarlig: Bernhard** Ansvar for utforming, vedlikehold og forbedring av prosjektets dokumentasjon.
* **Øystein fra Øysteins blyant: Theodor** Ansvar for grafisk design, research etter designinspirasjon.
* **DJ: Johannes** Ansvar for lydeffekter og bakgrunnsmusikk.

---
## Utviklingsprosess:
Hybrid mellom Scrum og Kanban:

- Lag en tavle (Kanban) og legg inn oppgaver (Issues).
- Jobb i Sprints: Sett mål for hver uke.
- Bruker trello for å sette spesifikke oppgaver som fordeles på teamet.
  - Link til trello: https://trello.com/b/UkQgtCrS/inf112-a-laget
- Discord og notion for å samle informasjon og dele lenker.
- Roller: Team Lead styrer tavlen, mens Strukturansvarlig passer på at ingen pusher rotete kode til GitHub.


---

## Status:
**Retroperspektiv:**
* Blitt enig om å møtes tirsdager og i gruppetimer på fredager
* Kommunikasjon og samarbeid fungerer godt så langt
* Satt oss inn i trello og andre kommunikasjonsverktøy

**Plan videre:**
* Sette oss inn i Libgdx og andre biblioteker
* Finne sprites, frames osv.
* Begynne på MVP