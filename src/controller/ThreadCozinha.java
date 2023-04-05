package controller;
import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread{
	
	Semaphore cozinheiro;
	int i;
	int type;
	String name="";
	
	public ThreadCozinha(int i, Semaphore cozinheiro) {
		this.i = i;
		this.cozinheiro = cozinheiro;
	}
	
	@Override
	public void run() {
		type = getType((double) i);
		operacao();
	}
	
	public int getType(double idcalc) {
		if(idcalc % 2 != 0) {
			i = 1;
			name = "Sopa de Cebola";
		}else {
				i = 2;
				name = "Lasanha a Bolonhesa";
		}
		return (int) i;
	}
	
	public void operacao(){
		switch(type) {
		case 1: cozinhar(0.5, 0.8);
			break;
		case 2: cozinhar(0.6, 1.2);
			break;
		}
	}
	
	public void cozinhar(double min, double max) {
		
		System.out.println(name+" teve seu preparo iniciado");
		double time = ((Math.random()*max)+min);
		
		try {
			for(double i=0;i<=time;i+=0.1) {
				System.out.println(name+" Porcentagem do preparo: "+i+" ("+i+")"); //sinceramente n faço ideia de como faz pra isso ser porcentagem '-'
				sleep((long) (0.1*1000));
			}
			System.out.println(name+" Finalizado! Partindo para a entrega!");
			try {
				cozinheiro.acquire();
				sleep((long) (0.5*1000));
				System.out.println(name+" Entregue! ("+i+")");
			} catch (Exception e) {
				
			} finally {
				cozinheiro.release();
			}
		} catch (Exception e) {
			
		}
	}
}
