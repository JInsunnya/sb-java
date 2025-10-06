package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Todo> todos = new ArrayList<>();
    private long todosLastId = 0;
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("할일 관리 앱, 시작");

//        Scanner scanner = new Scanner(System.in);
//        scanner.close();
//        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("명령) ");
                String cmd = scanner.nextLine().trim();

                if (cmd.equals("exit")) break;
                else if (cmd.equals("add")) {
                    add();
                }
                else if (cmd.equals("list")) {
                    list();
                }
                else if (cmd.equals("del")) {
                    del();
                }
                else if (cmd.equals("modify")) {
                    modify();
                }
            }
//        }

        System.out.println("할일 관리 앱, 끝");
    }

    private void modify() {
        System.out.print("수정할 할일의 번호 : ");
        long id = Long.parseLong(scanner.nextLine().trim());

        Todo foundTodo = todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundTodo == null) {
            System.out.printf("%d번 할일은 존재하지 않습니다.\n", id);
            return;
        }
        System.out.printf("기존 할일 : %s\n", foundTodo.getContent());
        System.out.print("새 할일 : ");
        foundTodo.setContent(scanner.nextLine().trim());

        System.out.printf("%d번 할일이 수정되었습니다.\n", id);
    }

    private void list() {
        System.out.println("번호 / 내용");

//                    for (int i = 0; i < todos.size(); i++) {
//                        Todo todo = todos.get(i);
//                    }
//                    for (Todo todo : todos) {
//                        System.out.printf("%d / %s\n", todo.getId(), todo.getContent());
//                    }
        todos.forEach(todo -> System.out.printf("%d / %s\n", todo.getId(), todo.getContent()));
    }

    private void add() {
        long id = todosLastId + 1;
        System.out.print("할일 : ");
        String content = scanner.nextLine().trim();

        Todo todo = new Todo(id, content);
        todos.add(todo);
        todosLastId++;

        System.out.printf("%d번 할일이 생성되었습니다.\n", id);
    }

    private void del() {
        System.out.print("삭제할 할일의 번호 : ");
        long id = Long.parseLong(scanner.nextLine().trim());

        boolean isRemoved = todos.removeIf(todo -> todo.getId() == id);

        if (!isRemoved) {
            System.out.printf("%d번 할일은 존재하지 않습니다.\n", id);
            return;
        }
        System.out.printf("%d번 할일은 삭제되었습니다.\n", id);
    }
}
