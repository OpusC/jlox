package craftinginterpreters.lox;

public class AstPrinter implements Expr.Visitor<String> {

  public String print(Expr expr) {
    return expr.accept(this);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    return parenthesizer(expr.operator.lexeme, expr.left, expr.right);
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return parenthesizer("group", expr.expression);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null) return "nil";
    return expr.value.toString();
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return parenthesizer(expr.operator.lexeme, expr.right);
  }

  // ... is called Varargs in Java
  // It can be used ONLY in the final argument position
  // it is shorthand for allowing an array of the specified type to be passed in
  private String parenthesizer(String name, Expr... exprs) {
    StringBuilder builder = new StringBuilder();

    builder.append("(").append(name);
    for (Expr expr : exprs) {
      builder.append(" ");
      builder.append(expr.accept(this));
    }
    builder.append(")");

    return builder.toString();
  }

//  public static void main (String[] args) {
//    Expr expression = new Expr.Binary(
//            new Expr.Unary(
//                    new Token(TokenType.MINUS, "-", null, 1),
//                    new Expr.Literal(123)),
//            new Token(TokenType.STAR, "*", null, 1),
//            new Expr.Grouping(
//                    new Expr.Literal(45.67)
//            )
//            );
//            System.out.println(new AstPrinter().print(expression));
//  }
  
}
