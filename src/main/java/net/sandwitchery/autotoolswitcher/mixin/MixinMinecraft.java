package net.sandwitchery.autotoolswitcher.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.sandwitchery.autotoolswitcher.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow private static Minecraft INSTANCE;

    @Shadow public World world;

    @Shadow public ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo info) {
        if (world != null) {
            if (Main.switcher) {
                if(Main.lastSlot){
                    player.inventory.selectedSlot = 8;
                } else {
                    int tool = -1;
                    @Nullable
                    HitResult hr = INSTANCE.field_2823;
                    if (hr != null) {
                        @Nullable
                        BlockState b = INSTANCE.world.getBlockState(hr.blockX, hr.blockY, hr.blockZ);
                        if (b != null) {
                            for (int t = 0; t < 9; t++) {
                                ItemStack is = player.inventory.getStack(t);
                                if (is != null && is.isSuitableFor(b)) {
                                    tool = t;
                                    break;
                                }
                            }
                            if (tool >= 0) {
                                player.inventory.selectedSlot = tool;
                            }
                        }
                    }
                    if (tool < 0) {
                        int sword = -1;
                        for (int t = 0; t < 9; t++) {
                            ItemStack is = player.inventory.getStack(t);
                            if (is != null && is.getItem() instanceof SwordItem) {
                                sword = t;
                                break;
                            }
                        }

                        if (sword >= 0) {
                            player.inventory.selectedSlot = sword;
                        }
                    }
                }
            }
        }
    }
}
