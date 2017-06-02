package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Classe per il comando creacasa che costruisce una casa attorno al giocatore che lo invoca.
 * 
 * La documentazione sulla libreria Forge è navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@MinecraftEvent(aliases = { "creacasa" },
	description = "Costruisci una semplice casa per ripararsi dai mostri!",
	registerInEventBus = true)
public class CreaCasa extends GenericCommand implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		EntityPlayer me = (EntityPlayer) sender;
		BlockPos origin = me.getPosition();
		World w = me.getEntityWorld();

		// Dimensioni della casa
		int width = 5;
		int height = 5;

		// Centra la casa attorno al giocatore.
		origin = new BlockPos(origin.getX() - (width / 2), origin.getY() - 1, origin.getZ() - (width / 2));

		// Imposta tutta l'area con un cubo di legno di quercia.
		creaCubo(w, origin, 0, 0, 0, width, height, Blocks.LOG);
		// Imposta il centro del cubo con dei blocchi d'aria (per lasciare la zona "vuota").
		creaCubo(w, origin, 1, 1, 1, width - 2, height - 2, Blocks.AIR);

		// Crea una porta nel muro
		BlockPos loc = new BlockPos(origin.getX() + (width / 2), origin.getY(),	origin.getZ());

		// La porta viene creata attraverso la creazione di due blocchi.
		// Due valori "magici" sono impostati per segnalare la parte bassa e
		// qualla alta della porta.
		PropertyEnum<EnumDoorHalf> HALF = PropertyEnum.create("half", EnumDoorHalf.class);
		loc = new BlockPos(loc.getX(), loc.getY() + 1, loc.getZ());
		impostaBlocco(w, loc, Blocks.OAK_DOOR, HALF, EnumDoorHalf.LOWER); //parte bassa
		loc = new BlockPos(loc.getX(), loc.getY() + 1, loc.getZ());
		impostaBlocco(w, loc, Blocks.OAK_DOOR, HALF, EnumDoorHalf.UPPER); //parte alta

		// Metti una torcia sopra la porta
		loc = new BlockPos(loc.getX(), loc.getY() + 1, loc.getZ() + 1);
		impostaBlocco(w, loc, Blocks.TORCH);
	}

	private void creaCubo(World w, BlockPos origin, int offsetX, int offsetY, int offsetZ, int width, int height, Block type) {
		BlockPos loc = new BlockPos(0, 0, 0);

		// Crea un cubo facendo tre cicli for annidati (uno per la x, uno per la y e uno per la z).
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < height; k++) {
					loc = new BlockPos(origin.getX() + offsetX + i, origin.getY() + offsetY + k, origin.getZ() + offsetZ + j);

					impostaBlocco(w, loc, type);
				}
			}
		}
	}

}
