package pictures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.randomUUID;

public class PicturesManager {

    private Map<Long, PictureWithOrder> pictures;

    public PicturesManager() {
        this.pictures = new HashMap<>();
    }

    public Long createPicture(PictureDto picture) {
        long id = randomUUID().getLeastSignificantBits();
        picture.setId(id);
        PictureWithOrder pictureWithOrder = new PictureWithOrder(lastAssignedOrder() + 1, picture);
        pictures.put(id, pictureWithOrder);
        return id;
    }

    private Integer lastAssignedOrder() {
        return pictures.values()
                .stream()
                .max(Comparator.comparing(PictureWithOrder::getOrder))
                .map(PictureWithOrder::getOrder)
                .orElse(0);
    }

    public PictureDto getPicture(Long id) {
        return getPictureWithOrder(id).getPictureDto();
    }

    private PictureWithOrder getPictureWithOrder(Long id) {
        if (!pictures.containsKey(id)) throw new PictureNotFoundException();
        return pictures.get(id);
    }

    public void selectAsMainPicture(Long id) {
        unSetMainPicture();
        PictureWithOrder pictureWithOrder = getPictureWithOrder(id);
        pictureWithOrder.setOrder(1);
    }

    public boolean isMainPicture(Long id) {
        return getPictureWithOrder(id).getOrder() == 1;
    }

    private void unSetMainPicture() {
        pictures.values()
                .stream()
                .filter(p -> p.getOrder() == 1)
                .forEach(p -> p.setOrder(lastAssignedOrder() + 1));
    }

    public void removePicture(Long id) {
        if (!pictures.containsKey(id)) throw new PictureNotFoundException();
        pictures.remove(id);
    }
}
