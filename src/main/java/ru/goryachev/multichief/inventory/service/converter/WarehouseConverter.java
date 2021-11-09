package ru.goryachev.multichief.inventory.service.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.goryachev.multichief.inventory.model.dto.CommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.BomCommonDto;
import ru.goryachev.multichief.inventory.model.dto.common.WarehouseCommonDto;
import ru.goryachev.multichief.inventory.model.entity.Bom;
import ru.goryachev.multichief.inventory.model.entity.Warehouse;

/**
 * WarehouseConverter. Converter is used for converting Entity to CommonDto and CommonDto to Entity using common fields.
 * @author Lev Goryachev
 * @version 1.1
 */

@Component
public class WarehouseConverter implements Converter {
    private ModelMapper modelMapper;

      public WarehouseConverter() {
            this.modelMapper = new ModelMapper();
      }

    @Override
    public CommonDto entityToDto (Object warehouse){
          return modelMapper.map(warehouse, WarehouseCommonDto.class);
    }

    @Override
    public Object dtoToEntity (CommonDto warehouseCommonDto) {
          return modelMapper.map(warehouseCommonDto, Warehouse.class);
    }

}
