package pictures;

import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.randomUUID;

public class PicturesManager {

    private Map<Long, PictureDto> pictures;
    private Map<Long, Integer> orders;

    public PicturesManager() {
        this.pictures = new HashMap<>();
        this.orders = new HashMap<>();
    }

    public Long createPicture(PictureDto picture) {
        long id = randomUUID().getLeastSignificantBits();
        picture.setId(id);
        pictures.put(id, picture);
        orders.put(id, lastAssignedOrder() + 1);
        return id;
    }

    private Integer lastAssignedOrder() {
        return orders.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    public PictureDto getPicture(Long id) {
        if (!pictures.containsKey(id)) throw new PictureNotFoundException();
        return pictures.get(id);
    }

    public void selectAsMainPicture(Long id) {
        unSetMainPicture();
        orders.put(id, 1);
    }

    public boolean isMainPicture(Long id) {
        if (!orders.containsKey(id)) throw new PictureNotFoundException();
        return orders.get(id) == 1;
    }

    private void unSetMainPicture() {
        orders.keySet().stream()
                .filter(k -> orders.get(k) == 1)
                .forEach(k -> {
                    int newOrder = lastAssignedOrder() + 1;
                    orders.put(k, newOrder);
                });
    }

    public void removePicture(Long id) {
        if (!pictures.containsKey(id)) throw new PictureNotFoundException();
        pictures.remove(id);
    }
}
