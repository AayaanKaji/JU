package src;

class ShapeFactory {
    public static Shape getShape(String shapeType, double... params) {
        if (shapeType == null || params == null) {
            return null;
        }

        switch (shapeType.toLowerCase()) {
            case "circle":
                if (params.length < 1)
                    return null;
                return new Circle(params[0]);
            case "square":
                if (params.length < 1)
                    return null;
                return new Square(params[0]);
            case "rectangle":
                if (params.length < 2)
                    return null;
                return new Rectangle(params[0], params[1]);
            default:
                return null;
        }
    }
}

public class FactoryPatternDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle", 5);
        if (circle != null) {
            circle.draw();
        }

        Shape square = ShapeFactory.getShape("square", 5);
        if (square != null) {
            square.draw();
        }

        Shape rectangle = ShapeFactory.getShape("rectangle", 3, 6);
        if (rectangle != null) {
            rectangle.draw();
        }
    }
}