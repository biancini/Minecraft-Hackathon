# Minecraft-Hackathon
Progetto per realizzare un Hackathon che permetta di sviluppare plugin per Minecraft.

Questo progetto contiene il codice Java necessario per realizzare un plugin per Minecraft utilizzando la mod Minecraft chiamata CanaryMod [https://canarymod.net/].
La documentazione sulla libreria Canary è navigabile qui: [http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html].
Il progetto &egrave; stato sviluppato con Eclipse [http://www.eclipse.org/] ma &egrave; possibile utilizzare qualsiasi IDE per sviluppare il proprio plugin.
Per realizzare un plugin, inoltre, &egrave; necessario installare il Java Development Kit (JDK) [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html].

Il server Minecraft &egrave; gi&agrave; disponibile all'interno di questo progetto, insieme ad esso questo progetto contiene anche un plugin d'esempio che mostri le possibilit&agrave; di implementazione offerte da CanaryMod e un progetto che idealmente dovr&agrave; essere utilizzato come base di partenza per lo sviluppo dell'hackathon.

## server
Questa cartella contiene il server Minecraft realizzato da Forge. Per startare Minecraft, &egrave; possibile usare il comando:
``
gradlew startClient
``
Se si utilizza Eclipse, &egrave; possibile lanciare il server anche richiamando la configurazione di lancio `Minecraft Server`.

## SamplePlugin
Questa cartella contiene i files necessari a definire un plugin di esempio che mostra alcune funzionalit&agrave; di Forge.
In particolare il plugin implementa diverse funzionalità richiamabili con un comando dalla chat del gioco. Inoltre il plugin crea un task asincrono che viene eseguito automaticamente dal server e registra un listener in grado di percepire la presenza di evento nel gioco per effettuare delle operazioni quando si verificano determinati eventi.

Il plugin implementa diverse funzionalit&agrave; d'esempio, di seguito elencate:
- con il comando `/creacasa` verr&agrave; creata una casa (5x5) di legno attorno al giocatore;
- con il comando `/sky` tutti gli esseri viventi presenti nel mondo, ad esclusione dei giocatori, verranno portati in cielo di 50 blocchi e lasciati cadere;
- con il comando `/rigenera` il giocatore otterr&agrave; il massimo dei punti energia e dei punti cibo;
- con il comando `/mandria ##` verr&agrave; creata una mandria di N mucche (con N uguale al secondo parametro numerico passato al comando) attorno al giocatore;
- facendo click col tasto destro quando si ha selezionato il materiale pelle, verr&agrave; "sparata" una mucca infuocata che, al contatto col terreno, esploder&agrave;

Il progetto pu&ograve; essere compilato come un semplice programma java, si consiglia l'utilizo di un IDE, come Eclipse, in grado di gestire automaticamente i progetti gradle.

## HackathonPlugin
Questa cartella contiene i fiels necessari a definire un altro plugin che conterr&agrave; il codice realizzato durante l'hackathon.

Il progetto pu&ograve; essere compilato come un semplice programma java, si consiglia l'utilizo di un IDE, come Eclipse, in grado di gestire automaticamente i progetti gradle.


# Source installation information for modders

This code follows the Minecraft Forge installation methodology. It will apply
some small patches to the vanilla MCP source code, giving you and it access 
to some of the data and functions you need to build a successful mod.

Note also that the patches are built against "unrenamed" MCP source code (aka
srgnames) - this means that you will not be able to read them directly against
normal code.

Source pack installation information:

## Standalone source installation

Step 1: Open your command-line and browse to the folder where you extracted the zip file.

Step 2: Once you have a command window up in the folder that the downloaded material was placed, type:

Windows: "gradlew setupDecompWorkspace"
Linux/Mac OS: "./gradlew setupDecompWorkspace"

Step 3: After all that finished, you're left with a choice.
For eclipse, run "gradlew eclipse" (./gradlew eclipse if you are on Mac/Linux)

If you preffer to use IntelliJ, steps are a little different.
1. Open IDEA, and import project.
2. Select your build.gradle file and have it import.
3. Once it's finished you must close IntelliJ and run the following command:

"gradlew genIntellijRuns" (./gradlew genIntellijRuns if you are on Mac/Linux)

Step 4: The final step is to open Eclipse and switch your workspace to /eclipse/ (if you use IDEA, it should automatically start on your project)

If at any point you are missing libraries in your IDE, or you've run into problems you can run "gradlew --refresh-dependencies" to refresh the local cache. "gradlew clean" to reset everything {this does not effect your code} and then start the processs again.

Should it still not work, 
Refer to #ForgeGradle on EsperNet for more information about the gradle environment.

Tip:
If you do not care about seeing Minecraft's source code you can replace "setupDecompWorkspace" with one of the following:
"setupDevWorkspace": Will patch, deobfusicated, and gather required assets to run minecraft, but will not generated human readable source code.
"setupCIWorkspace": Same as Dev but will not download any assets. This is useful in build servers as it is the fastest because it does the least work.

Tip:
When using Decomp workspace, the Minecraft source code is NOT added to your workspace in a editable way. Minecraft is treated like a normal Library. Sources are there for documentation and research purposes and usually can be accessed under the 'referenced libraries' section of your IDE.

## Forge source installation
MinecraftForge ships with this code and installs it as part of the forge
installation process, no further action is required on your part.

## LexManos' Install Video
https://www.youtube.com/watch?v=8VEdtQLuLO0&feature=youtu.be

For more details update more often refer to the Forge Forums:
http://www.minecraftforge.net/forum/index.php/topic,14048.0.html
