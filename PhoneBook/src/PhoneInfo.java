import java.io.Serializable;

public class PhoneInfo implements Serializable {
	String name; // 이름
	String PhoneNumber; // 전화번호
	public PhoneInfo(String name , String num) {  
		this.name = name;
		this.PhoneNumber = num;
	}
	public void showPhoneInfo() {
		System.out.println("name : " + name);
		System.out.println("Phone : " + PhoneNumber);
	}
	public int hashCode() {
		return name.hashCode();
	}
	public boolean equals(Object obj) {
		PhoneInfo cmp = (PhoneInfo)obj;
		if(name.compareTo(cmp.name)==0)
			return true;
		else
			return false;
	}
}
	