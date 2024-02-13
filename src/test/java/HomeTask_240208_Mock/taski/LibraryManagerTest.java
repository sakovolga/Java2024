package HomeTask_240208_Mock.taski;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class LibraryManagerTest {
    @InjectMocks
    LibraryManager libraryManager;
    BookRepository bookRepository = mock(BookRepository.class);
    @Mock
    Book book;
    @Mock
    Book book1;
    @Mock
    Book book2;
    @Mock
    Book book3;
    @BeforeEach
    void setUp(){

    }
    @Test
    void findBookByIdTest() {
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        Optional<Book> expected = Optional.ofNullable(book);
        Optional<Book> actual = libraryManager.findBookById("1");
        verify(bookRepository).findById("1");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void findAllBooks() {
        List<Book> bookList = List.of(book, book1, book2, book3);
        when(bookRepository.findAll()).thenReturn(bookList);

        List<Book> actual = libraryManager.findAllBooks();
        verify(bookRepository).findAll();
        Assertions.assertEquals(bookList, actual);
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void findBooksByAuthor() {
    }

    @Test
    void findBookByTitle() {
    }

    @Test
    void findBooksContainingTitle() {
    }

    @Test
    void lendBook() {
    }

    @Test
    void returnBook() {
    }
}