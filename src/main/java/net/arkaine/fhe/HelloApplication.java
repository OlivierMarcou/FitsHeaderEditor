package net.arkaine.fhe;

import javafx.application.Application;
import javafx.stage.Stage;
import nom.tam.fits.*;
import nom.tam.util.BufferedFile;
import nom.tam.util.FitsFile;
import nom.tam.util.SafeClose;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {

    private static String pathAndPrefixFileName = "D:\\siril\\pp_ZTF 02 13 2023_";

    private static String pathSource = "H:\\CapObj\\2023-02-12_22_32_36Z\\";
    @Override
    public void start(Stage stage) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
*/


        File fObj = new File(pathSource);

        Map<String, String> namesDates = new HashMap<>();
        if (fObj.exists() && fObj.isDirectory()) {
            for (File f : fObj.listFiles()) {
                if (f.exists() && f.isFile() && f.getName().endsWith(".PNG"))
                    getCreationDate(f, namesDates);

            }
        }

        for (String key : namesDates.keySet()
        ) {

            Fits f = null;
            FitsFile bf = null;
            try {
                f = new Fits(pathAndPrefixFileName + key + ".fit");
                bf = new FitsFile(pathAndPrefixFileName + key + ".fit", "rw");
                Header h = null;
                h = f.getHDU(0).getHeader();

                h.addValue("DATE-OBS", namesDates.get(key), "end of observation");
                h.write(bf);

            } catch (FitsException e) {
                e.getMessage();
                continue;
            } finally {
                SafeClose.close(bf);
                SafeClose.close(f);
            }
        }

    }

    private static final String defaultNumberSize = "00000";
    private int counter = 1;

    private String getCreationDate(File f, Map<String, String> namesDates) throws IOException {

        Path file = Paths.get(f.getPath());

        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        ThreadLocal<SimpleDateFormat> formatter = ThreadLocal
                .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String counterString = String.valueOf(counter);
        String endFileName = (defaultNumberSize + counterString).substring(counterString.length(), defaultNumberSize.length() + counterString.length());
        String newDate = formatter.get().format(attr.creationTime().toMillis());
        namesDates.put(endFileName, newDate.replaceAll(" ", "T"));
        counter++;
        return newDate;
    }

    public static void main(String[] args) {
        launch();
    }
}