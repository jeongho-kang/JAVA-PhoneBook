
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
			case INIT_MENU.INPUT:  // �������̽��� �̿�
				manager.inputData();
				break;
			case INIT_MENU.SEARCH: // �������̽��� �̿�
				manager.searchData();
				break;
			case INIT_MENU.DELETE: // �������̽��� �̿�
				manager.deleteDate();
				break;
			case INIT_MENU.EXIT : // �������̽��� �̿�
				System.out.println("���α׷��� �����մϴ�.");
				return;
				}
			}
			catch(MenuChoiceException e)   // ����ó��
			{
				e.showWrongChoice();
				System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�.\n");
			}
		}
	}

}
