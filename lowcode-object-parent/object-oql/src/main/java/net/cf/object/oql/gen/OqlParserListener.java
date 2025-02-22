// Generated from D:/clouds/java/lowcode-parent/lowcode-object-parent/object-oql/OqlParser.g4 by ANTLR 4.13.1
package net.cf.object.oql.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OqlParser}.
 */
public interface OqlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OqlParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(OqlParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(OqlParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(OqlParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(OqlParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link OqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelect(OqlParser.SimpleSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link OqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelect(OqlParser.SimpleSelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecification(OqlParser.QuerySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecification(OqlParser.QuerySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(OqlParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(OqlParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link OqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStarElement(OqlParser.SelectStarElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link OqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStarElement(OqlParser.SelectStarElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(OqlParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(OqlParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(OqlParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(OqlParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link OqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceBase(OqlParser.TableSourceBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link OqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceBase(OqlParser.TableSourceBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link OqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterAtomTableItem(OqlParser.AtomTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link OqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitAtomTableItem(OqlParser.AtomTableItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(OqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(OqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#fullId}.
	 * @param ctx the parse tree
	 */
	void enterFullId(OqlParser.FullIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#fullId}.
	 * @param ctx the parse tree
	 */
	void exitFullId(OqlParser.FullIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#uid}.
	 * @param ctx the parse tree
	 */
	void enterUid(OqlParser.UidContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#uid}.
	 * @param ctx the parse tree
	 */
	void exitUid(OqlParser.UidContext ctx);
	/**
	 * Enter a parse tree produced by {@link OqlParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void enterSimpleId(OqlParser.SimpleIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link OqlParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void exitSimpleId(OqlParser.SimpleIdContext ctx);
}