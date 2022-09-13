
public class MenuChoiceException extends Exception { // 에외처리
	int wrongChoice; // 선택을 잘못하였을때
	
	public MenuChoiceException(int choice) {
		super("잘못된 선택이 이루어졌습니다.");
		wrongChoice=choice;
	}
	public void showWrongChoice() {
		System.out.println(wrongChoice+"에 해당하는 선택은 존재하지 않습니다.");
	}
}
