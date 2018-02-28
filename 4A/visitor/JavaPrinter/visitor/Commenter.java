package visitor;

/*
 * It's 2 am in the computer science building.
 * I think I'm close to done.
 * but it could all be wrong.
 * I have work tomorrow at 9 am. At HPE
 * Doing the same stuff
 * My ride went to bed; I have to walk home.
 *
 * Well, I'm done.
 * I'm not sure what I learned about visitors, but I think I know something about state machines now. 'Cause this is a really bad state machine.
 */

import syntaxtree.NodeToken;

public class Commenter extends TreeDumper{
    private String currentLine;
    private int line;
    private int cursor;
    private int nestDepth;

    private String className;
    private String nestedClassName;

    private boolean inClassAttributes;
    private boolean hasClassAttributes;

    private boolean classLine;

    private boolean consumeVarT;
    private boolean varMethod;
    private boolean staticLine;
    private boolean varLine;
    private boolean methodLine;

    private boolean constructorLine;

    private String name;

    private static final String commentHeader = "/*************\n";
    private static final String commentCloser = "\n*************/\n";

    private static final String closeBrace = "}";
    private static final String openBrace = "{";
    private static final String openParen = "(";

    private static final String classString = "class";
    private static final String publicString = "public";
    private static final String privateString = "private";
    private static final String protectedString = "protected";
    private static final String staticString = "static";
    private static final String finalString = "final";

    public Commenter(){
        currentLine = "";
        line = 1;
        cursor = 1;
        nestDepth = 0;
    }

    @Override
    public void visit(NodeToken n){
        String tok = n.tokenImage;

        while (line < n.beginLine){
            outLine();
        }

        if(classLine && name.isEmpty())
            name = tok;

        if (varMethod){
            if (consumeVarT && !(tok.equals(finalString))){
                if(name.isEmpty() && !(tok.equals(finalString)))
                    name = tok;
                else if(!(name.isEmpty()) && tok.equals(openParen)){
                    methodLine = true;
                    varMethod = false;
                }
                else if (!name.isEmpty()){
                    varLine = true;
                    varMethod = false;
                }
            }
            else if (name.isEmpty() && tok.equals(staticString))
                staticLine=true;
            else if (name.isEmpty() && (tok.equals(className) || tok.equals(nestedClassName))) {
                constructorLine = true;
                name = tok;
                consumeVarT = true;
                varMethod = false;
            }
            else
                consumeVarT = true;
        }


        switch (tok){
            case openBrace:
                nestDepth++;
                break;
            case closeBrace:
                nestDepth--;
                break;
            case classString:
                classLine = true;
                name = "";
                break;
            case publicString:
            case privateString:
            case protectedString:
                varMethod = true;
                consumeVarT = false;
                name = "";
                break;
        }

        while (cursor < n.beginColumn){
            currentLine+=" ";
            cursor++;
        }
        cursor+=tok.length();
        currentLine+=tok;
    }

    private void outLine(){
        line++;
        String comment = createComment();
        System.out.println(comment+currentLine);
        currentLine = "";
        cursor = 1;

        classLine = false;
        varMethod = false;
        staticLine = false;
        varLine = false;
        methodLine = false;
        consumeVarT = false;
        constructorLine = false;
    }

    private String createComment(){
        String comment = "";
        if (classLine && nestDepth==0) {
            className = name;
            inClassAttributes = true;
            hasClassAttributes = false;
            return commentHeader + "New class " + name + commentCloser;
        }
        if (classLine){
            nestedClassName = name;
            inClassAttributes = true;
            hasClassAttributes = false;
            return commentHeader + "New nested class " + name + commentCloser;
        }

        if (inClassAttributes && varLine) {
            hasClassAttributes = true;
            inClassAttributes = false;
            comment += "// Class variable definition begins\n";
        } else if (hasClassAttributes && !varLine){
            hasClassAttributes = false;
            comment += "// Class variable definition ends\n";
        }

        if (methodLine) {
            comment += commentHeader;
            comment += "New ";
            if (staticLine)
                comment += "static ";
            comment += "method ";
            comment += name;
            comment += commentCloser;
        }

        if (constructorLine){
            comment += commentHeader+"New constructor " + name + commentCloser;
        }

        return comment;
    }

}
