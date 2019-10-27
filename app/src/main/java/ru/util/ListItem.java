package ru.util;

public class ListItem {
    private Long id;
    private Integer type;
    public ListItem(Long id, Integer type){
        this.id     = id;
        this.type   = type;
    }
    public Long getId(){ return id;        }
    public Integer getType(){ return type; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
