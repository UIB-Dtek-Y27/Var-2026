---
title: Git branch eksempel
---
## RTFM

Se forøvrig dokumentasjonen:

* [Git Branching](https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell)
* [Branching and merging](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)
* [Branching workflows](https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows) – [Feature branches](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow), [Gitflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)


## Eksempel på å lage ny gren, pushe den og lage merge request

Lage ny gren *frog*:
```sh
$ git checkout -b frog  # lage ny gren
Switched to a new branch 'frog'

$ git branch -v         # sjekk hvilke grener vi har
* frog 248e3d1 fix package info
  main 248e3d1 fix package info

$ git checkout main     # bytt til main
Switched to branch 'main'
Your branch is up to date with 'origin/main'.

$ git checkout frog     # bytt til frog
Switched to branch 'frog'
```

Etter å ha lagt til `Frog.java`:

```sh
$ git status
On branch frog
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	modified:   src/main/java/inf112/pond/Main.java

Untracked files:
  (use "git add <file>..." to include in what will be committed)
	src/main/java/inf112/pond/impl/Frog.java

no changes added to commit (use "git add" and/or "git commit -a")

$ mvn test    # vi bør teste før vi pusher
[INFO] Scanning for projects...
... OK

$ git add src/main/java/inf112/pond/Main.java src/main/java/inf112/pond/impl/Frog.java

$ git commit -m 'add frog'
[frog d557c4c] add frog
 2 files changed, 57 insertions(+)
 create mode 100644 src/main/java/inf112/pond/impl/Frog.java
anya@raven:~/inf112/textutils-upstream$ git branch -v    # vi har nå forskjellige commits på grenene
* frog d557c4c add frog
  main 248e3d1 fix package info
```

Pushe ny gren:

```sh
$ git push                    # oops
fatal: The current branch frog has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin frog

To have this happen automatically for branches without a tracking
upstream, see 'push.autoSetupRemote' in 'git help config'.

$ git push -u origin frog     # bare nødvendig første gang
Enumerating objects: 18, done.
Counting objects: 100% (18/18), done.
Delta compression using up to 16 threads
Compressing objects: 100% (9/9), done.
Writing objects: 100% (10/10), 1009 bytes | 504.00 KiB/s, done.
Total 10 (delta 5), reused 0 (delta 0), pack-reused 0
remote: 
remote: To create a merge request for frog, visit:
remote:   https://git.app.uib.no/inf112/25v/textutils/-/merge_requests/new?merge_request%5Bsource_branch%5D=frog
remote: 
To git.app.uib.no:inf112/25v/textutils.git
 * [new branch]      frog -> frog
branch 'frog' set up to track 'origin/frog'.
```

## Merge i GitLab

