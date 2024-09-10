public class Matrix {
    private int size;
    private double[][] values;
  
    public Matrix(int size){
        this.setSize(size);
    }
    
    public void setSize(int newSize){
        this.size = newSize;
        this.values = null;// clear the values because size has changed
    }

    public int getSize(){
        return this.size;
    }

    public void setValues(double[][] newValues){
        // first check new array is the right size
        if (newValues.length != this.size){
            System.err.println("Wrong size array for the matrix");
        }
        for (int i = 0; i < this.size; i++){
            if (newValues[i].length != this.size){
                System.err.println("Wrong size array for the matrix");
            }
        }
        this.values = newValues;
    }

    public double[][] getValues(){
        return this.values;
    }

    public void print(){// print a matrix in a nice format rounded to 3 d.p
        for (int i = 0; i < this.size; i++){
            System.out.print("[");
            for (int j = 0; j < this.size; j++){
                double value = this.values[i][j];
                System.out.print((double)Math.round(value * 1000d) / 1000d);
                System.out.print(" ");
            }
            System.out.println("]");
        }
    }

    public Matrix matMultiply(Matrix B){// multiply two matrices where A.matMultiply(B) = AB and B.matMultiply(A) = BA
        Matrix result = new Matrix(this.size);
        result.setSize(this.size);
        double[][] resultValues = new double[this.size][this.size];
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                resultValues[i][j] = 0;
                for (int count = 0; count < this.size; count++)
                    resultValues[i][j] += this.values[i][count]*B.values[count][j];
            }
        }
        result.setValues(resultValues);
        return result;
    }

    public Matrix matAddition(Matrix B){// add two matrices
        Matrix result = new Matrix(this.size);
        result.setSize(this.size);
        double[][] resultValues = new double[this.size][this.size];
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                resultValues[i][j] = this.values[i][j] + B.values[i][j];
            }
        }
        result.setValues(resultValues);
        return result;
    }

    public Matrix multiply(double a){// multiply by a real factor
        Matrix result = new Matrix(this.size);
        result.setSize(this.size);
        double[][] resultValues = new double[this.size][this.size];
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                resultValues[i][j] = a * this.values[i][j];
            }
        }
        result.setValues(resultValues);
        return result;
    }

    public Matrix matSubtraction(Matrix B){
        return this.matAddition(B.multiply(-1));
    }

    public double determinant(){
        PermutationSet allPermutations = new PermutationSet(this.size);// generate an instance of the PermutationSet class
        double total = 0;

        int[][] sn = allPermutations.getPermutations();

        for (int i = 0; i<sn.length; i++){// loop through the set of permutations, Sn
            double[] matrixEntries = new double[this.size];// a list of the matrix entries that are selected by this permutation
            for (int j = 0; j<this.size; j++){// loop through to find the values of the selected entries
                matrixEntries[j] = this.values[j][sn[i][j]];// sn[i][j] is the value that j gets mapped to in permutation i
            }
            total += allPermutations.sgn(sn[i]) * product(matrixEntries);// add the signed product of these entries to the running total
        }
        return total;
    }

    private double product(double[] nums){// calculates the product of all numbers in array
        double total = 1;
        for (int i = 0; i<nums.length; i++){
            total *= nums[i];
        }
        return total;
    }

    public Matrix adjugate(){// find the adjugate of the matrix
        Matrix result = new Matrix(this.size);
        double[][] values = new double[this.size][this.size];
        for (int i=0; i < this.size; i++){
            for (int j=0; j < this.size; j++){
                values[i][j] = (Math.pow(-1, i+j)) * (this.minor(j,i)).determinant();// each entry of the adj matrix is the signed det of the minor matrix
            }
        }
        result.setValues(values);
        return result;
    }

    public Matrix minor(int i, int j){// return the minor(i,j) of a matrix by eliminating column i and row j
        Matrix result =  new Matrix(this.size - 1);
        double[][] values = new double[this.size - 1][this.size - 1];
        for (int x=0; x < this.size - 1; x++){
            for (int y=0; y < this.size - 1; y++){
                int newX;
                int newY;
                if (x>=i){newX = x+1;}else{newX=x;}
                if (y>=j){newY = y+1;}else{newY=y;}
                values[x][y] = this.values[newX][newY];    
            }
        }
        result.setValues(values);
        return result;
    }
    
    public Matrix inverse(){// return the inverse of the matrix
        Matrix inverseMatrix = this.adjugate().multiply(1 / this.determinant());
        return inverseMatrix;
    }

}
