import java.io.*;
import java.util.*;

public class LerArquivo {


	public static void main(String[] args){

		String path = "c://temp//in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader)){

			while (line != null){
				System.out.println(line);
				line = br.readLine();
			}

		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}



	public void escreverArquivo(){

		String[] lines = new String[] {"Good Morning", "Good afternoon", "Good night"};

		String path = "c:\\temp\\out.txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			// sobreescreve o arquivo
			// para append: new FileWriter(path, true)
			for (String line : lines){
				bw.write(line);
				bw.newLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}



	public void directorySubdirectory(){

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a folder path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		File[] folders = path.listFiles( /* filtrar coisas aqui com funcao */ 
			File::isDirectory
		);

		File[] files = path.listFiles(File::isFile);


		boolean success = new File(strPath + "\\subdir").mkdir();


		sc.close();
	}


	public void getFilePath(){

		Scanner sc = new Scanner(System.in);
		sout("Enter a file path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		sout("getName: " + path.getName()); // so nome do arquivo
		sout("getParent: " + path.getParent()); // so o diretorio
		sout("getPath: " + path.getPath()); // path todo

	}


}