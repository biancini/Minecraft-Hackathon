package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.HelperFunctions;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class CreaCasa implements CommandListener {
	private void creaCubo(Location origin, int offsetX, int offsetY, int offsetZ, int width, int height, BlockType type) {
		int i, j, k;
		Location loc = new Location(origin.getWorld(), 0, 0, 0, 0, 0);

		// Crea un cubo facendo tre cicli for annidati (uno per la x, uno per la y e uno per la z).
		for (i = 0; i < width; i++) {
			for (j = 0; j < width; j++) {
				for (k = 0; k < height; k++) {
					loc.setX(origin.getX() + offsetX + i);
					loc.setZ(origin.getZ() + offsetZ + j);
					loc.setY(origin.getY() + offsetY + k);
					
					HelperFunctions.impostaBlocco(loc, type);
				}
			}
		}
	}
	@Command(aliases = { "creacasa" },
			description = "Costruisci una semplice casa per ripararsi dai mostri!",
			permissions = { "" },
			toolTip = "/creacasa")
	public void buildAHouseCommand(MessageReceiver caller, String[] parameters) {
		if (caller instanceof Player) {
			Player me = (Player) caller;
			Location origin = me.getLocation();

			// Dimensioni della casa
			int width = 5;
			int height = 5;

			// Centra la casa attorno al giocatore.
			origin.setY(origin.getY() - 1);
			origin.setZ(origin.getZ() - (width / 2));
			origin.setX(origin.getX() - (width / 2));

			// Imposta tutta l'area con un cubo di legno di quercia.
			creaCubo(origin, 0, 0, 0, width, height, BlockType.OakWood);
			// Imposta il centro del cubo con dei blocchi d'aria (per lasciare la zona "vuota".
			creaCubo(origin, 1, 1, 1, width - 2, height - 2, BlockType.Air);

			// Crea una porta nel muro
			Location loc = me.getLocation();

			// La porta viene creata attraverso la creazione di due blocchi.
			// Due valori "magici" sono impostati per segnalare la parte bassa e qualla alta della porta.
			loc.setY(loc.getY() + 1);
			HelperFunctions.impostaBlocco(loc, BlockType.WoodenDoor, (short) 0x4); // parte bassa
			loc.setY(loc.getY() + 1);
			HelperFunctions.impostaBlocco(loc, BlockType.WoodenDoor, (short) 0x8); // parte alta

			// Metti una torcia sopra la porta
			loc.setY(loc.getY() + 1);
			loc.setZ(loc.getZ() + 1);
			HelperFunctions.impostaBlocco(loc, BlockType.Torch);
		}
	}
	
}
