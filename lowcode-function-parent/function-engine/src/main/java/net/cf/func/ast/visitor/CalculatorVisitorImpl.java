package net.cf.func.ast.visitor;

import net.cf.func.ast.parser.CalculatorParser;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<String> {

    @Override
    public String visitProgram(CalculatorParser.ProgramContext ctx) {
        return "转码之后的可被某个EL引擎执行的源代码";
    }
}
