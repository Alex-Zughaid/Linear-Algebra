import java.util.ArrayList;
import java.util.Collections;

public class Polynomial{
    public ArrayList<Symbol> terms;

    public static void main(String[] args) {
        Symbol term1 = new Symbol(3,2);
        Symbol term2 = new Symbol(1,3);
        Symbol term3 = new Symbol(2,9);
        Symbol term4 = new Symbol(6,1);
        Symbol term5 = new Symbol(4,0);
        Symbol term6 = new Symbol(2.6,3);

        Polynomial expression = new Polynomial();
        expression.addTerm(term1);
        expression.addTerm(term2);
        expression.addTerm(term3);
        expression.addTerm(term4);
        expression.addTerm(term5);
        expression.addTerm(term6);



        expression.print();
        expression.sort();
        expression.print();
        Polynomial groupedExpression = expression.groupTerms();
        groupedExpression.print();
    }

    public Polynomial(){
        this.terms = new ArrayList<Symbol>();
    }

    public Polynomial(ArrayList<Symbol> x){
        this.terms = x;
    }

    public void print(){
        for (int i = 0; i < this.terms.size(); i++){
            this.terms.get(i).print();
            System.out.print(" + ");
        }
        System.out.println();
    }

    public void addTerm(Symbol x){
        terms.add(x);
    }

    public Polynomial groupTerms(){
        int prevPower = -1;// -1 so it does not equal the power of first term because first term is minimum 1 
        ArrayList<Symbol> newTerms = new ArrayList<Symbol>();

        for(int i = 0; i < this.terms.size(); i++){
            if (prevPower == this.terms.get(i).power){
                newTerms.getLast().add(this.terms.get(i));
            }
            else{
                newTerms.add(this.terms.get(i));
            }
            prevPower = this.terms.get(i).power;
        }

        return new Polynomial(newTerms);
    }
    
    public boolean equals(Polynomial P){
        if (this.terms.size() != P.terms.size()){return false;}
        for (int i = 0; i<this.terms.size();i++){
            if (this.terms.get(i) != P.terms.get(i)){
                return false;
            }
        }
        return true;
    }

    public void sort(){
        Collections.sort(this.terms, new SymbolComparator());
    }
}