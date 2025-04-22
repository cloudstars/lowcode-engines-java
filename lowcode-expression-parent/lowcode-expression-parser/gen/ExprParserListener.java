// Generated from D:/clouds/java/lowcode-parent/lowcode-expression-parent/lowcode-expression-parser/src/main/resources/g4/ExprParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#exprProgram}.
	 * @param ctx the parse tree
	 */
	void enterExprProgram(ExprParser.ExprProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#exprProgram}.
	 * @param ctx the parse tree
	 */
	void exitExprProgram(ExprParser.ExprProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(ExprParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(ExprParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expressionSequence}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSequence(ExprParser.ExpressionSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expressionSequence}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSequence(ExprParser.ExpressionSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#assignSingleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignSingleExpression(ExprParser.AssignSingleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#assignSingleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignSingleExpression(ExprParser.AssignSingleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TemplateStringExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterTemplateStringExpression(ExprParser.TemplateStringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TemplateStringExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitTemplateStringExpression(ExprParser.TemplateStringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(ExprParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(ExprParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(ExprParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(ExprParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PowerExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowerExpression(ExprParser.PowerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PowerExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowerExpression(ExprParser.PowerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreIncrementExpression(ExprParser.PreIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreIncrementExpression(ExprParser.PreIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ObjectLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteralExpression(ExprParser.ObjectLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ObjectLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteralExpression(ExprParser.ObjectLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(ExprParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(ExprParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OptionalChainExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptionalChainExpression(ExprParser.OptionalChainExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OptionalChainExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptionalChainExpression(ExprParser.OptionalChainExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(ExprParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(ExprParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreDecreaseExpression(ExprParser.PreDecreaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreDecreaseExpression(ExprParser.PreDecreaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDotExpression2}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberDotExpression2(ExprParser.MemberDotExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDotExpression2}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberDotExpression2(ExprParser.MemberDotExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgumentsExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterArgumentsExpression(ExprParser.ArgumentsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgumentsExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitArgumentsExpression(ExprParser.ArgumentsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(ExprParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(ExprParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(ExprParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(ExprParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostDecreaseExpression(ExprParser.PostDecreaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDecreaseExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostDecreaseExpression(ExprParser.PostDecreaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeofExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterTypeofExpression(ExprParser.TypeofExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeofExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitTypeofExpression(ExprParser.TypeofExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPlusExpression(ExprParser.UnaryPlusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPlusExpression(ExprParser.UnaryPlusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(ExprParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(ExprParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitXOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitXOrExpression(ExprParser.BitXOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitXOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitXOrExpression(ExprParser.BitXOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(ExprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(ExprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(ExprParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(ExprParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(ExprParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(ExprParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(ExprParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(ExprParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementExpression(ExprParser.PostIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementExpression(ExprParser.PostIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitNotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitNotExpression(ExprParser.BitNotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitNotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitNotExpression(ExprParser.BitNotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(ExprParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(ExprParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralExpression(ExprParser.ArrayLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLiteralExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralExpression(ExprParser.ArrayLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberDotExpression(ExprParser.MemberDotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDotExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberDotExpression(ExprParser.MemberDotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberIndexExpression(ExprParser.MemberIndexExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberIndexExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberIndexExpression(ExprParser.MemberIndexExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(ExprParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(ExprParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpression(ExprParser.BitAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitAndExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpression(ExprParser.BitAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpression(ExprParser.BitOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitOrExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpression(ExprParser.BitOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentOperatorExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperatorExpression(ExprParser.AssignmentOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentOperatorExpression}
	 * labeled alternative in {@link ExprParser#singleExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperatorExpression(ExprParser.AssignmentOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#templateStringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterTemplateStringLiteral(ExprParser.TemplateStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#templateStringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitTemplateStringLiteral(ExprParser.TemplateStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#templateStringAtom}.
	 * @param ctx the parse tree
	 */
	void enterTemplateStringAtom(ExprParser.TemplateStringAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#templateStringAtom}.
	 * @param ctx the parse tree
	 */
	void exitTemplateStringAtom(ExprParser.TemplateStringAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ExprParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ExprParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(ExprParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(ExprParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#bigintLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBigintLiteral(ExprParser.BigintLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#bigintLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBigintLiteral(ExprParser.BigintLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(ExprParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(ExprParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#elementList}.
	 * @param ctx the parse tree
	 */
	void enterElementList(ExprParser.ElementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#elementList}.
	 * @param ctx the parse tree
	 */
	void exitElementList(ExprParser.ElementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void enterArrayElement(ExprParser.ArrayElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arrayElement}.
	 * @param ctx the parse tree
	 */
	void exitArrayElement(ExprParser.ArrayElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteral(ExprParser.ObjectLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteral(ExprParser.ObjectLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void enterPropertyExpressionAssignment(ExprParser.PropertyExpressionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void exitPropertyExpressionAssignment(ExprParser.PropertyExpressionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComputedPropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void enterComputedPropertyExpressionAssignment(ExprParser.ComputedPropertyExpressionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComputedPropertyExpressionAssignment}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void exitComputedPropertyExpressionAssignment(ExprParser.ComputedPropertyExpressionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PropertyShorthand}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void enterPropertyShorthand(ExprParser.PropertyShorthandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PropertyShorthand}
	 * labeled alternative in {@link ExprParser#propertyAssignment}.
	 * @param ctx the parse tree
	 */
	void exitPropertyShorthand(ExprParser.PropertyShorthandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyName(ExprParser.PropertyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#propertyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyName(ExprParser.PropertyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#identifierName}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierName(ExprParser.IdentifierNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#identifierName}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierName(ExprParser.IdentifierNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ExprParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ExprParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void enterReservedWord(ExprParser.ReservedWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#reservedWord}.
	 * @param ctx the parse tree
	 */
	void exitReservedWord(ExprParser.ReservedWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(ExprParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(ExprParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#let_}.
	 * @param ctx the parse tree
	 */
	void enterLet_(ExprParser.Let_Context ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#let_}.
	 * @param ctx the parse tree
	 */
	void exitLet_(ExprParser.Let_Context ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(ExprParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(ExprParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#leftParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterLeftParenthesis(ExprParser.LeftParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#leftParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitLeftParenthesis(ExprParser.LeftParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#leftBrace}.
	 * @param ctx the parse tree
	 */
	void enterLeftBrace(ExprParser.LeftBraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#leftBrace}.
	 * @param ctx the parse tree
	 */
	void exitLeftBrace(ExprParser.LeftBraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#rightBrace}.
	 * @param ctx the parse tree
	 */
	void enterRightBrace(ExprParser.RightBraceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#rightBrace}.
	 * @param ctx the parse tree
	 */
	void exitRightBrace(ExprParser.RightBraceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#eos}.
	 * @param ctx the parse tree
	 */
	void enterEos(ExprParser.EosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#eos}.
	 * @param ctx the parse tree
	 */
	void exitEos(ExprParser.EosContext ctx);
}