import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private View view;
	private Polinom p1;
	private Polinom p2;
	public Controller(View view, Polinom p1,Polinom p2)
	{
		this.view=view;
		this.p1=p1;
		this.p2=p2;
		this.view.addPol1Listener(new addPol1Listener());
		this.view.addPol2Listener(new addPol2Listener());
		this.view.SumListener(new SumListener());
		this.view.SubListener(new SubListener());
		this.view.MulListener(new MulListener());
		this.view.Derivate1Listener(new Derivate1Listener());
		this.view.Derivate2Listener(new Derivate2Listener());
		this.view.Integrate1Listener(new Integrate1Listener());
		this.view.Integrate2Listener(new Integrate2Listener());
	}
	class addPol1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			p1.Polinom(new Monom(view.getter_monom1coef(),view.getter_monom1power()));
			view.setter_pol1(p1.toString());
		}
	}
class addPol2Listener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		p2.Polinom(new Monom(view.getter_monom2coef(),view.getter_monom2power()));
		view.setter_pol2(p2.toString());
	}
	}
class SumListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		Polinom result;
		result=p1.adunare(p2);
		view.setter_result(result.toString());
		
	}
}
class Derivate1Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Polinom result;
			result=p1.Derivate(p1);
			view.setter_result(result.toString());
			
		}
	}
class Derivate2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Polinom result;
			result=p2.Derivate(p2);
			view.setter_result(result.toString());
				
		}
	}
class Integrate1Listener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		Polinom result;
		result=p1.Integrate(p1);
		view.setter_result(result.toString());
			
	}
}
	class Integrate2Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Polinom result;
			result=p2.Integrate(p2);
			view.setter_result(result.toString());
				
		}
	}
	class SubListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Polinom result;
			result=p1.Substraction(p2);
			view.setter_result(result.toString());
			
		}
	}
		class MulListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				Polinom result;
				result=p1.Multiply(p2);
				view.setter_result(result.toString());
				
			}
	}
		
}