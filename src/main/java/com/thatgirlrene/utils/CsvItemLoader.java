package com.thatgirlrene.utils;

import com.thatgirlrene.model.Book;
import com.thatgirlrene.model.LibraryItem;
import com.thatgirlrene.model.Magazine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class CsvItemLoader implements ItemLoader {

    @Override
    public List <LibraryItem> loadItems(String path) throws IOException {
        List<LibraryItem> items = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        try {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()){
                    continue; // ignores blank lines
                }
                String[] parts = line.split(",");
                if (parts.length <3){
                    System.out.println("Skipping invalid line " + line);
                    continue;
                }

            String type = parts[0].trim();
            String id = parts[1].trim();
            String title=parts[2].trim();

            if (type.equals("Book")) {
                items.add(new Book(id, title));
            }else if (type.equals("Magazine")) {
                items.add(new Magazine(id, title));
            }else{
                System.out.println("Uknown item type " + type);
            }
            }
        }finally {
            reader.close();// closes the file evein if an error happens
        }
        return items;
    }
}
