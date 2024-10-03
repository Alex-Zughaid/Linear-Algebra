public class Demo {
    public static void main(String[] args) throws Exception {
        System.out.println("A demo for how to use the Matrix class");

        Matrix I7 = Matrix.identity(7);
        System.out.println("7x7 identity matrix:");
        I7.print();
       
        Matrix A = new Matrix(7);
        double[][] Avals = {{6,-1,-1,0,1,0,4}, {1,1,1,2,-5,2,0}, {10,1,3,-8,-1,3,1},{1,0,4,4,1,0,3}, {8,1,2,11,-6,2,0}, {1,0,7,9,-2,3,1}, {2,4,1,0,0,6,3}};
        A.setValues(Avals);
        System.out.println("a 7x7 matrix with a non-zero determinant:");
        A.print();

        System.out.print("the determinant is: ");
        System.out.println(A.determinant());

        System.out.println("the inverse is:");
        Matrix inverseA = A.inverse();
        inverseA.print();

        System.out.println("calculate the left and right multiplication of the inverse with the original matrix and check if this is equal to the identity matrix:");
        Matrix product1 = A.matMultiply(inverseA);
        System.out.println(product1.equals(I7));
        Matrix product2 = inverseA.matMultiply(A);
        System.out.println(product2.equals(I7));


    }
}
