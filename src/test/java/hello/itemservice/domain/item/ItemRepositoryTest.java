package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //Given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }
    @Test
    void findAll(){
        //Given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 30);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1,item2);
    }

    @Test
    void updateItem(){
        //Given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemID = savedItem.getId();

        //when
        Item updateParam = new Item("itemB",20000,30);
        itemRepository.update(itemID,updateParam);

        //then
        Item findItem = itemRepository.findById(itemID);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }


}