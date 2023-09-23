package mod.acgaming.universaltweaks.mods.botania.mixin;

import mod.acgaming.universaltweaks.UniversalTweaks;
import mod.acgaming.universaltweaks.config.UTConfigGeneral;
import mod.acgaming.universaltweaks.config.UTConfigTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.core.helper.ExperienceHelper;

@Mixin(value = ExperienceHelper.class, remap = false)
public class UTBotaniaLinearXPMixin
{
    @Inject(method = "getExperienceForLevel", at = @At("HEAD"), cancellable = true)
    private static void utBotaniaLinearXP(int level, CallbackInfoReturnable<Integer> cir)
    {
        if (UTConfigTweaks.MISC.utLinearXP < 1) return;
        if (UTConfigGeneral.DEBUG.utDebugToggle) UniversalTweaks.LOGGER.debug("UTBotaniaLinearXP ::: Get experience for level");
        cir.setReturnValue(level * UTConfigTweaks.MISC.utLinearXP);
    }
}