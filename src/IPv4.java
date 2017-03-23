/**
 * Created by Пользователь on 23.03.2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Пользователь on 22.03.2017.
 */
public class IPv4 {
    public static void main(String[] args) {

        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter first IP");
            String first = buf.readLine();
            System.out.println("Enter netmask");
            String net = buf.readLine();
            System.out.println("Enter second IP");
            String second = buf.readLine();
            System.out.println("Enter file name");
            String file_name = buf.readLine();
            IP4_calculator lol = new IP4_calculator(first, net, second, file_name);
            lol.print();

        } catch (IOException ex) {

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
}