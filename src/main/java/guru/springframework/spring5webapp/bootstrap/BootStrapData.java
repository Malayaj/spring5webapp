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

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository , PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher penguine = new Publisher("Penguine Publications", "332 Picadeli Circus", "London","33432");
        publisherRepository.save(penguine);
        Publisher rupa = new Publisher("Rupa & co.", "53 Chandni Chowk", "New Delhi","110011");
        publisherRepository.save(rupa);
        Book demonsAndAngels = new Book("Demons And Angels","283SSJDK22");
        Book heightOfEve = new Book ("Height of the Mount Everest", "32334ASGSD");
        Author neol = new Author("Neol","Simpson");
        Author magna = new Author("Magna", "Roberts");


        neol.getBooks().add(demonsAndAngels);
        demonsAndAngels.getAuthors().add(neol);
        demonsAndAngels.setPublisher(penguine);
        penguine.getBooks().add(demonsAndAngels);
        authorRepository.save(neol);
        bookRepository.save(demonsAndAngels);
        publisherRepository.save(penguine);

        magna.getBooks().add(heightOfEve);
        heightOfEve.getAuthors().add(magna);
        heightOfEve.setPublisher(rupa);
        rupa.getBooks().add(heightOfEve);
        authorRepository.save(magna);
        bookRepository.save(heightOfEve);
        publisherRepository.save(rupa);

        System.out.println("Started in BootStrap");
        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of publishers: "+ publisherRepository.count());
        System.out.println("Number of books for publisher for Rupa & co: "+ rupa.getBooks().size());
        System.out.println("Number of books for publisher for Penguine: "+ penguine.getBooks().size());



    }
}
