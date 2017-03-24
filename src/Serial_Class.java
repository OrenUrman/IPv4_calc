import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OrenUrman
 */
public class Serial_Class {
    /**
     * @param file_name - путь в директорию
     * @param list_ip   - список классов, которые нужно сериализовать
     * @throws IOException - в случае ошибки ввода/вывода
     */
    public static final void serial_out(String file_name, List<IP4_calculator> list_ip) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file_name + "\\file_ser.ser", true))) {
            for (IP4_calculator lol : list_ip) {
                os.writeObject(lol);
            }
            os.flush();
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param file_name - директория с файлом
     * @return - возвращает список типа IP4_calculator
     */
    public static final ArrayList<IP4_calculator> serial_in(String file_name) {
        ArrayList<IP4_calculator> IPClass = new ArrayList<>();
        IP4_calculator[] lalka = new IP4_calculator[2];
        try (FileInputStream fs = new FileInputStream(file_name + "\\file_ser.ser")) {
            ObjectInputStream os = new ObjectInputStream(fs);
            while (fs.available() > 0) {
                IP4_calculator lol = (IP4_calculator) os.readObject();
                IPClass.add(lol);
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return IPClass;
    }
}
