package com.antaladrien;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fuvar> fuvarok = new ArrayList<>();
	    feltoltFuvarok(fuvarok);
	    //kiirFuvarok(fuvarok);
	    kiirFajlba(fuvarok);
        System.out.println("3. feladat: Ennyi fuvar volt: " + fuvarok.size());
        kiir4esFeladat(fuvarok);
        kiir5osFeladat(fuvarok);
        kiir6osFeladat(fuvarok);
        kiir7esFeladat(fuvarok);
        kiir8asFeladat(fuvarok);
    }

    public static void feltoltFuvarok(ArrayList<Fuvar> fuvarok) {
        try (FileReader fr = new FileReader("fuvar.csv");
        BufferedReader br = new BufferedReader(fr)) {
            String sor;
            br.readLine();
            while ((sor = br.readLine()) != null) {
                //System.out.println(sor);
                StringTokenizer st = new StringTokenizer(sor, ";");
                int taxiAzonosito = Integer.parseInt(st.nextToken());
                String indulasIdopontja = st.nextToken();
                int utazasIdotartama = Integer.parseInt(st.nextToken());
                double megtettTavolsag = convertToDouble(st.nextToken());
                double vitelDij = convertToDouble(st.nextToken());
                double borraValo = convertToDouble(st.nextToken());
                String fizetesModja = st.nextToken();
                Fuvar actFuvar = new Fuvar(taxiAzonosito, indulasIdopontja, utazasIdotartama, megtettTavolsag, vitelDij, borraValo, fizetesModja);
                fuvarok.add(actFuvar);
            }

        } catch (FileNotFoundException fno) {
            System.out.println("Hiba: " + fno.toString());;
        } catch (IOException ioe) {
            System.out.println("Hiba: " + ioe.toString());;
        }
    }

    public static double convertToDouble(String num) {
        String okNum = num.replace(",", ".");
        return Double.parseDouble(okNum);
    }

    public static void kiirFuvarok(ArrayList<Fuvar> fuvarok) {
        for (Fuvar fuvar : fuvarok) {
            System.out.println(fuvar);
        }
    }

    public static void kiirFajlba(ArrayList<Fuvar> fuvarok) {
        try (FileWriter fw = new FileWriter("fuvar2.csv");
            BufferedWriter bw = new BufferedWriter(fw)) {
            for (Fuvar fuvar : fuvarok) {
                bw.write(convertToString(fuvar.taxiAzonosito));
                bw.write(';');
                bw.write(fuvar.indulasIdopontja);
                bw.write(';');
                bw.write(convertToString(fuvar.utazasIdotartama));
                bw.write(';');
                bw.write(convertToString(fuvar.megtettTavolsag));
                bw.write(';');
                bw.write(convertToString(fuvar.vitelDij));
                bw.write(';');
                bw.write(convertToString(fuvar.borraValo));
                bw.write(';');
                bw.write(fuvar.fizetesModja);
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Hiba: " + ioe.toString());;
        }
    }

    public static String convertToString(double num) {
        Double numDouble = num;
        String numString = numDouble.toString();
        return numString.replace(".", ",");
    }

    public static String convertToString(int num) {
        Integer numInteger = num;
        return numInteger.toString();
    }

    public static void kiir4esFeladat(ArrayList<Fuvar> fuvarok) {
        double bevetel = 0.0;
        int fuvarSzam = 0;
        for (Fuvar fuvar : fuvarok) {
            if (fuvar.taxiAzonosito == 6185) {
                ++fuvarSzam;
                bevetel += (fuvar.vitelDij + fuvar.borraValo);
            }
        }
        System.out.println("4. feladat: ");
        System.out.println("A 6185-ösnek a fuvar darabszáma: " + fuvarSzam);
        System.out.println("A 6185-ösnek a bevétele: " + bevetel);
    }

    public static void kiir5osFeladat(ArrayList<Fuvar> fuvarok) {
        Map<String, Integer> fizetesiModok = new HashMap<>();
        for (Fuvar fuvar : fuvarok) {
            if (fizetesiModok.containsKey(fuvar.fizetesModja)) {
                Integer fizetesSzamossaga = fizetesiModok.get(fuvar.fizetesModja);
                ++fizetesSzamossaga;
                fizetesiModok.replace(fuvar.fizetesModja, fizetesSzamossaga);
            } else {
                fizetesiModok.put(fuvar.fizetesModja, 1);
            }
        }
        System.out.println("5. feladat: ");
        for (Map.Entry<String, Integer> entry : fizetesiModok.entrySet()) {
            System.out.println("A fizetés módja: " + entry.getKey() + "; mennyisége: " + entry.getValue());
        }
    }

    public static void kiir6osFeladat(ArrayList<Fuvar> fuvarok) {
        double megtettMerfold = 0.0;
        for (Fuvar fuvar : fuvarok) {
            megtettMerfold += fuvar.megtettTavolsag;
        }
        System.out.println("6. feladat: ");
        System.out.println("Megtett mérföldek száma: " + String.format("%,2f",megtettMerfold));
        System.out.println("Megtett kilométerek: " + String.format("%,2f",megtettMerfold * 1.6));
    }

    public static void kiir7esFeladat(ArrayList<Fuvar> fuvarok) {
        Fuvar leghosszabbFuvar = fuvarok.get(0);
        for (Fuvar fuvar : fuvarok) {
            if (leghosszabbFuvar.utazasIdotartama < fuvar.utazasIdotartama) {
                leghosszabbFuvar = fuvar;
            }
        }
        System.out.println("7. feladat: ");
        System.out.println("A leghosszabb fuvar: " + leghosszabbFuvar.utazasIdotartama);
        System.out.println("A taxi azonosító: " + leghosszabbFuvar.taxiAzonosito);
        System.out.println("A megtett távolság: " + leghosszabbFuvar.megtettTavolsag);
        System.out.println("A viteldíj: " + (leghosszabbFuvar.vitelDij+leghosszabbFuvar.borraValo));
    }

    public static void kiir8asFeladat(ArrayList<Fuvar> fuvarok) {
        List<Fuvar> hibasak = new ArrayList<Fuvar>();
        try (FileWriter fw = new FileWriter("hibak.txt");
        BufferedWriter bw = new BufferedWriter(fw)) {
            for (Fuvar fuvar : fuvarok) {
                if (fuvar.vitelDij > 0 && fuvar.utazasIdotartama > 0 && fuvar.megtettTavolsag == 0.0) {
                    hibasak.add(fuvar);
                }
            }
            Collections.sort(hibasak);
            for (Fuvar fuvar : fuvarok) {
                bw.write(convertToString(fuvar.taxiAzonosito));
                bw.write(';');
                bw.write(fuvar.indulasIdopontja);
                bw.write(';');
                bw.write(convertToString(fuvar.utazasIdotartama));
                bw.write(';');
                bw.write(convertToString(fuvar.megtettTavolsag));
                bw.write(';');
                bw.write(convertToString(fuvar.vitelDij));
                bw.write(';');
                bw.write(convertToString(fuvar.borraValo));
                bw.write(';');
                bw.write(fuvar.fizetesModja);
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Hiba: " + ioe.toString());;
        }
    }
}
