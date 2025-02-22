// Generated from D:/clouds/java/lowcode-parent/lowcode-object-parent/object-oql/OqlParser.g4 by ANTLR 4.13.1
package net.cf.object.oql.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OqlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OqlParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(OqlParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(OqlParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link OqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelect(OqlParser.SimpleSelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#querySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecification(OqlParser.QuerySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(OqlParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link OqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStarElement(OqlParser.SelectStarElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(OqlParser.FromClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(OqlParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link OqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceBase(OqlParser.TableSourceBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link OqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomTableItem(OqlParser.AtomTableItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(OqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#fullId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullId(OqlParser.FullIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUid(OqlParser.UidContext ctx);
	/**
	 * Visit a parse tree produced by {@link OqlParser#simpleId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleId(OqlParser.SimpleIdContext ctx);
}