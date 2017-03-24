import java.io.*;

/**
 *  IP-калькулятор, принимает 2 IP адреса и маску подсети.
 *  для первого IP считает все необходимые значения и проверят, входит ли в эту подсеть второй IP
 *  так же производить сериализацию в файл
 *
 *  планируется добавить - десиреализацию + ввод нескольких значений + вывод в текстовый файл.
 *  возможно запись в БД и передача по сети
 *
 *  что еще пофиксить - максимальные маски (31,32) с ними коды получаются не верными. тупо условие добавить
 *
 * @author OrenUrman
 * @version 0.1
 */
public class IP4_calculator implements Serializable {
    private int first_IP;
    private int netmask;
    private int second_IP;
    private int wildcard_mask;
    private int IP_network;
    private int broadcast;
    private long quantity_access_ip;
    private long quantity_work_ip;
    private int address_start;
    private int address_finish;
    private boolean relations = false;

    private String first;
    private String netw;
    private String second;

    private String ser;

    /**
     * Конструктор
     * @param IP1  - первый IP адрес
     * @param net  - маска подсети
     * @param IP2   - второй IP адрес
     * @param file_name - путь к файлу, для сериализации
     * @throws NumberFormatException
     */
    public IP4_calculator(String IP1, String net, String IP2, String file_name) throws NumberFormatException {
        first = IP1;
        netw = net;
        second = IP2;
        first_IP = convert(IP1);
        netmask = convert(net);
        second_IP = convert(IP2);
        wildcard_mask = ~netmask;
        IP_network = first_IP & netmask;
        broadcast = IP_network | wildcard_mask;
        quantity_access_ip = wildcard_mask + 1;
        quantity_work_ip = wildcard_mask - 1;
        address_start = IP_network | 1;
        address_finish = broadcast - 1;
        ser= file_name;


    }

    /**
     *
     * @param ip_address - принимает 4 октета в виде строки
     * @return             - возвращает полученную строку в виде числа (int)
     * @throws NullPointerException     - в случае неверного октета
     */
    private int convert(String ip_address) throws NullPointerException {
        String[] str = ip_address.split("[.]");
        if (str.length != 4)
            throw new NumberFormatException("Invalid IP address: " + ip_address);

        int IP_value = 0;
        int i = 24;
        for (int n = 0; n < str.length; n++) {

            int value = Integer.parseInt(str[n]);

            if (value != (value & 0xff)) {

                throw new NumberFormatException("Invalid IP address: " + ip_address);
            }

            IP_value += value << i;
            i -= 8;
        }
        return IP_value;
    }


    /**
     * метод для вывода значений, хотя он более для самопроверки.
     * вызывает метод serial_out();
     */
    public void print() {
        System.out.println("IP address (first) - " + first + " in binary " + Integer.toBinaryString(first_IP));
        System.out.println("NETMASK - " + netw + " in binary " + Integer.toBinaryString(netmask));
        System.out.println("IP address (second) - " + second + " in binary " + Integer.toBinaryString(second_IP));
        System.out.println("wildcard_mask in binary " + Integer.toBinaryString(wildcard_mask));
        System.out.println("IP-address network - " + Integer.toBinaryString(IP_network));
        System.out.println("Broadcast-adress - " + Integer.toBinaryString(broadcast));
        System.out.println("Number of available addresses " + quantity_access_ip);
        System.out.println("Number of work addresses " + quantity_work_ip);
        System.out.println("IP address of the first host " + Integer.toBinaryString(address_start));
        System.out.println("IP address of the last host " + Integer.toBinaryString(address_finish));
        if (second_IP >= IP_network && second_IP <= broadcast) {
            System.out.println("IP addresses are in the same range");
            relations = true;
        }
        else
            System.out.println("IP addresses are in the different range");
    }


}
