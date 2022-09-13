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
	Scanner in = new Scanner(System.in); //입력을 위한 scanner 객체 생성
	private final File dataFile=new File("PhoneBook.dat"); // 파일 입출력을 위한 파일 생성
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
	private PhoneInfo readFriendInfo() { //일반친구 연락처 입력
		System.out.print("이름을 입력하세요 :");
		String name =Menuviewer.in.next();
		System.out.print("전화번호를 압력하세요 : ");
		String phone = Menuviewer.in.next();
		return new PhoneInfo(name,phone);
	}
	private PhoneInfo readUniverFriendInfo() { // 대학친구 연락처를 입력
		System.out.print("이름을 입력하세요 :");
		String name = Menuviewer.in.next();
		System.out.print("전화번호를 입력하세요 :");
		String phone = Menuviewer.in.next();
		System.out.print("전공을 입력하세요 :");
		String major = Menuviewer.in.next();
		System.out.print("학년을 입력하세요 :");
		int year = Menuviewer.in.nextInt();
		Menuviewer.in.nextLine();
		return new PhoneUniverInfo(name,phone,major,year);
	}
	private PhoneInfo readCompanyFriendInfo() { // 회사사람들 연락처 입력
		System.out.print("이름을 입력하세요 :");
		String name = Menuviewer.in.next();
		System.out.print("전화번호를 입력하세요 :");
		String phone = Menuviewer.in.next();
		System.out.print("다니는 회사를 입력하세요 :");
		String company = Menuviewer.in.next();
		return new PhoneCompanyInfo(name, phone, company);
	}
	public void inputData() throws MenuChoiceException { 
		System.out.println("전화번호부 입력을 시작합니다.");
		System.out.println("1. 일반 2. 대학 3.회사 :");
		System.out.print("선택 >> :");
		int choice = Menuviewer.in.nextInt();
		Menuviewer.in.next();
		PhoneInfo info = null;
		
		if(choice<INPUT_SELECT.NORMAL || choice>INPUT_SELECT.COMPANY)
			throw new MenuChoiceException(choice);
		
		switch (choice) {
		case INPUT_SELECT.NORMAL : // 1번을 누르면 일반사람들의 전화번호를 입력하고
			info = readFriendInfo();
		break;
		case INPUT_SELECT.UNIV :  // 2번을 누르면 대학사람들의 전화번호를 입력하고
			info = readUniverFriendInfo(); 
		break;
		case INPUT_SELECT.COMPANY :  // 3번을 누르면 회사사람들의 전화번호를 입력한다.
			info = readCompanyFriendInfo(); 
		break;
		
		}
		boolean isAdded=infoStorage.add(info);
		if(isAdded ==true) //전화번호 입력에 아무런 이상이 없을 떄
		System.out.println("전화번호부 입력이 완료되었습니다. \n");
		else // 이미 있는 전화번호 일떄
			System.out.println("이미 입력이 완료된 전화번호 입니다. \n");
	}
	public void searchData() {
		System.out.println("전화번호부 검색을 시작합니다.");
		System.out.print("이름을 입력하세요 : ");
		String name = Menuviewer.in.next();
		
		PhoneInfo info=search(name);
		if(info==null) // 검색하는 이름이 존재하지 않을 떄
		{
			System.out.println("해당하는 이름이 존재하지 않습니다. \n");
		}
		else { // 검색하는 이름이 존재 할 때
			info.showPhoneInfo();
			System.out.println("해당하는 이름검색이 완료되었습니다. \n");
		}
	}
	
	public void deleteDate() {
		System.out.println("전화번호부 데이터 삭제를 시작합니다.");
		System.out.print("이름을 입력하세요 : ");
		String name = Menuviewer.in.next();
		
		Iterator<PhoneInfo> itr=infoStorage.iterator();
		while(itr.hasNext()) {
			PhoneInfo curInfo=itr.next(); 
			if(name.compareTo(curInfo.name)==0)
			{    // 검색하는 이름과 동일한게 존재할 떄 삭제된다.
				itr.remove();
				System.out.println("전화번호부 삭제가 완료되었습니다.");
				return;
			}
		} 
		System.out.println("해당하는 전화번호부 데이터가 존재하지 않습니다.");
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
	public void storeToFile() {  // 파일 입출력을 위해 필요한 메서드
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
	public void readFromFile() {  // 파일을 불러온다.
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
	
	

