package ru.goryachev.multichief.inventory.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.ImOrderCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.model.entity.ImOrder;

/**
 * ImOrderConverter. Converter is used for converting Entity to CommonDto and CommonDto to Entity using common fields.
 * @author Lev Goryachev
 * @version 1.1
 */

@Component
public class ImOrderConverter {
    private ModelMapper modelMapper;

      public ImOrderConverter() {
            this.modelMapper = new ModelMapper();
      }

    public ImOrderCommonDto entityToDto (ImOrder imOrder){
          return modelMapper.map(imOrder, ImOrderCommonDto.class);
    }

    public ImOrder dtoToEntity (ImOrderCommonDto imOrderCommonDto) {
          return modelMapper.map(imOrderCommonDto, ImOrder.class);
    }

}
