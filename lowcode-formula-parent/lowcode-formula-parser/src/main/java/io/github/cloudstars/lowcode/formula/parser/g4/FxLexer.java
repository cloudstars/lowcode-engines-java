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
		Plus=1, Minus=2, WS=3, StringLiteral=4, DecimalLiteral=5;
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
			"Plus", "Minus", "WS", "StringLiteral", "DecimalLiteral", "DoubleStringCharacter", 
			"SingleStringCharacter", "DecimalIntegerLiteral", "EscapeSequence", "CharacterEscapeSequence", 
			"SingleEscapeCharacter", "NonEscapeCharacter", "LineContinuation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Plus", "Minus", "WS", "StringLiteral", "DecimalLiteral"
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
		case 3:
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
		"\u0004\u0000\u0005t\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0004\u0002!\b\u0002\u000b\u0002\f\u0002\"\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003)\b\u0003\n\u0003\f\u0003"+
		",\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u00031\b\u0003\n\u0003"+
		"\f\u00034\t\u0003\u0001\u0003\u0003\u00037\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004?\b\u0004"+
		"\n\u0004\f\u0004B\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"G\b\u0004\n\u0004\f\u0004J\t\u0004\u0001\u0004\u0003\u0004M\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005S\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006Y\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007^\b\u0007\n\u0007\f\u0007a\t"+
		"\u0007\u0003\u0007c\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0003\ti\b"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0004\fq\b"+
		"\f\u000b\f\f\fr\u0000\u0000\r\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0000\r\u0000\u000f\u0000\u0011\u0000\u0013\u0000"+
		"\u0015\u0000\u0017\u0000\u0019\u0000\u0001\u0000\t\u0003\u0000\t\n\r\r"+
		"  \u0001\u000009\u0002\u000009__\u0004\u0000\n\n\r\r\"\"\\\\\u0004\u0000"+
		"\n\n\r\r\'\'\\\\\u0001\u000019\t\u0000\"\"\'\'\\\\bbffnnrrttvv\f\u0000"+
		"\n\n\r\r\"\"\'\'09\\\\bbffnnrrtvxx\u0003\u0000\n\n\r\r\u2028\u2029{\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0001\u001b\u0001\u0000\u0000\u0000\u0003\u001d"+
		"\u0001\u0000\u0000\u0000\u0005 \u0001\u0000\u0000\u0000\u00076\u0001\u0000"+
		"\u0000\u0000\tL\u0001\u0000\u0000\u0000\u000bR\u0001\u0000\u0000\u0000"+
		"\rX\u0001\u0000\u0000\u0000\u000fb\u0001\u0000\u0000\u0000\u0011d\u0001"+
		"\u0000\u0000\u0000\u0013h\u0001\u0000\u0000\u0000\u0015j\u0001\u0000\u0000"+
		"\u0000\u0017l\u0001\u0000\u0000\u0000\u0019n\u0001\u0000\u0000\u0000\u001b"+
		"\u001c\u0005+\u0000\u0000\u001c\u0002\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0005-\u0000\u0000\u001e\u0004\u0001\u0000\u0000\u0000\u001f!\u0007\u0000"+
		"\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000"+
		"\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$%\u0006\u0002\u0000\u0000%\u0006\u0001\u0000\u0000\u0000&*\u0005"+
		"\"\u0000\u0000\')\u0003\u000b\u0005\u0000(\'\u0001\u0000\u0000\u0000)"+
		",\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000"+
		"\u0000+-\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000-7\u0005\"\u0000"+
		"\u0000.2\u0005\'\u0000\u0000/1\u0003\r\u0006\u00000/\u0001\u0000\u0000"+
		"\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000"+
		"\u0000\u000035\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000057\u0005"+
		"\'\u0000\u00006&\u0001\u0000\u0000\u00006.\u0001\u0000\u0000\u000078\u0001"+
		"\u0000\u0000\u000089\u0006\u0003\u0001\u00009\b\u0001\u0000\u0000\u0000"+
		":;\u0003\u000f\u0007\u0000;<\u0005.\u0000\u0000<@\u0007\u0001\u0000\u0000"+
		"=?\u0007\u0002\u0000\u0000>=\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000"+
		"\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AM\u0001\u0000"+
		"\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0005.\u0000\u0000DH\u0007\u0001"+
		"\u0000\u0000EG\u0007\u0002\u0000\u0000FE\u0001\u0000\u0000\u0000GJ\u0001"+
		"\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000"+
		"IM\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KM\u0003\u000f\u0007"+
		"\u0000L:\u0001\u0000\u0000\u0000LC\u0001\u0000\u0000\u0000LK\u0001\u0000"+
		"\u0000\u0000M\n\u0001\u0000\u0000\u0000NS\b\u0003\u0000\u0000OP\u0005"+
		"\\\u0000\u0000PS\u0003\u0011\b\u0000QS\u0003\u0019\f\u0000RN\u0001\u0000"+
		"\u0000\u0000RO\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000\u0000S\f\u0001"+
		"\u0000\u0000\u0000TY\b\u0004\u0000\u0000UV\u0005\\\u0000\u0000VY\u0003"+
		"\u0011\b\u0000WY\u0003\u0019\f\u0000XT\u0001\u0000\u0000\u0000XU\u0001"+
		"\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000Y\u000e\u0001\u0000\u0000"+
		"\u0000Zc\u00050\u0000\u0000[_\u0007\u0005\u0000\u0000\\^\u0007\u0002\u0000"+
		"\u0000]\\\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000"+
		"\u0000\u0000_`\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001"+
		"\u0000\u0000\u0000bZ\u0001\u0000\u0000\u0000b[\u0001\u0000\u0000\u0000"+
		"c\u0010\u0001\u0000\u0000\u0000de\u0003\u0013\t\u0000e\u0012\u0001\u0000"+
		"\u0000\u0000fi\u0003\u0015\n\u0000gi\u0003\u0017\u000b\u0000hf\u0001\u0000"+
		"\u0000\u0000hg\u0001\u0000\u0000\u0000i\u0014\u0001\u0000\u0000\u0000"+
		"jk\u0007\u0006\u0000\u0000k\u0016\u0001\u0000\u0000\u0000lm\b\u0007\u0000"+
		"\u0000m\u0018\u0001\u0000\u0000\u0000np\u0005\\\u0000\u0000oq\u0007\b"+
		"\u0000\u0000po\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rp\u0001"+
		"\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000s\u001a\u0001\u0000\u0000"+
		"\u0000\u000e\u0000\"*26@HLRX_bhr\u0002\u0006\u0000\u0000\u0001\u0003\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}