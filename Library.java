package Homework2_5;

import Homework2_5.Readers.Readers;
import Homework2_5.Readers.Staff;
import Homework2_5.Readers.Students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private final ArrayList<Readers> readers = new ArrayList<>();     //Readers的有序集合
    private final ArrayList<Books> books = new ArrayList<>();     //Books的有序集合
    private final ArrayList<BookRecorder> bookRecorders = new ArrayList<>();
    private final HashMap<String, Command> commandHashMap = new HashMap<>();    //字符串->命令对象HashMap
    private Integer numOfBooks;     //馆内存书数量，只加不减，具有唯一性，作为馆藏编号

    static class ReaderNotFoundException extends Exception{
        public ReaderNotFoundException(String warning){
            super(warning);
        }
    }
    static class BookNotFoundException extends Exception{
        public BookNotFoundException(String warning){
            super(warning);
        }
    }
    static class BookLentException extends Exception{
        public BookLentException(String warning){
            super(warning);
        }
    }
    static class CommandNotFound extends Exception{
        public CommandNotFound(String warning){
            super(warning);
        }
    }

    public  Library(){
        //指令名映射具体的指令对象，String->Command对象
        commandHashMap.put("帮助", (words)-> printHelp());

        commandHashMap.put("登记读者", (words) -> {
            try {
                register(words);
            }catch (ArrayIndexOutOfBoundsException e) {
                printCommandError();
            }
        });

        commandHashMap.put("移除读者", (words) -> {
            try {
                if(!removeReader(words))
                    throw new ReaderNotFoundException("该读者尚未登记，无法借书！");
                System.out.println("已成功移除！");
            }catch (ArrayIndexOutOfBoundsException e) {
                printCommandError();
            }catch (ReaderNotFoundException e){
                System.out.println(e.getMessage());
            }
        });

        commandHashMap.put("查找读者", (words) -> {
            try{
                Readers reader = searchReader(words);
                if(reader==null)
                    throw new ReaderNotFoundException("没有这个人");
                System.out.println("姓名：" + reader.getName() + " 学(工)号：" + reader.getId());
            }catch (ArrayIndexOutOfBoundsException e) {
                printCommandError();
            }catch (ReaderNotFoundException e){
                System.out.println(e.getMessage());
            }
        });

        commandHashMap.put("添加图书", (words) -> {
            try {
                addBook(words[1]);
                System.out.println("添加成功！");
            }catch (ArrayIndexOutOfBoundsException e) {
                printCommandError();
            }
        });

        commandHashMap.put("移除图书", (words) -> {
            try {
                if(!removeBook(words))
                    throw new BookNotFoundException("馆里的这种书都被借走了噢");
                System.out.println("已成功移除！");

            }catch (ArrayIndexOutOfBoundsException e) {
                printCommandError();
            }catch (BookNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });

        commandHashMap.put("查找图书", (words) -> {
            try {
                if (searchBook(words) == null)
                    throw new BookNotFoundException("找不到这本书！");

                System.out.println("查找完毕！");
            }catch (ArrayIndexOutOfBoundsException e){
                printCommandError();
            }catch (BookNotFoundException e){
                System.out.println(e.getMessage());
            }
        });

        commandHashMap.put("借书", (words) -> {
            try{
                String[] changedWords = new String[words.length];
                System.arraycopy(words, 0, changedWords, 0, words.length);      //arraycopy
                changedWords[1] = changedWords[2];
                Readers reader = searchReader(changedWords);
                if (reader == null)     //如果在馆内没找到<指定读者>的登记记录
                    throw new ReaderNotFoundException("该读者尚未登记，无法借书！");

                BookRecorder recorder = searchBook(words);
                if (recorder == null)      //如果在馆内没找到<指定图书>
                    throw new BookNotFoundException("咱们这没这本书！");

                if (recorder.getCondition().equals("已借出"))
                    throw new BookLentException("馆里的这种书都被借走了噢");

                //目前只有“已借出”、“在馆”两种状态
                if (recorder.getCondition().equals("在馆")) {
                    recorder.borrowBook(changedWords);
                    System.out.println("借书读者：" + reader.getName() + " 借书时间：" + recorder.getLendDate());
                    System.out.println("借书成功！本书馆藏编号为：" + recorder.getBook().getIndexCode());
                    System.out.println("还书时请使用馆藏编号~");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                printCommandError();
            }catch (ReaderNotFoundException | BookNotFoundException | BookLentException e){
                System.out.println(e.getMessage());
            }
        });

        commandHashMap.put("还书", (words) -> {
            try{
                BookRecorder recorder = searchBookIndex(words);
                if (recorder == null)       //如果没找到<指定图书>（书和图书记录器是绑在一起的，所以）
                    throw new BookNotFoundException("馆内没有这个编号的书！");
                recorder.returnBook(words);

                System.out.println("借书读者：" + recorder.getReader().getName() + " 借书时间：" + recorder.getLendDate());
                System.out.println("还书成功！还书时间：" + recorder.getReturnDate());
            }catch (ArrayIndexOutOfBoundsException e){
                printCommandError();
            }catch (BookNotFoundException e){
                System.out.println(e.getMessage());
            }
        });

        numOfBooks = 0;     //馆内存书量，初始化为0
    }

    public void run()
    {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();    //line存储下一行输入
            String[] words = line.split(" ");   //以“ ”为分隔将输入的line拆分存入words字符串数组

            try{
                if (words[0].equals("再见"))   //输入“再见”退出机制
                    break;

                //根据words[0]位置的命令名获取相应指令的对象
                Command command = commandHashMap.get(words[0]);
                if (command == null)
                    throw new CommandNotFound("没有这种服务~");
                command.doCmd(words);

            }catch (CommandNotFound e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printWelcome()
    {
        System.out.println("欢迎来到可每图书馆");
        System.out.println("你可以输入“帮助”来获取帮助，要离开请输入“离开”");
    }

    public void printCommandError(){
        System.out.println("指令不符合要求，请检查！");
    }

    public void printHelp(){
        System.out.println("你可以进行的操作有：");
        System.out.println();
        System.out.println("“添加图书 (书名)”");
        System.out.println("“移除图书 (馆藏编号)”");
        System.out.println("“查找图书 (书名)”");
        System.out.println();
        System.out.println("”登记读者 (身份|选填:’学生/‘职工’) (姓名) (学号/工号)“");
        System.out.println("“移除读者 (学号/工号)”");
        System.out.println("“查找读者 (学号/工号)”");
        System.out.println();
        System.out.println("“借书 (书名) (读者学号/工号)");
        System.out.println("“还书 (馆藏编号)”");
        System.out.println();
        System.out.println("注意：只有登记过的读者才可以借书哦！");
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
                System.out.println("书名：" + temp.getBook().getName() + " 馆藏编号：" + temp.getBook().getIndexCode());
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

        reader.setName(words[2]);
        reader.setId(words[3]);
        this.addReader(reader);
        System.out.println("登记成功！");
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.printWelcome();
        library.run();

        System.out.println("再见,欢迎下次光临!");
    }
}
