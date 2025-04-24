import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {
    static class Task {
        String text;
        String createdAt;

        Task(String text) {
            this.text = text;
            this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("=== ToDoリストアプリ ===");
        System.out.println("コマンド: add, list, delete, exit");

        while (true) {
            System.out.print("\n> コマンドを入力: ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("追加するタスクを入力: ");
                    String taskText = scanner.nextLine().trim();
                    if (taskText.isEmpty()) {
                        System.out.println("⚠ タスクが空です。もう一度入力してください。");
                    } else {
                        tasks.add(new Task(taskText));
                        System.out.println("✅ タスクを追加しました。");
                    }
                    break;

                case "list":
                    System.out.println("=== タスク一覧 ===");
                    if (tasks.isEmpty()) {
                        System.out.println("📭 タスクはまだありません。");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).text + "（追加日時: " + tasks.get(i).createdAt + "）");
                        }
                    }
                    break;

                case "delete":
                    if (tasks.isEmpty()) {
                        System.out.println("⚠ 削除できるタスクがありません。");
                        break;
                    }
                    System.out.print("削除するタスクの番号: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task removed = tasks.remove(index);
                            System.out.println("🗑️ 削除: " + removed.text);
                        } else {
                            System.out.println("⚠ 無効な番号です。");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("⚠ 数字を入力してください。");
                    }
                    break;

                case "exit":
                    System.out.println("👋 アプリを終了します。お疲れさまでした！");
                    return;

                default:
                    System.out.println("❓ 無効なコマンドです。使えるコマンド: add, list, delete, exit");
            }
        }
    }
}
