import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class PicturesManagerTest {

    @Test
    public void createPicture() {
        PicturesManager picturesManager = new PicturesManager();

        Picture picture = new Picture();
        picture.setUrl("http://pruebas.com/img.jpg");

        Long id = picturesManager.createPicture(picture);
        Picture createdPicture = picturesManager.getPicture(id);

        assertThat(createdPicture.getId(), equalTo(id));
        assertThat(createdPicture.getUrl(), equalTo("http://pruebas.com/img.jpg"));
    }
}