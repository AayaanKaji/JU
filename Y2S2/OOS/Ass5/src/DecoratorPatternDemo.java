package src;

class ShapeDecorator implements Shape {
    private Shape shape;

    ShapeDecorator (Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
        if (!(shape instanceof ShapeDecorator))
            System.out.println("\tWith basic decorations");
    }
}

class RedShapeDecorator extends ShapeDecorator {
    private int redBorder;

    RedShapeDecorator (Shape shape, int redBorder) {
        super(shape);
        this.redBorder = redBorder;
    }

    public void setRedBorder(int redBorder) {
        this.redBorder = redBorder;
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("\tWith " + redBorder + "px, red border");
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        circle.draw();

        circle = new ShapeDecorator(circle);
        circle.draw();

        circle = new RedShapeDecorator(circle, 2);
        circle.draw();


        RedShapeDecorator rect = new RedShapeDecorator(new Rectangle(3, 6), 4);
        rect.draw();
        rect.setRedBorder(10);
        rect.draw();
    }
}
