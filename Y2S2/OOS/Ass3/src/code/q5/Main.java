/*
    a class to represent complex numbers with 
        * constructors to initialize
            (i) real and imaginary parts to 0
            (ii) imaginary part to 0 but to initialize the real part to user defined value
            (iii) boths part to user defined values
        * member functions to add, multiply two complex numbers

    Also, a main() function to 
        (i) create two complex numbers 3+2i and 4-2i
        (ii) print the sum and product of those numbers.
*/
package q5_2;

class Complex {
    private int real;
    private int imaginary;

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complex(int real) {
        this.real = real;
        this.imaginary = 0;
    }

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        int newReal = this.real + other.real;
        int newImaginary = this.imaginary + other.imaginary;
        return (new Complex(newReal, newImaginary));
    }

    public Complex multiply(Complex other) {
        int newReal = (this.real * other.real) - (this.imaginary * other.imaginary);
        int newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real);
        return (new Complex(newReal, newImaginary));
    }

    public void display() {
	if(real != 0) {
		if(imaginary > 0)
			System.out.print(real + " + " + imaginary + "i");
		else if(imaginary == 0)
			System.out.print(real);
		else
			System.out.print(real + " - " + imaginary * (-1) + "i");
	}
        else if(real == 0) {
                if(imaginary == 0)
                        System.out.print(real);
                else
                        System.out.print(imaginary + "i");
	}
	else {
                if(imaginary > 0)
                        System.out.print(real + " + " + imaginary + "i");
                else if(imaginary == 0)
                        System.out.print(real);
                else
                        System.out.print(real + " - " + imaginary * (-1) + "i");
	}
    }
}

public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(3, 2);
        Complex c2 = new Complex(4, -2);

        Complex sum = c1.add(c2);
        Complex product = c1.multiply(c2);

        System.out.print("Sum of ");
        c1.display();
        System.out.print(" and ");
        c2.display();
        System.out.print(" is: ");
        sum.display();
        System.out.println();

        System.out.print("Product of ");
        c1.display();
        System.out.print(" and ");
        c2.display();
        System.out.print(" is: ");
        product.display();
        System.out.println();
    }
}