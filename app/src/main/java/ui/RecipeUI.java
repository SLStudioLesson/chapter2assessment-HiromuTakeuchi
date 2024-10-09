package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // RecipeFileHandlerからレシピを読み込む
        ArrayList<String> recipes = fileHandler.readRecipes();

        // 読み込んだレシピが空かどうか確認
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Recipes:");
            for (String recipe : recipes) {
                // レシピ名と材料に分ける
                String[] recipeParts = recipe.split(",", 2);
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + recipeParts[0]); // レシピ名を表示
                if (recipeParts.length > 1) {
                    System.out.println("Main Ingredients: " + recipeParts[1]); // 主な材料を表示
                } else {
                    System.out.println("Main Ingredients: N/A");
                }
            }
            System.out.println("-----------------------------------");
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    public void addNewRecipe() throws IOException {
        // ユーザーにレシピ名を入力させる
        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        // ユーザーに材料をカンマ区切りで入力させる
        System.out.print("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        // RecipeFileHandlerを使ってレシピをファイルに追加
        fileHandler.addRecipe(recipeName, ingredients);

        // 成功メッセージを表示
        System.out.println("Recipe added successfully.");
    }


    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}

