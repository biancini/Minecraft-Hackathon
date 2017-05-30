package it.reti.minecraft.plugin.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;

import it.reti.minecraft.plugin.sample.commands.CreaCasa;
import it.reti.minecraft.plugin.sample.commands.Mandria;
import it.reti.minecraft.plugin.sample.commands.Rigenera;
import it.reti.minecraft.plugin.sample.commands.Sky;
import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Classe per implementare un plugin di Minecraft.
 * La classe include alcune funzioni che possono essere utilizzate per svolgere le funzionalità più comuni.
 * 
 * La documentazione sulla libreria Canary è navigabile qui:
 * http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html
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
	private List<GenericCommand> commands;

	/**
	 * Costruttore di default per creare il plugin.
	 * Questo costruttore inizializza il logger che può essere usato per scrivere dei messaggi sulla console.
	 */
	public SamplePlugin() {
		logger = FMLLog.getLogger();
		
		commands = new ArrayList<GenericCommand>();
		commands.add(new CreaCasa());
		commands.add(new Sky());
		commands.add(new Mandria());
		commands.add(new Rigenera());
		
		//hooks.add(new LeatherHook());
	}

	@EventHandler
    public void init(FMLInitializationEvent event) throws InstantiationException, IllegalAccessException
    {
		logger.info("Avvio il plugin.");
		
		for (GenericCommand command : commands) {
			Command ann = (Command) command.getClass().getAnnotation(Command.class);
        	if (ann == null) continue;
        	
        	if (ann.registerInEventBus()) {
        		MinecraftForge.EVENT_BUS.register(command);
        	}
        	
			if (ann.registerGameEvent()) {
				FMLCommonHandler.instance().bus().register(command);
			}
        }
        
        logger.warn("Se non lo hai già fatto ricordati di darti i permessi di operatore.\n"
				+ "Per darti i permessi di operatore è necessario dare il seguente comando al server:\n"
				+ "op [Username]");
    }
    
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	for (GenericCommand command : commands) {
    		if (Arrays.asList(command.getClass().getInterfaces()).contains(ICommand.class)) {
        		event.registerServerCommand(command);
        	}
        }
    }
}
