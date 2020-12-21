import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Okno1 extends JFrame implements ActionListener {

    public static StringBuilder binary = new StringBuilder();
    JButton  b2;
    public static JTextField dane;
    JTextArea wDane;
    String wyjsciowe;
    String zmniejszenie_liter;
    String calosc="";



    Okno1() {
        Font myFont = new Font("Calibri", Font.BOLD, 18);
        JLabel background;
        JFrame f = new JFrame("Okno1");
        f.setSize(900, 700);
        f.setLayout(null);
        ImageIcon img = new ImageIcon("bg1.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 1544, 766);
        f.add(background);

        wDane = new JTextArea("Dodane bity stopu i startu");

        wDane.setBounds(250, 150, 400, 200);
        wDane.setFont(myFont);


        background.add(wDane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        b2 = new JButton(new ImageIcon ("przycisk.png"));
        background.add(b2);
        b2.setBounds(320, 500, 150, 70);
        dane = new JTextField("tu wpisz dane");
        background.add(dane);
        dane.setBounds(300, 400, 200, 70);

        b2.addActionListener(this);
        f.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();

        if (zrodlo == b2) {
            calosc="";
            zmniejszenie_liter=" ";
            binary.delete(0,1000);


            zmniejszenie_liter=dane.getText().toLowerCase();
            byte[] bytes = zmniejszenie_liter.getBytes();

            for (byte b : bytes) {
                binary.append('0');
                int val = b;
                for (int i = 0; i < 8; i++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
                binary.append('1');
                binary.append('1');
                binary.append(' ');
            }

            wDane.setLineWrap(true);
            wDane.setText("'" + zmniejszenie_liter + "' to wraz z bitami startu i stopu : " + binary);


            File file = new File("Wulgaryzmy.txt");
            Scanner scanner = null;

            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


            Okno2.binar = Okno1.binary.toString();

            Okno2.litery = Okno2.binar.split(" ");
            for (int j = 0; j < Okno2.litery.length; j++) {
                Okno2.litery[j] = Okno2.litery[j].substring(1, 9);

                calosc = calosc + Okno2.litery[j];
            }

            String[] slowa = calosc.split("00100000");


            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                for (int j = 0; j < slowa.length; j++) {
                    if (slowa[j].equals(line)) {



                        int dlugosc_slowa;
                        dlugosc_slowa = slowa[j].length();
                        slowa[j] = "";
                        for (int l = 0; l < dlugosc_slowa / 8; l++) {

                            slowa[j] = slowa[j] + "00101010";
                        }


                    }
                }
            }


            for (int j = 0; j < slowa.length; j++) {
                wyjsciowe = (Okno2.otrzymane.getText() + Arrays.stream(slowa[j].split("(?<=\\G.{8})"))
                        .parallel()
                        .map(eightBits -> (char) Integer.parseInt(eightBits, 2))
                        .collect(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append
                        ).toString()+" ");

                Okno2.otrzymane.setText(wyjsciowe);
            }



        }
    }
}

