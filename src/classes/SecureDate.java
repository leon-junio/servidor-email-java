/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author junio
 */
public class SecureDate {

    public void escrever(String IP) throws IOException {
        new File("dir/bin/bin.txt").delete();
        File f = new File("dir/bin/bin.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(f));
        out.write(IP);
        out.close();
    }

 
    public String ler() {
        try {
            File f = new File("dir/bin/bin.txt");
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.readLine();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        }
    }

    public String ler2() {
        try {
            File f = new File("dir/bin/bin2.txt");
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.readLine();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        }
    }

    public String ler3() {
        try {
            File f = new File("dir/bin/bin3.txt");
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.readLine();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Erro io" + ex.getMessage());
            return null;
        }
    }

    //METODOS PARA CONVERTER HORAS E DATAS
    public String dinheiroConverter(float num) {
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.HALF_UP);
            String text = "R$ ";
            text += df.format(num);
            return text;
        } catch (Exception e) {
            System.out.println("Erro na conversão de preço");
            return null;
        }
    }

    public boolean errors(Component c, String msg) {
        try {
            JOptionPane.showMessageDialog(c, msg, "Erro!", JOptionPane.ERROR_MESSAGE);
            return true;
        } catch (Exception e) {
            System.out.println("Erro na msg de erro");
            return false;
        }
    }

    public int exclusao(Component cp, String msg) {
        int x = JOptionPane.showConfirmDialog(cp, msg, "Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return x;
    }

    public MaskFormatter formatCpf() {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###.###.###-##");
            return mask;
        } catch (ParseException ex) {
            return null;
        }
    }

    public MaskFormatter formatCnpj() {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("##.###.###/0001-##");
            return mask;
        } catch (ParseException ex) {
            return null;
        }
    }

    public MaskFormatter formatTelefone() {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##)####-####");
            return mask;
        } catch (ParseException ex) {
            return null;
        }
    }

    public MaskFormatter formatCelular() {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##)#####-####");
            return mask;
        } catch (ParseException ex) {
            return null;
        }
    }

    public MaskFormatter formatCep() {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("#####-###");
            return mask;
        } catch (ParseException ex) {
            return null;
        }
    }

    public String formatHoras(java.sql.Date d) {
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
        return sfd.format(d);
    }

    public String geradorTab(int som) {
        String tab = "";
        for (int i = 0; i < som; i++) {
            tab += " ";
        }
        return tab;
    }
}
