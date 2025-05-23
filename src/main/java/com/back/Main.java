package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<String> contents = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();

        int lastId = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                lastId++;
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();

                index.add(lastId);
                contents.add(wiseSayingContent);
                authors.add(wiseSayingAuthor);

                System.out.println(lastId + "번 명언이 등록되었습니다.");

            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("---------------------");

                for (int i = contents.size() - 1; i >= 0; i--) {
                    System.out.println(index.get(i) + " / " + authors.get(i) + " / " + contents.get(i));
                }

            } else if (cmd.startsWith("삭제?id=")) {
                String[] partsForRemove = cmd.split("=");
                if (partsForRemove.length == 2 && partsForRemove[1].matches("\\d+")) {
                    int idToRemove = Integer.parseInt(partsForRemove[1]);
                    boolean found = false;

                    for (int i = 0; i < index.size(); i++) {
                        if (index.get(i) == idToRemove) {
                            index.remove(i);
                            contents.remove(i);
                            authors.remove(i);
                            System.out.println(idToRemove + "번 명언이 삭제되었습니다.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println(idToRemove + "번 명언은 존재하지 않습니다.");
                    }
                }

            } else if (cmd.startsWith("수정?id=")) {
                String[] partsIndex = cmd.split("=");
                if (partsIndex.length == 2 && partsIndex[1].matches("\\d+")) {
                    int targetId = Integer.parseInt(partsIndex[1]);

                    int foundIndex = -1;
                    for (int i = 0; i < index.size(); i++) {
                        if (index.get(i) == targetId) {
                            foundIndex = i;
                            break;
                        }
                    }

                    if (foundIndex == -1) {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    } else {
                        System.out.println("명언(기존) : " + contents.get(foundIndex));
                        System.out.print("명언 : ");
                        String newContent = scanner.nextLine().trim();

                        System.out.println("작가(기존) : " + authors.get(foundIndex));
                        System.out.print("작가 : ");
                        String newAuthor = scanner.nextLine().trim();

                        contents.set(foundIndex, newContent);
                        authors.set(foundIndex, newAuthor);
                    }
                }
            }
        }

        scanner.close();
    }
}
