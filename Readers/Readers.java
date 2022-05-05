package Homework2_5.Readers;

public abstract class Readers {
    private String name;
    private String id;
    public Readers(String name,String id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
}
