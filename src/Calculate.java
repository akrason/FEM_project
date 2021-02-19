import java.util.Arrays;

import static java.lang.Math.abs;

public class Calculate {
    double[] rozklad_LU(int n, double[][] W, double[] a, double[] X){

        double add;
        X[0] = a[0];
        for (int i = 0; i < n - 1; i++){
            if (abs(W[i][i]) ==0) return null;

            for (int j = i + 1; j < n; j++){
                W[j][i] /= W[i][i];
            }
            for (int j = i + 1; j < n; j++){
                for (int k = i + 1; k < n; k++){
                    W[j][k] -= W[j][i] * W[i][k];
                }
            }
        }

        for (int i = 1; i < n; i++){
            add = 0;
            for (int j = 0; j < i; j++){
                add += W[i][j] * X[j];
            }
            X[i] = a[i] - add;
        }

        if (abs(W[n - 1][n - 1]) ==0) return null;

        X[n - 1] /= W[n - 1][n - 1];

        for (int i = n - 2; i > -1; i--){
            add = 0;
            for (int j = i + 1; j < n; j++){
                add += W[i][j] * X[j];
            }
            if (abs(W[i][i]) ==0) return null;

            X[i] = (X[i] - add) / W[i][i];
        }
        //System.out.println(Arrays.toString(X));
        return X;
    }
    public double[] Gauss(int n, double[][] W, double[] a, double[] X){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(W[j][i]!=0){
                    eliminacja(W,a,n,j,i,wspx(W,j,i));
                    }
            }
        }
        wyniki(W,a,X,n);
        System.out.println(Arrays.toString(X));
        return X;
    }

    void eliminacja(double[][] W,double []a,int il_rz, int n, int k,double wsp){
        for(int i =0; i <il_rz;i++){
            W[n][i]-=wsp*W[k][i];
            if(W[n][i]<0.0001&&W[n][i]>-0.0001){
                W[n][i]=0;
            }
        }
        a[n]-=(wsp*a[k]);
    }

    double wspx(double[][] W,int n, int x){
        return W[n][x]/W[x][x];
    }
    void wyniki(double[][] W,double []a,double []x,int n){
        for(int i =n-1;i>=0;i--){
            x[i]=a[i];
            for(int j = 0; j<n; j++) {
                if(i!=j)
                    x[i]-=W[i][j]*x[j];
            }
            x[i]/=W[i][i];
        }

    }

}
