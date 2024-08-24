package nootovich.nglibhelper;

import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import nootovich.nglib.NGFileSystem;

public class NGGenerateVectors {

    private static final String CLASS_NAME = "NGVector";

    private static final char[]       DIMENSION_ALIASES = "xyzwvu".toCharArray();
    private static final Class<?>[]   CLASSES           = new Class[]{byte.class, short.class, int.class, long.class, float.class, double.class};
    private static final Class<?>[][] CLASSES_EXTRA     = new Class[][]{
        {Point.class, Point2D.class, Dimension.class, Dimension2D.class},
        { },
        {Rectangle.class, Rectangle2D.class, RectangularShape.class},
        { },
        { }
    };

    private static final HashMap<Class<?>, String[]> CLASSES_EXTRA_METHODS = new HashMap<>();

    static {
        CLASSES_EXTRA_METHODS.put(Point.class, new String[]{"getX", "getY"});
        CLASSES_EXTRA_METHODS.put(Point2D.class, new String[]{"getX", "getY"});
        CLASSES_EXTRA_METHODS.put(Dimension.class, new String[]{"getWidth", "getHeight"});
        CLASSES_EXTRA_METHODS.put(Dimension2D.class, new String[]{"getWidth", "getHeight"});
        CLASSES_EXTRA_METHODS.put(Rectangle.class, new String[]{"getX", "getY", "getWidth", "getHeight"});
        CLASSES_EXTRA_METHODS.put(Rectangle2D.class, new String[]{"getX", "getY", "getWidth", "getHeight"});
        CLASSES_EXTRA_METHODS.put(RectangularShape.class, new String[]{"getX", "getY", "getWidth", "getHeight"});
    }

    private static final int START_N_OF_DIMENSIONS = 2;
    private static final int END_N_OF_DIMENSIONS   = DIMENSION_ALIASES.length;

    private static final String OPERATION_ADD_NAME = "add";
    private static final String OPERATION_SUB_NAME = "sub";
    private static final String OPERATION_MLT_NAME = "mlt";
    private static final String OPERATION_DIV_NAME = "div";

    private static String vecName;
    private static String typeName;
    private static char   typeChar;
    private static int    n;
    private static int    ti;

