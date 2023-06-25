package org.example.mappers;

import org.apache.ibatis.annotations.Select;
import org.example.model.Flashcard;

import java.util.List;

public interface FlashcardMapper {

    @Select("SELECT * FROM flashcards")
    List<Flashcard> getAllFlashcards();

    // Add other methods for CRUD operations

}
