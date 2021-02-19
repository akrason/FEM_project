public class Node {
    GlobalData global=new GlobalData();
    int li= (int) (global.getT()/ global.getdT());
    private int id;
    private double x;
    private double y;
    private boolean flag;
    private double temp[]=new double[li+1];

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getID() {
        return id;
    }

    public void setTemp(double temp,int i) {
        this.temp[i] = temp;
    }

    public double getTemp(int i) { return temp[i]; }

    public boolean isFlag(double H, double W) {
        return (x==0 || x==W);
    }
    public boolean getFlag(){
        return flag;
    }
    public Node(int index, double X, double Y,double H, double W) {
        id = index;
        x = X;
        y = Y;
        flag=isFlag(H, W);
    }
}