
public class PhoneCompanyInfo extends PhoneInfo { // »ó¼Ó
	String company;
	
	public PhoneCompanyInfo(String name, String num, String company) {
		super(name,num);
		this.company = company;
	}
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("company : " + company);
	}
}
