package Homework2_5;

import Homework2_5.Command.*;
import Homework2_5.Readers.Readers;
import Homework2_5.Readers.Staff;
import Homework2_5.Readers.Students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private ArrayList<Readers> readers = new ArrayList<>();     //Readers的有序集合
    private ArrayList<Books> books = new ArrayList<>();     //Books的有序集合
    private ArrayList<BookRecorder> bookRecorders = new ArrayList<>();
    private HashMap<String, Command> commandHashMap = new HashMap<>();    //字符串->命令对象HashMap
    private Integer numOfBooks;     //馆内存书数量，只加不减，具有唯一性，作为馆藏编号

    public  Library(){
        //指令映射对象，如“帮助”->CommandHelp
        commandHashMap.put("帮助",new CommandHelp(this) );

        commandHashMap.put("登记读者",new CommandRegister(this) );
        commandHashMap.put("移除读者",new CommandRemoveReaders(this));
        commandHashMap.put("查找读者",new CommandSearchReader(this));

        commandHashMap.put("添加图书",new CommandAddBooks(this));
        commandHashMap.put("移除图书",new CommandRemoveBooks(this));
        commandHashMap.put("查找图书",new CommandSearchBook(this));

        commandHashMap.put("借书",new CommandBorrowBook(this));
        commandHashMap.put("还书",new CommandReturnBook(this));

        numOfBooks = 0;     //馆内存书量，初始化为0
    }

    public void run()
    {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();    //line存储下一行输入
            String[] words = line.split(" ");   //以“ ”为分隔将输入的line拆分存入words字符串数组

            if (words[0].equals("再见"))   //输入“再见”退出机制
                break;
            else {
                //根据words[0]位置的命令名获取相应指令的对象
                Command command = commandHashMap.get(words[0]);
                if (command != null) {
                    command.doCmd(words);
                } else
                    System.out.println("没有这种服务~");
            }
        }
    }

    public void printWelcome()
    {
        System.out.println("欢迎来到可每图书馆！");
        System.out.println("你可以输入“帮助”来获取帮助，要离开请输入“离开”");
    }

    public void addBook(String name)
    {
        numOfBooks++;
        //添加图书
        Books book = new Books(name,this.numOfBooks.toString());
        books.add(book);
        //添加记录
        BookRecorder recorder = new BookRecorder(book,"在馆",this);
        bookRecorders.add(recorder);
    }

    public boolean removeBook(String[] words)
    {
        boolean ret = false;
        for(int i=0;i<books.size();i++)
        {
            if(books.get(i).getIndexCode().equals(words[1]))
            {
                books.remove(i);
                bookRecorders.remove(i);
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void addReader(Readers reader)
    {
        readers.add(reader);
    }

    public boolean removeReader(String[] words)
    {
        boolean ret = false;
        for(int i=0;i<readers.size();i++)
        {
            if(readers.get(i).getId().equals(words [1]))
            {
                readers.remove(i);
                ret = true;
                break;
            }
        }
        return ret;
    }

    public BookRecorder searchBook(String[] words)
    {
        BookRecorder ret = null;
        for (BookRecorder temp : bookRecorders) {
            if (temp.getBook().getName().equals(words[1])) {
                ret = temp;     //搜索到目标书籍，令ret为该书籍的记录器
                System.out.println("书名：" + temp.getBook().getName() + " 馆藏编号：" + temp.getBook().getIndexCode() + " 图书状态：" + temp.getCondition());
                //如果是”借书“，只要找到一本处于"可借"状态的书就跳出
                if (words[0].equals("借书") && ret.getCondition().equals("在馆")) {
                    break;
                }
            }
        }

        return ret;
    }

    public BookRecorder searchBookIndex(String[] words)
    {
        BookRecorder ret = null;
        for (BookRecorder temp : bookRecorders) {
            if (temp.getBook().getIndexCode().equals(words[1])) {
                ret = temp;     //搜索到目标书籍，令ret为该书籍的记录器
                System.out.println("书名：" + temp.getBook().getName() + " 馆藏编号：" + temp.getBook().getIndexCode() + " 借出时间：" + temp.getLendDate());
                ret.returnBook(words);
            }
        }
        return ret;
    }


    public Readers searchReader(String[] words)
    {
        Readers ret = null;
        for(Readers temp : readers)
        {
            if (temp.getId().equals(words[1])) {
                ret = temp;
            }
        }
        return ret;
    }

    public void register(String[] words)
    {
        HashMap<String,Readers> readerHashMap = new HashMap<>();
        readerHashMap.put("学生",new Students("",""));
        readerHashMap.put("职工",new Staff("",""));

        Readers reader = readerHashMap.get(words[1]);  //根据words的1号位“身份”确定对象类型
        if (reader != null) {
            reader.setName(words[2]);
            reader.setId(words[3]);
            this.addReader(reader);
            System.out.println("登记成功！");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.printWelcome();
        library.run();

        System.out.println("再见,欢迎下次光临!");
    }
}
