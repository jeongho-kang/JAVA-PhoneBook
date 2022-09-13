import java.util.Scanner;

public class Menuviewer {  // 메뉴를 보여준다
	public static Scanner in = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("선택하세요 : " );
		System.out.println("1. 연락처 등록 " );
		System.out.println("2. 연락처 검색 " );
		System.out.println("3. 연락처 삭제" );
		System.out.println("4.프로그램 종료" );
		System.out.print("선택: ");
	}
}
