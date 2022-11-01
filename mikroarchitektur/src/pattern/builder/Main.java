package pattern.builder;

import java.util.Date;

//Example source https://www.vogella.com/tutorials/DesignPatternBuilder/article.html
public class Main {
    public static void main(String[] args) {
        Task task = new TaskBuilder(5).setDescription("Hello").setSummary("Test").build();
        Task task1 = new TaskBuilder(963).setSummary("Test").build();
        Task task2 = new TaskBuilder(5).setDescription("Hello").setDueDate(new Date()).build();
        System.out.println(task);
        System.out.println(task1);
        System.out.println(task2);
    }
}
