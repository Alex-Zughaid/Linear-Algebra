// this is an attempt to convert some of my previous python code to java using OOP
import java.util.ArrayList;

public class PermutationSet{
    ArrayList<int[]> sn;// use arraylist so the list can be appended to
    int size;

    public PermutationSet(int num){// constructor method
        int[] list = genList(num);
        this.size = list.length;
        this.sn = new ArrayList<>();
        this.recursion(list, 0);
    }

    private void recursion(int[] list, int i){// generate permutations and adds them to the sn list
        if (i == list.length) {// keep swapping until a depth that equals the list length
            // Add a copy of the current permutation to the list
            sn.add(list.clone());
            return;
        }
        for (int j = i; j < list.length; j++){
            swap(list, i, j);
            recursion(list, i + 1);// recursive call
            swap(list, i, j);
        }
    }

    // in-place list element swapping to make code neater in recursion function
    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int[][] getPermutations(){// convert the arrayList to a standard array when it is exported
        int[][] result = new int[this.factorial(this.size)][this.size];
        for (int i = 0; i < this.factorial(this.size); i++) {
            result[i] = sn.get(i);
        }
        return result;
    }

    private int factorial(int n){// n!
        if (n > 1) {
            return n * factorial(n - 1);
        } else {
            return 1;
        }
    }

    private int[] genList(int n){// genList(n) = {0,1,2,...,n-1}
        int[] list = new int[n]; 
        for (int i = 0; i<n; i++){
            list[i]=i;
        }
        return list;
    }

    public int sgn(int[] permutation){// work out the sign of permutatuon by counting number of inversions (amount of numbers in the wrong order)
        int inversions = 0;
        for (int i = 0; i < permutation.length; i++) {
            for (int j = i + 1; j < permutation.length; j++) {// check every element ahead of i in the permutation
                if (permutation[i] > permutation[j]) {
                    inversions++;// increment inversions variable if out of order
                }
            }
        }
        if (inversions % 2 == 0){// if inversions is even the sign is 1 i.e the permutation has even parity
            return 1;
        }
        else{
            return -1;
        }
    }
}
