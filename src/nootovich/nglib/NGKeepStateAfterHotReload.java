package nootovich.nglib;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NGKeepStateAfterHotReload {
}
