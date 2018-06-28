package com.tool.pojo;

import java.util.List;

public class Module {
    private String module;

    private List<String> poms;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public List<String> getPoms() {
        return poms;
    }

    public void setPoms(List<String> poms) {
        this.poms = poms;
    }

    @Override
    public String toString() {
        return "ClassPojo [module = " + module + ", poms = " + poms + "]";
    }
}