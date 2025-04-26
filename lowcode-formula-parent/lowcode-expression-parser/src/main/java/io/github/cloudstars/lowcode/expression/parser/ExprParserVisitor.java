// Generated from /Users/yuefengwu/code/lowcode-engines-java/lowcode-expression-parent/lowcode-expression-parser/src/main/resources/g4/ExprParser.g4 by ANTLR 4.13.2
package io.github.cloudstars.lowcode.expression.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#exprProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprProgram(ExprParser.ExprProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(ExprParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#expressionSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSequence(ExprParser.ExpressionSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#assignSingleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignSingleExpression(ExprParser.AssignSingleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TemplateStringExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStringExpression(ExprParser.TemplateStringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryExpression(ExprParser.TernaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(ExprParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PowerExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPowerExpression(ExprParser.PowerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncrementExpression(ExprParser.PreIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ObjectLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectLiteralExpression(ExprParser.ObjectLiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(ExprParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OptionalChainExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalChainExpression(ExprParser.OptionalChainExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(ExprParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreDecreaseExpression(ExprParser.PreDecreaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberDotExpression2}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDotExpression2(ExprParser.MemberDotExpression2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code ArgumentsExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentsExpression(ExprParser.ArgumentsExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinusExpression(ExprParser.UnaryMinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(ExprParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDecreaseExpression(ExprParser.PostDecreaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeofExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeofExpression(ExprParser.TypeofExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPlusExpression(ExprParser.UnaryPlusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(ExprParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitXOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitXOrExpression(ExprParser.BitXOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(ExprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(ExprParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(ExprParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(ExprParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementExpression(ExprParser.PostIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitNotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitNotExpression(ExprParser.BitNotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(ExprParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteralExpression(ExprParser.ArrayLiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDotExpression(ExprParser.MemberDotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberIndexExpression(ExprParser.MemberIndexExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(ExprParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAndExpression(ExprParser.BitAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOrExpression(ExprParser.BitOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentOperatorExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperatorExpression(ExprParser.AssignmentOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#templateStringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStringLiteral(ExprParser.TemplateStringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#templateStringAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateStringAtom(ExprParser.TemplateStringAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ExprParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#numericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(ExprParser.NumericLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#bigintLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBigintLiteral(ExprParser.BigintLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(ExprParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#elementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementList(ExprParser.ElementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#arrayElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElement(ExprParser.ArrayElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#objectLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectLiteral(ExprParser.ObjectLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyExpressionAssignment(ExprParser.PropertyExpressionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComputedPropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputedPropertyExpressionAssignment(ExprParser.ComputedPropertyExpressionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PropertyShorthand}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyShorthand(ExprParser.PropertyShorthandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#propertyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyName(ExprParser.PropertyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#identifierName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierName(ExprParser.IdentifierNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(ExprParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#reservedWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReservedWord(ExprParser.ReservedWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(ExprParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#let_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_(ExprParser.Let_Context ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(ExprParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#leftParenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftParenthesis(ExprParser.LeftParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#leftBrace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftBrace(ExprParser.LeftBraceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#rightBrace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightBrace(ExprParser.RightBraceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#eos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEos(ExprParser.EosContext ctx);
}