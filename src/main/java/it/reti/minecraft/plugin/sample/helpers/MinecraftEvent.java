package it.reti.minecraft.plugin.sample.helpers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MinecraftEvent {
	
	String[] aliases();
	String description();
	
	boolean registerInEventBus() default true;
	
}
