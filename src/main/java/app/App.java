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
        CurrentUser currentUser = new CurrentUser();
        currentUser.initialize();
        final AppCoordinator appCoordinator = AppCoordinator.getInstance();
        appCoordinator.createLoginView();
    }
}
