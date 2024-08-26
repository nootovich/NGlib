package nootovich.nglib;

public class NGAnimation {

    // position, rotation, scale, color, etc.
    public NGAnimationType type;

    public NGVec2f start;
    public NGVec2f end;
    public NGVec2f cur;

    public boolean active = true;

    public float duration;
    public float progress = 0;

    public enum NGAnimationType {POS, SIZE}

    // TODO SOMEDAY: interpolations

    public NGAnimation(NGAnimationType type, NGVec2f start, NGVec2f end, float duration) {
        this.type     = type;
        this.start    = start;
        this.end      = end;
        this.cur      = start;
        this.duration = duration;
    }

    public void update(float dt) {
        if (!active) return;
        progress += dt;
        if (progress >= duration) {
            cur = end;
            active = false;
        }
        cur = start.lerp(end, progress / duration);
    }
}
