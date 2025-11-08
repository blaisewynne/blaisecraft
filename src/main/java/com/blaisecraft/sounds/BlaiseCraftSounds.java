package com.blaisecraft.sounds;

import com.blaisecraft.BlaiseCraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class BlaiseCraftSounds {
    private BlaiseCraftSounds() {
    }

    public static final SoundEvent VAMPIRE_AMBIENT = registerSound("vampire_noise");
    public static final SoundEvent WEREWOLF_AMBIENT = registerSound("werewolf_ambient");
    public static final SoundEvent WEREWOLF_HURT = registerSound("werewolf_hurt");
  

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(BlaiseCraft.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }


    public static void initialize() {

    }
}
