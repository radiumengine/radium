package Radium.Graphics.Renderers;

import Radium.Components.Graphics.MeshFilter;
import Radium.Components.Graphics.Outline;
import Radium.Graphics.Shader.Shader;
import Radium.Objects.GameObject;

public final class LitRenderer extends Renderer {

    
    public void Initialize() {
        shader = new Shader("EngineAssets/Shaders/Lit/vert.glsl", "EngineAssets/Shaders/Lit/frag.glsl");
    }

    
    public void SetUniforms(GameObject gameObject) {
        gameObject.GetComponent(MeshFilter.class).SendMaterialToShader(shader);

        Outline outline = gameObject.GetComponent(Outline.class);
        if (outline != null) {
            outline.SendUniforms();
        }
    }

}
