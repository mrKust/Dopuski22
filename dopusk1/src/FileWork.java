import java.io.*;
import java.util.LinkedList;

/**
 * Данный класс является вспомогательным и осуществляет запись полученных значений в txt файл
 */
public class FileWork {
    protected String nameOfFile;
    PrintWriter writer;
    FileReader reader;

    public FileWork(String fileName1, boolean add) {
        this.nameOfFile = fileName1;
        try {
            reader = new FileReader(nameOfFile);
            FileWriter writer1 = new FileWriter(nameOfFile, add);
            writer = new PrintWriter(writer1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        writer.print(text);
        writer.flush();
    }

    public void write(int one, double two) throws IOException {
        writer.print(one);
        writer.write(" ");
        writer.println(two);
        writer.flush();
    }

    public void write(int one, double two, double three) throws IOException {
        writer.print(one);
        writer.write(" ");
        writer.print(two);
        writer.write(" ");
        writer.println(three);
        writer.flush();
    }

    public void write(float one, double two) throws IOException {
        writer.print(one);
        writer.write(" ");
        writer.println(two);
        writer.flush();
    }

    public void write(float one, double two, double three) {
        writer.print(one);
        writer.write(" ");
        writer.print(two);
        writer.write(" ");
        writer.println(three);
        writer.flush();
    }

    public void write(float one, double two, double three, double four) {
        writer.print(one);
        writer.write(" ");
        writer.print(two);
        writer.write(" ");
        writer.print(three);
        writer.write(" ");
        writer.println(four);
        writer.flush();
    }

    public void write(float one, double two, double three, double four, double five) {
        writer.print(one);
        writer.write(" ");
        writer.print(two);
        writer.write(" ");
        writer.print(three);
        writer.write(" ");
        writer.print(four);
        writer.write(" ");
        writer.println(five);
        writer.flush();
    }

    public void write(float one, double two, double three, double four, double five, double six) {
        writer.print(one);
        writer.write(" ");
        writer.print(two);
        writer.write(" ");
        writer.print(three);
        writer.write(" ");
        writer.print(four);
        writer.write(" ");
        writer.print(five);
        writer.write(" ");
        writer.println(six);
        writer.flush();
    }

    public void writeInColumns(String[] text) {
        String[] tmp = text[0].split(" ");
        System.out.println(tmp.length);
        String[][] result = new String[text.length][tmp.length];
        for (int i = 0; i < text.length; i++) {
            result[i] = text[i].split(" ");
        }
        int k = -150;
        for (int i = 0; i < tmp.length - 1; i++) {
            writer.print(k + 1);
            writer.write(" ");
            for (int j = 0; j < result.length; j++) {
                writer.print(result[j][i]);
                writer.write(" ");
            }
            k++;
            writer.write("\n");
        }
        writer.flush();
        System.out.println(text.length);
    }

    public String read() throws IOException {
        StringBuilder helper = new StringBuilder();
        int symbol;
        while((symbol = reader.read()) != -1) {
            helper.append((char)symbol);
        }
        String result = new String(helper);
        return result;
    }
}

