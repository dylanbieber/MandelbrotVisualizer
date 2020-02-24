package edu.ycp.cs201.mandelbrot;

public class Complex {
	 // ...fields...
	private double real;
	private double imag;

    // Constructor
    public Complex(double real, double imag) {
       
    	this.real = real;
    	this.imag = imag;
    }
    
    //get real field of complex
    public double getReal() {
    	
    	return this.real;
    }
    
    //set real field of complex
    public void setReal(double real) {
    	
    	this.real = real;
    }
    
    //get imaginary field of complex
    public double getImag() {
    	
    	return this.imag;
    }
    
    //set imag field of complex
    public void setImag(double imag) {
    	
    	this.imag = imag;
    }

    // add given complex number to this one, returning the Complex result
    public Complex add(Complex other) {
    	
    	Complex sum = new Complex( this.getReal() + other.getReal() , this.getImag() + other.getImag());
    
    	return sum;
    }

    // multiply given complex number by this one, returning the Complex result
    public Complex multiply(Complex other) {
    	
    	double a,b,c,real,imag;
    	
    	//FOIL terms for each complex number
    	// First (this gives a real product)
    	a = this.getReal() * other.getReal();
    	
    	// Outer / Inner (this gives an imaginary product)
    	b = (this.getReal() * other.getImag()) + (other.getReal() * this.getImag());
    	
    	//Last, i squared is -1 (this gives a real product)
    	c = -1 * (this.getImag() * other.getImag());
    	
    	//product real equals the sum of real products
    	real = a + c;
    	//product imaginary equals the sum of imaginary products (only b)
    	imag = b;
    	
    	//Construct new complex with the computed real and imaginary fields
    	Complex product = new Complex(real,imag);
    	
    	return product;
    }

    // get the magnitude of this complex number
    public double getMagnitude() {
    	double mag,real,imag;
    	
    	real = this.getReal();
    	imag = this.getImag();
    	
    	mag = Math.sqrt((real * real) + (imag * imag));
    	
    	return mag;
    }
     
}
