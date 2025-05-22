// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/g4/FxLexer.g4 by ANTLR 4.13.1
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
public class FxLexer extends FxLexerBase {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Plus=1, Minus=2, Multiply=3, Divide=4, Modulus=5, WS=6, StringLiteral=7, 
		DecimalLiteral=8;
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
			"Plus", "Minus", "Multiply", "Divide", "Modulus", "WS", "StringLiteral", 
			"DecimalLiteral", "DoubleStringCharacter", "SingleStringCharacter", "DecimalIntegerLiteral", 
			"EscapeSequence", "CharacterEscapeSequence", "SingleEscapeCharacter", 
			"NonEscapeCharacter", "LineContinuation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Plus", "Minus", "Multiply", "Divide", "Modulus", "WS", "StringLiteral", 
			"DecimalLiteral"
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


	public FxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FxLexer.g4"; }

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
		case 6:
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
		"\u0004\u0000\b\u0080\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0004\u0005-\b\u0005\u000b\u0005\f\u0005.\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0005\u00065\b\u0006\n\u0006\f\u00068\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006=\b\u0006\n\u0006\f\u0006"+
		"@\t\u0006\u0001\u0006\u0003\u0006C\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007K\b\u0007\n\u0007"+
		"\f\u0007N\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007S\b\u0007"+
		"\n\u0007\f\u0007V\t\u0007\u0001\u0007\u0003\u0007Y\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b_\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\te\b\t\u0001\n\u0001\n\u0001\n\u0005\nj\b\n\n\n\f\nm\t\n\u0003\no\b\n"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0003\fu\b\f\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0004\u000f}\b\u000f"+
		"\u000b\u000f\f\u000f~\u0000\u0000\u0010\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\u0000\u0013"+
		"\u0000\u0015\u0000\u0017\u0000\u0019\u0000\u001b\u0000\u001d\u0000\u001f"+
		"\u0000\u0001\u0000\t\u0003\u0000\t\n\r\r  \u0001\u000009\u0002\u00000"+
		"9__\u0004\u0000\n\n\r\r\"\"\\\\\u0004\u0000\n\n\r\r\'\'\\\\\u0001\u0000"+
		"19\t\u0000\"\"\'\'\\\\bbffnnrrttvv\f\u0000\n\n\r\r\"\"\'\'09\\\\bbffn"+
		"nrrtvxx\u0003\u0000\n\n\r\r\u2028\u2029\u0087\u0000\u0001\u0001\u0000"+
		"\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0001!\u0001\u0000\u0000\u0000\u0003"+
		"#\u0001\u0000\u0000\u0000\u0005%\u0001\u0000\u0000\u0000\u0007\'\u0001"+
		"\u0000\u0000\u0000\t)\u0001\u0000\u0000\u0000\u000b,\u0001\u0000\u0000"+
		"\u0000\rB\u0001\u0000\u0000\u0000\u000fX\u0001\u0000\u0000\u0000\u0011"+
		"^\u0001\u0000\u0000\u0000\u0013d\u0001\u0000\u0000\u0000\u0015n\u0001"+
		"\u0000\u0000\u0000\u0017p\u0001\u0000\u0000\u0000\u0019t\u0001\u0000\u0000"+
		"\u0000\u001bv\u0001\u0000\u0000\u0000\u001dx\u0001\u0000\u0000\u0000\u001f"+
		"z\u0001\u0000\u0000\u0000!\"\u0005+\u0000\u0000\"\u0002\u0001\u0000\u0000"+
		"\u0000#$\u0005-\u0000\u0000$\u0004\u0001\u0000\u0000\u0000%&\u0005*\u0000"+
		"\u0000&\u0006\u0001\u0000\u0000\u0000\'(\u0005/\u0000\u0000(\b\u0001\u0000"+
		"\u0000\u0000)*\u0005%\u0000\u0000*\n\u0001\u0000\u0000\u0000+-\u0007\u0000"+
		"\u0000\u0000,+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.,\u0001"+
		"\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u0000"+
		"01\u0006\u0005\u0000\u00001\f\u0001\u0000\u0000\u000026\u0005\"\u0000"+
		"\u000035\u0003\u0011\b\u000043\u0001\u0000\u0000\u000058\u0001\u0000\u0000"+
		"\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000079\u0001\u0000"+
		"\u0000\u000086\u0001\u0000\u0000\u00009C\u0005\"\u0000\u0000:>\u0005\'"+
		"\u0000\u0000;=\u0003\u0013\t\u0000<;\u0001\u0000\u0000\u0000=@\u0001\u0000"+
		"\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AC\u0005\'\u0000\u0000B2\u0001"+
		"\u0000\u0000\u0000B:\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000"+
		"DE\u0006\u0006\u0001\u0000E\u000e\u0001\u0000\u0000\u0000FG\u0003\u0015"+
		"\n\u0000GH\u0005.\u0000\u0000HL\u0007\u0001\u0000\u0000IK\u0007\u0002"+
		"\u0000\u0000JI\u0001\u0000\u0000\u0000KN\u0001\u0000\u0000\u0000LJ\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MY\u0001\u0000\u0000\u0000"+
		"NL\u0001\u0000\u0000\u0000OP\u0005.\u0000\u0000PT\u0007\u0001\u0000\u0000"+
		"QS\u0007\u0002\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000"+
		"\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UY\u0001\u0000"+
		"\u0000\u0000VT\u0001\u0000\u0000\u0000WY\u0003\u0015\n\u0000XF\u0001\u0000"+
		"\u0000\u0000XO\u0001\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000Y\u0010"+
		"\u0001\u0000\u0000\u0000Z_\b\u0003\u0000\u0000[\\\u0005\\\u0000\u0000"+
		"\\_\u0003\u0017\u000b\u0000]_\u0003\u001f\u000f\u0000^Z\u0001\u0000\u0000"+
		"\u0000^[\u0001\u0000\u0000\u0000^]\u0001\u0000\u0000\u0000_\u0012\u0001"+
		"\u0000\u0000\u0000`e\b\u0004\u0000\u0000ab\u0005\\\u0000\u0000be\u0003"+
		"\u0017\u000b\u0000ce\u0003\u001f\u000f\u0000d`\u0001\u0000\u0000\u0000"+
		"da\u0001\u0000\u0000\u0000dc\u0001\u0000\u0000\u0000e\u0014\u0001\u0000"+
		"\u0000\u0000fo\u00050\u0000\u0000gk\u0007\u0005\u0000\u0000hj\u0007\u0002"+
		"\u0000\u0000ih\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000"+
		"mk\u0001\u0000\u0000\u0000nf\u0001\u0000\u0000\u0000ng\u0001\u0000\u0000"+
		"\u0000o\u0016\u0001\u0000\u0000\u0000pq\u0003\u0019\f\u0000q\u0018\u0001"+
		"\u0000\u0000\u0000ru\u0003\u001b\r\u0000su\u0003\u001d\u000e\u0000tr\u0001"+
		"\u0000\u0000\u0000ts\u0001\u0000\u0000\u0000u\u001a\u0001\u0000\u0000"+
		"\u0000vw\u0007\u0006\u0000\u0000w\u001c\u0001\u0000\u0000\u0000xy\b\u0007"+
		"\u0000\u0000y\u001e\u0001\u0000\u0000\u0000z|\u0005\\\u0000\u0000{}\u0007"+
		"\b\u0000\u0000|{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001"+
		"\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f \u0001\u0000\u0000"+
		"\u0000\u000e\u0000.6>BLTX^dknt~\u0002\u0006\u0000\u0000\u0001\u0006\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}