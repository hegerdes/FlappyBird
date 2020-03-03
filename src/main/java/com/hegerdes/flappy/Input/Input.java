package com.hegerdes.flappy.Input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[6000];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action == GLFW.GLFW_PRESS;

    }
    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}
