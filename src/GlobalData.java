import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GlobalData {
    private double H, W, nH, nW,t0,a,ta,dT,T;
    private int n_p, n_E,n_N;


    public GlobalData() {
        double[] data = Data();

        H =  data[0];
        W = data[1];
        nH = data[2];
        nW = data[3];
        n_p = (int)data[4];
        t0=data[5];
        a=data[6];
        ta=data[7];
        dT=data[8];
        T=data[9];

        n_N = (int)(nH * nW);
        n_E = (int)((nH - 1) * (nW - 1));
    }

    public double[] Data(){
        double[] data = new double[15];
        int i = 0;
        File file = new File("/Users/Ola/Desktop/GitHub/FEM_project/src/resources/mes.txt");
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextDouble()){
                double number = scanner.nextDouble();
                data[i] = number;
                i++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Nie znaleziono pliku!!");
        }
        return data;
    }

    public double getH() { return H; }
    public double getW() { return W; }
    public double getnH() { return nH; }
    public double getnW() { return nW; }
    public int getN_p(){ return n_p; }
    public int getnE() { return n_E; }
    public int getnN() { return n_N; }
    public double getA() { return a; }
    public double getTa() { return ta; }
    public double getdT() { return dT; }
    public double getT0() { return t0; }
    public double getT() { return T; }
}
