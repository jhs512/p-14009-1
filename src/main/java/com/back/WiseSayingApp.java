package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class WiseSayingApp {
    static ArrayList<WiseSaying> wiseSayingList = new ArrayList<>();

    public static void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd;
            cmd = sc.nextLine().trim();
            if (cmd.equals("종료")) {
                break;
            }

            switch (cmd) {
                case "종료":
                    return;
                case "등록":
                    System.out.print("명언 : ");
                    String wiseSayingContent = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String wiseSayingAuthor = sc.nextLine().trim();
                    if (wiseSayingList.isEmpty()) {
                        wiseSayingList.add(new WiseSaying(1, wiseSayingContent, wiseSayingAuthor));
                    } else {
                        wiseSayingList.add(new WiseSaying(wiseSayingList.getLast().id + 1, wiseSayingContent, wiseSayingAuthor));
                    }
                    System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayingList.getLast().id);
                    break;
                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-----------------------------");
                    wiseSayingList.forEach(System.out::println);
                    break;
                case "삭제":
                    System.out.print("?id = ");
                    int willingDeleteId = sc.nextInt();
                    sc.nextLine();
                    boolean isRemoved = wiseSayingList.removeIf(wiseSaying ->   // removeIf() 각 컬랙션에 대해 함수형 인터페이스를 사용해 true를 반환하는 요소를 제거하며 제거한 요소가 있다면 true, 그렇지 않다면 false를 반환한다.
                            wiseSaying.id.equals(willingDeleteId));
                    if (!isRemoved) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", willingDeleteId);
                    } else {
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", willingDeleteId);
                    }
                    break;
                case "수정":
                    System.out.print("?id = ");
                    int willingEditId = sc.nextInt();
                    sc.nextLine();
                    WiseSaying result = wiseSayingList.stream()
                            .filter(wiseSaying -> wiseSaying.id.equals(willingEditId))
                            .findFirst()
                            .orElse(null);
                    if (result == null) {
                        System.out.printf("%d번 명언은 존재하지 않습니다.\n", willingEditId);
                    } else {
                        System.out.println("명언(기존) : " + result.content);
                        System.out.print("명언 : ");
                        result.content = sc.nextLine().trim();
                        System.out.println("작가(기존) : " + result.author);
                        System.out.print("작가 : ");
                        result.author = sc.nextLine().trim();
                    }
                    break;

            }
        }
        sc.close();
    }
}
