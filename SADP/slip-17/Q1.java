interface Shape {
  void draw();
}

class Rectangle implements Shape {
  public void draw() {
    System.out.println("Inside Simple Rectangle");
  }
}

class Square implements Shape {
  public void draw() {
    System.out.println("Inside Simple Square");
  }
}

class RoundedRectangle implements Shape {
  public void draw() {
    System.out.println("Inside Rounded Rectangle");
  }
}

class RoundedSquare implements Shape {
  public void draw() {
    System.out.println("Inside Rounded Square");
  }
}

abstract class AbstractFactory {
  abstract Shape getShape(String shapeType);
}

class ShapeFactory extends AbstractFactory {
  public Shape getShape(String shapeType) {
    if (shapeType.equalsIgnoreCase("Rectangle")) {
      return new Rectangle();
    } else if (shapeType.equalsIgnoreCase("Square")) {
      return new Square();
    }
    return null;
  }
}

class RoundedShapeFactory extends AbstractFactory {
  public Shape getShape(String shapeType) {
    if (shapeType.equalsIgnoreCase("Rectangle")) {
      return new RoundedRectangle();
    } else if (shapeType.equalsIgnoreCase("Square")) {
      return new RoundedSquare();
    }
    return null;
  }
}

class FactoryProducer {
  public static AbstractFactory getFactory(boolean rounded) {
    if (rounded) {
      return new RoundedShapeFactory();
    } else {
      return new ShapeFactory();
    }
  }
}

public class Q1 {
  public static void main(String[] args) {

    AbstractFactory shapeFactory = FactoryProducer.getFactory(false);
    
    Shape shape1 = shapeFactory.getShape("Rectangle");
    shape1.draw();
    
    Shape shape2 = shapeFactory.getShape("Square");
    shape2.draw();
    
    AbstractFactory roundedFactory = FactoryProducer.getFactory(true);
    
    Shape shape3 = roundedFactory.getShape("Rectangle");
    shape3.draw();
    
    Shape shape4 = roundedFactory.getShape("Square");
    shape4.draw();
  }
}