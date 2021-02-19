public class Element {

    Node[] Nodes = new Node[4];
    int nr;
    Material material;

    public Element(int number) { nr = number; }

    public void setNodes(Node n1 , Node n2, Node n3, Node n4) {
        Nodes[0] = n1;
        Nodes[1] = n2;
        Nodes[2] = n3;
        Nodes[3] = n4;
    }
    public void setMaterial(Material material){
        this.material = material;
    }

    @Override
    public String toString() {
        return "Element: " + nr + " \nwęzły:\n" + Nodes[3].getID() + "\t" + Nodes[2].getID() + "\n"
                + Nodes[0].getID() + "\t" + Nodes[1].getID();
    }

    public String materialE(){
        return "Element: "+nr+"\t cp:"+material.cp+"\t ro:"+material.ro +"\t k: "+material.k;
    }

    public Node[] getNodes() {
        return Nodes;
    }
}
