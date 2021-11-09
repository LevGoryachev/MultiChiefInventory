package ru.goryachev.multichief.inventory.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;

/**
 * BomConverter. Converter is used for converting Entity to CommonDto and CommonDto to Entity using common fields.
 * @author Lev Goryachev
 * @version 1.1
 */

@Component
public class BomConverter implements Converter {
    private ModelMapper modelMapper;

      public BomConverter() {
            this.modelMapper = new ModelMapper();
      }

    @Override
    public CommonDto entityToDto (Object bom){
          return modelMapper.map(bom, BomCommonDto.class);
    }

    @Override
    public Object dtoToEntity (CommonDto bomCommonDto) {
          return modelMapper.map(bomCommonDto, Bom.class);
    }

}
