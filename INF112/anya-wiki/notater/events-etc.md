---
title: 'Event handling; Observer/Listeners, Command, Strategy'
---


## Minimalt eksempel

* En *observer* er (et objekt med) en metode som blir kalt når noe skjer – f.eks. når et observert objekt endrer seg, eller ved interaksjon med resten av verden.
* Terminologien varierer avhengig av formålet og hvem man snakker med:
    * Observer / observable
    * Subscriber / publisher
    * Event listener, event handler, etc.
 
```java
	TextEditor editor = new TextEditor();
	editor.register((ed) -> {
		// will be called whenever editor state changes
		System.out.println("Text changed to: " + ed.text);
	});
	
	editor.edit(0, 0, "Hello, world!");
	editor.edit(5, 7, " there");
```

## I LibGDX

### Lavnivå

Grensesnittet `InputProcessor` har metoder for forskjellige input-hendelser, f.eks. `keyDown(keyCode)`, `keyUp(keyCode)`, `mouseMoved(x,y)`; ved å registrere en `InputProcessor` får vi beskjed når taster blir trykket, musen beveger seg osv. `InputAdapter` har en default «do nothing»-implementasjon av disse:

```java
	var listener = new InputAdapter() {
		@Override
		public boolean keyDown(int keyCode) {
			if (keyCode == Keys.LEFT || keyCode == Keys.A) {
				System.out.println("left");
				return true; // avbryter videre prosessering
			}
			return false; // vi håndterte ikke hendelsen, prøv neste
		}
	};
	// be LibGDX sende input til lytteren vår
	Gdx.input.setInputProcessor(listener);
```

Ved hjelp av `InputMultiplexer` kan man kjede sammen flere `InputProcessor`er:

```java
var multiplexer = new InputMultiplexer(listener, stage);
Gdx.input.setInputProcessor(listener, stage); 
```

`Stage` er også en `InputProcessor` og sender videre input til `Actor`s.

### Polling

I stedet for events går det også an å spørre hva nåværende tilstand er:

```java
if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	moveBy(-1, 0);
}
// evt. isButtonJustPressed for å se om knappen har blitt trykket siden sist frame
if(Gdx.input.isButtonPressed(0)) {
	attack();
}
var mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY())
```

Som regel er det best å håndtere input med events, det er mer oversiktlig og bruker (ørlite) mindre ressurser. Lese av nåværende tilstand er mest aktuelt i spill eller med analogt utstyr som joystick/gamepad.

### `keyDown`, `keyUp`, `keyTyped`/`keyPressed`

Det går an å lytte til to forskjellige typer events fra tastaturet:

* «rå» tastaturdata – sender `keyDown` når en tast trykkes ned, `keyUp` når den slippes igjen.
    * Man får vite hvilken *tast* som er trykket, men ikke hvilket *tegn* tasten tilsvarer. Dvs. hvis brukeren trykker *ø* på et norsk tastatur, vil du få vite at brukeren har trykket på `Keys.SEMICOLON` (den tilsvarende tasten på et amerikansk tastatur), og du vil motta én `keyDown(Keys.SEMICOLON)` event, etterfulgt av én `keyUp(Keys.SEMICOLON)` når brukeren slipper tasten.
    * `keyDown` repeteres ikke når brukeren holder tasten nede
    * alle tastene sendes som de er (unntatt *Fn*-tasten på laptoppen, som håndteres i firmware), dvs. uten at *shift*, *ctrl*, *alt* har spesiell mening.
    * hver tast er unik, så *venstre shift* != *høyre shift*.
* tegndata – sender `keyTyped` for hvert *tegn* brukeren skriver inn, uten at det nødvendigvis svarer til ett enkelt tastetrykk:
    * *shift+ø* vil gi deg `keyTyped('Ø')`
    * *akutt aksent* (*shift-backslash* på mitt tastatur) ettefulgt av *e*  gir `keyTyped('è')`, osv.
    * tegntaster som holdes nede, repeteres etter en liten stund – hver repetisjon sender ny `keyTyped` event
    * taster som ikke tilsvarer tegn blir ikke rapportert med `keyTyped`

I nettleseren gir `KeyboardEvent` rå tastaturdata, mens `InputEvent` sammen med tekstfelt e.l. bør brukes når man vil ha tegndata.

* LibGDX får inn «rå» input events fra OpenGL (eller retter sagt, [GLFW](https://www.glfw.org/)), som egner seg for f.eks. spill og liknende applikasjoner, men er mindre passende for GUI/kontor-applikasjoner.
* rapporterer `keyDown` når en knapp trykkes ned, og så sier den ingenting før den sender `keyUp` når knappen slippes.
* 