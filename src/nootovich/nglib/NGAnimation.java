package nootovich.nglib;

public class NGAnimation {

    // position, rotation, scale, color, etc.

    public NGVec2f start;
    public NGVec2f end;
    public NGVec2f state;

    public float duration;
    public float progress = 0;

    // TODO SOMEDAY: interpolations

    public NGAnimation(int startX, int startY, int endX, int endY, float duration) {
        this(new NGVec2f(startX, startY), new NGVec2f(endX, endY), duration);
    }

    public NGAnimation(NGVec2f start, NGVec2f end, float duration) {
        this.start    = start;
        this.end      = end;
        this.state    = start;
        this.duration = duration;
    }

    public void update(float dt) {
        progress += dt;
        if (progress >= duration) state = end;
        state = start.lerp(end, progress / duration);
    }
}
