// Generated from D:/clouds/java/lowcode-parent/lowcode-object-parent/object-formula-parent/object-formula-parser/FxParser.g4 by ANTLR 4.13.1
package net.cf.object.fx.parser.g4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FxParser}.
 */
public interface FxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FxParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(FxParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(FxParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(FxParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(FxParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link FxParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelect(FxParser.SimpleSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link FxParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelect(FxParser.SimpleSelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecification(FxParser.QuerySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecification(FxParser.QuerySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(FxParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(FxParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link FxParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStarElement(FxParser.SelectStarElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link FxParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStarElement(FxParser.SelectStarElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(FxParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(FxParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(FxParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(FxParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link FxParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceBase(FxParser.TableSourceBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link FxParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceBase(FxParser.TableSourceBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link FxParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterAtomTableItem(FxParser.AtomTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link FxParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitAtomTableItem(FxParser.AtomTableItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(FxParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(FxParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#fullId}.
	 * @param ctx the parse tree
	 */
	void enterFullId(FxParser.FullIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#fullId}.
	 * @param ctx the parse tree
	 */
	void exitFullId(FxParser.FullIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#uid}.
	 * @param ctx the parse tree
	 */
	void enterUid(FxParser.UidContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#uid}.
	 * @param ctx the parse tree
	 */
	void exitUid(FxParser.UidContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void enterSimpleId(FxParser.SimpleIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void exitSimpleId(FxParser.SimpleIdContext ctx);
}