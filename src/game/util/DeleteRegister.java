package game.util;

import java.io.IOException;

public class DeleteRegister {

	public static String deleteRegister(String fileName, String index) throws IOException {
		String[] registers = ReadFile.read(fileName);
		WriteFile.createFile(fileName);
		int i = 1;
		String quitar = "";
		for (String reg : registers) {
			String regindex = reg.split(",")[0];
			if (!(regindex.equals(index))) {
				WriteFile.write(fileName, reg.split(",")[1], i);
				i++;
			} else {
				quitar = reg.split(",")[1];
			}
		}
		return quitar;
	}

	public static void main(String[] args) throws IOException {
		deleteRegister("src/data/playersproperties/Elkin", "Estadio Metropolitano");
	}
}
