package com.wssh.Application.Mapping;

import java.util.ArrayList;
import java.util.List;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseDto(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListDto(List<O> origin, Class<D> destination){
        List<D> destinationDto = new ArrayList<D>();
        for(O o : origin){
            destinationDto.add(mapper.map(o, destination));
        }
        return destinationDto;
    }

}
