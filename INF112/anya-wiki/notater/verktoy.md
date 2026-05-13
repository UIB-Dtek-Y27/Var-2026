---
title: Verktøy
---
## IDE / Editor

* IDEer for Java: [Eclipse](https://www.eclipse.org/), [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Netbeans](https://netbeans.apache.org/front/main/index.html), [VSCode](https://code.visualstudio.com/)/[VSCodium](https://vscodium.com/), [og andre](https://en.wikipedia.org/wiki/Comparison_of_integrated_development_environments#Java) – typisk støtter de også andre språk
* VSCode er litt «halvveis» mellom å være IDE og [editor](https://en.wikipedia.org/wiki/Source-code_editor), men støtter i praksis mye av basis-funksjonaliteten man forventer fra en IDE. Andre editorer er f.eks. Atom, Emacs, Notepad++, vi/Vim.
* For Windows-utvikling i .NET / C#, C++ e.l. er det vanlig å bruke [Visual Studio](https://visualstudio.microsoft.com/) (som er urelatert til Visual Studio Code (VSCode)); på Mac har [Xcode](https://developer.apple.com/xcode/) støtter for Swift, (Objecive-)C(++), Java, Python osv. Xcode kommer også med vanlige Unix utviklingsverktøy.

Eksempel: Eclipse er skrevet i Java og er plugin-basert, så man kan bruke den til mange andre språk, og også som basis for andre applikasjoner. Java-pluginen (JDT) inneholder en komplett kompilator som kjører mens man editerer og gir «on-the-fly» tilbakemelding. Eclipse har også et komplett system for prosjekter, bygging, avhengigheter osv. (liknende Maven). Blant tingene som støttes for Java er:

* vanlig editering med fargelegging, formattering, feilmeldinger osv.
* navigasjon: hopp til deklarasjon, vis typehierarki, vis alle brukssteder, avansert søking
* automatisk kompilering, prosjektbygging
* kodegenerering (f.eks. `hashCode/equals`, delegering) og [standard refaktoreringer](https://refactoring.guru/refactoring/techniques)
* debugger med «hot-replacement» av endret kode
* analyseverktøy – noen innebygget, pluss støtte for f.eks. [SpotBugs-plugin](https://spotbugs.readthedocs.io/en/latest/eclipse.html)
* integrasjon med Git, Maven og en haug av andre verktøy

## Analyseverktøy

Kan hjelpe med å finne problemer i koden, sjekke at man følger kodestil osv.

* [SpotBugs](https://spotbugs.github.io/) – kan [kjøres fra Maven](http://spotbugs.readthedocs.io/en/latest/maven.html), har [plugin for Eclipse](http://spotbugs.readthedocs.io/en/latest/eclipse.html) (men dessverre [ikke VSCode](https://github.com/microsoft/vscode-java-pack/issues/1055))
* [PMD](https://pmd.github.io/)
* [CheckStyle](https://checkstyle.sourceforge.io/)
* [SonarQube](http://sonarqube.org/) – et mer avansert verktøy med støtte for mange språk

I tillegg finnes det også egne verktøy for å analysere sikkerhet. ChatGPT kan også være overraskende flink til å finne feil (men gjør en del dumme feil når den skal skrive kode selv).

