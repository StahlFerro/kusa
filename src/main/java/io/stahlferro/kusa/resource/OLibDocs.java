package io.stahlferro.kusa.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLibDocs {
    private String title;
    private int first_publish_year;
    private int ebook_count_i;
    public OLibDocs(){}

    public String getTitle() {
        return title;
    }

    public int getFirst_publish_year() {
        return first_publish_year;
    }

    public int getEbook_count_i() {
        return ebook_count_i;
    }
}
