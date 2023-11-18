package jpabook2.jpashop.domain.service;

import jakarta.transaction.Transactional;
import jpabook2.jpashop.domain.item.Album;
import jpabook2.jpashop.domain.item.Book;
import jpabook2.jpashop.domain.item.Item;
import jpabook2.jpashop.domain.item.Movie;
import jpabook2.jpashop.repository.ItemRepository;
import jpabook2.jpashop.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장")
    void saveItem() {
        // given
        Album album = new Album();
        album.setArtist("정국");
        album.setEtc("bts");
        album.setName("Standing Next to You");
        album.setPrice(10);
        album.setStockQuantity(1);

        // when
        itemService.saveItem(album);

        // then
        Album saveItem = (Album) itemRepository.findOne(album.getId());
        assertEquals("정국", saveItem.getArtist());
    }

    @Test
    @DisplayName("상품_단건_조회")
    void findOne() {
        // given
        Book book = new Book();
        book.setName("돈의속성");
        book.setPrice(17800);
        book.setStockQuantity(1);
        book.setAuthor("김승호");
        book.setIsbn("none");
        itemService.saveItem(book);

        // when
        Item findItem = itemRepository.findOne(book.getId());

        // then
        assertEquals(book.getId(), findItem.getId());
    }

    @Test
    @DisplayName("상품_전체_조회")
    void findItems() {
        // given
        Movie movie1 = new Movie();
        movie1.setName("이터널선샤인");
        movie1.setPrice(100);
        movie1.setStockQuantity(1);
        movie1.setDirector("미셀 공드리");
        movie1.setActor("커스틴 던스트");

        Movie movie2 = new Movie();
        movie2.setName("트루먼쇼");
        movie2.setPrice(200);
        movie2.setStockQuantity(0);
        movie2.setDirector("피터위어");
        movie2.setActor("짐캐리");

        itemService.saveItem(movie1);
        itemService.saveItem(movie2);

        // when
        List<Item> findAllItems = itemRepository.findAll();

        // then
        assertEquals(2, findAllItems.size());
        assertTrue(findAllItems.stream().anyMatch(item ->
                item.getName().equals("이터널선샤인") &&
                        ((Movie) item).getDirector().equals("미셀 공드리") &&
                        ((Movie) item).getActor().equals("커스틴 던스트")));
    }
}