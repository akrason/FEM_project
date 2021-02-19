

public class Matrix {
    double E = (1.0 / Math.sqrt(3));
    double w = 1;

    double E1 = (Math.sqrt(3.0 / 5.0));
    double E2 = 0;
    double w1 = (5.0 / 9.0);
    double w2 = (8.0 / 9.0);

    double n1 = (Math.sqrt((3.0 / 7.0) - (2.0 / 7.0 * Math.sqrt(6.0 / 5.0))));
    double n2 = (Math.sqrt((3.0 / 7.0) + (2.0 / 7.0 * Math.sqrt(6.0 / 5.0))));
    double w41 = ((18.0 + Math.sqrt(30)) / 36.0);
    double w42 = ((18.0 - Math.sqrt(30)) / 36.0);

    GlobalData global = new GlobalData();
    double lp = global.getN_p();
    double alfa = global.getA();
    double t_ambient= global.getTa();

    Local[] local;
    Local[] s;
    double[][] N;
    double[][] dNdXi;
    double[][] dNdEta;
    double[][] J;
    double[] detJ;
    double[][] dNdy;
    double[][] dNdx;
    double[][] H;
    double[][] C;
    int ilosc_pkt;

    public Matrix() {
        if (lp == 2) {                                  //2 punkty całkowania
            local = new Local[]{
                    new Local(-E, -E, w, w),
                    new Local(E, -E, w, w),
                    new Local(-E, E, w, w),
                    new Local(E, E, w, w)
            };
            s = new Local[]{
                    new Local(-E, -1, w, w),
                    new Local(E, -1, w, w),

                    new Local(1, -E, w, w),
                    new Local(1, E, w, w),

                    new Local(E, 1, w, w),
                    new Local(-E, 1, w, w),

                    new Local(-1, E, w, w),
                    new Local(-1, -E, w, w)
            };

        } else if (lp == 3) {                             //3 punkty całkowania
            local = new Local[]{
                    new Local(-E1, -E1, w1, w1),
                    new Local(E2, -E1, w2, w1),
                    new Local(E1, -E1, w1, w1),

                    new Local(-E1, E2, w1, w2),
                    new Local(E2, E2, w2, w2),
                    new Local(E1, E2, w1, w2),

                    new Local(-E1, E1, w1, w1),
                    new Local(E2, E1, w2, w1),
                    new Local(E1, E1, w1, w1)
            };

            s = new Local[]{
                    new Local(-E1, -1, w1, 1),
                    new Local(E2, -1, w2, 1),
                    new Local(E1, -1, w1, 1),

                    new Local(1, -E1, 1, w1),
                    new Local(1, E2, 1, w2),
                    new Local(1, E1, 1, w1),

                    new Local(E1, 1, w1, 1),
                    new Local(E2, 1, w2, 1),
                    new Local(-E1, 1, w1, 1),

                    new Local(-1, E1, 1, w1),
                    new Local(-1, E2, 1, w2),
                    new Local(-1, -E1, 1, w1),
            };

        } else {                                       //4 punkty całkowania
            local = new Local[]{
                    new Local(-n2, -n2, w42, w42),
                    new Local(-n1, -n2, w41, w42),
                    new Local(n1, -n2, w41, w42),
                    new Local(n2, -n2, w42, w42),

                    new Local(-n2, -n1, w42, w41),
                    new Local(-n1, -n1, w41, w41),
                    new Local(n1, -n1, w41, w41),
                    new Local(n2, -n1, w42, w41),

                    new Local(-n2, n1, w42, w41),
                    new Local(-n1, n1, w41, w41),
                    new Local(n1, n1, w41, w41),
                    new Local(n2, n1, w42, w41),

                    new Local(-n2, n2, w42, w42),
                    new Local(-n1, n2, w41, w42),
                    new Local(n1, n2, w41, w42),
                    new Local(n2, n2, w42, w42)
            };

            s = new Local[]{
                    new Local(-n2, -1, w42, 1),
                    new Local(-n1, -1, w41, 1),
                    new Local(n1, -1, w41, 1),
                    new Local(n2, -1, w42, 1),

                    new Local(1, -n2, 1, w42),
                    new Local(1, -n1, 1, w41),
                    new Local(1, n1, 1, w41),
                    new Local(1, n2, 1, w42),

                    new Local(n2, 1, w42, 1),
                    new Local(n1, 1, w41, 1),
                    new Local(-n1, 1, w41, 1),
                    new Local(-n2, 1, w42, 1),

                    new Local(-1, n2, 1, w42),
                    new Local(-1, n1, 1, w41),
                    new Local(-1, -n1, 1, w41),
                    new Local(-1, -n2, 1, w42)
            };
        }

        ilosc_pkt = local.length;
        N = new double[ilosc_pkt][4];
        dNdXi = new double[ilosc_pkt][4];
        dNdEta = new double[ilosc_pkt][4];
        J = new double[ilosc_pkt][4];
        detJ = new double[ilosc_pkt];
        dNdy = new double[ilosc_pkt][4];
        dNdx = new double[ilosc_pkt][4];
        H = new double[4][4];
        C = new double[4][4];
    }