    // TODO: add generic NGVec?
    public static void main(String[] args) {
        assert CLASSES_EXTRA.length == DIMENSION_ALIASES.length - 1;

        StringBuilder sb = new StringBuilder();

        for (n = 2; n <= 2; n++) {
            for (ti = 2; ti < 3; ti++) {
                // for (n = START_N_OF_DIMENSIONS; n <= END_N_OF_DIMENSIONS; n++) {
                //     for (ti = 0; ti < CLASSES.length; ti++) {
                Class<?> typeClass = CLASSES[ti];
                typeName = typeClass.getSimpleName();
                typeChar = typeName.charAt(0);
                vecName  = CLASS_NAME + n + typeChar;

                { // HEADER
                    sb.append("package nootovich.nglibtemp;\n\n");

                    for (Class<?> extraTypeClass: CLASSES_EXTRA[n - START_N_OF_DIMENSIONS]) {
                        sb.append("import %s;\n".formatted(extraTypeClass.getName()));
                    }
                    sb.append("import nootovich.nglib.NGUtils;\n\n");

                    sb.append("public class %s {\n\n".formatted(vecName));
                    sb.append("    public %s ".formatted(typeName));

                    for (int i = 0; i < n; i++) {
                        if (i > 0) sb.append(", ");
                        sb.append("%c = 0".formatted(DIMENSION_ALIASES[i]));
                    }
                    sb.append(";\n\n");
                } // HEADER
                { // CONSTRUCTORS
                    sb.append("    public %s() { }\n\n".formatted(vecName));

                    for (int oti = 0; oti < CLASSES.length; oti++) {
                        Class<?> otherTypeClass = CLASSES[oti];
                        String   otherTypeName  = otherTypeClass.getSimpleName();

                        sb.append("    public %s(%s n) {\n".formatted(vecName, otherTypeName));
                        String cast = oti > ti ? "(%s) ".formatted(typeName) : "";
                        for (int i = 0; i < n; i++) {
                            sb.append("        this.%c = %sn;\n".formatted(DIMENSION_ALIASES[i], cast));
                        }
                        sb.append("    }\n\n");

                        sb.append("    public %s(".formatted(vecName));
                        for (int i = 0; i < n; i++) {
                            if (i > 0) sb.append(", ");
                            sb.append("%s %c".formatted(otherTypeName, DIMENSION_ALIASES[i]));
                        }
                        sb.append(") {\n");
                        for (int i = 0; i < n; i++) {
                            sb.append("        this.%c = %s%1$c;\n".formatted(DIMENSION_ALIASES[i], cast));
                        }
                        sb.append("    }\n\n");

                        sb.append("    public %s(%s[] vals) {\n".formatted(vecName, otherTypeName));
                        sb.append("        if (vals.length != %d) NGUtils.error(\"Invalid amount of arguments. Expected %1$d but got \" + vals.length);\n".formatted(n));
                        for (int i = 0; i < n; i++) {
                            sb.append("        this.%c = %svals[%d];\n".formatted(DIMENSION_ALIASES[i], cast, i));
                        }
                        sb.append("    }\n\n");
                    }

                    for (Class<?> extraTypeClass: CLASSES_EXTRA[n - START_N_OF_DIMENSIONS]) {
                        String extraTypeName = extraTypeClass.getSimpleName();
                        char   extraTypeChar = extraTypeName.toLowerCase().charAt(0);
                        sb.append("    public %s(%s %c) {\n".formatted(vecName, extraTypeName, extraTypeChar));
                        sb.append("        this(");
                        for (int i = 0; i < CLASSES_EXTRA_METHODS.get(extraTypeClass).length; i++) {
                            String extraTypeMethod = CLASSES_EXTRA_METHODS.get(extraTypeClass)[i];
                            if (i > 0) sb.append(", ");
                            sb.append("%c.%s()".formatted(extraTypeChar, extraTypeMethod));
                        }
                        sb.append(");\n");
                        sb.append("    }\n\n");
                    }

                    sb.append("public static %s[] createArray(%s[][] positions) {\n".formatted(vecName, typeName));
                    sb.append("    %s[] result = new %1$s[positions.length];\n".formatted(vecName));
                    sb.append("    for (int i = 0; i < positions.length; i++) {\n");
                    // sb.append("        %s[] pos = positions[i];\n".formatted(vecName));
                    sb.append("        result[i] = new %s(positions[i]);\n".formatted(vecName));
                    sb.append("    }\n");
                    sb.append("    return result;\n");
                    sb.append("}\n\n");
                } // CONSTRUCTORS
                { // OPERATIONS
                    addOperation(sb, OPERATION_ADD_NAME, '+');
                    addOperation(sb, OPERATION_SUB_NAME, '-');
                    addOperation(sb, OPERATION_MLT_NAME, '*');
                    addOperation(sb, OPERATION_DIV_NAME, '/');
                    // TODO: neg (+ for each dir)
                    // TODO: snap
                    sb.append("    public %s lerp(%1$s other, float n) {\n".formatted(vecName));
                    sb.append("        return this.%s(other.%s(this).%s(n));\n".formatted(OPERATION_ADD_NAME, OPERATION_SUB_NAME, OPERATION_MLT_NAME));
                    sb.append("    }\n\n");
                } // OPERATIONS
                { // CONVERSIONS
                    for (int oti = 0; oti < CLASSES.length; oti++) {
                        if (oti == ti) continue;
                        String typeName       = CLASSES[oti].getSimpleName();
                        String methodTypeName = typeName.toUpperCase().charAt(0) + typeName.toLowerCase().substring(1);
                        String vecTypeName    = CLASS_NAME + n + typeName.toLowerCase().charAt(0);
                        sb.append("    public %s to%s() {\n".formatted(vecTypeName, methodTypeName));
                        sb.append("        return new %s(".formatted(vecTypeName));
                        for (int i = 0; i < n; i++) {
                            if (i > 0) sb.append(", ");
                            sb.append(DIMENSION_ALIASES[i]);
                        }
                        sb.append(");\n");
                        sb.append("    }\n\n");
                    }
                } // CONVERSIONS
                { // FOOTER
                    // TODO: `.equals()` is wrong
                    for (int oti = 0; oti < CLASSES.length; oti++) {
                        String cast = oti > ti ? "(%s) ".formatted(typeName) : "";
                        sb.append("    public boolean equals(%s other) {\n".formatted(CLASS_NAME + n + CLASSES[oti].getSimpleName().toLowerCase().charAt(0)));
                        sb.append("        return this.%c == %sother.%1$c &&\n".formatted(DIMENSION_ALIASES[0], cast));
                        for (int j = 1; j < n; j++) {
                            sb.append("               this.%c == %sother.%1$c".formatted(DIMENSION_ALIASES[j], cast));
                            if (j < n - 1) sb.append(" &&\n");
                        }
                        sb.append(";\n");
                        sb.append("    }\n\n");
                    }

                    sb.append("    @Override\n");
                    sb.append("    public String toString() {\n");
                    sb.append("        return \"(");
                    for (int i = 0; i < n; i++) {
                        if (i > 0) sb.append(", ");
                        sb.append("%%%s".formatted(ti < 4 ? "d" : "f"));
                    }
                    sb.append(")\".formatted(");
                    for (int i = 0; i < n; i++) {
                        if (i > 0) sb.append(", ");
                        sb.append("%c".formatted(DIMENSION_ALIASES[i]));
                    }
                    sb.append(");\n");
                    sb.append("    }\n");
                    sb.append("}\n");
                } // FOOTER
                NGFileSystem.saveFile("./src/nootovich/nglibtemp/%s.java".formatted(vecName), sb.toString());
                sb.setLength(0);
            }
        }
    }

