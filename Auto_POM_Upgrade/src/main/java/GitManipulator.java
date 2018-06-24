import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jdk.nashorn.internal.runtime.PropertyMap;
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

class GitManipulator {

    private String rootDirectory;
    private Repository repository;

    GitManipulator(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        try {
            repository = new FileRepositoryBuilder()
                    .setGitDir(new File(this.rootDirectory+"\\.git"))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> getGitDiff() throws GitAPIException, IOException {
        List<String> updatedModules = null;
        if (repository.findRef("origin/IRIS4_R24.1_POST") != null && repository.findRef("origin/IRIS4_R24.1_POST_INT") != null) {
            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "origin/IRIS4_R24.1_POST");
            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "origin/IRIS4_R24.1_POST_INT");

            Git git = new Git(repository);
            List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
            updatedModules = diff
                    .stream()
                    .map(GitManipulator::getString)
                    .distinct().filter(s -> !s.contains("Data"))
                    .collect(Collectors.toList());
        }
        return updatedModules;
    }

    private static String getString(DiffEntry entry) {

        if (entry.toString().contains("/config/")) {
            return entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().indexOf("/")).trim() + "\\Config";
        } else {
            return entry.toString().substring(entry.toString().indexOf(" ")
                    , entry.toString().contains("/") ? entry.toString().indexOf("/") : entry.toString().indexOf("]")).trim();
        }

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
}