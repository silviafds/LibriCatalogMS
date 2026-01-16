package com.libri.catalog.adapters.out.service;

import com.libri.catalog.adapters.in.web.dto.response.BookResponse;
import com.libri.catalog.application.ports.in.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogMessageService {

    @Autowired
    private BookService bookService;

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