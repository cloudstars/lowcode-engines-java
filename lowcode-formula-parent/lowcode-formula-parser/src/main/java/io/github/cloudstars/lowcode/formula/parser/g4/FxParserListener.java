// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/resources/g4/FxParser.g4 by ANTLR 4.13.1
package io.github.cloudstars.lowcode.formula.parser.g4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FxParser}.
 */
public interface FxParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FxParser#fx}.
	 * @param ctx the parse tree
	 */
	void enterFx(FxParser.FxContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#fx}.
	 * @param ctx the parse tree
	 */
	void exitFx(FxParser.FxContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxParser#fxStatement}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(FxParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxParser#fxStatement}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(FxParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(FxParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(FxParser.NumericLiteralContext ctx);
}