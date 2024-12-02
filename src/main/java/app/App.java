package app;

import global_storage.CurrentUser;

/**
 * The starting point of the program.
 */
public class App {

    /**
     * Call to start the app.
     */
    public static void main() {
        final CurrentUser currentUser = new CurrentUser();
        final AppCoordinator appCoordinator = new AppCoordinator();
        appCoordinator.createLoginView();
    }
}
