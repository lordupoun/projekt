package projektpack;

public class Hodnoceni {
	private int body; 
	private String slovniHodnoceni; 
	Hodnoceni(int body, String slovniHodnoceni)
	{
		this.body=body;
		this.slovniHodnoceni=slovniHodnoceni;
	}
	public int getBody()
	{
		return body;
	}
	public String getSlovniHodnoceni()
	{
		return slovniHodnoceni;
	}

	
}
