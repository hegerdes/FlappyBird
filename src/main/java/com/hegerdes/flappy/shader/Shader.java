package com.hegerdes.flappy.shader;

import com.hegerdes.flappy.math.Matrix4f;
import com.hegerdes.flappy.math.Vector3f;
import com.hegerdes.flappy.utils.ShaderUtils;
import java.util.HashMap;
import java.util.Map;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int ID;
    private Map<String, Integer> lacationCache = new HashMap<String,Integer>();

    public Shader(String vertex, String fragment){
        ID = ShaderUtils.load(vertex,fragment);
    }

    public int getUniform(String name){
        if(lacationCache.containsKey(name)){
            return lacationCache.get(name);
        }
        int i = glGetUniformLocation(ID, name);
        if(i == -1) System.err.println("Cant find uniform: " + name);

        lacationCache.put(name,i);
        return i;
    }

    public void setUniform1i(String name, int value){
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value){
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y){
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vec){
        glUniform3f(getUniform(name), vec.x, vec.y, vec.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix){
        glUniformMatrix4fv(getUniform(name),false, matrix.toFloatBuffer());
    }

    public void enable(){
        glUseProgram(ID);
    }

    public void disable(){
        glUseProgram(0);
    }
}
