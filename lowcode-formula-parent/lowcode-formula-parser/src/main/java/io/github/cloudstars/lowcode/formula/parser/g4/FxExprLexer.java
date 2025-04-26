// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/resources/g4/FxExprLexer.g4 by ANTLR 4.13.1
package io.github.cloudstars.lowcode.formula.parser.g4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FxExprLexer extends FxExprLexerBase {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		StringLiteral=1;
	public static final int
		ERROR=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN", "ERROR"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"StringLiteral", "DoubleStringCharacter", "SingleStringCharacter", "EscapeSequence", 
			"CharacterEscapeSequence", "SingleEscapeCharacter", "NonEscapeCharacter", 
			"LineContinuation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "StringLiteral"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FxExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FxExprLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			StringLiteral_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void StringLiteral_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			this.ProcessStringLiteral();
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\u0001A\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0001\u0000\u0001\u0000\u0005\u0000\u0014\b\u0000\n\u0000"+
		"\f\u0000\u0017\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"\u001c\b\u0000\n\u0000\f\u0000\u001f\t\u0000\u0001\u0000\u0003\u0000\""+
		"\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001*\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u00020\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0003\u00046\b\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0004\u0007>\b\u0007\u000b\u0007\f\u0007"+
		"?\u0000\u0000\b\u0001\u0001\u0003\u0000\u0005\u0000\u0007\u0000\t\u0000"+
		"\u000b\u0000\r\u0000\u000f\u0000\u0001\u0000\u0005\u0004\u0000\n\n\r\r"+
		"\"\"\\\\\u0004\u0000\n\n\r\r\'\'\\\\\t\u0000\"\"\'\'\\\\bbffnnrrttvv\f"+
		"\u0000\n\n\r\r\"\"\'\'09\\\\bbffnnrrtvxx\u0003\u0000\n\n\r\r\u2028\u2029"+
		"B\u0000\u0001\u0001\u0000\u0000\u0000\u0001!\u0001\u0000\u0000\u0000\u0003"+
		")\u0001\u0000\u0000\u0000\u0005/\u0001\u0000\u0000\u0000\u00071\u0001"+
		"\u0000\u0000\u0000\t5\u0001\u0000\u0000\u0000\u000b7\u0001\u0000\u0000"+
		"\u0000\r9\u0001\u0000\u0000\u0000\u000f;\u0001\u0000\u0000\u0000\u0011"+
		"\u0015\u0005\"\u0000\u0000\u0012\u0014\u0003\u0003\u0001\u0000\u0013\u0012"+
		"\u0001\u0000\u0000\u0000\u0014\u0017\u0001\u0000\u0000\u0000\u0015\u0013"+
		"\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0018"+
		"\u0001\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0018\"\u0005"+
		"\"\u0000\u0000\u0019\u001d\u0005\'\u0000\u0000\u001a\u001c\u0003\u0005"+
		"\u0002\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u001f\u0001\u0000"+
		"\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000"+
		"\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000"+
		"\u0000 \"\u0005\'\u0000\u0000!\u0011\u0001\u0000\u0000\u0000!\u0019\u0001"+
		"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#$\u0006\u0000\u0000\u0000"+
		"$\u0002\u0001\u0000\u0000\u0000%*\b\u0000\u0000\u0000&\'\u0005\\\u0000"+
		"\u0000\'*\u0003\u0007\u0003\u0000(*\u0003\u000f\u0007\u0000)%\u0001\u0000"+
		"\u0000\u0000)&\u0001\u0000\u0000\u0000)(\u0001\u0000\u0000\u0000*\u0004"+
		"\u0001\u0000\u0000\u0000+0\b\u0001\u0000\u0000,-\u0005\\\u0000\u0000-"+
		"0\u0003\u0007\u0003\u0000.0\u0003\u000f\u0007\u0000/+\u0001\u0000\u0000"+
		"\u0000/,\u0001\u0000\u0000\u0000/.\u0001\u0000\u0000\u00000\u0006\u0001"+
		"\u0000\u0000\u000012\u0003\t\u0004\u00002\b\u0001\u0000\u0000\u000036"+
		"\u0003\u000b\u0005\u000046\u0003\r\u0006\u000053\u0001\u0000\u0000\u0000"+
		"54\u0001\u0000\u0000\u00006\n\u0001\u0000\u0000\u000078\u0007\u0002\u0000"+
		"\u00008\f\u0001\u0000\u0000\u00009:\b\u0003\u0000\u0000:\u000e\u0001\u0000"+
		"\u0000\u0000;=\u0005\\\u0000\u0000<>\u0007\u0004\u0000\u0000=<\u0001\u0000"+
		"\u0000\u0000>?\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@\u0010\u0001\u0000\u0000\u0000\b\u0000\u0015\u001d"+
		"!)/5?\u0001\u0001\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}