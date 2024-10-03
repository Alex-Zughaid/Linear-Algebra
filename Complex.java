public class Complex {
    private double real;
    private double imag;

    public Complex(double r, double i){
        real = r;
        imag = i;
    }

    public double[] getValues(){
        double[] output = {this.real,this.imag};
        return output;
    } 
}