    public void matrixN() {
        for (int i = 0; i < ilosc_pkt; i++) {
            N[i] = new double[]{
                    local[i].N[0],
                    local[i].N[1],
                    local[i].N[2],
                    local[i].N[3]};
        }
    }

    public void matrixdNdXi() {
        for (int i = 0; i < ilosc_pkt; i++) {
            dNdXi[i] = new double[]{
                    local[i].dNdXi[0],
                    local[i].dNdXi[1],
                    local[i].dNdXi[2],
                    local[i].dNdXi[3]};
        }
    }

    public void matrixdNdEta() {
        for (int i = 0; i < ilosc_pkt; i++) {
            dNdEta[i] = new double[]{
                    local[i].dNdEta[0],
                    local[i].dNdEta[1],
                    local[i].dNdEta[2],
                    local[i].dNdEta[3]};
        }
    }

    public void matrixJ(Element element) {

        double[] dXdXi = new double[ilosc_pkt];
        double[] dXdEta = new double[ilosc_pkt];
        double[] dYdXi = new double[ilosc_pkt];
        double[] dYdEta = new double[ilosc_pkt];

        for (int i = 0; i < ilosc_pkt; i++) {
            for (int j = 0; j < 4; j++) {
                dXdXi[i] += dNdXi[i][j] * element.Nodes[j].getX();
                dXdEta[i] += dNdEta[i][j] * element.Nodes[j].getX();
                dYdXi[i] += dNdXi[i][j] * element.Nodes[j].getY();
                dYdEta[i] += dNdEta[i][j] * element.Nodes[j].getY();
            }
            J[i][0] = dXdXi[i];
            J[i][1] = dXdEta[i];
            J[i][2] = dYdXi[i];
            J[i][3] = dYdEta[i];

        }
    }

    public void detJ() {
        for (int j = 0; j < ilosc_pkt; j++) {
            detJ[j] = (J[j][0] * J[j][3]) - (J[j][2] * J[j][1]);
        }
    }

    public void dNDx() {
        for (int i = 0; i < ilosc_pkt; i++) {
            for (int j = 0; j < 4; j++) {
                dNdx[i][j] = (1 / detJ[i]) * ((J[i][3] * dNdXi[i][j]) - (J[i][2] * dNdEta[i][j]));
            }
        }
    }

    public void dNDy() {
        for (int i = 0; i < ilosc_pkt; i++) {
            for (int j = 0; j < 4; j++) {
                dNdy[i][j] = (1 / detJ[i]) * ((J[i][0] * dNdEta[i][j]) - (J[i][1] * dNdXi[i][j]));
            }
        }
    }

