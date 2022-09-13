import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PhoneBookManager {
	Scanner in = new Scanner(System.in); //�Է��� ���� scanner ��ü ����
	private final File dataFile=new File("PhoneBook.dat"); // ���� ������� ���� ���� ����
	HashSet<PhoneInfo> infoStorage = new HashSet<PhoneInfo>();
	
	static PhoneBookManager inst = null;
	public static PhoneBookManager createManagerInst()
	{
		if(inst==null)
			inst = new PhoneBookManager();
		return inst;
	}
	private PhoneBookManager(){
		readFromFile();
	}
	private PhoneInfo readFriendInfo() { //�Ϲ�ģ�� ����ó �Է�
		System.out.print("�̸��� �Է��ϼ��� :");
		String name =Menuviewer.in.next();
		System.out.print("��ȭ��ȣ�� �з��ϼ��� : ");
		String phone = Menuviewer.in.next();
		return new PhoneInfo(name,phone);
	}
	private PhoneInfo readUniverFriendInfo() { // ����ģ�� ����ó�� �Է�
		System.out.print("�̸��� �Է��ϼ��� :");
		String name = Menuviewer.in.next();
		System.out.print("��ȭ��ȣ�� �Է��ϼ��� :");
		String phone = Menuviewer.in.next();
		System.out.print("������ �Է��ϼ��� :");
		String major = Menuviewer.in.next();
		System.out.print("�г��� �Է��ϼ��� :");
		int year = Menuviewer.in.nextInt();
		Menuviewer.in.nextLine();
		return new PhoneUniverInfo(name,phone,major,year);
	}
	private PhoneInfo readCompanyFriendInfo() { // ȸ������ ����ó �Է�
		System.out.print("�̸��� �Է��ϼ��� :");
		String name = Menuviewer.in.next();
		System.out.print("��ȭ��ȣ�� �Է��ϼ��� :");
		String phone = Menuviewer.in.next();
		System.out.print("�ٴϴ� ȸ�縦 �Է��ϼ��� :");
		String company = Menuviewer.in.next();
		return new PhoneCompanyInfo(name, phone, company);
	}
	public void inputData() throws MenuChoiceException { 
		System.out.println("��ȭ��ȣ�� �Է��� �����մϴ�.");
		System.out.println("1. �Ϲ� 2. ���� 3.ȸ�� :");
		System.out.print("���� >> :");
		int choice = Menuviewer.in.nextInt();
		Menuviewer.in.next();
		PhoneInfo info = null;
		
		if(choice<INPUT_SELECT.NORMAL || choice>INPUT_SELECT.COMPANY)
			throw new MenuChoiceException(choice);
		
		switch (choice) {
		case INPUT_SELECT.NORMAL : // 1���� ������ �Ϲݻ������ ��ȭ��ȣ�� �Է��ϰ�
			info = readFriendInfo();
		break;
		case INPUT_SELECT.UNIV :  // 2���� ������ ���л������ ��ȭ��ȣ�� �Է��ϰ�
			info = readUniverFriendInfo(); 
		break;
		case INPUT_SELECT.COMPANY :  // 3���� ������ ȸ�������� ��ȭ��ȣ�� �Է��Ѵ�.
			info = readCompanyFriendInfo(); 
		break;
		
		}
		boolean isAdded=infoStorage.add(info);
		if(isAdded ==true) //��ȭ��ȣ �Է¿� �ƹ��� �̻��� ���� ��
		System.out.println("��ȭ��ȣ�� �Է��� �Ϸ�Ǿ����ϴ�. \n");
		else // �̹� �ִ� ��ȭ��ȣ �ϋ�
			System.out.println("�̹� �Է��� �Ϸ�� ��ȭ��ȣ �Դϴ�. \n");
	}
	public void searchData() {
		System.out.println("��ȭ��ȣ�� �˻��� �����մϴ�.");
		System.out.print("�̸��� �Է��ϼ��� : ");
		String name = Menuviewer.in.next();
		
		PhoneInfo info=search(name);
		if(info==null) // �˻��ϴ� �̸��� �������� ���� ��
		{
			System.out.println("�ش��ϴ� �̸��� �������� �ʽ��ϴ�. \n");
		}
		else { // �˻��ϴ� �̸��� ���� �� ��
			info.showPhoneInfo();
			System.out.println("�ش��ϴ� �̸��˻��� �Ϸ�Ǿ����ϴ�. \n");
		}
	}
	
	public void deleteDate() {
		System.out.println("��ȭ��ȣ�� ������ ������ �����մϴ�.");
		System.out.print("�̸��� �Է��ϼ��� : ");
		String name = Menuviewer.in.next();
		
		Iterator<PhoneInfo> itr=infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo=itr.next(); 
			if(name.compareTo(curInfo.name)==0)
			{    // �˻��ϴ� �̸��� �����Ѱ� ������ �� �����ȴ�.
				itr.remove();
				System.out.println("��ȭ��ȣ�� ������ �Ϸ�Ǿ����ϴ�.");
				return;
			}
		} 
		System.out.println("�ش��ϴ� ��ȭ��ȣ�� �����Ͱ� �������� �ʽ��ϴ�.");
	}
	private PhoneInfo search(String name) {
		
		Iterator<PhoneInfo> itr= infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo=itr.next();
			if(name.compareTo(curInfo.name)==0)
				return curInfo;
		}
		return null;
	}
	public void storeToFile() {  // ���� ������� ���� �ʿ��� �޼���
		try {
			FileOutputStream file = new FileOutputStream(dataFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			Iterator<PhoneInfo> itr=infoStorage.iterator();
			while(itr.hasNext())
				out.writeObject(itr.next());
			
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void readFromFile() {  // ������ �ҷ��´�.
		if(dataFile.exists()==false)
			return;
		try {
			FileInputStream file = new FileInputStream(dataFile);
			ObjectInputStream in = new ObjectInputStream(file);
			
			while(true) {
				PhoneInfo info = (PhoneInfo)in.readObject();
				if(info==null)
					break;
				infoStorage.add(info);
			}
			in.close();
		}
		catch(IOException e) {
			return;
		}
		catch(ClassNotFoundException e) {
			return;
		}
	}
}
	
	

