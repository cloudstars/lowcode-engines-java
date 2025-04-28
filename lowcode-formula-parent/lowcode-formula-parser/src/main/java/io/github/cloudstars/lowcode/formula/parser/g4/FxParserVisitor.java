// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/resources/g4/FxParser.g4 by ANTLR 4.13.1
package io.github.cloudstars.lowcode.formula.parser.g4;
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
	 * Visit a parse tree produced by {@link FxParser#fx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFx(FxParser.FxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxParser#fxStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(FxParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(FxParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link FxParser#numericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(FxParser.NumericLiteralContext ctx);
}