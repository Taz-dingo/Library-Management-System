package Homework2_5.Command;

import Homework2_5.Library;
import Homework2_5.Readers.Readers;

public class CommandSearchReader extends Command{
    public CommandSearchReader(Library library)
    {
        super(library);
    }

    @Override
    public void doCmd(String[] words)
    {
        if(words.length >= 2) {
            if (words[0].equals("查找读者")) {
                Readers reader = library.searchReader(words);
                if(reader!=null)
                {
                    System.out.println("姓名：" + reader.getName() + " 学(工)号：" + reader.getId());
                }else
                {
                    System.out.println("查无此人！");
                }
            } else if (words[1].equals("借书")) {
                String[] changedWords = words;
                changedWords[1] = changedWords[2];
                Readers readers = library.searchReader(changedWords);
            }
        }
        else
            System.out.println("没听懂，请检查指令是否输入正确！");
    }
}
