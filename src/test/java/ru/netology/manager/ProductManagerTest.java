package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.Repository;


import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private final Repository repo = new Repository();
    private final ProductManager manager = new ProductManager(repo);
    private final Product book1 = new Book(1, "В нутри убийцы", 120, "Майк Омер");
    private final Product book2 = new Book(2, "Тьма", 150, "Джон Маррс");
    private final Product book3 = new Book(3, "Тьма", 180, "Стивен Кинг");
    private final Product smartphone1 = new Smartphone(4, "Galaxy S21", 8500, "Samsung");
    private final Product smartphone2 = new Smartphone(5, "Redmi 5+", 3500, "Xiaomi");


    @Test
    public void addBook() {
        manager.add(book1);

        Product[] expected = {book1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addSmartphone() {
        manager.add(smartphone1);

        Product[] expected = {smartphone1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addBookAndSmartphone() {
        manager.add(book1);
        manager.add(smartphone1);

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "Не книга";

        Product[] expected = {};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "В нутри убийцы";

        Product[] expected = {book1};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void nameMatchTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);

        String name = "Тьма";

        Product[] expected = {book2, book3};
        Product[] actual = manager.searchBy(name);
        assertArrayEquals(expected, actual);
    }

}