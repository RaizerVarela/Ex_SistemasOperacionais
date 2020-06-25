package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	public ArquivosController() {
		super();
	}

	@Override
	public void readDir(String path) throws IOException {
		// TODO Auto-generated method stub
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File f : files) {
				if (f.isFile()) {
					System.out.println("     \t" + f.getName());
				} else {
					System.out.println("<DIR>\t" + f.getName());
				}
			}
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	@Override
	public void createFile(String path, String nome) throws IOException {
		File dir = new File(path);
		File arq = new File(path, nome + "csv"); // + ".txt"
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			String conteudo = geraTxt();
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório Inválido");
		}
	}

	private String geraTxt() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while (!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "Digite uma frase:", "Entrada de texto",
					JOptionPane.INFORMATION_MESSAGE);
			if (!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + "\n");
			}
		}
		return buffer.toString();
	}

	private void createFileCsv(String path, String nome) throws IOException {
		File dir = new File(path);
		File arq = new File(path, nome + ".csv"); // + ".txt"
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			String conteudo = copiaTxt(path, nome);
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório Inválido");
		}
	}

	private String copiaTxt(String path, String nome) throws IOException {
		StringBuffer buf = new StringBuffer();
		File arq = new File(path, nome + ".txt");
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while (linha != null) { // procurando EOF
			linha = buffer.readLine();
			buf.append(linha + "\n");
		}
		buffer.close();
		return buf.toString();
	}

	@Override
	public void transfCSV(String path, String nome) throws IOException {
		File arq = new File(path, nome);
		if (arq.exists() && arq.isFile()) {
			char aux[] = nome.toCharArray();
			nome = "";
			for (int i = 0; i < (aux.length - 4); i++) {
				nome += aux[i];
			}
			createFileCsv(path, nome); // passando arquivo para ser gerado como csv
		} else {
			throw new IOException("Arquivo Inválido");
		}

	}

	@Override
	public void readFile(String path, String nome) throws IOException {
		File arq = new File(path, nome);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();

			while (linha != null) { // procurando EOF
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Inválido");
		}

	}

	@Override
	public void openFile(String path, String nome) throws IOException {
		File arq = new File(path, nome);
		if (arq.exists() && arq.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(arq);
		} else {
			throw new IOException("Arquivo Inválido");
		}

	}
}
