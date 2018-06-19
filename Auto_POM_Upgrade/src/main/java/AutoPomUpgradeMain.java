import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class AutoPomUpgradeMain {
    private static Git git;
    private static Repository repository;
    private static String fileString;
    private static List<String> pomList = new ArrayList<>();

    private static Git initGit() throws IOException {
        repository = new FileRepositoryBuilder()
                .setGitDir(new File("D:\\gitvob\\GSP\\post\\iris4_gsp\\.git"))
                .build();
        return new Git(repository);
    }

    public static void main(String[] args) throws IOException, GitAPIException {
        fileString = "D:\\gitvob\\GSP\\post\\iris4_gsp\\";
        git = initGit();

        getGitDiff().forEach(diff -> {

        });
//        pomList.forEach(s -> {
//            if (s.contains("pom") && s.contains("xml") && !s.contains("\\Data\\")) {
//                System.out.println(s);
//            }
//        });
    }

    private static List<String> getGitDiff() throws GitAPIException, IOException {
        List<String> updatedModules = null;
        if (repository.exactRef("refs/heads/testbranch") == null) {

            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "IRIS4_R24.1");
            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "IRIS4_R24.1_POST");

            List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
            updatedModules = diff.stream()
                    .map(entry -> getString(entry)).distinct().collect(Collectors.toList());
        }

        return updatedModules;
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String ref) throws IOException {
        // from the commit we can build the tree which allows us to construct the TreeParser
        Ref head = repository.findRef(ref);
        RevWalk walk = new RevWalk(repository);
        RevCommit commit = walk.parseCommit(head.getObjectId());
        RevTree tree = walk.parseTree(commit.getTree().getId());

        CanonicalTreeParser treeParser = new CanonicalTreeParser();
        ObjectReader reader = repository.newObjectReader();
        treeParser.reset(reader, tree.getId());
        walk.dispose();

        return treeParser;
    }

    private static String getString(DiffEntry entry) {

        if (entry.toString().contains("/config/")) {
            return entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().indexOf("/")).trim() + "\\config";
        } else {
            return entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().indexOf("/")).trim();
        }
    }

    private static void displayIt(File node) {

        if (node.getAbsoluteFile().toString().contains("pom")) {
            pomList.add(node.getAbsoluteFile().toString());
        }
        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                displayIt(new File(node, filename));
            }
        }
    }
}
