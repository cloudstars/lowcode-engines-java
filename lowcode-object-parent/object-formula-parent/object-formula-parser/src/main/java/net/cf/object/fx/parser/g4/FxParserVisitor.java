// Generated from D:/clouds/java/lowcode-parent/lowcode-object-parent/object-formula-parent/object-formula-parser/FxParser.g4 by ANTLR 4.13.1
package net.cf.object.fx.parser.g4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FxParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FxParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(FxParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(FxParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link FxParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelect(FxParser.SimpleSelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#querySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecification(FxParser.QuerySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(FxParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link FxParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStarElement(FxParser.SelectStarElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(FxParser.FromClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(FxParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link FxParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceBase(FxParser.TableSourceBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link FxParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomTableItem(FxParser.AtomTableItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(FxParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#fullId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullId(FxParser.FullIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUid(FxParser.UidContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#simpleId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleId(FxParser.SimpleIdContext ctx);
}