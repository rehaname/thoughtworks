package com.tool.main;

import com.tool.pojo.DomainGit;
import java.io.File;
import java.io.IOException;
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

class GitManipulator {

    private static final String DATA = "Data";
    private static final String CONFIG = "/config/";
    private static final String FRONTSLASH = "/";
    private static final String CONFIG1 = "\\Config";
    private static final String SPACE = " ";
    private static final String CLOSINGBRACKET = "]";
    private static final String GIT = "\\.git";
    private DomainGit domain;
    private Repository repository;

    GitManipulator(DomainGit rootDirectory) {
        this.domain = rootDirectory;
        try {
            repository = new FileRepositoryBuilder()
                    .setGitDir(new File(this.domain.getRootDirectory() + GIT))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> getGitDiff() throws GitAPIException, IOException {
        List<String> updatedModules = null;
        if (repository.findRef(domain.getMainBranch()) != null && repository.findRef(domain.getIntBranch()) != null) {
            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, domain.getMainBranch());
            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, domain.getIntBranch());

            Git git = new Git(repository);
            List<DiffEntry> diff = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
            updatedModules = diff
                    .stream()
                    .map(GitManipulator::getString)
                    .distinct().filter(s -> !s.contains(DATA))
                    .collect(Collectors.toList());
        }
        return updatedModules;
    }

    private static String getString(DiffEntry entry) {

        if (entry.toString().contains(CONFIG)) {
            return entry.toString().substring(entry.toString().indexOf(SPACE)
                    , entry.toString().indexOf(FRONTSLASH)).trim() + CONFIG1;
        } else {
            return entry.toString().substring(entry.toString().indexOf(SPACE)
                    , entry.toString().contains(FRONTSLASH) ? entry.toString().indexOf(FRONTSLASH) : entry.toString().indexOf(CLOSINGBRACKET)).trim();
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