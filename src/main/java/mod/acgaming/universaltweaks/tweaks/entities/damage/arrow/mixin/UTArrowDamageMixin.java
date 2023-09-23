package mod.acgaming.universaltweaks.tweaks.entities.damage.arrow.mixin;

import java.util.Random;

import net.minecraft.entity.projectile.EntityArrow;

import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.config.UTConfigGeneral;
import mod.acgaming.universaltweaks.config.UTConfigTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityArrow.class)
public class UTArrowDamageMixin
{
    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    public int utArrowDamage(Random random, int bound)
    {
        if (UTConfigGeneral.DEBUG.utDebugToggle) UniversalTweaks.LOGGER.debug("UTArrowDamage ::: Critical arrow");
        return UTConfigTweaks.ENTITIES.utCriticalArrowDamage > 0 ? UTConfigTweaks.ENTITIES.utCriticalArrowDamage : 0;
    }
}