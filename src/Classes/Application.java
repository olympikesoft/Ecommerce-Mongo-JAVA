package Classes;

import java.util.ArrayList;
import java.util.List;

class Application {

	private static Application instance; // singleinstance
	private List<String> readFiles; // Create list of ReadFiles Name

	private Application() {
		readFiles = new ArrayList<String>();
	}

	public static Application instance() {
		if (instance == null) {
			instance = new Application();
		}
		// If file dont exist create instance for file;
		// If exist return instance and say that file exist;

		return instance;
	}

	public List<String> readFiles() {
		return readFiles;
	}

	public void addReadFile(String filename) {
		readFiles.add(filename);
	}
}
