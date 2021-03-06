package kr.co.deundeun.groopy.dto.common.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageRequestDto {

    private final int DEFAULT_SIZE = 10;

    private int page;

    private int size;

    private Property property;

    private Sort.Direction direction;

    public PageRequestDto(){
        page = 1;
        size = DEFAULT_SIZE;
        property = Property.DATE;
        direction = Sort.Direction.DESC;
    }

    public void setPage(int page){
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size){
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setProperty(Property property){
        this.property = property;
    }

    public void setDirection(Sort.Direction direction){
        this.direction = direction;
    }

    public PageRequest of(){
        return PageRequest.of(page - 1, size, Sort.by(direction, property.getColumn()));
    }

}
