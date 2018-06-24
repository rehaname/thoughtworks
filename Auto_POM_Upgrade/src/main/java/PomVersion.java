import org.w3c.dom.Node;

public class PomVersion {

    private Node name;
    private Node version;

    public Node getName() {
        return name;
    }

    public void setName(Node name) {
        this.name = name;
    }

    public Node getVersion() {
        return version;
    }

    public void setVersion(Node version) {
        this.version = version;
    }
}
