// Generated from D:/clouds/java/lowcode-parent/lowcode-object-parent/object-formula-parent/object-formula-parser/FxParser.g4 by ANTLR 4.13.1
package net.cf.object.fx.parser.g4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SPACE=1, COMMENT_INPUT=2, LINE_COMMENT=3, AS=4, FROM=5, SELECT=6, WHERE=7, 
		STAR=8, DOT=9, LR_BRACKET=10, RR_BRACKET=11, COMMA=12, STRING_LITERAL=13, 
		DOT_ID=14, ID=15;
	public static final int
		RULE_root = 0, RULE_sqlStatement = 1, RULE_selectStatement = 2, RULE_querySpecification = 3, 
		RULE_selectElements = 4, RULE_selectElement = 5, RULE_fromClause = 6, 
		RULE_tableSources = 7, RULE_tableSource = 8, RULE_tableSourceItem = 9, 
		RULE_tableName = 10, RULE_fullId = 11, RULE_uid = 12, RULE_simpleId = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "sqlStatement", "selectStatement", "querySpecification", "selectElements", 
			"selectElement", "fromClause", "tableSources", "tableSource", "tableSourceItem", 
			"tableName", "fullId", "uid", "simpleId"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'AS'", "'FROM'", "'SELECT'", "'WHERE'", "'*'", 
			"'.'", "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SPACE", "COMMENT_INPUT", "LINE_COMMENT", "AS", "FROM", "SELECT", 
			"WHERE", "STAR", "DOT", "LR_BRACKET", "RR_BRACKET", "COMMA", "STRING_LITERAL", 
			"DOT_ID", "ID"
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
	public String getGrammarFileName() { return "FxParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public SqlStatementContext sqlStatement() {
			return getRuleContext(SqlStatementContext.class,0);
		}
		public TerminalNode EOF() { return getToken(FxParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			sqlStatement();
			setState(29);
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
	public static class SqlStatementContext extends ParserRuleContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public SqlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterSqlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitSqlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitSqlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementContext sqlStatement() throws RecognitionException {
		SqlStatementContext _localctx = new SqlStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			selectStatement();
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
	public static class SelectStatementContext extends ParserRuleContext {
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
	 
		public SelectStatementContext() { }
		public void copyFrom(SelectStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SimpleSelectContext extends SelectStatementContext {
		public QuerySpecificationContext querySpecification() {
			return getRuleContext(QuerySpecificationContext.class,0);
		}
		public SimpleSelectContext(SelectStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterSimpleSelect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitSimpleSelect(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitSimpleSelect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectStatement);
		try {
			_localctx = new SimpleSelectContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			querySpecification();
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
	public static class QuerySpecificationContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(FxParser.SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public FromClauseContext fromClause() {
			return getRuleContext(FromClauseContext.class,0);
		}
		public QuerySpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_querySpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterQuerySpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitQuerySpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitQuerySpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuerySpecificationContext querySpecification() throws RecognitionException {
		QuerySpecificationContext _localctx = new QuerySpecificationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_querySpecification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(SELECT);
			setState(36);
			selectElements();
			setState(37);
			fromClause();
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
	public static class SelectElementsContext extends ParserRuleContext {
		public Token star;
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public TerminalNode STAR() { return getToken(FxParser.STAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FxParser.COMMA, i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_selectElements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(39);
				((SelectElementsContext)_localctx).star = match(STAR);
				}
				break;
			case STRING_LITERAL:
			case ID:
				{
				setState(40);
				selectElement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(43);
				match(COMMA);
				setState(44);
				selectElement();
				}
				}
				setState(49);
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
	public static class SelectElementContext extends ParserRuleContext {
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
	 
		public SelectElementContext() { }
		public void copyFrom(SelectElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectStarElementContext extends SelectElementContext {
		public FullIdContext fullId() {
			return getRuleContext(FullIdContext.class,0);
		}
		public TerminalNode DOT() { return getToken(FxParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(FxParser.STAR, 0); }
		public SelectStarElementContext(SelectElementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterSelectStarElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitSelectStarElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitSelectStarElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_selectElement);
		try {
			_localctx = new SelectStarElementContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			fullId();
			setState(51);
			match(DOT);
			setState(52);
			match(STAR);
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
	public static class FromClauseContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(FxParser.FROM, 0); }
		public TableSourcesContext tableSources() {
			return getRuleContext(TableSourcesContext.class,0);
		}
		public FromClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterFromClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitFromClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitFromClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromClauseContext fromClause() throws RecognitionException {
		FromClauseContext _localctx = new FromClauseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fromClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(54);
			match(FROM);
			setState(55);
			tableSources();
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
	public static class TableSourcesContext extends ParserRuleContext {
		public List<TableSourceContext> tableSource() {
			return getRuleContexts(TableSourceContext.class);
		}
		public TableSourceContext tableSource(int i) {
			return getRuleContext(TableSourceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FxParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FxParser.COMMA, i);
		}
		public TableSourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterTableSources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitTableSources(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitTableSources(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourcesContext tableSources() throws RecognitionException {
		TableSourcesContext _localctx = new TableSourcesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tableSources);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			tableSource();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(58);
				match(COMMA);
				setState(59);
				tableSource();
				}
				}
				setState(64);
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
	public static class TableSourceContext extends ParserRuleContext {
		public TableSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSource; }
	 
		public TableSourceContext() { }
		public void copyFrom(TableSourceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TableSourceBaseContext extends TableSourceContext {
		public TableSourceItemContext tableSourceItem() {
			return getRuleContext(TableSourceItemContext.class,0);
		}
		public TableSourceBaseContext(TableSourceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterTableSourceBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitTableSourceBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitTableSourceBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceContext tableSource() throws RecognitionException {
		TableSourceContext _localctx = new TableSourceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tableSource);
		try {
			_localctx = new TableSourceBaseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			tableSourceItem();
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
	public static class TableSourceItemContext extends ParserRuleContext {
		public TableSourceItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSourceItem; }
	 
		public TableSourceItemContext() { }
		public void copyFrom(TableSourceItemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomTableItemContext extends TableSourceItemContext {
		public UidContext alias;
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public UidContext uid() {
			return getRuleContext(UidContext.class,0);
		}
		public TerminalNode AS() { return getToken(FxParser.AS, 0); }
		public AtomTableItemContext(TableSourceItemContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterAtomTableItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitAtomTableItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitAtomTableItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceItemContext tableSourceItem() throws RecognitionException {
		TableSourceItemContext _localctx = new TableSourceItemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tableSourceItem);
		int _la;
		try {
			_localctx = new AtomTableItemContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			tableName();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 40976L) != 0)) {
				{
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(68);
					match(AS);
					}
				}

				setState(71);
				((AtomTableItemContext)_localctx).alias = uid();
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
	public static class TableNameContext extends ParserRuleContext {
		public FullIdContext fullId() {
			return getRuleContext(FullIdContext.class,0);
		}
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			fullId();
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
	public static class FullIdContext extends ParserRuleContext {
		public List<UidContext> uid() {
			return getRuleContexts(UidContext.class);
		}
		public UidContext uid(int i) {
			return getRuleContext(UidContext.class,i);
		}
		public TerminalNode DOT_ID() { return getToken(FxParser.DOT_ID, 0); }
		public TerminalNode DOT() { return getToken(FxParser.DOT, 0); }
		public FullIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterFullId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitFullId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitFullId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullIdContext fullId() throws RecognitionException {
		FullIdContext _localctx = new FullIdContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fullId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			uid();
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(77);
				match(DOT_ID);
				}
				break;
			case 2:
				{
				setState(78);
				match(DOT);
				setState(79);
				uid();
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
	public static class UidContext extends ParserRuleContext {
		public SimpleIdContext simpleId() {
			return getRuleContext(SimpleIdContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(FxParser.STRING_LITERAL, 0); }
		public UidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uid; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterUid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitUid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitUid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UidContext uid() throws RecognitionException {
		UidContext _localctx = new UidContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_uid);
		try {
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				simpleId();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(STRING_LITERAL);
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
	public static class SimpleIdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FxParser.ID, 0); }
		public SimpleIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).enterSimpleId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FxParserListener ) ((FxParserListener)listener).exitSimpleId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FxParserVisitor ) return ((FxParserVisitor<? extends T>)visitor).visitSimpleId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleIdContext simpleId() throws RecognitionException {
		SimpleIdContext _localctx = new SimpleIdContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_simpleId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ID);
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

	public static final String _serializedATN =
		"\u0004\u0001\u000fY\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0003\u0004*\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004.\b\u0004\n\u0004\f\u00041\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007=\b\u0007\n\u0007\f\u0007"+
		"@\t\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0003\tF\b\t\u0001\t\u0003\t"+
		"I\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000bQ\b\u000b\u0001\f\u0001\f\u0003\fU\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u0000\u0000R\u0000\u001c\u0001\u0000\u0000\u0000\u0002"+
		"\u001f\u0001\u0000\u0000\u0000\u0004!\u0001\u0000\u0000\u0000\u0006#\u0001"+
		"\u0000\u0000\u0000\b)\u0001\u0000\u0000\u0000\n2\u0001\u0000\u0000\u0000"+
		"\f6\u0001\u0000\u0000\u0000\u000e9\u0001\u0000\u0000\u0000\u0010A\u0001"+
		"\u0000\u0000\u0000\u0012C\u0001\u0000\u0000\u0000\u0014J\u0001\u0000\u0000"+
		"\u0000\u0016L\u0001\u0000\u0000\u0000\u0018T\u0001\u0000\u0000\u0000\u001a"+
		"V\u0001\u0000\u0000\u0000\u001c\u001d\u0003\u0002\u0001\u0000\u001d\u001e"+
		"\u0005\u0000\u0000\u0001\u001e\u0001\u0001\u0000\u0000\u0000\u001f \u0003"+
		"\u0004\u0002\u0000 \u0003\u0001\u0000\u0000\u0000!\"\u0003\u0006\u0003"+
		"\u0000\"\u0005\u0001\u0000\u0000\u0000#$\u0005\u0006\u0000\u0000$%\u0003"+
		"\b\u0004\u0000%&\u0003\f\u0006\u0000&\u0007\u0001\u0000\u0000\u0000\'"+
		"*\u0005\b\u0000\u0000(*\u0003\n\u0005\u0000)\'\u0001\u0000\u0000\u0000"+
		")(\u0001\u0000\u0000\u0000*/\u0001\u0000\u0000\u0000+,\u0005\f\u0000\u0000"+
		",.\u0003\n\u0005\u0000-+\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000"+
		"/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000\t\u0001\u0000\u0000"+
		"\u00001/\u0001\u0000\u0000\u000023\u0003\u0016\u000b\u000034\u0005\t\u0000"+
		"\u000045\u0005\b\u0000\u00005\u000b\u0001\u0000\u0000\u000067\u0005\u0005"+
		"\u0000\u000078\u0003\u000e\u0007\u00008\r\u0001\u0000\u0000\u00009>\u0003"+
		"\u0010\b\u0000:;\u0005\f\u0000\u0000;=\u0003\u0010\b\u0000<:\u0001\u0000"+
		"\u0000\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001"+
		"\u0000\u0000\u0000?\u000f\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000"+
		"\u0000AB\u0003\u0012\t\u0000B\u0011\u0001\u0000\u0000\u0000CH\u0003\u0014"+
		"\n\u0000DF\u0005\u0004\u0000\u0000ED\u0001\u0000\u0000\u0000EF\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GI\u0003\u0018\f\u0000HE\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000I\u0013\u0001\u0000\u0000\u0000"+
		"JK\u0003\u0016\u000b\u0000K\u0015\u0001\u0000\u0000\u0000LP\u0003\u0018"+
		"\f\u0000MQ\u0005\u000e\u0000\u0000NO\u0005\t\u0000\u0000OQ\u0003\u0018"+
		"\f\u0000PM\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Q\u0017\u0001\u0000\u0000\u0000RU\u0003\u001a\r\u0000SU\u0005"+
		"\r\u0000\u0000TR\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000U\u0019"+
		"\u0001\u0000\u0000\u0000VW\u0005\u000f\u0000\u0000W\u001b\u0001\u0000"+
		"\u0000\u0000\u0007)/>EHPT";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}