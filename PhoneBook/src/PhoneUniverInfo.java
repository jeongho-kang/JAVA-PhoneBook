
public class PhoneUniverInfo extends PhoneInfo {
	String major; // ����
	int year; // �г�
	
	public PhoneUniverInfo(String name, String num, String major, int year) {
		super(name,num);
		this.major = major;
		this.year = year;
	}
	public void ShowPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major : " + major);
		System.out.println("year : " + year);
	}
}
