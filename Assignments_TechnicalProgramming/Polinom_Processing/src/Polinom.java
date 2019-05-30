import java.util.ArrayList;


public class Polinom {
	ArrayList<Monom> polinom =new ArrayList<Monom>();
	
	public Polinom(Monom monom)
	{
		this.polinom.add(monom);
	}

	void Polinom(Monom monom)
	{
		this.polinom.add(monom);
	}
	public void addTerm(double coef, int exp) {
		if (polinom.size() > exp && polinom.get(exp) != null) {
			double coefExistent = polinom.get(exp).get_coef();
			polinom.set(exp, new Monom(coefExistent + coef, exp));
		} else {
			polinom.add(exp, new Monom(coef, exp));
		}
	}
	Boolean verifpol(Monom monom)
	{
		for(Monom x:this.polinom)
		{
			if(x.get_coef()==monom.get_coef() && x.get_exp()==monom.get_exp())
			{
				return false;
			}
		}
		return true;
	}
 @Override public String toString() {
	String s = null;	
	 for(Monom monom:polinom) 
	 {
		 if(monom.get_exp()>=0)
		 {
		 if(s!= null)
	 
		 {	 if(monom.get_coef()!=0) 
				s=s+"+"+monom.get_coef()+"*x^"+monom.get_exp();
		 	
			 
		 
		 }
		 else
		 {
			 if(monom.get_coef()!=0)
				 s=monom.get_coef()+"*x^"+monom.get_exp();
			 
		 }
		 }
	 }
	 
	 return s;
 }
 Polinom adunare(Polinom pol) {
		Polinom rez=new Polinom(new Monom(0,0));
		rez.polinom.clear();
      int bool=0;
		for(Monom x:this.polinom) {
			bool=0;
			for(Monom y:pol.polinom) {
				if(x.get_exp()==y.get_exp()) {
					bool=1;
					rez.polinom.add(new Monom(x.get_coef()+y.get_coef(),x.get_exp()));
				}
			}
		}
		for(Monom x:pol.polinom) {
			   bool=0;
		
			for(Monom y:rez.polinom) {
				
			if(x.get_exp()==y.get_exp()) {
					
					bool=1;
				}
			
			}
		     if(bool==0){
		    	 	rez.polinom.add(new Monom(x.get_coef(),x.get_exp()));  } 
		    	}
		for(Monom x:this.polinom) {
			   bool=0;
		
			for(Monom y:rez.polinom) {
				
			if(x.get_exp()==y.get_exp()) {
					
					bool=1;
				}
			
			}
		     if(bool==0){
		    	 	rez.polinom.add(new Monom(x.get_coef(),x.get_exp()));  } 
		    	}
		return rez;

		}
		


//	for(int i=0; i<pol.polinom.size(); i++)
// 	{
// 		rez.addTerm(pol.polinom.get(i).get_coef(), pol.polinom.get(i).get_exp(	));
// 	}
	
// public Polinom adunare(Polinom pol) {
//	 	Polinom rez=new Polinom(new Monom(0,0));
//	 	for(Monom x:this.polinom)
//	 		for(Monom y:pol.polinom) {
//	 			if(x.get_exp()==y.get_exp())
//	 			{
//	 				Monom monom=new Monom(x.get_coef()+y.get_coef(),x.get_exp());
//	 				rez.Polinom(monom);
//	 			}
//	 			
//	 				
//	 		}
//	 	for(Monom x:pol.polinom)
//	 	{
//	 			
//	 		Monom monom=new Monom(x.get_coef(),x.get_exp());
//	 		if(verifpol(monom)) {
//	 			rez.Polinom(x);
//	 		}
//			
//	 	}
//	 	for(Monom x:this.polinom)
//	 	{	
//	 		Monom monom=new Monom(x.get_coef(),x.get_exp());
//	 		if(verifpol(monom)) {
//	 			rez.Polinom(x);
//	 		}
//			
//	 	}
//	 	
//	 	
//	 	return rez;
// }

public Polinom Derivate(Polinom pol) {
	Polinom rez=new Polinom(new Monom(0,0));
	for(Monom x:this.polinom)
	{
		Monom monom=(new Monom(x.get_coef()*x.get_exp(), x.get_exp()-1));
		rez.Polinom(monom);
	}
	return rez;
}

public Polinom Integrate(Polinom pol) {
	Polinom rez=new Polinom(new Monom(0,0));
	for(Monom x:this.polinom)
	{
		Monom monom=(new Monom(x.get_coef()/(x.get_exp()+1), x.get_exp()+1));
		rez.Polinom(monom);
	}
	return rez;
}

public Polinom Substraction(Polinom pol) {
	Polinom rez=new Polinom(new Monom(0,0));
	rez.polinom.clear();
  int bool=0;
	for(Monom x:this.polinom) {
		bool=0;
		for(Monom y:pol.polinom) {
			if(x.get_exp()==y.get_exp()) {
				if(x.get_coef()<0 || y.get_coef()<0)
				{
					bool=1;
					rez.polinom.add(new Monom(x.get_coef()+y.get_coef(),x.get_exp()));
				}
				else
				{
					bool=1;
					rez.polinom.add(new Monom(x.get_coef()+y.get_coef()*(-1),x.get_exp()));
				}
			}
		}
	}
	for(Monom x:pol.polinom) {
		   bool=0;
	
		for(Monom y:rez.polinom) {
			
		if(x.get_exp()==y.get_exp()) {
				
				bool=1;
			}
		
		}
	     if(bool==0){
	    	 	rez.polinom.add(new Monom(x.get_coef(),x.get_exp()));  } 
	    	}
	for(Monom x:this.polinom) {
		   bool=0;
	
		for(Monom y:rez.polinom) {
			
		if(x.get_exp()==y.get_exp()) {
				
				bool=1;
			}
		
		}
	     if(bool==0){
	    	 	rez.polinom.add(new Monom(x.get_coef(),x.get_exp()));  } 
	    	}
	return rez;

}

public Polinom Multiply(Polinom pol) {
	Polinom rez=new Polinom(new Monom(0,0));
	for(Monom x:this.polinom)
 		for(Monom y:pol.polinom) {
 				Monom monom=new Monom(x.get_coef()*y.get_coef(),x.get_exp()+y.get_exp());
 				rez.Polinom(monom);	
 		}
	return rez;
 }
}
	
