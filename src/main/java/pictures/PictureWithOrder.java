package pictures;

public class PictureWithOrder {
    private Integer order;
    private PictureDto pictureDto;

    public PictureWithOrder(Integer order, PictureDto pictureDto) {
        this.order = order;
        this.pictureDto = pictureDto;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public PictureDto getPictureDto() {
        return pictureDto;
    }

    public void setPictureDto(PictureDto pictureDto) {
        this.pictureDto = pictureDto;
    }
}
