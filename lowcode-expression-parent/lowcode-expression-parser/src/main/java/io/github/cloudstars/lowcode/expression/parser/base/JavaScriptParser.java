// Generated from /Users/yuefengwu/code/antrl4-practice/src/main/resources/JavaScriptParser.g4 by ANTLR 4.13.1
package io.github.cloudstars.lowcode.expression.parser.base;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JavaScriptParser extends JavaScriptParserBase {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HashBangLine=1, MultiLineComment=2, SingleLineComment=3, RegularExpressionLiteral=4,
		OpenBracket=5, CloseBracket=6, OpenParen=7, CloseParen=8, OpenBrace=9,
		TemplateCloseBrace=10, CloseBrace=11, SemiColon=12, Comma=13, Assign=14,
		QuestionMark=15, QuestionMarkDot=16, Colon=17, Ellipsis=18, Dot=19, PlusPlus=20,
		MinusMinus=21, Plus=22, Minus=23, BitNot=24, Not=25, Multiply=26, Divide=27,
		Modulus=28, Power=29, NullCoalesce=30, Hashtag=31, RightShiftArithmetic=32,
		LeftShiftArithmetic=33, RightShiftLogical=34, LessThan=35, MoreThan=36,
		LessThanEquals=37, GreaterThanEquals=38, Equals_=39, NotEquals=40, IdentityEquals=41,
		IdentityNotEquals=42, BitAnd=43, BitXOr=44, BitOr=45, And=46, Or=47, MultiplyAssign=48,
		DivideAssign=49, ModulusAssign=50, PlusAssign=51, MinusAssign=52, LeftShiftArithmeticAssign=53,
		RightShiftArithmeticAssign=54, RightShiftLogicalAssign=55, BitAndAssign=56,
		BitXorAssign=57, BitOrAssign=58, PowerAssign=59, NullishCoalescingAssign=60,
		ARROW=61, NullLiteral=62, BooleanLiteral=63, DecimalLiteral=64, HexIntegerLiteral=65,
		OctalIntegerLiteral=66, OctalIntegerLiteral2=67, BinaryIntegerLiteral=68,
		BigHexIntegerLiteral=69, BigOctalIntegerLiteral=70, BigBinaryIntegerLiteral=71,
		BigDecimalIntegerLiteral=72, Break=73, Do=74, Instanceof=75, Typeof=76,
		Case=77, Else=78, ELSE_IF=79, New=80, Var=81, Let=82, Catch=83, Finally=84,
		Return=85, Void=86, Continue=87, For=88, Switch=89, While=90, Debugger=91,
		Function_=92, This=93, With=94, Default=95, If=96, Throw=97, Delete=98,
		In=99, Try=100, As=101, From=102, Of=103, Yield=104, YieldStar=105, Class=106,
		Enum=107, Extends=108, Super=109, Const=110, Export=111, Import=112, Async=113,
		Await=114, Implements=115, StrictLet=116, NonStrictLet=117, Private=118,
		Public=119, Interface=120, Package=121, Protected=122, Static=123, Identifier=124,
		StringLiteral=125, BackTick=126, WhiteSpaces=127, LineTerminator=128,
		HtmlComment=129, CDataComment=130, UnexpectedCharacter=131, TemplateStringStartExpression=132,
		TemplateStringAtom=133, SPACES=134, NL=135;
	public static final int
		RULE_program = 0, RULE_sourceElement = 1, RULE_assignmentStatement = 2,
		RULE_statement = 3, RULE_objectLiteralStatement = 4, RULE_block = 5, RULE_statementList = 6,
		RULE_importStatement = 7, RULE_importFromBlock = 8, RULE_importModuleItems = 9,
		RULE_importAliasName = 10, RULE_moduleExportName = 11, RULE_importedBinding = 12,
		RULE_importDefault = 13, RULE_importNamespace = 14, RULE_importFrom = 15,
		RULE_aliasName = 16, RULE_exportStatement = 17, RULE_exportFromBlock = 18,
		RULE_exportModuleItems = 19, RULE_exportAliasName = 20, RULE_declaration = 21,
		RULE_variableStatement = 22, RULE_variableDeclarationList = 23, RULE_variableDeclaration = 24,
		RULE_emptyStatement_ = 25, RULE_expressionStatement = 26, RULE_ifStatement = 27,
		RULE_elseStatement = 28, RULE_elseIfStatement = 29, RULE_iterationStatement = 30,
		RULE_leftParenthesis = 31, RULE_varModifier = 32, RULE_continueStatement = 33,
		RULE_breakStatement = 34, RULE_returnStatement = 35, RULE_yieldStatement = 36,
		RULE_withStatement = 37, RULE_switchStatement = 38, RULE_caseBlock = 39,
		RULE_caseClauses = 40, RULE_caseClause = 41, RULE_defaultClause = 42,
		RULE_labelledStatement = 43, RULE_throwStatement = 44, RULE_tryStatement = 45,
		RULE_catchProduction = 46, RULE_finallyProduction = 47, RULE_debuggerStatement = 48,
		RULE_functionDeclaration = 49, RULE_classDeclaration = 50, RULE_classTail = 51,
		RULE_classElement = 52, RULE_methodDefinition = 53, RULE_fieldDefinition = 54,
		RULE_classElementName = 55, RULE_privateIdentifier = 56, RULE_formalParameterList = 57,
		RULE_formalParameterArg = 58, RULE_lastFormalParameterArg = 59, RULE_functionBody = 60,
		RULE_sourceElements = 61, RULE_arrayLiteral = 62, RULE_elementList = 63,
		RULE_arrayElement = 64, RULE_propertyAssignment = 65, RULE_propertyName = 66,
		RULE_arguments = 67, RULE_argument = 68, RULE_expressionSequence = 69,
		RULE_assignSingleExpression = 70, RULE_singleExpression = 71, RULE_initializer = 72,
		RULE_assignable = 73, RULE_objectLiteral = 74, RULE_anonymousFunction = 75,
		RULE_arrowFunctionParameters = 76, RULE_arrowFunctionBody = 77, RULE_assignmentOperator = 78,
		RULE_literal = 79, RULE_templateStringLiteral = 80, RULE_templateStringAtom = 81,
		RULE_numericLiteral = 82, RULE_bigintLiteral = 83, RULE_getter = 84, RULE_setter = 85,
		RULE_identifierName = 86, RULE_identifier = 87, RULE_reservedWord = 88,
		RULE_keyword = 89, RULE_let_ = 90, RULE_eos = 91;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "sourceElement", "assignmentStatement", "statement", "objectLiteralStatement",
			"block", "statementList", "importStatement", "importFromBlock", "importModuleItems",
			"importAliasName", "moduleExportName", "importedBinding", "importDefault",
			"importNamespace", "importFrom", "aliasName", "exportStatement", "exportFromBlock",
			"exportModuleItems", "exportAliasName", "declaration", "variableStatement",
			"variableDeclarationList", "variableDeclaration", "emptyStatement_",
			"expressionStatement", "ifStatement", "elseStatement", "elseIfStatement",
			"iterationStatement", "leftParenthesis", "varModifier", "continueStatement",
			"breakStatement", "returnStatement", "yieldStatement", "withStatement",
			"switchStatement", "caseBlock", "caseClauses", "caseClause", "defaultClause",
			"labelledStatement", "throwStatement", "tryStatement", "catchProduction",
			"finallyProduction", "debuggerStatement", "functionDeclaration", "classDeclaration",
			"classTail", "classElement", "methodDefinition", "fieldDefinition", "classElementName",
			"privateIdentifier", "formalParameterList", "formalParameterArg", "lastFormalParameterArg",
			"functionBody", "sourceElements", "arrayLiteral", "elementList", "arrayElement",
			"propertyAssignment", "propertyName", "arguments", "argument", "expressionSequence",
			"assignSingleExpression", "singleExpression", "initializer", "assignable",
			"objectLiteral", "anonymousFunction", "arrowFunctionParameters", "arrowFunctionBody",
			"assignmentOperator", "literal", "templateStringLiteral", "templateStringAtom",
			"numericLiteral", "bigintLiteral", "getter", "setter", "identifierName",
			"identifier", "reservedWord", "keyword", "let_", "eos"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'['", "']'", "'('", "')'", "'{'", null,
			"'}'", "';'", "','", "'='", "'?'", "'?.'", "':'", "'...'", "'.'", "'++'",
			"'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", "'**'", "'??'",
			"'#'", "'>>'", "'<<'", "'>>>'", "'<'", "'>'", "'<='", "'>='", "'=='",
			"'!='", "'==='", "'!=='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'*='",
			"'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'>>>='", "'&='", "'^='",
			"'|='", "'**='", "'??='", "'=>'", "'null'", null, null, null, null, null,
			null, null, null, null, null, "'break'", "'do'", "'instanceof'", "'typeof'",
			"'case'", "'else'", null, "'new'", "'var'", null, "'catch'", "'finally'",
			"'return'", "'void'", "'continue'", "'for'", "'switch'", "'while'", "'debugger'",
			"'function'", "'this'", "'with'", "'default'", "'if'", "'throw'", "'delete'",
			"'in'", "'try'", "'as'", "'from'", "'of'", "'yield'", "'yield*'", "'class'",
			"'enum'", "'extends'", "'super'", "'const'", "'export'", "'import'",
			"'async'", "'await'", "'implements'", null, null, "'private'", "'public'",
			"'interface'", "'package'", "'protected'", "'static'", null, null, null,
			null, null, null, null, null, "'${'", null, null, "'\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HashBangLine", "MultiLineComment", "SingleLineComment", "RegularExpressionLiteral",
			"OpenBracket", "CloseBracket", "OpenParen", "CloseParen", "OpenBrace",
			"TemplateCloseBrace", "CloseBrace", "SemiColon", "Comma", "Assign", "QuestionMark",
			"QuestionMarkDot", "Colon", "Ellipsis", "Dot", "PlusPlus", "MinusMinus",
			"Plus", "Minus", "BitNot", "Not", "Multiply", "Divide", "Modulus", "Power",
			"NullCoalesce", "Hashtag", "RightShiftArithmetic", "LeftShiftArithmetic",
			"RightShiftLogical", "LessThan", "MoreThan", "LessThanEquals", "GreaterThanEquals",
			"Equals_", "NotEquals", "IdentityEquals", "IdentityNotEquals", "BitAnd",
			"BitXOr", "BitOr", "And", "Or", "MultiplyAssign", "DivideAssign", "ModulusAssign",
			"PlusAssign", "MinusAssign", "LeftShiftArithmeticAssign", "RightShiftArithmeticAssign",
			"RightShiftLogicalAssign", "BitAndAssign", "BitXorAssign", "BitOrAssign",
			"PowerAssign", "NullishCoalescingAssign", "ARROW", "NullLiteral", "BooleanLiteral",
			"DecimalLiteral", "HexIntegerLiteral", "OctalIntegerLiteral", "OctalIntegerLiteral2",
			"BinaryIntegerLiteral", "BigHexIntegerLiteral", "BigOctalIntegerLiteral",
			"BigBinaryIntegerLiteral", "BigDecimalIntegerLiteral", "Break", "Do",
			"Instanceof", "Typeof", "Case", "Else", "ELSE_IF", "New", "Var", "Let",
			"Catch", "Finally", "Return", "Void", "Continue", "For", "Switch", "While",
			"Debugger", "Function_", "This", "With", "Default", "If", "Throw", "Delete",
			"In", "Try", "As", "From", "Of", "Yield", "YieldStar", "Class", "Enum",
			"Extends", "Super", "Const", "Export", "Import", "Async", "Await", "Implements",
			"StrictLet", "NonStrictLet", "Private", "Public", "Interface", "Package",
			"Protected", "Static", "Identifier", "StringLiteral", "BackTick", "WhiteSpaces",
			"LineTerminator", "HtmlComment", "CDataComment", "UnexpectedCharacter",
			"TemplateStringStartExpression", "TemplateStringAtom", "SPACES", "NL"
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

	@Override
	public String getGrammarFileName() { return "JavaScriptParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(JavaScriptParser.EOF, 0); }
		public TerminalNode HashBangLine() { return getToken(JavaScriptParser.HashBangLine, 0); }
		public SourceElementsContext sourceElements() {
			return getRuleContext(SourceElementsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(184);
				match(HashBangLine);
				}
				break;
			}
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(187);
				sourceElements();
				}
				break;
			}
			setState(190);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceElementContext extends ParserRuleContext {
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public List<TerminalNode> LineTerminator() { return getTokens(JavaScriptParser.LineTerminator); }
		public TerminalNode LineTerminator(int i) {
			return getToken(JavaScriptParser.LineTerminator, i);
		}
		public SourceElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterSourceElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitSourceElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitSourceElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceElementContext sourceElement() throws RecognitionException {
		SourceElementContext _localctx = new SourceElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sourceElement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			assignmentStatement();
			setState(196);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(193);
					match(LineTerminator);
					}
					}
				}
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ObjectLiteralStatementContext objectLiteralStatement() {
			return getRuleContext(ObjectLiteralStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public VariableStatementContext variableStatement() {
			return getRuleContext(VariableStatementContext.class,0);
		}
		public ImportStatementContext importStatement() {
			return getRuleContext(ImportStatementContext.class,0);
		}
		public ExportStatementContext exportStatement() {
			return getRuleContext(ExportStatementContext.class,0);
		}
		public EmptyStatement_Context emptyStatement_() {
			return getRuleContext(EmptyStatement_Context.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public YieldStatementContext yieldStatement() {
			return getRuleContext(YieldStatementContext.class,0);
		}
		public WithStatementContext withStatement() {
			return getRuleContext(WithStatementContext.class,0);
		}
		public LabelledStatementContext labelledStatement() {
			return getRuleContext(LabelledStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public TryStatementContext tryStatement() {
			return getRuleContext(TryStatementContext.class,0);
		}
		public DebuggerStatementContext debuggerStatement() {
			return getRuleContext(DebuggerStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				objectLiteralStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				expressionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				variableStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				importStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(206);
				exportStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(207);
				emptyStatement_();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(208);
				classDeclaration();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(209);
				ifStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(210);
				functionDeclaration();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(211);
				iterationStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(212);
				continueStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(213);
				breakStatement();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(214);
				returnStatement();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(215);
				yieldStatement();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(216);
				withStatement();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(217);
				labelledStatement();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(218);
				switchStatement();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(219);
				throwStatement();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(220);
				tryStatement();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(221);
				debuggerStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectLiteralStatementContext extends ParserRuleContext {
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ObjectLiteralStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteralStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterObjectLiteralStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitObjectLiteralStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitObjectLiteralStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectLiteralStatementContext objectLiteralStatement() throws RecognitionException {
		ObjectLiteralStatementContext _localctx = new ObjectLiteralStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_objectLiteralStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			objectLiteral();
			setState(225);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(OpenBrace);
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(228);
				statementList();
				}
				break;
			}
			setState(231);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementListContext extends ParserRuleContext {
		public List<AssignmentStatementContext> assignmentStatement() {
			return getRuleContexts(AssignmentStatementContext.class);
		}
		public AssignmentStatementContext assignmentStatement(int i) {
			return getRuleContext(AssignmentStatementContext.class,i);
		}
		public StatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitStatementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementListContext statementList() throws RecognitionException {
		StatementListContext _localctx = new StatementListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(233);
					assignmentStatement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(236);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportStatementContext extends ParserRuleContext {
		public TerminalNode Import() { return getToken(JavaScriptParser.Import, 0); }
		public ImportFromBlockContext importFromBlock() {
			return getRuleContext(ImportFromBlockContext.class,0);
		}
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_importStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(Import);
			setState(239);
			importFromBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportFromBlockContext extends ParserRuleContext {
		public ImportFromContext importFrom() {
			return getRuleContext(ImportFromContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ImportNamespaceContext importNamespace() {
			return getRuleContext(ImportNamespaceContext.class,0);
		}
		public ImportModuleItemsContext importModuleItems() {
			return getRuleContext(ImportModuleItemsContext.class,0);
		}
		public ImportDefaultContext importDefault() {
			return getRuleContext(ImportDefaultContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(JavaScriptParser.StringLiteral, 0); }
		public ImportFromBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importFromBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportFromBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportFromBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportFromBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportFromBlockContext importFromBlock() throws RecognitionException {
		ImportFromBlockContext _localctx = new ImportFromBlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_importFromBlock);
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBrace:
			case Multiply:
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(241);
					importDefault();
					}
					break;
				}
				setState(246);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Multiply:
				case NullLiteral:
				case BooleanLiteral:
				case Break:
				case Do:
				case Instanceof:
				case Typeof:
				case Case:
				case Else:
				case New:
				case Var:
				case Catch:
				case Finally:
				case Return:
				case Void:
				case Continue:
				case For:
				case Switch:
				case While:
				case Debugger:
				case Function_:
				case This:
				case With:
				case Default:
				case If:
				case Throw:
				case Delete:
				case In:
				case Try:
				case As:
				case From:
				case Of:
				case Yield:
				case YieldStar:
				case Class:
				case Enum:
				case Extends:
				case Super:
				case Const:
				case Export:
				case Import:
				case Async:
				case Await:
				case Implements:
				case StrictLet:
				case NonStrictLet:
				case Private:
				case Public:
				case Interface:
				case Package:
				case Protected:
				case Static:
				case Identifier:
					{
					setState(244);
					importNamespace();
					}
					break;
				case OpenBrace:
					{
					setState(245);
					importModuleItems();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(248);
				importFrom();
				setState(249);
				eos();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				match(StringLiteral);
				setState(252);
				eos();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportModuleItemsContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public List<ImportAliasNameContext> importAliasName() {
			return getRuleContexts(ImportAliasNameContext.class);
		}
		public ImportAliasNameContext importAliasName(int i) {
			return getRuleContext(ImportAliasNameContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public ImportModuleItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importModuleItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportModuleItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportModuleItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportModuleItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportModuleItemsContext importModuleItems() throws RecognitionException {
		ImportModuleItemsContext _localctx = new ImportModuleItemsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_importModuleItems);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(OpenBrace);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(256);
					importAliasName();
					setState(257);
					match(Comma);
					}
					}
				}
				setState(263);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & -1181693L) != 0)) {
				{
				setState(264);
				importAliasName();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(265);
					match(Comma);
					}
				}

				}
			}

			setState(270);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportAliasNameContext extends ParserRuleContext {
		public ModuleExportNameContext moduleExportName() {
			return getRuleContext(ModuleExportNameContext.class,0);
		}
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public ImportedBindingContext importedBinding() {
			return getRuleContext(ImportedBindingContext.class,0);
		}
		public ImportAliasNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importAliasName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportAliasName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportAliasName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportAliasName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportAliasNameContext importAliasName() throws RecognitionException {
		ImportAliasNameContext _localctx = new ImportAliasNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_importAliasName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			moduleExportName();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==As) {
				{
				setState(273);
				match(As);
				setState(274);
				importedBinding();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleExportNameContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(JavaScriptParser.StringLiteral, 0); }
		public ModuleExportNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleExportName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterModuleExportName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitModuleExportName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitModuleExportName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleExportNameContext moduleExportName() throws RecognitionException {
		ModuleExportNameContext _localctx = new ModuleExportNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_moduleExportName);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				identifierName();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(StringLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportedBindingContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaScriptParser.Identifier, 0); }
		public TerminalNode Yield() { return getToken(JavaScriptParser.Yield, 0); }
		public TerminalNode Await() { return getToken(JavaScriptParser.Await, 0); }
		public ImportedBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importedBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportedBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportedBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportedBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportedBindingContext importedBinding() throws RecognitionException {
		ImportedBindingContext _localctx = new ImportedBindingContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_importedBinding);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			_la = _input.LA(1);
			if ( !(((((_la - 104)) & ~0x3f) == 0 && ((1L << (_la - 104)) & 1049601L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportDefaultContext extends ParserRuleContext {
		public AliasNameContext aliasName() {
			return getRuleContext(AliasNameContext.class,0);
		}
		public TerminalNode Comma() { return getToken(JavaScriptParser.Comma, 0); }
		public ImportDefaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDefault; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportDefault(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportDefault(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportDefault(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDefaultContext importDefault() throws RecognitionException {
		ImportDefaultContext _localctx = new ImportDefaultContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_importDefault);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			aliasName();
			setState(284);
			match(Comma);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportNamespaceContext extends ParserRuleContext {
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public List<IdentifierNameContext> identifierName() {
			return getRuleContexts(IdentifierNameContext.class);
		}
		public IdentifierNameContext identifierName(int i) {
			return getRuleContext(IdentifierNameContext.class,i);
		}
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public ImportNamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importNamespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportNamespaceContext importNamespace() throws RecognitionException {
		ImportNamespaceContext _localctx = new ImportNamespaceContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_importNamespace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Multiply:
				{
				setState(286);
				match(Multiply);
				}
				break;
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				{
				setState(287);
				identifierName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==As) {
				{
				setState(290);
				match(As);
				setState(291);
				identifierName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportFromContext extends ParserRuleContext {
		public TerminalNode From() { return getToken(JavaScriptParser.From, 0); }
		public TerminalNode StringLiteral() { return getToken(JavaScriptParser.StringLiteral, 0); }
		public ImportFromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importFrom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportFrom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportFrom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportFromContext importFrom() throws RecognitionException {
		ImportFromContext _localctx = new ImportFromContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_importFrom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(From);
			setState(295);
			match(StringLiteral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasNameContext extends ParserRuleContext {
		public List<IdentifierNameContext> identifierName() {
			return getRuleContexts(IdentifierNameContext.class);
		}
		public IdentifierNameContext identifierName(int i) {
			return getRuleContext(IdentifierNameContext.class,i);
		}
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public AliasNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAliasName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAliasName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAliasName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasNameContext aliasName() throws RecognitionException {
		AliasNameContext _localctx = new AliasNameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_aliasName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			identifierName();
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==As) {
				{
				setState(298);
				match(As);
				setState(299);
				identifierName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExportStatementContext extends ParserRuleContext {
		public ExportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportStatement; }

		public ExportStatementContext() { }
		public void copyFrom(ExportStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExportDefaultDeclarationContext extends ExportStatementContext {
		public TerminalNode Export() { return getToken(JavaScriptParser.Export, 0); }
		public TerminalNode Default() { return getToken(JavaScriptParser.Default, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExportDefaultDeclarationContext(ExportStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExportDefaultDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExportDefaultDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExportDefaultDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExportDeclarationContext extends ExportStatementContext {
		public TerminalNode Export() { return getToken(JavaScriptParser.Export, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExportFromBlockContext exportFromBlock() {
			return getRuleContext(ExportFromBlockContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode Default() { return getToken(JavaScriptParser.Default, 0); }
		public ExportDeclarationContext(ExportStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExportDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportStatementContext exportStatement() throws RecognitionException {
		ExportStatementContext _localctx = new ExportStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exportStatement);
		try {
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new ExportDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				match(Export);
				setState(304);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(303);
					match(Default);
					}
					break;
				}
				setState(308);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(306);
					exportFromBlock();
					}
					break;
				case 2:
					{
					setState(307);
					declaration();
					}
					break;
				}
				setState(310);
				eos();
				}
				break;
			case 2:
				_localctx = new ExportDefaultDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				match(Export);
				setState(313);
				match(Default);
				setState(314);
				assignSingleExpression();
				setState(315);
				eos();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExportFromBlockContext extends ParserRuleContext {
		public ImportNamespaceContext importNamespace() {
			return getRuleContext(ImportNamespaceContext.class,0);
		}
		public ImportFromContext importFrom() {
			return getRuleContext(ImportFromContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExportModuleItemsContext exportModuleItems() {
			return getRuleContext(ExportModuleItemsContext.class,0);
		}
		public ExportFromBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportFromBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExportFromBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExportFromBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExportFromBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportFromBlockContext exportFromBlock() throws RecognitionException {
		ExportFromBlockContext _localctx = new ExportFromBlockContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exportFromBlock);
		try {
			setState(329);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Multiply:
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				importNamespace();
				setState(320);
				importFrom();
				setState(321);
				eos();
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				exportModuleItems();
				setState(325);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(324);
					importFrom();
					}
					break;
				}
				setState(327);
				eos();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExportModuleItemsContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public List<ExportAliasNameContext> exportAliasName() {
			return getRuleContexts(ExportAliasNameContext.class);
		}
		public ExportAliasNameContext exportAliasName(int i) {
			return getRuleContext(ExportAliasNameContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public ExportModuleItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportModuleItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExportModuleItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExportModuleItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExportModuleItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportModuleItemsContext exportModuleItems() throws RecognitionException {
		ExportModuleItemsContext _localctx = new ExportModuleItemsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exportModuleItems);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(OpenBrace);
			setState(337);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(332);
					exportAliasName();
					setState(333);
					match(Comma);
					}
					}
				}
				setState(339);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & -1181693L) != 0)) {
				{
				setState(340);
				exportAliasName();
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(341);
					match(Comma);
					}
				}

				}
			}

			setState(346);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExportAliasNameContext extends ParserRuleContext {
		public List<ModuleExportNameContext> moduleExportName() {
			return getRuleContexts(ModuleExportNameContext.class);
		}
		public ModuleExportNameContext moduleExportName(int i) {
			return getRuleContext(ModuleExportNameContext.class,i);
		}
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public ExportAliasNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exportAliasName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExportAliasName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExportAliasName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExportAliasName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExportAliasNameContext exportAliasName() throws RecognitionException {
		ExportAliasNameContext _localctx = new ExportAliasNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_exportAliasName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			moduleExportName();
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==As) {
				{
				setState(349);
				match(As);
				setState(350);
				moduleExportName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public VariableStatementContext variableStatement() {
			return getRuleContext(VariableStatementContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_declaration);
		try {
			setState(356);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Var:
			case Let:
			case Const:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				variableStatement();
				}
				break;
			case Class:
				enterOuterAlt(_localctx, 2);
				{
				setState(354);
				classDeclaration();
				}
				break;
			case Function_:
			case Async:
				enterOuterAlt(_localctx, 3);
				{
				setState(355);
				functionDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableStatementContext extends ParserRuleContext {
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public VariableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterVariableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitVariableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitVariableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableStatementContext variableStatement() throws RecognitionException {
		VariableStatementContext _localctx = new VariableStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_variableStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			variableDeclarationList();
			setState(359);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationListContext extends ParserRuleContext {
		public VarModifierContext varModifier() {
			return getRuleContext(VarModifierContext.class,0);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public VariableDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterVariableDeclarationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitVariableDeclarationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitVariableDeclarationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationListContext variableDeclarationList() throws RecognitionException {
		VariableDeclarationListContext _localctx = new VariableDeclarationListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variableDeclarationList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			varModifier();
			setState(362);
			variableDeclaration();
			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(363);
					match(Comma);
					setState(364);
					variableDeclaration();
					}
					}
				}
				setState(369);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode Assign() { return getToken(JavaScriptParser.Assign, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			assignable();
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(371);
				match(Assign);
				setState(372);
				assignSingleExpression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStatement_Context extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(JavaScriptParser.SemiColon, 0); }
		public EmptyStatement_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterEmptyStatement_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitEmptyStatement_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitEmptyStatement_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatement_Context emptyStatement_() throws RecognitionException {
		EmptyStatement_Context _localctx = new EmptyStatement_Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_emptyStatement_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(SemiColon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			if (!(this.notOpenBraceAndNotFunction())) throw new FailedPredicateException(this, "this.notOpenBraceAndNotFunction()");
			setState(378);
			expressionSequence();
			setState(379);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(JavaScriptParser.If, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public List<ElseIfStatementContext> elseIfStatement() {
			return getRuleContexts(ElseIfStatementContext.class);
		}
		public ElseIfStatementContext elseIfStatement(int i) {
			return getRuleContext(ElseIfStatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(If);
			setState(382);
			leftParenthesis();
			setState(383);
			expressionSequence();
			setState(384);
			match(CloseParen);
			setState(385);
			assignmentStatement();
			setState(389);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(386);
					elseIfStatement();
					}
					}
				}
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(393);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(392);
				elseStatement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(JavaScriptParser.Else, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(Else);
			setState(396);
			assignmentStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseIfStatementContext extends ParserRuleContext {
		public TerminalNode ELSE_IF() { return getToken(JavaScriptParser.ELSE_IF, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ElseIfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterElseIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitElseIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitElseIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfStatementContext elseIfStatement() throws RecognitionException {
		ElseIfStatementContext _localctx = new ElseIfStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(ELSE_IF);
			setState(399);
			leftParenthesis();
			setState(400);
			expressionSequence();
			setState(401);
			match(CloseParen);
			setState(402);
			assignmentStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }

		public IterationStatementContext() { }
		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoStatementContext extends IterationStatementContext {
		public TerminalNode Do() { return getToken(JavaScriptParser.Do, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public TerminalNode While() { return getToken(JavaScriptParser.While, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public DoStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitDoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitDoStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends IterationStatementContext {
		public TerminalNode While() { return getToken(JavaScriptParser.While, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public WhileStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(JavaScriptParser.For, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public List<TerminalNode> SemiColon() { return getTokens(JavaScriptParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(JavaScriptParser.SemiColon, i);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public List<ExpressionSequenceContext> expressionSequence() {
			return getRuleContexts(ExpressionSequenceContext.class);
		}
		public ExpressionSequenceContext expressionSequence(int i) {
			return getRuleContext(ExpressionSequenceContext.class,i);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public ForStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForInStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(JavaScriptParser.For, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode In() { return getToken(JavaScriptParser.In, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public ForInStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterForInStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitForInStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitForInStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForOfStatementContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(JavaScriptParser.For, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode Of() { return getToken(JavaScriptParser.Of, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public VariableDeclarationListContext variableDeclarationList() {
			return getRuleContext(VariableDeclarationListContext.class,0);
		}
		public TerminalNode Await() { return getToken(JavaScriptParser.Await, 0); }
		public ForOfStatementContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterForOfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitForOfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitForOfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_iterationStatement);
		int _la;
		try {
			setState(460);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				_localctx = new DoStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(404);
				match(Do);
				setState(405);
				assignmentStatement();
				setState(406);
				match(While);
				setState(407);
				leftParenthesis();
				setState(408);
				expressionSequence();
				setState(409);
				match(CloseParen);
				setState(410);
				eos();
				}
				break;
			case 2:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				match(While);
				setState(413);
				leftParenthesis();
				setState(414);
				expressionSequence();
				setState(415);
				match(CloseParen);
				setState(416);
				assignmentStatement();
				}
				break;
			case 3:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(418);
				match(For);
				setState(419);
				leftParenthesis();
				setState(422);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(420);
					expressionSequence();
					}
					break;
				case 2:
					{
					setState(421);
					variableDeclarationList();
					}
					break;
				}
				setState(424);
				match(SemiColon);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018361326928L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 9223372036854480895L) != 0)) {
					{
					setState(425);
					expressionSequence();
					}
				}

				setState(428);
				match(SemiColon);
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018361326928L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 9223372036854480895L) != 0)) {
					{
					setState(429);
					expressionSequence();
					}
				}

				setState(432);
				match(CloseParen);
				setState(433);
				assignmentStatement();
				}
				break;
			case 4:
				_localctx = new ForInStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(435);
				match(For);
				setState(436);
				leftParenthesis();
				setState(439);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(437);
					assignSingleExpression();
					}
					break;
				case 2:
					{
					setState(438);
					variableDeclarationList();
					}
					break;
				}
				setState(441);
				match(In);
				setState(442);
				expressionSequence();
				setState(443);
				match(CloseParen);
				setState(444);
				assignmentStatement();
				}
				break;
			case 5:
				_localctx = new ForOfStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(446);
				match(For);
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Await) {
					{
					setState(447);
					match(Await);
					}
				}

				setState(450);
				leftParenthesis();
				setState(453);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
				case 1:
					{
					setState(451);
					assignSingleExpression();
					}
					break;
				case 2:
					{
					setState(452);
					variableDeclarationList();
					}
					break;
				}
				setState(455);
				match(Of);
				setState(456);
				expressionSequence();
				setState(457);
				match(CloseParen);
				setState(458);
				assignmentStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LeftParenthesisContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(JavaScriptParser.OpenParen, 0); }
		public LeftParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftParenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLeftParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLeftParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLeftParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftParenthesisContext leftParenthesis() throws RecognitionException {
		LeftParenthesisContext _localctx = new LeftParenthesisContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_leftParenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			match(OpenParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarModifierContext extends ParserRuleContext {
		public TerminalNode Var() { return getToken(JavaScriptParser.Var, 0); }
		public TerminalNode Const() { return getToken(JavaScriptParser.Const, 0); }
		public TerminalNode Let() { return getToken(JavaScriptParser.Let, 0); }
		public VarModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterVarModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitVarModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitVarModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarModifierContext varModifier() throws RecognitionException {
		VarModifierContext _localctx = new VarModifierContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_varModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			_la = _input.LA(1);
			if ( !(((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 536870915L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(JavaScriptParser.Continue, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(Continue);
			setState(469);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(467);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(468);
				identifier();
				}
				break;
			}
			setState(471);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(JavaScriptParser.Break, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			match(Break);
			setState(476);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(474);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(475);
				identifier();
				}
				break;
			}
			setState(478);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(JavaScriptParser.Return, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			match(Return);
			setState(483);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(481);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(482);
				expressionSequence();
				}
				break;
			}
			setState(485);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class YieldStatementContext extends ParserRuleContext {
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public TerminalNode Yield() { return getToken(JavaScriptParser.Yield, 0); }
		public TerminalNode YieldStar() { return getToken(JavaScriptParser.YieldStar, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public YieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterYieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitYieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitYieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YieldStatementContext yieldStatement() throws RecognitionException {
		YieldStatementContext _localctx = new YieldStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_yieldStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_la = _input.LA(1);
			if ( !(_la==Yield || _la==YieldStar) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(488);
				if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
				setState(489);
				expressionSequence();
				}
				break;
			}
			setState(492);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithStatementContext extends ParserRuleContext {
		public TerminalNode With() { return getToken(JavaScriptParser.With, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public WithStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterWithStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitWithStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitWithStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithStatementContext withStatement() throws RecognitionException {
		WithStatementContext _localctx = new WithStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_withStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(With);
			setState(495);
			leftParenthesis();
			setState(496);
			expressionSequence();
			setState(497);
			match(CloseParen);
			setState(498);
			assignmentStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode Switch() { return getToken(JavaScriptParser.Switch, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public CaseBlockContext caseBlock() {
			return getRuleContext(CaseBlockContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_switchStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			match(Switch);
			setState(501);
			leftParenthesis();
			setState(502);
			expressionSequence();
			setState(503);
			match(CloseParen);
			setState(504);
			caseBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseBlockContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public List<CaseClausesContext> caseClauses() {
			return getRuleContexts(CaseClausesContext.class);
		}
		public CaseClausesContext caseClauses(int i) {
			return getRuleContext(CaseClausesContext.class,i);
		}
		public DefaultClauseContext defaultClause() {
			return getRuleContext(DefaultClauseContext.class,0);
		}
		public CaseBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterCaseBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitCaseBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitCaseBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseBlockContext caseBlock() throws RecognitionException {
		CaseBlockContext _localctx = new CaseBlockContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_caseBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
			match(OpenBrace);
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Case) {
				{
				setState(507);
				caseClauses();
				}
			}

			setState(514);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Default) {
				{
				setState(510);
				defaultClause();
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Case) {
					{
					setState(511);
					caseClauses();
					}
				}

				}
			}

			setState(516);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseClausesContext extends ParserRuleContext {
		public List<CaseClauseContext> caseClause() {
			return getRuleContexts(CaseClauseContext.class);
		}
		public CaseClauseContext caseClause(int i) {
			return getRuleContext(CaseClauseContext.class,i);
		}
		public CaseClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterCaseClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitCaseClauses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitCaseClauses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClausesContext caseClauses() throws RecognitionException {
		CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_caseClauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(518);
				caseClause();
				}
				}
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Case );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseClauseContext extends ParserRuleContext {
		public TerminalNode Case() { return getToken(JavaScriptParser.Case, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public CaseClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterCaseClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitCaseClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitCaseClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseClauseContext caseClause() throws RecognitionException {
		CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_caseClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(Case);
			setState(524);
			expressionSequence();
			setState(525);
			match(Colon);
			setState(527);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(526);
				statementList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultClauseContext extends ParserRuleContext {
		public TerminalNode Default() { return getToken(JavaScriptParser.Default, 0); }
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public StatementListContext statementList() {
			return getRuleContext(StatementListContext.class,0);
		}
		public DefaultClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterDefaultClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitDefaultClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitDefaultClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultClauseContext defaultClause() throws RecognitionException {
		DefaultClauseContext _localctx = new DefaultClauseContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_defaultClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			match(Default);
			setState(530);
			match(Colon);
			setState(532);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(531);
				statementList();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelledStatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public LabelledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLabelledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLabelledStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLabelledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelledStatementContext labelledStatement() throws RecognitionException {
		LabelledStatementContext _localctx = new LabelledStatementContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_labelledStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			identifier();
			setState(535);
			match(Colon);
			setState(536);
			assignmentStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ThrowStatementContext extends ParserRuleContext {
		public TerminalNode Throw() { return getToken(JavaScriptParser.Throw, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(Throw);
			setState(539);
			if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
			setState(540);
			expressionSequence();
			setState(541);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TryStatementContext extends ParserRuleContext {
		public TerminalNode Try() { return getToken(JavaScriptParser.Try, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CatchProductionContext catchProduction() {
			return getRuleContext(CatchProductionContext.class,0);
		}
		public FinallyProductionContext finallyProduction() {
			return getRuleContext(FinallyProductionContext.class,0);
		}
		public TryStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTryStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTryStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryStatementContext tryStatement() throws RecognitionException {
		TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_tryStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(Try);
			setState(544);
			block();
			setState(550);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Catch:
				{
				setState(545);
				catchProduction();
				setState(547);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(546);
					finallyProduction();
					}
					break;
				}
				}
				break;
			case Finally:
				{
				setState(549);
				finallyProduction();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CatchProductionContext extends ParserRuleContext {
		public TerminalNode Catch() { return getToken(JavaScriptParser.Catch, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public CatchProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterCatchProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitCatchProduction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitCatchProduction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchProductionContext catchProduction() throws RecognitionException {
		CatchProductionContext _localctx = new CatchProductionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_catchProduction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
			match(Catch);
			setState(559);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OpenParen) {
				{
				setState(553);
				leftParenthesis();
				setState(555);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OpenBracket || _la==OpenBrace || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(554);
					assignable();
					}
				}

				setState(557);
				match(CloseParen);
				}
			}

			setState(561);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FinallyProductionContext extends ParserRuleContext {
		public TerminalNode Finally() { return getToken(JavaScriptParser.Finally, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFinallyProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFinallyProduction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFinallyProduction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyProductionContext finallyProduction() throws RecognitionException {
		FinallyProductionContext _localctx = new FinallyProductionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_finallyProduction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563);
			match(Finally);
			setState(564);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DebuggerStatementContext extends ParserRuleContext {
		public TerminalNode Debugger() { return getToken(JavaScriptParser.Debugger, 0); }
		public EosContext eos() {
			return getRuleContext(EosContext.class,0);
		}
		public DebuggerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_debuggerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterDebuggerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitDebuggerStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitDebuggerStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DebuggerStatementContext debuggerStatement() throws RecognitionException {
		DebuggerStatementContext _localctx = new DebuggerStatementContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_debuggerStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			match(Debugger);
			setState(567);
			eos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode Function_() { return getToken(JavaScriptParser.Function_, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Async) {
				{
				setState(569);
				match(Async);
				}
			}

			setState(572);
			match(Function_);
			setState(574);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Multiply) {
				{
				setState(573);
				match(Multiply);
				}
			}

			setState(576);
			identifier();
			setState(577);
			leftParenthesis();
			setState(579);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
				{
				setState(578);
				formalParameterList();
				}
			}

			setState(581);
			match(CloseParen);
			setState(582);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(JavaScriptParser.Class, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_classDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584);
			match(Class);
			setState(585);
			identifier();
			setState(586);
			classTail();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassTailContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public TerminalNode Extends() { return getToken(JavaScriptParser.Extends, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public List<ClassElementContext> classElement() {
			return getRuleContexts(ClassElementContext.class);
		}
		public ClassElementContext classElement(int i) {
			return getRuleContext(ClassElementContext.class,i);
		}
		public ClassTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterClassTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitClassTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitClassTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTailContext classTail() throws RecognitionException {
		ClassTailContext _localctx = new ClassTailContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_classTail);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Extends) {
				{
				setState(588);
				match(Extends);
				setState(589);
				assignSingleExpression();
				}
			}

			setState(592);
			match(OpenBrace);
			setState(596);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(593);
					classElement();
					}
					}
				}
				setState(598);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			setState(599);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassElementContext extends ParserRuleContext {
		public MethodDefinitionContext methodDefinition() {
			return getRuleContext(MethodDefinitionContext.class,0);
		}
		public TerminalNode Static() { return getToken(JavaScriptParser.Static, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldDefinitionContext fieldDefinition() {
			return getRuleContext(FieldDefinitionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public EmptyStatement_Context emptyStatement_() {
			return getRuleContext(EmptyStatement_Context.class,0);
		}
		public ClassElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterClassElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitClassElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitClassElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassElementContext classElement() throws RecognitionException {
		ClassElementContext _localctx = new ClassElementContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_classElement);
		try {
			setState(620);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(604);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(601);
					match(Static);
					}
					break;
				case 2:
					{
					setState(602);
					if (!(this.n("static"))) throw new FailedPredicateException(this, "this.n(\"static\")");
					setState(603);
					identifier();
					}
					break;
				}
				setState(606);
				methodDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(610);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(607);
					match(Static);
					}
					break;
				case 2:
					{
					setState(608);
					if (!(this.n("static"))) throw new FailedPredicateException(this, "this.n(\"static\")");
					setState(609);
					identifier();
					}
					break;
				}
				setState(612);
				fieldDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(616);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(613);
					match(Static);
					}
					break;
				case 2:
					{
					setState(614);
					if (!(this.n("static"))) throw new FailedPredicateException(this, "this.n(\"static\")");
					setState(615);
					identifier();
					}
					break;
				}
				setState(618);
				block();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(619);
				emptyStatement_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodDefinitionContext extends ParserRuleContext {
		public ClassElementNameContext classElementName() {
			return getRuleContext(ClassElementNameContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public GetterContext getter() {
			return getRuleContext(GetterContext.class,0);
		}
		public SetterContext setter() {
			return getRuleContext(SetterContext.class,0);
		}
		public MethodDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterMethodDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitMethodDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitMethodDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDefinitionContext methodDefinition() throws RecognitionException {
		MethodDefinitionContext _localctx = new MethodDefinitionContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_methodDefinition);
		int _la;
		try {
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(624);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
				case 1:
					{
					setState(622);
					match(Async);
					setState(623);
					if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
					}
					break;
				}
				setState(627);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Multiply) {
					{
					setState(626);
					match(Multiply);
					}
				}

				setState(629);
				classElementName();
				setState(630);
				leftParenthesis();
				setState(632);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(631);
					formalParameterList();
					}
				}

				setState(634);
				match(CloseParen);
				setState(635);
				functionBody();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(638);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(637);
					match(Multiply);
					}
					break;
				}
				setState(640);
				getter();
				setState(641);
				leftParenthesis();
				setState(642);
				match(CloseParen);
				setState(643);
				functionBody();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(646);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
				case 1:
					{
					setState(645);
					match(Multiply);
					}
					break;
				}
				setState(648);
				setter();
				setState(649);
				leftParenthesis();
				setState(651);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(650);
					formalParameterList();
					}
				}

				setState(653);
				match(CloseParen);
				setState(654);
				functionBody();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDefinitionContext extends ParserRuleContext {
		public ClassElementNameContext classElementName() {
			return getRuleContext(ClassElementNameContext.class,0);
		}
		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class,0);
		}
		public FieldDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFieldDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFieldDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFieldDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDefinitionContext fieldDefinition() throws RecognitionException {
		FieldDefinitionContext _localctx = new FieldDefinitionContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_fieldDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			classElementName();
			setState(660);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				setState(659);
				initializer();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassElementNameContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public PrivateIdentifierContext privateIdentifier() {
			return getRuleContext(PrivateIdentifierContext.class,0);
		}
		public ClassElementNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classElementName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterClassElementName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitClassElementName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitClassElementName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassElementNameContext classElementName() throws RecognitionException {
		ClassElementNameContext _localctx = new ClassElementNameContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_classElementName);
		try {
			setState(664);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBracket:
			case NullLiteral:
			case BooleanLiteral:
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(662);
				propertyName();
				}
				break;
			case Hashtag:
				enterOuterAlt(_localctx, 2);
				{
				setState(663);
				privateIdentifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrivateIdentifierContext extends ParserRuleContext {
		public TerminalNode Hashtag() { return getToken(JavaScriptParser.Hashtag, 0); }
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public PrivateIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_privateIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPrivateIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPrivateIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPrivateIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrivateIdentifierContext privateIdentifier() throws RecognitionException {
		PrivateIdentifierContext _localctx = new PrivateIdentifierContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_privateIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			match(Hashtag);
			setState(667);
			identifierName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterArgContext> formalParameterArg() {
			return getRuleContexts(FormalParameterArgContext.class);
		}
		public FormalParameterArgContext formalParameterArg(int i) {
			return getRuleContext(FormalParameterArgContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public LastFormalParameterArgContext lastFormalParameterArg() {
			return getRuleContext(LastFormalParameterArgContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFormalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(682);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBracket:
			case OpenBrace:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(669);
				formalParameterArg();
				setState(674);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(670);
						match(Comma);
						setState(671);
						formalParameterArg();
						}
						}
					}
					setState(676);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				}
				setState(679);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(677);
					match(Comma);
					setState(678);
					lastFormalParameterArg();
					}
				}

				}
				break;
			case Ellipsis:
				enterOuterAlt(_localctx, 2);
				{
				setState(681);
				lastFormalParameterArg();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalParameterArgContext extends ParserRuleContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public TerminalNode Assign() { return getToken(JavaScriptParser.Assign, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public FormalParameterArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFormalParameterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFormalParameterArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFormalParameterArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterArgContext formalParameterArg() throws RecognitionException {
		FormalParameterArgContext _localctx = new FormalParameterArgContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_formalParameterArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(684);
			assignable();
			setState(687);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(685);
				match(Assign);
				setState(686);
				assignSingleExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LastFormalParameterArgContext extends ParserRuleContext {
		public TerminalNode Ellipsis() { return getToken(JavaScriptParser.Ellipsis, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public LastFormalParameterArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameterArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLastFormalParameterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLastFormalParameterArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLastFormalParameterArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LastFormalParameterArgContext lastFormalParameterArg() throws RecognitionException {
		LastFormalParameterArgContext _localctx = new LastFormalParameterArgContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_lastFormalParameterArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			match(Ellipsis);
			setState(690);
			assignSingleExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionBodyContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public SourceElementsContext sourceElements() {
			return getRuleContext(SourceElementsContext.class,0);
		}
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			match(OpenBrace);
			setState(694);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(693);
				sourceElements();
				}
				break;
			}
			setState(696);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceElementsContext extends ParserRuleContext {
		public List<SourceElementContext> sourceElement() {
			return getRuleContexts(SourceElementContext.class);
		}
		public SourceElementContext sourceElement(int i) {
			return getRuleContext(SourceElementContext.class,i);
		}
		public SourceElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterSourceElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitSourceElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitSourceElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceElementsContext sourceElements() throws RecognitionException {
		SourceElementsContext _localctx = new SourceElementsContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_sourceElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(699);
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(698);
					sourceElement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(701);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode OpenBracket() { return getToken(JavaScriptParser.OpenBracket, 0); }
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(JavaScriptParser.CloseBracket, 0); }
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_arrayLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(703);
			match(OpenBracket);
			setState(704);
			elementList();
			setState(705);
			match(CloseBracket);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementListContext extends ParserRuleContext {
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public List<ArrayElementContext> arrayElement() {
			return getRuleContexts(ArrayElementContext.class);
		}
		public ArrayElementContext arrayElement(int i) {
			return getRuleContext(ArrayElementContext.class,i);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterElementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitElementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_elementList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(707);
					match(Comma);
					}
					}
				}
				setState(712);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			}
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018361064784L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 9223372036854480895L) != 0)) {
				{
				setState(713);
				arrayElement();
				}
			}

			setState(724);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(717);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(716);
						match(Comma);
						}
						}
						setState(719);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==Comma );
					setState(721);
					arrayElement();
					}
					}
				}
				setState(726);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			setState(730);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(727);
				match(Comma);
				}
				}
				setState(732);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayElementContext extends ParserRuleContext {
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(JavaScriptParser.Ellipsis, 0); }
		public ArrayElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrayElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrayElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrayElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElementContext arrayElement() throws RecognitionException {
		ArrayElementContext _localctx = new ArrayElementContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_arrayElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(733);
				match(Ellipsis);
				}
			}

			setState(736);
			assignSingleExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyAssignmentContext extends ParserRuleContext {
		public PropertyAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyAssignment; }

		public PropertyAssignmentContext() { }
		public void copyFrom(PropertyAssignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropertyExpressionAssignmentContext extends PropertyAssignmentContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public PropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPropertyExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPropertyExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPropertyExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComputedPropertyExpressionAssignmentContext extends PropertyAssignmentContext {
		public TerminalNode OpenBracket() { return getToken(JavaScriptParser.OpenBracket, 0); }
		public List<AssignSingleExpressionContext> assignSingleExpression() {
			return getRuleContexts(AssignSingleExpressionContext.class);
		}
		public AssignSingleExpressionContext assignSingleExpression(int i) {
			return getRuleContext(AssignSingleExpressionContext.class,i);
		}
		public TerminalNode CloseBracket() { return getToken(JavaScriptParser.CloseBracket, 0); }
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public ComputedPropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterComputedPropertyExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitComputedPropertyExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitComputedPropertyExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropertyShorthandContext extends PropertyAssignmentContext {
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(JavaScriptParser.Ellipsis, 0); }
		public PropertyShorthandContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPropertyShorthand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPropertyShorthand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPropertyShorthand(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropertySetterContext extends PropertyAssignmentContext {
		public SetterContext setter() {
			return getRuleContext(SetterContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public FormalParameterArgContext formalParameterArg() {
			return getRuleContext(FormalParameterArgContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public PropertySetterContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPropertySetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPropertySetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPropertySetter(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PropertyGetterContext extends PropertyAssignmentContext {
		public GetterContext getter() {
			return getRuleContext(GetterContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public PropertyGetterContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPropertyGetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPropertyGetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPropertyGetter(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionPropertyContext extends PropertyAssignmentContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FunctionPropertyContext(PropertyAssignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFunctionProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFunctionProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFunctionProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyAssignmentContext propertyAssignment() throws RecognitionException {
		PropertyAssignmentContext _localctx = new PropertyAssignmentContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_propertyAssignment);
		int _la;
		try {
			setState(777);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				_localctx = new PropertyExpressionAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(738);
				propertyName();
				setState(739);
				match(Colon);
				setState(740);
				assignSingleExpression();
				}
				break;
			case 2:
				_localctx = new ComputedPropertyExpressionAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(742);
				match(OpenBracket);
				setState(743);
				assignSingleExpression();
				setState(744);
				match(CloseBracket);
				setState(745);
				match(Colon);
				setState(746);
				assignSingleExpression();
				}
				break;
			case 3:
				_localctx = new FunctionPropertyContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(749);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(748);
					match(Async);
					}
					break;
				}
				setState(752);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Multiply) {
					{
					setState(751);
					match(Multiply);
					}
				}

				setState(754);
				propertyName();
				setState(755);
				leftParenthesis();
				setState(757);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(756);
					formalParameterList();
					}
				}

				setState(759);
				match(CloseParen);
				setState(760);
				functionBody();
				}
				break;
			case 4:
				_localctx = new PropertyGetterContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(762);
				getter();
				setState(763);
				leftParenthesis();
				setState(764);
				match(CloseParen);
				setState(765);
				functionBody();
				}
				break;
			case 5:
				_localctx = new PropertySetterContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(767);
				setter();
				setState(768);
				leftParenthesis();
				setState(769);
				formalParameterArg();
				setState(770);
				match(CloseParen);
				setState(771);
				functionBody();
				}
				break;
			case 6:
				_localctx = new PropertyShorthandContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(774);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(773);
					match(Ellipsis);
					}
				}

				setState(776);
				assignSingleExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyNameContext extends ParserRuleContext {
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(JavaScriptParser.StringLiteral, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(JavaScriptParser.OpenBracket, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(JavaScriptParser.CloseBracket, 0); }
		public PropertyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPropertyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPropertyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPropertyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyNameContext propertyName() throws RecognitionException {
		PropertyNameContext _localctx = new PropertyNameContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_propertyName);
		try {
			setState(786);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
			case BooleanLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(779);
				identifierName();
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(780);
				match(StringLiteral);
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(781);
				numericLiteral();
				}
				break;
			case OpenBracket:
				enterOuterAlt(_localctx, 4);
				{
				setState(782);
				match(OpenBracket);
				setState(783);
				assignSingleExpression();
				setState(784);
				match(CloseBracket);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_arguments);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(788);
			leftParenthesis();
			setState(800);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018361064784L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 9223372036854480895L) != 0)) {
				{
				setState(789);
				argument();
				setState(794);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(790);
						match(Comma);
						setState(791);
						argument();
						}
						}
					}
					setState(796);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
				}
				setState(798);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(797);
					match(Comma);
					}
				}

				}
			}

			setState(802);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(JavaScriptParser.Ellipsis, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(805);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(804);
				match(Ellipsis);
				}
			}

			setState(809);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(807);
				assignSingleExpression();
				}
				break;
			case 2:
				{
				setState(808);
				identifier();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionSequenceContext extends ParserRuleContext {
		public List<AssignSingleExpressionContext> assignSingleExpression() {
			return getRuleContexts(AssignSingleExpressionContext.class);
		}
		public AssignSingleExpressionContext assignSingleExpression(int i) {
			return getRuleContext(AssignSingleExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public ExpressionSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterExpressionSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitExpressionSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitExpressionSequence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionSequenceContext expressionSequence() throws RecognitionException {
		ExpressionSequenceContext _localctx = new ExpressionSequenceContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_expressionSequence);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(811);
			assignSingleExpression();
			setState(816);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(812);
					match(Comma);
					setState(813);
					assignSingleExpression();
					}
					}
				}
				setState(818);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignSingleExpressionContext extends ParserRuleContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public AssignSingleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignSingleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignSingleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignSingleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignSingleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignSingleExpressionContext assignSingleExpression() throws RecognitionException {
		AssignSingleExpressionContext _localctx = new AssignSingleExpressionContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_assignSingleExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(819);
			singleExpression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleExpressionContext extends ParserRuleContext {
		public SingleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleExpression; }

		public SingleExpressionContext() { }
		public void copyFrom(SingleExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TemplateStringExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TemplateStringLiteralContext templateStringLiteral() {
			return getRuleContext(TemplateStringLiteralContext.class,0);
		}
		public TemplateStringExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTemplateStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTemplateStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTemplateStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode QuestionMark() { return getToken(JavaScriptParser.QuestionMark, 0); }
		public TerminalNode Colon() { return getToken(JavaScriptParser.Colon, 0); }
		public TernaryExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTernaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTernaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTernaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode And() { return getToken(JavaScriptParser.And, 0); }
		public LogicalAndExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLogicalAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLogicalAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowerExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Power() { return getToken(JavaScriptParser.Power, 0); }
		public PowerExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPowerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPowerExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPowerExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreIncrementExpressionContext extends SingleExpressionContext {
		public TerminalNode PlusPlus() { return getToken(JavaScriptParser.PlusPlus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public PreIncrementExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPreIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPreIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPreIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjectLiteralExpressionContext extends SingleExpressionContext {
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public ObjectLiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterObjectLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitObjectLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitObjectLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MetaExpressionContext extends SingleExpressionContext {
		public TerminalNode New() { return getToken(JavaScriptParser.New, 0); }
		public TerminalNode Dot() { return getToken(JavaScriptParser.Dot, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MetaExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterMetaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitMetaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitMetaExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode In() { return getToken(JavaScriptParser.In, 0); }
		public InExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Or() { return getToken(JavaScriptParser.Or, 0); }
		public LogicalOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLogicalOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLogicalOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OptionalChainExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode QuestionMarkDot() { return getToken(JavaScriptParser.QuestionMarkDot, 0); }
		public OptionalChainExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterOptionalChainExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitOptionalChainExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitOptionalChainExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends SingleExpressionContext {
		public TerminalNode Not() { return getToken(JavaScriptParser.Not, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public NotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreDecreaseExpressionContext extends SingleExpressionContext {
		public TerminalNode MinusMinus() { return getToken(JavaScriptParser.MinusMinus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public PreDecreaseExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPreDecreaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPreDecreaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPreDecreaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ArgumentsExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArgumentsExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArgumentsExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArgumentsExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AwaitExpressionContext extends SingleExpressionContext {
		public TerminalNode Await() { return getToken(JavaScriptParser.Await, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public AwaitExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAwaitExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAwaitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAwaitExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ThisExpressionContext extends SingleExpressionContext {
		public TerminalNode This() { return getToken(JavaScriptParser.This, 0); }
		public ThisExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterThisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitThisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitThisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionExpressionContext extends SingleExpressionContext {
		public AnonymousFunctionContext anonymousFunction() {
			return getRuleContext(AnonymousFunctionContext.class,0);
		}
		public FunctionExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusExpressionContext extends SingleExpressionContext {
		public TerminalNode Minus() { return getToken(JavaScriptParser.Minus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public UnaryMinusExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterUnaryMinusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitUnaryMinusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitUnaryMinusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(JavaScriptParser.Assign, 0); }
		public AssignmentExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignmentExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignmentExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostDecreaseExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode MinusMinus() { return getToken(JavaScriptParser.MinusMinus, 0); }
		public PostDecreaseExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPostDecreaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPostDecreaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPostDecreaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeofExpressionContext extends SingleExpressionContext {
		public TerminalNode Typeof() { return getToken(JavaScriptParser.Typeof, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TypeofExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTypeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTypeofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTypeofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstanceofExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Instanceof() { return getToken(JavaScriptParser.Instanceof, 0); }
		public InstanceofExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterInstanceofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitInstanceofExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitInstanceofExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryPlusExpressionContext extends SingleExpressionContext {
		public TerminalNode Plus() { return getToken(JavaScriptParser.Plus, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public UnaryPlusExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterUnaryPlusExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitUnaryPlusExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitUnaryPlusExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeleteExpressionContext extends SingleExpressionContext {
		public TerminalNode Delete() { return getToken(JavaScriptParser.Delete, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public DeleteExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterDeleteExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitDeleteExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitDeleteExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImportExpressionContext extends SingleExpressionContext {
		public TerminalNode Import() { return getToken(JavaScriptParser.Import, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public ImportExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterImportExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitImportExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitImportExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Equals_() { return getToken(JavaScriptParser.Equals_, 0); }
		public TerminalNode NotEquals() { return getToken(JavaScriptParser.NotEquals, 0); }
		public TerminalNode IdentityEquals() { return getToken(JavaScriptParser.IdentityEquals, 0); }
		public TerminalNode IdentityNotEquals() { return getToken(JavaScriptParser.IdentityNotEquals, 0); }
		public EqualityExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitXOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitXOr() { return getToken(JavaScriptParser.BitXOr, 0); }
		public BitXOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBitXOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBitXOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBitXOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SuperExpressionContext extends SingleExpressionContext {
		public TerminalNode Super() { return getToken(JavaScriptParser.Super, 0); }
		public SuperExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterSuperExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitSuperExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitSuperExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(JavaScriptParser.Divide, 0); }
		public TerminalNode Modulus() { return getToken(JavaScriptParser.Modulus, 0); }
		public MultiplicativeExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitShiftExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode LeftShiftArithmetic() { return getToken(JavaScriptParser.LeftShiftArithmetic, 0); }
		public TerminalNode RightShiftArithmetic() { return getToken(JavaScriptParser.RightShiftArithmetic, 0); }
		public TerminalNode RightShiftLogical() { return getToken(JavaScriptParser.RightShiftLogical, 0); }
		public BitShiftExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBitShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBitShiftExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBitShiftExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExpressionContext extends SingleExpressionContext {
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public ParenthesizedExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode Plus() { return getToken(JavaScriptParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(JavaScriptParser.Minus, 0); }
		public AdditiveExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode LessThan() { return getToken(JavaScriptParser.LessThan, 0); }
		public TerminalNode MoreThan() { return getToken(JavaScriptParser.MoreThan, 0); }
		public TerminalNode LessThanEquals() { return getToken(JavaScriptParser.LessThanEquals, 0); }
		public TerminalNode GreaterThanEquals() { return getToken(JavaScriptParser.GreaterThanEquals, 0); }
		public RelationalExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostIncrementExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(JavaScriptParser.PlusPlus, 0); }
		public PostIncrementExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterPostIncrementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitPostIncrementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitPostIncrementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class YieldExpressionContext extends SingleExpressionContext {
		public YieldStatementContext yieldStatement() {
			return getRuleContext(YieldStatementContext.class,0);
		}
		public YieldExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterYieldExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitYieldExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitYieldExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitNotExpressionContext extends SingleExpressionContext {
		public TerminalNode BitNot() { return getToken(JavaScriptParser.BitNot, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public BitNotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBitNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewExpressionContext extends SingleExpressionContext {
		public TerminalNode New() { return getToken(JavaScriptParser.New, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public NewExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExpressionContext extends SingleExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralExpressionContext extends SingleExpressionContext {
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ArrayLiteralExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrayLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrayLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrayLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberDotExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(JavaScriptParser.Dot, 0); }
		public IdentifierNameContext identifierName() {
			return getRuleContext(IdentifierNameContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(JavaScriptParser.QuestionMark, 0); }
		public TerminalNode Hashtag() { return getToken(JavaScriptParser.Hashtag, 0); }
		public MemberDotExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterMemberDotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitMemberDotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitMemberDotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ClassExpressionContext extends SingleExpressionContext {
		public TerminalNode Class() { return getToken(JavaScriptParser.Class, 0); }
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterClassExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitClassExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitClassExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberIndexExpressionContext extends SingleExpressionContext {
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public TerminalNode OpenBracket() { return getToken(JavaScriptParser.OpenBracket, 0); }
		public ExpressionSequenceContext expressionSequence() {
			return getRuleContext(ExpressionSequenceContext.class,0);
		}
		public TerminalNode CloseBracket() { return getToken(JavaScriptParser.CloseBracket, 0); }
		public TerminalNode QuestionMarkDot() { return getToken(JavaScriptParser.QuestionMarkDot, 0); }
		public MemberIndexExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterMemberIndexExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitMemberIndexExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitMemberIndexExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierExpressionContext extends SingleExpressionContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public IdentifierExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitAndExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitAnd() { return getToken(JavaScriptParser.BitAnd, 0); }
		public BitAndExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBitAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BitOrExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode BitOr() { return getToken(JavaScriptParser.BitOr, 0); }
		public BitOrExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBitOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentOperatorExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public AssignmentOperatorExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignmentOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignmentOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignmentOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VoidExpressionContext extends SingleExpressionContext {
		public TerminalNode Void() { return getToken(JavaScriptParser.Void, 0); }
		public SingleExpressionContext singleExpression() {
			return getRuleContext(SingleExpressionContext.class,0);
		}
		public VoidExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterVoidExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitVoidExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitVoidExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CoalesceExpressionContext extends SingleExpressionContext {
		public List<SingleExpressionContext> singleExpression() {
			return getRuleContexts(SingleExpressionContext.class);
		}
		public SingleExpressionContext singleExpression(int i) {
			return getRuleContext(SingleExpressionContext.class,i);
		}
		public TerminalNode NullCoalesce() { return getToken(JavaScriptParser.NullCoalesce, 0); }
		public CoalesceExpressionContext(SingleExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterCoalesceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitCoalesceExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitCoalesceExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleExpressionContext singleExpression() throws RecognitionException {
		return singleExpression(0);
	}

	private SingleExpressionContext singleExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SingleExpressionContext _localctx = new SingleExpressionContext(_ctx, _parentState);
		SingleExpressionContext _prevctx = _localctx;
		int _startState = 142;
		enterRecursionRule(_localctx, 142, RULE_singleExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(877);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(822);
				anonymousFunction();
				}
				break;
			case 2:
				{
				_localctx = new ClassExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(823);
				match(Class);
				setState(825);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 8458255L) != 0)) {
					{
					setState(824);
					identifier();
					}
				}

				setState(827);
				classTail();
				}
				break;
			case 3:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(828);
				match(New);
				setState(829);
				identifier();
				setState(830);
				arguments();
				}
				break;
			case 4:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(832);
				match(New);
				setState(833);
				singleExpression(0);
				setState(834);
				arguments();
				}
				break;
			case 5:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(836);
				match(New);
				setState(837);
				singleExpression(42);
				}
				break;
			case 6:
				{
				_localctx = new MetaExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(838);
				match(New);
				setState(839);
				match(Dot);
				setState(840);
				identifier();
				}
				break;
			case 7:
				{
				_localctx = new DeleteExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(841);
				match(Delete);
				setState(842);
				singleExpression(37);
				}
				break;
			case 8:
				{
				_localctx = new VoidExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(843);
				match(Void);
				setState(844);
				singleExpression(36);
				}
				break;
			case 9:
				{
				_localctx = new TypeofExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(845);
				match(Typeof);
				setState(846);
				singleExpression(35);
				}
				break;
			case 10:
				{
				_localctx = new PreIncrementExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(847);
				match(PlusPlus);
				setState(848);
				singleExpression(34);
				}
				break;
			case 11:
				{
				_localctx = new PreDecreaseExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(849);
				match(MinusMinus);
				setState(850);
				singleExpression(33);
				}
				break;
			case 12:
				{
				_localctx = new UnaryPlusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(851);
				match(Plus);
				setState(852);
				singleExpression(32);
				}
				break;
			case 13:
				{
				_localctx = new UnaryMinusExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(853);
				match(Minus);
				setState(854);
				singleExpression(31);
				}
				break;
			case 14:
				{
				_localctx = new BitNotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(855);
				match(BitNot);
				setState(856);
				singleExpression(30);
				}
				break;
			case 15:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(857);
				match(Not);
				setState(858);
				singleExpression(29);
				}
				break;
			case 16:
				{
				_localctx = new AwaitExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(859);
				match(Await);
				setState(860);
				singleExpression(28);
				}
				break;
			case 17:
				{
				_localctx = new ImportExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(861);
				match(Import);
				setState(862);
				leftParenthesis();
				setState(863);
				singleExpression(0);
				setState(864);
				match(CloseParen);
				}
				break;
			case 18:
				{
				_localctx = new YieldExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(866);
				yieldStatement();
				}
				break;
			case 19:
				{
				_localctx = new ThisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(867);
				match(This);
				}
				break;
			case 20:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(868);
				identifier();
				}
				break;
			case 21:
				{
				_localctx = new SuperExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(869);
				match(Super);
				}
				break;
			case 22:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(870);
				literal();
				}
				break;
			case 23:
				{
				_localctx = new ArrayLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(871);
				arrayLiteral();
				}
				break;
			case 24:
				{
				_localctx = new ObjectLiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(872);
				objectLiteral();
				}
				break;
			case 25:
				{
				_localctx = new ParenthesizedExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(873);
				leftParenthesis();
				setState(874);
				expressionSequence();
				setState(875);
				match(CloseParen);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(966);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(964);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
					case 1:
						{
						_localctx = new OptionalChainExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(879);
						if (!(precpred(_ctx, 47))) throw new FailedPredicateException(this, "precpred(_ctx, 47)");
						setState(880);
						match(QuestionMarkDot);
						setState(881);
						singleExpression(48);
						}
						break;
					case 2:
						{
						_localctx = new PowerExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(882);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(883);
						match(Power);
						setState(884);
						singleExpression(27);
						}
						break;
					case 3:
						{
						_localctx = new MultiplicativeExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(885);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(886);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 469762048L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(887);
						singleExpression(27);
						}
						break;
					case 4:
						{
						_localctx = new AdditiveExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(888);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(889);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(890);
						singleExpression(26);
						}
						break;
					case 5:
						{
						_localctx = new CoalesceExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(891);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(892);
						match(NullCoalesce);
						setState(893);
						singleExpression(25);
						}
						break;
					case 6:
						{
						_localctx = new BitShiftExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(894);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(895);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30064771072L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(896);
						singleExpression(24);
						}
						break;
					case 7:
						{
						_localctx = new RelationalExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(897);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(898);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 515396075520L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(899);
						singleExpression(23);
						}
						break;
					case 8:
						{
						_localctx = new InstanceofExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(900);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(901);
						match(Instanceof);
						setState(902);
						singleExpression(22);
						}
						break;
					case 9:
						{
						_localctx = new InExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(903);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(904);
						match(In);
						setState(905);
						singleExpression(21);
						}
						break;
					case 10:
						{
						_localctx = new EqualityExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(906);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(907);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8246337208320L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(908);
						singleExpression(20);
						}
						break;
					case 11:
						{
						_localctx = new BitAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(909);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(910);
						match(BitAnd);
						setState(911);
						singleExpression(19);
						}
						break;
					case 12:
						{
						_localctx = new BitXOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(912);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(913);
						match(BitXOr);
						setState(914);
						singleExpression(18);
						}
						break;
					case 13:
						{
						_localctx = new BitOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(915);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(916);
						match(BitOr);
						setState(917);
						singleExpression(17);
						}
						break;
					case 14:
						{
						_localctx = new LogicalAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(918);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(919);
						match(And);
						setState(920);
						singleExpression(16);
						}
						break;
					case 15:
						{
						_localctx = new LogicalOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(921);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(922);
						match(Or);
						setState(923);
						singleExpression(15);
						}
						break;
					case 16:
						{
						_localctx = new TernaryExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(924);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(925);
						match(QuestionMark);
						setState(926);
						singleExpression(0);
						setState(927);
						match(Colon);
						setState(928);
						singleExpression(14);
						}
						break;
					case 17:
						{
						_localctx = new AssignmentExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(930);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(931);
						match(Assign);
						setState(932);
						singleExpression(12);
						}
						break;
					case 18:
						{
						_localctx = new AssignmentOperatorExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(933);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(934);
						assignmentOperator();
						setState(935);
						singleExpression(11);
						}
						break;
					case 19:
						{
						_localctx = new MemberIndexExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(937);
						if (!(precpred(_ctx, 46))) throw new FailedPredicateException(this, "precpred(_ctx, 46)");
						setState(939);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==QuestionMarkDot) {
							{
							setState(938);
							match(QuestionMarkDot);
							}
						}

						setState(941);
						match(OpenBracket);
						setState(942);
						expressionSequence();
						setState(943);
						match(CloseBracket);
						}
						break;
					case 20:
						{
						_localctx = new MemberDotExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(945);
						if (!(precpred(_ctx, 45))) throw new FailedPredicateException(this, "precpred(_ctx, 45)");
						setState(947);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==QuestionMark) {
							{
							setState(946);
							match(QuestionMark);
							}
						}

						setState(949);
						match(Dot);
						setState(951);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==Hashtag) {
							{
							setState(950);
							match(Hashtag);
							}
						}

						setState(953);
						identifierName();
						}
						break;
					case 21:
						{
						_localctx = new ArgumentsExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(954);
						if (!(precpred(_ctx, 41))) throw new FailedPredicateException(this, "precpred(_ctx, 41)");
						setState(955);
						arguments();
						}
						break;
					case 22:
						{
						_localctx = new PostIncrementExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(956);
						if (!(precpred(_ctx, 39))) throw new FailedPredicateException(this, "precpred(_ctx, 39)");
						setState(957);
						if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
						setState(958);
						match(PlusPlus);
						}
						break;
					case 23:
						{
						_localctx = new PostDecreaseExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(959);
						if (!(precpred(_ctx, 38))) throw new FailedPredicateException(this, "precpred(_ctx, 38)");
						setState(960);
						if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
						setState(961);
						match(MinusMinus);
						}
						break;
					case 24:
						{
						_localctx = new TemplateStringExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
						setState(962);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(963);
						templateStringLiteral();
						}
						break;
					}
					}
				}
				setState(968);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitializerContext extends ParserRuleContext {
		public TerminalNode Assign() { return getToken(JavaScriptParser.Assign, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_initializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(969);
			match(Assign);
			setState(970);
			assignSingleExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignableContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public AssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignableContext assignable() throws RecognitionException {
		AssignableContext _localctx = new AssignableContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_assignable);
		try {
			setState(976);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(972);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(973);
				keyword();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(974);
				arrayLiteral();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(975);
				objectLiteral();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectLiteralContext extends ParserRuleContext {
		public TerminalNode OpenBrace() { return getToken(JavaScriptParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaScriptParser.CloseBrace, 0); }
		public List<PropertyAssignmentContext> propertyAssignment() {
			return getRuleContexts(PropertyAssignmentContext.class);
		}
		public PropertyAssignmentContext propertyAssignment(int i) {
			return getRuleContext(PropertyAssignmentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaScriptParser.Comma, i);
		}
		public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitObjectLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitObjectLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectLiteralContext objectLiteral() throws RecognitionException {
		ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_objectLiteral);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(978);
			match(OpenBrace);
			setState(990);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(979);
				propertyAssignment();
				setState(984);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(980);
						match(Comma);
						setState(981);
						propertyAssignment();
						}
						}
					}
					setState(986);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				}
				setState(988);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(987);
					match(Comma);
					}
				}

				}
				break;
			}
			setState(992);
			match(CloseBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnonymousFunctionContext extends ParserRuleContext {
		public AnonymousFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousFunction; }

		public AnonymousFunctionContext() { }
		public void copyFrom(AnonymousFunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AnonymousFunctionDeclContext extends AnonymousFunctionContext {
		public TerminalNode Function_() { return getToken(JavaScriptParser.Function_, 0); }
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode Multiply() { return getToken(JavaScriptParser.Multiply, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public AnonymousFunctionDeclContext(AnonymousFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAnonymousFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAnonymousFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAnonymousFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrowFunctionContext extends AnonymousFunctionContext {
		public ArrowFunctionParametersContext arrowFunctionParameters() {
			return getRuleContext(ArrowFunctionParametersContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(JavaScriptParser.ARROW, 0); }
		public ArrowFunctionBodyContext arrowFunctionBody() {
			return getRuleContext(ArrowFunctionBodyContext.class,0);
		}
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public ArrowFunctionContext(AnonymousFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrowFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrowFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrowFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NamedFunctionContext extends AnonymousFunctionContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public NamedFunctionContext(AnonymousFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterNamedFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitNamedFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitNamedFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonymousFunctionContext anonymousFunction() throws RecognitionException {
		AnonymousFunctionContext _localctx = new AnonymousFunctionContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_anonymousFunction);
		int _la;
		try {
			setState(1016);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				_localctx = new NamedFunctionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(994);
				functionDeclaration();
				}
				break;
			case 2:
				_localctx = new AnonymousFunctionDeclContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(996);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Async) {
					{
					setState(995);
					match(Async);
					}
				}

				setState(998);
				match(Function_);
				setState(1000);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Multiply) {
					{
					setState(999);
					match(Multiply);
					}
				}

				setState(1002);
				leftParenthesis();
				setState(1004);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(1003);
					formalParameterList();
					}
				}

				setState(1006);
				match(CloseParen);
				setState(1007);
				functionBody();
				}
				break;
			case 3:
				_localctx = new ArrowFunctionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1010);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					{
					setState(1009);
					match(Async);
					}
					break;
				}
				setState(1012);
				arrowFunctionParameters();
				setState(1013);
				match(ARROW);
				setState(1014);
				arrowFunctionBody();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrowFunctionParametersContext extends ParserRuleContext {
		public PropertyNameContext propertyName() {
			return getRuleContext(PropertyNameContext.class,0);
		}
		public LeftParenthesisContext leftParenthesis() {
			return getRuleContext(LeftParenthesisContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaScriptParser.CloseParen, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ArrowFunctionParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunctionParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrowFunctionParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrowFunctionParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrowFunctionParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrowFunctionParametersContext arrowFunctionParameters() throws RecognitionException {
		ArrowFunctionParametersContext _localctx = new ArrowFunctionParametersContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_arrowFunctionParameters);
		int _la;
		try {
			setState(1025);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenBracket:
			case NullLiteral:
			case BooleanLiteral:
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
			case Identifier:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(1018);
				propertyName();
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 2);
				{
				setState(1019);
				leftParenthesis();
				setState(1021);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 262688L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 4503599627369919L) != 0)) {
					{
					setState(1020);
					formalParameterList();
					}
				}

				setState(1023);
				match(CloseParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrowFunctionBodyContext extends ParserRuleContext {
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public ArrowFunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowFunctionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterArrowFunctionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitArrowFunctionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitArrowFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrowFunctionBodyContext arrowFunctionBody() throws RecognitionException {
		ArrowFunctionBodyContext _localctx = new ArrowFunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_arrowFunctionBody);
		try {
			setState(1029);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,112,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1027);
				assignSingleExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1028);
				functionBody();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode MultiplyAssign() { return getToken(JavaScriptParser.MultiplyAssign, 0); }
		public TerminalNode DivideAssign() { return getToken(JavaScriptParser.DivideAssign, 0); }
		public TerminalNode ModulusAssign() { return getToken(JavaScriptParser.ModulusAssign, 0); }
		public TerminalNode PlusAssign() { return getToken(JavaScriptParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(JavaScriptParser.MinusAssign, 0); }
		public TerminalNode LeftShiftArithmeticAssign() { return getToken(JavaScriptParser.LeftShiftArithmeticAssign, 0); }
		public TerminalNode RightShiftArithmeticAssign() { return getToken(JavaScriptParser.RightShiftArithmeticAssign, 0); }
		public TerminalNode RightShiftLogicalAssign() { return getToken(JavaScriptParser.RightShiftLogicalAssign, 0); }
		public TerminalNode BitAndAssign() { return getToken(JavaScriptParser.BitAndAssign, 0); }
		public TerminalNode BitXorAssign() { return getToken(JavaScriptParser.BitXorAssign, 0); }
		public TerminalNode BitOrAssign() { return getToken(JavaScriptParser.BitOrAssign, 0); }
		public TerminalNode PowerAssign() { return getToken(JavaScriptParser.PowerAssign, 0); }
		public TerminalNode NullishCoalescingAssign() { return getToken(JavaScriptParser.NullishCoalescingAssign, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitAssignmentOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1031);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2305561534236983296L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NullLiteral() { return getToken(JavaScriptParser.NullLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(JavaScriptParser.BooleanLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(JavaScriptParser.StringLiteral, 0); }
		public TemplateStringLiteralContext templateStringLiteral() {
			return getRuleContext(TemplateStringLiteralContext.class,0);
		}
		public TerminalNode RegularExpressionLiteral() { return getToken(JavaScriptParser.RegularExpressionLiteral, 0); }
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BigintLiteralContext bigintLiteral() {
			return getRuleContext(BigintLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_literal);
		try {
			setState(1040);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NullLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(1033);
				match(NullLiteral);
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1034);
				match(BooleanLiteral);
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(1035);
				match(StringLiteral);
				}
				break;
			case BackTick:
				enterOuterAlt(_localctx, 4);
				{
				setState(1036);
				templateStringLiteral();
				}
				break;
			case RegularExpressionLiteral:
				enterOuterAlt(_localctx, 5);
				{
				setState(1037);
				match(RegularExpressionLiteral);
				}
				break;
			case DecimalLiteral:
			case HexIntegerLiteral:
			case OctalIntegerLiteral:
			case OctalIntegerLiteral2:
			case BinaryIntegerLiteral:
				enterOuterAlt(_localctx, 6);
				{
				setState(1038);
				numericLiteral();
				}
				break;
			case BigHexIntegerLiteral:
			case BigOctalIntegerLiteral:
			case BigBinaryIntegerLiteral:
			case BigDecimalIntegerLiteral:
				enterOuterAlt(_localctx, 7);
				{
				setState(1039);
				bigintLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateStringLiteralContext extends ParserRuleContext {
		public List<TerminalNode> BackTick() { return getTokens(JavaScriptParser.BackTick); }
		public TerminalNode BackTick(int i) {
			return getToken(JavaScriptParser.BackTick, i);
		}
		public List<TemplateStringAtomContext> templateStringAtom() {
			return getRuleContexts(TemplateStringAtomContext.class);
		}
		public TemplateStringAtomContext templateStringAtom(int i) {
			return getRuleContext(TemplateStringAtomContext.class,i);
		}
		public TemplateStringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTemplateStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTemplateStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTemplateStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateStringLiteralContext templateStringLiteral() throws RecognitionException {
		TemplateStringLiteralContext _localctx = new TemplateStringLiteralContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_templateStringLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1042);
			match(BackTick);
			setState(1046);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TemplateStringStartExpression || _la==TemplateStringAtom) {
				{
				{
				setState(1043);
				templateStringAtom();
				}
				}
				setState(1048);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1049);
			match(BackTick);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateStringAtomContext extends ParserRuleContext {
		public TerminalNode TemplateStringAtom() { return getToken(JavaScriptParser.TemplateStringAtom, 0); }
		public TerminalNode TemplateStringStartExpression() { return getToken(JavaScriptParser.TemplateStringStartExpression, 0); }
		public AssignSingleExpressionContext assignSingleExpression() {
			return getRuleContext(AssignSingleExpressionContext.class,0);
		}
		public TerminalNode TemplateCloseBrace() { return getToken(JavaScriptParser.TemplateCloseBrace, 0); }
		public TemplateStringAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_templateStringAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterTemplateStringAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitTemplateStringAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitTemplateStringAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TemplateStringAtomContext templateStringAtom() throws RecognitionException {
		TemplateStringAtomContext _localctx = new TemplateStringAtomContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_templateStringAtom);
		try {
			setState(1056);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TemplateStringAtom:
				enterOuterAlt(_localctx, 1);
				{
				setState(1051);
				match(TemplateStringAtom);
				}
				break;
			case TemplateStringStartExpression:
				enterOuterAlt(_localctx, 2);
				{
				setState(1052);
				match(TemplateStringStartExpression);
				setState(1053);
				assignSingleExpression();
				setState(1054);
				match(TemplateCloseBrace);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericLiteralContext extends ParserRuleContext {
		public TerminalNode DecimalLiteral() { return getToken(JavaScriptParser.DecimalLiteral, 0); }
		public TerminalNode HexIntegerLiteral() { return getToken(JavaScriptParser.HexIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral() { return getToken(JavaScriptParser.OctalIntegerLiteral, 0); }
		public TerminalNode OctalIntegerLiteral2() { return getToken(JavaScriptParser.OctalIntegerLiteral2, 0); }
		public TerminalNode BinaryIntegerLiteral() { return getToken(JavaScriptParser.BinaryIntegerLiteral, 0); }
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitNumericLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitNumericLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_numericLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1058);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 31L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BigintLiteralContext extends ParserRuleContext {
		public TerminalNode BigDecimalIntegerLiteral() { return getToken(JavaScriptParser.BigDecimalIntegerLiteral, 0); }
		public TerminalNode BigHexIntegerLiteral() { return getToken(JavaScriptParser.BigHexIntegerLiteral, 0); }
		public TerminalNode BigOctalIntegerLiteral() { return getToken(JavaScriptParser.BigOctalIntegerLiteral, 0); }
		public TerminalNode BigBinaryIntegerLiteral() { return getToken(JavaScriptParser.BigBinaryIntegerLiteral, 0); }
		public BigintLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bigintLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterBigintLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitBigintLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitBigintLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BigintLiteralContext bigintLiteral() throws RecognitionException {
		BigintLiteralContext _localctx = new BigintLiteralContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_bigintLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1060);
			_la = _input.LA(1);
			if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GetterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassElementNameContext classElementName() {
			return getRuleContext(ClassElementNameContext.class,0);
		}
		public GetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterGetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitGetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitGetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetterContext getter() throws RecognitionException {
		GetterContext _localctx = new GetterContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_getter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1062);
			if (!(this.n("get"))) throw new FailedPredicateException(this, "this.n(\"get\")");
			setState(1063);
			identifier();
			setState(1064);
			classElementName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassElementNameContext classElementName() {
			return getRuleContext(ClassElementNameContext.class,0);
		}
		public SetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterSetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitSetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitSetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetterContext setter() throws RecognitionException {
		SetterContext _localctx = new SetterContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_setter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1066);
			if (!(this.n("set"))) throw new FailedPredicateException(this, "this.n(\"set\")");
			setState(1067);
			identifier();
			setState(1068);
			classElementName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ReservedWordContext reservedWord() {
			return getRuleContext(ReservedWordContext.class,0);
		}
		public IdentifierNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterIdentifierName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitIdentifierName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitIdentifierName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierNameContext identifierName() throws RecognitionException {
		IdentifierNameContext _localctx = new IdentifierNameContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_identifierName);
		try {
			setState(1072);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1070);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1071);
				reservedWord();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaScriptParser.Identifier, 0); }
		public TerminalNode NonStrictLet() { return getToken(JavaScriptParser.NonStrictLet, 0); }
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public TerminalNode From() { return getToken(JavaScriptParser.From, 0); }
		public TerminalNode Yield() { return getToken(JavaScriptParser.Yield, 0); }
		public TerminalNode Of() { return getToken(JavaScriptParser.Of, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			_la = _input.LA(1);
			if ( !(((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 8458255L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReservedWordContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode NullLiteral() { return getToken(JavaScriptParser.NullLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(JavaScriptParser.BooleanLiteral, 0); }
		public ReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitReservedWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitReservedWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReservedWordContext reservedWord() throws RecognitionException {
		ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_reservedWord);
		try {
			setState(1079);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
			case Do:
			case Instanceof:
			case Typeof:
			case Case:
			case Else:
			case New:
			case Var:
			case Catch:
			case Finally:
			case Return:
			case Void:
			case Continue:
			case For:
			case Switch:
			case While:
			case Debugger:
			case Function_:
			case This:
			case With:
			case Default:
			case If:
			case Throw:
			case Delete:
			case In:
			case Try:
			case As:
			case From:
			case Of:
			case Yield:
			case YieldStar:
			case Class:
			case Enum:
			case Extends:
			case Super:
			case Const:
			case Export:
			case Import:
			case Async:
			case Await:
			case Implements:
			case StrictLet:
			case NonStrictLet:
			case Private:
			case Public:
			case Interface:
			case Package:
			case Protected:
			case Static:
				enterOuterAlt(_localctx, 1);
				{
				setState(1076);
				keyword();
				}
				break;
			case NullLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(1077);
				match(NullLiteral);
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(1078);
				match(BooleanLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(JavaScriptParser.Break, 0); }
		public TerminalNode Do() { return getToken(JavaScriptParser.Do, 0); }
		public TerminalNode Instanceof() { return getToken(JavaScriptParser.Instanceof, 0); }
		public TerminalNode Typeof() { return getToken(JavaScriptParser.Typeof, 0); }
		public TerminalNode Case() { return getToken(JavaScriptParser.Case, 0); }
		public TerminalNode Else() { return getToken(JavaScriptParser.Else, 0); }
		public TerminalNode New() { return getToken(JavaScriptParser.New, 0); }
		public TerminalNode Var() { return getToken(JavaScriptParser.Var, 0); }
		public TerminalNode Catch() { return getToken(JavaScriptParser.Catch, 0); }
		public TerminalNode Finally() { return getToken(JavaScriptParser.Finally, 0); }
		public TerminalNode Return() { return getToken(JavaScriptParser.Return, 0); }
		public TerminalNode Void() { return getToken(JavaScriptParser.Void, 0); }
		public TerminalNode Continue() { return getToken(JavaScriptParser.Continue, 0); }
		public TerminalNode For() { return getToken(JavaScriptParser.For, 0); }
		public TerminalNode Switch() { return getToken(JavaScriptParser.Switch, 0); }
		public TerminalNode While() { return getToken(JavaScriptParser.While, 0); }
		public TerminalNode Debugger() { return getToken(JavaScriptParser.Debugger, 0); }
		public TerminalNode Function_() { return getToken(JavaScriptParser.Function_, 0); }
		public TerminalNode This() { return getToken(JavaScriptParser.This, 0); }
		public TerminalNode With() { return getToken(JavaScriptParser.With, 0); }
		public TerminalNode Default() { return getToken(JavaScriptParser.Default, 0); }
		public TerminalNode If() { return getToken(JavaScriptParser.If, 0); }
		public TerminalNode Throw() { return getToken(JavaScriptParser.Throw, 0); }
		public TerminalNode Delete() { return getToken(JavaScriptParser.Delete, 0); }
		public TerminalNode In() { return getToken(JavaScriptParser.In, 0); }
		public TerminalNode Try() { return getToken(JavaScriptParser.Try, 0); }
		public TerminalNode Class() { return getToken(JavaScriptParser.Class, 0); }
		public TerminalNode Enum() { return getToken(JavaScriptParser.Enum, 0); }
		public TerminalNode Extends() { return getToken(JavaScriptParser.Extends, 0); }
		public TerminalNode Super() { return getToken(JavaScriptParser.Super, 0); }
		public TerminalNode Const() { return getToken(JavaScriptParser.Const, 0); }
		public TerminalNode Export() { return getToken(JavaScriptParser.Export, 0); }
		public TerminalNode Import() { return getToken(JavaScriptParser.Import, 0); }
		public TerminalNode Implements() { return getToken(JavaScriptParser.Implements, 0); }
		public Let_Context let_() {
			return getRuleContext(Let_Context.class,0);
		}
		public TerminalNode Private() { return getToken(JavaScriptParser.Private, 0); }
		public TerminalNode Public() { return getToken(JavaScriptParser.Public, 0); }
		public TerminalNode Interface() { return getToken(JavaScriptParser.Interface, 0); }
		public TerminalNode Package() { return getToken(JavaScriptParser.Package, 0); }
		public TerminalNode Protected() { return getToken(JavaScriptParser.Protected, 0); }
		public TerminalNode Static() { return getToken(JavaScriptParser.Static, 0); }
		public TerminalNode Yield() { return getToken(JavaScriptParser.Yield, 0); }
		public TerminalNode YieldStar() { return getToken(JavaScriptParser.YieldStar, 0); }
		public TerminalNode Async() { return getToken(JavaScriptParser.Async, 0); }
		public TerminalNode Await() { return getToken(JavaScriptParser.Await, 0); }
		public TerminalNode From() { return getToken(JavaScriptParser.From, 0); }
		public TerminalNode As() { return getToken(JavaScriptParser.As, 0); }
		public TerminalNode Of() { return getToken(JavaScriptParser.Of, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_keyword);
		try {
			setState(1129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
				enterOuterAlt(_localctx, 1);
				{
				setState(1081);
				match(Break);
				}
				break;
			case Do:
				enterOuterAlt(_localctx, 2);
				{
				setState(1082);
				match(Do);
				}
				break;
			case Instanceof:
				enterOuterAlt(_localctx, 3);
				{
				setState(1083);
				match(Instanceof);
				}
				break;
			case Typeof:
				enterOuterAlt(_localctx, 4);
				{
				setState(1084);
				match(Typeof);
				}
				break;
			case Case:
				enterOuterAlt(_localctx, 5);
				{
				setState(1085);
				match(Case);
				}
				break;
			case Else:
				enterOuterAlt(_localctx, 6);
				{
				setState(1086);
				match(Else);
				}
				break;
			case New:
				enterOuterAlt(_localctx, 7);
				{
				setState(1087);
				match(New);
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 8);
				{
				setState(1088);
				match(Var);
				}
				break;
			case Catch:
				enterOuterAlt(_localctx, 9);
				{
				setState(1089);
				match(Catch);
				}
				break;
			case Finally:
				enterOuterAlt(_localctx, 10);
				{
				setState(1090);
				match(Finally);
				}
				break;
			case Return:
				enterOuterAlt(_localctx, 11);
				{
				setState(1091);
				match(Return);
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 12);
				{
				setState(1092);
				match(Void);
				}
				break;
			case Continue:
				enterOuterAlt(_localctx, 13);
				{
				setState(1093);
				match(Continue);
				}
				break;
			case For:
				enterOuterAlt(_localctx, 14);
				{
				setState(1094);
				match(For);
				}
				break;
			case Switch:
				enterOuterAlt(_localctx, 15);
				{
				setState(1095);
				match(Switch);
				}
				break;
			case While:
				enterOuterAlt(_localctx, 16);
				{
				setState(1096);
				match(While);
				}
				break;
			case Debugger:
				enterOuterAlt(_localctx, 17);
				{
				setState(1097);
				match(Debugger);
				}
				break;
			case Function_:
				enterOuterAlt(_localctx, 18);
				{
				setState(1098);
				match(Function_);
				}
				break;
			case This:
				enterOuterAlt(_localctx, 19);
				{
				setState(1099);
				match(This);
				}
				break;
			case With:
				enterOuterAlt(_localctx, 20);
				{
				setState(1100);
				match(With);
				}
				break;
			case Default:
				enterOuterAlt(_localctx, 21);
				{
				setState(1101);
				match(Default);
				}
				break;
			case If:
				enterOuterAlt(_localctx, 22);
				{
				setState(1102);
				match(If);
				}
				break;
			case Throw:
				enterOuterAlt(_localctx, 23);
				{
				setState(1103);
				match(Throw);
				}
				break;
			case Delete:
				enterOuterAlt(_localctx, 24);
				{
				setState(1104);
				match(Delete);
				}
				break;
			case In:
				enterOuterAlt(_localctx, 25);
				{
				setState(1105);
				match(In);
				}
				break;
			case Try:
				enterOuterAlt(_localctx, 26);
				{
				setState(1106);
				match(Try);
				}
				break;
			case Class:
				enterOuterAlt(_localctx, 27);
				{
				setState(1107);
				match(Class);
				}
				break;
			case Enum:
				enterOuterAlt(_localctx, 28);
				{
				setState(1108);
				match(Enum);
				}
				break;
			case Extends:
				enterOuterAlt(_localctx, 29);
				{
				setState(1109);
				match(Extends);
				}
				break;
			case Super:
				enterOuterAlt(_localctx, 30);
				{
				setState(1110);
				match(Super);
				}
				break;
			case Const:
				enterOuterAlt(_localctx, 31);
				{
				setState(1111);
				match(Const);
				}
				break;
			case Export:
				enterOuterAlt(_localctx, 32);
				{
				setState(1112);
				match(Export);
				}
				break;
			case Import:
				enterOuterAlt(_localctx, 33);
				{
				setState(1113);
				match(Import);
				}
				break;
			case Implements:
				enterOuterAlt(_localctx, 34);
				{
				setState(1114);
				match(Implements);
				}
				break;
			case StrictLet:
			case NonStrictLet:
				enterOuterAlt(_localctx, 35);
				{
				setState(1115);
				let_();
				}
				break;
			case Private:
				enterOuterAlt(_localctx, 36);
				{
				setState(1116);
				match(Private);
				}
				break;
			case Public:
				enterOuterAlt(_localctx, 37);
				{
				setState(1117);
				match(Public);
				}
				break;
			case Interface:
				enterOuterAlt(_localctx, 38);
				{
				setState(1118);
				match(Interface);
				}
				break;
			case Package:
				enterOuterAlt(_localctx, 39);
				{
				setState(1119);
				match(Package);
				}
				break;
			case Protected:
				enterOuterAlt(_localctx, 40);
				{
				setState(1120);
				match(Protected);
				}
				break;
			case Static:
				enterOuterAlt(_localctx, 41);
				{
				setState(1121);
				match(Static);
				}
				break;
			case Yield:
				enterOuterAlt(_localctx, 42);
				{
				setState(1122);
				match(Yield);
				}
				break;
			case YieldStar:
				enterOuterAlt(_localctx, 43);
				{
				setState(1123);
				match(YieldStar);
				}
				break;
			case Async:
				enterOuterAlt(_localctx, 44);
				{
				setState(1124);
				match(Async);
				}
				break;
			case Await:
				enterOuterAlt(_localctx, 45);
				{
				setState(1125);
				match(Await);
				}
				break;
			case From:
				enterOuterAlt(_localctx, 46);
				{
				setState(1126);
				match(From);
				}
				break;
			case As:
				enterOuterAlt(_localctx, 47);
				{
				setState(1127);
				match(As);
				}
				break;
			case Of:
				enterOuterAlt(_localctx, 48);
				{
				setState(1128);
				match(Of);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Let_Context extends ParserRuleContext {
		public TerminalNode NonStrictLet() { return getToken(JavaScriptParser.NonStrictLet, 0); }
		public TerminalNode StrictLet() { return getToken(JavaScriptParser.StrictLet, 0); }
		public Let_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterLet_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitLet_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitLet_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_Context let_() throws RecognitionException {
		Let_Context _localctx = new Let_Context(_ctx, getState());
		enterRule(_localctx, 180, RULE_let_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1131);
			_la = _input.LA(1);
			if ( !(_la==StrictLet || _la==NonStrictLet) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EosContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(JavaScriptParser.SemiColon, 0); }
		public TerminalNode EOF() { return getToken(JavaScriptParser.EOF, 0); }
		public List<TerminalNode> LineTerminator() { return getTokens(JavaScriptParser.LineTerminator); }
		public TerminalNode LineTerminator(int i) {
			return getToken(JavaScriptParser.LineTerminator, i);
		}
		public EosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).enterEos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaScriptParserListener ) ((JavaScriptParserListener)listener).exitEos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaScriptParserVisitor ) return ((JavaScriptParserVisitor<? extends T>)visitor).visitEos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EosContext eos() throws RecognitionException {
		EosContext _localctx = new EosContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_eos);
		try {
			int _alt;
			setState(1143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1133);
				match(SemiColon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1134);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1135);
						match(LineTerminator);
						}
						}
					}
					setState(1140);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1141);
				if (!(this.lineTerminatorAhead())) throw new FailedPredicateException(this, "this.lineTerminatorAhead()");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1142);
				if (!(this.closeBrace())) throw new FailedPredicateException(this, "this.closeBrace()");
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 26:
			return expressionStatement_sempred((ExpressionStatementContext)_localctx, predIndex);
		case 33:
			return continueStatement_sempred((ContinueStatementContext)_localctx, predIndex);
		case 34:
			return breakStatement_sempred((BreakStatementContext)_localctx, predIndex);
		case 35:
			return returnStatement_sempred((ReturnStatementContext)_localctx, predIndex);
		case 36:
			return yieldStatement_sempred((YieldStatementContext)_localctx, predIndex);
		case 44:
			return throwStatement_sempred((ThrowStatementContext)_localctx, predIndex);
		case 52:
			return classElement_sempred((ClassElementContext)_localctx, predIndex);
		case 53:
			return methodDefinition_sempred((MethodDefinitionContext)_localctx, predIndex);
		case 71:
			return singleExpression_sempred((SingleExpressionContext)_localctx, predIndex);
		case 84:
			return getter_sempred((GetterContext)_localctx, predIndex);
		case 85:
			return setter_sempred((SetterContext)_localctx, predIndex);
		case 91:
			return eos_sempred((EosContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expressionStatement_sempred(ExpressionStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return this.notOpenBraceAndNotFunction();
		}
		return true;
	}
	private boolean continueStatement_sempred(ContinueStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean breakStatement_sempred(BreakStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean returnStatement_sempred(ReturnStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean yieldStatement_sempred(YieldStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean throwStatement_sempred(ThrowStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean classElement_sempred(ClassElementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return this.n("static");
		case 7:
			return this.n("static");
		case 8:
			return this.n("static");
		}
		return true;
	}
	private boolean methodDefinition_sempred(MethodDefinitionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return this.notLineTerminator();
		}
		return true;
	}
	private boolean singleExpression_sempred(SingleExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 47);
		case 11:
			return precpred(_ctx, 27);
		case 12:
			return precpred(_ctx, 26);
		case 13:
			return precpred(_ctx, 25);
		case 14:
			return precpred(_ctx, 24);
		case 15:
			return precpred(_ctx, 23);
		case 16:
			return precpred(_ctx, 22);
		case 17:
			return precpred(_ctx, 21);
		case 18:
			return precpred(_ctx, 20);
		case 19:
			return precpred(_ctx, 19);
		case 20:
			return precpred(_ctx, 18);
		case 21:
			return precpred(_ctx, 17);
		case 22:
			return precpred(_ctx, 16);
		case 23:
			return precpred(_ctx, 15);
		case 24:
			return precpred(_ctx, 14);
		case 25:
			return precpred(_ctx, 13);
		case 26:
			return precpred(_ctx, 12);
		case 27:
			return precpred(_ctx, 11);
		case 28:
			return precpred(_ctx, 46);
		case 29:
			return precpred(_ctx, 45);
		case 30:
			return precpred(_ctx, 41);
		case 31:
			return precpred(_ctx, 39);
		case 32:
			return this.notLineTerminator();
		case 33:
			return precpred(_ctx, 38);
		case 34:
			return this.notLineTerminator();
		case 35:
			return precpred(_ctx, 9);
		}
		return true;
	}
	private boolean getter_sempred(GetterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 36:
			return this.n("get");
		}
		return true;
	}
	private boolean setter_sempred(SetterContext _localctx, int predIndex) {
		switch (predIndex) {
		case 37:
			return this.n("set");
		}
		return true;
	}
	private boolean eos_sempred(EosContext _localctx, int predIndex) {
		switch (predIndex) {
		case 38:
			return this.lineTerminatorAhead();
		case 39:
			return this.closeBrace();
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0087\u047a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007"+
		"Y\u0002Z\u0007Z\u0002[\u0007[\u0001\u0000\u0003\u0000\u00ba\b\u0000\u0001"+
		"\u0000\u0003\u0000\u00bd\b\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0005\u0001\u00c3\b\u0001\n\u0001\f\u0001\u00c6\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00df\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u00e6\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006\u00eb\b"+
		"\u0006\u000b\u0006\f\u0006\u00ec\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0003\b\u00f3\b\b\u0001\b\u0001\b\u0003\b\u00f7\b\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0003\b\u00fe\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u0104\b\t\n\t\f\t\u0107\t\t\u0001\t\u0001\t\u0003\t\u010b\b"+
		"\t\u0003\t\u010d\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n\u0114"+
		"\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u0118\b\u000b\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u0121\b\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u0125\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u012d\b\u0010"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u0131\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u0135\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u013e\b\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0146\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u014a\b\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0150\b\u0013\n"+
		"\u0013\f\u0013\u0153\t\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0157"+
		"\b\u0013\u0003\u0013\u0159\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0160\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u0165\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u016e\b\u0017"+
		"\n\u0017\f\u0017\u0171\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u0176\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u0184\b\u001b\n\u001b\f\u001b\u0187\t\u001b"+
		"\u0001\u001b\u0003\u001b\u018a\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0003\u001e\u01a7\b\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01ab\b"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01af\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0003"+
		"\u001e\u01b8\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01c1\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u01c6\b\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01cd\b\u001e\u0001\u001f\u0001"+
		"\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0003!\u01d6\b!\u0001!\u0001"+
		"!\u0001\"\u0001\"\u0001\"\u0003\"\u01dd\b\"\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0003#\u01e4\b#\u0001#\u0001#\u0001$\u0001$\u0001$\u0003$\u01eb"+
		"\b$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0003\'\u01fd\b\'\u0001"+
		"\'\u0001\'\u0003\'\u0201\b\'\u0003\'\u0203\b\'\u0001\'\u0001\'\u0001("+
		"\u0004(\u0208\b(\u000b(\f(\u0209\u0001)\u0001)\u0001)\u0001)\u0003)\u0210"+
		"\b)\u0001*\u0001*\u0001*\u0003*\u0215\b*\u0001+\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0003-\u0224"+
		"\b-\u0001-\u0003-\u0227\b-\u0001.\u0001.\u0001.\u0003.\u022c\b.\u0001"+
		".\u0001.\u0003.\u0230\b.\u0001.\u0001.\u0001/\u0001/\u0001/\u00010\u0001"+
		"0\u00010\u00011\u00031\u023b\b1\u00011\u00011\u00031\u023f\b1\u00011\u0001"+
		"1\u00011\u00031\u0244\b1\u00011\u00011\u00011\u00012\u00012\u00012\u0001"+
		"2\u00013\u00013\u00033\u024f\b3\u00013\u00013\u00053\u0253\b3\n3\f3\u0256"+
		"\t3\u00013\u00013\u00014\u00014\u00014\u00034\u025d\b4\u00014\u00014\u0001"+
		"4\u00014\u00034\u0263\b4\u00014\u00014\u00014\u00014\u00034\u0269\b4\u0001"+
		"4\u00014\u00034\u026d\b4\u00015\u00015\u00035\u0271\b5\u00015\u00035\u0274"+
		"\b5\u00015\u00015\u00015\u00035\u0279\b5\u00015\u00015\u00015\u00015\u0003"+
		"5\u027f\b5\u00015\u00015\u00015\u00015\u00015\u00015\u00035\u0287\b5\u0001"+
		"5\u00015\u00015\u00035\u028c\b5\u00015\u00015\u00015\u00035\u0291\b5\u0001"+
		"6\u00016\u00036\u0295\b6\u00017\u00017\u00037\u0299\b7\u00018\u00018\u0001"+
		"8\u00019\u00019\u00019\u00059\u02a1\b9\n9\f9\u02a4\t9\u00019\u00019\u0003"+
		"9\u02a8\b9\u00019\u00039\u02ab\b9\u0001:\u0001:\u0001:\u0003:\u02b0\b"+
		":\u0001;\u0001;\u0001;\u0001<\u0001<\u0003<\u02b7\b<\u0001<\u0001<\u0001"+
		"=\u0004=\u02bc\b=\u000b=\f=\u02bd\u0001>\u0001>\u0001>\u0001>\u0001?\u0005"+
		"?\u02c5\b?\n?\f?\u02c8\t?\u0001?\u0003?\u02cb\b?\u0001?\u0004?\u02ce\b"+
		"?\u000b?\f?\u02cf\u0001?\u0005?\u02d3\b?\n?\f?\u02d6\t?\u0001?\u0005?"+
		"\u02d9\b?\n?\f?\u02dc\t?\u0001@\u0003@\u02df\b@\u0001@\u0001@\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0003"+
		"A\u02ee\bA\u0001A\u0003A\u02f1\bA\u0001A\u0001A\u0001A\u0003A\u02f6\b"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0003A\u0307\bA\u0001A\u0003A\u030a"+
		"\bA\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0003B\u0313\bB\u0001"+
		"C\u0001C\u0001C\u0001C\u0005C\u0319\bC\nC\fC\u031c\tC\u0001C\u0003C\u031f"+
		"\bC\u0003C\u0321\bC\u0001C\u0001C\u0001D\u0003D\u0326\bD\u0001D\u0001"+
		"D\u0003D\u032a\bD\u0001E\u0001E\u0001E\u0005E\u032f\bE\nE\fE\u0332\tE"+
		"\u0001F\u0001F\u0001G\u0001G\u0001G\u0001G\u0003G\u033a\bG\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0003G\u036e"+
		"\bG\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0003G\u03ac\bG\u0001G\u0001G\u0001G\u0001G\u0001G\u0001G\u0003G\u03b4"+
		"\bG\u0001G\u0001G\u0003G\u03b8\bG\u0001G\u0001G\u0001G\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001G\u0001G\u0001G\u0005G\u03c5\bG\nG\fG\u03c8\tG\u0001"+
		"H\u0001H\u0001H\u0001I\u0001I\u0001I\u0001I\u0003I\u03d1\bI\u0001J\u0001"+
		"J\u0001J\u0001J\u0005J\u03d7\bJ\nJ\fJ\u03da\tJ\u0001J\u0003J\u03dd\bJ"+
		"\u0003J\u03df\bJ\u0001J\u0001J\u0001K\u0001K\u0003K\u03e5\bK\u0001K\u0001"+
		"K\u0003K\u03e9\bK\u0001K\u0001K\u0003K\u03ed\bK\u0001K\u0001K\u0001K\u0001"+
		"K\u0003K\u03f3\bK\u0001K\u0001K\u0001K\u0001K\u0003K\u03f9\bK\u0001L\u0001"+
		"L\u0001L\u0003L\u03fe\bL\u0001L\u0001L\u0003L\u0402\bL\u0001M\u0001M\u0003"+
		"M\u0406\bM\u0001N\u0001N\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0003O\u0411\bO\u0001P\u0001P\u0005P\u0415\bP\nP\fP\u0418\tP\u0001P"+
		"\u0001P\u0001Q\u0001Q\u0001Q\u0001Q\u0001Q\u0003Q\u0421\bQ\u0001R\u0001"+
		"R\u0001S\u0001S\u0001T\u0001T\u0001T\u0001T\u0001U\u0001U\u0001U\u0001"+
		"U\u0001V\u0001V\u0003V\u0431\bV\u0001W\u0001W\u0001X\u0001X\u0001X\u0003"+
		"X\u0438\bX\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001"+
		"Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001"+
		"Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001"+
		"Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001"+
		"Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0001Y\u0003"+
		"Y\u046a\bY\u0001Z\u0001Z\u0001[\u0001[\u0001[\u0005[\u0471\b[\n[\f[\u0474"+
		"\t[\u0001[\u0001[\u0003[\u0478\b[\u0001[\u0000\u0001\u008e\\\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e"+
		"\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6"+
		"\u0000\r\u0003\u0000hhrr||\u0002\u0000QRnn\u0001\u0000hi\u0001\u0000\u001a"+
		"\u001c\u0001\u0000\u0016\u0017\u0001\u0000 \"\u0001\u0000#&\u0001\u0000"+
		"\'*\u0001\u00000<\u0001\u0000@D\u0001\u0000EH\u0004\u0000ehqquu||\u0001"+
		"\u0000tu\u0520\u0000\u00b9\u0001\u0000\u0000\u0000\u0002\u00c0\u0001\u0000"+
		"\u0000\u0000\u0004\u00c7\u0001\u0000\u0000\u0000\u0006\u00de\u0001\u0000"+
		"\u0000\u0000\b\u00e0\u0001\u0000\u0000\u0000\n\u00e3\u0001\u0000\u0000"+
		"\u0000\f\u00ea\u0001\u0000\u0000\u0000\u000e\u00ee\u0001\u0000\u0000\u0000"+
		"\u0010\u00fd\u0001\u0000\u0000\u0000\u0012\u00ff\u0001\u0000\u0000\u0000"+
		"\u0014\u0110\u0001\u0000\u0000\u0000\u0016\u0117\u0001\u0000\u0000\u0000"+
		"\u0018\u0119\u0001\u0000\u0000\u0000\u001a\u011b\u0001\u0000\u0000\u0000"+
		"\u001c\u0120\u0001\u0000\u0000\u0000\u001e\u0126\u0001\u0000\u0000\u0000"+
		" \u0129\u0001\u0000\u0000\u0000\"\u013d\u0001\u0000\u0000\u0000$\u0149"+
		"\u0001\u0000\u0000\u0000&\u014b\u0001\u0000\u0000\u0000(\u015c\u0001\u0000"+
		"\u0000\u0000*\u0164\u0001\u0000\u0000\u0000,\u0166\u0001\u0000\u0000\u0000"+
		".\u0169\u0001\u0000\u0000\u00000\u0172\u0001\u0000\u0000\u00002\u0177"+
		"\u0001\u0000\u0000\u00004\u0179\u0001\u0000\u0000\u00006\u017d\u0001\u0000"+
		"\u0000\u00008\u018b\u0001\u0000\u0000\u0000:\u018e\u0001\u0000\u0000\u0000"+
		"<\u01cc\u0001\u0000\u0000\u0000>\u01ce\u0001\u0000\u0000\u0000@\u01d0"+
		"\u0001\u0000\u0000\u0000B\u01d2\u0001\u0000\u0000\u0000D\u01d9\u0001\u0000"+
		"\u0000\u0000F\u01e0\u0001\u0000\u0000\u0000H\u01e7\u0001\u0000\u0000\u0000"+
		"J\u01ee\u0001\u0000\u0000\u0000L\u01f4\u0001\u0000\u0000\u0000N\u01fa"+
		"\u0001\u0000\u0000\u0000P\u0207\u0001\u0000\u0000\u0000R\u020b\u0001\u0000"+
		"\u0000\u0000T\u0211\u0001\u0000\u0000\u0000V\u0216\u0001\u0000\u0000\u0000"+
		"X\u021a\u0001\u0000\u0000\u0000Z\u021f\u0001\u0000\u0000\u0000\\\u0228"+
		"\u0001\u0000\u0000\u0000^\u0233\u0001\u0000\u0000\u0000`\u0236\u0001\u0000"+
		"\u0000\u0000b\u023a\u0001\u0000\u0000\u0000d\u0248\u0001\u0000\u0000\u0000"+
		"f\u024e\u0001\u0000\u0000\u0000h\u026c\u0001\u0000\u0000\u0000j\u0290"+
		"\u0001\u0000\u0000\u0000l\u0292\u0001\u0000\u0000\u0000n\u0298\u0001\u0000"+
		"\u0000\u0000p\u029a\u0001\u0000\u0000\u0000r\u02aa\u0001\u0000\u0000\u0000"+
		"t\u02ac\u0001\u0000\u0000\u0000v\u02b1\u0001\u0000\u0000\u0000x\u02b4"+
		"\u0001\u0000\u0000\u0000z\u02bb\u0001\u0000\u0000\u0000|\u02bf\u0001\u0000"+
		"\u0000\u0000~\u02c6\u0001\u0000\u0000\u0000\u0080\u02de\u0001\u0000\u0000"+
		"\u0000\u0082\u0309\u0001\u0000\u0000\u0000\u0084\u0312\u0001\u0000\u0000"+
		"\u0000\u0086\u0314\u0001\u0000\u0000\u0000\u0088\u0325\u0001\u0000\u0000"+
		"\u0000\u008a\u032b\u0001\u0000\u0000\u0000\u008c\u0333\u0001\u0000\u0000"+
		"\u0000\u008e\u036d\u0001\u0000\u0000\u0000\u0090\u03c9\u0001\u0000\u0000"+
		"\u0000\u0092\u03d0\u0001\u0000\u0000\u0000\u0094\u03d2\u0001\u0000\u0000"+
		"\u0000\u0096\u03f8\u0001\u0000\u0000\u0000\u0098\u0401\u0001\u0000\u0000"+
		"\u0000\u009a\u0405\u0001\u0000\u0000\u0000\u009c\u0407\u0001\u0000\u0000"+
		"\u0000\u009e\u0410\u0001\u0000\u0000\u0000\u00a0\u0412\u0001\u0000\u0000"+
		"\u0000\u00a2\u0420\u0001\u0000\u0000\u0000\u00a4\u0422\u0001\u0000\u0000"+
		"\u0000\u00a6\u0424\u0001\u0000\u0000\u0000\u00a8\u0426\u0001\u0000\u0000"+
		"\u0000\u00aa\u042a\u0001\u0000\u0000\u0000\u00ac\u0430\u0001\u0000\u0000"+
		"\u0000\u00ae\u0432\u0001\u0000\u0000\u0000\u00b0\u0437\u0001\u0000\u0000"+
		"\u0000\u00b2\u0469\u0001\u0000\u0000\u0000\u00b4\u046b\u0001\u0000\u0000"+
		"\u0000\u00b6\u0477\u0001\u0000\u0000\u0000\u00b8\u00ba\u0005\u0001\u0000"+
		"\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bc\u0001\u0000\u0000\u0000\u00bb\u00bd\u0003z=\u0000\u00bc"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u0000\u0000\u0001\u00bf"+
		"\u0001\u0001\u0000\u0000\u0000\u00c0\u00c4\u0003\u0004\u0002\u0000\u00c1"+
		"\u00c3\u0005\u0080\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c5\u0001\u0000\u0000\u0000\u00c5\u0003\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c8\u0003\u0006\u0003\u0000\u00c8"+
		"\u0005\u0001\u0000\u0000\u0000\u00c9\u00df\u0003\n\u0005\u0000\u00ca\u00df"+
		"\u0003\b\u0004\u0000\u00cb\u00df\u00034\u001a\u0000\u00cc\u00df\u0003"+
		",\u0016\u0000\u00cd\u00df\u0003\u000e\u0007\u0000\u00ce\u00df\u0003\""+
		"\u0011\u0000\u00cf\u00df\u00032\u0019\u0000\u00d0\u00df\u0003d2\u0000"+
		"\u00d1\u00df\u00036\u001b\u0000\u00d2\u00df\u0003b1\u0000\u00d3\u00df"+
		"\u0003<\u001e\u0000\u00d4\u00df\u0003B!\u0000\u00d5\u00df\u0003D\"\u0000"+
		"\u00d6\u00df\u0003F#\u0000\u00d7\u00df\u0003H$\u0000\u00d8\u00df\u0003"+
		"J%\u0000\u00d9\u00df\u0003V+\u0000\u00da\u00df\u0003L&\u0000\u00db\u00df"+
		"\u0003X,\u0000\u00dc\u00df\u0003Z-\u0000\u00dd\u00df\u0003`0\u0000\u00de"+
		"\u00c9\u0001\u0000\u0000\u0000\u00de\u00ca\u0001\u0000\u0000\u0000\u00de"+
		"\u00cb\u0001\u0000\u0000\u0000\u00de\u00cc\u0001\u0000\u0000\u0000\u00de"+
		"\u00cd\u0001\u0000\u0000\u0000\u00de\u00ce\u0001\u0000\u0000\u0000\u00de"+
		"\u00cf\u0001\u0000\u0000\u0000\u00de\u00d0\u0001\u0000\u0000\u0000\u00de"+
		"\u00d1\u0001\u0000\u0000\u0000\u00de\u00d2\u0001\u0000\u0000\u0000\u00de"+
		"\u00d3\u0001\u0000\u0000\u0000\u00de\u00d4\u0001\u0000\u0000\u0000\u00de"+
		"\u00d5\u0001\u0000\u0000\u0000\u00de\u00d6\u0001\u0000\u0000\u0000\u00de"+
		"\u00d7\u0001\u0000\u0000\u0000\u00de\u00d8\u0001\u0000\u0000\u0000\u00de"+
		"\u00d9\u0001\u0000\u0000\u0000\u00de\u00da\u0001\u0000\u0000\u0000\u00de"+
		"\u00db\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de"+
		"\u00dd\u0001\u0000\u0000\u0000\u00df\u0007\u0001\u0000\u0000\u0000\u00e0"+
		"\u00e1\u0003\u0094J\u0000\u00e1\u00e2\u0003\u00b6[\u0000\u00e2\t\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e5\u0005\t\u0000\u0000\u00e4\u00e6\u0003\f"+
		"\u0006\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\u000b"+
		"\u0000\u0000\u00e8\u000b\u0001\u0000\u0000\u0000\u00e9\u00eb\u0003\u0004"+
		"\u0002\u0000\u00ea\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000"+
		"\u0000\u0000\u00ed\r\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005p\u0000"+
		"\u0000\u00ef\u00f0\u0003\u0010\b\u0000\u00f0\u000f\u0001\u0000\u0000\u0000"+
		"\u00f1\u00f3\u0003\u001a\r\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f7\u0003\u001c\u000e\u0000\u00f5\u00f7\u0003\u0012\t\u0000\u00f6\u00f4"+
		"\u0001\u0000\u0000\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f8"+
		"\u0001\u0000\u0000\u0000\u00f8\u00f9\u0003\u001e\u000f\u0000\u00f9\u00fa"+
		"\u0003\u00b6[\u0000\u00fa\u00fe\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005"+
		"}\u0000\u0000\u00fc\u00fe\u0003\u00b6[\u0000\u00fd\u00f2\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fe\u0011\u0001\u0000\u0000"+
		"\u0000\u00ff\u0105\u0005\t\u0000\u0000\u0100\u0101\u0003\u0014\n\u0000"+
		"\u0101\u0102\u0005\r\u0000\u0000\u0102\u0104\u0001\u0000\u0000\u0000\u0103"+
		"\u0100\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000\u0000\u0000\u0105"+
		"\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106"+
		"\u010c\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0108"+
		"\u010a\u0003\u0014\n\u0000\u0109\u010b\u0005\r\u0000\u0000\u010a\u0109"+
		"\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\u010d"+
		"\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0005\u000b\u0000\u0000\u010f\u0013\u0001\u0000\u0000\u0000\u0110\u0113"+
		"\u0003\u0016\u000b\u0000\u0111\u0112\u0005e\u0000\u0000\u0112\u0114\u0003"+
		"\u0018\f\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000"+
		"\u0000\u0000\u0114\u0015\u0001\u0000\u0000\u0000\u0115\u0118\u0003\u00ac"+
		"V\u0000\u0116\u0118\u0005}\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000"+
		"\u0117\u0116\u0001\u0000\u0000\u0000\u0118\u0017\u0001\u0000\u0000\u0000"+
		"\u0119\u011a\u0007\u0000\u0000\u0000\u011a\u0019\u0001\u0000\u0000\u0000"+
		"\u011b\u011c\u0003 \u0010\u0000\u011c\u011d\u0005\r\u0000\u0000\u011d"+
		"\u001b\u0001\u0000\u0000\u0000\u011e\u0121\u0005\u001a\u0000\u0000\u011f"+
		"\u0121\u0003\u00acV\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0120\u011f"+
		"\u0001\u0000\u0000\u0000\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0123"+
		"\u0005e\u0000\u0000\u0123\u0125\u0003\u00acV\u0000\u0124\u0122\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u001d\u0001\u0000"+
		"\u0000\u0000\u0126\u0127\u0005f\u0000\u0000\u0127\u0128\u0005}\u0000\u0000"+
		"\u0128\u001f\u0001\u0000\u0000\u0000\u0129\u012c\u0003\u00acV\u0000\u012a"+
		"\u012b\u0005e\u0000\u0000\u012b\u012d\u0003\u00acV\u0000\u012c\u012a\u0001"+
		"\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d!\u0001\u0000"+
		"\u0000\u0000\u012e\u0130\u0005o\u0000\u0000\u012f\u0131\u0005_\u0000\u0000"+
		"\u0130\u012f\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000"+
		"\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0135\u0003$\u0012\u0000\u0133"+
		"\u0135\u0003*\u0015\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0134\u0133"+
		"\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0137"+
		"\u0003\u00b6[\u0000\u0137\u013e\u0001\u0000\u0000\u0000\u0138\u0139\u0005"+
		"o\u0000\u0000\u0139\u013a\u0005_\u0000\u0000\u013a\u013b\u0003\u008cF"+
		"\u0000\u013b\u013c\u0003\u00b6[\u0000\u013c\u013e\u0001\u0000\u0000\u0000"+
		"\u013d\u012e\u0001\u0000\u0000\u0000\u013d\u0138\u0001\u0000\u0000\u0000"+
		"\u013e#\u0001\u0000\u0000\u0000\u013f\u0140\u0003\u001c\u000e\u0000\u0140"+
		"\u0141\u0003\u001e\u000f\u0000\u0141\u0142\u0003\u00b6[\u0000\u0142\u014a"+
		"\u0001\u0000\u0000\u0000\u0143\u0145\u0003&\u0013\u0000\u0144\u0146\u0003"+
		"\u001e\u000f\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0145\u0146\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0003"+
		"\u00b6[\u0000\u0148\u014a\u0001\u0000\u0000\u0000\u0149\u013f\u0001\u0000"+
		"\u0000\u0000\u0149\u0143\u0001\u0000\u0000\u0000\u014a%\u0001\u0000\u0000"+
		"\u0000\u014b\u0151\u0005\t\u0000\u0000\u014c\u014d\u0003(\u0014\u0000"+
		"\u014d\u014e\u0005\r\u0000\u0000\u014e\u0150\u0001\u0000\u0000\u0000\u014f"+
		"\u014c\u0001\u0000\u0000\u0000\u0150\u0153\u0001\u0000\u0000\u0000\u0151"+
		"\u014f\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152"+
		"\u0158\u0001\u0000\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0154"+
		"\u0156\u0003(\u0014\u0000\u0155\u0157\u0005\r\u0000\u0000\u0156\u0155"+
		"\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0159"+
		"\u0001\u0000\u0000\u0000\u0158\u0154\u0001\u0000\u0000\u0000\u0158\u0159"+
		"\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015b"+
		"\u0005\u000b\u0000\u0000\u015b\'\u0001\u0000\u0000\u0000\u015c\u015f\u0003"+
		"\u0016\u000b\u0000\u015d\u015e\u0005e\u0000\u0000\u015e\u0160\u0003\u0016"+
		"\u000b\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000"+
		"\u0000\u0000\u0160)\u0001\u0000\u0000\u0000\u0161\u0165\u0003,\u0016\u0000"+
		"\u0162\u0165\u0003d2\u0000\u0163\u0165\u0003b1\u0000\u0164\u0161\u0001"+
		"\u0000\u0000\u0000\u0164\u0162\u0001\u0000\u0000\u0000\u0164\u0163\u0001"+
		"\u0000\u0000\u0000\u0165+\u0001\u0000\u0000\u0000\u0166\u0167\u0003.\u0017"+
		"\u0000\u0167\u0168\u0003\u00b6[\u0000\u0168-\u0001\u0000\u0000\u0000\u0169"+
		"\u016a\u0003@ \u0000\u016a\u016f\u00030\u0018\u0000\u016b\u016c\u0005"+
		"\r\u0000\u0000\u016c\u016e\u00030\u0018\u0000\u016d\u016b\u0001\u0000"+
		"\u0000\u0000\u016e\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000"+
		"\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170/\u0001\u0000\u0000"+
		"\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0172\u0175\u0003\u0092I\u0000"+
		"\u0173\u0174\u0005\u000e\u0000\u0000\u0174\u0176\u0003\u008cF\u0000\u0175"+
		"\u0173\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176"+
		"1\u0001\u0000\u0000\u0000\u0177\u0178\u0005\f\u0000\u0000\u01783\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0004\u001a\u0000\u0000\u017a\u017b\u0003"+
		"\u008aE\u0000\u017b\u017c\u0003\u00b6[\u0000\u017c5\u0001\u0000\u0000"+
		"\u0000\u017d\u017e\u0005`\u0000\u0000\u017e\u017f\u0003>\u001f\u0000\u017f"+
		"\u0180\u0003\u008aE\u0000\u0180\u0181\u0005\b\u0000\u0000\u0181\u0185"+
		"\u0003\u0004\u0002\u0000\u0182\u0184\u0003:\u001d\u0000\u0183\u0182\u0001"+
		"\u0000\u0000\u0000\u0184\u0187\u0001\u0000\u0000\u0000\u0185\u0183\u0001"+
		"\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0189\u0001"+
		"\u0000\u0000\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u018a\u0003"+
		"8\u001c\u0000\u0189\u0188\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000"+
		"\u0000\u0000\u018a7\u0001\u0000\u0000\u0000\u018b\u018c\u0005N\u0000\u0000"+
		"\u018c\u018d\u0003\u0004\u0002\u0000\u018d9\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\u0005O\u0000\u0000\u018f\u0190\u0003>\u001f\u0000\u0190\u0191\u0003"+
		"\u008aE\u0000\u0191\u0192\u0005\b\u0000\u0000\u0192\u0193\u0003\u0004"+
		"\u0002\u0000\u0193;\u0001\u0000\u0000\u0000\u0194\u0195\u0005J\u0000\u0000"+
		"\u0195\u0196\u0003\u0004\u0002\u0000\u0196\u0197\u0005Z\u0000\u0000\u0197"+
		"\u0198\u0003>\u001f\u0000\u0198\u0199\u0003\u008aE\u0000\u0199\u019a\u0005"+
		"\b\u0000\u0000\u019a\u019b\u0003\u00b6[\u0000\u019b\u01cd\u0001\u0000"+
		"\u0000\u0000\u019c\u019d\u0005Z\u0000\u0000\u019d\u019e\u0003>\u001f\u0000"+
		"\u019e\u019f\u0003\u008aE\u0000\u019f\u01a0\u0005\b\u0000\u0000\u01a0"+
		"\u01a1\u0003\u0004\u0002\u0000\u01a1\u01cd\u0001\u0000\u0000\u0000\u01a2"+
		"\u01a3\u0005X\u0000\u0000\u01a3\u01a6\u0003>\u001f\u0000\u01a4\u01a7\u0003"+
		"\u008aE\u0000\u01a5\u01a7\u0003.\u0017\u0000\u01a6\u01a4\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a5\u0001\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000"+
		"\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8\u01aa\u0005\f\u0000\u0000"+
		"\u01a9\u01ab\u0003\u008aE\u0000\u01aa\u01a9\u0001\u0000\u0000\u0000\u01aa"+
		"\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac"+
		"\u01ae\u0005\f\u0000\u0000\u01ad\u01af\u0003\u008aE\u0000\u01ae\u01ad"+
		"\u0001\u0000\u0000\u0000\u01ae\u01af\u0001\u0000\u0000\u0000\u01af\u01b0"+
		"\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005\b\u0000\u0000\u01b1\u01b2\u0003"+
		"\u0004\u0002\u0000\u01b2\u01cd\u0001\u0000\u0000\u0000\u01b3\u01b4\u0005"+
		"X\u0000\u0000\u01b4\u01b7\u0003>\u001f\u0000\u01b5\u01b8\u0003\u008cF"+
		"\u0000\u01b6\u01b8\u0003.\u0017\u0000\u01b7\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b7\u01b6\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000"+
		"\u01b9\u01ba\u0005c\u0000\u0000\u01ba\u01bb\u0003\u008aE\u0000\u01bb\u01bc"+
		"\u0005\b\u0000\u0000\u01bc\u01bd\u0003\u0004\u0002\u0000\u01bd\u01cd\u0001"+
		"\u0000\u0000\u0000\u01be\u01c0\u0005X\u0000\u0000\u01bf\u01c1\u0005r\u0000"+
		"\u0000\u01c0\u01bf\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000"+
		"\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2\u01c5\u0003>\u001f\u0000"+
		"\u01c3\u01c6\u0003\u008cF\u0000\u01c4\u01c6\u0003.\u0017\u0000\u01c5\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000\u01c6\u01c7"+
		"\u0001\u0000\u0000\u0000\u01c7\u01c8\u0005g\u0000\u0000\u01c8\u01c9\u0003"+
		"\u008aE\u0000\u01c9\u01ca\u0005\b\u0000\u0000\u01ca\u01cb\u0003\u0004"+
		"\u0002\u0000\u01cb\u01cd\u0001\u0000\u0000\u0000\u01cc\u0194\u0001\u0000"+
		"\u0000\u0000\u01cc\u019c\u0001\u0000\u0000\u0000\u01cc\u01a2\u0001\u0000"+
		"\u0000\u0000\u01cc\u01b3\u0001\u0000\u0000\u0000\u01cc\u01be\u0001\u0000"+
		"\u0000\u0000\u01cd=\u0001\u0000\u0000\u0000\u01ce\u01cf\u0005\u0007\u0000"+
		"\u0000\u01cf?\u0001\u0000\u0000\u0000\u01d0\u01d1\u0007\u0001\u0000\u0000"+
		"\u01d1A\u0001\u0000\u0000\u0000\u01d2\u01d5\u0005W\u0000\u0000\u01d3\u01d4"+
		"\u0004!\u0001\u0000\u01d4\u01d6\u0003\u00aeW\u0000\u01d5\u01d3\u0001\u0000"+
		"\u0000\u0000\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000"+
		"\u0000\u0000\u01d7\u01d8\u0003\u00b6[\u0000\u01d8C\u0001\u0000\u0000\u0000"+
		"\u01d9\u01dc\u0005I\u0000\u0000\u01da\u01db\u0004\"\u0002\u0000\u01db"+
		"\u01dd\u0003\u00aeW\u0000\u01dc\u01da\u0001\u0000\u0000\u0000\u01dc\u01dd"+
		"\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de\u01df"+
		"\u0003\u00b6[\u0000\u01dfE\u0001\u0000\u0000\u0000\u01e0\u01e3\u0005U"+
		"\u0000\u0000\u01e1\u01e2\u0004#\u0003\u0000\u01e2\u01e4\u0003\u008aE\u0000"+
		"\u01e3\u01e1\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000"+
		"\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01e6\u0003\u00b6[\u0000\u01e6"+
		"G\u0001\u0000\u0000\u0000\u01e7\u01ea\u0007\u0002\u0000\u0000\u01e8\u01e9"+
		"\u0004$\u0004\u0000\u01e9\u01eb\u0003\u008aE\u0000\u01ea\u01e8\u0001\u0000"+
		"\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000"+
		"\u0000\u0000\u01ec\u01ed\u0003\u00b6[\u0000\u01edI\u0001\u0000\u0000\u0000"+
		"\u01ee\u01ef\u0005^\u0000\u0000\u01ef\u01f0\u0003>\u001f\u0000\u01f0\u01f1"+
		"\u0003\u008aE\u0000\u01f1\u01f2\u0005\b\u0000\u0000\u01f2\u01f3\u0003"+
		"\u0004\u0002\u0000\u01f3K\u0001\u0000\u0000\u0000\u01f4\u01f5\u0005Y\u0000"+
		"\u0000\u01f5\u01f6\u0003>\u001f\u0000\u01f6\u01f7\u0003\u008aE\u0000\u01f7"+
		"\u01f8\u0005\b\u0000\u0000\u01f8\u01f9\u0003N\'\u0000\u01f9M\u0001\u0000"+
		"\u0000\u0000\u01fa\u01fc\u0005\t\u0000\u0000\u01fb\u01fd\u0003P(\u0000"+
		"\u01fc\u01fb\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000"+
		"\u01fd\u0202\u0001\u0000\u0000\u0000\u01fe\u0200\u0003T*\u0000\u01ff\u0201"+
		"\u0003P(\u0000\u0200\u01ff\u0001\u0000\u0000\u0000\u0200\u0201\u0001\u0000"+
		"\u0000\u0000\u0201\u0203\u0001\u0000\u0000\u0000\u0202\u01fe\u0001\u0000"+
		"\u0000\u0000\u0202\u0203\u0001\u0000\u0000\u0000\u0203\u0204\u0001\u0000"+
		"\u0000\u0000\u0204\u0205\u0005\u000b\u0000\u0000\u0205O\u0001\u0000\u0000"+
		"\u0000\u0206\u0208\u0003R)\u0000\u0207\u0206\u0001\u0000\u0000\u0000\u0208"+
		"\u0209\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000\u0000\u0000\u0209"+
		"\u020a\u0001\u0000\u0000\u0000\u020aQ\u0001\u0000\u0000\u0000\u020b\u020c"+
		"\u0005M\u0000\u0000\u020c\u020d\u0003\u008aE\u0000\u020d\u020f\u0005\u0011"+
		"\u0000\u0000\u020e\u0210\u0003\f\u0006\u0000\u020f\u020e\u0001\u0000\u0000"+
		"\u0000\u020f\u0210\u0001\u0000\u0000\u0000\u0210S\u0001\u0000\u0000\u0000"+
		"\u0211\u0212\u0005_\u0000\u0000\u0212\u0214\u0005\u0011\u0000\u0000\u0213"+
		"\u0215\u0003\f\u0006\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0214\u0215"+
		"\u0001\u0000\u0000\u0000\u0215U\u0001\u0000\u0000\u0000\u0216\u0217\u0003"+
		"\u00aeW\u0000\u0217\u0218\u0005\u0011\u0000\u0000\u0218\u0219\u0003\u0004"+
		"\u0002\u0000\u0219W\u0001\u0000\u0000\u0000\u021a\u021b\u0005a\u0000\u0000"+
		"\u021b\u021c\u0004,\u0005\u0000\u021c\u021d\u0003\u008aE\u0000\u021d\u021e"+
		"\u0003\u00b6[\u0000\u021eY\u0001\u0000\u0000\u0000\u021f\u0220\u0005d"+
		"\u0000\u0000\u0220\u0226\u0003\n\u0005\u0000\u0221\u0223\u0003\\.\u0000"+
		"\u0222\u0224\u0003^/\u0000\u0223\u0222\u0001\u0000\u0000\u0000\u0223\u0224"+
		"\u0001\u0000\u0000\u0000\u0224\u0227\u0001\u0000\u0000\u0000\u0225\u0227"+
		"\u0003^/\u0000\u0226\u0221\u0001\u0000\u0000\u0000\u0226\u0225\u0001\u0000"+
		"\u0000\u0000\u0227[\u0001\u0000\u0000\u0000\u0228\u022f\u0005S\u0000\u0000"+
		"\u0229\u022b\u0003>\u001f\u0000\u022a\u022c\u0003\u0092I\u0000\u022b\u022a"+
		"\u0001\u0000\u0000\u0000\u022b\u022c\u0001\u0000\u0000\u0000\u022c\u022d"+
		"\u0001\u0000\u0000\u0000\u022d\u022e\u0005\b\u0000\u0000\u022e\u0230\u0001"+
		"\u0000\u0000\u0000\u022f\u0229\u0001\u0000\u0000\u0000\u022f\u0230\u0001"+
		"\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000\u0000\u0231\u0232\u0003"+
		"\n\u0005\u0000\u0232]\u0001\u0000\u0000\u0000\u0233\u0234\u0005T\u0000"+
		"\u0000\u0234\u0235\u0003\n\u0005\u0000\u0235_\u0001\u0000\u0000\u0000"+
		"\u0236\u0237\u0005[\u0000\u0000\u0237\u0238\u0003\u00b6[\u0000\u0238a"+
		"\u0001\u0000\u0000\u0000\u0239\u023b\u0005q\u0000\u0000\u023a\u0239\u0001"+
		"\u0000\u0000\u0000\u023a\u023b\u0001\u0000\u0000\u0000\u023b\u023c\u0001"+
		"\u0000\u0000\u0000\u023c\u023e\u0005\\\u0000\u0000\u023d\u023f\u0005\u001a"+
		"\u0000\u0000\u023e\u023d\u0001\u0000\u0000\u0000\u023e\u023f\u0001\u0000"+
		"\u0000\u0000\u023f\u0240\u0001\u0000\u0000\u0000\u0240\u0241\u0003\u00ae"+
		"W\u0000\u0241\u0243\u0003>\u001f\u0000\u0242\u0244\u0003r9\u0000\u0243"+
		"\u0242\u0001\u0000\u0000\u0000\u0243\u0244\u0001\u0000\u0000\u0000\u0244"+
		"\u0245\u0001\u0000\u0000\u0000\u0245\u0246\u0005\b\u0000\u0000\u0246\u0247"+
		"\u0003x<\u0000\u0247c\u0001\u0000\u0000\u0000\u0248\u0249\u0005j\u0000"+
		"\u0000\u0249\u024a\u0003\u00aeW\u0000\u024a\u024b\u0003f3\u0000\u024b"+
		"e\u0001\u0000\u0000\u0000\u024c\u024d\u0005l\u0000\u0000\u024d\u024f\u0003"+
		"\u008cF\u0000\u024e\u024c\u0001\u0000\u0000\u0000\u024e\u024f\u0001\u0000"+
		"\u0000\u0000\u024f\u0250\u0001\u0000\u0000\u0000\u0250\u0254\u0005\t\u0000"+
		"\u0000\u0251\u0253\u0003h4\u0000\u0252\u0251\u0001\u0000\u0000\u0000\u0253"+
		"\u0256\u0001\u0000\u0000\u0000\u0254\u0252\u0001\u0000\u0000\u0000\u0254"+
		"\u0255\u0001\u0000\u0000\u0000\u0255\u0257\u0001\u0000\u0000\u0000\u0256"+
		"\u0254\u0001\u0000\u0000\u0000\u0257\u0258\u0005\u000b\u0000\u0000\u0258"+
		"g\u0001\u0000\u0000\u0000\u0259\u025d\u0005{\u0000\u0000\u025a\u025b\u0004"+
		"4\u0006\u0000\u025b\u025d\u0003\u00aeW\u0000\u025c\u0259\u0001\u0000\u0000"+
		"\u0000\u025c\u025a\u0001\u0000\u0000\u0000\u025c\u025d\u0001\u0000\u0000"+
		"\u0000\u025d\u025e\u0001\u0000\u0000\u0000\u025e\u026d\u0003j5\u0000\u025f"+
		"\u0263\u0005{\u0000\u0000\u0260\u0261\u00044\u0007\u0000\u0261\u0263\u0003"+
		"\u00aeW\u0000\u0262\u025f\u0001\u0000\u0000\u0000\u0262\u0260\u0001\u0000"+
		"\u0000\u0000\u0262\u0263\u0001\u0000\u0000\u0000\u0263\u0264\u0001\u0000"+
		"\u0000\u0000\u0264\u026d\u0003l6\u0000\u0265\u0269\u0005{\u0000\u0000"+
		"\u0266\u0267\u00044\b\u0000\u0267\u0269\u0003\u00aeW\u0000\u0268\u0265"+
		"\u0001\u0000\u0000\u0000\u0268\u0266\u0001\u0000\u0000\u0000\u0269\u026a"+
		"\u0001\u0000\u0000\u0000\u026a\u026d\u0003\n\u0005\u0000\u026b\u026d\u0003"+
		"2\u0019\u0000\u026c\u025c\u0001\u0000\u0000\u0000\u026c\u0262\u0001\u0000"+
		"\u0000\u0000\u026c\u0268\u0001\u0000\u0000\u0000\u026c\u026b\u0001\u0000"+
		"\u0000\u0000\u026di\u0001\u0000\u0000\u0000\u026e\u026f\u0005q\u0000\u0000"+
		"\u026f\u0271\u00045\t\u0000\u0270\u026e\u0001\u0000\u0000\u0000\u0270"+
		"\u0271\u0001\u0000\u0000\u0000\u0271\u0273\u0001\u0000\u0000\u0000\u0272"+
		"\u0274\u0005\u001a\u0000\u0000\u0273\u0272\u0001\u0000\u0000\u0000\u0273"+
		"\u0274\u0001\u0000\u0000\u0000\u0274\u0275\u0001\u0000\u0000\u0000\u0275"+
		"\u0276\u0003n7\u0000\u0276\u0278\u0003>\u001f\u0000\u0277\u0279\u0003"+
		"r9\u0000\u0278\u0277\u0001\u0000\u0000\u0000\u0278\u0279\u0001\u0000\u0000"+
		"\u0000\u0279\u027a\u0001\u0000\u0000\u0000\u027a\u027b\u0005\b\u0000\u0000"+
		"\u027b\u027c\u0003x<\u0000\u027c\u0291\u0001\u0000\u0000\u0000\u027d\u027f"+
		"\u0005\u001a\u0000\u0000\u027e\u027d\u0001\u0000\u0000\u0000\u027e\u027f"+
		"\u0001\u0000\u0000\u0000\u027f\u0280\u0001\u0000\u0000\u0000\u0280\u0281"+
		"\u0003\u00a8T\u0000\u0281\u0282\u0003>\u001f\u0000\u0282\u0283\u0005\b"+
		"\u0000\u0000\u0283\u0284\u0003x<\u0000\u0284\u0291\u0001\u0000\u0000\u0000"+
		"\u0285\u0287\u0005\u001a\u0000\u0000\u0286\u0285\u0001\u0000\u0000\u0000"+
		"\u0286\u0287\u0001\u0000\u0000\u0000\u0287\u0288\u0001\u0000\u0000\u0000"+
		"\u0288\u0289\u0003\u00aaU\u0000\u0289\u028b\u0003>\u001f\u0000\u028a\u028c"+
		"\u0003r9\u0000\u028b\u028a\u0001\u0000\u0000\u0000\u028b\u028c\u0001\u0000"+
		"\u0000\u0000\u028c\u028d\u0001\u0000\u0000\u0000\u028d\u028e\u0005\b\u0000"+
		"\u0000\u028e\u028f\u0003x<\u0000\u028f\u0291\u0001\u0000\u0000\u0000\u0290"+
		"\u0270\u0001\u0000\u0000\u0000\u0290\u027e\u0001\u0000\u0000\u0000\u0290"+
		"\u0286\u0001\u0000\u0000\u0000\u0291k\u0001\u0000\u0000\u0000\u0292\u0294"+
		"\u0003n7\u0000\u0293\u0295\u0003\u0090H\u0000\u0294\u0293\u0001\u0000"+
		"\u0000\u0000\u0294\u0295\u0001\u0000\u0000\u0000\u0295m\u0001\u0000\u0000"+
		"\u0000\u0296\u0299\u0003\u0084B\u0000\u0297\u0299\u0003p8\u0000\u0298"+
		"\u0296\u0001\u0000\u0000\u0000\u0298\u0297\u0001\u0000\u0000\u0000\u0299"+
		"o\u0001\u0000\u0000\u0000\u029a\u029b\u0005\u001f\u0000\u0000\u029b\u029c"+
		"\u0003\u00acV\u0000\u029cq\u0001\u0000\u0000\u0000\u029d\u02a2\u0003t"+
		":\u0000\u029e\u029f\u0005\r\u0000\u0000\u029f\u02a1\u0003t:\u0000\u02a0"+
		"\u029e\u0001\u0000\u0000\u0000\u02a1\u02a4\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a0\u0001\u0000\u0000\u0000\u02a2\u02a3\u0001\u0000\u0000\u0000\u02a3"+
		"\u02a7\u0001\u0000\u0000\u0000\u02a4\u02a2\u0001\u0000\u0000\u0000\u02a5"+
		"\u02a6\u0005\r\u0000\u0000\u02a6\u02a8\u0003v;\u0000\u02a7\u02a5\u0001"+
		"\u0000\u0000\u0000\u02a7\u02a8\u0001\u0000\u0000\u0000\u02a8\u02ab\u0001"+
		"\u0000\u0000\u0000\u02a9\u02ab\u0003v;\u0000\u02aa\u029d\u0001\u0000\u0000"+
		"\u0000\u02aa\u02a9\u0001\u0000\u0000\u0000\u02abs\u0001\u0000\u0000\u0000"+
		"\u02ac\u02af\u0003\u0092I\u0000\u02ad\u02ae\u0005\u000e\u0000\u0000\u02ae"+
		"\u02b0\u0003\u008cF\u0000\u02af\u02ad\u0001\u0000\u0000\u0000\u02af\u02b0"+
		"\u0001\u0000\u0000\u0000\u02b0u\u0001\u0000\u0000\u0000\u02b1\u02b2\u0005"+
		"\u0012\u0000\u0000\u02b2\u02b3\u0003\u008cF\u0000\u02b3w\u0001\u0000\u0000"+
		"\u0000\u02b4\u02b6\u0005\t\u0000\u0000\u02b5\u02b7\u0003z=\u0000\u02b6"+
		"\u02b5\u0001\u0000\u0000\u0000\u02b6\u02b7\u0001\u0000\u0000\u0000\u02b7"+
		"\u02b8\u0001\u0000\u0000\u0000\u02b8\u02b9\u0005\u000b\u0000\u0000\u02b9"+
		"y\u0001\u0000\u0000\u0000\u02ba\u02bc\u0003\u0002\u0001\u0000\u02bb\u02ba"+
		"\u0001\u0000\u0000\u0000\u02bc\u02bd\u0001\u0000\u0000\u0000\u02bd\u02bb"+
		"\u0001\u0000\u0000\u0000\u02bd\u02be\u0001\u0000\u0000\u0000\u02be{\u0001"+
		"\u0000\u0000\u0000\u02bf\u02c0\u0005\u0005\u0000\u0000\u02c0\u02c1\u0003"+
		"~?\u0000\u02c1\u02c2\u0005\u0006\u0000\u0000\u02c2}\u0001\u0000\u0000"+
		"\u0000\u02c3\u02c5\u0005\r\u0000\u0000\u02c4\u02c3\u0001\u0000\u0000\u0000"+
		"\u02c5\u02c8\u0001\u0000\u0000\u0000\u02c6\u02c4\u0001\u0000\u0000\u0000"+
		"\u02c6\u02c7\u0001\u0000\u0000\u0000\u02c7\u02ca\u0001\u0000\u0000\u0000"+
		"\u02c8\u02c6\u0001\u0000\u0000\u0000\u02c9\u02cb\u0003\u0080@\u0000\u02ca"+
		"\u02c9\u0001\u0000\u0000\u0000\u02ca\u02cb\u0001\u0000\u0000\u0000\u02cb"+
		"\u02d4\u0001\u0000\u0000\u0000\u02cc\u02ce\u0005\r\u0000\u0000\u02cd\u02cc"+
		"\u0001\u0000\u0000\u0000\u02ce\u02cf\u0001\u0000\u0000\u0000\u02cf\u02cd"+
		"\u0001\u0000\u0000\u0000\u02cf\u02d0\u0001\u0000\u0000\u0000\u02d0\u02d1"+
		"\u0001\u0000\u0000\u0000\u02d1\u02d3\u0003\u0080@\u0000\u02d2\u02cd\u0001"+
		"\u0000\u0000\u0000\u02d3\u02d6\u0001\u0000\u0000\u0000\u02d4\u02d2\u0001"+
		"\u0000\u0000\u0000\u02d4\u02d5\u0001\u0000\u0000\u0000\u02d5\u02da\u0001"+
		"\u0000\u0000\u0000\u02d6\u02d4\u0001\u0000\u0000\u0000\u02d7\u02d9\u0005"+
		"\r\u0000\u0000\u02d8\u02d7\u0001\u0000\u0000\u0000\u02d9\u02dc\u0001\u0000"+
		"\u0000\u0000\u02da\u02d8\u0001\u0000\u0000\u0000\u02da\u02db\u0001\u0000"+
		"\u0000\u0000\u02db\u007f\u0001\u0000\u0000\u0000\u02dc\u02da\u0001\u0000"+
		"\u0000\u0000\u02dd\u02df\u0005\u0012\u0000\u0000\u02de\u02dd\u0001\u0000"+
		"\u0000\u0000\u02de\u02df\u0001\u0000\u0000\u0000\u02df\u02e0\u0001\u0000"+
		"\u0000\u0000\u02e0\u02e1\u0003\u008cF\u0000\u02e1\u0081\u0001\u0000\u0000"+
		"\u0000\u02e2\u02e3\u0003\u0084B\u0000\u02e3\u02e4\u0005\u0011\u0000\u0000"+
		"\u02e4\u02e5\u0003\u008cF\u0000\u02e5\u030a\u0001\u0000\u0000\u0000\u02e6"+
		"\u02e7\u0005\u0005\u0000\u0000\u02e7\u02e8\u0003\u008cF\u0000\u02e8\u02e9"+
		"\u0005\u0006\u0000\u0000\u02e9\u02ea\u0005\u0011\u0000\u0000\u02ea\u02eb"+
		"\u0003\u008cF\u0000\u02eb\u030a\u0001\u0000\u0000\u0000\u02ec\u02ee\u0005"+
		"q\u0000\u0000\u02ed\u02ec\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001\u0000"+
		"\u0000\u0000\u02ee\u02f0\u0001\u0000\u0000\u0000\u02ef\u02f1\u0005\u001a"+
		"\u0000\u0000\u02f0\u02ef\u0001\u0000\u0000\u0000\u02f0\u02f1\u0001\u0000"+
		"\u0000\u0000\u02f1\u02f2\u0001\u0000\u0000\u0000\u02f2\u02f3\u0003\u0084"+
		"B\u0000\u02f3\u02f5\u0003>\u001f\u0000\u02f4\u02f6\u0003r9\u0000\u02f5"+
		"\u02f4\u0001\u0000\u0000\u0000\u02f5\u02f6\u0001\u0000\u0000\u0000\u02f6"+
		"\u02f7\u0001\u0000\u0000\u0000\u02f7\u02f8\u0005\b\u0000\u0000\u02f8\u02f9"+
		"\u0003x<\u0000\u02f9\u030a\u0001\u0000\u0000\u0000\u02fa\u02fb\u0003\u00a8"+
		"T\u0000\u02fb\u02fc\u0003>\u001f\u0000\u02fc\u02fd\u0005\b\u0000\u0000"+
		"\u02fd\u02fe\u0003x<\u0000\u02fe\u030a\u0001\u0000\u0000\u0000\u02ff\u0300"+
		"\u0003\u00aaU\u0000\u0300\u0301\u0003>\u001f\u0000\u0301\u0302\u0003t"+
		":\u0000\u0302\u0303\u0005\b\u0000\u0000\u0303\u0304\u0003x<\u0000\u0304"+
		"\u030a\u0001\u0000\u0000\u0000\u0305\u0307\u0005\u0012\u0000\u0000\u0306"+
		"\u0305\u0001\u0000\u0000\u0000\u0306\u0307\u0001\u0000\u0000\u0000\u0307"+
		"\u0308\u0001\u0000\u0000\u0000\u0308\u030a\u0003\u008cF\u0000\u0309\u02e2"+
		"\u0001\u0000\u0000\u0000\u0309\u02e6\u0001\u0000\u0000\u0000\u0309\u02ed"+
		"\u0001\u0000\u0000\u0000\u0309\u02fa\u0001\u0000\u0000\u0000\u0309\u02ff"+
		"\u0001\u0000\u0000\u0000\u0309\u0306\u0001\u0000\u0000\u0000\u030a\u0083"+
		"\u0001\u0000\u0000\u0000\u030b\u0313\u0003\u00acV\u0000\u030c\u0313\u0005"+
		"}\u0000\u0000\u030d\u0313\u0003\u00a4R\u0000\u030e\u030f\u0005\u0005\u0000"+
		"\u0000\u030f\u0310\u0003\u008cF\u0000\u0310\u0311\u0005\u0006\u0000\u0000"+
		"\u0311\u0313\u0001\u0000\u0000\u0000\u0312\u030b\u0001\u0000\u0000\u0000"+
		"\u0312\u030c\u0001\u0000\u0000\u0000\u0312\u030d\u0001\u0000\u0000\u0000"+
		"\u0312\u030e\u0001\u0000\u0000\u0000\u0313\u0085\u0001\u0000\u0000\u0000"+
		"\u0314\u0320\u0003>\u001f\u0000\u0315\u031a\u0003\u0088D\u0000\u0316\u0317"+
		"\u0005\r\u0000\u0000\u0317\u0319\u0003\u0088D\u0000\u0318\u0316\u0001"+
		"\u0000\u0000\u0000\u0319\u031c\u0001\u0000\u0000\u0000\u031a\u0318\u0001"+
		"\u0000\u0000\u0000\u031a\u031b\u0001\u0000\u0000\u0000\u031b\u031e\u0001"+
		"\u0000\u0000\u0000\u031c\u031a\u0001\u0000\u0000\u0000\u031d\u031f\u0005"+
		"\r\u0000\u0000\u031e\u031d\u0001\u0000\u0000\u0000\u031e\u031f\u0001\u0000"+
		"\u0000\u0000\u031f\u0321\u0001\u0000\u0000\u0000\u0320\u0315\u0001\u0000"+
		"\u0000\u0000\u0320\u0321\u0001\u0000\u0000\u0000\u0321\u0322\u0001\u0000"+
		"\u0000\u0000\u0322\u0323\u0005\b\u0000\u0000\u0323\u0087\u0001\u0000\u0000"+
		"\u0000\u0324\u0326\u0005\u0012\u0000\u0000\u0325\u0324\u0001\u0000\u0000"+
		"\u0000\u0325\u0326\u0001\u0000\u0000\u0000\u0326\u0329\u0001\u0000\u0000"+
		"\u0000\u0327\u032a\u0003\u008cF\u0000\u0328\u032a\u0003\u00aeW\u0000\u0329"+
		"\u0327\u0001\u0000\u0000\u0000\u0329\u0328\u0001\u0000\u0000\u0000\u032a"+
		"\u0089\u0001\u0000\u0000\u0000\u032b\u0330\u0003\u008cF\u0000\u032c\u032d"+
		"\u0005\r\u0000\u0000\u032d\u032f\u0003\u008cF\u0000\u032e\u032c\u0001"+
		"\u0000\u0000\u0000\u032f\u0332\u0001\u0000\u0000\u0000\u0330\u032e\u0001"+
		"\u0000\u0000\u0000\u0330\u0331\u0001\u0000\u0000\u0000\u0331\u008b\u0001"+
		"\u0000\u0000\u0000\u0332\u0330\u0001\u0000\u0000\u0000\u0333\u0334\u0003"+
		"\u008eG\u0000\u0334\u008d\u0001\u0000\u0000\u0000\u0335\u0336\u0006G\uffff"+
		"\uffff\u0000\u0336\u036e\u0003\u0096K\u0000\u0337\u0339\u0005j\u0000\u0000"+
		"\u0338\u033a\u0003\u00aeW\u0000\u0339\u0338\u0001\u0000\u0000\u0000\u0339"+
		"\u033a\u0001\u0000\u0000\u0000\u033a\u033b\u0001\u0000\u0000\u0000\u033b"+
		"\u036e\u0003f3\u0000\u033c\u033d\u0005P\u0000\u0000\u033d\u033e\u0003"+
		"\u00aeW\u0000\u033e\u033f\u0003\u0086C\u0000\u033f\u036e\u0001\u0000\u0000"+
		"\u0000\u0340\u0341\u0005P\u0000\u0000\u0341\u0342\u0003\u008eG\u0000\u0342"+
		"\u0343\u0003\u0086C\u0000\u0343\u036e\u0001\u0000\u0000\u0000\u0344\u0345"+
		"\u0005P\u0000\u0000\u0345\u036e\u0003\u008eG*\u0346\u0347\u0005P\u0000"+
		"\u0000\u0347\u0348\u0005\u0013\u0000\u0000\u0348\u036e\u0003\u00aeW\u0000"+
		"\u0349\u034a\u0005b\u0000\u0000\u034a\u036e\u0003\u008eG%\u034b\u034c"+
		"\u0005V\u0000\u0000\u034c\u036e\u0003\u008eG$\u034d\u034e\u0005L\u0000"+
		"\u0000\u034e\u036e\u0003\u008eG#\u034f\u0350\u0005\u0014\u0000\u0000\u0350"+
		"\u036e\u0003\u008eG\"\u0351\u0352\u0005\u0015\u0000\u0000\u0352\u036e"+
		"\u0003\u008eG!\u0353\u0354\u0005\u0016\u0000\u0000\u0354\u036e\u0003\u008e"+
		"G \u0355\u0356\u0005\u0017\u0000\u0000\u0356\u036e\u0003\u008eG\u001f"+
		"\u0357\u0358\u0005\u0018\u0000\u0000\u0358\u036e\u0003\u008eG\u001e\u0359"+
		"\u035a\u0005\u0019\u0000\u0000\u035a\u036e\u0003\u008eG\u001d\u035b\u035c"+
		"\u0005r\u0000\u0000\u035c\u036e\u0003\u008eG\u001c\u035d\u035e\u0005p"+
		"\u0000\u0000\u035e\u035f\u0003>\u001f\u0000\u035f\u0360\u0003\u008eG\u0000"+
		"\u0360\u0361\u0005\b\u0000\u0000\u0361\u036e\u0001\u0000\u0000\u0000\u0362"+
		"\u036e\u0003H$\u0000\u0363\u036e\u0005]\u0000\u0000\u0364\u036e\u0003"+
		"\u00aeW\u0000\u0365\u036e\u0005m\u0000\u0000\u0366\u036e\u0003\u009eO"+
		"\u0000\u0367\u036e\u0003|>\u0000\u0368\u036e\u0003\u0094J\u0000\u0369"+
		"\u036a\u0003>\u001f\u0000\u036a\u036b\u0003\u008aE\u0000\u036b\u036c\u0005"+
		"\b\u0000\u0000\u036c\u036e\u0001\u0000\u0000\u0000\u036d\u0335\u0001\u0000"+
		"\u0000\u0000\u036d\u0337\u0001\u0000\u0000\u0000\u036d\u033c\u0001\u0000"+
		"\u0000\u0000\u036d\u0340\u0001\u0000\u0000\u0000\u036d\u0344\u0001\u0000"+
		"\u0000\u0000\u036d\u0346\u0001\u0000\u0000\u0000\u036d\u0349\u0001\u0000"+
		"\u0000\u0000\u036d\u034b\u0001\u0000\u0000\u0000\u036d\u034d\u0001\u0000"+
		"\u0000\u0000\u036d\u034f\u0001\u0000\u0000\u0000\u036d\u0351\u0001\u0000"+
		"\u0000\u0000\u036d\u0353\u0001\u0000\u0000\u0000\u036d\u0355\u0001\u0000"+
		"\u0000\u0000\u036d\u0357\u0001\u0000\u0000\u0000\u036d\u0359\u0001\u0000"+
		"\u0000\u0000\u036d\u035b\u0001\u0000\u0000\u0000\u036d\u035d\u0001\u0000"+
		"\u0000\u0000\u036d\u0362\u0001\u0000\u0000\u0000\u036d\u0363\u0001\u0000"+
		"\u0000\u0000\u036d\u0364\u0001\u0000\u0000\u0000\u036d\u0365\u0001\u0000"+
		"\u0000\u0000\u036d\u0366\u0001\u0000\u0000\u0000\u036d\u0367\u0001\u0000"+
		"\u0000\u0000\u036d\u0368\u0001\u0000\u0000\u0000\u036d\u0369\u0001\u0000"+
		"\u0000\u0000\u036e\u03c6\u0001\u0000\u0000\u0000\u036f\u0370\n/\u0000"+
		"\u0000\u0370\u0371\u0005\u0010\u0000\u0000\u0371\u03c5\u0003\u008eG0\u0372"+
		"\u0373\n\u001b\u0000\u0000\u0373\u0374\u0005\u001d\u0000\u0000\u0374\u03c5"+
		"\u0003\u008eG\u001b\u0375\u0376\n\u001a\u0000\u0000\u0376\u0377\u0007"+
		"\u0003\u0000\u0000\u0377\u03c5\u0003\u008eG\u001b\u0378\u0379\n\u0019"+
		"\u0000\u0000\u0379\u037a\u0007\u0004\u0000\u0000\u037a\u03c5\u0003\u008e"+
		"G\u001a\u037b\u037c\n\u0018\u0000\u0000\u037c\u037d\u0005\u001e\u0000"+
		"\u0000\u037d\u03c5\u0003\u008eG\u0019\u037e\u037f\n\u0017\u0000\u0000"+
		"\u037f\u0380\u0007\u0005\u0000\u0000\u0380\u03c5\u0003\u008eG\u0018\u0381"+
		"\u0382\n\u0016\u0000\u0000\u0382\u0383\u0007\u0006\u0000\u0000\u0383\u03c5"+
		"\u0003\u008eG\u0017\u0384\u0385\n\u0015\u0000\u0000\u0385\u0386\u0005"+
		"K\u0000\u0000\u0386\u03c5\u0003\u008eG\u0016\u0387\u0388\n\u0014\u0000"+
		"\u0000\u0388\u0389\u0005c\u0000\u0000\u0389\u03c5\u0003\u008eG\u0015\u038a"+
		"\u038b\n\u0013\u0000\u0000\u038b\u038c\u0007\u0007\u0000\u0000\u038c\u03c5"+
		"\u0003\u008eG\u0014\u038d\u038e\n\u0012\u0000\u0000\u038e\u038f\u0005"+
		"+\u0000\u0000\u038f\u03c5\u0003\u008eG\u0013\u0390\u0391\n\u0011\u0000"+
		"\u0000\u0391\u0392\u0005,\u0000\u0000\u0392\u03c5\u0003\u008eG\u0012\u0393"+
		"\u0394\n\u0010\u0000\u0000\u0394\u0395\u0005-\u0000\u0000\u0395\u03c5"+
		"\u0003\u008eG\u0011\u0396\u0397\n\u000f\u0000\u0000\u0397\u0398\u0005"+
		".\u0000\u0000\u0398\u03c5\u0003\u008eG\u0010\u0399\u039a\n\u000e\u0000"+
		"\u0000\u039a\u039b\u0005/\u0000\u0000\u039b\u03c5\u0003\u008eG\u000f\u039c"+
		"\u039d\n\r\u0000\u0000\u039d\u039e\u0005\u000f\u0000\u0000\u039e\u039f"+
		"\u0003\u008eG\u0000\u039f\u03a0\u0005\u0011\u0000\u0000\u03a0\u03a1\u0003"+
		"\u008eG\u000e\u03a1\u03c5\u0001\u0000\u0000\u0000\u03a2\u03a3\n\f\u0000"+
		"\u0000\u03a3\u03a4\u0005\u000e\u0000\u0000\u03a4\u03c5\u0003\u008eG\f"+
		"\u03a5\u03a6\n\u000b\u0000\u0000\u03a6\u03a7\u0003\u009cN\u0000\u03a7"+
		"\u03a8\u0003\u008eG\u000b\u03a8\u03c5\u0001\u0000\u0000\u0000\u03a9\u03ab"+
		"\n.\u0000\u0000\u03aa\u03ac\u0005\u0010\u0000\u0000\u03ab\u03aa\u0001"+
		"\u0000\u0000\u0000\u03ab\u03ac\u0001\u0000\u0000\u0000\u03ac\u03ad\u0001"+
		"\u0000\u0000\u0000\u03ad\u03ae\u0005\u0005\u0000\u0000\u03ae\u03af\u0003"+
		"\u008aE\u0000\u03af\u03b0\u0005\u0006\u0000\u0000\u03b0\u03c5\u0001\u0000"+
		"\u0000\u0000\u03b1\u03b3\n-\u0000\u0000\u03b2\u03b4\u0005\u000f\u0000"+
		"\u0000\u03b3\u03b2\u0001\u0000\u0000\u0000\u03b3\u03b4\u0001\u0000\u0000"+
		"\u0000\u03b4\u03b5\u0001\u0000\u0000\u0000\u03b5\u03b7\u0005\u0013\u0000"+
		"\u0000\u03b6\u03b8\u0005\u001f\u0000\u0000\u03b7\u03b6\u0001\u0000\u0000"+
		"\u0000\u03b7\u03b8\u0001\u0000\u0000\u0000\u03b8\u03b9\u0001\u0000\u0000"+
		"\u0000\u03b9\u03c5\u0003\u00acV\u0000\u03ba\u03bb\n)\u0000\u0000\u03bb"+
		"\u03c5\u0003\u0086C\u0000\u03bc\u03bd\n\'\u0000\u0000\u03bd\u03be\u0004"+
		"G \u0000\u03be\u03c5\u0005\u0014\u0000\u0000\u03bf\u03c0\n&\u0000\u0000"+
		"\u03c0\u03c1\u0004G\"\u0000\u03c1\u03c5\u0005\u0015\u0000\u0000\u03c2"+
		"\u03c3\n\t\u0000\u0000\u03c3\u03c5\u0003\u00a0P\u0000\u03c4\u036f\u0001"+
		"\u0000\u0000\u0000\u03c4\u0372\u0001\u0000\u0000\u0000\u03c4\u0375\u0001"+
		"\u0000\u0000\u0000\u03c4\u0378\u0001\u0000\u0000\u0000\u03c4\u037b\u0001"+
		"\u0000\u0000\u0000\u03c4\u037e\u0001\u0000\u0000\u0000\u03c4\u0381\u0001"+
		"\u0000\u0000\u0000\u03c4\u0384\u0001\u0000\u0000\u0000\u03c4\u0387\u0001"+
		"\u0000\u0000\u0000\u03c4\u038a\u0001\u0000\u0000\u0000\u03c4\u038d\u0001"+
		"\u0000\u0000\u0000\u03c4\u0390\u0001\u0000\u0000\u0000\u03c4\u0393\u0001"+
		"\u0000\u0000\u0000\u03c4\u0396\u0001\u0000\u0000\u0000\u03c4\u0399\u0001"+
		"\u0000\u0000\u0000\u03c4\u039c\u0001\u0000\u0000\u0000\u03c4\u03a2\u0001"+
		"\u0000\u0000\u0000\u03c4\u03a5\u0001\u0000\u0000\u0000\u03c4\u03a9\u0001"+
		"\u0000\u0000\u0000\u03c4\u03b1\u0001\u0000\u0000\u0000\u03c4\u03ba\u0001"+
		"\u0000\u0000\u0000\u03c4\u03bc\u0001\u0000\u0000\u0000\u03c4\u03bf\u0001"+
		"\u0000\u0000\u0000\u03c4\u03c2\u0001\u0000\u0000\u0000\u03c5\u03c8\u0001"+
		"\u0000\u0000\u0000\u03c6\u03c4\u0001\u0000\u0000\u0000\u03c6\u03c7\u0001"+
		"\u0000\u0000\u0000\u03c7\u008f\u0001\u0000\u0000\u0000\u03c8\u03c6\u0001"+
		"\u0000\u0000\u0000\u03c9\u03ca\u0005\u000e\u0000\u0000\u03ca\u03cb\u0003"+
		"\u008cF\u0000\u03cb\u0091\u0001\u0000\u0000\u0000\u03cc\u03d1\u0003\u00ae"+
		"W\u0000\u03cd\u03d1\u0003\u00b2Y\u0000\u03ce\u03d1\u0003|>\u0000\u03cf"+
		"\u03d1\u0003\u0094J\u0000\u03d0\u03cc\u0001\u0000\u0000\u0000\u03d0\u03cd"+
		"\u0001\u0000\u0000\u0000\u03d0\u03ce\u0001\u0000\u0000\u0000\u03d0\u03cf"+
		"\u0001\u0000\u0000\u0000\u03d1\u0093\u0001\u0000\u0000\u0000\u03d2\u03de"+
		"\u0005\t\u0000\u0000\u03d3\u03d8\u0003\u0082A\u0000\u03d4\u03d5\u0005"+
		"\r\u0000\u0000\u03d5\u03d7\u0003\u0082A\u0000\u03d6\u03d4\u0001\u0000"+
		"\u0000\u0000\u03d7\u03da\u0001\u0000\u0000\u0000\u03d8\u03d6\u0001\u0000"+
		"\u0000\u0000\u03d8\u03d9\u0001\u0000\u0000\u0000\u03d9\u03dc\u0001\u0000"+
		"\u0000\u0000\u03da\u03d8\u0001\u0000\u0000\u0000\u03db\u03dd\u0005\r\u0000"+
		"\u0000\u03dc\u03db\u0001\u0000\u0000\u0000\u03dc\u03dd\u0001\u0000\u0000"+
		"\u0000\u03dd\u03df\u0001\u0000\u0000\u0000\u03de\u03d3\u0001\u0000\u0000"+
		"\u0000\u03de\u03df\u0001\u0000\u0000\u0000\u03df\u03e0\u0001\u0000\u0000"+
		"\u0000\u03e0\u03e1\u0005\u000b\u0000\u0000\u03e1\u0095\u0001\u0000\u0000"+
		"\u0000\u03e2\u03f9\u0003b1\u0000\u03e3\u03e5\u0005q\u0000\u0000\u03e4"+
		"\u03e3\u0001\u0000\u0000\u0000\u03e4\u03e5\u0001\u0000\u0000\u0000\u03e5"+
		"\u03e6\u0001\u0000\u0000\u0000\u03e6\u03e8\u0005\\\u0000\u0000\u03e7\u03e9"+
		"\u0005\u001a\u0000\u0000\u03e8\u03e7\u0001\u0000\u0000\u0000\u03e8\u03e9"+
		"\u0001\u0000\u0000\u0000\u03e9\u03ea\u0001\u0000\u0000\u0000\u03ea\u03ec"+
		"\u0003>\u001f\u0000\u03eb\u03ed\u0003r9\u0000\u03ec\u03eb\u0001\u0000"+
		"\u0000\u0000\u03ec\u03ed\u0001\u0000\u0000\u0000\u03ed\u03ee\u0001\u0000"+
		"\u0000\u0000\u03ee\u03ef\u0005\b\u0000\u0000\u03ef\u03f0\u0003x<\u0000"+
		"\u03f0\u03f9\u0001\u0000\u0000\u0000\u03f1\u03f3\u0005q\u0000\u0000\u03f2"+
		"\u03f1\u0001\u0000\u0000\u0000\u03f2\u03f3\u0001\u0000\u0000\u0000\u03f3"+
		"\u03f4\u0001\u0000\u0000\u0000\u03f4\u03f5\u0003\u0098L\u0000\u03f5\u03f6"+
		"\u0005=\u0000\u0000\u03f6\u03f7\u0003\u009aM\u0000\u03f7\u03f9\u0001\u0000"+
		"\u0000\u0000\u03f8\u03e2\u0001\u0000\u0000\u0000\u03f8\u03e4\u0001\u0000"+
		"\u0000\u0000\u03f8\u03f2\u0001\u0000\u0000\u0000\u03f9\u0097\u0001\u0000"+
		"\u0000\u0000\u03fa\u0402\u0003\u0084B\u0000\u03fb\u03fd\u0003>\u001f\u0000"+
		"\u03fc\u03fe\u0003r9\u0000\u03fd\u03fc\u0001\u0000\u0000\u0000\u03fd\u03fe"+
		"\u0001\u0000\u0000\u0000\u03fe\u03ff\u0001\u0000\u0000\u0000\u03ff\u0400"+
		"\u0005\b\u0000\u0000\u0400\u0402\u0001\u0000\u0000\u0000\u0401\u03fa\u0001"+
		"\u0000\u0000\u0000\u0401\u03fb\u0001\u0000\u0000\u0000\u0402\u0099\u0001"+
		"\u0000\u0000\u0000\u0403\u0406\u0003\u008cF\u0000\u0404\u0406\u0003x<"+
		"\u0000\u0405\u0403\u0001\u0000\u0000\u0000\u0405\u0404\u0001\u0000\u0000"+
		"\u0000\u0406\u009b\u0001\u0000\u0000\u0000\u0407\u0408\u0007\b\u0000\u0000"+
		"\u0408\u009d\u0001\u0000\u0000\u0000\u0409\u0411\u0005>\u0000\u0000\u040a"+
		"\u0411\u0005?\u0000\u0000\u040b\u0411\u0005}\u0000\u0000\u040c\u0411\u0003"+
		"\u00a0P\u0000\u040d\u0411\u0005\u0004\u0000\u0000\u040e\u0411\u0003\u00a4"+
		"R\u0000\u040f\u0411\u0003\u00a6S\u0000\u0410\u0409\u0001\u0000\u0000\u0000"+
		"\u0410\u040a\u0001\u0000\u0000\u0000\u0410\u040b\u0001\u0000\u0000\u0000"+
		"\u0410\u040c\u0001\u0000\u0000\u0000\u0410\u040d\u0001\u0000\u0000\u0000"+
		"\u0410\u040e\u0001\u0000\u0000\u0000\u0410\u040f\u0001\u0000\u0000\u0000"+
		"\u0411\u009f\u0001\u0000\u0000\u0000\u0412\u0416\u0005~\u0000\u0000\u0413"+
		"\u0415\u0003\u00a2Q\u0000\u0414\u0413\u0001\u0000\u0000\u0000\u0415\u0418"+
		"\u0001\u0000\u0000\u0000\u0416\u0414\u0001\u0000\u0000\u0000\u0416\u0417"+
		"\u0001\u0000\u0000\u0000\u0417\u0419\u0001\u0000\u0000\u0000\u0418\u0416"+
		"\u0001\u0000\u0000\u0000\u0419\u041a\u0005~\u0000\u0000\u041a\u00a1\u0001"+
		"\u0000\u0000\u0000\u041b\u0421\u0005\u0085\u0000\u0000\u041c\u041d\u0005"+
		"\u0084\u0000\u0000\u041d\u041e\u0003\u008cF\u0000\u041e\u041f\u0005\n"+
		"\u0000\u0000\u041f\u0421\u0001\u0000\u0000\u0000\u0420\u041b\u0001\u0000"+
		"\u0000\u0000\u0420\u041c\u0001\u0000\u0000\u0000\u0421\u00a3\u0001\u0000"+
		"\u0000\u0000\u0422\u0423\u0007\t\u0000\u0000\u0423\u00a5\u0001\u0000\u0000"+
		"\u0000\u0424\u0425\u0007\n\u0000\u0000\u0425\u00a7\u0001\u0000\u0000\u0000"+
		"\u0426\u0427\u0004T$\u0000\u0427\u0428\u0003\u00aeW\u0000\u0428\u0429"+
		"\u0003n7\u0000\u0429\u00a9\u0001\u0000\u0000\u0000\u042a\u042b\u0004U"+
		"%\u0000\u042b\u042c\u0003\u00aeW\u0000\u042c\u042d\u0003n7\u0000\u042d"+
		"\u00ab\u0001\u0000\u0000\u0000\u042e\u0431\u0003\u00aeW\u0000\u042f\u0431"+
		"\u0003\u00b0X\u0000\u0430\u042e\u0001\u0000\u0000\u0000\u0430\u042f\u0001"+
		"\u0000\u0000\u0000\u0431\u00ad\u0001\u0000\u0000\u0000\u0432\u0433\u0007"+
		"\u000b\u0000\u0000\u0433\u00af\u0001\u0000\u0000\u0000\u0434\u0438\u0003"+
		"\u00b2Y\u0000\u0435\u0438\u0005>\u0000\u0000\u0436\u0438\u0005?\u0000"+
		"\u0000\u0437\u0434\u0001\u0000\u0000\u0000\u0437\u0435\u0001\u0000\u0000"+
		"\u0000\u0437\u0436\u0001\u0000\u0000\u0000\u0438\u00b1\u0001\u0000\u0000"+
		"\u0000\u0439\u046a\u0005I\u0000\u0000\u043a\u046a\u0005J\u0000\u0000\u043b"+
		"\u046a\u0005K\u0000\u0000\u043c\u046a\u0005L\u0000\u0000\u043d\u046a\u0005"+
		"M\u0000\u0000\u043e\u046a\u0005N\u0000\u0000\u043f\u046a\u0005P\u0000"+
		"\u0000\u0440\u046a\u0005Q\u0000\u0000\u0441\u046a\u0005S\u0000\u0000\u0442"+
		"\u046a\u0005T\u0000\u0000\u0443\u046a\u0005U\u0000\u0000\u0444\u046a\u0005"+
		"V\u0000\u0000\u0445\u046a\u0005W\u0000\u0000\u0446\u046a\u0005X\u0000"+
		"\u0000\u0447\u046a\u0005Y\u0000\u0000\u0448\u046a\u0005Z\u0000\u0000\u0449"+
		"\u046a\u0005[\u0000\u0000\u044a\u046a\u0005\\\u0000\u0000\u044b\u046a"+
		"\u0005]\u0000\u0000\u044c\u046a\u0005^\u0000\u0000\u044d\u046a\u0005_"+
		"\u0000\u0000\u044e\u046a\u0005`\u0000\u0000\u044f\u046a\u0005a\u0000\u0000"+
		"\u0450\u046a\u0005b\u0000\u0000\u0451\u046a\u0005c\u0000\u0000\u0452\u046a"+
		"\u0005d\u0000\u0000\u0453\u046a\u0005j\u0000\u0000\u0454\u046a\u0005k"+
		"\u0000\u0000\u0455\u046a\u0005l\u0000\u0000\u0456\u046a\u0005m\u0000\u0000"+
		"\u0457\u046a\u0005n\u0000\u0000\u0458\u046a\u0005o\u0000\u0000\u0459\u046a"+
		"\u0005p\u0000\u0000\u045a\u046a\u0005s\u0000\u0000\u045b\u046a\u0003\u00b4"+
		"Z\u0000\u045c\u046a\u0005v\u0000\u0000\u045d\u046a\u0005w\u0000\u0000"+
		"\u045e\u046a\u0005x\u0000\u0000\u045f\u046a\u0005y\u0000\u0000\u0460\u046a"+
		"\u0005z\u0000\u0000\u0461\u046a\u0005{\u0000\u0000\u0462\u046a\u0005h"+
		"\u0000\u0000\u0463\u046a\u0005i\u0000\u0000\u0464\u046a\u0005q\u0000\u0000"+
		"\u0465\u046a\u0005r\u0000\u0000\u0466\u046a\u0005f\u0000\u0000\u0467\u046a"+
		"\u0005e\u0000\u0000\u0468\u046a\u0005g\u0000\u0000\u0469\u0439\u0001\u0000"+
		"\u0000\u0000\u0469\u043a\u0001\u0000\u0000\u0000\u0469\u043b\u0001\u0000"+
		"\u0000\u0000\u0469\u043c\u0001\u0000\u0000\u0000\u0469\u043d\u0001\u0000"+
		"\u0000\u0000\u0469\u043e\u0001\u0000\u0000\u0000\u0469\u043f\u0001\u0000"+
		"\u0000\u0000\u0469\u0440\u0001\u0000\u0000\u0000\u0469\u0441\u0001\u0000"+
		"\u0000\u0000\u0469\u0442\u0001\u0000\u0000\u0000\u0469\u0443\u0001\u0000"+
		"\u0000\u0000\u0469\u0444\u0001\u0000\u0000\u0000\u0469\u0445\u0001\u0000"+
		"\u0000\u0000\u0469\u0446\u0001\u0000\u0000\u0000\u0469\u0447\u0001\u0000"+
		"\u0000\u0000\u0469\u0448\u0001\u0000\u0000\u0000\u0469\u0449\u0001\u0000"+
		"\u0000\u0000\u0469\u044a\u0001\u0000\u0000\u0000\u0469\u044b\u0001\u0000"+
		"\u0000\u0000\u0469\u044c\u0001\u0000\u0000\u0000\u0469\u044d\u0001\u0000"+
		"\u0000\u0000\u0469\u044e\u0001\u0000\u0000\u0000\u0469\u044f\u0001\u0000"+
		"\u0000\u0000\u0469\u0450\u0001\u0000\u0000\u0000\u0469\u0451\u0001\u0000"+
		"\u0000\u0000\u0469\u0452\u0001\u0000\u0000\u0000\u0469\u0453\u0001\u0000"+
		"\u0000\u0000\u0469\u0454\u0001\u0000\u0000\u0000\u0469\u0455\u0001\u0000"+
		"\u0000\u0000\u0469\u0456\u0001\u0000\u0000\u0000\u0469\u0457\u0001\u0000"+
		"\u0000\u0000\u0469\u0458\u0001\u0000\u0000\u0000\u0469\u0459\u0001\u0000"+
		"\u0000\u0000\u0469\u045a\u0001\u0000\u0000\u0000\u0469\u045b\u0001\u0000"+
		"\u0000\u0000\u0469\u045c\u0001\u0000\u0000\u0000\u0469\u045d\u0001\u0000"+
		"\u0000\u0000\u0469\u045e\u0001\u0000\u0000\u0000\u0469\u045f\u0001\u0000"+
		"\u0000\u0000\u0469\u0460\u0001\u0000\u0000\u0000\u0469\u0461\u0001\u0000"+
		"\u0000\u0000\u0469\u0462\u0001\u0000\u0000\u0000\u0469\u0463\u0001\u0000"+
		"\u0000\u0000\u0469\u0464\u0001\u0000\u0000\u0000\u0469\u0465\u0001\u0000"+
		"\u0000\u0000\u0469\u0466\u0001\u0000\u0000\u0000\u0469\u0467\u0001\u0000"+
		"\u0000\u0000\u0469\u0468\u0001\u0000\u0000\u0000\u046a\u00b3\u0001\u0000"+
		"\u0000\u0000\u046b\u046c\u0007\f\u0000\u0000\u046c\u00b5\u0001\u0000\u0000"+
		"\u0000\u046d\u0478\u0005\f\u0000\u0000\u046e\u0478\u0005\u0000\u0000\u0001"+
		"\u046f\u0471\u0005\u0080\u0000\u0000\u0470\u046f\u0001\u0000\u0000\u0000"+
		"\u0471\u0474\u0001\u0000\u0000\u0000\u0472\u0470\u0001\u0000\u0000\u0000"+
		"\u0472\u0473\u0001\u0000\u0000\u0000\u0473\u0478\u0001\u0000\u0000\u0000"+
		"\u0474\u0472\u0001\u0000\u0000\u0000\u0475\u0478\u0004[&\u0000\u0476\u0478"+
		"\u0004[\'\u0000\u0477\u046d\u0001\u0000\u0000\u0000\u0477\u046e\u0001"+
		"\u0000\u0000\u0000\u0477\u0472\u0001\u0000\u0000\u0000\u0477\u0475\u0001"+
		"\u0000\u0000\u0000\u0477\u0476\u0001\u0000\u0000\u0000\u0478\u00b7\u0001"+
		"\u0000\u0000\u0000y\u00b9\u00bc\u00c4\u00de\u00e5\u00ec\u00f2\u00f6\u00fd"+
		"\u0105\u010a\u010c\u0113\u0117\u0120\u0124\u012c\u0130\u0134\u013d\u0145"+
		"\u0149\u0151\u0156\u0158\u015f\u0164\u016f\u0175\u0185\u0189\u01a6\u01aa"+
		"\u01ae\u01b7\u01c0\u01c5\u01cc\u01d5\u01dc\u01e3\u01ea\u01fc\u0200\u0202"+
		"\u0209\u020f\u0214\u0223\u0226\u022b\u022f\u023a\u023e\u0243\u024e\u0254"+
		"\u025c\u0262\u0268\u026c\u0270\u0273\u0278\u027e\u0286\u028b\u0290\u0294"+
		"\u0298\u02a2\u02a7\u02aa\u02af\u02b6\u02bd\u02c6\u02ca\u02cf\u02d4\u02da"+
		"\u02de\u02ed\u02f0\u02f5\u0306\u0309\u0312\u031a\u031e\u0320\u0325\u0329"+
		"\u0330\u0339\u036d\u03ab\u03b3\u03b7\u03c4\u03c6\u03d0\u03d8\u03dc\u03de"+
		"\u03e4\u03e8\u03ec\u03f2\u03f8\u03fd\u0401\u0405\u0410\u0416\u0420\u0430"+
		"\u0437\u0469\u0472\u0477";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
