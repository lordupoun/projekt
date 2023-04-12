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
	/*public void setBody(int body)
	{
		this.body=body;
	}
	public void setSlovniHodnoceni(String slovniHodnoceni)
	{
		this.slovniHodnoceni=slovniHodnoceni;
	}*/
	
}
