import java.util.List;

public class DomainGit {
    private String rootDirectory;

    private String mainBranch;

    private String intBranch;

    private List<Module> modules;

    public String getMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(String mainBranch) {
        this.mainBranch = mainBranch;
    }

    public String getIntBranch() {
        return intBranch;
    }

    public void setIntBranch(String intBranch) {
        this.intBranch = intBranch;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Module getSingleModule(String diffModule) {
        for (Module module : modules) {
            if (module.getModule().equals(diffModule)) {
                return module;
            }
        }
        return null;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    @Override
    public String toString() {
        return "ClassPojo [mainBranch = " + mainBranch + ", intBranch = " + intBranch + ", modules = " + modules + ", root = " + rootDirectory +  "]";
    }
}