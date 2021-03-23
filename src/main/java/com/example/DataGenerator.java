package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataGenerator {

    public static void main(String[] args) {

        for (int i = 1; i < 100; i++) {
            int postId = 1 + i/10;
            String sqlInsert = String.format("insert into comment(id, post_id, content, created) \n" +
                    "values (%d, %d, 'Comment %d', '" + LocalDateTime.now().minusDays(i) + "');\n", i, postId, i);

            System.out.print(sqlInsert);
        }

    }

}
