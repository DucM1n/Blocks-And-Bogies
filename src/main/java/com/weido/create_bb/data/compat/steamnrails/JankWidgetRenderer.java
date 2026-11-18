package com.weido.create_bb.data.compat.steamnrails;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;

import java.lang.reflect.Method;

public class JankWidgetRenderer {
    public static <T extends GuiEventListener & Renderable & NarratableEntry> void addRenderableWidgetReflect(Screen screen, T widget) {
        String[] methodNames = new String[]{"addRenderableWidget", "method_37063", "m_142416_"};
        for (String name : methodNames) {
            for (Method method : Screen.class.getDeclaredMethods()) {
                if (method.getName().equals(name)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(screen, widget);
                        return;
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }
}
