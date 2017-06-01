package it.reti.minecraft.plugin.sample.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/**
 * Classe per che implementa delle funzioni helper. La classe include alcune
 * funzioni che possono essere utilizzate per svolgere le funzionalità più
 * comuni.
 * 
 * La documentazione sulla libreria Canary è navigabile qui:
 * http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
public class HelperFunctions {
	/**
	 * Metodo per impostare un blocco in una determinata posizione.
	 * 
	 * @param w il mondo in cui si trova il giocatore
	 * @param loc la posizione in cui deve essere impostate il blocco.
	 * @param type il tipo di blocco da impostare.
	 */
	public static void impostaBlocco(World w, BlockPos pos, Block type) {
		w.setBlockState(pos, type.getBlockState().getBaseState());
	}
	
	/**
	 * Metodo per impostare un blocco in una determinata posizione.
	 * 
	 * @param w il mondo in cui si trova il giocatore
	 * @param loc la posizione in cui deve essere impostate il blocco.
	 * @param type il tipo di blocco da impostare.
	 * @param flag il flag che indica il block state
	 */
	public static <T extends Comparable<T>, V extends T>  void impostaBlocco(World w, BlockPos pos, Block type, IProperty<T> property, V value) {
		w.setBlockState(pos, type.getBlockState().getBaseState().withProperty(property, value));
	}

	/**
	 * Metodo che crea un essere vivente.
	 * 
	 * @param loc
	 *            la posizione dove creare l'essere vivente.
	 * @param type
	 *            il tipo di essere vivente da creare.
	 * @return l'essere vivente creato.
	 */
	public static EntityLiving creaEssereVivente(World w, BlockPos pos, Class<? extends EntityLiving> type) {
		try {
			EntityLiving living = type.getConstructor(World.class).newInstance(w);
			living.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			w.spawnEntity(living);
			return living;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Metodo che fa svolazzare un entità.
	 * 
	 * @param player
	 *            il giocatore che lo richiama.
	 * @param entity
	 *            l'entità da far svolazzare.
	 * @param factor
	 *            fattore che indica la dimensione del volo.
	 */
	public static void svolazza(EntityPlayer player, EntityLiving entity, double factor) {
		double pitch = (player.cameraPitch + 90.0F) * Math.PI / 180.0D;
		double rot = (player.rotationPitch + 90.0F) * Math.PI / 180.0D;
		double x = Math.sin(pitch) * Math.cos(rot);
		double z = Math.sin(pitch) * Math.sin(rot);
		double y = Math.cos(pitch);

		entity.move(MoverType.PLAYER, x * factor, y + 0.5, z * factor);
	}
	
	/**
	 * Metodo che manda un messaggio di errore all'utente.
	 * 
	 * @param sender l'oggetto che deve inviare l'errore.
	 * @param message il messaggio di errore
	 */
	public static void sendErrorMessage(ICommandSender sender, String message) {
		Style red = new Style().setColor(TextFormatting.DARK_RED);
		sender.sendMessage(new TextComponentString(message).setStyle(red));
	}

}