    public double[][] matrixH(Element el) {

        double[][] H_matrix = new double[4][4];
        for (int i = 0; i < ilosc_pkt; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 4; l++) {
                    H_matrix[j][l] += el.material.k * (dNdx[i][j] * dNdx[i][l] + dNdy[i][j] * dNdy[i][l]) * detJ[i] * local[i].getW1() * local[i].getW2();
                }
            }
        }

        H = H_matrix;
        return H;
    }

    public double[][] H_global(Grid grid) {
        Element[] elements = grid.getElements();
        double[][] H_global = new double[grid.getNodes().length][grid.getNodes().length];
        for (Element element : elements) {
            int[] ID = new int[4];
            for (int j = 0; j < 4; j++) {
                ID[j] = element.Nodes[j].getID();
            }
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    H_global[ID[j]][ID[k]] += matrixH(element)[j][k];
                }
            }
        }
        return H_global;
    }

    public double[][] matrixC(Element el) {

        double[][] C_matrix = new double[4][4];
        for (int i = 0; i < ilosc_pkt; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 4; l++) {
                    C_matrix[j][l] += el.material.cp * el.material.ro * (N[i][j] * N[i][l]) * detJ[i] * local[i].getW1() * local[i].getW2();
                }
            }
        }

        C = C_matrix;
        return C;
    }

    public double[][] C_global(Grid grid) {
        Element[] elements = grid.getElements();
        double[][] C_global = new double[grid.getNodes().length][grid.getNodes().length];
        for (Element element : elements) {
            int[] ID = new int[4];
            for (int j = 0; j < 4; j++) {
                ID[j] = element.Nodes[j].getID();
            }
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    C_global[ID[j]][ID[k]] += matrixC(element)[j][k];
                }
            }
        }
        return C_global;
    }

    public double[][][] N_1D(Element el) {
        double[][][] NS = new double[ilosc_pkt][(int) lp][ilosc_pkt];

        for (int i = 0; i < lp; i++) {
            Local[] liczba = new Local[(int) lp];
            liczba[i] = s[i];
            for (int j = 0; j < 4; j++) {
                if (el.Nodes[0].getFlag() && el.Nodes[1].getFlag()) {
                    NS[0][i][j] = liczba[i].N[j];
                } else {
                    NS[0][i][j] = 0;
                }
            }
        }

        for (int i = 0; i < lp; i++) {
            Local[] liczba = new Local[(int) lp];
            liczba[i] = s[(int) (i + lp)];
            for (int j = 0; j < 4; j++) {
                if (el.Nodes[1].getFlag() && el.Nodes[2].getFlag()) {
                    NS[1][i][j] = liczba[i].N[j];
                } else {
                    NS[1][i][j] = 0;
                }
            }
        }

        for (int i = 0; i < lp; i++) {
            Local[] liczba = new Local[(int) lp];
            liczba[i] = s[(int) (i + 2 * lp)];
            for (int j = 0; j < 4; j++) {
                if (el.Nodes[2].getFlag() && el.Nodes[3].getFlag()) {
                    NS[2][i][j] = liczba[i].N[j];
                } else {
                    NS[2][i][j] = 0;
                }
            }
        }

        for (int i = 0; i < lp; i++) {
            Local[] liczba = new Local[(int) lp];
            liczba[i] = s[(int) (i + 3 * lp)];
            for (int j = 0; j < 4; j++) {
                if (el.Nodes[3].getFlag() && el.Nodes[0].getFlag()) {
                    NS[3][i][j] = liczba[i].N[j];
                } else {
                    NS[3][i][j] = 0;
                }
            }
        }
        return NS;
    }

    public double detJ1D(Element el, int[] nodeID) {
        double x1, x0, y1, y0;
        x0 = el.getNodes()[nodeID[0]].getX();
        y0 = el.getNodes()[nodeID[0]].getY();

        x1 = el.getNodes()[nodeID[1]].getX();
        y1 = el.getNodes()[nodeID[1]].getY();

        return 0.5 * Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
    }

    public double[][] matrixH_BC(Element el) {
        double[][] matrixH_BC = new double[4][4];
        int[][] nodeID = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        switch ((int) lp) {
            case 2:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            for(int l=0;l<lp;l++) {
                                matrixH_BC[i][k] += detJ * alfa * N_1D(el)[s][l][i] * N_1D(el)[s][l][k];
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < lp; l++) {
                                if (l == 0 || l == 2) {
                                    matrixH_BC[i][k] += detJ * alfa * w1 * N_1D(el)[s][l][i] * N_1D(el)[s][l][k];
                                } else {
                                    matrixH_BC[i][k] += detJ* alfa * w2 * N_1D(el)[s][l][i] * N_1D(el)[s][l][k];
                                }
                            }
                        }
                    }
                }
                break;
            case 4:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < lp; l++) {
                                if (l == 0 || l == 3) {
                                    matrixH_BC[i][k] += detJ * alfa * w42 * N_1D(el)[s][l][i] * N_1D(el)[s][l][k];
                                } else {
                                    matrixH_BC[i][k] += detJ* alfa * w41 * N_1D(el)[s][l][i] * N_1D(el)[s][l][k];
                                }
                            }
                        }
                    }
                }
                break;


        }
        return matrixH_BC;
    }
    public double[][] H_BC_global(Grid grid) {
        Element[] elements = grid.getElements();
        double[][] H_BC_global = new double[grid.getNodes().length][grid.getNodes().length];
        for (Element el : elements) {
            int[] ID = new int[4];
            for (int j = 0; j < 4; j++) {
                ID[j] = el.Nodes[j].getID();
            }
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    H_BC_global[ID[j]][ID[k]] += matrixH_BC(el)[j][k];
                }
            }
        }
        return H_BC_global;
    }

    public double[] vectorP(Element el){
        int[][] nodeID = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        double[] P =new double[4];
        switch((int) lp) {
            case 2:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int l = 0; l < lp; l++) {
                            P[i] += -alfa * t_ambient * (N_1D(el)[s][l][i]) * detJ;
                        }
                    }
                }
                break;
            case 3:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int l = 0; l < lp; l++) {
                            if (l == 1) {
                                P[i] += -alfa * t_ambient * (N_1D(el)[s][l][i] * w2) * detJ;
                            } else {
                                P[i] += -alfa * t_ambient * (N_1D(el)[s][l][i] * w1) * detJ;
                            }
                        }
                    }
                }
                break;
            case 4:
                for (int s = 0; s < 4; s++) {
                    double detJ = detJ1D(el, nodeID[s]);
                    for (int i = 0; i < 4; i++) {
                        for (int l = 0; l < lp; l++) {
                            if (l == 1 || l == 2) {
                                P[i] += -alfa * t_ambient * (N_1D(el)[s][l][i] * w41) * detJ;
                            } else {
                                P[i] += -alfa * t_ambient * (N_1D(el)[s][l][i] * w42) * detJ;
                            }
                        }
                    }
                }
                break;
        }
        return P;
    }



    public double[] P_global(Grid grid){
        Element [] elements = grid.getElements();
        double [] P_global = new double[grid.getNodes().length];
        for (Element el : elements) {
            int[] ID = new int[4];
            for (int j = 0; j < 4; j++) {
                ID[j] = el.Nodes[j].getID();
            }
            for (int k = 0; k < 4; k++) {
                P_global[ID[k]] += vectorP(el)[k];
            }
        }
/*
        System.out.println();
        System.out.println("Vector P global");
        System.out.println(Arrays.toString(P_global));*/

        return P_global;
    }
}