package view;
import java.util.concurrent.Semaphore;
import controller.ThreadCozinha;

public class Main {
	static Semaphore cozinheiro;
	public static void main(String[] args) {
		cozinheiro = new Semaphore(1);
		for(int i=1;i<=5;i++) {
			Thread cozinha = new ThreadCozinha(i, cozinheiro);
			cozinha.start();
		}
	}
}
