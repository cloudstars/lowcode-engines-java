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
		OpenParen=1, CloseParen=2, Plus=3, Minus=4, Multiply=5, Divide=6, Modulus=7, 
		WS=8, StringLiteral=9, DecimalLiteral=10;
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
			"OpenParen", "CloseParen", "Plus", "Minus", "Multiply", "Divide", "Modulus", 
			"WS", "StringLiteral", "DecimalLiteral", "DoubleStringCharacter", "SingleStringCharacter", 
			"DecimalIntegerLiteral", "EscapeSequence", "CharacterEscapeSequence", 
			"SingleEscapeCharacter", "NonEscapeCharacter", "LineContinuation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OpenParen", "CloseParen", "Plus", "Minus", "Multiply", "Divide", 
			"Modulus", "WS", "StringLiteral", "DecimalLiteral"
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
		case 8:
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
		"\u0004\u0000\n\u0088\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0004\u00075\b\u0007\u000b\u0007\f\u0007"+
		"6\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0005\b=\b\b\n\b\f\b@\t\b\u0001"+
		"\b\u0001\b\u0001\b\u0005\bE\b\b\n\b\f\bH\t\b\u0001\b\u0003\bK\b\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\tS\b\t\n\t\f\tV\t\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\t[\b\t\n\t\f\t^\t\t\u0001\t\u0003\ta\b\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\ng\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000bm\b\u000b\u0001\f\u0001\f\u0001\f\u0005"+
		"\fr\b\f\n\f\f\fu\t\f\u0003\fw\b\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0003\u000e}\b\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u0085\b\u0011\u000b\u0011\f\u0011"+
		"\u0086\u0000\u0000\u0012\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u0000\u0017"+
		"\u0000\u0019\u0000\u001b\u0000\u001d\u0000\u001f\u0000!\u0000#\u0000\u0001"+
		"\u0000\t\u0003\u0000\t\n\r\r  \u0001\u000009\u0002\u000009__\u0004\u0000"+
		"\n\n\r\r\"\"\\\\\u0004\u0000\n\n\r\r\'\'\\\\\u0001\u000019\t\u0000\"\""+
		"\'\'\\\\bbffnnrrttvv\f\u0000\n\n\r\r\"\"\'\'09\\\\bbffnnrrtvxx\u0003\u0000"+
		"\n\n\r\r\u2028\u2029\u008f\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0001%\u0001\u0000\u0000\u0000\u0003\'\u0001\u0000\u0000"+
		"\u0000\u0005)\u0001\u0000\u0000\u0000\u0007+\u0001\u0000\u0000\u0000\t"+
		"-\u0001\u0000\u0000\u0000\u000b/\u0001\u0000\u0000\u0000\r1\u0001\u0000"+
		"\u0000\u0000\u000f4\u0001\u0000\u0000\u0000\u0011J\u0001\u0000\u0000\u0000"+
		"\u0013`\u0001\u0000\u0000\u0000\u0015f\u0001\u0000\u0000\u0000\u0017l"+
		"\u0001\u0000\u0000\u0000\u0019v\u0001\u0000\u0000\u0000\u001bx\u0001\u0000"+
		"\u0000\u0000\u001d|\u0001\u0000\u0000\u0000\u001f~\u0001\u0000\u0000\u0000"+
		"!\u0080\u0001\u0000\u0000\u0000#\u0082\u0001\u0000\u0000\u0000%&\u0005"+
		"(\u0000\u0000&\u0002\u0001\u0000\u0000\u0000\'(\u0005)\u0000\u0000(\u0004"+
		"\u0001\u0000\u0000\u0000)*\u0005+\u0000\u0000*\u0006\u0001\u0000\u0000"+
		"\u0000+,\u0005-\u0000\u0000,\b\u0001\u0000\u0000\u0000-.\u0005*\u0000"+
		"\u0000.\n\u0001\u0000\u0000\u0000/0\u0005/\u0000\u00000\f\u0001\u0000"+
		"\u0000\u000012\u0005%\u0000\u00002\u000e\u0001\u0000\u0000\u000035\u0007"+
		"\u0000\u0000\u000043\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u0000"+
		"64\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000078\u0001\u0000\u0000"+
		"\u000089\u0006\u0007\u0000\u00009\u0010\u0001\u0000\u0000\u0000:>\u0005"+
		"\"\u0000\u0000;=\u0003\u0015\n\u0000<;\u0001\u0000\u0000\u0000=@\u0001"+
		"\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000"+
		"?A\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AK\u0005\"\u0000\u0000"+
		"BF\u0005\'\u0000\u0000CE\u0003\u0017\u000b\u0000DC\u0001\u0000\u0000\u0000"+
		"EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000"+
		"\u0000GI\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IK\u0005\'\u0000"+
		"\u0000J:\u0001\u0000\u0000\u0000JB\u0001\u0000\u0000\u0000KL\u0001\u0000"+
		"\u0000\u0000LM\u0006\b\u0001\u0000M\u0012\u0001\u0000\u0000\u0000NO\u0003"+
		"\u0019\f\u0000OP\u0005.\u0000\u0000PT\u0007\u0001\u0000\u0000QS\u0007"+
		"\u0002\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000\u0000"+
		"TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000Ua\u0001\u0000\u0000"+
		"\u0000VT\u0001\u0000\u0000\u0000WX\u0005.\u0000\u0000X\\\u0007\u0001\u0000"+
		"\u0000Y[\u0007\u0002\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]a\u0001"+
		"\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_a\u0003\u0019\f\u0000`N"+
		"\u0001\u0000\u0000\u0000`W\u0001\u0000\u0000\u0000`_\u0001\u0000\u0000"+
		"\u0000a\u0014\u0001\u0000\u0000\u0000bg\b\u0003\u0000\u0000cd\u0005\\"+
		"\u0000\u0000dg\u0003\u001b\r\u0000eg\u0003#\u0011\u0000fb\u0001\u0000"+
		"\u0000\u0000fc\u0001\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000g\u0016"+
		"\u0001\u0000\u0000\u0000hm\b\u0004\u0000\u0000ij\u0005\\\u0000\u0000j"+
		"m\u0003\u001b\r\u0000km\u0003#\u0011\u0000lh\u0001\u0000\u0000\u0000l"+
		"i\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000m\u0018\u0001\u0000"+
		"\u0000\u0000nw\u00050\u0000\u0000os\u0007\u0005\u0000\u0000pr\u0007\u0002"+
		"\u0000\u0000qp\u0001\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001"+
		"\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000vn\u0001\u0000\u0000\u0000vo\u0001\u0000\u0000"+
		"\u0000w\u001a\u0001\u0000\u0000\u0000xy\u0003\u001d\u000e\u0000y\u001c"+
		"\u0001\u0000\u0000\u0000z}\u0003\u001f\u000f\u0000{}\u0003!\u0010\u0000"+
		"|z\u0001\u0000\u0000\u0000|{\u0001\u0000\u0000\u0000}\u001e\u0001\u0000"+
		"\u0000\u0000~\u007f\u0007\u0006\u0000\u0000\u007f \u0001\u0000\u0000\u0000"+
		"\u0080\u0081\b\u0007\u0000\u0000\u0081\"\u0001\u0000\u0000\u0000\u0082"+
		"\u0084\u0005\\\u0000\u0000\u0083\u0085\u0007\b\u0000\u0000\u0084\u0083"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0084"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087$\u0001"+
		"\u0000\u0000\u0000\u000e\u00006>FJT\\`flsv|\u0086\u0002\u0006\u0000\u0000"+
		"\u0001\b\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}