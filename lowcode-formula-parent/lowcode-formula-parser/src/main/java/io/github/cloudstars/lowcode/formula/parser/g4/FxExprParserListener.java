// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/resources/g4/FxExprParser.g4 by ANTLR 4.13.1
package io.github.cloudstars.lowcode.formula.parser.g4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FxExprParser}.
 */
public interface FxExprParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FxExprParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FxExprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxExprParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FxExprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxExprParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(FxExprParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxExprParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(FxExprParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FxExprParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FxExprParser.LiteralContext ctx);
}