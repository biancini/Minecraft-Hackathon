package it.reti.minecraft.plugin.hackathon;

import net.canarymod.Canary;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.factory.EntityFactory;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.effects.Particle;
import net.canarymod.api.world.effects.SoundEffect;
import net.canarymod.api.world.position.Location;

/**
 * Classe per che implementa delle funzioni helper.
 * La classe include alcune funzioni che possono essere utilizzate per svolgere le funzionalità più comuni.
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
	 * @param loc la posizione in cui deve essere impostate il blocco.
	 * @param type il tipo di blocco da impostare.
	 */
	public static void impostaBlocco(Location loc, BlockType type) {
		World w = loc.getWorld();
		w.setBlockAt(loc, type);
	}
	
	/**
	 * Metodo per impostare un blocco in una determinata posizione.
	 * 
	 * @param loc la posizione in cui deve essere impostate il blocco.
	 * @param type il tipo di blocco da impostare.
	 * @param c dei dati specifici per il blocco che si sta creando.
	 */
	public static void impostaBlocco(Location loc, BlockType type, short c) {
		World w = loc.getWorld();
		w.setBlockAt(loc, type.getId(), c);
	}

	/**
	 * Metodo che permette di riprodurre un suono.
	 * 
	 * @param loc la posizione in cui ci si trova.
	 * @param type il tipo di suono da riprodurre.
	 */
	public static void emettiSuono(Location loc, SoundEffect.Type type) {
		World w = loc.getWorld();
		SoundEffect s = new SoundEffect(type, loc.getX(), loc.getY(), loc.getZ(), 1.0f, 1.0f);
		w.playSound(s);
	}

	/**
	 * Metodo che crea un essere vivente.
	 * 
	 * @param loc la posizione dove creare l'essere vivente.
	 * @param type il tipo di essere vivente da creare.
	 * @return l'essere vivente creato.
	 */
	public static EntityLiving creaEssereVivente(Location loc, EntityType type) {
		EntityFactory factory = Canary.factory().getEntityFactory();
		
		EntityLiving thing = factory.newEntityLiving(type, loc);
		thing.spawn();
		
		return thing;
	}

	/**
	 * Metodo che crea una particella nel mondo.
	 * 
	 * @param loc la posizione dove creare la particella.
	 * @param type il tipo di particella da creare.
	 */
	public static void creaParticella(Location loc, Particle.Type type) {
		World w = loc.getWorld();
		w.spawnParticle(new Particle(loc.getX(), loc.getY(), loc.getZ(), type));
	}

}