
public class PhoneBookMain {

	public static void main(String[] args) {
		PhoneBookManager manager=PhoneBookManager.createManagerInst();
		int choice;
		
		while(true) {
			try {
			Menuviewer.showMenu();
			choice=Menuviewer.in.nextInt();
			Menuviewer.in.next();
			
			if(choice<INIT_MENU.INPUT || choice>INIT_MENU.EXIT)
				throw new MenuChoiceException(choice);
			
			switch(choice) {
			case INIT_MENU.INPUT:  // 인터페이스를 이용
				manager.inputData();
				break;
			case INIT_MENU.SEARCH: // 인터페이스를 이용
				manager.searchData();
				break;
			case INIT_MENU.DELETE: // 인터페이스를 이용
				manager.deleteDate();
				break;
			case INIT_MENU.EXIT : // 인터페이스를 이용
				System.out.println("프로그램을 종료합니다.");
				return;
				}
			}
			catch(MenuChoiceException e)   // 예외처리
			{
				e.showWrongChoice();
				System.out.println("메뉴 선택을 처음부터 다시 진행합니다.\n");
			}
		}
	}

}
