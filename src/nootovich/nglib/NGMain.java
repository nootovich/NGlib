package nootovich.nglib;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class NGMain implements AWTEventListener {

    private static final int WINDOW_MINIMAL_SIZE = 100;

    public static int w;
    public static int h;

    public static float TICK_DURATION  = 0.0333f; // Measured in seconds
    public static float FRAME_DURATION = 0.0167f; // Measured in seconds

    public static NGWindow window;

    public static int tickCount = 0;

    public static Stack<String> heldKeys = new Stack<>();

    public void setTickRate(int ups) {
        TICK_DURATION = 1.0f / ups;
    }

    public void setFrameRate(int fps) {
        FRAME_DURATION = 1.0f / fps;
    }

    public void createWindow(int w, int h, NGRenderer renderer) {
        this.w = w;
        this.h = h;
        window = new NGWindow(w, h, renderer, this);
    }

    public void start() {
        NGUtils.info("tickrate: " + 1 / TICK_DURATION);
        NGUtils.info("framerate: " + 1 / FRAME_DURATION);
        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){updateAll();}},0,(long)(TICK_DURATION*1000));
        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){window.redraw();}},0,(long)(FRAME_DURATION*1000));
    }

    public void exit() {
        window.shouldClose = true;
    }

    public void exit(float waitTime) {
        new Timer().scheduleAtFixedRate(new TimerTask(){public void run(){window.shouldClose = true;}},0,(long)(waitTime*1000));
    }

    public void updateAll() {
        updateHeldKeys();
        update();
        tickCount++;
    }

    public void update() { }

    public void updateHeldKeys() {
        for (String heldKey: heldKeys) {
            try {
                if (heldKey.equals("LMB") || heldKey.equals("RMB") || heldKey.equals("MMB"))
                    getClass().getDeclaredMethod("while" + heldKey + "Held", NGVec2i.class)
                              .invoke(this, new NGVec2i(MouseInfo.getPointerInfo().getLocation()).sub(window.pos));
                else getClass().getDeclaredMethod("while" + heldKey + "Held").invoke(this);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) { }
        }
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        int id = event.getID();
        if (id == KeyEvent.KEY_PRESSED) {
            int  keyCode = ((KeyEvent) event).getKeyCode();
            char keyChar = ((KeyEvent) event).getKeyChar();
            onAnyKeyPress(keyCode, keyChar);
            switch (keyCode) {
                case 10: {
                    heldKeys.push("Enter");
                    onEnterPress();
                    break;
                }
                case 12: {
                    heldKeys.push("Clear");
                    onClearPress();
                    break;
                }
                case 16: {
                    heldKeys.push("Shift");
                    onShiftPress();
                    break;
                }
                case 17: {
                    heldKeys.push("Ctrl");
                    onCtrlPress();
                    break;
                }
                case 18: {
                    heldKeys.push("Alt");
                    onAltPress();
                    break;
                }
                case 19: {
                    heldKeys.push("Pause");
                    onPausePress();
                    break;
                }
                case 20: {
                    heldKeys.push("CapsLock");
                    onCapsLockPress();
                    break;
                }
                case 21: {
                    heldKeys.push("Kana");
                    onKanaPress();
                    break;
                }
                case 24: {
                    heldKeys.push("Final");
                    onFinalPress();
                    break;
                }
                case 25: {
                    heldKeys.push("Kanji");
                    onKanjiPress();
                    break;
                }
                case 27: {
                    heldKeys.push("Escape");
                    onEscapePress();
                    break;
                }
                case 28: {
                    heldKeys.push("Convert");
                    onConvertPress();
                    break;
                }
                case 29: {
                    heldKeys.push("NoConvert");
                    onNoConvertPress();
                    break;
                }
                case 30: {
                    heldKeys.push("Accept");
                    onAcceptPress();
                    break;
                }
                case 31: {
                    heldKeys.push("ModeChange");
                    onModeChangePress();
                    break;
                }
                case 32: {
                    heldKeys.push("Space");
                    onSpacePress();
                    break;
                }
                case 33: {
                    heldKeys.push("PageUp");
                    onPageUpPress();
                    break;
                }
                case 34: {
                    heldKeys.push("PageDown");
                    onPageDownPress();
                    break;
                }
                case 35: {
                    heldKeys.push("End");
                    onEndPress();
                    break;
                }
                case 36: {
                    heldKeys.push("Home");
                    onHomePress();
                    break;
                }
                case 37: {
                    heldKeys.push("Left");
                    onLeftPress();
                    break;
                }
                case 38: {
                    heldKeys.push("Up");
                    onUpPress();
                    break;
                }
                case 39: {
                    heldKeys.push("Right");
                    onRightPress();
                    break;
                }
                case 40: {
                    heldKeys.push("Down");
                    onDownPress();
                    break;
                }
                case 44: {
                    heldKeys.push("Comma");
                    onCommaPress();
                    break;
                }
                case 45: {
                    heldKeys.push("Minus");
                    onMinusPress();
                    break;
                }
                case 46: {
                    heldKeys.push("Period");
                    onPeriodPress();
                    break;
                }
                case 47: {
                    heldKeys.push("Slash");
                    onSlashPress();
                    break;
                }
                case 48: {
                    heldKeys.push("0");
                    on0Press();
                    break;
                }
                case 49: {
                    heldKeys.push("1");
                    on1Press();
                    break;
                }
                case 50: {
                    heldKeys.push("2");
                    on2Press();
                    break;
                }
                case 51: {
                    heldKeys.push("3");
                    on3Press();
                    break;
                }
                case 52: {
                    heldKeys.push("4");
                    on4Press();
                    break;
                }
                case 53: {
                    heldKeys.push("5");
                    on5Press();
                    break;
                }
                case 54: {
                    heldKeys.push("6");
                    on6Press();
                    break;
                }
                case 55: {
                    heldKeys.push("7");
                    on7Press();
                    break;
                }
                case 56: {
                    heldKeys.push("8");
                    on8Press();
                    break;
                }
                case 57: {
                    heldKeys.push("9");
                    on9Press();
                    break;
                }
                case 59: {
                    heldKeys.push("Semicolon");
                    onSemicolonPress();
                    break;
                }
                case 61: {
                    heldKeys.push("Equals");
                    onEqualsPress();
                    break;
                }
                case 65: {
                    heldKeys.push("A");
                    onAPress();
                    break;
                }
                case 66: {
                    heldKeys.push("B");
                    onBPress();
                    break;
                }
                case 67: {
                    heldKeys.push("C");
                    onCPress();
                    break;
                }
                case 68: {
                    heldKeys.push("D");
                    onDPress();
                    break;
                }
                case 69: {
                    heldKeys.push("E");
                    onEPress();
                    break;
                }
                case 70: {
                    heldKeys.push("F");
                    onFPress();
                    break;
                }
                case 71: {
                    heldKeys.push("G");
                    onGPress();
                    break;
                }
                case 72: {
                    heldKeys.push("H");
                    onHPress();
                    break;
                }
                case 73: {
                    heldKeys.push("I");
                    onIPress();
                    break;
                }
                case 74: {
                    heldKeys.push("J");
                    onJPress();
                    break;
                }
                case 75: {
                    heldKeys.push("K");
                    onKPress();
                    break;
                }
                case 76: {
                    heldKeys.push("L");
                    onLPress();
                    break;
                }
                case 77: {
                    heldKeys.push("M");
                    onMPress();
                    break;
                }
                case 78: {
                    heldKeys.push("N");
                    onNPress();
                    break;
                }
                case 79: {
                    heldKeys.push("O");
                    onOPress();
                    break;
                }
                case 80: {
                    heldKeys.push("P");
                    onPPress();
                    break;
                }
                case 81: {
                    heldKeys.push("Q");
                    onQPress();
                    break;
                }
                case 82: {
                    heldKeys.push("R");
                    onRPress();
                    break;
                }
                case 83: {
                    heldKeys.push("S");
                    onSPress();
                    break;
                }
                case 84: {
                    heldKeys.push("T");
                    onTPress();
                    break;
                }
                case 85: {
                    heldKeys.push("U");
                    onUPress();
                    break;
                }
                case 86: {
                    heldKeys.push("V");
                    onVPress();
                    break;
                }
                case 87: {
                    heldKeys.push("W");
                    onWPress();
                    break;
                }
                case 88: {
                    heldKeys.push("X");
                    onXPress();
                    break;
                }
                case 89: {
                    heldKeys.push("Y");
                    onYPress();
                    break;
                }
                case 90: {
                    heldKeys.push("Z");
                    onZPress();
                    break;
                }
                case 91: {
                    heldKeys.push("OpenBracket");
                    onOpenBracketPress();
                    break;
                }
                case 92: {
                    heldKeys.push("BackSlash");
                    onBackSlashPress();
                    break;
                }
                case 93: {
                    heldKeys.push("CloseBracket");
                    onCloseBracketPress();
                    break;
                }
                case 96: {
                    heldKeys.push("NumPad0");
                    onNumPad0Press();
                    break;
                }
                case 97: {
                    heldKeys.push("NumPad1");
                    onNumPad1Press();
                    break;
                }
                case 98: {
                    heldKeys.push("NumPad2");
                    onNumPad2Press();
                    break;
                }
                case 99: {
                    heldKeys.push("NumPad3");
                    onNumPad3Press();
                    break;
                }
                case 100: {
                    heldKeys.push("NumPad4");
                    onNumPad4Press();
                    break;
                }
                case 101: {
                    heldKeys.push("NumPad5");
                    onNumPad5Press();
                    break;
                }
                case 102: {
                    heldKeys.push("NumPad6");
                    onNumPad6Press();
                    break;
                }
                case 103: {
                    heldKeys.push("NumPad7");
                    onNumPad7Press();
                    break;
                }
                case 104: {
                    heldKeys.push("NumPad8");
                    onNumPad8Press();
                    break;
                }
                case 105: {
                    heldKeys.push("NumPad9");
                    onNumPad9Press();
                    break;
                }
                case 106: {
                    heldKeys.push("NumPadAsterisk");
                    onNumPadAsteriskPress();
                    break;
                }
                case 107: {
                    heldKeys.push("NumPadPlus");
                    onNumPadPlusPress();
                    break;
                }
                case 108: {
                    heldKeys.push("NumPadComma");
                    onNumPadCommaPress();
                    break;
                }
                case 109: {
                    heldKeys.push("NumPad");
                    onNumPadPress();
                    break;
                }
                case 110: {
                    heldKeys.push("NumPadPeriod");
                    onNumPadPeriodPress();
                    break;
                }
                case 111: {
                    heldKeys.push("NumPadSlash");
                    onNumPadSlashPress();
                    break;
                }
                case 112: {
                    heldKeys.push("F1");
                    onF1Press();
                    break;
                }
                case 113: {
                    heldKeys.push("F2");
                    onF2Press();
                    break;
                }
                case 114: {
                    heldKeys.push("F3");
                    onF3Press();
                    break;
                }
                case 115: {
                    heldKeys.push("F4");
                    onF4Press();
                    break;
                }
                case 116: {
                    heldKeys.push("F5");
                    onF5Press();
                    break;
                }
                case 117: {
                    heldKeys.push("F6");
                    onF6Press();
                    break;
                }
                case 118: {
                    heldKeys.push("F7");
                    onF7Press();
                    break;
                }
                case 119: {
                    heldKeys.push("F8");
                    onF8Press();
                    break;
                }
                case 120: {
                    heldKeys.push("F9");
                    onF9Press();
                    break;
                }
                case 121: {
                    heldKeys.push("F10");
                    onF10Press();
                    break;
                }
                case 122: {
                    heldKeys.push("F11");
                    onF11Press();
                    break;
                }
                case 123: {
                    heldKeys.push("F12");
                    onF12Press();
                    break;
                }
                case 127: {
                    heldKeys.push("Delete");
                    onDeletePress();
                    break;
                }
                case 128: {
                    heldKeys.push("DeadGrave");
                    onDeadGravePress();
                    break;
                }
                case 129: {
                    heldKeys.push("DeadAcute");
                    onDeadAcutePress();
                    break;
                }
                case 130: {
                    heldKeys.push("DeadCircumflex");
                    onDeadCircumflexPress();
                    break;
                }
                case 131: {
                    heldKeys.push("DeadTilde");
                    onDeadTildePress();
                    break;
                }
                case 132: {
                    heldKeys.push("DeadMacron");
                    onDeadMacronPress();
                    break;
                }
                case 133: {
                    heldKeys.push("DeadBreve");
                    onDeadBrevePress();
                    break;
                }
                case 134: {
                    heldKeys.push("DeadAboveDot");
                    onDeadAboveDotPress();
                    break;
                }
                case 135: {
                    heldKeys.push("DeadDiaeresis");
                    onDeadDiaeresisPress();
                    break;
                }
                case 136: {
                    heldKeys.push("DeadAboveRing");
                    onDeadAboveRingPress();
                    break;
                }
                case 137: {
                    heldKeys.push("DeadDoubleAcute");
                    onDeadDoubleAcutePress();
                    break;
                }
                case 138: {
                    heldKeys.push("DeadCaron");
                    onDeadCaronPress();
                    break;
                }
                case 139: {
                    heldKeys.push("DeadCedilla");
                    onDeadCedillaPress();
                    break;
                }
                case 140: {
                    heldKeys.push("DeadOgonek");
                    onDeadOgonekPress();
                    break;
                }
                case 141: {
                    heldKeys.push("DeadIota");
                    onDeadIotaPress();
                    break;
                }
                case 142: {
                    heldKeys.push("DeadVoicedSound");
                    onDeadVoicedSoundPress();
                    break;
                }
                case 143: {
                    heldKeys.push("DeadSemivoicedSound");
                    onDeadSemivoicedSoundPress();
                    break;
                }
                case 144: {
                    heldKeys.push("NumLock");
                    onNumLockPress();
                    break;
                }
                case 145: {
                    heldKeys.push("ScrollLock");
                    onScrollLockPress();
                    break;
                }
                case 150: {
                    heldKeys.push("Ampersand");
                    onAmpersandPress();
                    break;
                }
                case 151: {
                    heldKeys.push("Asterisk");
                    onAsteriskPress();
                    break;
                }
                case 152: {
                    heldKeys.push("DoubleQuote");
                    onDoubleQuotePress();
                    break;
                }
                case 153: {
                    heldKeys.push("Less");
                    onLessPress();
                    break;
                }
                case 154: {
                    heldKeys.push("PrintScreen");
                    onPrintScreenPress();
                    break;
                }
                case 155: {
                    heldKeys.push("Insert");
                    onInsertPress();
                    break;
                }
                case 156: {
                    heldKeys.push("Help");
                    onHelpPress();
                    break;
                }
                case 157: {
                    heldKeys.push("Meta");
                    onMetaPress();
                    break;
                }
                case 160: {
                    heldKeys.push("Greater");
                    onGreaterPress();
                    break;
                }
                case 161: {
                    heldKeys.push("LeftBrace");
                    onLeftBracePress();
                    break;
                }
                case 162: {
                    heldKeys.push("RightBrace");
                    onRightBracePress();
                    break;
                }
                case 192: {
                    heldKeys.push("BackQuote");
                    onBackQuotePress();
                    break;
                }
                case 222: {
                    heldKeys.push("Quote");
                    onQuotePress();
                    break;
                }
                case 240: {
                    heldKeys.push("Alphanumeric");
                    onAlphanumericPress();
                    break;
                }
                case 241: {
                    heldKeys.push("Katakana");
                    onKatakanaPress();
                    break;
                }
                case 242: {
                    heldKeys.push("Hiragana");
                    onHiraganaPress();
                    break;
                }
                case 243: {
                    heldKeys.push("FullWidth");
                    onFullWidthPress();
                    break;
                }
                case 244: {
                    heldKeys.push("HalfWidth");
                    onHalfWidthPress();
                    break;
                }
                case 245: {
                    heldKeys.push("RomanCharacters");
                    onRomanCharactersPress();
                    break;
                }
                case 256: {
                    heldKeys.push("AllCandidates");
                    onAllCandidatesPress();
                    break;
                }
                case 257: {
                    heldKeys.push("PreviousCandidate");
                    onPreviousCandidatePress();
                    break;
                }
                case 258: {
                    heldKeys.push("CodeInput");
                    onCodeInputPress();
                    break;
                }
                case 259: {
                    heldKeys.push("JapaneseKatakana");
                    onJapaneseKatakanaPress();
                    break;
                }
                case 260: {
                    heldKeys.push("JapaneseHiragana");
                    onJapaneseHiraganaPress();
                    break;
                }
                case 261: {
                    heldKeys.push("JapaneseRoman");
                    onJapaneseRomanPress();
                    break;
                }
                case 262: {
                    heldKeys.push("KanaLock");
                    onKanaLockPress();
                    break;
                }
                case 263: {
                    heldKeys.push("InputMethodOnOff");
                    onInputMethodOnOffPress();
                    break;
                }
                case 512: {
                    heldKeys.push("At");
                    onAtPress();
                    break;
                }
                case 513: {
                    heldKeys.push("Colon");
                    onColonPress();
                    break;
                }
                case 514: {
                    heldKeys.push("Circumflex");
                    onCircumflexPress();
                    break;
                }
                case 515: {
                    heldKeys.push("Dollar");
                    onDollarPress();
                    break;
                }
                case 516: {
                    heldKeys.push("Euro");
                    onEuroPress();
                    break;
                }
                case 517: {
                    heldKeys.push("ExclamationMark");
                    onExclamationMarkPress();
                    break;
                }
                case 518: {
                    heldKeys.push("InvertedExclamationMark");
                    onInvertedExclamationMarkPress();
                    break;
                }
                case 519: {
                    heldKeys.push("LeftParenthesis");
                    onLeftParenthesisPress();
                    break;
                }
                case 520: {
                    heldKeys.push("NumberSign");
                    onNumberSignPress();
                    break;
                }
                case 521: {
                    heldKeys.push("Plus");
                    onPlusPress();
                    break;
                }
                case 522: {
                    heldKeys.push("RightParenthesis");
                    onRightParenthesisPress();
                    break;
                }
                case 523: {
                    heldKeys.push("Underscore");
                    onUnderscorePress();
                    break;
                }
                case 524: {
                    heldKeys.push("Windows");
                    onWindowsPress();
                    break;
                }
                case 525: {
                    heldKeys.push("ContextMenu");
                    onContextMenuPress();
                    break;
                }
                case 61440: {
                    heldKeys.push("F13");
                    onF13Press();
                    break;
                }
                case 61441: {
                    heldKeys.push("F14");
                    onF14Press();
                    break;
                }
                case 61442: {
                    heldKeys.push("F15");
                    onF15Press();
                    break;
                }
                case 61443: {
                    heldKeys.push("F16");
                    onF16Press();
                    break;
                }
                case 61444: {
                    heldKeys.push("F17");
                    onF17Press();
                    break;
                }
                case 61445: {
                    heldKeys.push("F18");
                    onF18Press();
                    break;
                }
                case 61446: {
                    heldKeys.push("F19");
                    onF19Press();
                    break;
                }
                case 61447: {
                    heldKeys.push("F20");
                    onF20Press();
                    break;
                }
                case 61448: {
                    heldKeys.push("F21");
                    onF21Press();
                    break;
                }
                case 61449: {
                    heldKeys.push("F22");
                    onF22Press();
                    break;
                }
                case 61450: {
                    heldKeys.push("F23");
                    onF23Press();
                    break;
                }
                case 61451: {
                    heldKeys.push("F24");
                    onF24Press();
                    break;
                }
                case 65312: {
                    heldKeys.push("Compose");
                    onComposePress();
                    break;
                }
            }
            afterAnyKeyPress(keyCode, keyChar);
        } else if (id == KeyEvent.KEY_RELEASED) {
            int keyCode  = ((KeyEvent) event).getKeyCode();
            char keyChar = ((KeyEvent) event).getKeyChar();
            onAnyKeyRelease(keyCode, keyChar);
            switch (keyCode) {
                case 10: {
                    heldKeys.remove("Enter");
                    onEnterRelease();
                    break;
                }
                case 12: {
                    heldKeys.remove("Clear");
                    onClearRelease();
                    break;
                }
                case 16: {
                    heldKeys.remove("Shift");
                    onShiftRelease();
                    break;
                }
                case 17: {
                    heldKeys.remove("Ctrl");
                    onCtrlRelease();
                    break;
                }
                case 18: {
                    heldKeys.remove("Alt");
                    onAltRelease();
                    break;
                }
                case 19: {
                    heldKeys.remove("Pause");
                    onPauseRelease();
                    break;
                }
                case 20: {
                    heldKeys.remove("CapsLock");
                    onCapsLockRelease();
                    break;
                }
                case 21: {
                    heldKeys.remove("Kana");
                    onKanaRelease();
                    break;
                }
                case 24: {
                    heldKeys.remove("Final");
                    onFinalRelease();
                    break;
                }
                case 25: {
                    heldKeys.remove("Kanji");
                    onKanjiRelease();
                    break;
                }
                case 27: {
                    heldKeys.remove("Escape");
                    onEscapeRelease();
                    break;
                }
                case 28: {
                    heldKeys.remove("Convert");
                    onConvertRelease();
                    break;
                }
                case 29: {
                    heldKeys.remove("NoConvert");
                    onNoConvertRelease();
                    break;
                }
                case 30: {
                    heldKeys.remove("Accept");
                    onAcceptRelease();
                    break;
                }
                case 31: {
                    heldKeys.remove("ModeChange");
                    onModeChangeRelease();
                    break;
                }
                case 32: {
                    heldKeys.remove("Space");
                    onSpaceRelease();
                    break;
                }
                case 33: {
                    heldKeys.remove("PageUp");
                    onPageUpRelease();
                    break;
                }
                case 34: {
                    heldKeys.remove("PageDown");
                    onPageDownRelease();
                    break;
                }
                case 35: {
                    heldKeys.remove("End");
                    onEndRelease();
                    break;
                }
                case 36: {
                    heldKeys.remove("Home");
                    onHomeRelease();
                    break;
                }
                case 37: {
                    heldKeys.remove("Left");
                    onLeftRelease();
                    break;
                }
                case 38: {
                    heldKeys.remove("Up");
                    onUpRelease();
                    break;
                }
                case 39: {
                    heldKeys.remove("Right");
                    onRightRelease();
                    break;
                }
                case 40: {
                    heldKeys.remove("Down");
                    onDownRelease();
                    break;
                }
                case 44: {
                    heldKeys.remove("Comma");
                    onCommaRelease();
                    break;
                }
                case 45: {
                    heldKeys.remove("Minus");
                    onMinusRelease();
                    break;
                }
                case 46: {
                    heldKeys.remove("Period");
                    onPeriodRelease();
                    break;
                }
                case 47: {
                    heldKeys.remove("Slash");
                    onSlashRelease();
                    break;
                }
                case 48: {
                    heldKeys.remove("0");
                    on0Release();
                    break;
                }
                case 49: {
                    heldKeys.remove("1");
                    on1Release();
                    break;
                }
                case 50: {
                    heldKeys.remove("2");
                    on2Release();
                    break;
                }
                case 51: {
                    heldKeys.remove("3");
                    on3Release();
                    break;
                }
                case 52: {
                    heldKeys.remove("4");
                    on4Release();
                    break;
                }
                case 53: {
                    heldKeys.remove("5");
                    on5Release();
                    break;
                }
                case 54: {
                    heldKeys.remove("6");
                    on6Release();
                    break;
                }
                case 55: {
                    heldKeys.remove("7");
                    on7Release();
                    break;
                }
                case 56: {
                    heldKeys.remove("8");
                    on8Release();
                    break;
                }
                case 57: {
                    heldKeys.remove("9");
                    on9Release();
                    break;
                }
                case 59: {
                    heldKeys.remove("Semicolon");
                    onSemicolonRelease();
                    break;
                }
                case 61: {
                    heldKeys.remove("Equals");
                    onEqualsRelease();
                    break;
                }
                case 65: {
                    heldKeys.remove("A");
                    onARelease();
                    break;
                }
                case 66: {
                    heldKeys.remove("B");
                    onBRelease();
                    break;
                }
                case 67: {
                    heldKeys.remove("C");
                    onCRelease();
                    break;
                }
                case 68: {
                    heldKeys.remove("D");
                    onDRelease();
                    break;
                }
                case 69: {
                    heldKeys.remove("E");
                    onERelease();
                    break;
                }
                case 70: {
                    heldKeys.remove("F");
                    onFRelease();
                    break;
                }
                case 71: {
                    heldKeys.remove("G");
                    onGRelease();
                    break;
                }
                case 72: {
                    heldKeys.remove("H");
                    onHRelease();
                    break;
                }
                case 73: {
                    heldKeys.remove("I");
                    onIRelease();
                    break;
                }
                case 74: {
                    heldKeys.remove("J");
                    onJRelease();
                    break;
                }
                case 75: {
                    heldKeys.remove("K");
                    onKRelease();
                    break;
                }
                case 76: {
                    heldKeys.remove("L");
                    onLRelease();
                    break;
                }
                case 77: {
                    heldKeys.remove("M");
                    onMRelease();
                    break;
                }
                case 78: {
                    heldKeys.remove("N");
                    onNRelease();
                    break;
                }
                case 79: {
                    heldKeys.remove("O");
                    onORelease();
                    break;
                }
                case 80: {
                    heldKeys.remove("P");
                    onPRelease();
                    break;
                }
                case 81: {
                    heldKeys.remove("Q");
                    onQRelease();
                    break;
                }
                case 82: {
                    heldKeys.remove("R");
                    onRRelease();
                    break;
                }
                case 83: {
                    heldKeys.remove("S");
                    onSRelease();
                    break;
                }
                case 84: {
                    heldKeys.remove("T");
                    onTRelease();
                    break;
                }
                case 85: {
                    heldKeys.remove("U");
                    onURelease();
                    break;
                }
                case 86: {
                    heldKeys.remove("V");
                    onVRelease();
                    break;
                }
                case 87: {
                    heldKeys.remove("W");
                    onWRelease();
                    break;
                }
                case 88: {
                    heldKeys.remove("X");
                    onXRelease();
                    break;
                }
                case 89: {
                    heldKeys.remove("Y");
                    onYRelease();
                    break;
                }
                case 90: {
                    heldKeys.remove("Z");
                    onZRelease();
                    break;
                }
                case 91: {
                    heldKeys.remove("OpenBracket");
                    onOpenBracketRelease();
                    break;
                }
                case 92: {
                    heldKeys.remove("BackSlash");
                    onBackSlashRelease();
                    break;
                }
                case 93: {
                    heldKeys.remove("CloseBracket");
                    onCloseBracketRelease();
                    break;
                }
                case 96: {
                    heldKeys.remove("NumPad0");
                    onNumPad0Release();
                    break;
                }
                case 97: {
                    heldKeys.remove("NumPad1");
                    onNumPad1Release();
                    break;
                }
                case 98: {
                    heldKeys.remove("NumPad2");
                    onNumPad2Release();
                    break;
                }
                case 99: {
                    heldKeys.remove("NumPad3");
                    onNumPad3Release();
                    break;
                }
                case 100: {
                    heldKeys.remove("NumPad4");
                    onNumPad4Release();
                    break;
                }
                case 101: {
                    heldKeys.remove("NumPad5");
                    onNumPad5Release();
                    break;
                }
                case 102: {
                    heldKeys.remove("NumPad6");
                    onNumPad6Release();
                    break;
                }
                case 103: {
                    heldKeys.remove("NumPad7");
                    onNumPad7Release();
                    break;
                }
                case 104: {
                    heldKeys.remove("NumPad8");
                    onNumPad8Release();
                    break;
                }
                case 105: {
                    heldKeys.remove("NumPad9");
                    onNumPad9Release();
                    break;
                }
                case 106: {
                    heldKeys.remove("NumPadAsterisk");
                    onNumPadAsteriskRelease();
                    break;
                }
                case 107: {
                    heldKeys.remove("NumPadPlus");
                    onNumPadPlusRelease();
                    break;
                }
                case 108: {
                    heldKeys.remove("NumPadComma");
                    onNumPadCommaRelease();
                    break;
                }
                case 109: {
                    heldKeys.remove("NumPad");
                    onNumPadRelease();
                    break;
                }
                case 110: {
                    heldKeys.remove("NumPadPeriod");
                    onNumPadPeriodRelease();
                    break;
                }
                case 111: {
                    heldKeys.remove("NumPadSlash");
                    onNumPadSlashRelease();
                    break;
                }
                case 112: {
                    heldKeys.remove("F1");
                    onF1Release();
                    break;
                }
                case 113: {
                    heldKeys.remove("F2");
                    onF2Release();
                    break;
                }
                case 114: {
                    heldKeys.remove("F3");
                    onF3Release();
                    break;
                }
                case 115: {
                    heldKeys.remove("F4");
                    onF4Release();
                    break;
                }
                case 116: {
                    heldKeys.remove("F5");
                    onF5Release();
                    break;
                }
                case 117: {
                    heldKeys.remove("F6");
                    onF6Release();
                    break;
                }
                case 118: {
                    heldKeys.remove("F7");
                    onF7Release();
                    break;
                }
                case 119: {
                    heldKeys.remove("F8");
                    onF8Release();
                    break;
                }
                case 120: {
                    heldKeys.remove("F9");
                    onF9Release();
                    break;
                }
                case 121: {
                    heldKeys.remove("F10");
                    onF10Release();
                    break;
                }
                case 122: {
                    heldKeys.remove("F11");
                    onF11Release();
                    break;
                }
                case 123: {
                    heldKeys.remove("F12");
                    onF12Release();
                    break;
                }
                case 127: {
                    heldKeys.remove("Delete");
                    onDeleteRelease();
                    break;
                }
                case 128: {
                    heldKeys.remove("DeadGrave");
                    onDeadGraveRelease();
                    break;
                }
                case 129: {
                    heldKeys.remove("DeadAcute");
                    onDeadAcuteRelease();
                    break;
                }
                case 130: {
                    heldKeys.remove("DeadCircumflex");
                    onDeadCircumflexRelease();
                    break;
                }
                case 131: {
                    heldKeys.remove("DeadTilde");
                    onDeadTildeRelease();
                    break;
                }
                case 132: {
                    heldKeys.remove("DeadMacron");
                    onDeadMacronRelease();
                    break;
                }
                case 133: {
                    heldKeys.remove("DeadBreve");
                    onDeadBreveRelease();
                    break;
                }
                case 134: {
                    heldKeys.remove("DeadAboveDot");
                    onDeadAboveDotRelease();
                    break;
                }
                case 135: {
                    heldKeys.remove("DeadDiaeresis");
                    onDeadDiaeresisRelease();
                    break;
                }
                case 136: {
                    heldKeys.remove("DeadAboveRing");
                    onDeadAboveRingRelease();
                    break;
                }
                case 137: {
                    heldKeys.remove("DeadDoubleAcute");
                    onDeadDoubleAcuteRelease();
                    break;
                }
                case 138: {
                    heldKeys.remove("DeadCaron");
                    onDeadCaronRelease();
                    break;
                }
                case 139: {
                    heldKeys.remove("DeadCedilla");
                    onDeadCedillaRelease();
                    break;
                }
                case 140: {
                    heldKeys.remove("DeadOgonek");
                    onDeadOgonekRelease();
                    break;
                }
                case 141: {
                    heldKeys.remove("DeadIota");
                    onDeadIotaRelease();
                    break;
                }
                case 142: {
                    heldKeys.remove("DeadVoicedSound");
                    onDeadVoicedSoundRelease();
                    break;
                }
                case 143: {
                    heldKeys.remove("DeadSemivoicedSound");
                    onDeadSemivoicedSoundRelease();
                    break;
                }
                case 144: {
                    heldKeys.remove("NumLock");
                    onNumLockRelease();
                    break;
                }
                case 145: {
                    heldKeys.remove("ScrollLock");
                    onScrollLockRelease();
                    break;
                }
                case 150: {
                    heldKeys.remove("Ampersand");
                    onAmpersandRelease();
                    break;
                }
                case 151: {
                    heldKeys.remove("Asterisk");
                    onAsteriskRelease();
                    break;
                }
                case 152: {
                    heldKeys.remove("DoubleQuote");
                    onDoubleQuoteRelease();
                    break;
                }
                case 153: {
                    heldKeys.remove("Less");
                    onLessRelease();
                    break;
                }
                case 154: {
                    heldKeys.remove("PrintScreen");
                    onPrintScreenRelease();
                    break;
                }
                case 155: {
                    heldKeys.remove("Insert");
                    onInsertRelease();
                    break;
                }
                case 156: {
                    heldKeys.remove("Help");
                    onHelpRelease();
                    break;
                }
                case 157: {
                    heldKeys.remove("Meta");
                    onMetaRelease();
                    break;
                }
                case 160: {
                    heldKeys.remove("Greater");
                    onGreaterRelease();
                    break;
                }
                case 161: {
                    heldKeys.remove("LeftBrace");
                    onLeftBraceRelease();
                    break;
                }
                case 162: {
                    heldKeys.remove("RightBrace");
                    onRightBraceRelease();
                    break;
                }
                case 192: {
                    heldKeys.remove("BackQuote");
                    onBackQuoteRelease();
                    break;
                }
                case 222: {
                    heldKeys.remove("Quote");
                    onQuoteRelease();
                    break;
                }
                case 240: {
                    heldKeys.remove("Alphanumeric");
                    onAlphanumericRelease();
                    break;
                }
                case 241: {
                    heldKeys.remove("Katakana");
                    onKatakanaRelease();
                    break;
                }
                case 242: {
                    heldKeys.remove("Hiragana");
                    onHiraganaRelease();
                    break;
                }
                case 243: {
                    heldKeys.remove("FullWidth");
                    onFullWidthRelease();
                    break;
                }
                case 244: {
                    heldKeys.remove("HalfWidth");
                    onHalfWidthRelease();
                    break;
                }
                case 245: {
                    heldKeys.remove("RomanCharacters");
                    onRomanCharactersRelease();
                    break;
                }
                case 256: {
                    heldKeys.remove("AllCandidates");
                    onAllCandidatesRelease();
                    break;
                }
                case 257: {
                    heldKeys.remove("PreviousCandidate");
                    onPreviousCandidateRelease();
                    break;
                }
                case 258: {
                    heldKeys.remove("CodeInput");
                    onCodeInputRelease();
                    break;
                }
                case 259: {
                    heldKeys.remove("JapaneseKatakana");
                    onJapaneseKatakanaRelease();
                    break;
                }
                case 260: {
                    heldKeys.remove("JapaneseHiragana");
                    onJapaneseHiraganaRelease();
                    break;
                }
                case 261: {
                    heldKeys.remove("JapaneseRoman");
                    onJapaneseRomanRelease();
                    break;
                }
                case 262: {
                    heldKeys.remove("KanaLock");
                    onKanaLockRelease();
                    break;
                }
                case 263: {
                    heldKeys.remove("InputMethodOnOff");
                    onInputMethodOnOffRelease();
                    break;
                }
                case 512: {
                    heldKeys.remove("At");
                    onAtRelease();
                    break;
                }
                case 513: {
                    heldKeys.remove("Colon");
                    onColonRelease();
                    break;
                }
                case 514: {
                    heldKeys.remove("Circumflex");
                    onCircumflexRelease();
                    break;
                }
                case 515: {
                    heldKeys.remove("Dollar");
                    onDollarRelease();
                    break;
                }
                case 516: {
                    heldKeys.remove("Euro");
                    onEuroRelease();
                    break;
                }
                case 517: {
                    heldKeys.remove("ExclamationMark");
                    onExclamationMarkRelease();
                    break;
                }
                case 518: {
                    heldKeys.remove("InvertedExclamationMark");
                    onInvertedExclamationMarkRelease();
                    break;
                }
                case 519: {
                    heldKeys.remove("LeftParenthesis");
                    onLeftParenthesisRelease();
                    break;
                }
                case 520: {
                    heldKeys.remove("NumberSign");
                    onNumberSignRelease();
                    break;
                }
                case 521: {
                    heldKeys.remove("Plus");
                    onPlusRelease();
                    break;
                }
                case 522: {
                    heldKeys.remove("RightParenthesis");
                    onRightParenthesisRelease();
                    break;
                }
                case 523: {
                    heldKeys.remove("Underscore");
                    onUnderscoreRelease();
                    break;
                }
                case 524: {
                    heldKeys.remove("Windows");
                    onWindowsRelease();
                    break;
                }
                case 525: {
                    heldKeys.remove("ContextMenu");
                    onContextMenuRelease();
                    break;
                }
                case 61440: {
                    heldKeys.remove("F13");
                    onF13Release();
                    break;
                }
                case 61441: {
                    heldKeys.remove("F14");
                    onF14Release();
                    break;
                }
                case 61442: {
                    heldKeys.remove("F15");
                    onF15Release();
                    break;
                }
                case 61443: {
                    heldKeys.remove("F16");
                    onF16Release();
                    break;
                }
                case 61444: {
                    heldKeys.remove("F17");
                    onF17Release();
                    break;
                }
                case 61445: {
                    heldKeys.remove("F18");
                    onF18Release();
                    break;
                }
                case 61446: {
                    heldKeys.remove("F19");
                    onF19Release();
                    break;
                }
                case 61447: {
                    heldKeys.remove("F20");
                    onF20Release();
                    break;
                }
                case 61448: {
                    heldKeys.remove("F21");
                    onF21Release();
                    break;
                }
                case 61449: {
                    heldKeys.remove("F22");
                    onF22Release();
                    break;
                }
                case 61450: {
                    heldKeys.remove("F23");
                    onF23Release();
                    break;
                }
                case 61451: {
                    heldKeys.remove("F24");
                    onF24Release();
                    break;
                }
                case 65312: {
                    heldKeys.remove("Compose");
                    onComposeRelease();
                    break;
                }
            }
            afterAnyKeyRelease(keyCode, keyChar);
        } else if (id == MouseEvent.MOUSE_PRESSED || id == MouseEvent.MOUSE_RELEASED) {
            Insets  ins = ((JFrame) event.getSource()).getInsets();
            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);
            switch (((MouseEvent) event).getButton()) {
                case MouseEvent.BUTTON1: {
                    if (id == MouseEvent.MOUSE_PRESSED) {
                        heldKeys.push("LMB");
                        onLMBPress(pos);
                    } else {
                        heldKeys.remove("LMB");
                        onLMBRelease(pos);
                    }
                    break;
                }
                case MouseEvent.BUTTON2: {
                    if (id == MouseEvent.MOUSE_PRESSED) {
                        heldKeys.push("MMB");
                        onMMBPress(pos);
                    } else {
                        heldKeys.remove("MMB");
                        onMMBRelease(pos);
                    }
                    break;
                }
                case MouseEvent.BUTTON3: {
                    if (id == MouseEvent.MOUSE_PRESSED) {
                        heldKeys.push("RMB");
                        onRMBPress(pos);
                    } else {
                        heldKeys.remove("RMB");
                        onRMBRelease(pos);
                    }
                    break;
                }
            }
        } else if (id == MouseEvent.MOUSE_MOVED || id == MouseEvent.MOUSE_DRAGGED) {
            Insets  ins = ((JFrame) event.getSource()).getInsets();
            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);
            onMouseMoved(pos);
        } else if (id == MouseEvent.MOUSE_WHEEL) {
            Insets  ins = ((JFrame) event.getSource()).getInsets();
            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);
            onMouseWheel(pos, ((MouseWheelEvent) event).getWheelRotation());
        } else if (id == ComponentEvent.COMPONENT_MOVED) {
            Component c = ((ComponentEvent) event).getComponent();
            if (!(c instanceof JFrame && c.isVisible())) return;
            // NOTE: window.pos points to the top-left corner of the drawable area.
            //  In other words - excludes title bar.
            window.pos = new NGVec2i(c.getLocation()).add(window.ins.left, window.ins.top);
            onWindowMoved(c.getX(), c.getY());
        } else if (id == ComponentEvent.COMPONENT_RESIZED) {
            Component c = ((ComponentEvent) event).getComponent();
            if (!(c instanceof JFrame && c.isVisible())) return;
            Insets ins = ((JFrame) c).getInsets();
            w = Math.max(WINDOW_MINIMAL_SIZE, c.getWidth() - ins.left - ins.right);
            h = Math.max(WINDOW_MINIMAL_SIZE, c.getHeight() - ins.top - ins.bottom);
            window.g.resize(w, h);
            onWindowResize(w, h);
        } else if (id == WindowEvent.WINDOW_ICONIFIED) {
            onWindowMinimize();
        } else if (id == WindowEvent.WINDOW_DEICONIFIED) {
            onWindowRestore();
        }
    }

    public void onMouseMoved(NGVec2i pos) { }
    public void onMouseWheel(NGVec2i pos, int direction) { }

    public void onWindowMoved(int x, int y) { }
    public void onWindowResize(int w, int h) { }
    public void onWindowMinimize() { }
    public void onWindowRestore() { }

    public void onLMBPress(NGVec2i pos) { }
    public void onLMBRelease(NGVec2i pos) { }
    public void whileLMBHeld(NGVec2i pos) { }

    public void onMMBPress(NGVec2i pos) { }
    public void onMMBRelease(NGVec2i pos) { }
    public void whileMMBHeld(NGVec2i pos) { }

    public void onRMBPress(NGVec2i pos) { }
    public void onRMBRelease(NGVec2i pos) { }
    public void whileRMBHeld(NGVec2i pos) { }

    public void onAnyKeyPress(int keyCode, char keyChar) { }
    public void afterAnyKeyPress(int keyCode, char keyChar) { }

    public void onAnyKeyRelease(int keyCode, char keyChar) { }
    public void afterAnyKeyRelease(int keyCode, char keyChar) { }

    public void onEnterPress() { }
    public void onEnterRelease() { }
    public void whileEnterHeld() { }

    public void onClearPress() { }
    public void onClearRelease() { }
    public void whileClearHeld() { }

    public void onShiftPress() { }
    public void onShiftRelease() { }
    public void whileShiftHeld() { }

    public void onCtrlPress() { }
    public void onCtrlRelease() { }
    public void whileCtrlHeld() { }

    public void onAltPress() { }
    public void onAltRelease() { }
    public void whileAltHeld() { }

    public void onPausePress() { }
    public void onPauseRelease() { }
    public void whilePauseHeld() { }

    public void onCapsLockPress() { }
    public void onCapsLockRelease() { }
    public void whileCapsLockHeld() { }

    public void onKanaPress() { }
    public void onKanaRelease() { }
    public void whileKanaHeld() { }

    public void onFinalPress() { }
    public void onFinalRelease() { }
    public void whileFinalHeld() { }

    public void onKanjiPress() { }
    public void onKanjiRelease() { }
    public void whileKanjiHeld() { }

    public void onEscapePress() {exit();}
    public void onEscapeRelease() { }
    public void whileEscapeHeld() { }

    public void onConvertPress() { }
    public void onConvertRelease() { }
    public void whileConvertHeld() { }

    public void onNoConvertPress() { }
    public void onNoConvertRelease() { }
    public void whileNoConvertHeld() { }

    public void onAcceptPress() { }
    public void onAcceptRelease() { }
    public void whileAcceptHeld() { }

    public void onModeChangePress() { }
    public void onModeChangeRelease() { }
    public void whileModeChangeHeld() { }

    public void onSpacePress() { }
    public void onSpaceRelease() { }
    public void whileSpaceHeld() { }

    public void onPageUpPress() { }
    public void onPageUpRelease() { }
    public void whilePageUpHeld() { }

    public void onPageDownPress() { }
    public void onPageDownRelease() { }
    public void whilePageDownHeld() { }

    public void onEndPress() { }
    public void onEndRelease() { }
    public void whileEndHeld() { }

    public void onHomePress() { }
    public void onHomeRelease() { }
    public void whileHomeHeld() { }

    public void onLeftPress() { }
    public void onLeftRelease() { }
    public void whileLeftHeld() { }

    public void onUpPress() { }
    public void onUpRelease() { }
    public void whileUpHeld() { }

    public void onRightPress() { }
    public void onRightRelease() { }
    public void whileRightHeld() { }

    public void onDownPress() { }
    public void onDownRelease() { }
    public void whileDownHeld() { }

    public void onCommaPress() { }
    public void onCommaRelease() { }
    public void whileCommaHeld() { }

    public void onMinusPress() { }
    public void onMinusRelease() { }
    public void whileMinusHeld() { }

    public void onPeriodPress() { }
    public void onPeriodRelease() { }
    public void whilePeriodHeld() { }

    public void onSlashPress() { }
    public void onSlashRelease() { }
    public void whileSlashHeld() { }

    public void on0Press() { }
    public void on0Release() { }
    public void while0Held() { }

    public void on1Press() { }
    public void on1Release() { }
    public void while1Held() { }

    public void on2Press() { }
    public void on2Release() { }
    public void while2Held() { }

    public void on3Press() { }
    public void on3Release() { }
    public void while3Held() { }

    public void on4Press() { }
    public void on4Release() { }
    public void while4Held() { }

    public void on5Press() { }
    public void on5Release() { }
    public void while5Held() { }

    public void on6Press() { }
    public void on6Release() { }
    public void while6Held() { }

    public void on7Press() { }
    public void on7Release() { }
    public void while7Held() { }

    public void on8Press() { }
    public void on8Release() { }
    public void while8Held() { }

    public void on9Press() { }
    public void on9Release() { }
    public void while9Held() { }

    public void onSemicolonPress() { }
    public void onSemicolonRelease() { }
    public void whileSemicolonHeld() { }

    public void onEqualsPress() { }
    public void onEqualsRelease() { }
    public void whileEqualsHeld() { }

    public void onAPress() { }
    public void onARelease() { }
    public void whileAHeld() { }

    public void onBPress() { }
    public void onBRelease() { }
    public void whileBHeld() { }

    public void onCPress() { }
    public void onCRelease() { }
    public void whileCHeld() { }

    public void onDPress() { }
    public void onDRelease() { }
    public void whileDHeld() { }

    public void onEPress() { }
    public void onERelease() { }
    public void whileEHeld() { }

    public void onFPress() { }
    public void onFRelease() { }
    public void whileFHeld() { }

    public void onGPress() { }
    public void onGRelease() { }
    public void whileGHeld() { }

    public void onHPress() { }
    public void onHRelease() { }
    public void whileHHeld() { }

    public void onIPress() { }
    public void onIRelease() { }
    public void whileIHeld() { }

    public void onJPress() { }
    public void onJRelease() { }
    public void whileJHeld() { }

    public void onKPress() { }
    public void onKRelease() { }
    public void whileKHeld() { }

    public void onLPress() { }
    public void onLRelease() { }
    public void whileLHeld() { }

    public void onMPress() { }
    public void onMRelease() { }
    public void whileMHeld() { }

    public void onNPress() { }
    public void onNRelease() { }
    public void whileNHeld() { }

    public void onOPress() { }
    public void onORelease() { }
    public void whileOHeld() { }

    public void onPPress() { }
    public void onPRelease() { }
    public void whilePHeld() { }

    public void onQPress() { }
    public void onQRelease() { }
    public void whileQHeld() { }

    public void onRPress() { }
    public void onRRelease() { }
    public void whileRHeld() { }

    public void onSPress() { }
    public void onSRelease() { }
    public void whileSHeld() { }

    public void onTPress() { }
    public void onTRelease() { }
    public void whileTHeld() { }

    public void onUPress() { }
    public void onURelease() { }
    public void whileUHeld() { }

    public void onVPress() { }
    public void onVRelease() { }
    public void whileVHeld() { }

    public void onWPress() { }
    public void onWRelease() { }
    public void whileWHeld() { }

    public void onXPress() { }
    public void onXRelease() { }
    public void whileXHeld() { }

    public void onYPress() { }
    public void onYRelease() { }
    public void whileYHeld() { }

    public void onZPress() { }
    public void onZRelease() { }
    public void whileZHeld() { }

    public void onOpenBracketPress() { }
    public void onOpenBracketRelease() { }
    public void whileOpenBracketHeld() { }

    public void onBackSlashPress() { }
    public void onBackSlashRelease() { }
    public void whileBackSlashHeld() { }

    public void onCloseBracketPress() { }
    public void onCloseBracketRelease() { }
    public void whileCloseBracketHeld() { }

    public void onNumPad0Press() { }
    public void onNumPad0Release() { }
    public void whileNumPad0Held() { }

    public void onNumPad1Press() { }
    public void onNumPad1Release() { }
    public void whileNumPad1Held() { }

    public void onNumPad2Press() { }
    public void onNumPad2Release() { }
    public void whileNumPad2Held() { }

    public void onNumPad3Press() { }
    public void onNumPad3Release() { }
    public void whileNumPad3Held() { }

    public void onNumPad4Press() { }
    public void onNumPad4Release() { }
    public void whileNumPad4Held() { }

    public void onNumPad5Press() { }
    public void onNumPad5Release() { }
    public void whileNumPad5Held() { }

    public void onNumPad6Press() { }
    public void onNumPad6Release() { }
    public void whileNumPad6Held() { }

    public void onNumPad7Press() { }
    public void onNumPad7Release() { }
    public void whileNumPad7Held() { }

    public void onNumPad8Press() { }
    public void onNumPad8Release() { }
    public void whileNumPad8Held() { }

    public void onNumPad9Press() { }
    public void onNumPad9Release() { }
    public void whileNumPad9Held() { }

    public void onNumPadAsteriskPress() { }
    public void onNumPadAsteriskRelease() { }
    public void whileNumPadAsteriskHeld() { }

    public void onNumPadPlusPress() { }
    public void onNumPadPlusRelease() { }
    public void whileNumPadPlusHeld() { }

    public void onNumPadCommaPress() { }
    public void onNumPadCommaRelease() { }
    public void whileNumPadCommaHeld() { }

    public void onNumPadPress() { }
    public void onNumPadRelease() { }
    public void whileNumPadHeld() { }

    public void onNumPadPeriodPress() { }
    public void onNumPadPeriodRelease() { }
    public void whileNumPadPeriodHeld() { }

    public void onNumPadSlashPress() { }
    public void onNumPadSlashRelease() { }
    public void whileNumPadSlashHeld() { }

    public void onF1Press() { }
    public void onF1Release() { }
    public void whileF1Held() { }

    public void onF2Press() { }
    public void onF2Release() { }
    public void whileF2Held() { }

    public void onF3Press() { }
    public void onF3Release() { }
    public void whileF3Held() { }

    public void onF4Press() { }
    public void onF4Release() { }
    public void whileF4Held() { }

    public void onF5Press() { }
    public void onF5Release() { }
    public void whileF5Held() { }

    public void onF6Press() { }
    public void onF6Release() { }
    public void whileF6Held() { }

    public void onF7Press() { }
    public void onF7Release() { }
    public void whileF7Held() { }

    public void onF8Press() { }
    public void onF8Release() { }
    public void whileF8Held() { }

    public void onF9Press() { }
    public void onF9Release() { }
    public void whileF9Held() { }

    public void onF10Press() { }
    public void onF10Release() { }
    public void whileF10Held() { }

    public void onF11Press() { }
    public void onF11Release() { }
    public void whileF11Held() { }

    public void onF12Press() { }
    public void onF12Release() { }
    public void whileF12Held() { }

    public void onDeletePress() { }
    public void onDeleteRelease() { }
    public void whileDeleteHeld() { }

    public void onDeadGravePress() { }
    public void onDeadGraveRelease() { }
    public void whileDeadGraveHeld() { }

    public void onDeadAcutePress() { }
    public void onDeadAcuteRelease() { }
    public void whileDeadAcuteHeld() { }

    public void onDeadCircumflexPress() { }
    public void onDeadCircumflexRelease() { }
    public void whileDeadCircumflexHeld() { }

    public void onDeadTildePress() { }
    public void onDeadTildeRelease() { }
    public void whileDeadTildeHeld() { }

    public void onDeadMacronPress() { }
    public void onDeadMacronRelease() { }
    public void whileDeadMacronHeld() { }

    public void onDeadBrevePress() { }
    public void onDeadBreveRelease() { }
    public void whileDeadBreveHeld() { }

    public void onDeadAboveDotPress() { }
    public void onDeadAboveDotRelease() { }
    public void whileDeadAboveDotHeld() { }

    public void onDeadDiaeresisPress() { }
    public void onDeadDiaeresisRelease() { }
    public void whileDeadDiaeresisHeld() { }

    public void onDeadAboveRingPress() { }
    public void onDeadAboveRingRelease() { }
    public void whileDeadAboveRingHeld() { }

    public void onDeadDoubleAcutePress() { }
    public void onDeadDoubleAcuteRelease() { }
    public void whileDeadDoubleAcuteHeld() { }

    public void onDeadCaronPress() { }
    public void onDeadCaronRelease() { }
    public void whileDeadCaronHeld() { }

    public void onDeadCedillaPress() { }
    public void onDeadCedillaRelease() { }
    public void whileDeadCedillaHeld() { }

    public void onDeadOgonekPress() { }
    public void onDeadOgonekRelease() { }
    public void whileDeadOgonekHeld() { }

    public void onDeadIotaPress() { }
    public void onDeadIotaRelease() { }
    public void whileDeadIotaHeld() { }

    public void onDeadVoicedSoundPress() { }
    public void onDeadVoicedSoundRelease() { }
    public void whileDeadVoicedSoundHeld() { }

    public void onDeadSemivoicedSoundPress() { }
    public void onDeadSemivoicedSoundRelease() { }
    public void whileDeadSemivoicedSoundHeld() { }

    public void onNumLockPress() { }
    public void onNumLockRelease() { }
    public void whileNumLockHeld() { }

    public void onScrollLockPress() { }
    public void onScrollLockRelease() { }
    public void whileScrollLockHeld() { }

    public void onAmpersandPress() { }
    public void onAmpersandRelease() { }
    public void whileAmpersandHeld() { }

    public void onAsteriskPress() { }
    public void onAsteriskRelease() { }
    public void whileAsteriskHeld() { }

    public void onDoubleQuotePress() { }
    public void onDoubleQuoteRelease() { }
    public void whileDoubleQuoteHeld() { }

    public void onLessPress() { }
    public void onLessRelease() { }
    public void whileLessHeld() { }

    public void onPrintScreenPress() { }
    public void onPrintScreenRelease() { }
    public void whilePrintScreenHeld() { }

    public void onInsertPress() { }
    public void onInsertRelease() { }
    public void whileInsertHeld() { }

    public void onHelpPress() { }
    public void onHelpRelease() { }
    public void whileHelpHeld() { }

    public void onMetaPress() { }
    public void onMetaRelease() { }
    public void whileMetaHeld() { }

    public void onGreaterPress() { }
    public void onGreaterRelease() { }
    public void whileGreaterHeld() { }

    public void onLeftBracePress() { }
    public void onLeftBraceRelease() { }
    public void whileLeftBraceHeld() { }

    public void onRightBracePress() { }
    public void onRightBraceRelease() { }
    public void whileRightBraceHeld() { }

    public void onBackQuotePress() { }
    public void onBackQuoteRelease() { }
    public void whileBackQuoteHeld() { }

    public void onQuotePress() { }
    public void onQuoteRelease() { }
    public void whileQuoteHeld() { }

    public void onAlphanumericPress() { }
    public void onAlphanumericRelease() { }
    public void whileAlphanumericHeld() { }

    public void onKatakanaPress() { }
    public void onKatakanaRelease() { }
    public void whileKatakanaHeld() { }

    public void onHiraganaPress() { }
    public void onHiraganaRelease() { }
    public void whileHiraganaHeld() { }

    public void onFullWidthPress() { }
    public void onFullWidthRelease() { }
    public void whileFullWidthHeld() { }

    public void onHalfWidthPress() { }
    public void onHalfWidthRelease() { }
    public void whileHalfWidthHeld() { }

    public void onRomanCharactersPress() { }
    public void onRomanCharactersRelease() { }
    public void whileRomanCharactersHeld() { }

    public void onAllCandidatesPress() { }
    public void onAllCandidatesRelease() { }
    public void whileAllCandidatesHeld() { }

    public void onPreviousCandidatePress() { }
    public void onPreviousCandidateRelease() { }
    public void whilePreviousCandidateHeld() { }

    public void onCodeInputPress() { }
    public void onCodeInputRelease() { }
    public void whileCodeInputHeld() { }

    public void onJapaneseKatakanaPress() { }
    public void onJapaneseKatakanaRelease() { }
    public void whileJapaneseKatakanaHeld() { }

    public void onJapaneseHiraganaPress() { }
    public void onJapaneseHiraganaRelease() { }
    public void whileJapaneseHiraganaHeld() { }

    public void onJapaneseRomanPress() { }
    public void onJapaneseRomanRelease() { }
    public void whileJapaneseRomanHeld() { }

    public void onKanaLockPress() { }
    public void onKanaLockRelease() { }
    public void whileKanaLockHeld() { }

    public void onInputMethodOnOffPress() { }
    public void onInputMethodOnOffRelease() { }
    public void whileInputMethodOnOffHeld() { }

    public void onAtPress() { }
    public void onAtRelease() { }
    public void whileAtHeld() { }

    public void onColonPress() { }
    public void onColonRelease() { }
    public void whileColonHeld() { }

    public void onCircumflexPress() { }
    public void onCircumflexRelease() { }
    public void whileCircumflexHeld() { }

    public void onDollarPress() { }
    public void onDollarRelease() { }
    public void whileDollarHeld() { }

    public void onEuroPress() { }
    public void onEuroRelease() { }
    public void whileEuroHeld() { }

    public void onExclamationMarkPress() { }
    public void onExclamationMarkRelease() { }
    public void whileExclamationMarkHeld() { }

    public void onInvertedExclamationMarkPress() { }
    public void onInvertedExclamationMarkRelease() { }
    public void whileInvertedExclamationMarkHeld() { }

    public void onLeftParenthesisPress() { }
    public void onLeftParenthesisRelease() { }
    public void whileLeftParenthesisHeld() { }

    public void onNumberSignPress() { }
    public void onNumberSignRelease() { }
    public void whileNumberSignHeld() { }

    public void onPlusPress() { }
    public void onPlusRelease() { }
    public void whilePlusHeld() { }

    public void onRightParenthesisPress() { }
    public void onRightParenthesisRelease() { }
    public void whileRightParenthesisHeld() { }

    public void onUnderscorePress() { }
    public void onUnderscoreRelease() { }
    public void whileUnderscoreHeld() { }

    public void onWindowsPress() { }
    public void onWindowsRelease() { }
    public void whileWindowsHeld() { }

    public void onContextMenuPress() { }
    public void onContextMenuRelease() { }
    public void whileContextMenuHeld() { }

    public void onF13Press() { }
    public void onF13Release() { }
    public void whileF13Held() { }

    public void onF14Press() { }
    public void onF14Release() { }
    public void whileF14Held() { }

    public void onF15Press() { }
    public void onF15Release() { }
    public void whileF15Held() { }

    public void onF16Press() { }
    public void onF16Release() { }
    public void whileF16Held() { }

    public void onF17Press() { }
    public void onF17Release() { }
    public void whileF17Held() { }

    public void onF18Press() { }
    public void onF18Release() { }
    public void whileF18Held() { }

    public void onF19Press() { }
    public void onF19Release() { }
    public void whileF19Held() { }

    public void onF20Press() { }
    public void onF20Release() { }
    public void whileF20Held() { }

    public void onF21Press() { }
    public void onF21Release() { }
    public void whileF21Held() { }

    public void onF22Press() { }
    public void onF22Release() { }
    public void whileF22Held() { }

    public void onF23Press() { }
    public void onF23Release() { }
    public void whileF23Held() { }

    public void onF24Press() { }
    public void onF24Release() { }
    public void whileF24Held() { }

    public void onComposePress() { }
    public void onComposeRelease() { }
    public void whileComposeHeld() { }


}
