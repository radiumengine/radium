package Engine.Gizmo;

public abstract class Gizmo {

    public abstract void Update();
    public abstract void OnDestroy();

    public void Destroy() {
        OnDestroy();
        GizmoManager.gizmos.remove(this);
    }

}
