/**
 * OrenUrman
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class IPv4 {
    public static void main(String[] args) {
        System.out.println("Make a choice: 1 - enter data for calculator, 2 - load data ");
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))) {
            String s = buf.readLine();
            if ("1".equals(s)) {
                create();
            } else if ("2".equals(s)) {
                load();

            } else {
                System.out.println("Invalid value");
                System.exit(0);
            }
        } catch (IOException ex) {

        }

    }


    private static void create() {
        List<IP4_calculator> list_ip = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
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
                list_ip.add(lol);
                System.out.println("Continue typing (y/n)");
                if ("y".equals(buf.readLine()))
                    continue;
                else {
                    Serial_Class.serial_out(file_name, list_ip);
                    break;
                }
            }

        } catch (IOException ex) {

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private static void load() {
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter dir of file");
            String str = buf.readLine();
            ArrayList<IP4_calculator> list_IP = Serial_Class.serial_in(str);
            System.out.println(list_IP.size());
            for (IP4_calculator calc : list_IP) {
                calc.print();
            }
        } catch (Throwable ex) {

        }

    }


}