package ru.goryachev.multichief.inventory.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.goryachev.multichief.inventory.model.dto.common.MaterialCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Material;

/**
 * MaterialConverter. Converter is used for converting Entity to CommonDto and CommonDto to Entity using common fields.
 * @author Lev Goryachev
 * @version 1.1
 */

@Component
public class MaterialConverter {
    private ModelMapper modelMapper;

      public MaterialConverter() {
            this.modelMapper = new ModelMapper();
      }

    public MaterialCommonDto entityToDto (Material material){
          return modelMapper.map(material, MaterialCommonDto.class);
    }

    public Material dtoToEntity (MaterialCommonDto materialCommonDto) {
          return modelMapper.map(materialCommonDto, Material.class);
    }

}
