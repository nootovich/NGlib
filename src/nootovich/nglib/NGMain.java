package nootovich.nglib;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class NGMain extends NGHotReloadable implements AWTEventListener {

    @Override
    public void eventDispatched(AWTEvent event) {
        int id = event.getID();
        if (id == KeyEvent.KEY_PRESSED) {
            switch (((KeyEvent) event).getKeyCode()) {
                case 10 -> onEnterPress();
                case 12 -> onClearPress();
                case 16 -> onShiftPress();
                case 17 -> onCtrlPress();
                case 18 -> onAltPress();
                case 19 -> onPausePress();
                case 20 -> onCapsLockPress();
                case 21 -> onKanaPress();
                case 24 -> onFinalPress();
                case 25 -> onKanjiPress();
                case 27 -> onEscapePress();
                case 28 -> onConvertPress();
                case 29 -> onNoConvertPress();
                case 30 -> onAcceptPress();
                case 31 -> onModeChangePress();
                case 32 -> onSpacePress();
                case 33 -> onPageUpPress();
                case 34 -> onPageDownPress();
                case 35 -> onEndPress();
                case 36 -> onHomePress();
                case 37 -> onLeftPress();
                case 38 -> onUpPress();
                case 39 -> onRightPress();
                case 40 -> onDownPress();
                case 44 -> onCommaPress();
                case 45 -> onMinusPress();
                case 46 -> onPeriodPress();
                case 47 -> onSlashPress();
                case 48 -> on0Press();
                case 49 -> on1Press();
                case 50 -> on2Press();
                case 51 -> on3Press();
                case 52 -> on4Press();
                case 53 -> on5Press();
                case 54 -> on6Press();
                case 55 -> on7Press();
                case 56 -> on8Press();
                case 57 -> on9Press();
                case 59 -> onSemicolonPress();
                case 61 -> onEqualsPress();
                case 65 -> onAPress();
                case 66 -> onBPress();
                case 67 -> onCPress();
                case 68 -> onDPress();
                case 69 -> onEPress();
                case 70 -> onFPress();
                case 71 -> onGPress();
                case 72 -> onHPress();
                case 73 -> onIPress();
                case 74 -> onJPress();
                case 75 -> onKPress();
                case 76 -> onLPress();
                case 77 -> onMPress();
                case 78 -> onNPress();
                case 79 -> onOPress();
                case 80 -> onPPress();
                case 81 -> onQPress();
                case 82 -> onRPress();
                case 83 -> onSPress();
                case 84 -> onTPress();
                case 85 -> onUPress();
                case 86 -> onVPress();
                case 87 -> onWPress();
                case 88 -> onXPress();
                case 89 -> onYPress();
                case 90 -> onZPress();
                case 91 -> onOpenBracketPress();
                case 92 -> onBackSlashPress();
                case 93 -> onCloseBracketPress();
                case 96 -> onNumPad0Press();
                case 97 -> onNumPad1Press();
                case 98 -> onNumPad2Press();
                case 99 -> onNumPad3Press();
                case 100 -> onNumPad4Press();
                case 101 -> onNumPad5Press();
                case 102 -> onNumPad6Press();
                case 103 -> onNumPad7Press();
                case 104 -> onNumPad8Press();
                case 105 -> onNumPad9Press();
                case 106 -> onNumPadAsteriskPress();
                case 107 -> onNumPadPlusPress();
                case 108 -> onNumPadCommaPress();
                case 109 -> onNumPadPress();
                case 110 -> onNumPadPeriodPress();
                case 111 -> onNumPadSlashPress();
                case 112 -> onF1Press();
                case 113 -> onF2Press();
                case 114 -> onF3Press();
                case 115 -> onF4Press();
                case 116 -> onF5Press();
                case 117 -> onF6Press();
                case 118 -> onF7Press();
                case 119 -> onF8Press();
                case 120 -> onF9Press();
                case 121 -> onF10Press();
                case 122 -> onF11Press();
                case 123 -> onF12Press();
                case 127 -> onDeletePress();
                case 128 -> onDeadGravePress();
                case 129 -> onDeadAcutePress();
                case 130 -> onDeadCircumflexPress();
                case 131 -> onDeadTildePress();
                case 132 -> onDeadMacronPress();
                case 133 -> onDeadBrevePress();
                case 134 -> onDeadAboveDotPress();
                case 135 -> onDeadDiaeresisPress();
                case 136 -> onDeadAboveRingPress();
                case 137 -> onDeadDoubleAcutePress();
                case 138 -> onDeadCaronPress();
                case 139 -> onDeadCedillaPress();
                case 140 -> onDeadOgonekPress();
                case 141 -> onDeadIotaPress();
                case 142 -> onDeadVoicedSoundPress();
                case 143 -> onDeadSemivoicedSoundPress();
                case 144 -> onNumLockPress();
                case 145 -> onScrollLockPress();
                case 150 -> onAmpersandPress();
                case 151 -> onAsteriskPress();
                case 152 -> onDoubleQuotePress();
                case 153 -> onLessPress();
                case 154 -> onPrintScreenPress();
                case 155 -> onInsertPress();
                case 156 -> onHelpPress();
                case 157 -> onMetaPress();
                case 160 -> onGreaterPress();
                case 161 -> onLeftBracePress();
                case 162 -> onRightBracePress();
                case 192 -> onBackQuotePress();
                case 222 -> onQuotePress();
                case 240 -> onAlphanumericPress();
                case 241 -> onKatakanaPress();
                case 242 -> onHiraganaPress();
                case 243 -> onFullWidthPress();
                case 244 -> onHalfWidthPress();
                case 245 -> onRomanCharactersPress();
                case 256 -> onAllCandidatesPress();
                case 257 -> onPreviousCandidatePress();
                case 258 -> onCodeInputPress();
                case 259 -> onJapaneseKatakanaPress();
                case 260 -> onJapaneseHiraganaPress();
                case 261 -> onJapaneseRomanPress();
                case 262 -> onKanaLockPress();
                case 263 -> onInputMethodOnOffPress();
                case 512 -> onAtPress();
                case 513 -> onColonPress();
                case 514 -> onCircumflexPress();
                case 515 -> onDollarPress();
                case 516 -> onEuroPress();
                case 517 -> onExclamationMarkPress();
                case 518 -> onInvertedExclamationMarkPress();
                case 519 -> onLeftParenthesisPress();
                case 520 -> onNumberSignPress();
                case 521 -> onPlusPress();
                case 522 -> onRightParenthesisPress();
                case 523 -> onUnderscorePress();
                case 524 -> onWindowsPress();
                case 525 -> onContextMenuPress();
                case 61440 -> onF13Press();
                case 61441 -> onF14Press();
                case 61442 -> onF15Press();
                case 61443 -> onF16Press();
                case 61444 -> onF17Press();
                case 61445 -> onF18Press();
                case 61446 -> onF19Press();
                case 61447 -> onF20Press();
                case 61448 -> onF21Press();
                case 61449 -> onF22Press();
                case 61450 -> onF23Press();
                case 61451 -> onF24Press();
                case 65312 -> onComposePress();
            }
        } else if (id == KeyEvent.KEY_RELEASED) {
            switch (((KeyEvent) event).getKeyCode()) {
                case 10 -> onEnterRelease();
                case 12 -> onClearRelease();
                case 16 -> onShiftRelease();
                case 17 -> onCtrlRelease();
                case 18 -> onAltRelease();
                case 19 -> onPauseRelease();
                case 20 -> onCapsLockRelease();
                case 21 -> onKanaRelease();
                case 24 -> onFinalRelease();
                case 25 -> onKanjiRelease();
                case 27 -> onEscapeRelease();
                case 28 -> onConvertRelease();
                case 29 -> onNoConvertRelease();
                case 30 -> onAcceptRelease();
                case 31 -> onModeChangeRelease();
                case 32 -> onSpaceRelease();
                case 33 -> onPageUpRelease();
                case 34 -> onPageDownRelease();
                case 35 -> onEndRelease();
                case 36 -> onHomeRelease();
                case 37 -> onLeftRelease();
                case 38 -> onUpRelease();
                case 39 -> onRightRelease();
                case 40 -> onDownRelease();
                case 44 -> onCommaRelease();
                case 45 -> onMinusRelease();
                case 46 -> onPeriodRelease();
                case 47 -> onSlashRelease();
                case 48 -> on0Release();
                case 49 -> on1Release();
                case 50 -> on2Release();
                case 51 -> on3Release();
                case 52 -> on4Release();
                case 53 -> on5Release();
                case 54 -> on6Release();
                case 55 -> on7Release();
                case 56 -> on8Release();
                case 57 -> on9Release();
                case 59 -> onSemicolonRelease();
                case 61 -> onEqualsRelease();
                case 65 -> onARelease();
                case 66 -> onBRelease();
                case 67 -> onCRelease();
                case 68 -> onDRelease();
                case 69 -> onERelease();
                case 70 -> onFRelease();
                case 71 -> onGRelease();
                case 72 -> onHRelease();
                case 73 -> onIRelease();
                case 74 -> onJRelease();
                case 75 -> onKRelease();
                case 76 -> onLRelease();
                case 77 -> onMRelease();
                case 78 -> onNRelease();
                case 79 -> onORelease();
                case 80 -> onPRelease();
                case 81 -> onQRelease();
                case 82 -> onRRelease();
                case 83 -> onSRelease();
                case 84 -> onTRelease();
                case 85 -> onURelease();
                case 86 -> onVRelease();
                case 87 -> onWRelease();
                case 88 -> onXRelease();
                case 89 -> onYRelease();
                case 90 -> onZRelease();
                case 91 -> onOpenBracketRelease();
                case 92 -> onBackSlashRelease();
                case 93 -> onCloseBracketRelease();
                case 96 -> onNumPad0Release();
                case 97 -> onNumPad1Release();
                case 98 -> onNumPad2Release();
                case 99 -> onNumPad3Release();
                case 100 -> onNumPad4Release();
                case 101 -> onNumPad5Release();
                case 102 -> onNumPad6Release();
                case 103 -> onNumPad7Release();
                case 104 -> onNumPad8Release();
                case 105 -> onNumPad9Release();
                case 106 -> onNumPadAsteriskRelease();
                case 107 -> onNumPadPlusRelease();
                case 108 -> onNumPadCommaRelease();
                case 109 -> onNumPadRelease();
                case 110 -> onNumPadPeriodRelease();
                case 111 -> onNumPadSlashRelease();
                case 112 -> onF1Release();
                case 113 -> onF2Release();
                case 114 -> onF3Release();
                case 115 -> onF4Release();
                case 116 -> onF5Release();
                case 117 -> onF6Release();
                case 118 -> onF7Release();
                case 119 -> onF8Release();
                case 120 -> onF9Release();
                case 121 -> onF10Release();
                case 122 -> onF11Release();
                case 123 -> onF12Release();
                case 127 -> onDeleteRelease();
                case 128 -> onDeadGraveRelease();
                case 129 -> onDeadAcuteRelease();
                case 130 -> onDeadCircumflexRelease();
                case 131 -> onDeadTildeRelease();
                case 132 -> onDeadMacronRelease();
                case 133 -> onDeadBreveRelease();
                case 134 -> onDeadAboveDotRelease();
                case 135 -> onDeadDiaeresisRelease();
                case 136 -> onDeadAboveRingRelease();
                case 137 -> onDeadDoubleAcuteRelease();
                case 138 -> onDeadCaronRelease();
                case 139 -> onDeadCedillaRelease();
                case 140 -> onDeadOgonekRelease();
                case 141 -> onDeadIotaRelease();
                case 142 -> onDeadVoicedSoundRelease();
                case 143 -> onDeadSemivoicedSoundRelease();
                case 144 -> onNumLockRelease();
                case 145 -> onScrollLockRelease();
                case 150 -> onAmpersandRelease();
                case 151 -> onAsteriskRelease();
                case 152 -> onDoubleQuoteRelease();
                case 153 -> onLessRelease();
                case 154 -> onPrintScreenRelease();
                case 155 -> onInsertRelease();
                case 156 -> onHelpRelease();
                case 157 -> onMetaRelease();
                case 160 -> onGreaterRelease();
                case 161 -> onLeftBraceRelease();
                case 162 -> onRightBraceRelease();
                case 192 -> onBackQuoteRelease();
                case 222 -> onQuoteRelease();
                case 240 -> onAlphanumericRelease();
                case 241 -> onKatakanaRelease();
                case 242 -> onHiraganaRelease();
                case 243 -> onFullWidthRelease();
                case 244 -> onHalfWidthRelease();
                case 245 -> onRomanCharactersRelease();
                case 256 -> onAllCandidatesRelease();
                case 257 -> onPreviousCandidateRelease();
                case 258 -> onCodeInputRelease();
                case 259 -> onJapaneseKatakanaRelease();
                case 260 -> onJapaneseHiraganaRelease();
                case 261 -> onJapaneseRomanRelease();
                case 262 -> onKanaLockRelease();
                case 263 -> onInputMethodOnOffRelease();
                case 512 -> onAtRelease();
                case 513 -> onColonRelease();
                case 514 -> onCircumflexRelease();
                case 515 -> onDollarRelease();
                case 516 -> onEuroRelease();
                case 517 -> onExclamationMarkRelease();
                case 518 -> onInvertedExclamationMarkRelease();
                case 519 -> onLeftParenthesisRelease();
                case 520 -> onNumberSignRelease();
                case 521 -> onPlusRelease();
                case 522 -> onRightParenthesisRelease();
                case 523 -> onUnderscoreRelease();
                case 524 -> onWindowsRelease();
                case 525 -> onContextMenuRelease();
                case 61440 -> onF13Release();
                case 61441 -> onF14Release();
                case 61442 -> onF15Release();
                case 61443 -> onF16Release();
                case 61444 -> onF17Release();
                case 61445 -> onF18Release();
                case 61446 -> onF19Release();
                case 61447 -> onF20Release();
                case 61448 -> onF21Release();
                case 61449 -> onF22Release();
                case 61450 -> onF23Release();
                case 61451 -> onF24Release();
                case 65312 -> onComposeRelease();
            }
        } else if (id == MouseEvent.MOUSE_PRESSED || id == MouseEvent.MOUSE_RELEASED) {
            Insets  ins = ((JFrame) event.getSource()).getInsets();
            NGVec2i pos = new NGVec2i(((MouseEvent) event).getPoint()).sub(ins.left, ins.top);
            switch (((MouseEvent) event).getButton()) {
                case MouseEvent.BUTTON1 -> { if (id == MouseEvent.MOUSE_PRESSED) onLMBPressed(pos); else onLMBReleased(pos); }
                case MouseEvent.BUTTON3 -> { if (id == MouseEvent.MOUSE_PRESSED) onRMBPressed(pos); else onRMBReleased(pos); }
                case MouseEvent.BUTTON2 -> { if (id == MouseEvent.MOUSE_PRESSED) onMMBPressed(pos); else onMMBReleased(pos); }
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
            onWindowMoved(c.getX(), c.getY());
        } else if (id == ComponentEvent.COMPONENT_RESIZED) {
            Component c = ((ComponentEvent) event).getComponent();
            if (!(c instanceof JFrame && c.isVisible())) return;
            Insets ins = ((JFrame) c).getInsets();
            onWindowResize(c.getWidth() - ins.left, c.getHeight() - ins.top);
        } else if (id == WindowEvent.WINDOW_ICONIFIED) {
            onWindowMinimize();
        } else if (id == WindowEvent.WINDOW_DEICONIFIED) {
            onWindowRestore();
        }
    }

    public void onLMBPressed(NGVec2i pos) { }
    public void onLMBReleased(NGVec2i pos) { }
    public void whileLMBHeld(NGVec2i pos) { }

    public void onRMBPressed(NGVec2i pos) { }
    public void onRMBReleased(NGVec2i pos) { }
    public void whileRMBHeld(NGVec2i pos) { }

    public void onMMBPressed(NGVec2i pos) { }
    public void onMMBReleased(NGVec2i pos) { }
    public void whileMMBHeld(NGVec2i pos) { }

    public void onMouseMoved(NGVec2i pos) { }
    public void onMouseWheel(NGVec2i pos,int direction) { }

    public void onWindowMoved(int x, int y) { }
    public void onWindowResize(int w, int h) { }
    public void onWindowMinimize() { }
    public void onWindowRestore() { }

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

    public void onEscapePress() { }
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
