package TCP_Example;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class QLSV_client {
    public static void menu(){
        System.out.println("1. Show all");
        System.out.println("2. Show hoc bong");
        System.out.println("3. Add sinh vien");
        System.out.println("4. Sua diem");
        System.out.println("5. Delete");
        System.out.print("Chon: ");
    }
    public static void main(String[] args) throws IOException {
        Socket cl = new Socket("localhost", 2349);
        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
        DataInputStream dis = new DataInputStream(cl.getInputStream());

        while (true){
            menu();
            int c = new Scanner(System.in).nextInt();
            dos.writeInt(c);

            switch (c){
                case 1:
                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println(dis.readUTF());
                    break;
                case 3:
                    System.out.println("Nhap id: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap ten: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap ngay sinh: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap dia chi: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println("Nhap diem: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println(dis.readUTF());
                    break;
                case 4:
                    System.out.println("Nhap id can sua: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println("Nhap diem: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());

                    System.out.println(dis.readUTF());
                    break;
                case 5:
                    System.out.println("Nhap id can xoa: ");
                    dos.writeUTF(new Scanner(System.in).nextLine());
                    System.out.println(dis.readUTF());
                    break;
            }
        }
    }
}
