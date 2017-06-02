package it.reti.minecraft.plugin.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;

import it.reti.minecraft.plugin.sample.commands.CreaCasa;
import it.reti.minecraft.plugin.sample.commands.Mandria;
import it.reti.minecraft.plugin.sample.commands.Rigenera;
import it.reti.minecraft.plugin.sample.commands.Sky;
import it.reti.minecraft.plugin.sample.helpers.GenericExtension;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import it.reti.minecraft.plugin.sample.hooks.LeatherHook;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Classe per implementare un plugin di Minecraft.
 * La classe include alcune funzioni che possono essere utilizzate per svolgere le funzionalità più comuni.
 * 
 * La documentazione sulla libreria Forge è navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@Mod(modid = SamplePlugin.MODID, name = SamplePlugin.NAME, version = SamplePlugin.VERSION)
public class SamplePlugin
{
    public static final String MODID = "sampleplugin";
    public static final String NAME = "Sample Plugin";
    public static final String VERSION = "1.0";

	public static Logger logger;
	private List<GenericExtension> extensions;

	/**
	 * Costruttore di default per creare il plugin.
	 * Questo costruttore inizializza il logger che può essere usato per scrivere dei messaggi sulla console.
	 */
	public SamplePlugin() {
		logger = FMLLog.getLogger();
		
		extensions = new ArrayList<GenericExtension>();
		extensions.add(new CreaCasa());
		extensions.add(new Sky());
		extensions.add(new Mandria());
		extensions.add(new Rigenera());
		extensions.add(new LeatherHook());
	}

	/**
	 * Gestore di eventi che inizializza il plugin creato.
	 * Tutte le estensioni registrate vengono fatte scorrere e quelle che hanno
	 * nell'annotazione "registerInEventBus" vengono registrate sul gestore di eventi
	 * di Minecraft.
	 * 
	 * @param event evento di inizializzazione.
	 */
	@EventHandler
    public void init(FMLInitializationEvent event) {
		logger.info("Avvio il plugin.");
		
		for (GenericExtension extension : extensions) {
			MinecraftEvent ann = (MinecraftEvent) extension.getClass().getAnnotation(MinecraftEvent.class);
        	if (ann == null) continue;
        	
        	if (ann.registerInEventBus()) {
        		MinecraftForge.EVENT_BUS.register(extension);
        	}
        }
        
        logger.warn("Se non lo hai già fatto ricordati di darti i permessi di operatore.\n"
				+ "Per darti i permessi di operatore è necessario dare il seguente comando al server:\n"
				+ "op [Username]");
    }
    
	/**
	 * Gestore di eventi che permette di registrare i comandi contenuti nel plugin.
	 * Tutte le estensioni registrate vengono fatte scorrere e quelle che implementano
	 * l'interfaccia ICommand (usata per definire i comandi di Minecraft) vengono registrate
	 * come comandi).
	 * 
	 * @param event evento di inizializzazione.
	 */
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
    	for (GenericExtension extension : extensions) {
    		if (Arrays.asList(extension.getClass().getInterfaces()).contains(ICommand.class)) {
        		event.registerServerCommand((ICommand) extension);
        	}
        }
    }
}
