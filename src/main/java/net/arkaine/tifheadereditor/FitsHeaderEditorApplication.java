package net.arkaine.tifheadereditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FitsHeaderEditorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FitsHeaderEditorApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Fits f = new Fits("bigimg.fits");
        BasicHDU img = f.getHDU(0);
        if (img.getData().reset()) {
            int[] line = new int[100000];
            long sum   = 0;
            long count = 0;
            ArrayDataInput in = f.getStream();
            while (in.readLArray(line) == line.length * 4) {  // int is 4 bytes
                for (int i=0; i<line.length; i += 1) {
                    sum += line[i];
                    count++;
                }
            }
            double avg = ((double) sum)/count;
        } else {
            System.err.println("Unable to seek to data”);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}