package mod.acgaming.universaltweaks.tweaks.entities.breeding;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.config.UTConfigGeneral;
import mod.acgaming.universaltweaks.config.UTConfigTweaks;

@Mod.EventBusSubscriber(modid = UniversalTweaks.MODID)
public class UTEasyBreeding
{
    @SubscribeEvent
    public static void utEasyBreeding(EntityJoinWorldEvent event)
    {
        if (UTConfigTweaks.ENTITIES.EASY_BREEDING.utEasyBreedingToggle && event.getEntity() instanceof EntityAnimal)
        {
            if (UTConfigGeneral.DEBUG.utDebugToggle) UniversalTweaks.LOGGER.debug("UTEasyBreeding ::: Entity join world event");
            EntityAnimal animal = (EntityAnimal) event.getEntity();
            animal.tasks.addTask(2, new UTEasyBreedingAI(animal));
        }
    }
}