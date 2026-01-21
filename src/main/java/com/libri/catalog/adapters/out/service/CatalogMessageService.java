package com.libri.catalog.adapters.out.service;

import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.application.ports.in.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling asynchronous communication with other services
 * via RabbitMQ.
 *
 * This class listens to messages sent to the "pedidos.catalog" queue and processes
 * book search requests coming from other microservices (e.g., Bookshelf Service).
 *
 * When a message containing a book ID is received, the service retrieves the
 * corresponding book from the catalog and returns its title as a response.
 */
@Service
public class CatalogMessageService {

    @Autowired
    private BookService bookService;

    /**
     * Receives book search requests from the RabbitMQ queue.
     *
     * This method listens to the "pedidos.catalog" queue, converts the received
     * book ID to a Long, retrieves the book information, and returns the book title.
     *
     * If an error occurs (invalid ID or book not found), an error message is returned.
     *
     * @param bookIdStr Book identifier received as a String message
     * @return Book title if found, or an error message if the operation fails
     */
    @RabbitListener(queues = "pedidos.catalog")
    public String receberPedidoLivro(String bookIdStr) {
        try {
            Long bookId = Long.parseLong(bookIdStr);
            BookResponse livro = bookService.listBookForId(bookId);
            System.out.println("✅ CATALOG: Retornando título: " + livro.getTitle());
            return livro.getTitle();

        } catch (Exception e) {
            System.out.println("❌ CATALOG: Erro: " + e.getMessage());
            return "ERRO: " + e.getMessage();
        }
    }
}