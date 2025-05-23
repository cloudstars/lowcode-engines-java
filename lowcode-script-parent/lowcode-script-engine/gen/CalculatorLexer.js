// Generated from D:/clouds/java/lowcode-parent/lowcode-function-parent/function-engine/src/test/resources/Calculator.g4 by ANTLR 4.13.1
// jshint ignore: start
import antlr4 from 'antlr4';


const serializedATN = [4,0,12,63,6,-1,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,2,
4,7,4,2,5,7,5,2,6,7,6,2,7,7,7,2,8,7,8,2,9,7,9,2,10,7,10,2,11,7,11,1,0,1,
0,1,1,1,1,1,2,1,2,1,3,1,3,1,4,1,4,1,5,1,5,1,6,1,6,1,7,4,7,41,8,7,11,7,12,
7,42,1,8,4,8,46,8,8,11,8,12,8,47,1,9,3,9,51,8,9,1,9,1,9,1,10,1,10,1,11,4,
11,58,8,11,11,11,12,11,59,1,11,1,11,0,0,12,1,1,3,2,5,3,7,4,9,5,11,6,13,7,
15,8,17,9,19,10,21,11,23,12,1,0,3,2,0,65,90,97,122,1,0,48,57,2,0,9,9,32,
32,66,0,1,1,0,0,0,0,3,1,0,0,0,0,5,1,0,0,0,0,7,1,0,0,0,0,9,1,0,0,0,0,11,1,
0,0,0,0,13,1,0,0,0,0,15,1,0,0,0,0,17,1,0,0,0,0,19,1,0,0,0,0,21,1,0,0,0,0,
23,1,0,0,0,1,25,1,0,0,0,3,27,1,0,0,0,5,29,1,0,0,0,7,31,1,0,0,0,9,33,1,0,
0,0,11,35,1,0,0,0,13,37,1,0,0,0,15,40,1,0,0,0,17,45,1,0,0,0,19,50,1,0,0,
0,21,54,1,0,0,0,23,57,1,0,0,0,25,26,5,61,0,0,26,2,1,0,0,0,27,28,5,40,0,0,
28,4,1,0,0,0,29,30,5,41,0,0,30,6,1,0,0,0,31,32,5,42,0,0,32,8,1,0,0,0,33,
34,5,47,0,0,34,10,1,0,0,0,35,36,5,43,0,0,36,12,1,0,0,0,37,38,5,45,0,0,38,
14,1,0,0,0,39,41,7,0,0,0,40,39,1,0,0,0,41,42,1,0,0,0,42,40,1,0,0,0,42,43,
1,0,0,0,43,16,1,0,0,0,44,46,7,1,0,0,45,44,1,0,0,0,46,47,1,0,0,0,47,45,1,
0,0,0,47,48,1,0,0,0,48,18,1,0,0,0,49,51,5,13,0,0,50,49,1,0,0,0,50,51,1,0,
0,0,51,52,1,0,0,0,52,53,5,10,0,0,53,20,1,0,0,0,54,55,5,59,0,0,55,22,1,0,
0,0,56,58,7,2,0,0,57,56,1,0,0,0,58,59,1,0,0,0,59,57,1,0,0,0,59,60,1,0,0,
0,60,61,1,0,0,0,61,62,6,11,0,0,62,24,1,0,0,0,5,0,42,47,50,59,1,6,0,0];


const atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

const decisionsToDFA = atn.decisionToState.map( (ds, index) => new antlr4.dfa.DFA(ds, index) );

export default class CalculatorLexer extends antlr4.Lexer {

    static grammarFileName = "Calculator.g4";
    static channelNames = [ "DEFAULT_TOKEN_CHANNEL", "HIDDEN" ];
	static modeNames = [ "DEFAULT_MODE" ];
	static literalNames = [ null, "'='", "'('", "')'", "'*'", "'/'", "'+'", 
                         "'-'", null, null, null, "';'" ];
	static symbolicNames = [ null, null, null, null, "MUL", "DIV", "ADD", "SUB", 
                          "ID", "INT", "NEWLINE", "DELIMITER", "WS" ];
	static ruleNames = [ "T__0", "T__1", "T__2", "MUL", "DIV", "ADD", "SUB", 
                      "ID", "INT", "NEWLINE", "DELIMITER", "WS" ];

    constructor(input) {
        super(input)
        this._interp = new antlr4.atn.LexerATNSimulator(this, atn, decisionsToDFA, new antlr4.atn.PredictionContextCache());
    }
}

CalculatorLexer.EOF = antlr4.Token.EOF;
CalculatorLexer.T__0 = 1;
CalculatorLexer.T__1 = 2;
CalculatorLexer.T__2 = 3;
CalculatorLexer.MUL = 4;
CalculatorLexer.DIV = 5;
CalculatorLexer.ADD = 6;
CalculatorLexer.SUB = 7;
CalculatorLexer.ID = 8;
CalculatorLexer.INT = 9;
CalculatorLexer.NEWLINE = 10;
CalculatorLexer.DELIMITER = 11;
CalculatorLexer.WS = 12;



