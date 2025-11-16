import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        OutputStreamWriter writer = new OutputStreamWriter(System.out);

        int ch;

        while ((ch = reader.read()) != -1) {
            writer.write(Character.toLowerCase(ch));
        }

        writer.flush();

        reader.close();
        writer.close();
        sc.close();
    }
}