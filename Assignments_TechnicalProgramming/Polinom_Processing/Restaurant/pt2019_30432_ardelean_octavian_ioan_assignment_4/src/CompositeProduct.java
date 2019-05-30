
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements BaseProduct{

	private List<BaseProduct> baseproducts = new ArrayList<BaseProduct>();
	@Override
	public int computeprice(int price) {
		for(BaseProduct baseproduct: baseproducts)
		{
			baseproduct.computeprice(price);
		}
		return price;
		
	}
	public List<BaseProduct> getBaseproducts() {
		return baseproducts;
	}
	public void setBaseproducts(List<BaseProduct> baseproducts) {
		this.baseproducts = baseproducts;
	}
	public void add(BaseProduct baseproduct)
	{
		this.baseproducts.add(baseproduct);
	}
	public void remove(BaseProduct baseproduct)
	{
		this.baseproducts.remove(baseproduct);
	}

}
