package net.sandwitchery.autotoolswitcher.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.sandwitchery.autotoolswitcher.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud extends DrawContext {
    final String enabledString = "Autoswitch enabled";

    @Shadow private Minecraft minecraft;

    @Inject(method = "render", at=@At("TAIL"))
    private void onRender(float f, boolean bl, int mouseX, int mouseY, CallbackInfo info){
//        class_564 var5 = new class_564(minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
//        int scaledWidth = var5.method_1857();
//        int scaledHeight = var5.method_1858();
        if(Main.switcher && !minecraft.options.debugHud) {
            drawTextWithShadow(minecraft.textRenderer, enabledString, 2, 11, 0xFFFFFF);
        }
    }
}
