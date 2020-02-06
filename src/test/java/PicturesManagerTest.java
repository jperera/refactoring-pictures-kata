import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pictures.PictureDto;
import pictures.PictureNotFoundException;
import pictures.PicturesManager;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PicturesManagerTest {

    private PicturesManager picturesManager;

    @BeforeEach
    public void setUp() {
        picturesManager = new PicturesManager();
    }

    @Test
    public void createPicture() {
        PictureDto picture1 = new PictureDto();
        picture1.setUrl("http://pruebas.com/img.jpg");
        PictureDto picture = picture1;

        Long id = picturesManager.createPicture(picture);
        PictureDto createdPicture = picturesManager.getPicture(id);

        assertThat(createdPicture.getId(), equalTo(id));
        assertThat(createdPicture.getUrl(), equalTo("http://pruebas.com/img.jpg"));
    }

    @Test
    public void uniquePictureIsMainPicture() {
        PictureDto picture = onePicture();

        Long id = picturesManager.createPicture(picture);

        assertThat(picturesManager.isMainPicture(id), equalTo(true));
    }

    @Test
    public void selectAsMainPicture() {
        PictureDto picture1 = onePicture();
        PictureDto picture2 = otherPicture();

        Long id1 = picturesManager.createPicture(picture1);
        Long id2 = picturesManager.createPicture(picture2);
        picturesManager.selectAsMainPicture(id2);

        assertThat(picturesManager.isMainPicture(id1), equalTo(false));
        assertThat(picturesManager.isMainPicture(id2), equalTo(true));
    }

    @Test
    public void removePicture() {
        PictureDto picture = onePicture();

        Long id = picturesManager.createPicture(picture);
        picturesManager.removePicture(id);

        assertThrows(PictureNotFoundException.class, () ->
            picturesManager.getPicture(id)
        );
    }

    @Test
    public void removeNotExistingPicture() {
        assertThrows(PictureNotFoundException.class, () ->
            picturesManager.removePicture(42L)
        );
    }

    private PictureDto onePicture() {
        PictureDto picture = new PictureDto();
        picture.setUrl("http://pruebas.com/img.jpg");
        return picture;
    }

    private PictureDto otherPicture() {
        PictureDto picture = new PictureDto();
        picture.setUrl("http://pruebas2.com/img2.jpg");
        return picture;
    }

}