import java.util.HashMap;
import java.util.Map;

import static java.util.UUID.randomUUID;

public class PicturesManager {

    private Map<Long, Picture> pictures;

    public PicturesManager() {
        this.pictures = new HashMap<Long, Picture>();
    }

    public Long createPicture(Picture picture) {
        long id = randomUUID().getLeastSignificantBits();
        picture.setId(id);
        pictures.put(id, picture);
        return id;
    }

    public Picture getPicture(Long id) {
        return pictures.get(id);
    }
}
