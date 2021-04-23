package TCP_Example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class QLSV_server {
    static ArrayList<sinhvien> listSV = new ArrayList<>();

    public static void ReadFile() throws IOException {
        File f = new File("/Users/barttran/Desktop/src/TCP_Example/sinhvien.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result;
            result = line.split("\\$");

            sinhvien sv = new sinhvien();
            sv.setId(result[0]);
            sv.setName(result[1]);
            sv.setDate(result[2]);
            sv.setAdd(result[3]);
            sv.setScore(result[4]);

            listSV.add(sv);
        }
        fr.close();
        br.close();
    }

    public static void WriteFile() throws IOException {
        File f = new File("/Users/barttran/Desktop/src/TCP_Example/sinhvien.txt");
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < listSV.size(); i++) {
            if (i < listSV.size() - 1) {
                fw.write(listSV.get(i).toString());
                fw.write("\n");
            } else fw.write(listSV.get(i).toString());
        }
        fw.close();
    }

    public static String showALL() {
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            result += listSV.get(i).toShow() + "\n";
        }
        return result;
    }

    public static String showHB() {
        String result = "";
        for (int i = 0; i < listSV.size(); i++) {
            if (Float.parseFloat(listSV.get(i).getScore()) >= 8)
                result += listSV.get(i).toShow() + "\n";
        }
        return result;
    }

    public static void AddSV(DataInputStream dis) throws IOException {

        sinhvien sv = new sinhvien();
        sv.setId(dis.readUTF());
        sv.setName(dis.readUTF());
        sv.setDate(dis.readUTF());
        sv.setAdd(dis.readUTF());
        sv.setScore(dis.readUTF());

        listSV.add(sv);
        WriteFile();

    }

    public static void suaDiem(String id, String score) throws IOException {
        for (int i = 0; i < listSV.size(); i++) {
            if (id.equals(listSV.get(i).getId())) {
                listSV.get(i).setScore(score);
            }
        }
        WriteFile();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket sv = new ServerSocket(2349);
        Socket ml = sv.accept();
        DataOutputStream dos = new DataOutputStream(ml.getOutputStream());
        DataInputStream dis = new DataInputStream(ml.getInputStream());

        ReadFile();

        while (true) {
            int c = dis.readInt();

            switch (c) {
                case 1:
                    dos.writeUTF(showALL());
                    break;
                case 2:
                    dos.writeUTF(showHB());
                    break;
                case 3:
                    AddSV(dis);
                    dos.writeUTF("Add thanh cong!!!");
                    break;
                case 4:
                    String id_re = dis.readUTF();
                    String score_re = dis.readUTF();
                    suaDiem(id_re, score_re);

                    dos.writeUTF("Sua thanh cong!!!");
                    break;
                case 5:
                    id_re = dis.readUTF();
                    for (int i = 0; i < listSV.size(); i++) {
                        if (id_re.equals(listSV.get(i).getId())) {
                            listSV.remove(i);
                        }
                    }
                    WriteFile();
                    dos.writeUTF("Xoa thanh cong!!!");
                    break;
            }
        }
    }
}