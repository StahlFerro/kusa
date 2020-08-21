package io.stahlferro.kusa.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class OLibAuthor {
    private long start;
    private long num_found;
    private long numFound;
    private List<OLibDocs> docs;
    public OLibAuthor() {}

    public long getStart() {
        return start;
    }
    public long getNum_found() {
        return num_found;
    }
    public long getNumFound() {
        return numFound;
    }
    public List<OLibDocs> getDocs() {
        return docs;
    }
}
