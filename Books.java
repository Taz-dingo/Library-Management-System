package Homework2_5;

public class Books {
    private final String name;    //书名
    private final String indexCode;   //馆藏编号

    public Books(String name,String indexCode)
    {
        this.name = name;
        this.indexCode = indexCode;
    }

    public String getName() {
        return name;
    }

    public String getIndexCode(){
        return indexCode;
    }
}
