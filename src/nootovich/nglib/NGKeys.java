package nootovich.nglib;

import java.awt.event.KeyEvent;
import java.util.Map;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public interface NGKeys {
    /* TODO:
     *  4  Arrows
     *  2  Ctrl
     *  2  Shift
     *  2  Alt
     *  1? Meta
     *  1  Insert
     *  1  Delete
     *  1  Home
     *  1  End
     *  1  PageUp
     *  1  PageDn
     *  1  PrintScreen
     *  1  PauseBreak
     *  1  NumLock
     *  16 Num-keys
     *  1  CapsLock
     *  1  ScrollLock
     *  24 F-keys
     *  total :62 */

    // 0-7 vacant
    int BACKSPACE = 8;
    int TAB       = 9;
    int ENTER = 10;
    // 11-13 vacant
    int LSHIFT = 14;
    int RSHIFT = 15;
    // 16-26 vacant
    int ESCAPE = 27;
    // 28-31 vacant
    int SPACEBAR = 32;
    // 33 -> 49
    // 34 -> 39
    // 35 -> 51
    // 36 -> 52
    // 37 -> 53
    // 38 -> 55
    int QUOTE    = 39;
    // 40 -> 57
    // 41 -> 48
    // 42 -> 56
    // 43 -> 61
    int COMMA = 44;
    int MINUS = 45;
    int PERIOD = 46;
    int SLASH  = 47;
    int _0    = 48;
    int _1 = 49;
    int _2 = 50;
    int _3 = 51;
    int _4 = 52;
    int _5 = 53;
    int _6 = 54;
    int _7 = 55;
    int _8 = 56;
    int _9 = 57;
    // 58 -> 59
    int SEMICOLON = 59;
    // 60 -> 44
    int EQUALS    = 61;
    // 62 -> 46
    // 63 -> 47
    // 64 -> 50
    int A      = 65;
    int B = 66;
    int C = 67;
    int D = 68;
    int E = 69;
    int F = 70;
    int G = 71;
    int H = 72;
    int I = 73;
    int J = 74;
    int K = 75;
    int L = 76;
    int M = 77;
    int N = 78;
    int O = 79;
    int P = 80;
    int Q = 81;
    int R = 82;
    int S = 83;
    int T = 84;
    int U = 85;
    int V = 86;
    int W = 87;
    int X = 88;
    int Y = 89;
    int Z = 90;
    int LBRACKET = 91;
    int BACKSLASH = 92;
    int RBRACKET  = 93;
    // 94 -> 54
    // 95 -> 45
    int BACKTICK = 96;
    // 97-125 -> 65-93
    // 126 -> 96

    Map<Integer, Integer> conversion = Map.of(
            KeyEvent.VK_ENTER, ENTER,       // 10
            KeyEvent.VK_SPACE, SPACEBAR,    // 32
            KeyEvent.VK_A, A,               // 65
            KeyEvent.VK_B, B,               // 66
            KeyEvent.VK_D, D,               // 68
            KeyEvent.VK_S, S,               // 83
            KeyEvent.VK_W, W                // 87
    );

}
