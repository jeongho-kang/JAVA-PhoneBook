import java.io.Serializable;

public class PhoneInfo implements Serializable {
	String name; // �̸�
	String PhoneNumber; // ��ȭ��ȣ
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
	