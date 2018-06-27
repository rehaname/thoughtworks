package com.tool.main;

import com.google.gson.Gson;
import com.tool.pojo.DomainGit;
import com.tool.pojo.Module;
import com.tool.pojo.PomVersion;
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
    private static final String DATA = "\\Data\\";
    private static final String URI = "..\\DomainGit.json";
    private static final String ARTIFACT_ID = "artifactId";
    private static final String VERSION = "version";
    private static final String BACKSLASH = "\\";
    private static final String POM = "pom";
    private static final String XML = "xml";
    private static final String YES = "yes";
    public static final int VERSION_SIZE = 12;
    public static final double INCREMENT_VERSION = .001;
    private static List<String> pomList = new ArrayList<>();
    private static DomainGit domain;


    public static void main(String[] args) throws IOException, GitAPIException {
        String jsonContent = new String(Files.readAllBytes(new File(URI).toPath()));
        domain = new Gson().fromJson(jsonContent, DomainGit.class);
        GitManipulator git = new GitManipulator(domain);
        git.getGitDiff().forEach(getPomToBeUpdated());
        updateAllPom();
    }

    private static void updateAllPom() {
        pomList.forEach((String s) -> {
            if (s.contains(POM) && s.contains(XML) && !s.contains(DATA)) {
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    String pomFile = domain.getRootDirectory() + BACKSLASH + s;
                    Document doc = docBuilder.parse(pomFile);

                    getPomVersions(doc);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, YES);
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
        for (int i = 0; i < doc.getElementsByTagName(ARTIFACT_ID).getLength() - 1; i++) {
            PomVersion ver = new PomVersion();
            ver.setName(doc.getElementsByTagName(ARTIFACT_ID).item(i));
            ver.setVersion(doc.getElementsByTagName(VERSION).item(i));
            versions.add(ver);
        }
        versions.forEach(v -> {
            if (v.getVersion() != null) {

                v.getVersion().setTextContent(incrementVersion(v.getVersion()));
            }
        });
    }

    private static String incrementVersion(Node version) {

        String currentVersion = version.getTextContent();
        String newVersion = currentVersion;
        if (currentVersion.length() == VERSION_SIZE) {
            newVersion = String.valueOf((Float.parseFloat(getSubstring(currentVersion, VERSION_SIZE)) + INCREMENT_VERSION));
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

    private static void getPom(Module module) {
        for (String pom : module.getPoms()) {
            Module innerModule = domain.getSingleModule(pom);
            if (innerModule != null) {
                getPom(innerModule);
            } else {
                if (!pomList.contains(module.getModule() + BACKSLASH + pom)) {
                    pomList.add(module.getModule() + BACKSLASH + pom);
                }
            }
        }
    }
}
