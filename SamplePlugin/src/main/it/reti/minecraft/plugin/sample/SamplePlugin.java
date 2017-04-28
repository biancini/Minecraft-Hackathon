package it.reti.minecraft.plugin.sample;

import java.util.ArrayList;
import java.util.List;

import it.reti.minecraft.plugin.sample.commands.CreaCasa;
import it.reti.minecraft.plugin.sample.commands.Mandria;
import it.reti.minecraft.plugin.sample.commands.Rigenera;
import it.reti.minecraft.plugin.sample.commands.Sky;
import it.reti.minecraft.plugin.sample.hooks.LeatherHook;
import net.canarymod.Canary;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandListener;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.canarymod.plugin.PluginListener;

/**
 * Classe per implementare un plugin di Minecraft.
 * La classe include alcune funzioni che possono essere utilizzate per svolgere le funzionalit� pi� comuni.
 * 
 * La documentazione sulla libreria Canary � navigabile qui:
 * http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
public class SamplePlugin extends Plugin {

	public static Logman logger;
	private List<CommandListener> commands;
	private List<PluginListener> hooks;

	/**
	 * Costruttore di default per creare il plugin.
	 * Questo costruttore inizializza il logger che pu� essere usato per scrivere dei messaggi sulla console.
	 */
	public SamplePlugin() {
		logger = getLogman();
		
		commands = new ArrayList<CommandListener>();
		commands.add(new CreaCasa());
		commands.add(new Sky());
		commands.add(new Mandria());
		commands.add(new Rigenera());
		
		hooks = new ArrayList<PluginListener>();
		hooks.add(new LeatherHook());
	}

	/**
	 * Metodo che viene richiamato quando il plugin viene inizializzato.
	 */
	@Override
	public boolean enable() {
		logger.info("Avvio il plugin.");
		
		for (PluginListener pluginListener : hooks) {			
			Canary.hooks().registerListener(pluginListener, this);
		}
		
		for (CommandListener commandListener : commands) {			
			try {
				Canary.commands().registerCommands(commandListener, this, false);
			} catch (CommandDependencyException e) {
				logger.error("Il nome comando � gi� stato usato.");
			}
		}
		
		logger.warn("Se non lo hai gi� fatto ricordati di darti i permessi di operatore.\n"
				+ "Per darti i permessi di operatore � necessario dare il seguente comando al server:\n"
				+ "op [Username]");
		
		return true;
	}

	/**
	 * Metodo che viene richiamato quando il plugin viene disabilitato.
	 */
	@Override
	public void disable() {
		logger.info("Disabilito il plugin.");
		
		for (PluginListener pluginListener : hooks) {			
			Canary.hooks().unregisterPluginListener(pluginListener);
		}
		
		Canary.commands().unregisterCommands(this);
	}
}
