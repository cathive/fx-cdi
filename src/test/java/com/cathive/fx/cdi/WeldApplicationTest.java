package com.cathive.fx.cdi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Very basic unit test that checks if the application can start up and close correctly.
 * given a basic AnchorPane fxml on classpath
 *
 * @author Alexander Erben (a_erben@outlook.com)
 */
public class WeldApplicationTest extends WeldApplication {

    private static Stage uut;

    /**
     * Will track the setup of the fx application.
     * <p>
     * <p>
     * First acquired on test startup
     * first released on UI creation.
     * <p>
     * Second acquire on test run
     * Second release on test finish.
     * <p>
     * Third acquire on fx HUI hide.
     * Third release on leaving the fx app thread.
     */
    private static Semaphore canExecuteTest = new Semaphore(1);

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    @FXMLLoaderParams()
    private FXMLLoader fxmlLoader;

    @Test
    public void testInit() throws Exception {
        canExecuteTest.acquire();

        launch();

        try {
            canExecuteTest.acquire();
            logger.log(Level.ALL, "Executing test for method testInit");
            assertThat(uut.getHeight(), is(129.0));
            assertThat(uut.getWidth(), is(102.0));
            logger.log(Level.ALL, "Finished executing test for method testInit");
        } catch (InterruptedException e) {
            Assert.fail("Could not execute testInit ");
        } finally {
            canExecuteTest.release();  // wo wouldn't want to leave any semaphores open, would we?
        }
    }

    @Override
    public void start(Stage stage) {
        URL resource = WeldApplicationTest.class.getResource("weldApplicationTest.fxml");
        try {
            AnchorPane pane = FXMLLoader.load(resource);
            stage.setScene(new Scene(pane));
            uut = stage;
            uut.show();
            canExecuteTest.release(); // test execution can now resume
            canExecuteTest.acquire(); // wait for the current test to finish
            uut.hide();
        } catch (IOException | InterruptedException e) {
            Assert.fail("Could not initialize FX application");
        } finally {
            canExecuteTest.release(); // wo wouldn't want to leave any semaphores open, would we?
        }
    }
}