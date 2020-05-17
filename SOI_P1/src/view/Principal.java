package view;

import java.util.concurrent.Semaphore;

import controller.Processos;

public class Principal {
	public static void main(String[] args) {
		
		int  permissoes = 4;
		Semaphore sem = new Semaphore(permissoes);
		
		for(int id=1; id <= 20; id++) {
			Thread processo = new Processos(id, sem);
			processo.start();
		}
	}
}
