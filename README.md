# Minecraft-Hackathon
Progetto per realizzare un Hackathon che permetta di sviluppare plugin per Minecraft.

Questo progetto contiene il codice Java necessario per realizzare un plugin per Minecraft utilizzando la mod Minecraft chiamata CanaryMod [https://canarymod.net/].
La documentazione sulla libreria Canary è navigabile qui: [http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html].
Il progetto &egrave; stato sviluppato con Eclipse [http://www.eclipse.org/] ma &egrave; possibile utilizzare qualsiasi IDE per sviluppare il proprio plugin.
Per realizzare un plugin, inoltre, &egrave; necessario installare il Java Development Kit (JDK) [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html].

Il server Minecraft &egrave; gi&agrave; disponibile all'interno di questo progetto, insieme ad esso questo progetto contiene anche un plugin d'esempio che mostri le possibilit&agrave; di implementazione offerte da CanaryMod e un progetto che idealmente dovr&agrave; essere utilizzato come base di partenza per lo sviluppo dell'hackathon.

## server
Questa cartella contiene il server Minecraft realizzato da CanaryMod. Per startare il server Minecraft, &egrave; possibile usare il comando:
``
java -Xms1024M -Xmx1024M -jar lib/CanaryMod-1.7.10-1.1.2.jar
``
Se si utilizza Eclipse, &egrave; possibile lanciare il server anche richiamando la configurazione di lancio `Minecraft Server`.

## SamplePlugin
Questa cartella contiene i files necessari a definire un plugin di esempio che mostra alcune funzionalit&agrave; di CanaryMod.
In particolare il plugin implementa diverse funzionalità richiamabili con un comando dalla chat del gioco. Inoltre il plugin crea un task asincrono che viene eseguito automaticamente dal server e registra un listener in grado di percepire la presenza di evento nel gioco per effettuare delle operazioni quando si verificano determinati eventi.

Il plugin implementa diverse funzionalit&agrave; d'esempio, di seguito elencate:
- con il comando `/creacasa` verr&agrave; creata una casa (5x5) di legno attorno al giocatore;
- con il comando `/sky` tutti gli esseri viventi presenti nel mondo, ad esclusione dei giocatori, verranno portati in cielo di 50 blocchi e lasciati cadere;
- con il comando `/rigenera` il giocatore otterr&agrave; il massimo dei punti energia e dei punti cibo;
- con il comando `/mandria ##` verr&agrave; creata una mandria di N mucche (con N uguale al secondo parametro numerico passato al comando) attorno al giocatore;
- facendo click col tasto destro quando si ha selezionato il materiale pelle, verr&agrave; "sparata" una mucca infuocata che, al contatto col terreno, esploder&agrave;

Il progetto pu&ograve; essere compilato come un semplice programma java, &egrave; importante che durante la compilazione venga aggiunta la dipendenza a `CanaryMod-1.7.10-1.1.2.jar` in modo che tutte le classi e le dipendenze possano essere risolte. Una volta compilato il jar, &egrave; sufficiente copiarlo nella cartella `plugins ` del server perch&egrave; al successivo riavvio del server stesso il plugin sia disponibile.
Se si utilizza Eclipse, &egrave; possibile compilare il plugin e renderlo disponibile al server richiamando la configurazione di lancio `Build Plugin SamplePlugin`.

## HackathonPlugin
Questa cartella contiene i fiels necessari a definire un altro plugin che conterr&agrave; il codice realizzato durante l'hackathon.

Il progetto pu&ograve; essere compilato come un semplice programma java, &egrave; importante che durante la compilazione venga aggiunta la dipendenza a `CanaryMod-1.7.10-1.1.2.jar` in modo che tutte le classi e le dipendenze possano essere risolte. Una volta compilato il jar, &egrave; sufficiente copiarlo nella cartella `plugins ` del server perch&egrave; al successivo riavvio del server stesso il plugin sia disponibile.
Se si utilizza Eclipse, &egrave; possibile compilare il plugin e renderlo disponibile al server richiamando la configurazione di lancio `Build Plugin HackathonPlugin`.
