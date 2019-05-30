
public class Monom {
	int exp;
	double coef;
	public Monom(double coef,int exp) {
		super();
		this.coef=coef;
		this.exp=exp;
	}
	
	
	
		int get_exp() {
		
		return this.exp;
	}
		double get_coef() {
		
		return this.coef;
	}
	void setcoef(double x) {
		
		this.coef=x;
	
	}
	void setexp(int y)
	{
		this.exp=y;
	}
	public int compareTo(Monom arg) {
		
		if(arg.get_exp()==this.get_exp()) {
			
			return -1;
		}else if(arg.get_exp()>this.get_exp()) {
			
			return 1;
		}
		
		return 0;
	}
}
