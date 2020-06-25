package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		
		IArquivosController arqCont = new ArquivosController();
		
		String dirWin = "";
		String path = "C:\\Users\\Raize\\Desktop\\FATEC\\SOI\\Exercicio_Arquivos_Java";
		String nome = "relatorio.txt";
		
		try {
		//	arqCont.readDir(dirWin);
		//	arqCont.createFile(path, nome);
		//	arqCont.readFile(path, nome);
			arqCont.transfCSV(path, nome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
