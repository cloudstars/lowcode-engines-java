// Generated from D:/clouds/java/lowcode-parent/lowcode-formula-parent/lowcode-formula-parser/src/main/g4/FxParser.g4 by ANTLR 4.13.1
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
	 * Enter a parse tree produced by {@link FxParser#expressionSequence}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSequence(FxParser.ExpressionSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#expressionSequence}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSequence(FxParser.ExpressionSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(FxParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(FxParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(FxParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(FxParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(FxParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(FxParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(FxParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(FxParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(FxParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(FxParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPlusExpression(FxParser.UnaryPlusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPlusExpression(FxParser.UnaryPlusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(FxParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(FxParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(FxParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(FxParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(FxParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link FxParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(FxParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(FxParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(FxParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FxParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(FxParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(FxParser.ArgumentContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link FxParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(FxParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link FxParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(FxParser.IdentifierContext ctx);
}