package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.HelperFunctions;
import it.reti.minecraft.plugin.sample.helpers.Location;
import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;

@Command(aliases = { "creacasa" },
	description = "Costruisci una semplice casa per ripararsi dai mostri!",
	registerInEventBus = true,
	registerGameEvent = false)
public class CreaCasa extends GenericCommand implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		Location origin = new Location(me);

		// Dimensioni della casa
		int width = 5;
		int height = 5;

		// Centra la casa attorno al giocatore.
		origin.setPosY(origin.getPosY() - 1);
		origin.setPosZ(origin.getPosZ() - (width / 2));
		origin.setPosX(origin.getPosX() - (width / 2));

		// Imposta tutta l'area con un cubo di legno di quercia.
		creaCubo(origin, 0, 0, 0, width, height, Blocks.LOG);
		// Imposta il centro del cubo con dei blocchi d'aria (per lasciare la
		// zona "vuota".
		creaCubo(origin, 1, 1, 1, width - 2, height - 2, Blocks.AIR);

		// Crea una porta nel muro
		Location loc = new Location(origin.getWorld(), origin.getPosX() + (width / 2), origin.getPosY(),
				origin.getPosZ());

		// La porta viene creata attraverso la creazione di due blocchi.
		// Due valori "magici" sono impostati per segnalare la parte bassa e
		// qualla alta della porta.
		loc.setPosY(loc.getPosY() + 1);
		HelperFunctions.impostaBlocco(loc, Blocks.OAK_DOOR);
		loc.setPosY(loc.getPosY() + 1);
		HelperFunctions.impostaBlocco(loc, Blocks.OAK_DOOR);

		// Metti una torcia sopra la porta
		loc.setPosY(loc.getPosY() + 1);
		loc.setPosZ(loc.getPosZ() + 1);
		HelperFunctions.impostaBlocco(loc, Blocks.TORCH);
	}

	private void creaCubo(Location origin, int offsetX, int offsetY, int offsetZ, int width, int height, Block type) {
		int i, j, k;
		Location loc = new Location(origin.getWorld(), 0, 0, 0);

		// Crea un cubo facendo tre cicli for annidati (uno per la x, uno per la
		// y e uno per la z).
		for (i = 0; i < width; i++) {
			for (j = 0; j < width; j++) {
				for (k = 0; k < height; k++) {
					loc.setPosX(origin.getPosX() + offsetX + i);
					loc.setPosZ(origin.getPosZ() + offsetZ + j);
					loc.setPosY(origin.getPosY() + offsetY + k);

					HelperFunctions.impostaBlocco(loc, type);
				}
			}
		}
	}

}
