package app;

import global_storage.CurrentUser;

/**
 * The starting point of the program.
 */
public class App {

    /**
     * The starting point of the program.
     * @param args default input.
     */
    public static void main(String[] args) {
        final CurrentUser currentUser = new CurrentUser();
        final AppCoordinator appCoordinator = AppCoordinator.getInstance();
        appCoordinator.createLoginView();
    }
}
