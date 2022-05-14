package game.util;

import java.io.IOException;

public class DeleteRegister {

	public static void deleteRegister(String fileName, String name) throws IOException {
		String[] registers = ReadFile.read(fileName);
		WriteFile.createFile(fileName);
		for (String reg : registers) {
			if (!(reg.equals(name))) {
				WriteFile.write(fileName, reg);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		deleteRegister("src/data/playersproperties/Elkin", "Estadio Metropolitano");
	}
}
