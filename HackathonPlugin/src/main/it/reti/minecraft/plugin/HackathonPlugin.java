/***
 * Excerpted from "Learn to Program with Minecraft Plugins, CanaryMod Edition",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/ahmine2 for more book information.
 ***/
package it.reti.minecraft.plugin;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandListener;
import net.canarymod.logger.Logman;

//
// This is a bit convoluted, but it will let you call the "buildMyHouse"
// function without having to see or read this code.
// You might want to pull out the "makeCube" function to use
// in another plugin; it's a triple-nested for-loop
// to make a 3D block.
//

public class HackathonPlugin extends GenericPlugin implements CommandListener {
	public static Logman logger;
	
	public HackathonPlugin() {
		logger = getLogman();
	}

	@Override
	public boolean enable() {
		logger.info("Starting up HackathonPlugin");

		try {
			Canary.commands().registerCommands(this, this, false);
		} catch (CommandDependencyException e) {
			logger.error("Duplicate command name");
		}
		return true;
	}

	@Override
	public void disable() {
		logger.info("Stopping");
	}
	
	// Write your plugin part here!
}
