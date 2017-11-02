package Classes;

import java.io.File;
import java.util.TimerTask;

//User timertask to run the thread;
public class LogsDirListener extends TimerTask {
	String logsFilesPath;

	LogsDirListener() {

		// Path of Files;
		logsFilesPath = "C:\\Invoices";

	}

	// Run thread;
	@Override
	public void run() {

		File[] filesOnDir = getFiles(); // list of files C:\Clients

		for (File file : filesOnDir) { // Foreach file from filesOnDirectory
			String fileName = file.getName();
			if (Application.instance().readFiles().contains(fileName)) // If
				// instance
				// application
				// have
				// the
				// same
				// name
				// of
				// list
				// of
				// file
				// of
				// directory
				// simpletask
				continue;

			MyFileReader reader = new MyFileReader(fileName);
			reader.start(); // Start reading thread;
			System.out.println("Reading a file \n" + fileName);
			Application.instance().addReadFile(fileName); // Add file of reader;
		}
	}

	private File[] getFiles() { // Return array of files;
		File folder = new File(logsFilesPath);
		File[] listOfFiles = folder.listFiles();

		return listOfFiles;
	}
}
