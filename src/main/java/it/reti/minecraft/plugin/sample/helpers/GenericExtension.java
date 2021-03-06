package it.reti.minecraft.plugin.sample.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/**
 * Classe per che implementa delle funzioni helper. La classe include alcune
 * funzioni che possono essere utilizzate per svolgere le funzionalit� pi�
 * comuni.
 * 
 * La documentazione sulla libreria Forge � navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
public abstract class GenericExtension {
	/**
	 * Metodo per impostare un blocco in una determinata posizione.
	 * 
	 * @param w il mondo in cui si trova il giocatore
	 * @param pos la posizione in cui deve essere impostate il blocco.
	 * @param type il tipo di blocco da impostare.
	 */
	public void impostaBlocco(World w, BlockPos pos, Block type) {
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
	public <T extends Comparable<T>, V extends T> void impostaBlocco(World w, BlockPos pos, Block type,
			IProperty<T> property, V value) {
		w.setBlockState(pos, type.getBlockState().getBaseState().withProperty(property, value));
	}

	/**
	 * Metodo che crea un essere vivente.
	 * 
	 * @param w il mondo in cui si trova il giocatore
	 * @param pos la posizione dove creare l'essere vivente.
	 * @param type il tipo di essere vivente da creare.
	 * @return l'essere vivente creato.
	 */
	public EntityLiving creaEssereVivente(World w, BlockPos pos, Class<? extends EntityLiving> type) {
		try {
			EntityLiving living = type.getConstructor(World.class).newInstance(w);
			if (!w.spawnEntity(living)) {
				throw new Exception("Errore durante lo spawn dell'essere vivente!");
			}
			living.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			return living;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Metodo che manda un messaggio di errore all'utente.
	 * 
	 * @param sender l'oggetto che deve inviare l'errore.
	 * @param message il messaggio di errore
	 */
	public void sendErrorMessage(ICommandSender sender, String message) {
		Style red = new Style().setColor(TextFormatting.DARK_RED);
		sender.sendMessage(new TextComponentString(message).setStyle(red));
	}

}