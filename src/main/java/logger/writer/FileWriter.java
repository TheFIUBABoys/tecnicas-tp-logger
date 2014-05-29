package logger.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * @author Tomas
 *         Creates FileWriter that implements Writer to write to an outputFile.
 */
public class FileWriter implements Writer {

    private BufferedWriter bufferedWriter;

    /**
     * Creates a new instance of FileWriter
     *
     * @param outputFile the output filename.
     * @throws IOException if an error occurred when creating new FileWriter.
     */
    public FileWriter(String outputFile) throws IOException {
        File file = new File(outputFile);

        if (!file.exists()) {
            file.createNewFile();
        }

        java.io.FileWriter fw = new java.io.FileWriter(file, true);
        bufferedWriter = new BufferedWriter(fw);
    }

    /**
     * {@inheritDoc}
     */
    public void write(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
