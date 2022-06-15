package Radium.Components.Audio;

import Radium.Audio.Audio;
import Radium.Audio.AudioType;
import Radium.Math.Vector.Vector3;
import java.io.File;
import Radium.Variables;
import RadiumEditor.Annotations.ExecuteGUI;
import RadiumEditor.Annotations.RunInEditMode;
import Radium.Component;
import Radium.Graphics.Texture;
import Radium.PerformanceImpact;
import RadiumEditor.Console;
import RadiumEditor.EditorGUI;
import org.lwjgl.openal.*;

/**
 * Loads and plays audio files
 */
@RunInEditMode
public class Source extends Component {

    @ExecuteGUI("AUDIO_CLIP")
    private File audioClip;
    public AudioType audioType = AudioType.ThreeDimensional;
    public float pitch = 1;
    public float gain = 1;
    public boolean loop = false;

    private int source = 0;

    /**
     * Generate an empty Source with no audio
     */
    public Source() {
        icon = new Texture("EngineAssets/Editor/Icons/source.png").textureID;

        name = "Source";
        description = "Loads and plays sounds";
        impact = PerformanceImpact.Low;
        submenu = "Audio";
    }

    
    public void Start() {
        Play();
    }

    
    public void Update() {
        if (audioType == AudioType.ThreeDimensional) {
            Vector3 pos = gameObject.transform.WorldPosition();
            AL11.alSource3f(source, AL11.AL_POSITION, pos.x, pos.y, pos.z);
            AL11.alSource3f(source, AL11.AL_VELOCITY, 0, 0, 0);
        } else {
            Vector3 camPos = Variables.DefaultCamera.gameObject.transform.WorldPosition();
            AL11.alSource3f(source, AL11.AL_POSITION, camPos.x, camPos.y, camPos.z);
        }
    }

    
    public void Stop() {
        StopPlay();
    }
    
    public void OnAdd() {

    }

    @Override
    public void UpdateVariable(String variableName) {
        AL11.alSourcef(source, AL11.AL_PITCH, pitch);
        AL11.alSourcef(source, AL11.AL_GAIN, gain);
        AL11.alSourcei(source, AL11.AL_LOOPING, loop ? AL11.AL_TRUE : AL11.AL_FALSE);
    }

    private static String[] allowedTypes = new String[] { "ogg" };

    @Override
    public void ExecuteGUI(String name) {
        if (name.equals("AUDIO_CLIP")) {
            File f = EditorGUI.FileReceive(allowedTypes, "Audio Clip", audioClip);
            if (f != null) {
                audioClip = f;
                source = Audio.LoadAudio(audioClip.getPath());
            }
        }
    }

    public void Play() {
        AL11.alSourcePlay(source);
    }

    public void Pause() {
        AL11.alSourcePause(source);
    }

    public void StopPlay() {
        AL11.alSourceStop(source);
    }

}
