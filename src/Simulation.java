import java.util.Arrays;

public class Simulation {
    public void simulate(Grid grid){
        Matrix matrix = new Matrix();
        GlobalData global=new GlobalData();
        Calculate calc=new Calculate();
        matrix.matrixN();
        matrix.matrixdNdXi();
        matrix.matrixdNdEta();
        double[][] H,H_BC,C;
        double[]   P,t_vector = new double[grid.getNodes().length];
        double[][] macierz_H = new double[grid.getNodes().length][grid.getNodes().length];
        double deltaT= global.getdT();
        double t0= global.getT0();
        double T= global.getT();
        int it= (int) (T/deltaT);
        double[]min=new double[it];
        double[]max=new double[it];

        Arrays.fill(t_vector,t0);
        for (int j=0;j<grid.getNodes().length;j++){
            grid.getNodes()[j].setTemp(t_vector[j],0);
        }
        for(int i=0;i<grid.getElements().length;i++) {
            matrix.matrixJ(grid.getElements()[i]);
            matrix.detJ();
            matrix.dNDx();
            matrix.dNDy();
        }
        for(int i = 0;i<it;i++){

            C=matrix.C_global(grid);
            H=matrix.H_global(grid);
            H_BC=matrix.H_BC_global(grid);
            P= matrix.P_global(grid);
            for(int j=0;j<P.length;j++){
                P[j]*=-1;
            }
            for (int k =0;k<grid.getNodes().length;k++){
                for (int j =0;j<grid.getNodes().length;j++){
                    macierz_H[k][j]=H_BC[k][j]+H[k][j]+C[k][j]/deltaT;
                    P[k]+=C[k][j]/deltaT*t_vector[j];
                }
            }
            //System.out.println("Iteracja: "+i+"\n");

            //System.out.println("{P+C/dT*t0}\n"+Arrays.toString(P)+"\n");
            t_vector=calc.rozklad_LU(grid.getNodes().length,macierz_H,P,t_vector);

            for (int j=0;j<grid.getNodes().length;j++){
                grid.getNodes()[j].setTemp(t_vector[j],i+1);
            }
            System.out.println("Iteracja: "+ i+"\t"+t_vector[2]+"\t"+t_vector[17]+"\t"+t_vector[153]+"\t"+t_vector[292]+"\t"+t_vector[307]);
        }
    }
}
