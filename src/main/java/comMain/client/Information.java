package comMain.client;

import comMain.entities.BookEntity;
import comMain.entities.ReadersEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;

public class Information {

    public static DefaultListModel<BookEntity> getAllBooks(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/book";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<BookEntity>> responseType = new ParameterizedTypeReference<ArrayList<BookEntity>>() {};
        ArrayList<BookEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<BookEntity> booksListModel = new DefaultListModel<>();
        for (BookEntity book : response) {
            booksListModel.addElement(book);
        }

        return booksListModel;
    }

    public static DefaultListModel<ReadersEntity> getAllReaders(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/readers";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<ArrayList<ReadersEntity>> responseType = new ParameterizedTypeReference<ArrayList<ReadersEntity>>() {};
        ArrayList<ReadersEntity> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
        DefaultListModel<ReadersEntity> readersListModel = new DefaultListModel<>();
        for (ReadersEntity reader : response) {
            readersListModel.addElement(reader);
        }

        System.out.println(response);
        return readersListModel;
    }

}
