public class Local {
    double[] w;
    double[] dNdXi;
    double[] dNdEta;
    double[] N;

    public Local(double xi,double eta, double w1,double w2){
        w=new double[]{w1,w2};
        N= getN(xi,eta);
        dNdXi = getdNdXi(eta);
        dNdEta = getdNdEta(xi);
    }

    public double[] getN(double xi, double eta){return new double[]{0.25 * (1 - xi) * (1 - eta),
            0.25 * (1 + xi) * (1 - eta),
            0.25 * (1 + xi) * (1 + eta),
            0.25 * (1 - xi) * (1 + eta)};
    }

    public double[] getdNdXi(double eta) {
        return new double[]{(-0.25) * (1 - eta),0.25 * (1 - eta),0.25 * (1 + eta),(-0.25) * (1 + eta)};
    }
    public double[] getdNdEta(double xi) {
        return new double[]{(-0.25) * (1 - xi),(-0.25) * (1 + xi),0.25 * (1 + xi),0.25 * (1 - xi)};
    }

    public double getW1(){ return w[0]; }
    public double getW2(){return w[1];}
}
