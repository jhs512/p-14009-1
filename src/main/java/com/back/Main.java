package com.back;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        Map<Integer, WiseSaying> sayings = new HashMap<>();
        int nextId = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            }

            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                sayings.put(nextId, new WiseSaying(content, author));
                System.out.printf("%d번 명언이 등록되었습니다.\n", nextId);
                nextId++;
            }

            else if (cmd.equals("목록")) {
                System.out.println("----------------------");

                List<Map.Entry<Integer, WiseSaying>> entryList = new ArrayList<>(sayings.entrySet());
                entryList.sort((a, b) -> b.getKey() - a.getKey()); // id 역순 정렬

                for (Map.Entry<Integer, WiseSaying> entry : entryList) {
                    int id = entry.getKey();
                    WiseSaying ws = entry.getValue();
                    System.out.printf("%d / %s / %s\n", id, ws.author, ws.content);
                }
            }

            else if (cmd.startsWith("삭제?id=")) {
                String idStr = cmd.substring("삭제?id=".length());
                int delId;

                try {
                    delId = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    System.out.println("올바르지 않은 id입니다.");
                    continue;
                }

                if (!sayings.containsKey(delId)) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", delId);
                    continue;
                }

                sayings.remove(delId);
                System.out.printf("%d번 명언이 삭제되었습니다.\n", delId);
            }

            else if (cmd.startsWith("수정?id=")) {
                String idStr = cmd.substring("수정?id=".length());
                int editId;

                try {
                    editId = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    System.out.println("올바르지 않은 id입니다.");
                    continue;
                }

                if (!sayings.containsKey(editId)) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", editId);
                    continue;
                }

                WiseSaying old = sayings.get(editId);

                System.out.printf("명언(기존) : %s\n", old.content);
                System.out.print("명언 : ");
                String newContent = scanner.nextLine().trim();

                System.out.printf("작가(기존) : %s\n", old.author);
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine().trim();

                sayings.put(editId, new WiseSaying(newContent, newAuthor));
            }

            else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        scanner.close();
    }
}