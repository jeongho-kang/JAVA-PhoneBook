
public class MenuChoiceException extends Exception { // ����ó��
	int wrongChoice; // ������ �߸��Ͽ�����
	
	public MenuChoiceException(int choice) {
		super("�߸��� ������ �̷�������ϴ�.");
		wrongChoice=choice;
	}
	public void showWrongChoice() {
		System.out.println(wrongChoice+"�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}
}
