public class Symbol{
    public double coefficient;
    public int power;

    public static void main(String[] args){
        Symbol term = new Symbol(3,2);

        term.print(); 
    }

    public Symbol(double coeff, int pow){
        this.coefficient = coeff;
        this.power = pow;
    }

    public void print(){
        System.out.print(this.coefficient);
        System.out.print("X^");
        System.out.print(this.power);
    }

    public void setValues(double coeff, int pow){
            this.coefficient = coeff;
            this.power = pow;
        }

    public Symbol multiply(Symbol B){
        double newCoeff = this.coefficient * B.coefficient;
        int newPow = this.power + B.power;
        return new Symbol(newCoeff,newPow);
    }

    public boolean addTo(Symbol B){
        double oldCoeff = B.coefficient;
        if (B.power==this.power){
            B.coefficient = oldCoeff + this.coefficient;
            return true;
        }
        else{return false;}
    }

    public boolean add(Symbol B){
        double oldCoeff = B.coefficient;
        if (B.power==this.power){
            this.coefficient = oldCoeff + this.coefficient;
            return true;
        }
        else{return false;}
    }
}

class SymbolComparator implements java.util.Comparator<Symbol> {
    @Override
    public int compare(Symbol a, Symbol b) {
        return a.power - b.power;
    }
}