package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//컴포넌트 스캔의 대상
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();//static
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        //업데이트용 객체를 하나 더 만드는게 낫다...?
       Item findItem = findById(itemId);
       findItem.setItemName(updateParam.getItemName());
       findItem.setPrice(updateParam.getPrice());
       findItem.setQuantity(updateParam.getQuantity());

    }
    public void clearStore(){
        store.clear();
    }

}