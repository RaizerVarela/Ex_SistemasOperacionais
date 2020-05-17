package controller;

import java.util.concurrent.Semaphore;

public class Processos extends Thread {

	private int id;
	private Semaphore sem;
	private static int posicao;

	public Processos(int id, Semaphore sem) {
		this.id = id;
		this.sem = sem;
	}

	@Override
	public void run() {
		try {
			sem.acquire();
			processando();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sem.release();
			processado();
		}
	}

	private void processando() {
		int tempo = (int) ((Math.random() * 120000) + 4000);
		int seg = (int)(tempo / 1000);
		try {
			System.out.println("Processo #" + id 
					+ "\nStatus: Processando ...\nTempo para termino: " 
					+ seg + " segundos");
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processado() {
		posicao++;
		System.out.println("\nProcesso #" + id + " foi o " + posicao + "° a ser concluido.\n");
	}
}
