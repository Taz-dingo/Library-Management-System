package Homework2_5;

import Homework2_5.Readers.Readers;

import java.util.Date;

public class BookRecorder {
    private final Library library;
    private final Books book;
    private Readers reader;
    private String lendDate;   //借出日期
    private String returnDate;    //还回日期
    private String condition;    //图书状态

    public BookRecorder(Books book,String condition,Library library) {
        //书籍入库时记录书的信息
        this.book = book;
        this.condition = condition;   //书入库时状态为"在馆"
        this.library = library;
    }

    public String getCondition(){
        return condition;
    }
    public Books getBook() {
        return book;
    }
    public String getLendDate(){
        return lendDate;
    }
    public String getReturnDate(){ return returnDate; }
    public Readers getReader(){ return reader; }
    public void borrowBook(String[] words){
        this.condition = "已借出";      //图书借出时状态设置为“已借出”
        Date date = new Date();
        this.lendDate = date.toString();      //借出时间
        this.reader = library.searchReader(words);   //借阅人
    }
    public void returnBook(String[] words) {
        this.condition = "在馆";
        Date date = new Date();
        this.returnDate = date.toString();      //还回时间
    }
}
