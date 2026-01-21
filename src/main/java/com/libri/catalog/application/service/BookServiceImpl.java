package com.libri.catalog.application.service;

import com.libri.catalog.adapters.in.web.dto.response.BookRegistrationResponse;
import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.application.mapper.BookMapper;
import com.libri.catalog.application.ports.in.service.BookService;
import com.libri.catalog.application.ports.out.repository.BookRepository;
import com.libri.catalog.domain.enums.RegistrationStatus;
import com.libri.catalog.domain.exceptions.BookNotFoundException;
import com.libri.catalog.domain.model.Book;
import com.libri.catalog.domain.vo.BookVo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation responsible for managing book-related business logic.
 *
 * This class handles:
 * - Book registration with validation
 * - Book deletion
 * - Book listing and searching
 * - Partial updates of book data
 *
 * It acts as an intermediary between the controller layer and the persistence layer.
 */
@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * Registers a new book in the catalog.
     *
     * @param bookVo Book data received from the client
     * @return BookRegistrationResponse containing status and message
     */
    @Override
    public BookRegistrationResponse registerBook(BookVo bookVo) {
        BookRegistrationResponse response = new BookRegistrationResponse();

        try {
            if (!isValid(bookVo)) {
                response.setStatus(400);
                response.setMessage(String.valueOf(RegistrationStatus.MISSING_DATA.getDefaultMessage()));
                return response;
            }

            String validationError = validateBookData(bookVo);
            if (validationError != null) {
                response.setStatus(400);
                response.setMessage(String.valueOf(RegistrationStatus.VALIDATION_ERROR.getDefaultMessage()));
                return response;
            }

            Book book = bookMapper.toBook(bookVo);
            bookRepository.save(book);

            response.setStatus(200);
            response.setMessage(String.valueOf(RegistrationStatus.SUCCESS.getDefaultMessage()));

        } catch (DataIntegrityViolationException e) {
            response.setStatus(400);
            response.setMessage(String.valueOf(RegistrationStatus.DATABASE_ERROR.getDefaultMessage()));

        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage(String.valueOf(RegistrationStatus.UNKNOWN_ERROR.getDefaultMessage()));
        }

        return response;

    }

    /**
     * Deletes a book by its ID.
     *
     * @param idBook Book identifier
     * @return Confirmation response
     * @throws BookNotFoundException if the book does not exist
     */
    @Override
    public BookRegistrationResponse deleteBook(Long idBook) {
        if (!bookRepository.existsById(idBook)) {
            throw new BookNotFoundException(idBook);
        }

        Book book = bookRepository.findById(idBook).get();
        bookRepository.delete(book);

        BookRegistrationResponse response = new BookRegistrationResponse();
        response.setStatus(200);
        response.setMessage("Livro removido do banco de dados.");
        return response;
    }

    /**
     * Retrieves all books from the catalog.
     *
     * @return List of books as response DTOs
     */
    @Override
    public List<BookResponse> listAllBooks() {
        List<Book> book = bookRepository.findAll();

        return bookMapper.bookListToBookResponseList(book);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param idBook Book identifier
     * @return Book response DTO
     * @throws BookNotFoundException if the book does not exist
     */
    @Override
    public BookResponse listBookForId(Long idBook) {
        if (!bookRepository.existsById(idBook)) {
            throw new BookNotFoundException(idBook);
        }

        Book book = bookRepository.findById(idBook).get();

        return bookMapper.bookToBookResponse(book);

    }

    /**
     * Partially updates an existing book.
     *
     * @param vo Book data to be updated
     * @return Updated book response
     * @throws BookNotFoundException if the book does not exist
     */
    @Override
    public BookResponse partialUpdate(BookVo vo) {
        BookRegistrationResponse response = new BookRegistrationResponse();

        if (!bookRepository.existsById(vo.getId())) {
            throw new BookNotFoundException(vo.getId());
        }

        bookRepository.updateBook(vo.getId(), vo.getTitle(), vo.getAuthor(), vo.getDescription(), vo.getSynopsis(),
                vo.getPageCount(), vo.getPublisher(), vo.getType());

        Book updatedBook = bookRepository.findById(vo.getId()).get();

        return bookMapper.bookToBookResponse(updatedBook);
    }

    /**
     * Searches for a book by its title.
     *
     * @param title Book title
     * @return Book response DTO
     * @throws BookNotFoundException if no book is found
     */
    @Override
    public BookResponse searchBookByTitle(String title) {
        Book book = bookRepository.searchBookByTitle(title);

        if (book == null) {
            throw new BookNotFoundException("Livro com título '" + title + "' não existe no catálogo");
        }

        return bookMapper.bookToBookResponse(book);
    }

    /**
     * Validates required book fields.
     */
    private boolean isValid(BookVo bookVo) {
        return bookVo.getTitle() != null && !bookVo.getTitle().trim().isEmpty() &&
                bookVo.getAuthor() != null && !bookVo.getAuthor().trim().isEmpty() &&
                bookVo.getType() != null;
    }

    /**
     * Validates business rules for book data.
     *
     * @return Validation error message or null if valid
     */
    private String validateBookData(BookVo bookVo) {
        if (bookVo.getTitle().length() > 200) {
            return "Título não pode exceder 200 caracteres";
        }

        if (bookVo.getAuthor().length() > 100) {
            return "Nome do autor não pode exceder 100 caracteres";
        }

        if (bookVo.getPageCount() != null && bookVo.getPageCount() <= 0) {
            return "Número de páginas deve ser positivo";
        }

        return null;
    }
}
