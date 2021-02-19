

public class Grid {

    GlobalData global = new GlobalData();
    Node[] nodes;
    Element[] elements;
    double deltaX = (global.getW()) / (global.getnW() -1);
    double deltaY = (global.getH()) / (global.getnH() -1);
    Material szklo = new Material(840,2500,0.8);
    Material ceramika=new Material(1000,2300,1.3);
    Material stal = new Material(460,7900,17);
    Material woda = new Material(4200,980,0.6);

    public Grid() {
        nodes = new Node[global.getnN()];
        elements = new Element[global.getnE()];
        int id = 0;
        for(int i=0; i<global.getnW(); i++){
            for(int j=0; j<global.getnH(); j++ ){
                nodes[id] = new Node(id,i*deltaX,j*deltaY,global.getH(), global.getW());
                id++;
            }
        }
        id = 0;
        for(int i=0; i< global.getnE(); i++){
            elements[i] = new Element(i + 1);
            if((nodes[id].getY() == global.getH()) && (i != 0 )) { id++; }

            int id1=nodes[id].getID();

            elements[i].setNodes(nodes[id1], nodes[id1 +(int)global.getnH()],
                    nodes[id1 + (int)global.getnH() + 1], nodes[id1 + 1]);
            id++;
        }
        for(int i =0;i<12;i++){
            elements[i].setMaterial(stal);
        }
        for(int i = 12;i<232;i++){
            elements[i].setMaterial(woda);
        }
        for(int i =232;i<244;i++){
            elements[i].setMaterial(stal);
        }
    }

    public Node[] getNodes() { return nodes; }
    public Element[] getElements() { return elements; }
}