package com.recipebook.util;

import com.recipebook.model.Ingredient;
import com.recipebook.model.Recipe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class FileExporter {
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
    private final Path directory;

    public FileExporter(Path directory) {
        if (directory == null) throw new IllegalArgumentException("Export directory is required.");
        this.directory = directory;
    }

    public Path exportRecipe(Recipe recipe) throws IOException {
        if (recipe == null) throw new IllegalArgumentException("Recipe is required.");
        String content = recipe.toExportText();
        return writeUnique(safeName(recipe.getName()) + "_recipe", content);
    }

    public Path exportShoppingList(Recipe recipe, List<Ingredient> ingredients) throws IOException {
        if (recipe == null || ingredients == null) throw new IllegalArgumentException("Recipe and ingredients are required.");
        StringBuilder content = new StringBuilder("Shopping List: ")
                .append(recipe.getName()).append(System.lineSeparator()).append(System.lineSeparator());
        ingredients.forEach(item -> content.append("- ").append(item).append(System.lineSeparator()));
        return writeUnique(safeName(recipe.getName()) + "_shopping_list", content.toString());
    }

    private Path writeUnique(String baseName, String content) throws IOException {
        Files.createDirectories(directory);
        String timestamp = LocalDateTime.now().format(TIMESTAMP);
        Path candidate = directory.resolve(baseName + "_" + timestamp + ".txt");
        int counter = 1;
        while (Files.exists(candidate)) {
            candidate = directory.resolve(baseName + "_" + timestamp + "_" + counter + ".txt");
            counter++;
        }
        try {
            return Files.writeString(candidate, content, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (java.nio.file.FileAlreadyExistsException ex) {
            return writeUnique(baseName, content);
        }
    }

    private String safeName(String name) {
        return name.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "_").replaceAll("^_+|_+$", "");
    }
}
