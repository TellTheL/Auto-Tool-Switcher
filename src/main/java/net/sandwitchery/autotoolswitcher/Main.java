package net.sandwitchery.autotoolswitcher;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class Main {
    public static boolean switcher = false;
    public static boolean lastSlot = false;
    KeyBinding toggleKey = new KeyBinding("key.autotoolswitcher.toggle", Keyboard.KEY_X);
    KeyBinding lastSlotKey = new KeyBinding("key.autotoolswitcher.lastslot", Keyboard.KEY_LCONTROL);

    @EventListener
    public void onStart(KeyBindingRegisterEvent event){
        event.keyBindings.add(toggleKey);
        event.keyBindings.add(lastSlotKey);
    }

    @EventListener
    public void onKeyChange(KeyStateChangedEvent event){
        if(event.environment == KeyStateChangedEvent.Environment.IN_GAME){
            if(Keyboard.getEventKey() == toggleKey.code) {
                if (Keyboard.getEventKeyState()) {
                    switcher = !switcher;
                }
            } else if(Keyboard.getEventKey() == lastSlotKey.code){
                lastSlot = Keyboard.getEventKeyState();
            }
        }
    }
}
