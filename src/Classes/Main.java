package Classes;

import java.util.Timer;

public class Main {

	public static void main(String[] args) {

		try {
			System.err.println("Starting program!");
			// db.loadInitialDatabase();
			Timer timer = new Timer();

			LogsDirListener task = new LogsDirListener();
			timer.schedule(task, 3000);
			// task.run();

		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println(ae);
		} catch (IndexOutOfBoundsException ee) {
			System.out.println(ee);
		} catch (RuntimeException re) {
			System.out.println(re);
		} catch (Exception e) {
			System.out.println(e);
		} catch (Throwable t) {
			System.out.println(t);
		}

	}
}
