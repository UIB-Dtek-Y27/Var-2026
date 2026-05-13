# Øving 1 – Løsningsmuligheter

For `center(text, width)`, ting å sjekke og hvordan det kan håndteres:

<details>
<summary markdown="span">SPOILERS! Vent med å lese til dere har prøvd selv!</summary>

### Aktuelle ting å tenke på:

* `text == null` – returnere null, kaste exception, returnere streng med bare spaces, e.l.
* `width < text.length()` – returnere text uendret, eventuelt dele den i flere linjer *(F.eks.: Python spesifiserer at den returneres uendret)*
* `width < 0` – IllegalArgumentException, eller returnere text uendret *(Python gir ingen feilmelding, og returnerer strengen uendret)*
* `((width - text.length()) / 2) % 2 == 1` (ujevnt antall tegn å fordele på venstre og høyre side) – ha færrest mellomrom på venstre side (kanskje best?) eller høyre side; evt. gjøre den `width-1` lang (antakelig dårlig løsning). *(F.eks.: Python spesifiserer ikke hva som skjer i denne situasjonen – i praksis ser det ut til at den legger til et ekstra mellomrom på venstre side hvis `width` er et oddetall og høyre side hvis `width` er et liketall.)*
* `text.startsWith(" ") || text.endsWith(" ")` – ikke gjøre noe spesielt, eller bruk `text.strip()` (kanskje best) *(Python håndterer mellomrom på samme måte som andre tegn, f.eks. `' a'.center(3) == '   a'`)*
* `text.isEmpty()` – returnere streng med `width` mellomrom

For ujevnt antall tegn kan det godt være at vi lar det være udefinert om ekstra mellomrom legges til på høyre eller venstre side – i såfall bør testene våre håndtere begge varianter som lovlige løsninger, ellers har vi *overspesifisert* og gjort oss avhengig av implementasjonsdetaljer som kanskje vil endre seg senere. Det er ganske vanlig at den dokumenterte oppførselen lar noen detaljer være uspesifisert/implementasjonsavhengig. Hvis man så gjør seg avhengig av implementasjonsdetaljer eller uspesifisert oppførsel (noe som er ganske vanlig i praksis), så ender man opp med problemer i fremtiden – eller hindrer forbedringer fordi man ikke tør endre implementasjonsdetaljer av frykt for slike problemer.¹

Forskjellig språk har litt forskjellige problemer for hvordan uforventede/ugyldige parametre håndteres. I Java er det vanlig å være streng og kaste exception om man ikke får "fornuftige" parameterverdier – f.eks. ved negativ bredde, eller negativ/for stor indeks. I Python er det vanlig å være litt mer fleksibel - f.eks. er det ok med negativ `width` for `center()`, og negativ indeks kan brukes for å indeksere fra slutten av strengen/tabellen. JavaScript gir ofte `undefined` som resultat i stedet for å kaste exception når noe er feil og funksjoner er ofte veldig tolerante for feil.²

