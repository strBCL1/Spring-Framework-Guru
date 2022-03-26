package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher(); // Initialize the publisher
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher); // Save publisher to its repo
//        System.out.println("Amount of publishers in their repo after the publisher's initialization: " +
//                publisherRepository.count());

        // ------------------------------------------------------------------------------------------------------- \\

        Author eric = new Author("Eric", "Evans"); // Initialize Eric Evans
        Book DDD = new Book("Domain Driven Design", "123456"); // Initialize Eric Evans's "DDD" book

        eric.getBooks().add(DDD); // Eric's books.add(DDD)
        DDD.getAuthors().add(eric); // DDD's authors.add(Eric)
        DDD.setPublisher(publisher); // DDD's publisher = The Publisher
        publisher.getBooks().add(DDD); // Publisher's published books.add(DDD)

        authorRepository.save(eric); // Save Eric to authorRepository
        bookRepository.save(DDD); // Save "DDD" to bookRepository
        publisherRepository.save(publisher); // Save the Publisher to publisherRepository

//        System.out.println("Amount of publishers in their repo after DDD's adding: " +
//                publisherRepository.count());

        // ------------------------------------------------------------------------------------------------------- \\

        Author rod = new Author("Rod", "Johnson"); // Initialize Rod Johnson
        Book noEJB = new Book("J2EE", "123456789"); // Initialize Rod Johnson's "J2EE" book

        rod.getBooks().add(noEJB); // Rod's books.add(noEJB)
        noEJB.getAuthors().add(rod); // noEJB's authors.add(rod)
        noEJB.setPublisher(publisher); // noEJB's publisher = The Publisher
        publisher.getBooks().add(noEJB); // Publisher's published books.add(noEJB)

        authorRepository.save(rod); // Save Rod to authorRepository
        bookRepository.save(noEJB); // Save "noEJB" to bookRepository
        publisherRepository.save(publisher); // Amount of publishers in repo doesn't change, since new publisher hasn't been created

        System.out.println("Amount of publishers: " + publisherRepository.count()); // Total amount of publishers in database (publisherRepository)
        System.out.println("Number of books: " + bookRepository.count()); // Total amount of books in bookRepository
        System.out.println("Publisher number of books: " + publisher.getBooks().size()); // Total amount of books The Publisher has published
    }
}
