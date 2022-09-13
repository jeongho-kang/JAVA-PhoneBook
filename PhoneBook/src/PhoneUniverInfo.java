
public class PhoneUniverInfo extends PhoneInfo {
	String major; // 전공
	int year; // 학년
	
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