¹ Microsoft var en gang i tiden ganske notoriske for å sørge for at populær programvare var tett knyttet til deres implementasjon, slik at det ble vanskelig og dyrt for konkurrerende utviklere å være [bug-kompatible](https://en.wikipedia.org/wiki/Bug_compatibility).

² Hvis JavaScripts `string` hadde innebygget sentrering, så ville den sikkert hatt *tre forskjellige* funksjoner med inkompatible parameterkonvensjoner (se [`substring()`, `substr()` og `slice`](https://javascript.info/string#getting-a-substring))

### Flere linjer:
*Det er et definisjonspørsmål om dette inngår eller ikke inngår i oppførselen.* (Vanlig at det *ikke* gjør det.)

* `text.contains("\n")` (linjeskift) – dele opp i linjer (med `split()` eller `lines()`), og sentrer dem individuelt; eller bare behandle linjeskift som et hvilket som helst annet tegn
* `text.contains("\r\n")` (Windows linjeskift) – håndtere som `"\n"`? Kanskje «normalisere» strengen ved å slette `"\r"`

### Dette er litt mer obskure problemstillinger:
*Antakelig ikke verdt å tenke på*

* `text.contains("\t")` (tab) – behandle som space, eller erstatte med 8 space (ikke helt riktig), eller fikse på en eller annen udefinert måte så den hopper til neste kolonne som er delelig med 8. Sannsynligvis er det poengløst å sentrere en streng uten å fjerne tab-tegnene.
* `text.contains("\f")` (form feed / sideskift) – nå er vi virkelig på bærtur, men kan kanskje ordnes på samme måte som linjeskift
* teksten inneholder andre kontrolltegn som ikke tar opp plass, f.eks. [ANSI escape codes](https://en.wikipedia.org/wiki/ANSI_escape_code) – det er rimelig å ikke støtte dette

### Dette er aktuelle problemstillinger i en internasjonal setting
*Som regel noe man må bruke et eget bibliotek til; dette er ikke standard strenghåndtering.*

* `text.codePointCount(0, text.length()) != text.length()` (f.eks., `"🐧"` – noen av tegnene er kodet med et [UTF-16 surrogate pair](https://en.wikipedia.org/wiki/UTF-16)) – overrasket om noen tenker på dette; håndteres evt. ved å bruke lengden i Unicode codepoints i stedet, eller jobbe med strengen som en liste av codepoints. (Python håndtere dette riktig (fordi den bruker codepoints); Java og JavaScript bruker UTF-16 og vil normalt regne at `"🐧"` er to tegn lang.)
* *noen av tegnene i text utgjør et [grapheme cluster](https://unicode.org/glossary/#grapheme_cluster)* (f.eks. `"👩‍🎓"` består av tre tegn, ['Woman', 'Zero Width Joiner', 'Graduation Cap']) – lykke til med det...
* *noen av tegnene har er øst-asiatiske tegn med full bredde* – lykke til med det også :)
* teksten er i et språk som skrives høyre-til-venstre – burde funke greit, men litt uklart om betydningen av `flushRight` og `flushLeft` skal være omvendt
* *deler* av teksten er skrevet høyre-til-venstre og andre deler er venstre-til-høyre – kanskje håndterbart om man har en ok måte å måle hvor lang/bred teksten er
* teksten er i et språk som skrives vertikalt – tja...

*Standard implementasjoner (f.eks. innebygd i Python) av disse metodene håndterer gjerne de øverste tingene, og kanskje flere linjer. Støtte for at ting har forskjellig bredde og skrives forskjellig vei er som regel bare aktuelt når det er snakk om å typesette tekst i en konkret (som regel [proporsjonal](https://en.wikipedia.org/wiki/Font#Metrics)) font – slik en nettleser eller tekstbehandler gjør – og det er egentlig et helt annet problem.*

For å støtte avanserte ting – og det er *definitivt ikke meningen i denne oppgaven*, og ikke noe brukeren av `center()`-metoden vil forvente eller sette pris på – [trenger du noe slikt](https://github.com/anyahelene/turtleduck/blob/master/base/src/main/java/turtleduck/text/Graphemizer.java) som håndterer Unicode, grapheme clusters og ANSI escape codes og deler opp i ett og ett «tegn» som tar opp én plass på skjermen.
</details>

For `flushRight/Left()` er det tilsvarende, men ikke noe problem med å legge til et odde antall mellomrom.

For `justify()` – denne er rimelig tricky, og har neppe noen god løsning egentlig; dette er egentlig noe som bare funker skikkelig hvis man jobber med tekstdokumenter med proporsjonale fonter.

## Realistisk eksempel

Sjekk hva [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) gjør i sin [StringUtils::center()](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html#center-java.lang.String-int-)-metode, og hvordan det er dokumentert og [testet](https://gitbox.apache.org/repos/asf?p=commons-lang.git;a=blob;f=src/test/java/org/apache/commons/lang3/StringUtilsTest.java;h=74b836906e60f014a8958d5852ac51d133c46a00;hb=HEAD#l493). (`flushRight()` tilsvarer `leftPad()` og `flushLeft()` tilsvarer `rightPad()`, og StringUtils ser ikke ut til å ha `justify`).




