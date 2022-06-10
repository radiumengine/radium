package RadiumEditor.Profiling;

import Radium.Math.Mathf;
import org.python.modules.time.Time;

public class ProfilingTimer {

    private long begin, end;
    private long finalTime;

    public ProfilingTimer(boolean start) {
        if (start) StartSampling();
    }

    public ProfilingTimer() {
        this(true);
    }

    public void StartSampling() {
        begin = System.currentTimeMillis();
    }

    public void EndSampling() {
        end = System.currentTimeMillis();

        finalTime = end - begin;
    }

    public long GetTime() {
        return finalTime;
    }

}
