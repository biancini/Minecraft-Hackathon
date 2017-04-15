package it.reti.minecraft.plugin.sample;

import java.util.ArrayList;
import java.util.List;

import it.reti.minecraft.plugin.sample.commands.CreaCasa;
import it.reti.minecraft.plugin.sample.commands.Sky;
import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandListener;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;

/***
 * Classe per implementare un plugin di Minecraft.
 * La classe include alcune funzioni che possono essere utilizzate per svolgere le funzionalità più comuni.
 * 
 * La documentazione sulla libreria Canary è navigabile qui:
 * http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
public class SamplePlugin extends Plugin {
	protected static Logman logger;
	List<CommandListener> commands;

	/***
	 * Costruttore di default per creare il plugin.
	 * Questo costruttore inizializza il logger che può essere usato per scrivere dei messaggi sulla console.
	 */
	public SamplePlugin() {
		logger = getLogman();
		commands = new ArrayList<CommandListener>();
		commands.add(new CreaCasa());
		commands.add(new Sky());
	}

	/***
	 * Metodo che viene richiamato quando il plugin viene inizializzato.
	 */
	@Override
	public boolean enable() {
		logger.info("Avvio il plugin.");
		
		for (CommandListener commandListener : commands) {			
			try {
				Canary.commands().registerCommands(commandListener, this, false);
			} catch (CommandDependencyException e) {
				logger.error("Il nome comando è già stato usato.");
			}
		}
		
		return true;
	}

	/***
	 * Metodo che viene richiamato quando il plugin viene disabilitato.
	 */
	@Override
	public void disable() {
		logger.info("Disabilito il plugin.");
		Canary.commands().unregisterCommands(this);
	}
}