Når vi har pushet, viser [prosjektsiden](https://git.app.uib.no/inf112/25v/textutils) en hendig *Create merge request* knapp:

![Screenshot_2025-02-07_13-31-19](uploads/b7a7cbfb43ac1376745dc924e1ac2df2/Screenshot_2025-02-07_13-31-19.png)

For å lage new merge request kan klikke på den, eller klikke på *New*-knappen på [branch-oversikten](https://git.app.uib.no/inf112/25v/textutils/-/branches):

![Screenshot_2025-02-07_13-31-42](uploads/244f638c736a013e5108e1eacbe27d7a/Screenshot_2025-02-07_13-31-42.png)

Grenen kan merges (og evt. slettes) fra merge request siden. Der kan man også undersøke endringene, sjekke testresultater, kommentere, like osv.:

![Screenshot_2025-02-07_13-38-42](uploads/63dd63ecfd34a8c816cfe24b2b7bf137/Screenshot_2025-02-07_13-38-42.png)

På oversikten over endringer går det an å legge inn kommentarer (og foreslå endringer) på enkelt-linjer, og med [CI og test coverage](https://git.app.uib.no/help/ci/testing/test_coverage_visualization/index.md) går det også an å se om endringene er dekket av testene:

![Screenshot_2025-02-07_13-41-08](uploads/594b15d9b7eb9fd76f28d9fafe8ef7ce/Screenshot_2025-02-07_13-41-08.png)


## Fortsatt utvikling etter merge

Etter at vi har merget *frog* med *main* på git.app.uib.no:

```sh
$ git checkout main
Switched to branch 'main'
Your branch is up to date with 'origin/main'.

$ git pull            # hent nytt fra serveren
remote: Enumerating objects: 1, done.
remote: Counting objects: 100% (1/1), done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
Unpacking objects: 100% (1/1), 250 bytes | 250.00 KiB/s, done.
From git.app.uib.no:inf112/25v/textutils
   248e3d1..3d56ae4  main       -> origin/main
Updating 248e3d1..3d56ae4
Fast-forward
 src/main/java/inf112/pond/Main.java      |  2 +
 src/main/java/inf112/pond/impl/Frog.java | 55 +++++++++++++++
 2 files changed, 57 insertions(+)
 create mode 100644 src/main/java/inf112/pond/impl/Frog.java

$ git branch -v       # main har nå fått en merge commit
  frog d557c4c add frog
* main 3d56ae4 Merge branch 'frog' into 'main'
```

Vi kan nå enten fortsette utviklingen på *frog* – i såfall må vi merge merge-committen fra *main*, slik at *main* og *frog* står på samme commit:

```sh
$ git checkout frog   # bytt til frog 
Switched to branch 'frog'
Your branch is up to date with 'origin/frog'.
$ git merge main      # hent endringer fra main
Updating d557c4c..3d56ae4
$ git branch -v       # grenene er nå like
* frog  3d56ae4 Merge branch 'frog' into 'main'
  main  3d56ae4 Merge branch 'frog' into 'main'
```

Eller vi kan slette *frog*, og gjøre evt utvikling på en ny gren:

```sh
$ git branch -d frog
Deleted branch frog (was d557c4c).
```

## Eksempel med konflikter

Vi fyller inn `Frog`-implementasjonen i *main* først – [Frog.java](https://git.app.uib.no/inf112/25v/textutils/-/blob/main/src/main/java/inf112/pond/impl/Frog.java?ref_type=heads). Vi kan så sjekke at den funker:

```sh
$ mvn compile exec:java  # sjekk at den funker
                                                                     🐸        🦆
                                                                    🐸        🦆 
                                                                   🐸        🦆  
                                                                  🐸        🦆   
```

...og så committe og pushe:

```sh
$ git status             # se endringene
On branch main
Your branch is up to date with 'origin/main'.

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	modified:   src/main/java/inf112/pond/Main.java
	modified:   src/main/java/inf112/pond/impl/Frog.java

no changes added to commit (use "git add" and/or "git commit -a")

$ git commit -am 'implement frog'      # -a – commit alle endringene
[main fd97232] implement frog
 2 files changed, 13 insertions(+), 14 deletions(-)
$ git push
Enumerating objects: 19, done.
Counting objects: 100% (19/19), done.
Delta compression using up to 16 threads
Compressing objects: 100% (9/9), done.
Writing objects: 100% (10/10), 902 bytes | 902.00 KiB/s, done.
Total 10 (delta 6), reused 0 (delta 0), pack-reused 0
To git.app.uib.no:inf112/25v/textutils.git
   3d56ae4..fd97232  main -> main
```

Siden vi vil vise hva som skjer når man får konflikter, trenger vi en annen gren med andre endringer. Vi skulle laget denne grenen først (hvis vi lager den nå, så har den allerede endringene fra *main*), men det kan vi fikse ved å hoppe tilbake i historien:


```sh
$ git checkout 3d56ae4   # eller git checkout HEAD^
Note: switching to '3d56ae40566f4c860e2c28c648e3704221ef16ca'.

You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by switching back to a branch.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -c with the switch command. Example:

  git switch -c <new-branch-name>

Or undo this operation with:

  git switch -

Turn off this advice by setting config variable advice.detachedHead to false

HEAD is now at 3d56ae4 Merge branch 'frog' into 'main'

$ git checkout -b frog2     # lag ny gren
Switched to a new branch 'frog2'

$ git branch -v
* frog2 3d56ae4 Merge branch 'frog' into 'main'    # uten main frog implementasjon
  main  fd97232 [ahead 1] implement frog           # med main frog implementasjon
```

Vi kan så lage en [annen frog-implementasjon i *frog2*-grenen](https://git.app.uib.no/inf112/25v/textutils/-/blob/frog2/src/main/java/inf112/pond/impl/Frog.java?ref_type=heads), og teste den:

```sh
$ mvn compile exec:java

                                                                               🐸
                                                                              🐸 
                                                                             🦆🐸
                                                                            🦆🐸 
                                                                           🦆🐸  
                                                                          🦆🐸   
                                                                         🦆🐸    
```

...og så committe og pushe den:

```sh
$ git commit -am 'another frog implementation'
[frog2 0d82277] another frog implementation
 1 file changed, 12 insertions(+), 9 deletions(-)

$ git push -u origin frog2
Enumerating objects: 17, done.
Counting objects: 100% (17/17), done.
Delta compression using up to 16 threads
Compressing objects: 100% (8/8), done.
Writing objects: 100% (9/9), 815 bytes | 407.00 KiB/s, done.
Total 9 (delta 5), reused 0 (delta 0), pack-reused 0
remote: 
remote: To create a merge request for frog2, visit:
remote:   https://git.app.uib.no/inf112/25v/textutils/-/merge_requests/new?merge_request%5Bsource_branch%5D=frog2
remote: 
To git.app.uib.no:inf112/25v/textutils.git
 * [new branch]      frog2 -> frog2
branch 'frog2' set up to track 'origin/frog2'.
```

Vi har nå to grener *main* og *frog2*, som begge har gjort endringer på de samme linjene i `Frog.java` – det vil gi oss en merge conflict.


### Merge conflicts – i GitLab

Når vi har laget ny merge request vil den klage på konflikter:

![Screenshot_2025-02-07_13-25-11](uploads/c00ddbbc95ac0367bdea1972e1ae85ef/Screenshot_2025-02-07_13-25-11.png)

Hvis vi velger *Resolve conflicts* får vi et enkelt verktøy for å fikse konflikter ved å velge «vår» (endringene fra grenen vi merger) eller «deres» (endringene i main) utgave av endringene:

![Screenshot_2025-02-07_13-26-17](uploads/489d5319d798085cc4bc26cec7af0866/Screenshot_2025-02-07_13-26-17.png)

Evt. går det an å editere koden, tilsvarende til lokal merge.

### Merge conflicts – lokalt / i arbeidskopien

Hvis det har skjedd endringer på serveren bør vi pulle først:

```sh
$ git checkout main; git pull
remote: Enumerating objects: 17, done.
remote: Counting objects: 100% (17/17), done.
remote: Compressing objects: 100% (8/8), done.
remote: Total 9 (delta 5), reused 3 (delta 0), pack-reused 0 (from 0)
Unpacking objects: 100% (9/9), 652 bytes | 652.00 KiB/s, done.
From git.app.uib.no:inf112/25v/textutils
  67fd790..7cdd967  frog2      -> origin/frog2
Already up to date.

$ git checkout frog2; git pull
Switched to branch 'frog2'
Your branch is behind 'origin/frog2' by 1 commit, and can be fast-forwarded.
  (use "git pull" to update your local branch)
Updating 67fd790..7cdd967
Fast-forward
 src/main/java/inf112/pond/impl/Frog.java | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
```

Merge *frog2* inn i *main*:

```sh
$ git checkout main
Switched to branch 'main'
Your branch is up to date with 'origin/main'.

$ git merge frog2
Auto-merging src/main/java/inf112/pond/impl/Frog.java
CONFLICT (content): Merge conflict in src/main/java/inf112/pond/impl/Frog.java
Automatic merge failed; fix conflicts and then commit the result.

$ git status
On branch main
Your branch is up to date with 'origin/main'.

You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)
	both modified:   src/main/java/inf112/pond/impl/Frog.java

no changes added to commit (use "git add" and/or "git commit -a")
```

Ooops, vi fikk konflikter. Vi må fikse dem i editoren, f.eks endre

```java
	@Override
	public void step(Pond pond) {
<<<<<<< HEAD
		pos.move(-1, 0);
=======
		x--;
>>>>>>> frog2
	}
```

til

```java
	@Override
	public void step(Pond pond) {
		pos.move(-1, 0);
	}
```

Når alle konfliktene er fikset, kjører vi `git add` og så `git commit`:

```sh
$ git add src/main/java/inf112/pond/impl/Frog.java

$ git commit -m "Merge branch 'frog2'"
[main eca05a1] Merge branch 'frog2'

$ git push

Alternativt, hvis noe går veldig galt, kan vi avbryte mergingen:

```sh
$ git merge --abort
```

(Tilsvarende finnes det en `git rebase --abort` hvis man prøver å merge koden ved [såkalt rebasing](https://git-scm.com/book/en/v2/Git-Branching-Rebasing). Jeg har f.eks. slått på at Git skal bruke *rebase* i stedet for *merge* når jeg gjør `git pull`, så når jeg får konflikter ved *pull*, pleier jeg gjerne å avbryte og gjøre `git merge` i stedet.)