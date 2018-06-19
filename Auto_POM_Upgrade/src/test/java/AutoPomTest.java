import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.junit.Test;

public class AutoPomTest {

    @Test
    public void getRepository() throws Exception {
        Repository repository = new FileRepositoryBuilder()
                .setGitDir(new File("D:\\gitvob\\GSP\\post\\iris4_gsp\\.git"))
                .build();

        // Get a reference
        Ref master = repository.findRef("IRIS4_R24.1_POST");

// Get the object the reference points to
        ObjectId masterTip = master.getObjectId();
        ObjectLoader loader = repository.open(masterTip);
        loader.copyTo(System.out);
    }

    @Test
    public void getGitDiff() throws IOException, GitAPIException {
        Repository repository = new FileRepositoryBuilder()
                .setGitDir(new File("D:\\gitvob\\GSP\\post\\iris4_gsp\\.git"))
                .build();
        Git git = new Git(repository);

        if (repository.exactRef("refs/heads/testbranch") == null) {

            // the diff works on TreeIterators, we prepare two for the two branches
            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "IRIS4_R24.1");
            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "IRIS4_R24.1_POST");

            // then the procelain diff-command returns a list of diff entries
            List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
            List<String> test = diff.stream()
                    .map(entry -> getString(entry)).distinct().collect(Collectors.toList());

            test.forEach(System.out::println);


        }
    }

    private String getString(DiffEntry entry) {
        String result = "";
        if(entry.toString().contains("/config/")){
            result = "Entry: " + entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().indexOf("/"))+"-Config";
        }else {
            result = "Entry: " + entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().indexOf("/"));
        }
        return result;
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

    @Test
    public void searchPOMFile(){
        File node = new File("D:\\gitvob\\GSP\\post\\iris4_gsp");
        displayIt(node);
    }

    private void displayIt(File node) {
        System.out.println(node.getAbsoluteFile());

        if(node.isDirectory()){
            String[] subNote = node.list();
            for(String filename : subNote){
                displayIt(new File(node, filename));
            }
        }
    }
}

