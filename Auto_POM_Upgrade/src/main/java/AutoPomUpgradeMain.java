import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.impl.Log4JLogger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class AutoPomUpgradeMain {
    public static final String URI = "D:\\My Documents\\Melvin Yu\\thoughtworks\\Auto_POM_Upgrade\\src\\main\\java\\DomainGit.json";
    private static List<String> pomList = new ArrayList<>();
    private static DomainGit domain;


    public static void main(String[] args) throws IOException, GitAPIException {
        String jsonContent = new String(Files.readAllBytes(new File(URI).toPath()));
        domain = new Gson().fromJson(jsonContent, DomainGit.class);
        GitManipulator git = new GitManipulator(domain.getRootDirectory());
        git.getGitDiff().forEach(getPomToBeUpdated());
        updateAllPom();

    }

    private static void updateAllPom() {
        pomList.forEach((String s) -> {
            if (s.contains("pom") && s.contains("xml") && !s.contains("\\Data\\")) {
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    String pomFile = domain.getRootDirectory() + "\\" + s;
                    Document doc = docBuilder.parse(pomFile);

                    // Get the element by tag name directly
                    getPomVersions(doc);

                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(pomFile));
                    transformer.transform(source, result);

                } catch (ParserConfigurationException | TransformerException | org.xml.sax.SAXException | IOException e) {
                    new Log4JLogger().info(e.getMessage());
                }
            }
        });
    }

    private static void getPomVersions(Document doc) {

        List<PomVersion> versions = new ArrayList<>();
        for (int i = 0; i < doc.getElementsByTagName("artifactId").getLength() - 1; i++) {
            PomVersion ver = new PomVersion();
            ver.setName(doc.getElementsByTagName("artifactId").item(i));
            ver.setVersion(doc.getElementsByTagName("version").item(i));
            versions.add(ver);
        }
        versions.stream().forEach(v -> {
            if (v.getVersion() != null) {

                v.getVersion().setTextContent(incrementVersion(v.getVersion()));
            }
        });
    }

    private static String incrementVersion(Node version) {

        String currentVersion = version.getTextContent();
        String newVersion = currentVersion;
        if (currentVersion.length() == 12) {
            newVersion = String.valueOf((Float.parseFloat(getSubstring(currentVersion, 12)) + .001));
            newVersion = currentVersion.substring(0, currentVersion.lastIndexOf('.')) + getSubstring(newVersion, 5);
        }
        return newVersion;
    }


    private static String getSubstring(String string, int endIndex) {
        return string.substring(string.lastIndexOf('.'), endIndex);
    }


    private static Consumer<String> getPomToBeUpdated() {
        return diffModule -> {
            Module module = domain.getSingleModule(diffModule);
            getPom(module);
        };
    }

    public static void getPom(Module module) {
        for (String pom : module.getPoms()) {
            Module innerModule = domain.getSingleModule(pom);
            if (innerModule != null) {
                getPom(innerModule);
            } else {
                if (!pomList.contains(module.getModule() + "\\" + pom)) {
                    pomList.add(module.getModule() + "\\" + pom);
                }
            }
        }
    }
}