    private static void addOperation(StringBuilder sb, String operationName, char operation) {
        // SCALAR
        for (int oti = 0; oti < CLASSES.length; oti++) {
            String otherTypeName = CLASSES[oti].getSimpleName();
            sb.append("    public %s %s(%s n) {\n".formatted(vecName, operationName, otherTypeName));
            sb.append("        return new %s(".formatted(vecName));
            String cast = oti > ti ? "(%s) (".formatted(typeName) : "";
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                sb.append("%s%c %c n%s".formatted(cast, DIMENSION_ALIASES[i], operation, oti > ti ? ")" : ""));
            }
            sb.append(");\n");
            sb.append("    }\n\n");
        }

        // INDIVIDUAL VARS
        for (int oti = 0; oti < CLASSES.length; oti++) {
            String otherTypeName = CLASSES[oti].getSimpleName();
            sb.append("    public %s %s(".formatted(vecName, operationName));
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                sb.append("%s d%c".formatted(otherTypeName, DIMENSION_ALIASES[i]));
            }
            sb.append(") {\n");
            sb.append("        return new %s(".formatted(vecName));
            String cast = oti > ti ? "(%s) (".formatted(typeName) : "";
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                sb.append("%s%c %c d%2$c%s".formatted(cast, DIMENSION_ALIASES[i], operation, oti > ti ? ")" : ""));
            }
            sb.append(");\n");
            sb.append("    }\n\n");
        }

        // VECTORS
        for (int oti = 0; oti < CLASSES.length; oti++) {
            String vecTypeName = CLASS_NAME + n + CLASSES[oti].getSimpleName().toLowerCase().charAt(0);
            sb.append("    public %s %s(%s other) {\n".formatted(vecName, operationName, vecTypeName));
            sb.append("        return new %s(".formatted(vecName));
            String cast = oti > ti ? "(%s) (".formatted(typeName) : "";
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(", ");
                sb.append("%s%c %c other.%2$c%s".formatted(cast, DIMENSION_ALIASES[i], operation, oti > ti ? ")" : ""));
            }
            sb.append(");\n");
            sb.append("    }\n\n");
        }
    }
}
