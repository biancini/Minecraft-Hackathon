package it.reti.minecraft.plugin.sample.hooks;

import it.reti.minecraft.plugin.sample.SamplePlugin;
import it.reti.minecraft.plugin.sample.helpers.GenericExtension;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import it.reti.minecraft.plugin.sample.tasks.MuccaTask;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Classe che implementa un hook in grado di gestire l'evento di interazione
 * (click col tasto destro del mouse) dell'utente quando nella mano principale
 * possiede della pelle.
 * 
 * La documentazione sulla libreria Forge è navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@MinecraftEvent(aliases = {},
	description = "Evento che ascolta il click sull'elemento pelle.",
	registerInEventBus = true)
public class LeatherHook extends GenericExtension {

	/**
	 * Metodo che si sottoscrive come listener dell'evento di interazione dell'utente.
	 * @param event l'evento di interazione
	 */
	@SubscribeEvent
	public void interactLeather(RightClickItem event) {
		// Se l'evento è cancellato, ignoralo.
		if (event.isCanceled()) {
			return;
		}
		
		// Recupera il giocatore che ha scatenato l'evento.
		EntityPlayer player = (EntityPlayer) event.getEntityPlayer();
		// Se l'evento è generato sul server ignoralo.
		if (player instanceof EntityPlayerSP) return;

		// Controlla se il giocatore ha selezionato un elemento di tipo pelle.
		if ("item.leather".equals(player.getHeldItemMainhand().getItem().getUnlocalizedName())) {
			BlockPos loc = new BlockPos(player.posX, player.posY + 2, player.posZ);

			// Crea una nuova mucca e associale un task che la fa bruciare ed esplodere quando tocca terra.
			EntityCow victim = (EntityCow) creaEssereVivente(player.getEntityWorld(), loc, EntityCow.class);
			//Canary.getServer().addSynchronousTask(new MuccaTask(victim));
			victim.tasks.addTask(0, new MuccaTask(victim));
			SamplePlugin.logger.info("Creato un MuccaTask per la povera mucca appena lanciata!");

			// Lancia la mucca e incendiala.
			lancia(player, victim, 3);
			victim.setFire(600);
		}
	}
	
	private void lancia(EntityPlayer player, EntityLiving entity, double factor) {
		Vec3d looking = player.getLookVec();
		
		if (looking != null) {
			entity.motionX = looking.xCoord * factor;
			entity.motionY = looking.yCoord * factor;
			entity.motionZ = looking.zCoord * factor;
		}
	}
}
