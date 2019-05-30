
public class Main {
	public static void main(String[] args)
	{
	 View guiobj = new View();
	 guiobj.grapInterf();
     Polinom p1=new Polinom(new Monom(0,0));
	 Polinom p2=new Polinom(new Monom(0,0));
	 Controller control=new Controller(guiobj,p1,p2);
	
	
	}
}
