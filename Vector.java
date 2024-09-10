public class Vector {
    private int size;
    private double[] values;

    public static void main(String[] args){
        Vector x = new Vector();
        x.setSize(3);
        double[] xvals = {1,2,3};
        x.setValues(xvals);
        x.print();
    }

    public void setSize(int newSize){
        this.size = newSize;
        this.values = null;// clear the values because size has changed
    }

    public void setValues(double[] newValues){
        // first check new array is the right size
        if (newValues.length != this.size){
            System.err.println("Wrong size array for the vector");
        }
        this.values = newValues;
    }

    public void print(){// print a vector in a nice format
        for (int i = 0; i < this.size; i++){
            System.out.print("[");
            System.out.print(this.values[i]);
            System.out.print(" ");
            System.out.println("]");
        }
    }

    public Vector add(Vector x){
        if (x.size != this.size){
            System.err.println("Vectors differ in size");
        }
        Vector result = new Vector();
        double[] resultValues = new double[this.size]; 
        result.setSize(this.size);
        for (int i = 0; i < this.size; i++){
            resultValues[i] = this.values[i] + x.values[i];
        }
        result.setValues(resultValues);
        return result;
    }

    public Vector multiply(double x){
        Vector result = new Vector();
        double[] resultValues = new double[this.size]; 
        result.setSize(this.size);
        for (int i = 0; i < this.size; i++){
            resultValues[i] = x * this.values[i];
        }
        result.setValues(resultValues);
        return result;
    }

    public Vector subtract(Vector x){
        return this.add(x.multiply(-1));
    }

    public double dot(Vector x){
        if (x.size != this.size){
            System.err.println("Vectors differ in size");
        }
        double total = 0;
        for (int i = 0; i < this.size; i++){
            total += this.values[i] * x.values[i];
        }
        return total;
    }
}